package com.chmykhun.sokoban.controller;

import com.chmykhun.sokoban.model.Direction;
import com.chmykhun.sokoban.model.GameObjects;
import com.chmykhun.sokoban.model.Model;
import com.chmykhun.sokoban.view.View;

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
