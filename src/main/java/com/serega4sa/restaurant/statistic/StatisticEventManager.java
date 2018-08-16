package com.serega4sa.restaurant.statistic;

import com.serega4sa.restaurant.ad.Advertisement;
import com.serega4sa.restaurant.ad.StatisticAdvertisementManager;
import com.serega4sa.restaurant.kitchen.Cook;
import com.serega4sa.restaurant.kitchen.Waitor;
import com.serega4sa.restaurant.statistic.event.CookedOrderEventDataRow;
import com.serega4sa.restaurant.statistic.event.EventDataRow;
import com.serega4sa.restaurant.statistic.event.EventType;
import com.serega4sa.restaurant.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StatisticEventManager {

    private static StatisticEventManager manager = new StatisticEventManager();
    private StatisticStorage storage;
    private Map<String, Long> advertisementProfitReport;
    private Map<String, Map<Cook, Long>> cookWorkloadingReport;
    private Set<Cook> cookSet = new HashSet<>();
    private Set<Waitor> waitorSet = new HashSet<>();

    public static StatisticEventManager getInstance() {
        return manager;
    }

    private StatisticEventManager() {
        storage = new StatisticStorage();
    }

    public void register(EventDataRow data) {
        storage.put(data);
    }

    public void register(Cook cook) {
        cookSet.add(cook);
    }

    public void register(Waitor waitor) {
        waitorSet.add(waitor);
    }

    public Map<String, Long> getAdvertisementProfit() {
        advertisementProfitReport = new HashMap<>();
        for (EventDataRow eventDataRow : storage.getData().get(EventType.SELECTED_VIDEOS)) {
            addProfitData(eventDataRow);
        }
        return advertisementProfitReport;
    }

    /**
     * Adds profit data to the source map. If there are some data, the new data will be added to existing
     */
    private void addProfitData(EventDataRow eventDataRow) {
        long profit = ((VideoSelectedEventDataRow) eventDataRow).getAmount();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String eventDate = sdf.format(eventDataRow.getDate());
        if (isDatePresent(advertisementProfitReport, eventDate)) {
            profit += advertisementProfitReport.get(eventDate);
        }
        advertisementProfitReport.put(eventDate, profit);
    }

    public Map<String, Map<Cook, Long>> getCookWorkloading() {
        cookWorkloadingReport = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        for (EventDataRow eventDataRow : storage.getData().get(EventType.COOKED_ORDER)) {
            String eventDate = sdf.format(eventDataRow.getDate());
            Map<Cook, Long> cookDailyWorkDuration = getCookDailyWorkDuration(eventDate, eventDataRow);
            addDailyData(eventDate, cookDailyWorkDuration);
        }
        return cookWorkloadingReport;
    }

    private Map<Cook, Long> getCookDailyWorkDuration(String eventDate, EventDataRow eventDataRow) {
        Map<Cook, Long> cookDailyWorkDuration = new HashMap<>();
        if (isDatePresent(cookWorkloadingReport, eventDate)) {
            cookDailyWorkDuration = new HashMap<>(cookWorkloadingReport.get(eventDate));
        }
        for (Cook cook : cookSet) {
            if (((CookedOrderEventDataRow) eventDataRow).getCookName().equals(cook.getCookName())) {
                long workDuration = eventDataRow.getTime();
                if (cookDailyWorkDuration.containsKey(cook)) {
                    workDuration += cookDailyWorkDuration.get(cook);
                }
                cookDailyWorkDuration.put(cook, workDuration);
            }
        }
        return cookDailyWorkDuration;
    }

    /**
     * Adds data to the source map. If there are some data, the new data will be added to existing
     * @param date - date for which this data was generated
     * @param cookDailyWorkDuration - data that will be inserted
     */
    private void addDailyData(String date, Map<Cook, Long> cookDailyWorkDuration) {
        if (isDatePresent(cookWorkloadingReport, date)) {
            for (Map.Entry<Cook, Long> cookData : cookDailyWorkDuration.entrySet()) {
                Map<Cook, Long> reportDayData = cookWorkloadingReport.get(date);
                if (reportDayData.containsKey(cookData.getKey())) {
                    reportDayData.put(cookData.getKey(), reportDayData.get(cookData.getKey()) + cookData.getValue());
                } else {
                    reportDayData.put(cookData.getKey(), cookData.getValue());
                }
            }
        } else {
            cookWorkloadingReport.put(date, cookDailyWorkDuration);
        }
    }

    /**
     * Check whether map contains key with specified date (without taking into an account a time)
     * @param in - source map
     * @param date - date in string format
     * @return true, if map contains specified date
     */
    private <K extends String, V> boolean isDatePresent(Map<K, V> in, String date) {
        for (Map.Entry<K, V> inEntry : in.entrySet()) {
            if (inEntry.getKey().equals(date)) {
                return true;
            }
        }
        return false;
    }

    public List<Advertisement> getActiveVideoSet() {
        return StatisticAdvertisementManager.getInstance().getActiveAdVideos();
    }

    public List<Advertisement> getArchivedVideoSet() {
        return StatisticAdvertisementManager.getInstance().getArchivedAdVideos();
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
