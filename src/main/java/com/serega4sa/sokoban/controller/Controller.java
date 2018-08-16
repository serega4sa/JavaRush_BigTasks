package com.serega4sa.sokoban.controller;

import com.serega4sa.sokoban.model.Direction;
import com.serega4sa.sokoban.model.GameObjects;
import com.serega4sa.sokoban.model.Model;
import com.serega4sa.sokoban.view.View;

public class Controller implements EventListener {

    private View view;
    private Model model;

    public Controller() {
        view = new View(this);
        model = new Model();
        view.init();
        model.restart();
        view.setEventListener(this);
        model.setEventListener(this);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

    public int getCurrentLevel() {
        return model.getCurrentLevel();
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }
}
