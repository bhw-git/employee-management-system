package net.java.Springbt_restapi.service.impl;

import lombok.AllArgsConstructor;
import net.java.Springbt_restapi.dto.DepartmentDto;
import net.java.Springbt_restapi.entity.DepartmentEntity;
import net.java.Springbt_restapi.exception.ResourceNotFoundException;
import net.java.Springbt_restapi.mapper.DepartmentMapper;
import net.java.Springbt_restapi.repository.DepartmentRepository;
import net.java.Springbt_restapi.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        DepartmentEntity departmentEntity = DepartmentMapper.mapToDepartmentEntity(departmentDto);
        DepartmentEntity savedDepartment = departmentRepository.save(departmentEntity);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department ID doesn't exist" + id));
        return DepartmentMapper.mapToDepartmentDto(departmentEntity);
    }

    @Override
    public List<DepartmentDto> getAllDepartment() {
        List<DepartmentEntity> listOfDepartment = departmentRepository.findAll();
        return listOfDepartment.stream().map((departmentEntity) -> DepartmentMapper.mapToDepartmentDto(departmentEntity)).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The Department ID doesn't exist" +id));

        departmentEntity.setDepartmentName(departmentDto.getDepartmentName());
        departmentEntity.setDepartmentDescription(departmentDto.getDepartmentDescription());

        DepartmentEntity departmentEntity1 =  departmentRepository.save(departmentEntity);
        return DepartmentMapper.mapToDepartmentDto(departmentEntity1);
    }

    @Override
    public void deleteDepartment(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department ID is not valid : " +id));
        departmentRepository.delete(departmentEntity);
//        try{
//            departmentRepository.deleteById(id);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
}
