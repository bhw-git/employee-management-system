package net.java.Springbt_restapi.service;

import net.java.Springbt_restapi.dto.request.EmployeeUpdateRequestDTO;
import net.java.Springbt_restapi.dto.response.EmployeeResponseDTO;


public interface EmployeeService {

    EmployeeResponseDTO getEmployeeByEeid(String eeid);

    EmployeeResponseDTO updateEmployee(String eeid, EmployeeUpdateRequestDTO employeeUpdateRequestDTO);
}
