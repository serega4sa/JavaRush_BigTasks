package com.serega4sa.restaurant.statistic.event;

import com.serega4sa.restaurant.ad.Advertisement;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class VideoSelectedEventDataRow implements EventDataRow {

    private List<Advertisement> optimalVideoSet;
    private long amount;
    private int totalDuration;
    private Date currentDate;

    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration) {
        this.optimalVideoSet = optimalVideoSet;
        this.amount = amount;
        this.totalDuration = totalDuration;
        this.currentDate = Calendar.getInstance().getTime();
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public EventType getType() {
        return EventType.SELECTED_VIDEOS;
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
