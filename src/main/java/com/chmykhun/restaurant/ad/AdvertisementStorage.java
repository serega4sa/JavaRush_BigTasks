package com.chmykhun.restaurant.ad;

import java.util.ArrayList;
import java.util.List;

class AdvertisementStorage {
    private static final AdvertisementStorage storage = new AdvertisementStorage();
    private List<Advertisement> videos = new ArrayList<>();

    public AdvertisementStorage() {
        addAdVideo(new Advertisement( "First video", 5000, 100, 3 * 60));
        addAdVideo(new Advertisement( "Second video", 100, 10, 15 * 60));
        addAdVideo(new Advertisement( "Third video", 400, 2, 10 * 60));
    }

    public static AdvertisementStorage getAdStorage() {
        return storage;
    }

    public List<Advertisement> getVideos() {
        return videos;
    }

    public void addAdVideo(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
