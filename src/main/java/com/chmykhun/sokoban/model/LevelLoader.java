package com.chmykhun.sokoban.model;

import java.nio.file.Path;
import java.util.Collections;

public class LevelLoader {

    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    // TODO: test implementation, should be reworked
    public GameObjects getLevel(int level) {
        return new GameObjects(Collections.singleton(new Wall(60, 60)),
                Collections.singleton(new Box(40, 40)),
                Collections.singleton(new Home(100, 60)),
                new Player(20, 40));
    }
}
