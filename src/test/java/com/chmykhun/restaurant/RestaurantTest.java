package com.chmykhun.restaurant;

import com.chmykhun.restaurant.kitchen.Cook;
import com.chmykhun.restaurant.kitchen.Waitor;

import java.util.ArrayList;
import java.util.List;

public class RestaurantTest extends Restaurant {

    public static void main(String[] args) {
        Waitor waitor = new Waitor();

        List<Cook> cooks = createCooks(waitor, "Amigo", "Jack");
        for (Cook cook : cooks) {
            Thread cookThread = new Thread(cook);
            cookThread.start();
        }

        Runnable testFlow = new RandomOrderGeneratorTask(createTestTablets(2));
        testFlow.run();

        try {
            Thread.sleep(20000);
            createDirectorReport();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static List<TestTablet> createTestTablets(int number) {
        List<TestTablet> tablets = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            TestTablet tablet = new TestTablet(i);
            tablets.add(tablet);
            tablet.setQueue(QUEUE);
            tablets.add(tablet);
        }
        return tablets;
    }
}
