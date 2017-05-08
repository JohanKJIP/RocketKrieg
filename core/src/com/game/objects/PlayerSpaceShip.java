package com.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.AssetStorage;
import com.game.worldGeneration.ChunkManager;

import java.util.ArrayList;

/**
 * Created by Johan on 27/04/2017.
 */
public class PlayerSpaceShip extends GameEntity implements Entity{
    //Multipliers
    private final float SPEED_MULTIPLIER = 5000F;
    private final float MAX_ANGULARVELOCITY = 10F;
    private final float TURNING_SPEED = 3F;
    //Textures
    private Sprite sprite, offLeft, offRight, on, onLeft, onRight;
    //
    private float timeElapsed;

    /**
     * Initialise player ship.
     */
    public PlayerSpaceShip() {
        super();
        float sizeX = 25;
        float sizeY = 70;
        //load images
        sprite = new Sprite(new Texture(Gdx.files.internal("images/spaceship/Spaceship1.png")));
        sprite.setSize(sizeX,sizeY);

        //setup hitbox
        Rectangle bounds = new Rectangle(position.x+sizeX,position.y+sizeY,sizeX,sizeY);
        hitbox = new Polygon(new float[]{0,0,bounds.width,0,bounds.width,bounds.height,0,bounds.height});
        hitbox.setOrigin(bounds.width/2,bounds.height/2);
    }

    /**
     * Render the players ship.
     */
    public void render(SpriteBatch batch) {
        Sprite img = sprite;
        /* <-- Commented until all textures are created. -->
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            img = on;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            img = offLeft;
            if(Gdx.input.isKeyPressed(Input.Keys.W)) {
                img = onLeft;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            img = offRight;
            if(Gdx.input.isKeyPressed(Input.Keys.W)) {
                img = onRight;
            }
        }
        */
        sprite.setOriginCenter();
        sprite.setRotation((float)Math.toDegrees(angle)-90);
        sprite.setPosition(position.x,position.y);
        super.render(batch,img);
    }

    /**
     * Update ship values based on input.
     * @param delta
     */
    public void update(float delta) {
        timeElapsed += delta;
        move(delta);
        //flight controls
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            accel(delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            turnLeft(delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            turnRight(delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fireMissile();
        }
        //reset position to center of the screen.
        if(Gdx.input.isKeyPressed(Input.Keys.R)) {
            setPos(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        }
    }

    /**
     * Fire a missile.
     */
    public void fireMissile() {
        if(timeElapsed > 1.2) {
            Missile missile = new Missile(position,velocity,acceleration,angle);
            ChunkManager.addEntity(missile);
            timeElapsed = 0;
        }
    }

    /**
     * Turn right by changing the spaceship angular velocity.
     */
    public void turnRight(float delta) {
        if(angularVelocity<MAX_ANGULARVELOCITY) {
            angularVelocity += TURNING_SPEED*delta;
        }
    }

    /**
     * Turn left by changing the spaceship angular velocity.
     */
    public void turnLeft(float delta) {
        if(angularVelocity>-MAX_ANGULARVELOCITY) {
            angularVelocity -= TURNING_SPEED*delta;
        }
    }

    /**
     * Accelerate forward.
     */
    public void accel(float delta) {
        acceleration.set((float)Math.cos(angle)*SPEED_MULTIPLIER*delta,(float)Math.sin(angle)*SPEED_MULTIPLIER*delta);
    }
}
