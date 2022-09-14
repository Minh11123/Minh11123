package com.vti.rk25finalexam.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AccountCreateDTO {

    @NotBlank(message = "Username is not blank")
    @Length(max = 50, message = "Length of username is not over 50 chars")
    private String username;

    private String firstName;

    private String lastName;

    private String role;

    private Integer departmentId;

}
