package com.chmykhun.cashmachine;

import com.chmykhun.cashmachine.command.CommandExecutor;
import com.chmykhun.cashmachine.command.Operation;
import com.chmykhun.cashmachine.exception.InterruptOperationException;

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
