package com.serega4sa.restaurant;

import com.serega4sa.restaurant.kitchen.Order;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {

    private final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue;

    Tablet(int number) {
        this.number = number;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public int getNumber() {
        return number;
    }

    public void createOrder() {
        try {
            createOrder(new Order(this));
        } catch (IOException e) {
            logger.log(Level.SEVERE, ConsoleHelper.Messages.consoleUnavailable);
        }
    }

    void createOrder(Order order) {
        if (!order.isEmpty()) {
            ConsoleHelper.writeMessage(order.toString());
            queue.add(order);
        }
    }
}
