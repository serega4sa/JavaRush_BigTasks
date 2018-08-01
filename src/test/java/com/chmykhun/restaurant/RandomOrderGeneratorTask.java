package com.chmykhun.restaurant;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {

    private int interval;
    private List<TestTablet> tablets;

    public RandomOrderGeneratorTask(List<TestTablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (!RestaurantTest.isStopWorking()) {
            try {
                int tabletId = (int) (Math.random() * tablets.size());
                tablets.get(tabletId).createOrder();
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
