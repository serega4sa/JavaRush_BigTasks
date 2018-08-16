package com.serega4sa.sokoban.view;

import com.serega4sa.sokoban.controller.EventListener;
import com.serega4sa.sokoban.model.Direction;
import com.serega4sa.sokoban.model.GameObject;
import com.serega4sa.sokoban.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Set;

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
        drawGrid(g);
        drawGameObjects(g);
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.GRAY);
        drawLines(g, true, getHeight());
        drawLines(g, false, getWidth());
    }

    private void drawLines(Graphics g, boolean isX, int maxPos) {
        for (int i = 1; i < maxPos; i++) {
            int sellPos = i * Model.FIELD_SELL_SIZE;
            if (isX) {
                g.drawLine(sellPos, 0, sellPos, maxPos);
            } else {
                g.drawLine(0, sellPos, maxPos, sellPos);
            }
        }
    }

    private void drawGameObjects(Graphics g) {
        drawGameObjects(g, view.getGameObjects().getWalls());
        drawGameObjects(g, view.getGameObjects().getHomes());
        drawGameObjects(g, view.getGameObjects().getBoxes());
        drawGameObjects(g, Collections.singleton(view.getGameObjects().getPlayer()));
    }

    private void drawGameObjects(Graphics g, Set<? extends GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
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
