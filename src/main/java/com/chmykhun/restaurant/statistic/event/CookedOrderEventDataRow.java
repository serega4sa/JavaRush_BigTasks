package com.chmykhun.restaurant.statistic.event;

import com.chmykhun.restaurant.kitchen.Dish;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CookedOrderEventDataRow implements EventDataRow {

    private String tabletName;
    private String cookName;
    private int cookingTimeSeconds;
    private List<Dish> cookingDishs;
    private Date currentDate;

    public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishs) {
        this.tabletName = tabletName;
        this.cookName = cookName;
        this.cookingTimeSeconds = cookingTimeSeconds;
        this.cookingDishs = cookingDishs;
        this.currentDate = Calendar.getInstance().getTime();
    }

    public String getCookName() {
        return cookName;
    }

    @Override
    public EventType getType() {
        return EventType.COOKED_ORDER;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return cookingTimeSeconds;
    }
}
