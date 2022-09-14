package com.vti.rk25finalexam.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Rk25Error {
    private String code;
    private String message;
    private Object param;

    public Rk25Error code(String code) {
        this.code = code;
        return this;
    }

    public Rk25Error message(String message) {
        this.message = message;
        return this;
    }

    public Rk25Error param(Object param) {
        this.param = param;
        return this;
    }
}
