package com.serega4sa.sokoban.view;

import com.serega4sa.sokoban.controller.Controller;
import com.serega4sa.sokoban.controller.EventListener;
import com.serega4sa.sokoban.model.GameObjects;

import javax.swing.*;

public class View extends JFrame {

    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        field = new Field(this);
        add(field);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Sokoban. Level: #" + controller.getCurrentLevel());
        setVisible(true);
    }

    public void update() {
        field.repaint();
    }

    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);
    }

    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }

    public void completed(int level) {
        update();
        JOptionPane.showMessageDialog(this, "Congratulations! You've completed level #" + level);
        controller.startNextLevel();
    }
}
