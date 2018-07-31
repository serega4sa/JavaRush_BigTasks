package com.chmykhun.restaurant;

import com.chmykhun.restaurant.kitchen.Cook;
import com.chmykhun.restaurant.kitchen.OrderManager;
import com.chmykhun.restaurant.kitchen.Waitor;
import com.chmykhun.restaurant.statistic.StatisticEventManager;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    public static void main(String[] args) {
        Waitor waitor = new Waitor();

        List<Cook> cooks = createCooks(waitor, "Amigo", "Jack");
        List<Tablet> tablets = createTablets(5);
        OrderManager orderManager = new OrderManager();
        createObservers(tablets, orderManager);
        createOrders(tablets);
        createDirectorReport();
    }

    protected static List<Cook> createCooks(Waitor waitor, String... cooknames) {
        List<Cook> cooks = new ArrayList<>();
        for (String cookname : cooknames) {
            Cook cook = new Cook(cookname);
            cook.addObserver(waitor);
            cooks.add(cook);
            StatisticEventManager.getInstance().register(cook);
        }
        return cooks;
    }

    protected static List<Tablet> createTablets(int number) {
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            tablets.add(new Tablet(i));
        }
        return tablets;
    }

    private static void createObservers(List<Tablet> tablets, OrderManager orderManager) {
        for (Tablet tablet : tablets) {
            tablet.addObserver(orderManager);
        }
    }

    protected static void createOrders(List<Tablet> tablets) {
        for (Tablet tablet : tablets) {
            tablet.createOrder();
        }
    }

    protected static void createDirectorReport() {
        DirectorTablet dirTabl = new DirectorTablet();
        dirTabl.printReport();
    }
}
