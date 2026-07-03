package net.java.Springbt_restapi.service.impl;

import lombok.AllArgsConstructor;
import net.java.Springbt_restapi.dto.request.EmployeeCreateRequestDTO;
import net.java.Springbt_restapi.dto.request.EmployeeUpdateRequestDTO;
import net.java.Springbt_restapi.dto.response.EmployeeResponseDTO;
import net.java.Springbt_restapi.entity.DepartmentEntity;
import net.java.Springbt_restapi.entity.EmployeeEntity;
import net.java.Springbt_restapi.exception.ResourceNotFoundException;
import net.java.Springbt_restapi.mapper.EmployeeMapper;
import net.java.Springbt_restapi.repository.DepartmentRepository;
import net.java.Springbt_restapi.repository.EmployeeRepository;
import net.java.Springbt_restapi.service.EmployeeService;
import net.java.Springbt_restapi.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final FileStorageService fileStorageService;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeResponseDTO createEmployee(EmployeeCreateRequestDTO employeeCreateRequestDTO) {
        EmployeeEntity employeeEntity = EmployeeMapper.mapToEmployeeEntity(employeeCreateRequestDTO);
        employeeEntity.setEeid(generateUUID());
        DepartmentEntity departmentEntity = departmentRepository.findById(employeeCreateRequestDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department Id doesn't exist" + employeeCreateRequestDTO.getDepartmentId()));
        employeeEntity.setDepartmentEntity(departmentEntity);
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        //Handle Multipart Form Data for ProfilePhoto
//        MultipartFile photo = employeeCreateRequestDTO.getProfilePhoto();
//        if(photo!=null && !photo.isEmpty()){
//            String fileName = fileStorageService.save(photo);
//            employeeEntity.setProfilePhotoURL(fileName);
//        }
        return EmployeeMapper.mapToEmployeeResponseDTO(savedEmployee);
    }

    @Override
    public EmployeeResponseDTO getEmployeeByEeid(String eeid) {
        EmployeeEntity employeeEntity = employeeRepository.findByEeidWithDepartment(eeid)
                .orElseThrow(() -> new ResourceNotFoundException("Employee EEID doesn't exists" + eeid));
        return EmployeeMapper.mapToEmployeeResponseDTO(employeeEntity);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<EmployeeEntity> employeesList =  employeeRepository.findAllWithDepartment();
        return employeesList.stream().map((employeeEntity) -> EmployeeMapper.mapToEmployeeResponseDTO(employeeEntity))
                .collect(Collectors.toList()).reversed();
    }

    @Override
    public EmployeeResponseDTO updateEmployee(String eeid, EmployeeUpdateRequestDTO employeeUpdateRequestDTO) {
        EmployeeEntity employeeEntity = employeeRepository.findByEeidWithDepartment(eeid)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist" + eeid));

        employeeEntity.setFirstName(employeeUpdateRequestDTO.getFirstName());
        employeeEntity.setLastName(employeeUpdateRequestDTO.getLastName());
        employeeEntity.setOfficialEmail(employeeUpdateRequestDTO.getOfficialEmail());
        employeeEntity.setPersonalEmail(employeeUpdateRequestDTO.getPersonalEmail());
        employeeEntity.setDob(employeeUpdateRequestDTO.getDob());
        employeeEntity.setGender(employeeUpdateRequestDTO.getGender());
        //Handle Multipart Form Data of Uploaded Photo
        MultipartFile photo = employeeUpdateRequestDTO.getProfilePhoto();
        if(photo!= null && !photo.isEmpty()){
            String fileName = fileStorageService.save(photo);
            employeeEntity.setProfilePhotoURL(fileName);
        }
        EmployeeMapper.updateEntityFromDTO(employeeUpdateRequestDTO, employeeEntity);
        if(employeeUpdateRequestDTO.getDepartmentId()!=null){
            DepartmentEntity departmentEntity = departmentRepository.findById(employeeUpdateRequestDTO.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department Id doesn't exist" + employeeUpdateRequestDTO.getDepartmentId()));
            employeeEntity.setDepartmentEntity(departmentEntity);
        }
        EmployeeEntity updatedEmployeeEntity = employeeRepository.save(employeeEntity);
        return EmployeeMapper.mapToEmployeeResponseDTO(updatedEmployeeEntity);
    }

    @Override
    public void deleteEmployee(String eeid) {
        EmployeeEntity employeeEntity = employeeRepository.findByEeidWithDepartment(eeid).orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist" + eeid));
        employeeRepository.delete(employeeEntity);

//        Other method
//        employeeRepository.deleteById(id);
    }
    //Generate Employee UUID
    private String generateUUID(){
        return "EMP-" + UUID.randomUUID()
                .toString()
                .substring(0,7)
                .toUpperCase();
    }
}
