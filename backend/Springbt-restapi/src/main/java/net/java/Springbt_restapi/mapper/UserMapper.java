package net.java.Springbt_restapi.mapper;

import net.java.Springbt_restapi.dto.request.UserRequestDTO;
import net.java.Springbt_restapi.dto.response.UserResponseDTO;
import net.java.Springbt_restapi.entity.UserEntity;

public class UserMapper {

    public static UserEntity mapToUserEntity(UserRequestDTO adminDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(adminDTO.getFirstName());
        userEntity.setLastName(adminDTO.getLastName());
        userEntity.setOfficialEmail(adminDTO.getOfficialEmail());
        userEntity.setAccountNonLocked(adminDTO.isAccountNonLocked());
        userEntity.setAccountNonExpired(adminDTO.isAccountNonExpired());
        userEntity.setCredentialsNonExpired(adminDTO.isCredentialsNonExpired());
        userEntity.setCredentialExpiryDate(adminDTO.getCredentialExpiryDate());
        userEntity.setAccountExpiryDate(adminDTO.getAccountExpiryDate());
        userEntity.setSignUpMethod(adminDTO.getSignUpMethod());
        userEntity.setEmpStatus(adminDTO.getEmpStatus());
        userEntity.setRole(adminDTO.getRole());
        userEntity.setCreatedDate(adminDTO.getCreatedDate());
        userEntity.setUpdatedDate(adminDTO.getUpdatedDate());

        return userEntity;
    }

    public static UserResponseDTO mapToUserDTO(UserEntity userEntity){
        UserResponseDTO adminDTO = new UserResponseDTO();
        adminDTO.setEmpId(userEntity.getEeid());
        adminDTO.setFirstName(userEntity.getFirstName());
        adminDTO.setLastName(userEntity.getLastName());
        adminDTO.setOfficialEmail(userEntity.getOfficialEmail());
        adminDTO.setAccountNonLocked(userEntity.isAccountNonLocked());
        adminDTO.setAccountNonExpired(userEntity.isAccountNonExpired());
        adminDTO.setCredentialsNonExpired(userEntity.isCredentialsNonExpired());
        adminDTO.setCredentialExpiryDate(userEntity.getCredentialExpiryDate());
        adminDTO.setAccountExpiryDate(userEntity.getAccountExpiryDate());
        adminDTO.setSignUpMethod(userEntity.getSignUpMethod());
        adminDTO.setEmpStatus(userEntity.getEmpStatus());
        adminDTO.setRole(userEntity.getRole());
        adminDTO.setCreatedDate(userEntity.getCreatedDate());
        adminDTO.setUpdatedDate(userEntity.getUpdatedDate());
        if(userEntity.getDepartmentEntity() != null){
            adminDTO.setDepartmentId(
                    userEntity
                            .getDepartmentEntity()
                            .getId()
            );
        }

        return adminDTO;
    }
}
