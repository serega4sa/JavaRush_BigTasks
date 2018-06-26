package com.chmykhun.restaurant.kitchen;

import com.chmykhun.restaurant.ConsoleHelper;
import com.chmykhun.restaurant.Tablet;
import com.chmykhun.restaurant.ad.AdvertisementManager;
import com.chmykhun.restaurant.ad.NoVideoAvailableException;
import com.chmykhun.restaurant.statistic.StatisticManager;
import com.chmykhun.restaurant.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cook extends Observable implements Observer{

    private String cookName;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Cook(String name) {
        cookName = name;
    }

    public String getCookName() {
        return cookName;
    }

    @Override
    public String toString() {
        return cookName;
    }

    @Override
    public void update(Observable tablet, Object order) {
        ConsoleHelper.writeMessage(ConsoleHelper.Messages.startCooking + order.toString() + String.format(ConsoleHelper.Messages.cookingTime, ((Order) order).getTotalCookingTime()));
        try {
            new AdvertisementManager(((Order) order).getTotalCookingTime()).processVideos();
        } catch (NoVideoAvailableException e1) {
            logger.log(Level.INFO, ConsoleHelper.Messages.noSuitableVideo + order.toString());
        }
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(String.valueOf(((Order) order).getTablet().getNumber()), cookName, ((Order) order).getTotalCookingTime(), ((Order) order).getOrderedDishes()));
        setChanged();
        notifyObservers(order);
    }
}
