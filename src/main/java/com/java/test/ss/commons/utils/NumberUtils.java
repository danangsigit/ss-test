package com.java.test.ss.commons.utils;

import java.math.BigDecimal;

public class NumberUtils {
    public static Long round100(Double input) {
        if (input != null && input > 0) {
            return Math.round(input + (100 - (input % 100 == 0 ? 100 : input % 100)));
        }

        if (input != null && input <= 0) {
            return Math.round(input);
        }
        return null;
    }

    public static Long round100(Float input) {
        if (input != null && input > 0) {
            return new Long(Math.round(input + (100 - (input % 100 == 0 ? 100 : input % 100))));
        }

        if (input != null && input <= 0) {
            return new Long(Math.round(input));
        }
        return null;
    }

    public static Long round100(Long input) {
        if (input != null && input > 0) {
            return input + (100 - (input % 100 == 0 ? 100 : input % 100));
        }

        if (input != null && input <= 0) {
            return input;
        }
        return null;
    }

    public static float roundFloat(float number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static double roundDouble(double number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
}
