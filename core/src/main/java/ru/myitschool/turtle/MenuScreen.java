package ru.myitschool.turtle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuScreen extends ScreenAdapter {
    private final Stage stage;
    private final MainGame game;

    public MenuScreen(MainGame mainGame) {
        this.game = mainGame;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        ImageTextButton.ImageTextButtonStyle textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.up = new TextureRegionDrawable(new Texture("buttons/g9202.png"));
        textButtonStyle.font = MainGame.createFont(50);

        ImageTextButton playButton = new ImageTextButton("Play", textButtonStyle);
        ImageTextButton exitButton = new ImageTextButton("Exit", textButtonStyle);

        playButton.setSize(200, 60);
        playButton.setPosition(
            Gdx.graphics.getWidth()/2f - 100,
            Gdx.graphics.getHeight()/2f + 40
        );

        exitButton.setSize(200, 60);
        exitButton.setPosition(
            Gdx.graphics.getWidth()/2f - 100,
            Gdx.graphics.getHeight()/2f - 40
        );

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(playButton);
        stage.addActor(exitButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }
}
