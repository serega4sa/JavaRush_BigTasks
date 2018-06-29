package com.chmykhun.restaurant.ad;

public class Advertisement {

    private String name;
    private long initialAmount;
    private int hits;
    private int duration;
    private long amountPerOneDisplaying;

    public Advertisement(String name, long initialAmount, int hits, int duration) {
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        amountPerOneDisplaying = calculateAmountPerOneDisplaying();
    }

    public String getName() {
        return name;
    }

    public int getHits() {
        return hits;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    private long calculateAmountPerOneDisplaying() {
        return initialAmount / hits;
    }

    public void revalidate() {
        if (hits <= 0) {
            throw new UnsupportedOperationException();
        }
        hits--;
        amountPerOneDisplaying = hits > 0 ? calculateAmountPerOneDisplaying() : 0;
    }
}
