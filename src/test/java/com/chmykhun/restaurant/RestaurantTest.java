package com.chmykhun.restaurant;

import com.chmykhun.restaurant.kitchen.Cook;
import com.chmykhun.restaurant.kitchen.Waitor;
import com.chmykhun.restaurant.statistic.StatisticEventManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestaurantTest extends Restaurant {

    public static void main(String[] args) {
        Waitor waitor = new Waitor();
        Cook cook = new Cook("Amigo");
        cook.addObserver(waitor);
        StatisticEventManager.getInstance().register(cook);

        Runnable testFlow = new RandomOrderGeneratorTask(createTestTablets(2), Collections.singletonList(cook));
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
            tablets.add(new TestTablet(i));
        }
        return tablets;
    }
}
