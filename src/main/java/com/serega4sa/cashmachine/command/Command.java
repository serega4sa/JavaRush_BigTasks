package com.serega4sa.cashmachine.command;

import com.serega4sa.cashmachine.exception.InterruptOperationException;

public interface Command {
    void execute() throws InterruptOperationException;
}
