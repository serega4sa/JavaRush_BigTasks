package com.chmykhun.restaurant;

import com.chmykhun.restaurant.kitchen.Cook;
import com.chmykhun.restaurant.statistic.StatisticManager;

import java.util.Map;

public class DirectorTablet {

    public void printReport() {
        ConsoleHelper.writeMessage(ConsoleHelper.Messages.reportHead);
        printAdvertisementProfit();
        printCookWorkloading();
        printActiveVideoSet();
        printArchivedVideoSet();
    }

    private void printAdvertisementProfit() {
        ConsoleHelper.writeMessage(ConsoleHelper.Messages.advertisementReportHead);
        long totalAmount = 0;
        Map<String, Long> reportData = StatisticManager.getInstance().getAdvertisementProfit();
        for (Map.Entry<String, Long> oneDayData : reportData.entrySet()) {
            totalAmount += oneDayData.getValue();
            ConsoleHelper.writeMessage(String.format(ConsoleHelper.Messages.advertisementReportFormat, oneDayData.getKey(), oneDayData.getValue()));
        }
        ConsoleHelper.writeMessage("Total - " + totalAmount);
    }

    private void printCookWorkloading() {
        ConsoleHelper.writeMessage(ConsoleHelper.Messages.cookWorkloadReportHead);
        Map<String, Map<Cook, Long>> reportData = StatisticManager.getInstance().getCookWorkloading();
        for (Map.Entry<String, Map<Cook, Long>> oneDayData : reportData.entrySet()) {
            ConsoleHelper.writeMessage(oneDayData.getKey());
            for (Map.Entry<Cook, Long> cookData : oneDayData.getValue().entrySet()) {
                ConsoleHelper.writeMessage(String.format(ConsoleHelper.Messages.cookWorkloadReportFormat, cookData.getKey().getCookName(), cookData.getValue() / 60));
            }
            ConsoleHelper.writeMessage("");
        }
    }

    private void printActiveVideoSet() {
        StatisticManager.getInstance().getActiveVideoSet();
    }

    private void printArchivedVideoSet() {
        StatisticManager.getInstance().getArchivedVideoSet();
    }
}
