package com.chmykhun.game2048.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model {

    public static final int FIELD_WIDTH = 4;

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
        addTile();
    }

    public List<List<Tile>> getGameTiles() {
        return gameTiles;
    }

    public void resetGameTiles() {
        initGameTiles();
    }

    public int getScore() {
        return score;
    }

    public void resetGameScore() {
        score = 0;
    }

    public int getMaxTile() {
        return maxTile;
    }

    public void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        emptyTiles.get((int) (emptyTiles.size() * Math.random())).setValue(Math.random() < 0.9 ? 2 : 4);
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty()) {
            return true;
        }

        List<List<Tile>> origGameTiles = getGameTilesDeepCopy();
        int origScore = score;

        for (Direction direction : Direction.values()) {
            move(direction);
        }

        boolean canMove = compareLists(origGameTiles, gameTiles);

        gameTiles = origGameTiles;
        score = origScore;

        return canMove;
    }

    public void left() {
        move(Direction.LEFT);
    }

    public void up() {
        move(Direction.UP);
    }

    public void right() {
        move(Direction.RIGHT);
    }

    public void down() {
        move(Direction.DOWN);
    }

    private void move(Direction direction) {
        boolean isTilesCompressed = false;
        boolean isTilesMerged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            List<Tile> tilesLine = getTilesLine(direction, i);
            isTilesCompressed |= compressTiles(tilesLine);
            isTilesMerged |= mergeTiles(tilesLine);
        }
        if (isTilesCompressed || isTilesMerged) {
            addTile();
        }
    }

    /**
     * Generates list of tiles in one line according to move direction. As compression and merge are performed from the left to the right.
     *
     * For example:
     *      0 2 0 4
     *      4 0 0 0
     *      2 0 0 0
     *      0 0 0 0
     * resulting list for i=0 will be:
     *      left:  [0, 2, 0, 4]
     *      up:    [0, 4, 2, 0]
     *      right: [4, 0, 2, 0]
     *      down:  [0, 2, 4, 0]
     *
     * @param direction is direction in which performs movement
     * @param i is index of line
     * @return list of tiles in one line
     */
    private List<Tile> getTilesLine(Direction direction, int i) {
        List<Tile> tilesLine = new ArrayList<>();
        switch (direction) {
            case LEFT: {
                for (List<Tile> gameTile : gameTiles) {
                    tilesLine.add(gameTile.get(i));
                }
                break;
            }
            case UP: {
                tilesLine = new ArrayList<>(gameTiles.get(i));
                break;
            }
            case RIGHT: {
                for (List<Tile> gameTile : gameTiles) {
                    tilesLine.add(gameTile.get(i));
                }
                Collections.reverse(tilesLine);
                break;
            }
            case DOWN: {
                tilesLine = new ArrayList<>(gameTiles.get(i));
                Collections.reverse(tilesLine);
                break;
            }
        }
        return tilesLine;
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
        for (int i = 0; i < tiles.size() - 1; i++) {
            if (tiles.get(i).getValue() == 0 && tiles.get(i + 1).getValue() > 0) {
                tiles.get(i).setValue(tiles.get(i + 1).getValue());
                tiles.get(i + 1).setValue(0);
                compressTiles(tiles);
                isTilesCompressed = true;
            }
        }
        return isTilesCompressed;
    }

    private boolean mergeTiles(List<Tile> tiles) {
        boolean isTilesMerged = false;
        for (int i = 0; i < tiles.size() - 1; i++) {
            if (tiles.get(i).getValue() > 0 && tiles.get(i).getValue() == tiles.get(i + 1).getValue()) {
                int newTileValue = tiles.get(i).getValue() * 2;
                maxTile = newTileValue > maxTile ? newTileValue : maxTile;
                score += newTileValue;
                tiles.get(i).setValue(newTileValue);
                tiles.get(i + 1).setValue(0);
                compressTiles(tiles);
                isTilesMerged = true;
            }
        }
        return isTilesMerged;
    }

    /**
     * Creates a copy of two-dimensional game tiles list
     * @return Deep copy of game tiles list
     */
    private List<List<Tile>> getGameTilesDeepCopy() {
        List<List<Tile>> copyGameTiles = new ArrayList<>();
        for (List<Tile> gameTile : gameTiles) {
            List<Tile> gameTiles = new ArrayList<>();
            for (Tile tile : gameTile) {
                gameTiles.add(new Tile(tile.getValue()));
            }
            copyGameTiles.add(gameTiles);
        }
        return copyGameTiles;
    }

    /**
     * Compares two-dimensional lists
     * @return true if lists are different
     */
    private boolean compareLists(List<List<Tile>> list1, List<List<Tile>> list2) {
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list1.get(i).size(); j++) {
                if (list1.get(i).get(j).getValue() != list2.get(i).get(j).getValue()) {
                    return true;
                }
            }
        }
        return false;
    }
}
