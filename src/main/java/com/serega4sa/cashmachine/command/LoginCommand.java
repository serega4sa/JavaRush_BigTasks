package com.serega4sa.cashmachine.command;

import com.serega4sa.cashmachine.ConsoleHelper;
import com.serega4sa.cashmachine.CreditCard;
import com.serega4sa.cashmachine.exception.InterruptOperationException;

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
