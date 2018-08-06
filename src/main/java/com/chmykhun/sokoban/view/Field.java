package com.chmykhun.sokoban.view;

import com.chmykhun.sokoban.controller.EventListener;
import com.chmykhun.sokoban.model.Direction;
import com.chmykhun.sokoban.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel {

    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        addKeyListener(new KeyHandler());
        setFocusable(true);
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

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                eventListener.move(Direction.LEFT);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                eventListener.move(Direction.RIGHT);
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                eventListener.move(Direction.UP);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                eventListener.move(Direction.DOWN);
            } else if (e.getKeyCode() == KeyEvent.VK_R) {
                eventListener.restart();
            }
        }
    }
}
