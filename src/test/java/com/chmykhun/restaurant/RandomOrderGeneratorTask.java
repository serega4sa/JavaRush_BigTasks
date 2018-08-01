package com.chmykhun.restaurant;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {

    private static final int ORDER_CREATING_INTERVAL = 15000;
    private List<TestTablet> tablets;

    public RandomOrderGeneratorTask(List<TestTablet> tablets) {
        this.tablets = tablets;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int tabletId = (int) (Math.random() * tablets.size());
                tablets.get(tabletId).createOrder();
                Thread.sleep(ORDER_CREATING_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
