package com.chmykhun.sokoban.model;

public class CollisionObject {

    private int x;
    private int y;

    public CollisionObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        return (getXAfterMove(gameObject.getX(), direction) == x &&
                getYAfterMove(gameObject.getY() , direction) == y);
    }

    private int getXAfterMove(int x, Direction direction) {
        return x + getDelta(true, direction);
    }

    private int getYAfterMove(int y, Direction direction) {
        return y + getDelta(false, direction);
    }

    private int getDelta(boolean isX, Direction direction) {
        if (isX) {
            if (direction.equals(Direction.LEFT)) {
                return -1;
            } else if (direction.equals(Direction.RIGHT)) {
                return 1;
            }
        } else {
            if (direction.equals(Direction.UP)) {
                return -1;
            } else if (direction.equals(Direction.DOWN)) {
                return 1;
            }
        }
        return 0;
    }
}
