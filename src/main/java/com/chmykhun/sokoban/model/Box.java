package com.chmykhun.sokoban.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {

    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.drawRect(getX(), getY(), getWidth(), getHeight());
        graphics.setColor(Color.GRAY);
        graphics.fillRect(getX() + 1, getY() + 1, getWidth() - 1, getHeight() - 1);
        graphics.setColor(Color.ORANGE);
        graphics.drawLine(getX(), getY(), getX() + getWidth(), getY() + getHeight());
        graphics.drawLine(getX(), getY() + getHeight(), getX() + getWidth(), getY());
    }
}
