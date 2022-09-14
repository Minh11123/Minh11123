package com.vti.rk25finalexam.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
//import org.springframework.hateoas.RepresentationModel;

//@EqualsAndHashCode(callSuper = true)
@Data
public class AccountDTO {

    private Integer id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String role;

    @JsonProperty("deptId")
    private Integer departmentId;

    @JsonProperty("deptName")
    private String departmentName;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public AccountDTO id(Integer id) {
        this.id = id;
        return this;
    }

    public AccountDTO username(String username) {
        this.username = username;
        return this;
    }

    public AccountDTO password(String password) {
        this.password = password;
        return this;
    }

    public AccountDTO firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AccountDTO lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AccountDTO role(String role) {
        this.role = role;
        return this;
    }

    public AccountDTO departmentId(Integer departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public AccountDTO departmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }
}
