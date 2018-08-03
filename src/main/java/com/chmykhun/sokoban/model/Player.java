package com.chmykhun.sokoban.model;

import java.awt.*;

public class Player extends GameObject implements Movable {

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
        graphics.drawOval(getX() / 2, getY() / 2, getWidth() / 2, getHeight());
        graphics.fillOval(getX() / 2, getY() / 2, getWidth() / 2, getHeight());
    }
}