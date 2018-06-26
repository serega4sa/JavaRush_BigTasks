package com.chmykhun.cashmachine.command;

import com.chmykhun.cashmachine.ConsoleHelper;
import com.chmykhun.cashmachine.exception.InterruptOperationException;

public class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        if (ConsoleHelper.askExitConfirmation()) {
            throw new InterruptOperationException();
        }
    }
}
