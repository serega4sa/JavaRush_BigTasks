package com.chmykhun.restaurant.kitchen;

import com.chmykhun.restaurant.ConsoleHelper;
import com.chmykhun.restaurant.Tablet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Order {

    private static List<Dish> allDishes = Arrays.asList(Dish.values());
    private List<Dish> orderedDishes;
    private Tablet tablet;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        orderedDishes = ConsoleHelper.getAllDishesForOrder();
    }

    public List<Dish> getOrderedDishes() {
        return orderedDishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    public static String allDishesToString() {
        return dishesToString(allDishes);
    }

    public static String dishesToString(List<Dish> list) {
        StringBuilder strBld = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            strBld.append(list.get(i)).append(i == list.size() - 1 ? "" : ", ");
        }
        return strBld.toString();
    }

    public int getTotalCookingTime() {
        int totalTime = 0;
        for (Dish dish : orderedDishes) {
            totalTime += dish.getDuration();
        }
        return totalTime;
    }

    public boolean isEmpty() {
        return orderedDishes.isEmpty();
    }

    @Override
    public String toString() {
        return String.format(ConsoleHelper.Messages.orderInfo, dishesToString(orderedDishes), tablet.getNumber());
    }
}
