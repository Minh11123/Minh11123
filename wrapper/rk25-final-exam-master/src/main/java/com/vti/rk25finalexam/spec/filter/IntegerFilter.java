package com.vti.rk25finalexam.spec.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class IntegerFilter extends Filter<Integer> {
    private Integer greaterThan;
    private Integer greaterThanOrEquals;
    private Integer lessThan;
    private Integer lessThanOrEquals;

    public IntegerFilter greaterThan(Integer greaterThan) {
        this.greaterThan = greaterThan;
        return this;
    }

}
