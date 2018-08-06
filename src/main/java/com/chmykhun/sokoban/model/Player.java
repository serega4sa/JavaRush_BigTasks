package com.chmykhun.sokoban.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {

    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.drawOval(getX() + Model.FIELD_SELL_SIZE / 4, getY(), getWidth() / 2, getHeight());
        graphics.fillOval(getX() + Model.FIELD_SELL_SIZE / 4, getY(), getWidth() / 2, getHeight());
    }
}
