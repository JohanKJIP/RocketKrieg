package com.game.worldGeneration;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.game.AssetStorage;
import com.game.RocketKrieg;
import com.game.objects.*;
import com.game.objects.alien.AlienShip;
import com.game.objects.alien.AlienShipSpecial;

import java.util.ArrayList;
import java.util.Random;

/**
 * Tile creation class.
 * @author Johan von Hacht & David Johansson 
 * @version 1.2 (2017-05-17)
 */
public class Tile {
    private Sprite img;
    public static final int TILE_SIZE = 512;
    private Vector2 position;
    private Random rand = new Random();
    private ArrayList<Entity> tileEntities;

    /**
     * Constructor for Tile.
     * @param x tile spawn coordinate
     * @param y tile spawn coordinate
     */
    public Tile(int x, int y, float noise) {
        //generate random percent
        tileEntities = new ArrayList<Entity>();
        int percent = rand.nextInt(100);

        //choose random entity
        if(noise <= 0.4){
            for (int i = 0; (i<(RocketKrieg.getScore()/10) && i<3) || i<1; i++) {
                tileEntities.add(new Asteroid(x + rand.nextInt(TILE_SIZE), y + rand.nextInt(TILE_SIZE)));
            }
        }
        else if(noise <= 0.5){
            for (int i = 0; (i<(RocketKrieg.getScore()/15) && i<30) || i<1; i++) {
                tileEntities.add(new AlienShip(x + (TILE_SIZE/(1+RocketKrieg.getScore()/15))*i, y + (TILE_SIZE/(1+RocketKrieg.getScore()/15))*i));
            }
        }
        else if(noise <= 0.6){
            for (int i = 0; (i<(RocketKrieg.getScore()/15) && i<30) || i<1; i++) {
                tileEntities.add(new AlienShipSpecial(x + (TILE_SIZE/(1+RocketKrieg.getScore()/15))*i, y + (TILE_SIZE/(1+RocketKrieg.getScore()/15))*i));
            }
        }
        else if(noise <= 0.7){
            tileEntities.add(new ScorePoint(x + rand.nextInt(TILE_SIZE), y + rand.nextInt(TILE_SIZE)));
            tileEntities.add(new ScorePoint(x + rand.nextInt(TILE_SIZE), y + rand.nextInt(TILE_SIZE)));
        }
        else{
            tileEntities.add(new Planet(x, y));
        }

        int percent2 = rand.nextInt(100);

        //choose random tile texture.
        if(percent2 <= 97) {
            img = AssetStorage.tile1;
        }
        else if(percent2 <= 98){
            img = AssetStorage.tile2;
        }
        else if(percent2 <= 99){
            img = AssetStorage.tile4;
        }
        else {
            img = AssetStorage.tile3;
        }

        //initialize starting position
        position = new Vector2(x,y);
    }
     /**
     * Get Sprite of tile.
     * @return Sprite img.
     */
    public Sprite getImg() {
        return img;
    }

    /**
     * Get x position of tile.
     * @return x coordinate
     */
    public float getX() {return position.x;}

    /**
     * Get y position of tile.
     * @return y coordinate
     */
    public float getY() {return position.y;}

    /**
     * Get tile entity.
     * @return entity
     */
    public ArrayList<Entity> getEntity() {return tileEntities;}
}