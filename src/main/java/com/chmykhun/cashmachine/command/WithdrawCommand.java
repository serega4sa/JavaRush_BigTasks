package com.chmykhun.cashmachine.command;

import com.chmykhun.cashmachine.ConsoleHelper;
import com.chmykhun.cashmachine.currency.CurrencyManipulator;
import com.chmykhun.cashmachine.currency.CurrencyManipulatorFactory;
import com.chmykhun.cashmachine.exception.InterruptOperationException;
import com.chmykhun.cashmachine.exception.NotEnoughMoneyException;

public class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        mainLoop: while (true) {
            long amount = ConsoleHelper.askAmount();
            while (!manipulator.isAmountAvailable(amount)) {
                if (!ConsoleHelper.askChangeAmount()) {
                    break mainLoop;
                }
                amount = ConsoleHelper.askAmount();
            }
            try {
                ConsoleHelper.showResultOfOperation(manipulator.withdrawAmount(amount));
                break;
            } catch (NotEnoughMoneyException e) {
                if (!ConsoleHelper.askChangeAmount()) {
                    break;
                }
            }
        }
    }
}
