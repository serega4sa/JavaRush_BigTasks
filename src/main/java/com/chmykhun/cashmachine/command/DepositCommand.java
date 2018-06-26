package com.chmykhun.cashmachine.command;

import com.chmykhun.cashmachine.ConsoleHelper;
import com.chmykhun.cashmachine.currency.CurrencyManipulator;
import com.chmykhun.cashmachine.currency.CurrencyManipulatorFactory;
import com.chmykhun.cashmachine.exception.InterruptOperationException;

public class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        String [] denom = ConsoleHelper.getValidTwoDigits();
        manipulator.addAmount(Integer.parseInt(denom[0]), Integer.parseInt(denom[1]));
    }
}
