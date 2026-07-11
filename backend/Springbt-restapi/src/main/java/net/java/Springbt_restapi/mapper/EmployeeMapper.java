package net.java.Springbt_restapi.mapper;

import net.java.Springbt_restapi.dto.request.EmployeeUpdateRequestDTO;
import net.java.Springbt_restapi.dto.response.EmployeeResponseDTO;
import net.java.Springbt_restapi.entity.EmployeeEntity;

public class EmployeeMapper {

    //This maps EmployeeDto into the Employee Entity
    //The DTO is converted to Entity
//    public static EmployeeEntity mapToEmployeeEntity(EmployeeUpdateRequestDTO updateDTO){
//        EmployeeEntity employeeEntity = new EmployeeEntity();
//        employeeEntity.setFirstName(updateDTO.getFirstName());
//        employeeEntity.setLastName(updateDTO.getLastName());
//        employeeEntity.setPersonalEmail(updateDTO.getPersonalEmail());
//        employeeEntity.setDob(updateDTO.getDob());
//        employeeEntity.setGender(updateDTO.getGender());
////        employeeEntity.setProfilePhotoURL(String.valueOf(updateDTO.getProfilePhoto()));
//        employeeEntity.setPhone(updateDTO.getPhone());
//        return employeeEntity;
//    }

    //Maps Entity back into ResponseDTO
    public static EmployeeResponseDTO mapToEmployeeResponseDTO(EmployeeEntity employeeEntity){
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();

        responseDTO.setEeid(String.valueOf(employeeEntity.getId()));
        responseDTO.setFirstName(employeeEntity.getFirstName());
        responseDTO.setLastName(employeeEntity.getLastName());
        responseDTO.setOfficialEmail(employeeEntity.getOfficialEmail());
        responseDTO.setPersonalEmail(employeeEntity.getPersonalEmail());
        responseDTO.setDob(employeeEntity.getDob());
        responseDTO.setGender(employeeEntity.getGender());
        responseDTO.setEmpStatus(employeeEntity.getEmpStatus());
        responseDTO.setProfilePhotoURL(employeeEntity.getProfilePhotoURL());
        responseDTO.setRole(employeeEntity.getRole());
        responseDTO.setPhone(employeeEntity.getPhone());
        responseDTO.setSalary(employeeEntity.getSalary());
        responseDTO.setJoiningDate(employeeEntity.getCreatedAt());
        responseDTO.setUpdatedAt(employeeEntity.getUpdatedAt());
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

    //This maps EmployeeDto into the Employee Entity
    //The DTO is converted to Entity
    public static void updateEntityFromDTO(EmployeeUpdateRequestDTO dto, EmployeeEntity entity){
        if (dto.getFirstName() != null) {
            entity.setFirstName(dto.getFirstName());
        }

        if (dto.getLastName() != null) {
            entity.setLastName(dto.getLastName());
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

        if(dto.getPhone() != null){
            entity.setPhone(dto.getPhone());
        }

        // Department update
//        if (dto.getDepartmentId() != null) {
//            DepartmentEntity departmentEntity = new DepartmentEntity();
//            departmentEntity.setId(dto.getDepartmentId());
//            entity.setDepartmentEntity(departmentEntity);
//        }
    }
}