package com.chmykhun.game2048.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private final int FIELD_WIDTH = 4;

    private List<List<Tile>> gameTiles;

    public Model() {
        gameTiles = new ArrayList<>();
        for (int x = 0; x < FIELD_WIDTH; x++) {
            List<Tile> tilesColumn = new ArrayList<>();
            for (int y = 0; y < FIELD_WIDTH; y++) {
                tilesColumn.add(new Tile());
            }
            gameTiles.add(tilesColumn);
        }
    }
}
