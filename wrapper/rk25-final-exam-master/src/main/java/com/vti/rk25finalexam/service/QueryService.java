package com.vti.rk25finalexam.service;

import com.vti.rk25finalexam.common.Constants;
import com.vti.rk25finalexam.common.Constants.OPERATOR;
import com.vti.rk25finalexam.spec.Expression;
import com.vti.rk25finalexam.spec.Spec;
import com.vti.rk25finalexam.spec.filter.IntegerFilter;
import com.vti.rk25finalexam.spec.filter.StringFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class QueryService<T> {

    public Specification<T> buildIntegerFilter(String field, IntegerFilter filter) {
        if (filter.getEquals() != null) {
            return new Spec<T>(new Expression(field, OPERATOR.EQUALS, filter.getEquals()));
        }
        if (filter.getNotEquals() != null) {
            return new Spec<T>(new Expression(field, OPERATOR.NOT_EQUALS, filter.getNotEquals()));
        }
        if (filter.getGreaterThan() != null) {
            return new Spec<T>(new Expression(field, OPERATOR.GREATER_THAN, filter.getGreaterThan()));
        }
        if (filter.getLessThan() != null) {
            return new Spec<T>(new Expression(field, OPERATOR.LESS_THAN, filter.getLessThan()));
        }
        if (filter.getGreaterThanOrEquals() != null) {
            return new Spec<T>(new Expression(field, OPERATOR.GREATER_THAN_OR_EQUALS, filter.getGreaterThanOrEquals()));
        }
        if (filter.getLessThanOrEquals() != null) {
            return new Spec<T>(new Expression(field, OPERATOR.LESS_THAN_OR_EQUALS, filter.getLessThanOrEquals()));
        }
        return null;
    }

    public Specification<T> buildStringFilter(String field, StringFilter filter) {
        if (filter.getEquals() != null) {
            return new Spec<T>(new Expression(field, OPERATOR.EQUALS, filter.getEquals()));
        }
        if (filter.getNotEquals() != null) {
            return new Spec<T>(new Expression(field, OPERATOR.NOT_EQUALS, filter.getNotEquals()));
        }
        if (filter.getContains() != null) {
            return new Spec<T>(new Expression(field, OPERATOR.CONTAINS, filter.getContains()));
        }
        if (filter.getNotContains() != null) {
            return new Spec<T>(new Expression(field, OPERATOR.NOT_CONTAINS, filter.getNotContains()));
        }

        return null;
    }
}
