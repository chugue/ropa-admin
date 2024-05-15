package com.example.finalproject._core.utils;

import org.junit.jupiter.api.Test;

public class FormatterTest {
    @Test
    public void format_test() {
        int num = 1000000000;

        String number = Formatter.number(num);

        System.out.println(number);

    }
}
