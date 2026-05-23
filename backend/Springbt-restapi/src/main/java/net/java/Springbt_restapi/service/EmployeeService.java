package net.java.Springbt_restapi.service;

import net.java.Springbt_restapi.dto.request.EmployeeCreateRequestDTO;
import net.java.Springbt_restapi.dto.request.EmployeeUpdateRequestDTO;
import net.java.Springbt_restapi.dto.response.EmployeeResponseDTO;

import java.util.List;


public interface EmployeeService {
    EmployeeResponseDTO createEmployee(EmployeeCreateRequestDTO employeeCreateRequestDTO);

    EmployeeResponseDTO getEmployeeByEeid(String eeid);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO updateEmployee(String eeid, EmployeeUpdateRequestDTO employeeUpdateRequestDTO);

    void deleteEmployee(String eeid);
}
