package com.chmykhun.restaurant;

import com.chmykhun.restaurant.kitchen.Cook;
import com.chmykhun.restaurant.kitchen.Waitor;
import com.chmykhun.restaurant.statistic.StatisticEventManager;

public class Restaurant {

    public static void main(String[] args) {
        Waitor waitor = new Waitor();
        Cook cook = new Cook("Amigo");
        cook.addObserver(waitor);
        StatisticEventManager.getInstance().register(cook);

        Tablet tablet = new Tablet(1);
        tablet.addObserver(cook);
        tablet.createOrder();

        Tablet tablet1 = new Tablet(2);
        tablet1.addObserver(cook);
        tablet1.createOrder();

        DirectorTablet dirTabl = new DirectorTablet();
        dirTabl.printReport();
    }
}
