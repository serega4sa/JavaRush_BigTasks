package com.chmykhun.sokoban.model;

import com.chmykhun.sokoban.controller.EventListener;

import java.nio.file.Paths;

public class Model {

    public static final int FIELD_SELL_SIZE = 20;

    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("..\\res\\levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    private void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }
}
