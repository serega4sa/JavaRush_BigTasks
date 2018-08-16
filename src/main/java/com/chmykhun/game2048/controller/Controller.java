package com.chmykhun.game2048.controller;

import com.chmykhun.game2048.model.Model;
import com.chmykhun.game2048.model.Tile;
import com.chmykhun.game2048.view.View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Controller extends KeyAdapter {

    private static final int WINNING_TILE = 2048;

    private Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    public View getView() {
        return view;
    }

    public List<List<Tile>> getGameTiles() {
        return model.getGameTiles();
    }

    public int getScore() {
        return model.getScore();
    }

    private void resetGame() {
        view.setGameWon(false);
        view.setGameLost(false);
        model.resetGameScore();
        model.resetGameTiles();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (model.canMove()) {
            if (!view.isGameWon() && !view.isGameLost()) {
                model.setSaveNeeded(true);
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    model.left();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    model.up();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    model.right();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    model.down();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    resetGame();
                } else if (e.getKeyCode() == KeyEvent.VK_Z) {
                    model.rollBack();
                } else if (e.getKeyCode() == KeyEvent.VK_R) {
                    model.randomMove();
                }
                if (model.getMaxTile() == WINNING_TILE) {
                    view.setGameWon(true);
                }
            }
        } else {
            view.setGameLost(true);
        }
        view.repaint();
    }
}
