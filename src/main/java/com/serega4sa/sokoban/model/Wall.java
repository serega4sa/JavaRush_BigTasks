package com.serega4sa.sokoban.model;

import java.awt.*;

public class Wall extends CollisionObject {

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(75, 27, 11));
        graphics.drawRect(getX(), getY(), getWidth(), getHeight());
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public <T extends GameObject> T cloneGameObject() {
        return (T) new Wall(getX(), getY());
    }
}
