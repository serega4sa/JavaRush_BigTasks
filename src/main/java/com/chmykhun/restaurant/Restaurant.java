package com.chmykhun.restaurant;

import com.chmykhun.restaurant.kitchen.Cook;
import com.chmykhun.restaurant.kitchen.Order;
import com.chmykhun.restaurant.kitchen.Waitor;
import com.chmykhun.restaurant.statistic.StatisticEventManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {

    protected static final LinkedBlockingQueue<Order> ORDERS_QUEUE_IN = new LinkedBlockingQueue<>();
    protected static final LinkedBlockingQueue<Order> ORDERS_QUEUE_OUT = new LinkedBlockingQueue<>();

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

        List<Tablet> tablets = createTablets(5);
        createOrders(tablets);
        createDirectorReport();
    }

    protected static List<Waitor> creareWaitors(String... waitorsNames) {
        List<Waitor> waitors = new ArrayList<>();
        for (String waitorName : waitorsNames) {
            Waitor waitor = new Waitor(waitorName);
            waitor.setOredersQueueOut(ORDERS_QUEUE_OUT);
            waitors.add(waitor);
            StatisticEventManager.getInstance().register(waitor);
        }
        return waitors;
    }

    protected static List<Cook> createCooks(String... cooksNames) {
        List<Cook> cooks = new ArrayList<>();
        for (String cookName : cooksNames) {
            Cook cook = new Cook(cookName);
            cook.setOredersQueueIn(ORDERS_QUEUE_IN);
            cook.setOredersQueueOut(ORDERS_QUEUE_OUT);
            cooks.add(cook);
            StatisticEventManager.getInstance().register(cook);
        }
        return cooks;
    }

    protected static List<Tablet> createTablets(int number) {
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(ORDERS_QUEUE_IN);
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
