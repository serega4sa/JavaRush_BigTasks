package com.chmykhun.game2048.controller;

import com.chmykhun.game2048.model.Model;

public class Controller {

    private Model model;

    public Controller() {
        model = new Model();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}
