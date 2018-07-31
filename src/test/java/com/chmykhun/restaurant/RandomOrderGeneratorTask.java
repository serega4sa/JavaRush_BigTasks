package com.chmykhun.restaurant;

import com.chmykhun.restaurant.kitchen.OrderManager;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {

    private static final int ORDER_CREATING_INTERVAL = 15000;
    private List<TestTablet> tablets;

    public RandomOrderGeneratorTask(List<TestTablet> tablets) {
        this.tablets = tablets;
    }

    @Override
    public void run() {
        OrderManager orderManager = new OrderManager();
        while (true) {
            try {
                int tabletId = (int) (Math.random() * tablets.size());
                tablets.get(tabletId).addObserver(orderManager);
                tablets.get(tabletId).createTestOrder();
                Thread.sleep(ORDER_CREATING_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
