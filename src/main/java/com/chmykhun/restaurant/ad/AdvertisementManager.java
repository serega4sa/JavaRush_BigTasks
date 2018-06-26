package com.chmykhun.restaurant.ad;

import com.chmykhun.restaurant.ConsoleHelper;
import com.chmykhun.restaurant.statistic.StatisticManager;
import com.chmykhun.restaurant.statistic.event.NoAvailableVideoEventDataRow;
import com.chmykhun.restaurant.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class AdvertisementManager {
    private static final AdvertisementStorage storage = new AdvertisementStorage();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        processVideos(timeSeconds);
    }

    private void processVideos(int timeSeconds) throws NoVideoAvailableException {
        List<Advertisement> videos = new ArrayList<>(storage.getVideos());
        videos.sort(Comparator.comparing(Advertisement::getAmountPerOneDisplaying).reversed());
        boolean isVideoFound = false;
        for (Advertisement advertisement : videos) {
            if (advertisement.getDuration() <= timeSeconds) {
                isVideoFound = true;
                int amountPerSecond = (int) (advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());
                StatisticManager.getInstance().register(new VideoSelectedEventDataRow(Collections.singletonList(advertisement), amountPerSecond, advertisement.getDuration()));
                ConsoleHelper.writeMessage(String.format(ConsoleHelper.Messages.videoProcessing, advertisement.getName(), advertisement.getAmountPerOneDisplaying(), amountPerSecond));
                advertisement.revalidate();
                processVideos(timeSeconds - advertisement.getDuration());
            }
        }
        if (!isVideoFound) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();
        }
    }
}