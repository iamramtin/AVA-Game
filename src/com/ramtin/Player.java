package com.ramtin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Created by PC User on 2017-04-20.
 */
public class Player extends GameObject {

	/**
	 * FIELDS
	 **/
	private static int width = 32, height = 32;
	private static Color color = Color.WHITE;
	private Handler handler;
	private Game game;

	private BufferedImage player_image;

	/**
	 * CONSTRUCTOR
	 **/
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		// SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		//
		// player_image = ss.grabImage(1, 1, 32, 32);
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
		x = Game.clamp(x, (int) (Game.WIDTH - width * 1.35), 0);
		y = Game.clamp(y, (int) (Game.HEIGHT - height * 2.8), 0);

		x += velX;
		y += velY;

		collision();
	}

	/**
	 * COLLISION
	 **/
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.VerticalEnemy
					|| tempObject.getId() == ID.HorizontalEnemy || tempObject.getId() == ID.VerticalEnemyStraight
							|| tempObject.getId() == ID.HorizontalEnemyStraight || tempObject.getId() == ID.SmartEnemy
					|| tempObject.getId() == ID.EnemyBoss || tempObject.getId() == ID.HardEnemy) {
				// checking to see if tempObject is basic enemy and if it
				// collides with player
				if (this.getBounds().intersects(tempObject.getBounds())) {
					// collision code
					HUD.HEALTH --;
				}
			}
		}

	}

	/**
	 * RENDER
	 **/
	@Override
	public void render(Graphics g) {

		g.setColor(color);
		g.fillRect(x, y, width, height);

		// g.drawImage(player_image, x, y, this);
	}
}
