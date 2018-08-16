package com.serega4sa.restaurant.kitchen;

import com.serega4sa.restaurant.ConsoleHelper;
import com.serega4sa.restaurant.Tablet;
import com.serega4sa.restaurant.ad.AdvertisementManager;
import com.serega4sa.restaurant.ad.NoVideoAvailableException;
import com.serega4sa.restaurant.statistic.StatisticEventManager;
import com.serega4sa.restaurant.statistic.event.CookedOrderEventDataRow;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cook implements Runnable {

    private LinkedBlockingQueue<Order> orders_queue_in;
    private LinkedBlockingQueue<Order> orders_queue_out;

    private String cookName;
    private boolean busy;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Cook(String name) {
        cookName = name;
    }

    public void setOredersQueueIn(LinkedBlockingQueue<Order> oreders_in_queue) {
        this.orders_queue_in = oreders_in_queue;
    }

    public void setOredersQueueOut(LinkedBlockingQueue<Order> oreders_queue_out) {
        this.orders_queue_out = oreders_queue_out;
    }

    public String getCookName() {
        return cookName;
    }

    public void startCookingOrder(Order order) {
        busy = true;
        ConsoleHelper.writeMessage(ConsoleHelper.Messages.startCooking + order.toString() + String.format(ConsoleHelper.Messages.cookingTime, order.getTotalCookingTime() / 60));
        try {
            new AdvertisementManager(order.getTotalCookingTime()).processVideos();
            Thread.sleep(order.getTotalCookingTime() / 6);
        } catch (NoVideoAvailableException | UnsupportedOperationException e1) {
            logger.log(Level.INFO, ConsoleHelper.Messages.noSuitableVideo + order.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StatisticEventManager.getInstance().register(new CookedOrderEventDataRow(String.valueOf(order.getTablet().getNumber()), cookName, order.getTotalCookingTime(), order.getOrderedDishes()));
        orders_queue_out.add(order);
        busy = false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!orders_queue_in.isEmpty() && !busy) {
                    Order order = orders_queue_in.poll();
                    order.setCookName(cookName);
                    startCookingOrder(order);
                } else {
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return cookName;
    }
}
