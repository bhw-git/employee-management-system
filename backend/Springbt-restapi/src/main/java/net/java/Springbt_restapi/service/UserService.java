package net.java.Springbt_restapi.service;

import net.java.Springbt_restapi.dto.request.UserRequestDTO;
import net.java.Springbt_restapi.dto.request.UserUpdateDTO;
import net.java.Springbt_restapi.dto.response.UserResponseDTO;
import org.springframework.data.domain.Page;

import java.nio.file.AccessDeniedException;

public interface UserService {
    UserResponseDTO createEmployee(UserRequestDTO userRequestDTO) throws AccessDeniedException;

    UserResponseDTO getEmployeeByEeid(String eeid) throws AccessDeniedException;

    Page<UserResponseDTO> getAllEmployees(int page, int size, String sort) throws AccessDeniedException;

    UserResponseDTO updateEmployee(String eeid, UserUpdateDTO userUpdateDTO) throws AccessDeniedException;

    void deleteEmployee(String eeid) throws AccessDeniedException;
}
