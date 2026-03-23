package net.java.Springbt_restapi.mapper;

import net.java.Springbt_restapi.dto.DepartmentDto;
import net.java.Springbt_restapi.entity.DepartmentEntity;

public class DepartmentMapper {

    //To map Departments Dto into JPA Entity
    //The Dto is converted into Entity
    public static DepartmentEntity mapToDepartmentEntity(DepartmentDto departmentDto){
        return new DepartmentEntity(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription()
        );
    }

    //To map Departments JPA Entity into Dto
    //The Entity is converted to DTO
    public static DepartmentDto mapToDepartmentDto(DepartmentEntity departmentEntity){
        return new DepartmentDto(
                departmentEntity.getId(),
                departmentEntity.getDepartmentName(),
                departmentEntity.getDepartmentDescription()
        );
    }
}
