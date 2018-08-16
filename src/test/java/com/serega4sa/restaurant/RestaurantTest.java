package com.serega4sa.restaurant;

import com.serega4sa.restaurant.kitchen.Cook;
import com.serega4sa.restaurant.kitchen.Waitor;

import java.util.ArrayList;
import java.util.List;

public class RestaurantTest extends Restaurant {

    private static final int ORDER_CREATING_TIME = 60000;
    private static final int LAST_ORDER_PROCESSING_DELAY = 15000;
    private static final int ORDER_CREATING_INTERVAL = 10000;
    private static boolean stopWorking;

    public static void main(String[] args) {
        List<Waitor> waitors = creareWaitors("Anastasia", "Mary", "Kris");
        for (Waitor waitor : waitors) {
            Thread waitorThread = new Thread(waitor);
            waitorThread.start();
        }

        List<Cook> cooks = createCooks("Amigo", "Jack");
        for (Cook cook : cooks) {
            Thread cookThread = new Thread(cook);
            cookThread.start();
        }

        Thread testFlow = new Thread(new RandomOrderGeneratorTask(createTestTablets(2), ORDER_CREATING_INTERVAL));
        testFlow.start();

        try {
            Thread.sleep(ORDER_CREATING_TIME);
            stopWorking = true;
            Thread.sleep(LAST_ORDER_PROCESSING_DELAY);
            createDirectorReport();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isStopWorking() {
        return stopWorking;
    }

    private static List<TestTablet> createTestTablets(int number) {
        List<TestTablet> tablets = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            TestTablet tablet = new TestTablet(i);
            tablets.add(tablet);
            tablet.setQueue(ORDERS_QUEUE_IN);
            tablets.add(tablet);
        }
        return tablets;
    }
}
