package com.chmykhun.restaurant;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestTablet extends Tablet {

    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public TestTablet(int number) {
        super(number);
    }

    public void createTestOrder() {
        try {
            TestOrder order = new TestOrder(this);
            if (!order.isEmpty()) {
                ConsoleHelper.writeMessage(order.toString());
                setChanged();
                notifyObservers(order);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, ConsoleHelper.Messages.consoleUnavailable);
        }
    }
}
