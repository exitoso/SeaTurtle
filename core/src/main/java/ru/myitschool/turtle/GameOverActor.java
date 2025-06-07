package ru.myitschool.turtle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameOverActor extends Actor {

    private Texture texture;

    public GameOverActor() {
        texture = new Texture("game-over.png");
        setX((MainGame.WORLD_WIDTH / 2F) - texture.getWidth() / 2F);
        setY(MainGame.WORLD_HEIGHT / 2F - texture.getHeight() / 2F);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY());
    }
}
