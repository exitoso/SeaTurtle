package ru.myitschool.turtle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class StarFishActor extends Actor {

    private TextureRegion textureRegion;
    private Polygon hitbox;

    public StarFishActor(Texture texture, int startX, int startY){
        textureRegion = new TextureRegion(texture);
        setSize(texture.getWidth(), texture.getHeight());
        setPosition(startX, startY);
        setOrigin(getWidth()/2, getHeight()/2);
        float[] vertices = {
            0, 0,
            0, texture.getHeight(),
            texture.getWidth(), texture.getHeight(),
            texture.getWidth(), 0
        };
        hitbox = new Polygon(vertices);
        hitbox.setPosition(startX, startY);


        ScaleToAction scaleUpAction = Actions.scaleTo(1.2F, 1.2F, 1);
        ScaleToAction scaleDownAction = Actions.scaleTo(0.8F, 0.8F, 1);
        SequenceAction sequenceAction = Actions.sequence(scaleUpAction, scaleDownAction);
        RepeatAction scaleForeverAction = Actions.forever(sequenceAction);
        addAction(scaleForeverAction);

        int direction = MathUtils.randomSign();
        RotateByAction rotateByAction = Actions.rotateBy(direction*360, 3);
        RepeatAction foreverRotateAction = Actions.forever(rotateByAction);
        addAction(foreverRotateAction);

    }

    public void reSpawn() {
        float startX = MathUtils.random(MainGame.WORLD_WIDTH-getWidth());
        float startY = MathUtils.random(MainGame.WORLD_HEIGHT-getHeight());

        setPosition(startX, startY);
        hitbox.setPosition(startX, startY);
    }

    public Polygon getHitbox(){
        return hitbox;
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
}
