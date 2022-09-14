package com.vti.rk25finalexam.controller;

import com.vti.rk25finalexam.entity.Department;
import com.vti.rk25finalexam.entity.dto.DepartmentDTO;
import com.vti.rk25finalexam.service.DepartmentService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public ResponseEntity<List<Department>> getAll() {
        List<Department> DepartmentList = departmentService.getAll();
        return ResponseEntity
            .ok()
            .body(DepartmentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DepartmentDTO>> getOne(@PathVariable Integer id) {
        Optional<DepartmentDTO> departmentDTO = departmentService.getOneReturnDTO(id);
        return ResponseEntity
            .ok()
            .body(departmentDTO);
    }

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody Department Department) {
        Department responseDepartment = departmentService.create(Department);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(responseDepartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(
        @PathVariable Integer id,
        @RequestBody Department Department
    )
        throws NotFoundException {
        Department responseDepartment = departmentService.update(id, Department);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(responseDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Department> delete(@PathVariable Integer id)
        throws NotFoundException {
        Department responseDepartment = departmentService.delete(id);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(responseDepartment);
    }

}
