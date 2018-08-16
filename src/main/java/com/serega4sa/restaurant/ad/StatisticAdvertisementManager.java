package com.serega4sa.restaurant.ad;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {

    private static final StatisticAdvertisementManager statAdvManager = new StatisticAdvertisementManager();
    private AdvertisementStorage advStorage;

    private StatisticAdvertisementManager() {
        advStorage = AdvertisementStorage.getInstance();
    }

    public static StatisticAdvertisementManager getInstance() {
        return statAdvManager;
    }

    public List<Advertisement> getActiveAdVideos() {
        return getAdVideos(true);
    }

    public List<Advertisement> getArchivedAdVideos() {
        return getAdVideos(false);
    }

    private List<Advertisement> getAdVideos(boolean active) {
        List<Advertisement> adVideos = new ArrayList<>();
        for (Advertisement advertisement : advStorage.getVideos()) {
            if ((active && advertisement.getHits() > 0) || (!active && advertisement.getHits() == 0)) {
                adVideos.add(advertisement);
            }
        }
        return adVideos;
    }
}
