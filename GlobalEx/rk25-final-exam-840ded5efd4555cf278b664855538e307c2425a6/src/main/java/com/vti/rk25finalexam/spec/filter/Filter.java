package com.vti.rk25finalexam.spec.filter;

import lombok.Data;

@Data
public class Filter<T> {
    private T equals;
    private T notEquals;
}
