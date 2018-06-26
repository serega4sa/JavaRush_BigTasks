package com.chmykhun.cashmachine.command;

import com.chmykhun.cashmachine.ConsoleHelper;
import com.chmykhun.cashmachine.CreditCard;
import com.chmykhun.cashmachine.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand implements Command {

    private static final ResourceBundle cardsData = ResourceBundle.getBundle("cashMachine/verifiedCards", Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException {
        while (true) {
            CreditCard creditCard = ConsoleHelper.askAndValidateLogin();
            if (cardsData.containsKey(creditCard.getCardId()) && cardsData.getString(creditCard.getCardId()).equals(creditCard.getPassword())) {
                ConsoleHelper.writeMessage(ConsoleHelper.Messages.loginSuccessful);
                break;
            } else {
                ConsoleHelper.writeMessage(ConsoleHelper.Messages.reEnterCredentials);
            }
        }
    }
}
