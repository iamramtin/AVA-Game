package com.ramtin;

import java.awt.*;

/**
 * Created by PC User on 2017-04-20.
 */
public class VerticalEnemyStraight extends GameObject {


    /**
     * FIELDS
     **/
    private static int width = 20, height = 20;
    private static Color color = Color.BLUE;
    private Handler handler;


    /**
     * CONSTRUCTOR
     **/
    public VerticalEnemyStraight(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 10;
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
        if (x <= 0 || x >= Game.WIDTH - width * 1.4) {
            velX *= -1;
        }

        handler.addObject(new Trail(x, y, width, height, ID.Trail, handler, color, 0.06f));

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
