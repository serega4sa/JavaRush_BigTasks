package com.chmykhun.sokoban.model;

import com.chmykhun.sokoban.controller.EventListener;

import java.nio.file.Paths;

public class Model {

    public static final int FIELD_SELL_SIZE = 20;

    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("./src/main/java/" +
            getClass().getPackage().getName()
                    .replaceAll("\\.", "/")
                    .replace("model", "res/levels.txt")));

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

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction) || checkBoxCollision(player, direction)) {
            return;
        }
        player.move(player.getDelta(true, direction), player.getDelta(false, direction));
        checkCompletion();
    }

    private boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (wall.isCollision(gameObject, direction)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkBoxCollision(CollisionObject gameObject, Direction direction) {
        for (Box box : gameObjects.getBoxes()) {
            if (box.isCollision(gameObject, direction)) {
                if (gameObject instanceof Player) {
                    boolean isBoxCanBeMoved = !checkWallCollision(box, direction) && !checkBoxCollision(box, direction);
                    if (isBoxCanBeMoved) {
                        box.move(box.getDelta(true, direction), box.getDelta(false, direction));
                        break;
                    }
                    return true;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkCompletion() {
        int filledHomes = 0;
        for (Home home : gameObjects.getHomes()) {
            for (Box box : gameObjects.getBoxes()) {
                if (home.getX() == box.getX() && home.getY() == box.getY()) {
                    filledHomes++;
                    break;
                }
            }
        }
        if (filledHomes == gameObjects.getHomes().size()) {
            eventListener.levelCompleted(currentLevel);
        }
    }
}
