package com.chmykhun.restaurant;

import com.chmykhun.restaurant.kitchen.Cook;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {

    private static final int ORDER_CREATING_INTERVAL = 15000;
    private List<TestTablet> tablets;
    private List<Cook> cooks;

    public RandomOrderGeneratorTask(List<TestTablet> tablets, List<Cook> cooks) {
        this.tablets = tablets;
        this.cooks = cooks;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int tabletId = (int) (Math.random() * tablets.size());
                int cookId = (int) (Math.random() * cooks.size());
                tablets.get(tabletId).addObserver(cooks.get(cookId));
                tablets.get(tabletId).createTestOrder();
                Thread.sleep(ORDER_CREATING_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
