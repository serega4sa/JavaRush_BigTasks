package com.chmykhun.game2048;

import com.chmykhun.game2048.controller.Controller;
import com.chmykhun.game2048.model.Model;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        Controller controller = new Controller(new Model());

        JFrame game = new JFrame();
        game.setTitle("2048");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(450, 500);
        game.setResizable(false);
        game.add(controller.getView());
        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}
