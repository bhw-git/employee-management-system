package net.java.Springbt_restapi.controller;

import lombok.AllArgsConstructor;
import net.java.Springbt_restapi.dto.DepartmentDto;
import net.java.Springbt_restapi.entity.DepartmentEntity;
import net.java.Springbt_restapi.service.DepartmentService;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto saveDepartment = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentbyId(@PathVariable Long id){
        DepartmentDto getDepartment = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(getDepartment,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartment(){
        List<DepartmentDto> getAllDepartment = departmentService.getAllDepartment();
        return new ResponseEntity<>(getAllDepartment, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto){
        DepartmentDto updateDepartment = departmentService.updateDepartment(id, departmentDto);
        return ResponseEntity.ok(updateDepartment);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department Deleted Successfully");
    }

}
