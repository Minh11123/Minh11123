package com.vti.rk25finalexam.utils;

public class Utils {

    public static Boolean checkStringAsDigit(String s) {
        if (s == null) return false;
        return s.matches("\\d+");
    }
}
