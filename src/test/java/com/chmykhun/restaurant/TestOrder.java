package com.chmykhun.restaurant;

import com.chmykhun.restaurant.kitchen.Dish;
import com.chmykhun.restaurant.kitchen.Order;

import java.io.IOException;

public class TestOrder extends Order {

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() {
        int dishesNumber = 1 + (int) (Math.random() * Dish.values().length);
        for (int i = 0; i < dishesNumber; i++) {
            orderedDishes.add(Dish.values()[(int) (Math.random() * Dish.values().length)]);
        }
        System.out.println(orderedDishes.toString());
    }

}
