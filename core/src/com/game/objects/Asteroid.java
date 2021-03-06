package com.game.objects;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.game.AssetStorage;

/**
 *  Asteroid entity class
 *  @author David Johansson 
 *  @version 1.0 (2017-05-02)
 */
public class Asteroid extends GameEntity implements Entity{
    private Sprite asteroid;
    private float sizeX;
    private float sizeY;

    /**
     * Constructor for Asteroid entity.
     * @param x spawn coordinate
     * @param y spawn coordinate
     */
    public Asteroid(float x, float y){
        super();
        sizeX = MathUtils.random(15, 50);
        sizeY = MathUtils.random(15, 50);

        //set properties
        asteroid = AssetStorage.asteroid;
        position.set(x,y);
        velocity.set(MathUtils.random(-50,50),MathUtils.random(-50,50));
        angularVelocity = MathUtils.random(-50, 50);

        //setup hitbox
        Rectangle bounds =  new Rectangle(position.x,position.y,sizeX-10,sizeY-10);
        hitbox = new Polygon(new float[]{0,0,bounds.width,0,bounds.width,bounds.height,0,bounds.height});
        hitbox.setOrigin(bounds.width/2, bounds.height/2);
        ID = 22;
    }

    /**
     * Method to render asteroid.
     * @param batch SpriteBatch batch.
     */
    public void render(SpriteBatch batch) {
        asteroid.setSize(sizeX,sizeY);
        super.render(batch, asteroid, Math.toDegrees(angle));
    }

    /**
     * Update asteroid position.
     */
    public void update(float delta){
        move(delta);
    }
}
