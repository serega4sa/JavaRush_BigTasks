package com.serega4sa.cashmachine.command;

import com.serega4sa.cashmachine.ConsoleHelper;
import com.serega4sa.cashmachine.currency.CurrencyManipulator;
import com.serega4sa.cashmachine.currency.CurrencyManipulatorFactory;
import com.serega4sa.cashmachine.exception.InterruptOperationException;

public class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        String [] denom = ConsoleHelper.getValidTwoDigits();
        manipulator.addAmount(Integer.parseInt(denom[0]), Integer.parseInt(denom[1]));
    }
}
