package com.serega4sa.cashmachine;

import com.serega4sa.cashmachine.command.Operation;
import com.serega4sa.cashmachine.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ConsoleHelper {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final ResourceBundle texts = ResourceBundle.getBundle("cashMachine/consoleTexts_en", Locale.ENGLISH);

    public interface Messages {
        // Common texts
        String enterOperation = texts.getString("choose.operation");
        String reEnterOperation = texts.getString("wrong.operation");
        String reConfirm = texts.getString("wrong.confirmation");
        String goodbye = texts.getString("bye");

        // Login texts
        String enterCardId = texts.getString("enter.cardId");
        String enterPassword = texts.getString("enter.password");
        String loginSuccessful = texts.getString("login.success");
        String reEnterCredentials = texts.getString("login.fail");

        // Info texts
        String infoHead = texts.getString("info.head");
        String noMoney = texts.getString("no.money");

        // Deposit texts
        String enterCurrency = texts.getString("enter.currency");
        String reEnterCurrency = texts.getString("wrong.currency");
        String enterDenomination = texts.getString("enter.denomination");
        String reEnterDenomination = texts.getString("wrong.denomination");

        // Withdraw texts
        String enterAmount = texts.getString("enter.amount");
        String reEnterAmount = texts.getString("wrong.amount");
        String notPossible = texts.getString("not.possible");
        String changeAmount = texts.getString("change.amount");
        String operationComplete = texts.getString("operation.success");

        // Exit texts
        String exitConfirmation = texts.getString("exit.confirmation");
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String input = "";
        try {
            input = reader.readLine();
            if (input.toLowerCase().matches("exit")) {
                throw new InterruptOperationException();
            }
        } catch (IOException ignored) {
        }
        return input;
    }

    public static CreditCard askAndValidateLogin() throws InterruptOperationException {
        writeMessage(Messages.enterCardId);
        String cardId = readString();
        writeMessage(Messages.enterPassword);
        String pass = readString();
        if (cardId.matches("\\d{12}") && pass.matches("\\d{4}")) {
            return new CreditCard(cardId, pass);
        } else {
            writeMessage(Messages.reEnterCredentials);
            return askAndValidateLogin();
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(Messages.enterOperation);
        try {
            return Operation.getAllowableOperationByOrder(Integer.parseInt(readString()));
        } catch (IllegalArgumentException e) {
            writeMessage(Messages.reEnterOperation);
            return askOperation();
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(Messages.enterCurrency);

        String currencyCode = readString();
        if (isValidCurrencyCode(currencyCode)) {
            return currencyCode.toUpperCase();
        } else {
            writeMessage(Messages.reEnterCurrency);
            return askCurrencyCode();
        }
    }

    public static long askAmount() throws InterruptOperationException {
        writeMessage(Messages.enterAmount);

        String amount = readString();
        if (amount.matches("\\d+")) {
            return Long.parseLong(amount);
        } else {
            writeMessage(Messages.reEnterAmount);
            return askAmount();
        }
    }

    public static boolean askChangeAmount() throws InterruptOperationException {
        writeMessage(Messages.notPossible);
        writeMessage(Messages.changeAmount);
        String input = readString().toLowerCase();
        if (input.matches("y|yes")) {
            return true;
        } else if (input.matches("n|no")) {
            return false;
        } else {
            writeMessage(Messages.reConfirm);
            return askChangeAmount();
        }
    }

    public static void showResultOfOperation(Map<Integer, Integer> result) {
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
        }
        writeMessage(Messages.operationComplete);
    }

    public static boolean askExitConfirmation() throws InterruptOperationException {
        writeMessage(Messages.exitConfirmation);
        String input = readString().toLowerCase();
        if (input.matches("y|yes")) {
            writeMessage(Messages.goodbye);
            return true;
        } else if (input.matches("n|no")) {
            return false;
        } else {
            writeMessage(Messages.reConfirm);
            return askExitConfirmation();
        }
    }

    public static void printExitMessage() {
        writeMessage(Messages.goodbye);
    }

    private static boolean isValidCurrencyCode(String input) {
        return input.matches("[a-zA-Z]{3}");
    }

    public static String[] getValidTwoDigits() throws InterruptOperationException {
        writeMessage(Messages.enterDenomination);

        String input = readString();
        if (isValidDenomination(input)) {
            return input.split(" ");
        } else {
            writeMessage(Messages.reEnterDenomination);
            return getValidTwoDigits();
        }
    }

    private static boolean isValidDenomination(String input) {
        return input.matches("\\d+ \\d+");
    }
}
