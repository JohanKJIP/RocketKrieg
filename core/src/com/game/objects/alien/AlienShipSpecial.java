package com.game.objects.alien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.AssetStorage;
import com.game.GameEntry;
import com.game.RocketKrieg;
import com.game.objects.Entity;
import com.game.objects.GameEntity;
import com.game.objects.ship.PlayerSpaceShip;

/**
 *  AlienShipSpecial entity class
 *  @author David Johansson 
 *  @version 1.0 (2017-05-09)
 */
public class AlienShipSpecial extends GameEntity implements Entity {
    private Sprite alienShipSpecial;
    private Animation<TextureRegion> animation;
    private float sizeX;
    private float sizeY;
    private PlayerSpaceShip ship;
    private final float movingSpeed = 40;
    private final float acceleration = 80f;

    /**
     * Constructor of AlienShipSpecial entity.
     * @param x spawn coordinate
     * @param y spawn coordinate
     */
    public AlienShipSpecial(float x, float y){
        super();
        sizeX = 40;
        sizeY = 55;

        //set properties
        alienShipSpecial = AssetStorage.alienShipSpecial;
        animation = AssetStorage.redLightAnimation;
        position.set(x+MathUtils.random(0,500),y+MathUtils.random(0,500));

        //setup hitbox
        Rectangle bounds =  new Rectangle(position.x, position.y, sizeX, sizeY);
        hitbox = new Polygon(new float[]{0, 0, bounds.width, 0, bounds.width, bounds.height, 0, bounds.height});
        hitbox.setOrigin(bounds.width/2, bounds.height/2);

        //get ship
        ship = RocketKrieg.getShip();
    }

    /**
     * Method to render asteroid.
     * @param batch SpriteBatch batch.
     */
    public void render(SpriteBatch batch){
        alienShipSpecial.setSize(sizeX,sizeY);
        alienShipSpecial.setRotation((float)Math.toDegrees(angle)-90);
        super.render(batch, alienShipSpecial);
        GameEntry.batch.draw(animation.getKeyFrame(timeElapsed, true), position.x + sizeX/2 - 10, position.y + sizeY/2 - 2, 20f, 20f);
    }

    /**
     * Update asteroid position.
     */
    public void update(){
        move();

        //get position of playersip
        Vector2 shipPosition = ship.position;
        float distance = shipPosition.dst(position);

        //move alien ship if near
        if(distance < 800) {

            if (shipPosition.x > position.x) {
                moveRight();
            }
            if (shipPosition.x < position.x) {
                moveLeft();
            }
            if (shipPosition.y > position.y) {
                moveUp();
            }
            if (shipPosition.y < position.y) {
                moveDown();
            }
        }
    }

    /**
     * Move right by changing the alien ship velocity.
     */
    public void moveRight() {
        if(velocity.x < movingSpeed) {
            velocity.x += acceleration * Gdx.graphics.getDeltaTime();
        }
    }

    /**
     * Move left by changing the alien ship velocity.
     */
    public void moveLeft() {
        if(velocity.x > -movingSpeed) {
            velocity.x -= acceleration * Gdx.graphics.getDeltaTime();
        }
    }

    /**
     * Move up by changing the alien ship velocity.
     */
    public void moveUp() {
        if(velocity.y < movingSpeed) {
            velocity.y += acceleration * Gdx.graphics.getDeltaTime();
        }
    }

    /**
     * Move down by changing the alien ship velocity.
     */
    public void moveDown() {
        if(velocity.y > -movingSpeed) {
            velocity.y -= acceleration * Gdx.graphics.getDeltaTime();
        }
    }
}
