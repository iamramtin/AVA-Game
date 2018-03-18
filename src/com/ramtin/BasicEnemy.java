package com.ramtin;

import java.awt.*;

/**
 * Created by PC User on 2017-04-20.
 */
public class BasicEnemy extends GameObject {


    /**
     * FIELDS
     **/
    private  static int width = 16, height= 16;
    private static Color color = Color.RED;
    private Handler handler;


    /**
     * CONSTRUCTOR
     **/
    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y,  id);

        this.handler=handler;

        velX = 4;
        velY = 4;
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

        //when reaches top/bottom of screen -> changes direction
        if (y <= 0 || y >= Game.HEIGHT - height * 2.8) {
            velY *= -1;
        }

        //when reaches left/right of screen -> changes direction
        if (x <= 0 || x >= Game.WIDTH - height * 1.4) {
            velX *= -1;
        }
        
        handler.addObject(new Trail(x, y, width,height, ID.Trail, handler, color, 0.02f));

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
