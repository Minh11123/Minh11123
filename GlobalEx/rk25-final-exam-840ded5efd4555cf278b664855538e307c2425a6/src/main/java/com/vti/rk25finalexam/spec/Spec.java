package com.vti.rk25finalexam.spec;

import com.vti.rk25finalexam.common.Constants.OPERATOR;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
@AllArgsConstructor
public class Spec<T> implements Specification<T> {

    private Expression expression;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder) {
        Predicate predicate = null;

        String field = expression.getField();

        String operator = expression.getOperator();
        
        Object value = expression.getValue();

        switch (operator) {
            case OPERATOR.EQUALS:
                if (value instanceof Integer) {
                    predicate = criteriaBuilder.equal(root.get(field), Integer.valueOf(String.valueOf(value)));
                }
                    if (value instanceof String) {
                    predicate = criteriaBuilder.equal(root.get(field), String.valueOf(value));
                }
                if (value instanceof Date) {
                    predicate = criteriaBuilder.equal(root.get(field), (Date) value);
                }
                if (value instanceof LocalDate) {
                    predicate = criteriaBuilder.equal(root.get(field), (LocalDate) value);
                }
                break;
            case OPERATOR.NOT_EQUALS:
                if (value instanceof Integer) {
                    predicate = criteriaBuilder.notEqual(root.get(field), Integer.valueOf(String.valueOf(value)));
                }
                if (value instanceof String) {
                    predicate = criteriaBuilder.notEqual(root.get(field), String.valueOf(value));
                }
                if (value instanceof Date) {
                    predicate = criteriaBuilder.notEqual(root.get(field), (Date) value);
                }
                if (value instanceof LocalDate) {
                    predicate = criteriaBuilder.notEqual(root.get(field), (LocalDate) value);
                }
                break;
            case OPERATOR.CONTAINS:
                predicate = criteriaBuilder.like(root.get(field), "%" + value + "%");
                break;
            case OPERATOR.NOT_CONTAINS:
                predicate = criteriaBuilder.notLike(root.get(field), "%" + value + "%");
                break;
            case OPERATOR.GREATER_THAN:
                if (value instanceof Integer) {
                    predicate = criteriaBuilder.greaterThan(root.get(field), Integer.valueOf(String.valueOf(value)));
                }
                if (value instanceof Date) {
                    predicate = criteriaBuilder.greaterThan(root.get(field), (Date) value);
                }
                if (value instanceof LocalDate) {
                    predicate = criteriaBuilder.greaterThan(root.get(field), (LocalDate) value);
                }
                break;
            case OPERATOR.GREATER_THAN_OR_EQUALS:
                if (value instanceof Integer) {
                    predicate = criteriaBuilder.greaterThanOrEqualTo(root.get(field), Integer.valueOf(String.valueOf(value)));
                }
                if (value instanceof Date) {
                    predicate = criteriaBuilder.greaterThanOrEqualTo(root.get(field), (Date) value);
                }
                if (value instanceof LocalDate) {
                    predicate = criteriaBuilder.greaterThanOrEqualTo(root.get(field), (LocalDate) value);
                }
                break;
            case OPERATOR.LESS_THAN:
                if (value instanceof Integer) {
                    predicate = criteriaBuilder.lessThan(root.get(field), Integer.valueOf(String.valueOf(value)));
                }
                if (value instanceof Date) {
                    predicate = criteriaBuilder.lessThan(root.get(field), (Date) value);
                }
                if (value instanceof LocalDate) {
                    predicate = criteriaBuilder.lessThan(root.get(field), (LocalDate) value);
                }
                break;
            case OPERATOR.LESS_THAN_OR_EQUALS:
                if (value instanceof Integer) {
                    predicate = criteriaBuilder.lessThanOrEqualTo(root.get(field), Integer.valueOf(String.valueOf(value)));
                }
                if (value instanceof Date) {
                    predicate = criteriaBuilder.lessThanOrEqualTo(root.get(field), (Date) value);
                }
                if (value instanceof LocalDate) {
                    predicate = criteriaBuilder.lessThanOrEqualTo(root.get(field), (LocalDate) value);
                }
                break;
        }
        return predicate;
    }
}
