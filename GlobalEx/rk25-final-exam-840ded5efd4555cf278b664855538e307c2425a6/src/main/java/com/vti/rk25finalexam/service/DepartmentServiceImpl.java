package com.vti.rk25finalexam.service;

import com.vti.rk25finalexam.entity.Department;
import com.vti.rk25finalexam.entity.dto.DepartmentDTO;
import com.vti.rk25finalexam.repository.DepartmentRepository;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    
    private final DepartmentRepository deptRepo;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(
        DepartmentRepository deptRepo,
        ModelMapper modelMapper
    ) {
        this.deptRepo = deptRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Department> getAll() {
        return deptRepo.findAll();
    }

    @Override
    public Optional<Department> getOne(Integer id) {
        return deptRepo.findById(id);
    }

    @Override
    public Department create(Department department) {
        return null;
    }

    @Override
    public Department update(Integer id, Department account) throws NotFoundException {
        return null;
    }

    @Override
    public Optional<DepartmentDTO> getOneReturnDTO(Integer id) {
        return getOne(id).map(department -> modelMapper.map(department, DepartmentDTO.class));
    }

    @Override
    public Department delete(Integer id) throws NotFoundException {
        return null;
    }
}
