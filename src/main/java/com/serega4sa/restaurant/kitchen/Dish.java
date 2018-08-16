package com.serega4sa.restaurant.kitchen;

public enum Dish {
    Fish(25*60),
    Steak(30*60),
    Soup(15*60),
    Juice(5*60),
    Water(3);

    private int duration;

    Dish(int cookingTime) {
        duration = cookingTime;
    }

    public int getDuration() {
        return duration;
    }

    public static boolean isDish(String input) {
        for (Dish dish : values()) {
            if (dish.name().toLowerCase().equals(input.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
