package net.java.Springbt_restapi.mapper;

import net.java.Springbt_restapi.dto.request.EmployeeCreateRequestDTO;
import net.java.Springbt_restapi.dto.request.EmployeeUpdateRequestDTO;
import net.java.Springbt_restapi.dto.response.EmployeeResponseDTO;
import net.java.Springbt_restapi.entity.EmployeeEntity;

public class EmployeeMapper {

    //This maps EmployeeDto into the Employee Entity
    //The DTO is converted to Entity
    public static EmployeeEntity mapToEmployeeEntity(EmployeeCreateRequestDTO createDTO){
        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setFirstName(createDTO.getFirstName());
        employeeEntity.setLastName(createDTO.getLastName());
        employeeEntity.setOfficialEmail(createDTO.getOfficialEmail());
        employeeEntity.setPersonalEmail(createDTO.getPersonalEmail());
        employeeEntity.setDob(createDTO.getDob());
        employeeEntity.setGender(createDTO.getGender());
        employeeEntity.setEmpStatus(createDTO.getEmpStatus());
        employeeEntity.setProfilePhotoURL(String.valueOf(createDTO.getProfilePhoto()));
        employeeEntity.setRole(createDTO.getRole());
        return employeeEntity;
    }

    public static EmployeeResponseDTO mapToEmployeeResponseDTO(EmployeeEntity employeeEntity){
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();

        responseDTO.setEeid(employeeEntity.getEeid());
        responseDTO.setFirstName(employeeEntity.getFirstName());
        responseDTO.setLastName(employeeEntity.getLastName());
        responseDTO.setOfficialEmail(employeeEntity.getOfficialEmail());
        responseDTO.setPersonalEmail(employeeEntity.getPersonalEmail());
        responseDTO.setDob(employeeEntity.getDob());
        responseDTO.setGender(employeeEntity.getGender());
        responseDTO.setEmpStatus(employeeEntity.getEmpStatus());
        responseDTO.setProfilePhotoURL(employeeEntity.getProfilePhotoURL());
        responseDTO.setRole(employeeEntity.getRole());
        if(employeeEntity.getDepartmentEntity()!=null){
            responseDTO.setDepartment(
                    employeeEntity
                        .getDepartmentEntity()
                        .getDepartmentName());
            responseDTO.setDepartmentId(
                    employeeEntity
                            .getDepartmentEntity()
                            .getId());
        }
        return responseDTO;
    }

    public static void updateEntityFromDTO(EmployeeUpdateRequestDTO dto, EmployeeEntity entity){
        if (dto.getFirstName() != null) {
            entity.setFirstName(dto.getFirstName());
        }

        if (dto.getLastName() != null) {
            entity.setLastName(dto.getLastName());
        }

        if (dto.getOfficialEmail() != null) {
            entity.setOfficialEmail(dto.getOfficialEmail());
        }

        if (dto.getPersonalEmail() != null) {
            entity.setPersonalEmail(dto.getPersonalEmail());
        }

        if (dto.getDob() != null) {
            entity.setDob(dto.getDob());
        }

        if (dto.getGender() != null) {
            entity.setGender(dto.getGender());
        }

        if (dto.getEmpStatus() != null) {
            entity.setEmpStatus(dto.getEmpStatus());
        }

        if (dto.getRole() != null){
            entity.setRole(dto.getRole());
        }

        // Department update
//        if (dto.getDepartmentId() != null) {
//            DepartmentEntity departmentEntity = new DepartmentEntity();
//            departmentEntity.setId(dto.getDepartmentId());
//            entity.setDepartmentEntity(departmentEntity);
//        }
    }
}