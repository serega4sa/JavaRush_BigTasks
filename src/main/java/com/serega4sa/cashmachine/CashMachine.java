package com.serega4sa.cashmachine;

import com.serega4sa.cashmachine.command.CommandExecutor;
import com.serega4sa.cashmachine.command.Operation;
import com.serega4sa.cashmachine.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            CommandExecutor.execute(Operation.LOGIN);
            while (true) {
                CommandExecutor.execute(ConsoleHelper.askOperation());
            }
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
    }
}
