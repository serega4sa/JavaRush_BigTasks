package com.chmykhun.cashmachine.currency;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> manipulators = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (!manipulators.containsKey(currencyCode)) {
            manipulators.put(currencyCode, new CurrencyManipulator(currencyCode));
        }
        return manipulators.get(currencyCode);
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return manipulators.values();
    }

    public static boolean isMoneyAvailable() {
        for (CurrencyManipulator manipulator : manipulators.values()) {
            if (manipulator.getTotalAmount() > 0) {
                return true;
            }
        }
        return false;
    }
}
