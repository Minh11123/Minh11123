package com.vti.rk25finalexam.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;


@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentDTO
    extends RepresentationModel<DepartmentDTO> {

    private String id;

    private String name;


    public DepartmentDTO id(String id) {
        this.id = id;
        return this;
    }

    public DepartmentDTO name(String name) {
        this.name = name;
        return this;
    }
}
