package net.java.Springbt_restapi.controller;

import lombok.RequiredArgsConstructor;
import net.java.Springbt_restapi.dto.request.EmployeeUpdateRequestDTO;
import net.java.Springbt_restapi.dto.response.EmployeeResponseDTO;
import net.java.Springbt_restapi.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping({"/api/employees","/api/v1/employees"})
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    //Building Get Employee by ID using RESTAPI
    @GetMapping("{eeid}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable String eeid){
        EmployeeResponseDTO getEmployee = employeeService.getEmployeeByEeid(eeid);
        return ResponseEntity.ok(getEmployee);
    }

    //Building Put Employee using RESTAPI
    @PatchMapping(value = "{eeid}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable String eeid, @ModelAttribute EmployeeUpdateRequestDTO employeeUpdateRequestDTO){
        EmployeeResponseDTO employeeDto = employeeService.updateEmployee(eeid,employeeUpdateRequestDTO);
        return ResponseEntity.ok(employeeDto);
    }

    //Building Add Employee using RestAPI
//    @PostMapping
//    public ResponseEntity<EmployeeResponseDTO> createEmployee( @RequestBody EmployeeCreateRequestDTO employeeCreateRequestDTO){
//        EmployeeResponseDTO savedEmployee = employeeService.createEmployee(employeeCreateRequestDTO);
//        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
//    }
    //Update to accept Multipart form data
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<EmployeeResponseDTO> createEmployee( @ModelAttribute EmployeeCreateRequestDTO employeeCreateRequestDTO){
//        EmployeeResponseDTO savedEmployee = employeeService.createEmployee(employeeCreateRequestDTO);
//        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
//    }

//Building Get All Employees using RESTAPI
//    @GetMapping
//    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(){
//        List<EmployeeResponseDTO> employeesList = employeeService.getAllEmployees();
//        return ResponseEntity.ok(employeesList);
//    }

//    @DeleteMapping("{eeid}")
//    public ResponseEntity<String> deleteEmployee(@PathVariable String eeid){
//        employeeService.deleteEmployee(eeid);
//        return ResponseEntity.ok("Employee Deleted Successfully");
//    }
}
