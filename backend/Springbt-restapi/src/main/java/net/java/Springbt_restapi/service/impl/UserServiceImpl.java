package net.java.Springbt_restapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.java.Springbt_restapi.dto.request.UserRequestDTO;
import net.java.Springbt_restapi.dto.request.UserUpdateDTO;
import net.java.Springbt_restapi.dto.response.UserResponseDTO;
import net.java.Springbt_restapi.entity.DepartmentEntity;
import net.java.Springbt_restapi.entity.EmployeeEntity;
import net.java.Springbt_restapi.entity.UserEntity;
import net.java.Springbt_restapi.entity.Users;
import net.java.Springbt_restapi.enums.CredentialStatus;
import net.java.Springbt_restapi.enums.EmployeeStatus;
import net.java.Springbt_restapi.enums.Role;
import net.java.Springbt_restapi.exception.ResourceNotFoundException;
import net.java.Springbt_restapi.mapper.UserMapper;
import net.java.Springbt_restapi.repository.EmployeeRepository;
import net.java.Springbt_restapi.repository.UserCredentialRepository;
import net.java.Springbt_restapi.repository.UserRepository;
import net.java.Springbt_restapi.repository.DepartmentRepository;
import net.java.Springbt_restapi.service.UserService;
import net.java.Springbt_restapi.validation.RolePermissionValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolePermissionValidator rolePermissionValidator;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public UserResponseDTO createEmployee(UserRequestDTO userRequestDTO) throws AccessDeniedException {

        Role creatorRole = getLoggedInUserRole();
        rolePermissionValidator.validateUserCreation(creatorRole, userRequestDTO.getRole());

        UserEntity userEntity = UserMapper.mapToUserEntity(userRequestDTO);
        DepartmentEntity departmentEntity = departmentRepository.findById(userRequestDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department Name Not Found" +userRequestDTO.getDepartmentId()));
        //Get role from DTO else set a default role as "ROLE_EMPLOYEE"
        Role role = Optional.ofNullable(userRequestDTO.getRole()).orElse(Role.ROLE_EMPLOYEE);
        userEntity.setEeid(generateUUID());
        userEntity.setAccountNonLocked(true);
        userEntity.setAccountNonExpired(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setCredentialExpiryDate(null);
        userEntity.setAccountExpiryDate(null);
        userEntity.setSignUpMethod("Email");
        userEntity.setEmpStatus(EmployeeStatus.ACTIVE);
        userEntity.setRole(role);
        userEntity.setCreatedDate(LocalDateTime.now());
        userEntity.setUpdatedDate(LocalDateTime.now());
        userEntity.setDepartmentEntity(departmentEntity);
        UserEntity savedEntity = userRepository.save(userEntity);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(userRequestDTO.getFirstName());
        employeeEntity.setLastName(userRequestDTO.getLastName());
        employeeEntity.setOfficialEmail(userRequestDTO.getOfficialEmail());
        employeeEntity.setEmpStatus(userRequestDTO.getEmpStatus());
        employeeEntity.setRole(role);
        employeeEntity.setCreatedAt(LocalDate.now());
        employeeEntity.setUpdatedAt(LocalDate.now());
        employeeEntity.setDepartmentEntity(departmentEntity);
        employeeEntity.setUserEntity(savedEntity);
        employeeRepository.save(employeeEntity);

        Users credential = new Users();
        credential.setUserEntity(savedEntity);
        credential.setEmail(savedEntity.getOfficialEmail());
        credential.setPasswordHash(passwordEncoder.encode("welcome@123"));
        credential.setStatus(CredentialStatus.ACTIVE);
        credential.setMustChangePassword(true);
        credential.setLastLogin(LocalDateTime.now());
        userCredentialRepository.save(credential);
        return UserMapper.mapToUserDTO(savedEntity);
    }

    @Override
    public UserResponseDTO getEmployeeByEeid(String eeid) throws AccessDeniedException {
        UserEntity userEntity = userRepository.findByEeid(eeid)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist" + eeid));
        //Role based security validation
        rolePermissionValidator.validateHierarchy(getLoggedInUserRole(),userEntity.getRole());
        return UserMapper.mapToUserDTO(userEntity);
    }

    @Override
    public Page<UserResponseDTO> getAllEmployees(int page, int size, String sortDirection) throws AccessDeniedException {

        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by("createdDate").descending() : Sort.by("createdDate").ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Role loggedInRole = getLoggedInUserRole();
        Set<Role> roles = rolePermissionValidator.getAccessibleRoles(loggedInRole);
        Page<UserEntity> users = userRepository.findByRoleIn(roles, pageable);
        return users.map(UserMapper::mapToUserDTO);
    }

    @Override
    public UserResponseDTO updateEmployee(String eeid, UserUpdateDTO userUpdateDTO) throws AccessDeniedException {
        UserEntity userEntity = userRepository.findByEeid(eeid)
                .orElseThrow(() -> new ResourceNotFoundException("No Employee found in UserRepository" +eeid));
        EmployeeEntity employeeEntity = employeeRepository.findByUserEntity(userEntity)
                .orElseThrow(() -> new ResourceNotFoundException("No Employee found in EmployeeRepository" +eeid));
        DepartmentEntity departmentEntity = departmentRepository.findById(userUpdateDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department Name Not Found" +userUpdateDTO.getDepartmentId()));

        //Role based User Validation
        if(getLoggedInUserRole() == Role.ROLE_SUPER_ADMIN || getLoggedInUserRole() == Role.ROLE_ADMIN){
            userEntity.setRole(userUpdateDTO.getRole());
        }
        rolePermissionValidator.validateHierarchy(getLoggedInUserRole(),userEntity.getRole());
        userEntity.setFirstName(userUpdateDTO.getFirstName());
        userEntity.setLastName(userUpdateDTO.getLastName());
        userEntity.setOfficialEmail(userUpdateDTO.getOfficialEmail());
        userEntity.setAccountNonLocked(userUpdateDTO.isAccountNonLocked());
        userEntity.setAccountNonExpired(userUpdateDTO.isAccountNonExpired());
        userEntity.setCredentialsNonExpired(userUpdateDTO.isCredentialsNonExpired());
        userEntity.setCredentialExpiryDate(userUpdateDTO.getCredentialExpiryDate());
        userEntity.setAccountExpiryDate(userUpdateDTO.getAccountExpiryDate());
        userEntity.setSignUpMethod(userUpdateDTO.getSignUpMethod());
        userEntity.setEmpStatus(userUpdateDTO.getEmpStatus());
        userEntity.setRole(userUpdateDTO.getRole());
        userEntity.setCreatedDate(LocalDateTime.now());
        userEntity.setUpdatedDate(LocalDateTime.now());
        userEntity.setDepartmentEntity(departmentEntity);
        UserEntity saveUserEntity = userRepository.save(userEntity);
        UserMapper.mapToUserDTO(saveUserEntity);

        employeeEntity.setOfficialEmail(userUpdateDTO.getOfficialEmail());
        employeeEntity.setEmpStatus(userUpdateDTO.getEmpStatus());
        employeeEntity.setRole(userUpdateDTO.getRole());
        employeeEntity.setSalary(userUpdateDTO.getSalary());
        employeeEntity.setDepartmentEntity(departmentEntity);
        employeeRepository.save(employeeEntity);
        return UserMapper.mapToUserDTO(saveUserEntity);
    }

    @Override
    public void deleteEmployee(String eeid) throws AccessDeniedException {
        UserEntity userEntity = userRepository.findByEeid(eeid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found in userEntity table" + eeid));
        Users credential = userCredentialRepository.findByUserEntity_Eeid(eeid)
                        .orElseThrow(() -> new ResourceNotFoundException("Credentials not found in Users table" + eeid));
        //Role based user validation
        rolePermissionValidator.validateHierarchy(getLoggedInUserRole(),userEntity.getRole());
        userCredentialRepository.delete(credential);
        userRepository.delete(userEntity);
    }

    private Role getLoggedInUserRole() throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .map(Role::valueOf)
                .orElseThrow(() -> new AccessDeniedException("User has no assigned role."));
    }

    public String generateUUID(){
        return "EMP-" + UUID.randomUUID()
                .toString()
                .substring(0,7)
                .toUpperCase();
    }
}
