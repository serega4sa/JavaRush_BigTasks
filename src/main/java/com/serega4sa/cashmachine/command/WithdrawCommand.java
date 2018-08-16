package com.serega4sa.cashmachine.command;

import com.serega4sa.cashmachine.ConsoleHelper;
import com.serega4sa.cashmachine.currency.CurrencyManipulator;
import com.serega4sa.cashmachine.currency.CurrencyManipulatorFactory;
import com.serega4sa.cashmachine.exception.InterruptOperationException;
import com.serega4sa.cashmachine.exception.NotEnoughMoneyException;

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
