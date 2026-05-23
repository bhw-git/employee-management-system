package net.java.Springbt_restapi.controller;

import jakarta.validation.Valid;
import net.java.Springbt_restapi.dto.request.EmployeeCreateRequestDTO;
import net.java.Springbt_restapi.dto.request.EmployeeUpdateRequestDTO;
import net.java.Springbt_restapi.dto.response.EmployeeResponseDTO;
import net.java.Springbt_restapi.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Building Add Employee using RestAPI
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee( @RequestBody EmployeeCreateRequestDTO employeeCreateRequestDTO){
        EmployeeResponseDTO savedEmployee = employeeService.createEmployee(employeeCreateRequestDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Building Get Employee by ID using RESTAPI
    @GetMapping("{eeid}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable String eeid){
        EmployeeResponseDTO getEmployee = employeeService.getEmployeeByEeid(eeid);
        return ResponseEntity.ok(getEmployee);
    }

    //Building Get All Employees using RESTAPI
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(){
        List<EmployeeResponseDTO> employeesList = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeesList);
    }

    //Building Put Employee using RESTAPI
    @PatchMapping("{eeid}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable String eeid, @RequestBody EmployeeUpdateRequestDTO employeeUpdateRequestDTO){
        EmployeeResponseDTO employeeDto1 = employeeService.updateEmployee(eeid,employeeUpdateRequestDTO);
        return ResponseEntity.ok(employeeDto1);
    }

    @DeleteMapping("{eeid}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String eeid){
        employeeService.deleteEmployee(eeid);
        return ResponseEntity.ok("Employee Deleted Successfully");
    }
}
