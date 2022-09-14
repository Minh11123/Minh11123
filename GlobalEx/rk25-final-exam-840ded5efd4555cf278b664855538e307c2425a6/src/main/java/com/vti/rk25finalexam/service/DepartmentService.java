package com.vti.rk25finalexam.service;

import com.vti.rk25finalexam.entity.Account;
import com.vti.rk25finalexam.entity.Department;
import com.vti.rk25finalexam.entity.dto.DepartmentDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface DepartmentService {
    List<Department> getAll();

    Optional<Department> getOne(Integer id);

    Department create(Department department);

    Department update(Integer id, Department account) throws NotFoundException;

    Department delete(Integer id) throws NotFoundException;

    Optional<DepartmentDTO> getOneReturnDTO(Integer id);
}
