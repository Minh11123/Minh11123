package com.vti.rk25finalexam.common;

public class Constants {

    public interface IS_DELETED {
        Integer TRUE = 1;
        Integer FALSE = 0;
    }

    public interface ROLE {
        String ADMIN = "ADMIN";
        String EMPLOYEE = "EMPLOYEE";
        String MANAGER = "MANAGER";
    }

    public interface ACCOUNT {
        String ID = "id";
        String USERNAME = "username";
        String FIRST_NAME = "firstName";
        String LAST_NAME = "lastName";
        String ROLE = "role";
    }


    public interface OPERATOR {
        String NOT_EQUALS = "notEquals";
        String EQUALS = "equals";
        String CONTAINS = "contains";
        String NOT_CONTAINS = "notContains";
        String GREATER_THAN = "greaterThan";
        String GREATER_THAN_OR_EQUALS = "greaterThanOrEquals";
        String LESS_THAN = "lessThan";
        String LESS_THAN_OR_EQUALS = "lessThanOrEquals";
    }


}
