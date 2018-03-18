package com.ramtin;

import java.awt.*;

/**
 * Created by PC User on 2017-04-20.
 */
public abstract class GameObject {

    /**
     * =========================================================
     * GAME OBJECT CLASS
     * Basically all objects will extend this class
     * Player, enemies, tokens, etc
     * =========================================================
     **/


    /**
     * FIELDS
     **/

    //protected -> can only be accessed by objects inheriting this class
    // -> will be initialised for the object inheriting

    protected int x, y;
    protected ID id;
    protected int velX, velY; //control speed in x direction and y direction


    /**
     * CONSTRUCTOR
     **/
    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }


    /**ABSTRACT METHODS**/
    //means it definitely needs to be used by the inheriting object
    ///////////////////////////////////////////////////////////


    /**
     * TICK
     **/
    public abstract void tick();
    //////////////////////////////////////////

    /**
     * RENDER
     **/
    public abstract void render(Graphics g);
    //////////////////////////////////////////

    /**
     * GET BOUNDS
     **/
    public abstract Rectangle getBounds();
    //////////////////////////////////////////


    /**
     * GETTERS
     **/
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }
  

    public ID getId() {
        return id;
    }

    //////////////////////

    /**
     * SETTERS
     **/
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
