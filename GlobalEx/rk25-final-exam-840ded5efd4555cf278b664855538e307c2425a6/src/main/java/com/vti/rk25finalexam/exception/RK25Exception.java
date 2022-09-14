package com.vti.rk25finalexam.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RK25Exception extends RuntimeException {
    private String code;
    private Object param;

    public RK25Exception code(String code) {
        this.code = code;
        return this;
    }

    public RK25Exception param(Object param) {
        this.param = param;
        return this;
    }
}
