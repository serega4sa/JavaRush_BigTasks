package com.chmykhun.sokoban.controller;

import com.chmykhun.sokoban.model.Direction;
import com.chmykhun.sokoban.model.Model;
import com.chmykhun.sokoban.view.View;

public class Controller implements EventListener {

    private View view;
    private Model model;

    public Controller() {
        view = new View(this);
        model = new Model();
        view.init();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }

    @Override
    public void move(Direction direction) {
        //TODO: implement logic
    }

    @Override
    public void restart() {
        //TODO: implement logic
    }

    @Override
    public void startNextLevel() {
        //TODO: implement logic
    }

    @Override
    public void levelCompleted(int level) {
        //TODO: implement logic
    }
}
