package com.chmykhun.cashmachine.command;

import com.chmykhun.cashmachine.exception.InterruptOperationException;

public interface Command {
    void execute() throws InterruptOperationException;
}
