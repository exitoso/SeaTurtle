package ru.myitschool.turtle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EnemyActor extends Actor {

    private Polygon hitbox;
    private TextureRegion textureRegion;
    private TurtleActor turtleActor;
    private static final int SPEED = 2;

    public EnemyActor(Texture texture, TurtleActor turtleActor) {
        this.textureRegion = new TextureRegion(texture);
        this.turtleActor = turtleActor;

        setWidth(192);
        setHeight(108);
        setOriginX(getWidth() / 2);
        setOriginY(getHeight() / 2);
        setRotation(0);

        float[] vertices = {
            0, getHeight() / 2F,
            getWidth() / 3F, getHeight(),
            2 * getWidth() / 3F, getHeight(),
            getWidth(), getHeight() / 2F,
            2 * getWidth() / 3F, 0,
            getWidth() / 3F, 0
        };
        hitbox = new Polygon(vertices);
        hitbox.setOrigin(getOriginX(), getOriginY());

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Vector2 turtleVector = new Vector2(turtleActor.getX() + getOriginX(), turtleActor.getY() + getOriginY());
        Vector2 enemyVector = new Vector2(getX() + getOriginX(), getY() + getOriginY());
        Vector2 resultVector = turtleVector.sub(enemyVector);

        if(resultVector.len() > 0){
            float angle = resultVector.angleDeg();
            setRotation(angle);

            Vector2 velocity = new Vector2(SPEED, 0);
            velocity.setAngleDeg(angle);
            moveBy(velocity.x, velocity.y);
        }

        hitbox.setPosition(getX(), getY());
        hitbox.setRotation(getRotation());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
            textureRegion,
            getX(),
            getY(),
            getOriginX(),
            getOriginY(),
            getWidth(),
            getHeight(),
            getScaleX(),
            getScaleY(),
            getRotation()
        );
    }

    public Polygon getHitbox() {
        return hitbox;
    }

}

