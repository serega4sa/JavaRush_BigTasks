package com.chmykhun.sokoban.model;

public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        return (getXAfterMove(gameObject.getX(), direction) == getX() &&
                getYAfterMove(gameObject.getY() , direction) == getY());
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
                return -Model.FIELD_SELL_SIZE;
            } else if (direction.equals(Direction.RIGHT)) {
                return Model.FIELD_SELL_SIZE;
            }
        } else {
            if (direction.equals(Direction.UP)) {
                return -Model.FIELD_SELL_SIZE;
            } else if (direction.equals(Direction.DOWN)) {
                return Model.FIELD_SELL_SIZE;
            }
        }
        return 0;
    }
}
