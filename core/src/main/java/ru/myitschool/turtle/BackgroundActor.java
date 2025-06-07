package ru.myitschool.turtle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BackgroundActor extends Actor {

    private Texture texture;

    public BackgroundActor(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, 0, 0, MainGame.WORLD_WIDTH, MainGame.WORLD_HEIGHT);
    }
}
