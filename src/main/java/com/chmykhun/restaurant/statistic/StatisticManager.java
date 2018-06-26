package com.chmykhun.restaurant.statistic;

import com.chmykhun.restaurant.ad.Advertisement;
import com.chmykhun.restaurant.kitchen.Cook;
import com.chmykhun.restaurant.statistic.event.EventDataRow;
import com.chmykhun.restaurant.statistic.event.EventType;
import com.chmykhun.restaurant.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StatisticManager {

    private static StatisticManager manager = new StatisticManager();
    private StatisticStorage storage;
    private Set<Cook> cookSet = new HashSet<>();

    public static StatisticManager getInstance() {
        return manager;
    }

    private StatisticManager() {
        storage = new StatisticStorage();
    }

    public void register(EventDataRow data) {
        storage.put(data);
    }

    public void register(Cook cook) {
        cookSet.add(cook);
    }

    public Map<Date, Long> getAdvertisementProfit() {
        Map<Date, Long> advertisementProfitReport = new HashMap<>();
        for (EventDataRow eventDataRow : storage.getData().get(EventType.SELECTED_VIDEOS)) {
            addProfitData(advertisementProfitReport, eventDataRow);
        }
        return advertisementProfitReport;
    }

    private void addProfitData(Map<Date, Long> advertisementProfitReport, EventDataRow eventDataRow) {
        long profit = ((VideoSelectedEventDataRow) eventDataRow).getAmount();
        if (advertisementProfitReport.containsKey(eventDataRow.getDate())) {
            profit += advertisementProfitReport.get(eventDataRow.getDate());
        }
        advertisementProfitReport.put(eventDataRow.getDate(), profit);
    }

    public long getCookWorkloading() {
        return 0;
    }

    public List<Advertisement> getActiveVideoSet() {
        return null;
    }

    public List<Advertisement> getArchivedVideoSet() {
        return null;
    }

    class StatisticStorage {

        private Map<EventType, List<EventDataRow>> data;

        StatisticStorage() {
            data = new HashMap<>();
            for (EventType eventType : EventType.values()) {
                data.put(eventType, new ArrayList<>());
            }
        }

        void put(EventDataRow newData) {
            data.get(newData.getType()).add(newData);
        }

        public Map<EventType, List<EventDataRow>> getData() {
            return data;
        }
    }
}
