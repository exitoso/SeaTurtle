package ru.myitschool.turtle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    private FitViewport viewport;
    private Stage stage;
    private static final int STARFISH_COUNT = 10;
    public static final int ROCK_COUNT = 5;
    private TurtleActor turtleActor;
    private OrthographicCamera cam;
    private Touchpad touchpad;


    @Override
    public void show() {
        viewport = new FitViewport(MainGame.WORLD_WIDTH, MainGame.WORLD_HEIGHT);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        Texture starfishTexture = new Texture("starfish.png");
        for (int i = 0; i < STARFISH_COUNT ; i++) {
            int startX = MathUtils.random(MainGame.WORLD_WIDTH-starfishTexture.getWidth());
            int startY = MathUtils.random(MainGame.WORLD_HEIGHT-starfishTexture.getHeight());
            StarFishActor starFishActor = new StarFishActor(starfishTexture, startX, startY);
            starFishActor.setPosition(startX, startY);
            stage.addActor(starFishActor);
        }

        Texture rockTexture = new Texture("rock.png");
        for (int i = 0; i < ROCK_COUNT ; i++) {
            int startX = MathUtils.random(MainGame.WORLD_WIDTH-rockTexture.getWidth());
            int startY = MathUtils.random(MainGame.WORLD_HEIGHT-rockTexture.getHeight());
            RockActor rockActor = new RockActor(rockTexture, startX, startY);
            rockActor.setPosition(startX, startY);
            stage.addActor(rockActor);
        }

        touchpad = createTouchpad();
        touchpad.setPosition(MainGame.WORLD_WIDTH - touchpad.getWidth() - 50, 50);

        Label scores = createScores();
        scores.setPosition(30, 580);
        stage.addActor(scores);

        Texture turtleTexture = new Texture("turtle-1.png");
        turtleActor = new TurtleActor(turtleTexture, touchpad, scores);
        turtleActor.setPosition(500, 300);
        stage.addActor(turtleActor);
        stage.addActor(touchpad);

    }

    private Label createScores() {
        FileHandle fontFile = Gdx.files.internal("font.ttf");
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 100;
        BitmapFont bitmapFont = fontGenerator.generateFont(params);

        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont, Color.RED);
        return new Label("0", labelStyle);
    }


    private Touchpad createTouchpad(){
        Texture backgroundTexture = new Texture("joystick-background.png");
        TextureRegionDrawable background = new TextureRegionDrawable(backgroundTexture);
        Texture knobTexture = new Texture("joystick-knob.png");
        TextureRegionDrawable knob = new TextureRegionDrawable(knobTexture);
        float deadZoneRadius = 0;
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle(background, knob);
        return new Touchpad(deadZoneRadius, touchpadStyle);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.CLEAR);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
/*
 Дженерик - механизм, позволяющий создавать контейнеры для любых типов данных. Тип данных контейнера указывается в <>. ArrayList<String> list = new ArrayList(10);
 В дженериках создают переменные в которых ранится тип переменных контейнера
Переменная указывается рядом с названием класса.
 public ArrayList <T>
 Список - расширяемый массив.
 ArrayList <String> list = new ArrayList<>();
list.add();
list.set();
list.get();
list.remove();
Collections.sort();
Collections.shuffle();
*/
