package com.vti.rk25finalexam.repository;

import com.vti.rk25finalexam.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository
    extends JpaRepository<Department, Integer> {

}
