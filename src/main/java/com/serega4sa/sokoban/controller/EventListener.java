package com.serega4sa.sokoban.controller;

import com.serega4sa.sokoban.model.Direction;

public interface EventListener {

    /**
     * Move object in specified direction
     * @param direction
     */
    void move(Direction direction);

    /**
     * Restart current level
     */
    void restart();

    /**
     * Start next level
     */
    void startNextLevel();

    /**
     * Specified level is completed
     * @param level
     */
    void levelCompleted(int level);
}
