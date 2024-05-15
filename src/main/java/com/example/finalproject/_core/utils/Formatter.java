package com.example.finalproject._core.utils;

import java.text.DecimalFormat;

public class Formatter {
    public static String number(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }
}
