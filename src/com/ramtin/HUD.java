package com.ramtin;

import java.awt.*;

/**
 * Created by PC User on 2017-04-20.
 */
public class HUD {

	/**
	 * FIELDS
	 **/
	public static int HEALTH = 100;
	private int greenValue = 255;

	private int score = 0;
	private int level = 1;

	/**
	 * TICK
	 **/
	public void tick() {

		// AS HEALTH DEPLETES, COLOR OF HEALTH BAR SLOWLY BECOMES RED FROM GREEN
		// USES CLAMP METHOD
		HEALTH = Game.clamp(HEALTH, 100, 0);
		greenValue = Game.clamp(greenValue, 0, 255);

		greenValue = HEALTH * 2;
		score++;
	}

	/**
	 * RENDER
	 **/
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// HEALTH BAR
		g.setColor(Color.BLACK);
		g.fillRect(15, 15, 200, 32);

		g.setColor(new Color(100, greenValue, 0));
		g.fillRect(15, 15, HEALTH * 2, 32);

		BasicStroke bs = new BasicStroke(2);
		g2d.setStroke(bs);
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, 200, 32);

		// SCORE
		g.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		g.drawString("'P' to PAUSE", 560, 440);
		g.drawString("Wave: " + Spawn.wave, 15, 64);
	}

	/**
	 * SETTERS
	 **/
	public void setScore(int score) {
		this.score = score;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * GETTERS
	 **/
	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}
}
