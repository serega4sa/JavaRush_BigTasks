package com.chmykhun.restaurant;

import com.chmykhun.restaurant.kitchen.Cook;
import com.chmykhun.restaurant.kitchen.Waitor;
import com.chmykhun.restaurant.statistic.StatisticEventManager;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    public static void main(String[] args) {
        Waitor waitor = new Waitor();
        Cook cook = new Cook("Amigo");
        cook.addObserver(waitor);
        StatisticEventManager.getInstance().register(cook);

        createOrders(createTablets(2), cook);
        createDirectorReport();
    }

    protected static List<Tablet> createTablets(int number) {
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            tablets.add(new Tablet(i));
        }
        return tablets;
    }

    protected static void createOrders(List<Tablet> tablets, Cook cook) {
        for (Tablet tablet : tablets) {
            tablet.addObserver(cook);
            tablet.createOrder();
        }
    }

    protected static void createDirectorReport() {
        DirectorTablet dirTabl = new DirectorTablet();
        dirTabl.printReport();
    }
}
