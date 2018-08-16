package com.serega4sa.restaurant;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestTablet extends Tablet {

    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public TestTablet(int number) {
        super(number);
    }

    @Override
    public void createOrder() {
        try {
            createOrder(new TestOrder(this));
        } catch (IOException e) {
            logger.log(Level.SEVERE, ConsoleHelper.Messages.consoleUnavailable);
        }
    }
}
