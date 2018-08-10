package com.chmykhun.game2048.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private final int FIELD_WIDTH = 4;

    private List<List<Tile>> gameTiles;
    private int score;
    private int maxTile;

    public Model() {
        initGameTiles();
    }

    private void initGameTiles() {
        gameTiles = new ArrayList<>();
        for (int x = 0; x < FIELD_WIDTH; x++) {
            List<Tile> tilesColumn = new ArrayList<>();
            for (int y = 0; y < FIELD_WIDTH; y++) {
                tilesColumn.add(new Tile());
            }
            gameTiles.add(tilesColumn);
        }
    }

    public void resetGameTiles() {
        initGameTiles();
    }

    public void left() {
        boolean isTilesCompressed = false;
        boolean isTilesMerged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            List<Tile> tilesLine = new ArrayList<>();
            for (List<Tile> gameTile : gameTiles) {
                tilesLine.add(gameTile.get(i));
            }
            isTilesCompressed = compressTiles(tilesLine);
            isTilesMerged = mergeTiles(tilesLine);
        }
        if (isTilesCompressed || isTilesMerged) {
            addTile();
        }
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        emptyTiles.get((int) (emptyTiles.size() * Math.random())).setValue(Math.random() < 0.9 ? 2 : 4);
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (List<Tile> gameTilesColumn : gameTiles) {
            for (Tile tile : gameTilesColumn) {
                if (tile.isEmpty()) {
                    emptyTiles.add(tile);
                }
            }
        }
        return emptyTiles;
    }

    private boolean compressTiles(List<Tile> tiles) {
        boolean isTilesCompressed = false;
        for (int i = 1; i <= tiles.size(); i++) {
            if (tiles.get(i).getValue() > 0 && tiles.get(i - 1).getValue() == 0) {
                tiles.get(i - 1).setValue(tiles.get(i).getValue());
                tiles.get(i).setValue(0);
                isTilesCompressed = true;
            }
        }
        return isTilesCompressed;
    }

    private boolean mergeTiles(List<Tile> tiles) {
        boolean isTilesMerged = false;
        for (int i = 0; i < tiles.size() - 1; i++) {
            if (tiles.get(i).getValue() > 0 && tiles.get(i).getValue() == tiles.get(i + 1).getValue()) {
                tiles.get(i).setValue(tiles.get(i).getValue() * 2);
                tiles.get(i + 1).setValue(0);
                compressTiles(tiles);
                isTilesMerged = true;
                mergeTiles(tiles);
            }
        }
        return isTilesMerged;
    }
}
