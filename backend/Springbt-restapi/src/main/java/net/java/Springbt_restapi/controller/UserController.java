package net.java.Springbt_restapi.controller;

import lombok.RequiredArgsConstructor;
import net.java.Springbt_restapi.dto.request.UserRequestDTO;
import net.java.Springbt_restapi.dto.response.UserResponseDTO;
import net.java.Springbt_restapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping({"/api/users","/api/v1/users"})
public class UserController {

    private final UserService userService;

    //Building Add Employee using RestAPI
    @PostMapping
    public ResponseEntity<UserResponseDTO> createEmployee(@RequestBody UserRequestDTO userRequestDTO) throws AccessDeniedException {
        UserResponseDTO saveEmployee = userService.createEmployee(userRequestDTO);
        return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
    }

    //Update to accept Multipart form data
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<EmployeeResponseDTO> createEmployee( @ModelAttribute EmployeeCreateRequestDTO employeeCreateRequestDTO){
//        EmployeeResponseDTO savedEmployee = employeeService.createEmployee(employeeCreateRequestDTO);
//        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
//    }

    //Building Get Employee by ID using RESTAPI
    @GetMapping("{eeid}")
    public ResponseEntity<UserResponseDTO> getEmployee(@PathVariable String eeid) throws AccessDeniedException {
        UserResponseDTO getEmployee = userService.getEmployeeByEeid(eeid);
        return ResponseEntity.ok(getEmployee);
    }

    //Building Get All Employees using RESTAPI
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllEmployees() throws AccessDeniedException {
        List<UserResponseDTO> employeesList = userService.getAllEmployees();
        return ResponseEntity.ok(employeesList);
    }

    //Building Put Employee using RESTAPI
    @PatchMapping(value = "{eeid}")
    // , consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    public ResponseEntity<UserResponseDTO> updateEmployee(
            @PathVariable String eeid,
            @RequestBody UserRequestDTO userRequestDTO)
            throws AccessDeniedException {
        UserResponseDTO employeeDto = userService.updateEmployee(eeid, userRequestDTO);
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("{eeid}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String eeid) throws AccessDeniedException {
        userService.deleteEmployee(eeid);
        return ResponseEntity.ok("Employee Deleted Successfully");
    }
}
