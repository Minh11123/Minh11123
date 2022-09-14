package com.vti.rk25finalexam.spec.filter;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StringFilter extends Filter<String> {

    private String contains;

    private String notContains;
}
