package com.chmykhun;

import com.chmykhun.cashmachine.ConsoleHelper;
import com.chmykhun.cashmachine.currency.CurrencyManipulator;
import com.chmykhun.cashmachine.currency.CurrencyManipulatorFactory;
import com.chmykhun.cashmachine.command.CommandExecutor;
import com.chmykhun.cashmachine.exception.InterruptOperationException;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CashMachineTest {
    private enum Denoms {
        USD, EUR, UAH
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        addDenoms();
        while (true) {
            try {
                CommandExecutor.execute(ConsoleHelper.askOperation());
            } catch (InterruptOperationException e) {
                ConsoleHelper.writeMessage(ConsoleHelper.Messages.goodbye);
                break;
            }
        }
    }

    private static void addDenoms() {
        for (Denoms denom : Denoms.values()) {
            CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(denom.toString());
            for (int i = 0; i < 5; i++) {
                List<Integer> randomNumbers = getDenomAndAmount();
                manipulator.addAmount(randomNumbers.get(0), randomNumbers.get(1));
            }
        }
        CurrencyManipulatorFactory.getAllCurrencyManipulators();
        System.out.println();
    }

    private static List<Integer> getDenomAndAmount() {
        int denom = new Random().ints(1,1, 10).findFirst().getAsInt() * 10;
        int amount = new Random().ints(1,1, 10).findFirst().getAsInt();
        return Arrays.asList(denom, amount);
    }
}
