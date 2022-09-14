package com.vti.rk25finalexam.spec;

import com.vti.rk25finalexam.common.Constants.ACCOUNT;
import com.vti.rk25finalexam.common.Constants.OPERATOR;
import com.vti.rk25finalexam.entity.Account;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
@AllArgsConstructor
public class AccountSpec implements Specification<Account> {

    private Expression expression;

    @Override
    public Predicate toPredicate(
        Root<Account> root,
        CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder
    ) {

        switch (expression.getField()) {
            case ACCOUNT.USERNAME:
                switch (expression.getOperator()) {
                    case OPERATOR.CONTAINS:
                        return criteriaBuilder
                            .like(root.get(ACCOUNT.USERNAME), "%" + expression.getValue()+ "%");

                    case OPERATOR.EQUALS:
                        return criteriaBuilder
                            .equal(root.get(ACCOUNT.USERNAME), String.valueOf(expression.getValue()));
                }
                return null;
            default:
                return null;
        }
    }
}
