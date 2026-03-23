package net.java.Springbt_restapi.mapper;

import net.java.Springbt_restapi.dto.EmployeeDto;
import net.java.Springbt_restapi.entity.EmployeeEntity;

public class EmployeeMapper {

    //This maps Employee Entity into the EmployeeDTO
    //The Entity is converted to DTO
    public static EmployeeDto mapToEmployeeDto(EmployeeEntity employeeEntity){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employeeEntity.getId());
        employeeDto.setFirstName(employeeEntity.getFirstName());
        employeeDto.setLastName(employeeEntity.getLastName());
        employeeDto.setEmail(employeeEntity.getEmail());

        if (employeeEntity.getDepartmentEntity() != null) {
            employeeDto.setDepartment(employeeEntity.getDepartmentEntity().getDepartmentName());
            employeeDto.setDepartmentId(employeeEntity.getDepartmentEntity().getId());
        } else {
            employeeDto.setDepartment(null);
            employeeDto.setDepartmentId(null);
        }
        return employeeDto;
    }

    //This maps EmployeeDto into the Employee Entity
    //The DTO is converted to Entity
    public static EmployeeEntity mapToEmployeeEntity(EmployeeDto employeeDto){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employeeDto.getId());
        employeeEntity.setFirstName(employeeDto.getFirstName());
        employeeEntity.setLastName(employeeDto.getLastName());
        employeeEntity.setEmail(employeeDto.getEmail());
        return employeeEntity;
    }
}
