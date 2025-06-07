package ru.myitschool.turtle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MenuScreen extends ScreenAdapter {
    private final Stage stage;
    private final MainGame game;
    private final FitViewport viewport;

    public MenuScreen(MainGame mainGame) {
        this.game = mainGame;
        viewport = new FitViewport(MainGame.WORLD_WIDTH / 1.5F, MainGame.WORLD_HEIGHT / 1.5F);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        ImageTextButton.ImageTextButtonStyle textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.up = new TextureRegionDrawable(new Texture("buttons/g9202.png"));
        textButtonStyle.font = MainGame.createFont(50);

        ImageTextButton playButton = new ImageTextButton("Play", textButtonStyle);
        ImageTextButton exitButton = new ImageTextButton("Exit", textButtonStyle);

        Table table = new Table();
        table.setFillParent(true);
        table.add(playButton).center();
        table.row().pad(40);
        table.add(exitButton).center();

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.openGameScreen();
                dispose();
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
