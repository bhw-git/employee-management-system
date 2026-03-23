package net.java.Springbt_restapi.service.impl;

import lombok.AllArgsConstructor;
import net.java.Springbt_restapi.dto.EmployeeDto;
import net.java.Springbt_restapi.entity.DepartmentEntity;
import net.java.Springbt_restapi.entity.EmployeeEntity;
import net.java.Springbt_restapi.exception.ResourceNotFoundException;
import net.java.Springbt_restapi.mapper.DepartmentMapper;
import net.java.Springbt_restapi.mapper.EmployeeMapper;
import net.java.Springbt_restapi.repository.DepartmentRepository;
import net.java.Springbt_restapi.repository.EmployeeRepository;
import net.java.Springbt_restapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        EmployeeEntity employeeEntity = EmployeeMapper.mapToEmployeeEntity(employeeDto);
        DepartmentEntity departmentEntity = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department Id doesn't exist" + employeeDto.getDepartmentId()));
        employeeEntity.setDepartmentEntity(departmentEntity);
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findByIdWithDepartment(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee ID doesn't exists" + id));
        return EmployeeMapper.mapToEmployeeDto(employeeEntity);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeEntity> employeesList =  employeeRepository.findAllWithDepartment();
        return employeesList.stream().map((employeeEntity) -> EmployeeMapper.mapToEmployeeDto(employeeEntity))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto updatedEmployee) {
        EmployeeEntity employeeEntity = employeeRepository.findByIdWithDepartment(id).orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist" + id));

        employeeEntity.setFirstName(updatedEmployee.getFirstName());
        employeeEntity.setLastName(updatedEmployee.getLastName());
        employeeEntity.setEmail(updatedEmployee.getEmail());

        DepartmentEntity departmentEntity = departmentRepository.findById(updatedEmployee.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department Id doesn't exist" + updatedEmployee.getDepartmentId()));
        employeeEntity.setDepartmentEntity(departmentEntity);
        EmployeeEntity updatedEmployeeEntity = employeeRepository.save(employeeEntity);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeEntity);
    }

    @Override
    public void deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist" + id));
        employeeRepository.delete(employeeEntity);

//        Other method
//        employeeRepository.deleteById(id);
    }

}
