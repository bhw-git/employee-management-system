package net.java.Springbt_restapi.service.impl;

import lombok.AllArgsConstructor;
import net.java.Springbt_restapi.dto.request.EmployeeUpdateRequestDTO;
import net.java.Springbt_restapi.dto.response.EmployeeResponseDTO;
import net.java.Springbt_restapi.entity.EmployeeEntity;
import net.java.Springbt_restapi.entity.UserEntity;
import net.java.Springbt_restapi.exception.ResourceNotFoundException;
import net.java.Springbt_restapi.mapper.EmployeeMapper;
import net.java.Springbt_restapi.repository.EmployeeRepository;
import net.java.Springbt_restapi.repository.UserRepository;
import net.java.Springbt_restapi.service.EmployeeService;
import net.java.Springbt_restapi.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final FileStorageService fileStorageService;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    @Override
    public EmployeeResponseDTO getEmployeeByEeid(String eeid) {
        UserEntity userEntity = userRepository.findByEeid(eeid)
                .orElseThrow(() -> new ResourceNotFoundException("Employee EEID doesn't exist" +eeid));
        EmployeeEntity employeeEntity = employeeRepository.findByUserEntity(userEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Employee EEID doesn't exists" + eeid));
        return EmployeeMapper.mapToEmployeeResponseDTO(employeeEntity);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(String eeid, EmployeeUpdateRequestDTO employeeUpdateRequestDTO) {
        UserEntity userEntity = userRepository.findByEeid(eeid)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist in User Repo " + eeid));
        EmployeeEntity employeeEntity = employeeRepository.findByUserEntity(userEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist in Employee Repo " + eeid));

        employeeEntity.setFirstName(employeeUpdateRequestDTO.getFirstName());
        employeeEntity.setLastName(employeeUpdateRequestDTO.getLastName());
        employeeEntity.setPersonalEmail(employeeUpdateRequestDTO.getPersonalEmail());
        employeeEntity.setDob(employeeUpdateRequestDTO.getDob());
        employeeEntity.setGender(employeeUpdateRequestDTO.getGender());
        employeeEntity.setPhone(employeeUpdateRequestDTO.getPhone());

        //Handle Multipart Form Data of Uploaded Photo
        MultipartFile photo = employeeUpdateRequestDTO.getProfilePhoto();
        if(photo!= null && !photo.isEmpty()){
            String fileName = fileStorageService.save(photo);
            employeeEntity.setProfilePhotoURL(fileName);
        }

//        employeeEntity.setRole(employeeEntity.getRole());
//        employeeEntity.setEmpStatus(employeeEntity.getEmpStatus());
//        employeeEntity.setOfficialEmail(employeeEntity.getOfficialEmail());
//        employeeEntity.setCreatedAt(employeeEntity.getCreatedAt());
//        employeeEntity.setUpdatedAt(employeeEntity.getUpdatedAt());
//        employeeEntity.setDepartmentEntity(employeeEntity.getDepartmentEntity());
//        EmployeeMapper.updateEntityFromDTO(employeeUpdateRequestDTO, employeeEntity);
//        if(employeeUpdateRequestDTO.getDepartmentId()!=null){
//            DepartmentEntity departmentEntity = departmentRepository.findById(employeeUpdateRequestDTO.getDepartmentId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Department id doesn't exist" + employeeUpdateRequestDTO.getDepartmentId()));
//            employeeEntity.setDepartmentEntity(departmentEntity);
//        }
        userEntity.setFirstName(employeeUpdateRequestDTO.getFirstName());
        userEntity.setLastName(employeeUpdateRequestDTO.getLastName());
        userEntity.setUpdatedDate(LocalDateTime.now());
        userRepository.save(userEntity);
        employeeEntity.setUserEntity(userEntity);
        EmployeeEntity updatedEmployeeEntity = employeeRepository.save(employeeEntity);
        return EmployeeMapper.mapToEmployeeResponseDTO(updatedEmployeeEntity);
    }
}
