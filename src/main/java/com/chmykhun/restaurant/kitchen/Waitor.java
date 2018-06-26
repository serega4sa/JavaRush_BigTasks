package com.chmykhun.restaurant.kitchen;

import com.chmykhun.restaurant.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

public class Waitor implements Observer {
    @Override
    public void update(Observable cook, Object order) {
        ConsoleHelper.writeMessage(order.toString() + ConsoleHelper.Messages.cooked + ((Cook) cook).getCookName());
    }
}
