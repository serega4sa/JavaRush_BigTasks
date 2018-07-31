package com.chmykhun.restaurant.kitchen;

import com.chmykhun.restaurant.statistic.StatisticEventManager;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {

    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    public OrderManager() {
        Thread daemon = new Thread(new Supervisor());
        daemon.setDaemon(true);
        daemon.start();
    }

    @Override
    public void update(Observable tablet, Object order) {
        queue.add(((Order) order));
    }

    /**
     * Looks into the orders queue and finds an available cook for processing it
     */
    class Supervisor implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    if (!queue.isEmpty() && isCookAvailable()) {
                        for (Cook cook : StatisticEventManager.getInstance().getCookSet()) {
                            if (!cook.isBusy()) {
                                cook.startCookingOrder(queue.poll());
                            }
                        }
                    } else {
                        Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private boolean isCookAvailable() {
            for (Cook cook : StatisticEventManager.getInstance().getCookSet()) {
                if (!cook.isBusy()) {
                    return true;
                }
            }
            return false;
        }
    }
}
