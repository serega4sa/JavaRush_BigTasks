package com.chmykhun.restaurant;

import com.chmykhun.restaurant.kitchen.Cook;
import com.chmykhun.restaurant.kitchen.Order;
import com.chmykhun.restaurant.kitchen.Waitor;
import com.chmykhun.restaurant.statistic.StatisticEventManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {

    static final LinkedBlockingQueue<Order> QUEUE = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Waitor waitor = new Waitor();

        List<Cook> cooks = createCooks(waitor, "Amigo", "Jack");
        for (Cook cook : cooks) {
            Thread cookThread = new Thread(cook);
            cookThread.start();
        }
        List<Tablet> tablets = createTablets(5);
        createOrders(tablets);
        createDirectorReport();
    }

    protected static List<Cook> createCooks(Waitor waitor, String... cooknames) {
        List<Cook> cooks = new ArrayList<>();
        for (String cookname : cooknames) {
            Cook cook = new Cook(cookname);
            cook.addObserver(waitor);
            cook.setQueue(QUEUE);
            cooks.add(cook);
            StatisticEventManager.getInstance().register(cook);
        }
        return cooks;
    }

    protected static List<Tablet> createTablets(int number) {
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(QUEUE);
            tablets.add(tablet);
        }
        return tablets;
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
