package ru.myitschool.turtle;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {
    public static final int WORLD_HEIGHT = 720;
    public static final int WORLD_WIDTH = 1280;


    @Override
    public void create() {
        setScreen(new FirstScreen());
    }
}
