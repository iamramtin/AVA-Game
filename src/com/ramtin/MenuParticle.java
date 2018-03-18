package com.ramtin;

import java.awt.*;
import java.util.Random;

/**
 * Created by PC User on 2017-04-20.
 */
public class MenuParticle extends GameObject {

	/**
	 * FIELDS
	 **/
	private static int width = 15, height = 15;
	private Handler handler;

	private Random random = new Random();
	private Color color;

	/**
	 * CONSTRUCTOR
	 **/
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		velX = 3;
		velY = 8;

		color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
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

		// when reaches top/bottom of screen -> changes direction
		if (y <= 25 || y >= Game.HEIGHT - 65) {
			velY *= -1;
		}

		// when reaches left/right of screen -> changes direction
		if (x <= 25 || x >= Game.WIDTH - 45) {
			velX *= -1;
		}

		handler.addObject(new Trail(x, y, width, height, ID.Trail, handler, color, 0.05f));

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
