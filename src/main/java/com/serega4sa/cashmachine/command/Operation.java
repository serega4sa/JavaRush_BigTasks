package com.serega4sa.cashmachine.command;

public enum Operation {
    LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT;

    public static Operation getAllowableOperationByOrder(Integer i) throws IllegalArgumentException {
        if (Operation.values().length >= i && i > 0) {
            return Operation.values()[i];
        } else {
            throw new IllegalArgumentException();
        }
    }
}
