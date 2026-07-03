package net.java.Springbt_restapi.service;

import net.java.Springbt_restapi.dto.request.UserRequestDTO;
import net.java.Springbt_restapi.dto.response.UserResponseDTO;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface UserService {
    UserResponseDTO createEmployee(UserRequestDTO userRequestDTO) throws AccessDeniedException;

    UserResponseDTO getEmployeeByEeid(String eeid) throws AccessDeniedException;

    List<UserResponseDTO> getAllEmployees() throws AccessDeniedException;

    UserResponseDTO updateEmployee(String eeid, UserRequestDTO userRequestDTO) throws AccessDeniedException;

    void deleteEmployee(String eeid) throws AccessDeniedException;
}
