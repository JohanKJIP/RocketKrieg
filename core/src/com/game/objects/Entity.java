package com.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Johan on 02/05/2017.
 */
public interface Entity {
    /**
     * Render Entity
     * @param batch
     */
    void render(SpriteBatch batch);

    /**
     * Move entity based on velocity etc.
     * @param delta time since last frame.
     */
    void update(float delta);

    /**
     * Return position of entity.
     * @return position
     */
    Vector2 getPosition();

    /**
     * Return polygon of entity.
     * @return polygon pol
     */
    Polygon getHitBox();
}
