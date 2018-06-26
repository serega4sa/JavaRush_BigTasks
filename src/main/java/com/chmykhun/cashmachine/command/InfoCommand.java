package com.chmykhun.cashmachine.command;

import com.chmykhun.cashmachine.ConsoleHelper;
import com.chmykhun.cashmachine.currency.CurrencyManipulator;
import com.chmykhun.cashmachine.currency.CurrencyManipulatorFactory;

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
