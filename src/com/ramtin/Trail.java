package com.ramtin;

import java.awt.*;

/**
 * Created by PC User on 2017-04-21.
 */
public class Trail extends GameObject {


    /**
     * FIELDS
     **/
    private float alpha = 1;
    private float life; //life = 0.001 -> 0.1 (smaller number = longer the duration of trail particles existing)
    private Handler handler;
    private Color color;
    private int width, height;


    /**
     * CONSTRUCTOR
     **/
    public Trail(int x, int y, int width, int height, ID id, Handler handler, Color color, float life) {
        super(x, y, id);

        this.width = width;
        this.height = height;
        this.handler = handler;
        this.life = life;
        this.color = color;
    }


    /**
     * TICK
     **/
    @Override
    public void tick() {
        if (alpha > life) {
            alpha -= life - 0.01f;
        } else {
            handler.removeObject(this);
        }
    }


    /**
     * RENDER
     **/
    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillRect(x, y, width, height);

        g2d.setComposite(makeTransparent(1));
    }


    /**
     * GET BOUNDS
     **/
    @Override
    public Rectangle getBounds() {
        return null;
    }


    /**
     * MAKE TRANSPARENT
     **/
    private AlphaComposite makeTransparent(float alpha) {
        //RENDERS OUT TRANSPARENCY
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }
}
