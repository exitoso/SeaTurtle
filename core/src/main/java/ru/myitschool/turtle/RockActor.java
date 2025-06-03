package ru.myitschool.turtle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RockActor extends Actor {
    private Texture texture;
    private Polygon hitbox;


    public RockActor(Texture texture, int startX, int startY){
       this.texture = texture;
       setPosition(startX, startY);
       setSize(texture.getWidth(), texture.getHeight());
        float[] vertices = {
            0, 0,
            0, texture.getHeight(),
            texture.getWidth(), texture.getHeight(),
            texture.getWidth(), 0
        };
        hitbox = new Polygon(vertices);
        hitbox.setPosition(startX, startY);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public Polygon getHitbox() {
        return hitbox;
    }
}
