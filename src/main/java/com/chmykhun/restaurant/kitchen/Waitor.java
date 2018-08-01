package com.chmykhun.restaurant.kitchen;

import com.chmykhun.restaurant.ConsoleHelper;

import java.util.concurrent.LinkedBlockingQueue;

public class Waitor implements Runnable {

    private LinkedBlockingQueue<Order> orders_queue_out;
    private String waitorName;

    public Waitor(String waitorName) {
        this.waitorName = waitorName;
    }

    public void setOredersQueueOut(LinkedBlockingQueue<Order> oreders_queue_out) {
        this.orders_queue_out = oreders_queue_out;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!orders_queue_out.isEmpty()) {
                    Order order = orders_queue_out.poll();
                    ConsoleHelper.writeMessage(order.toString() + String.format(ConsoleHelper.Messages.cookedAndServed, order.getCookName(), waitorName));
                } else {
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
