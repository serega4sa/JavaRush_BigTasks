package com.chmykhun.game2048.model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Tile {

    private static final Map<Integer, Color> TILE_COLORS = new HashMap<>();

    private int value;

    static {
        TILE_COLORS.put(0, new Color(0xcdc1b4));
        TILE_COLORS.put(2, new Color(0xeee4da));
        TILE_COLORS.put(4, new Color(0xede0c8));
        TILE_COLORS.put(8, new Color(0xf2b179));
        TILE_COLORS.put(16, new Color(0xf59563));
        TILE_COLORS.put(32, new Color(0xf67c5f));
        TILE_COLORS.put(64, new Color(0xf65e3b));
        TILE_COLORS.put(128, new Color(0xedcf72));
        TILE_COLORS.put(256, new Color(0xedcc61));
        TILE_COLORS.put(512, new Color(0xedc850));
        TILE_COLORS.put(1024, new Color(0xedc53f));
        TILE_COLORS.put(2048, new Color(0xedc22e));
    }

    public Tile() {
        value = 0;
    }

    public Tile(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public Color getFontColor() {
        return value > 16 ? new Color(0x776e65) : new Color(0xf9f6f2);
    }

    public Color getTileColor() {
        return TILE_COLORS.containsKey(value) ? TILE_COLORS.get(value) : new Color(0xff0000);
    }
}
