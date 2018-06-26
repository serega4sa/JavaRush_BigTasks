package com.chmykhun.cashmachine.command;

import com.chmykhun.cashmachine.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static Map<Operation, Command> commands = new HashMap<>();

    static {
        for (Operation operation : Operation.values()) {
            switch (operation) {
                case LOGIN:
                    commands.put(operation, new LoginCommand());
                    continue;
                case INFO:
                    commands.put(operation, new InfoCommand());
                    continue;
                case DEPOSIT:
                    commands.put(operation, new DepositCommand());
                    continue;
                case WITHDRAW:
                    commands.put(operation, new WithdrawCommand());
                    continue;
                case EXIT:
                    commands.put(operation, new ExitCommand());
            }
        }
    }

    public static void execute(Operation operation) throws InterruptOperationException {
        commands.get(operation).execute();
    }
}
