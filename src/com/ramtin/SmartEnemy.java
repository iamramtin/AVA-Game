package com.ramtin;

import java.awt.*;

/**
 * Created by PC User on 2017-04-20.
 */
public class SmartEnemy extends GameObject {


    /**
     * FIELDS
     **/
    private final static int width = 14, height = 14;
    private static Color color = Color.ORANGE.darker();
    private Handler handler;
    private GameObject player;


    /**
     * CONSTRUCTOR
     **/
    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Player) {
                player = handler.object.get(i);
            }
        }
    }

    /**
     * GET BOUNDS
     **/
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * TICK
     **/
    @Override
    public void tick() {
        x += velX;
        y += velY;

        //////////////////////////////////////////////////////////////////////////////////////////
        /*ALGORITHM TO MAKE ENEMY MOVE TOWARDS PLAYER*/

        //ALTERNATIVE
        //**********************************************************************************************
        float diffX = x - player.getX();
        float diffY = y - player.getY();

        float distance = (float) Math.sqrt((diffX * diffX) + (diffY * diffY)); //distance formula

        diffX = x - player.getX();
        diffY = y - player.getY();

        velX = (int) ((-3 / distance) * diffX);
        velY = (int) ((-3 / distance) * diffY);
        //**********************************************************************************************

        //**************************
        //ALTERNATIVE
//        if(x < player.getX())
//            velX = 2;
//        if(x > player.getX())
//            velX = -2;
//        if(y < player.getY())
//            velY = 2;
//        if(y > player.getY())
//            velY = -2;
        //**************************

        //////////////////////////////////////////////////////////////////////////////////////////

        //when reaches top/bottom of screen -> changes direction
        if (y <= 0 || y >= Game.HEIGHT - height * 2.8) {
            velY *= -1;
        }

        //when reaches left/right of screen -> changes direction
        if (x <= 0 || x >= Game.WIDTH - width * 1.4) {
            velX *= -1;
        }

        //////////////////////////////////////////////////////////////////////////////////////////

        handler.addObject(new Trail(x, y, width - 1, height - 1, ID.Trail, handler, color, 0.02f));

    }


    /**
     * RENDER
     **/
    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
