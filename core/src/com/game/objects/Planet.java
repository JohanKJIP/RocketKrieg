package com.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.*;
import com.game.AssetStorage;
import com.game.GameEntry;

import java.util.Random;

/**
 *  Planet entity class
 *  @author David Johansson 
 *  @version 1.0 (2017-05-09)
 */
public class Planet extends GameEntity implements Entity{
    private Sprite planet;
    private Random rand;

    /**
     * Constructor for Planet entity.
     * @param x spawn coordinate
     * @param y spawn coordinate
     */
    public Planet(float x, float y, float sizeX, float sizeY){
        super();
        rand = new Random();
        if((sizeX != 0) && (sizeY != 0)) {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
        } else {
            int size = rand.nextInt(350) + 150;
            this.sizeX = size;
            this.sizeY = size;
        }

        //set properties
        int randomNumber = MathUtils.random(100);
        if(randomNumber<25) {
            planet = AssetStorage.planet;
        } else if(randomNumber<50) {
            planet = AssetStorage.planet1;
        } else if(randomNumber<75){
            planet = AssetStorage.planet2;
        } else {
            planet = AssetStorage.planet3;
        }
        position.set(x,y);
        angle = (float)Math.toRadians(rand.nextInt(361));

        //setup hitbox
        Rectangle bounds =  new Rectangle(position.x+2, position.y+2, this.sizeX-7, this.sizeY-7);
        hitbox = new Polygon(new float[]{
                bounds.width/2, 0,
                3 * (bounds.width/4), bounds.height/4,
                bounds.width, bounds.height/2,
                3 * (bounds.width/4), 3 * (bounds.height/4),
                bounds.width/2, bounds.height,
                bounds.width/4, 3 * (bounds.height/4),
                0, bounds.height/2,
                bounds.width/4, bounds.height/4
        });
        hitbox.setOrigin(bounds.width/2, bounds.height/2);
        ID = 3;
    }

    /**
     * Method to render asteroid.
     * @param batch SpriteBatch batch.
     */
    public void render(SpriteBatch batch){
        planet.setSize(sizeX,sizeY);
        super.render(batch, planet, Math.toDegrees(angle));
    }

    /**
     * Update asteroid position.
     */
    public void update(float delta){
        move(delta);
    }
}
