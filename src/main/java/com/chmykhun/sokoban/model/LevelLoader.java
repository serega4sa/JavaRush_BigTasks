package com.chmykhun.sokoban.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {

    private Path levelsSource;
    private List<GameObjects> levels = new ArrayList<>();

    public LevelLoader(Path levelsSource) {
        this.levelsSource = levelsSource;
        parseLevels();
    }

    public GameObjects getLevel(int level) {
        return levels.get(level - 1);
    }

    private void parseLevels() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(levelsSource.toFile()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches("Maze")) {
                    List<String> levelConfig = getLevelConfiguration(reader);
                    int width = getIntAfterColumn(levelConfig.get(1));
                    int height = getIntAfterColumn(levelConfig.get(2));
                    GameObjects gameObjects = new GameObjects();
                    for (int i = 6; i < 6 + height; i++) {
                        createGameObjects(levelConfig.get(i).substring(0, width), i - 6, gameObjects);
                    }
                    levels.add(gameObjects);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getIntAfterColumn(String str) {
        return Integer.parseInt(str.substring(str.indexOf(":") + 2));
    }

    private List<String> getLevelConfiguration(BufferedReader reader) throws IOException {
        List<String> levelConfig = new ArrayList<>();
        String line;
        while (!(line = reader.readLine()).matches("\\*{5,}")) {
            levelConfig.add(line);
        }
        return levelConfig;
    }

    private void createGameObjects(String fieldLine, int lineIndex, GameObjects gameObjects) {
        String[] objects = fieldLine.split("");
        for (int i = 0; i < objects.length; i++) {
            if (!objects[i].equals(" ")) {
                int x = i * Model.FIELD_SELL_SIZE;
                int y = lineIndex * Model.FIELD_SELL_SIZE;
                if (objects[i].equals("X")) {
                    gameObjects.addWall(new Wall(x, y));
                } else if (objects[i].equals("*")) {
                    gameObjects.addBox(new Box(x, y));
                } else if (objects[i].equals(".")) {
                    gameObjects.addHome(new Home(x, y));
                } else if (objects[i].equals("&")) {
                    gameObjects.addHome(new Home(x, y));
                    gameObjects.addBox(new Box(x, y));
                } else if (objects[i].equals("@")) {
                    gameObjects.setPlayer(new Player(x, y));
                }
            }
        }
    }
}
