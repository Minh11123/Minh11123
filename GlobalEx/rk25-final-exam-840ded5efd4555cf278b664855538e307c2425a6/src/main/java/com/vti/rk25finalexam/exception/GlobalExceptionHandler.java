package com.vti.rk25finalexam.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status,
            WebRequest request
    ) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(objectError -> {
                    String field = ((FieldError) objectError).getField();
                    String message = objectError.getDefaultMessage();
                    errorMap.put(field, message);
                });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorMap);
    }


    @ExceptionHandler({RK25Exception.class})
    public ResponseEntity<Error> handleRK25Exception(RK25Exception ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Error());
    }

}
