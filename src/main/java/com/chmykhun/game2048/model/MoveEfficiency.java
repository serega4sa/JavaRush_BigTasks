package com.chmykhun.game2048.model;

public class MoveEfficiency implements Comparable<MoveEfficiency> {

    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency that) {
        int efficiencyByTiles = Integer.compare(numberOfEmptyTiles, that.numberOfEmptyTiles);
        if (efficiencyByTiles == 0) {
            return Integer.compare(score, that.score);
        }
        return efficiencyByTiles;
    }
}
