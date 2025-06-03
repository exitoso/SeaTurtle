package ru.myitschool.turtle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.Array;

public class TurtleActor extends Actor {

    private Polygon hitbox;
    private Animation<TextureRegion> animation;
    private TextureRegion textureRegion;
    private final Touchpad touchpad;
    public static final int SPEED = 5;
    private float stateTime = 0;
    private Label scores;


    public TurtleActor(Texture texture, Touchpad touchpad, Label scores){
        this.textureRegion = new TextureRegion(texture);
        this.touchpad = touchpad;
        this.scores = scores;

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setOriginX(getWidth()/2);
        setOriginY(getHeight()/2);
        setRotation(0);

        animation = createAnimation();
        float[] vertices = {
            0, texture.getHeight() / 2F,
            texture.getWidth() / 3F, texture.getHeight(),
            2*texture.getWidth() / 3F, texture.getHeight(),
            texture.getWidth(), texture.getHeight() / 2F,
            2*texture.getWidth() / 3F, 0,
            texture.getWidth() / 3F, 0
        };
        hitbox = new Polygon(vertices);
        hitbox.setOrigin(getOriginX(), getOriginY());

    }
    private void checkOverLap(){
        Stage stage = getStage();
        Array<Actor> actors = stage.getActors();
        for (int i = 0; i < actors.size; i++) {
            Actor actor = actors.get(i);
            if(actor instanceof StarFishActor){
                StarFishActor starFishActor = (StarFishActor)actor;
                boolean isOverlap = Intersector.overlapConvexPolygons(hitbox, starFishActor.getHitbox());
                if (isOverlap) {
                    starFishActor.remove();
                    // starFishActor.reSpawn();
                    String text = scores.getText().toString();
                    int scoresInt = Integer.parseInt(text);
                    int newScores = scoresInt + 1;
                    scores.setText(newScores);
                }

            }
            if(actor instanceof RockActor){
                RockActor rockActor = (RockActor) actor;
                Intersector.MinimumTranslationVector mtv = new Intersector.MinimumTranslationVector();
                boolean isOverlap = Intersector.overlapConvexPolygons(hitbox, rockActor.getHitbox(), mtv);
                if(isOverlap) {
                    float nextX = mtv.normal.x * mtv.depth;
                    float nextY = mtv.normal.y * mtv.depth;
                    moveBy(nextX, nextY);
                }
            }
        }
    }




    private Animation<TextureRegion> createAnimation(){
        float flameDuration = 1/6F;

        Array<TextureRegion> frames = new Array<>();
        frames.add(new TextureRegion(new Texture("turtle-1.png")));
        frames.add(new TextureRegion(new Texture("turtle-2.png")));
        frames.add(new TextureRegion(new Texture("turtle-3.png")));
        frames.add(new TextureRegion(new Texture("turtle-4.png")));
        frames.add(new TextureRegion(new Texture("turtle-5.png")));
        frames.add(new TextureRegion(new Texture("turtle-6.png")));


        return new Animation<>(flameDuration, frames, Animation.PlayMode.LOOP);
    }




    @Override
    public void act(float delta) {
        super.act(delta);
        float nextX = touchpad.getKnobPercentX() * SPEED;
        float nextY = touchpad.getKnobPercentY() * SPEED;
        moveBy(nextX, nextY);

        Vector2 vector2 = new Vector2(nextX, nextY);
        if(vector2.len() > 0){
            float angle = vector2.angleDeg();
            setRotation(angle);

        }
        stateTime += delta;

        hitbox.setPosition(getX(), getY());
        hitbox.setRotation(getRotation());

        checkOverLap();

        if (getY() < 0) {
            setY(0);
        }

        if (getX() < 0) {
            setX(0);
        }

        if(getY() + getHeight() > MainGame.WORLD_HEIGHT) {
            setY(MainGame.WORLD_HEIGHT - getHeight());
        }

        if (getX() + getWidth() > MainGame.WORLD_WIDTH) {
            setX(MainGame.WORLD_WIDTH - getWidth());
        }
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        textureRegion = animation.getKeyFrame(stateTime, true);
        hitbox.setPosition(getX(), getY());

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
    public Polygon getHitbox(){
        return hitbox;
    }

}

