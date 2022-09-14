package com.vti.rk25finalexam.entity.criteria;

import com.vti.rk25finalexam.spec.filter.IntegerFilter;
import com.vti.rk25finalexam.spec.filter.StringFilter;
import lombok.Data;

@Data
public class AccountCriteria {

    private IntegerFilter id;

    private StringFilter username;

    private StringFilter firstName;

    private StringFilter lastName;

    private StringFilter role;

    private StringFilter search;
}
