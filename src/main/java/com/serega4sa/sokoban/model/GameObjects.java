package com.serega4sa.sokoban.model;

import java.util.HashSet;
import java.util.Set;

public class GameObjects {

    private Set<Wall> walls = new HashSet<>();
    private Set<Box> boxes = new HashSet<>();
    private Set<Home> homes = new HashSet<>();
    private Player player;

    public Set<Wall> getWalls() {
        return walls;
    }

    public void addWall(Wall wall) {
        this.walls.add(wall);
    }

    public void setWalls(Set<Wall> walls) {
        this.walls = walls;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public void addBox(Box boxe) {
        this.boxes.add(boxe);
    }

    public void setBoxes(Set<Box> boxes) {
        this.boxes = boxes;
    }

    public Set<Home> getHomes() {
        return homes;
    }

    public void addHome(Home home) {
        this.homes.add(home);
    }

    public void setHomes(Set<Home> homes) {
        this.homes = homes;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Set<GameObject> getAll() {
        Set<GameObject> allElements = new HashSet<>();
        allElements.addAll(walls);
        allElements.addAll(boxes);
        allElements.addAll(homes);
        allElements.add(player);
        return allElements;
    }
}
