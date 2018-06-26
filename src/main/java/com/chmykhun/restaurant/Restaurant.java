package com.chmykhun.restaurant;

import com.chmykhun.restaurant.kitchen.Cook;
import com.chmykhun.restaurant.kitchen.Waitor;
import com.chmykhun.restaurant.statistic.StatisticManager;

public class Restaurant {

//    private static List<String> cookNames = Arrays.asList("Amigo", "Bender", "Robert", "Max", "Alex", "Tom", "Jon");
//    private static List<Cook> cookSet;
//    private static List<Tablet> tablets;

    public static void main(String[] args) {
        Waitor waitor = new Waitor();
        Cook cook = new Cook("Amigo");
        cook.addObserver(waitor);
        StatisticManager.getInstance().register(cook);

        Tablet tablet = new Tablet(1);
        tablet.addObserver(cook);
        tablet.createOrder();

//        cookSet = createCookSet(5);
//        tablets = createTablets(1);

        DirectorTablet dirTabl = new DirectorTablet();
        dirTabl.printAdvertisementProfit();
    }

    /*private static List<Cook> createCookSet(int number) {
        List<Cook> cookSet = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Waitor waitor = new Waitor();
            Cook cook = new Cook(cookNames.size() > i ? cookNames.get(i) : "newcomer" + i);
            cook.addObserver(waitor);
            StatisticManager.getInstance().register(cook);
            cookSet.add(cook);
        }
        return cookSet;
    }

    private static List<Tablet> createTablets(int number) {
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Tablet tablet = new Tablet(1);
            for (Cook cook : cookSet) {
                tablet.addObserver(cook);
            }
            tablet.createOrder();
        }
        return tablets;
    }*/
}
