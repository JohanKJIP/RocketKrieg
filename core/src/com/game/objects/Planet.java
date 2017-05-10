package com.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.*;
import com.game.AssetStorage;
import com.game.GameEntry;

/**
 *  Planet entity class
 *  @author David Johansson 
 *  @version 1.0 (2017-05-09)
 */
public class Planet extends GameEntity implements Entity{
    private Sprite planet;
    private Animation<TextureRegion> animation;
    private float timeElapsed;
    private float sizeX;
    private float sizeY;

    public Planet(float x, float y){
        super();
        sizeX = 300;
        sizeY = 300;
        timeElapsed = 0;

        //set properties
        planet = AssetStorage.planet;
        animation = AssetStorage.atmosphereAnimation;
        position.set(x + 250, y + 250);

        //setup hitbox
        Rectangle bounds =  new Rectangle(position.x, position.y, sizeX, sizeY);
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
    }

    /**
     * Method to render asteroid.
     * @param batch SpriteBatch batch.
     */
    public void render(SpriteBatch batch){
        planet.setSize(sizeX,sizeY);
        planet.setOriginCenter();
        planet.setPosition(position.x, position.y);

        super.render(batch, planet);
        GameEntry.batch.draw(animation.getKeyFrame(timeElapsed, true), position.x + sizeX/2 - 135, position.y + sizeY/2 - 135, sizeX - 29, sizeY - 29);
    }

    /**
     * Update asteroid position.
     * @param delta time since last frame.
     */
    public void update(float delta){
        timeElapsed += delta;
        move(delta);
    }
}