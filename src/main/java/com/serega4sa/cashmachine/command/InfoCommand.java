package com.serega4sa.cashmachine.command;

import com.serega4sa.cashmachine.ConsoleHelper;
import com.serega4sa.cashmachine.currency.CurrencyManipulator;
import com.serega4sa.cashmachine.currency.CurrencyManipulatorFactory;

public class InfoCommand implements Command {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(ConsoleHelper.Messages.infoHead);
        if (CurrencyManipulatorFactory.isMoneyAvailable()) {
            for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
                ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
            }
        } else {
            ConsoleHelper.writeMessage(ConsoleHelper.Messages.noMoney);
        }
    }
}
