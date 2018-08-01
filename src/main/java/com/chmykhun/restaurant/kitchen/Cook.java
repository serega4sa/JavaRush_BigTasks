package com.chmykhun.restaurant.kitchen;

import com.chmykhun.restaurant.ConsoleHelper;
import com.chmykhun.restaurant.Tablet;
import com.chmykhun.restaurant.ad.AdvertisementManager;
import com.chmykhun.restaurant.ad.NoVideoAvailableException;
import com.chmykhun.restaurant.statistic.StatisticEventManager;
import com.chmykhun.restaurant.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cook extends Observable implements Runnable {

    private LinkedBlockingQueue<Order> queue;

    private String cookName;
    private boolean busy;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Cook(String name) {
        cookName = name;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
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
        } catch (NoVideoAvailableException e1) {
            logger.log(Level.INFO, ConsoleHelper.Messages.noSuitableVideo + order.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StatisticEventManager.getInstance().register(new CookedOrderEventDataRow(String.valueOf(order.getTablet().getNumber()), cookName, order.getTotalCookingTime(), order.getOrderedDishes()));
        setChanged();
        notifyObservers(order);
        busy = false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!queue.isEmpty() && !busy) {
                    startCookingOrder(queue.poll());
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
