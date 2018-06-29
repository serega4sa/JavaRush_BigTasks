package com.chmykhun.restaurant;

import com.chmykhun.restaurant.ad.Advertisement;
import com.chmykhun.restaurant.kitchen.Cook;
import com.chmykhun.restaurant.statistic.StatisticEventManager;

import java.util.Comparator;
import java.util.List;
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
        Map<String, Long> reportData = StatisticEventManager.getInstance().getAdvertisementProfit();
        for (Map.Entry<String, Long> oneDayData : reportData.entrySet()) {
            totalAmount += oneDayData.getValue();
            ConsoleHelper.writeMessage(String.format(ConsoleHelper.Messages.commonReportFormat, oneDayData.getKey(), oneDayData.getValue()));
        }
        ConsoleHelper.writeMessage("Total - " + totalAmount);
    }

    private void printCookWorkloading() {
        ConsoleHelper.writeMessage(ConsoleHelper.Messages.cookWorkloadReportHead);
        Map<String, Map<Cook, Long>> reportData = StatisticEventManager.getInstance().getCookWorkloading();
        for (Map.Entry<String, Map<Cook, Long>> oneDayData : reportData.entrySet()) {
            ConsoleHelper.writeMessage(oneDayData.getKey());
            for (Map.Entry<Cook, Long> cookData : oneDayData.getValue().entrySet()) {
                ConsoleHelper.writeMessage(String.format(ConsoleHelper.Messages.cookWorkloadReportFormat, cookData.getKey().getCookName(), cookData.getValue() / 60));
            }
        }
    }

    private void printActiveVideoSet() {
        ConsoleHelper.writeMessage(ConsoleHelper.Messages.activeVideoReportHead);
        printVideoSet(true);
    }

    private void printArchivedVideoSet() {
        ConsoleHelper.writeMessage(ConsoleHelper.Messages.archivedVideoReportHead);
        printVideoSet(false);
    }

    private void printVideoSet(boolean active) {
        List<Advertisement> videoSet = active ? StatisticEventManager.getInstance().getActiveVideoSet() : StatisticEventManager.getInstance().getArchivedVideoSet();
        videoSet.sort(Comparator.comparing(Advertisement::getName));
        for (Advertisement advertisement : videoSet) {
            if (active) {
                ConsoleHelper.writeMessage(String.format(ConsoleHelper.Messages.commonReportFormat, advertisement.getName(), advertisement.getHits()));
            } else {
                ConsoleHelper.writeMessage(advertisement.getName());
            }
        }
    }
}
