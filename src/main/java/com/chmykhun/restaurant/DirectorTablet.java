package com.chmykhun.restaurant;

import com.chmykhun.restaurant.statistic.StatisticManager;

import java.util.Date;
import java.util.Map;

public class DirectorTablet {
    public void printAdvertisementProfit() {
        long totalAmount = 0;
        Map<Date, Long> reportData = StatisticManager.getInstance().getAdvertisementProfit();
        for (Map.Entry<Date, Long> oneDayData : reportData.entrySet()) {
            totalAmount += oneDayData.getValue();
            ConsoleHelper.writeMessage(String.format(ConsoleHelper.Messages.advertisementReportFormat, oneDayData.getKey(), oneDayData.getValue()));
        }
        ConsoleHelper.writeMessage("Total - " + totalAmount);
    }

    public void printCookWorkloading() {
        StatisticManager.getInstance().getCookWorkloading();
    }

    public void printActiveVideoSet() {
        StatisticManager.getInstance().getActiveVideoSet();
    }

    public void printArchivedVideoSet() {
        StatisticManager.getInstance().getArchivedVideoSet();
    }
}
