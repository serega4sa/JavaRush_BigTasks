package com.chmykhun.restaurant;

import com.chmykhun.restaurant.kitchen.Cook;
import com.chmykhun.restaurant.kitchen.Waitor;

import java.util.ArrayList;
import java.util.List;

public class RestaurantTest extends Restaurant {

    public static void main(String[] args) {
        Waitor waitor = new Waitor();

        List<Cook> cooks = createCooks(waitor, "Amigo", "Jack");

        Runnable testFlow = new RandomOrderGeneratorTask(createTestTablets(2), cooks);
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
