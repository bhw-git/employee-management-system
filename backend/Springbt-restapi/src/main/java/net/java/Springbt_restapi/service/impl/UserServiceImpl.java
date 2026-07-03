package net.java.Springbt_restapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.java.Springbt_restapi.dto.request.UserRequestDTO;
import net.java.Springbt_restapi.dto.response.UserResponseDTO;
import net.java.Springbt_restapi.entity.DepartmentEntity;
import net.java.Springbt_restapi.entity.UserEntity;
import net.java.Springbt_restapi.entity.Users;
import net.java.Springbt_restapi.enums.CredentialStatus;
import net.java.Springbt_restapi.enums.EmployeeStatus;
import net.java.Springbt_restapi.enums.Role;
import net.java.Springbt_restapi.exception.ResourceNotFoundException;
import net.java.Springbt_restapi.mapper.UserMapper;
import net.java.Springbt_restapi.repository.UserCredentialRepository;
import net.java.Springbt_restapi.repository.UserRepository;
import net.java.Springbt_restapi.repository.DepartmentRepository;
import net.java.Springbt_restapi.service.UserService;
import net.java.Springbt_restapi.validation.RolePermissionValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolePermissionValidator rolePermissionValidator;

    @Override
    @Transactional
    public UserResponseDTO createEmployee(UserRequestDTO userRequestDTO) throws AccessDeniedException {

        Role creatorRole = getLoggedInUserRole();
        rolePermissionValidator.validateUserCreation(creatorRole, userRequestDTO.getRole());

        UserEntity userEntity = UserMapper.mapToUserEntity(userRequestDTO);
        DepartmentEntity departmentEntity = departmentRepository.findById(userRequestDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department Name Not Found" +userRequestDTO.getDepartmentId()));
        userEntity.setEeid(generateUUID());
        userEntity.setAccountNonLocked(true);
        userEntity.setAccountNonExpired(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setCredentialExpiryDate(null);
        userEntity.setAccountExpiryDate(null);
        userEntity.setSignUpMethod("Email");
        userEntity.setEmpStatus(EmployeeStatus.ACTIVE);
//        userEntity.setRole(userRequestDTO.getRole());
        userEntity.setCreatedDate(LocalDateTime.now());
        userEntity.setUpdatedDate(LocalDateTime.now());
        userEntity.setDepartmentEntity(departmentEntity);
        UserEntity savedEntity = userRepository.save(userEntity);

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
    public List<UserResponseDTO> getAllEmployees() throws AccessDeniedException {
        List<UserEntity> userEntities = userRepository.findAll();
        // Role based security validation
        Role loggedInRole = getLoggedInUserRole();
        return userEntities
                .stream()
                .filter(user -> rolePermissionValidator.canAccess(
                        loggedInRole, user.getRole()))
                .map(UserMapper::mapToUserDTO)
                .toList();
    }

    @Override
    public UserResponseDTO updateEmployee(String eeid, UserRequestDTO userRequestDTO) throws AccessDeniedException {
        UserEntity userEntity = userRepository.findByEeid(eeid)
                .orElseThrow(() -> new ResourceNotFoundException("No Employee found" +eeid));
        DepartmentEntity departmentEntity = departmentRepository.findById(userRequestDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department Name Not Found" +userRequestDTO.getDepartmentId()));

        //Role based User Validation
        if(getLoggedInUserRole() == Role.ROLE_SUPER_ADMIN || getLoggedInUserRole() == Role.ROLE_ADMIN){
            userEntity.setRole(userRequestDTO.getRole());
        }
        rolePermissionValidator.validateHierarchy(getLoggedInUserRole(),userEntity.getRole());
        userEntity.setFirstName(userRequestDTO.getFirstName());
        userEntity.setLastName(userRequestDTO.getLastName());
        userEntity.setOfficialEmail(userRequestDTO.getOfficialEmail());
        userEntity.setAccountNonLocked(userRequestDTO.isAccountNonLocked());
        userEntity.setAccountNonExpired(userRequestDTO.isAccountNonExpired());
        userEntity.setCredentialsNonExpired(userRequestDTO.isCredentialsNonExpired());
        userEntity.setCredentialExpiryDate(userRequestDTO.getCredentialExpiryDate());
        userEntity.setAccountExpiryDate(userRequestDTO.getAccountExpiryDate());
        userEntity.setSignUpMethod(userRequestDTO.getSignUpMethod());
        userEntity.setEmpStatus(userRequestDTO.getEmpStatus());
        userEntity.setRole(userRequestDTO.getRole());
        userEntity.setCreatedDate(LocalDateTime.now());
        userEntity.setUpdatedDate(LocalDateTime.now());
        userEntity.setDepartmentEntity(departmentEntity);
        UserEntity savedEntity = userRepository.save(userEntity);
        return UserMapper.mapToUserDTO(savedEntity);
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
    //Generate Employee UUID
    private String generateUUID(){
        return "EMP-" + UUID.randomUUID()
                .toString()
                .substring(0,7)
                .toUpperCase();
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
}
