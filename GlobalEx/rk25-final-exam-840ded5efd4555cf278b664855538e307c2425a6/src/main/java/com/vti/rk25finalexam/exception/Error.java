package com.vti.rk25finalexam.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Error {
    private String errorCode;
    private String errorMessage;
    private String errorStatus;

    public Error errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Error errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public Error errorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
        return this;
    }
}
