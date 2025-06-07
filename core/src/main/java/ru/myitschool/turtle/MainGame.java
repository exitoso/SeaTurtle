package ru.myitschool.turtle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {
    public static final int WORLD_HEIGHT = 720;
    public static final int WORLD_WIDTH = 1280;


    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }

    public void openGameScreen(){
        setScreen(new GameScreen(this));
    }

    public void openMenuScreen(){
        setScreen(new MenuScreen(this));
    }

    public static BitmapFont createFont(int fontSize) {
        FileHandle fontFile = Gdx.files.internal("font.ttf");
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = fontSize;
        return fontGenerator.generateFont(params);
    }
}
