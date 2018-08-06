package com.chmykhun.sokoban.view;

import com.chmykhun.sokoban.controller.EventListener;

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
        //TODO: implement logic
    }
}
