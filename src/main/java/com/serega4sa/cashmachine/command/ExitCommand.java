package com.serega4sa.cashmachine.command;

import com.serega4sa.cashmachine.ConsoleHelper;
import com.serega4sa.cashmachine.exception.InterruptOperationException;

public class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        if (ConsoleHelper.askExitConfirmation()) {
            throw new InterruptOperationException();
        }
    }
}
