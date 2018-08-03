package com.chmykhun.sokoban.model;

import java.awt.*;

public class Home extends GameObject {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    public Home(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(255,0,0,150));
        graphics.drawOval(getX() / 2, getY() / 2, getWidth(), getHeight());
        graphics.fillOval(getX() / 2, getY() / 2, getWidth(), getHeight());
    }
}
