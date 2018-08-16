package com.serega4sa.cashmachine;

public class CreditCard {
    private String cardId;
    private String password;

    public CreditCard(String cardId, String password) {
        this.cardId = cardId;
        this.password = password;
    }

    public String getCardId() {
        return cardId;
    }

    public String getPassword() {
        return password;
    }
}
