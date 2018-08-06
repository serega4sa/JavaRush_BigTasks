package com.chmykhun.sokoban.view;

import com.chmykhun.sokoban.controller.EventListener;
import com.chmykhun.sokoban.model.GameObject;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {

    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        for (GameObject gameObject : view.getGameObjects().getAll()) {
            gameObject.draw(g);
        }
    }
}
