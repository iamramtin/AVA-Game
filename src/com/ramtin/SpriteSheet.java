package com.ramtin;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	/** FIELDS **/
	private BufferedImage sprite;

	/** CONSTRUCTOR **/
	public SpriteSheet(BufferedImage ss) {
		this.sprite = ss;
	}

	/** GRAB IMAGE **/
	public BufferedImage grabImage(int row, int col, int width, int height) {
		BufferedImage img = sprite.getSubimage((row * 32) - 32, (col * 32) - 32, width, height);
		return img;

	}

}
