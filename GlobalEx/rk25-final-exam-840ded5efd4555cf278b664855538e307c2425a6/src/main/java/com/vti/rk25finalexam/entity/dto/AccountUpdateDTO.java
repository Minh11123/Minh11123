package com.vti.rk25finalexam.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountUpdateDTO {
    private String username;

    private String firstName;

    private String lastName;

    private String role;

    private Integer departmentId;
}
