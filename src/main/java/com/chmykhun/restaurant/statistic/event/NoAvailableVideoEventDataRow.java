package com.chmykhun.restaurant.statistic.event;

import java.util.Calendar;
import java.util.Date;

public class NoAvailableVideoEventDataRow implements EventDataRow {

    private int totalDuration;
    private Date currentDate;

    public NoAvailableVideoEventDataRow(int totalDuration) {
        this.totalDuration = totalDuration;
        this.currentDate = Calendar.getInstance().getTime();
    }

    @Override
    public EventType getType() {
        return EventType.NO_AVAILABLE_VIDEO;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return 0;
    }
}
