package com.ramtin;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by PC User on 2017-04-20.
 */
public class KeyInput extends KeyAdapter {

	/**
	 * FIELDS
	 **/
	private Handler handler;
	private boolean keyDown[] = new boolean[4];

	/**
	 * CONSTRUCTOR
	 **/
	public KeyInput(Handler handler) {
		this.handler = handler;

		keyDown[0] = false; // up
		keyDown[1] = false; // down
		keyDown[2] = false; // right
		keyDown[3] = false; // left
	}

	/**
	 * KEY PRESSED
	 **/
	@Override
	public void keyPressed(KeyEvent e) {
		// * extra info -> to make multiple keys do an action simultaneously
		// Eg. making multiple objects move at the same time
		// -> would require multithreading -> can't be done with a single thread

		int key = e.getKeyCode(); // sets variable "key" to a letter binding
									// (ASCII numbers)

		////////////////////////////////////////////////
		/** PLAYER **/
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				// key events only apply to player
				// keys pressed will only affect player and not other objects

				if (key == KeyEvent.VK_UP) {
					tempObject.setVelY(-5); // y value decreases (means player
											// moves up -> inverted axis)
					keyDown[0] = true;
				}

				if (key == KeyEvent.VK_DOWN) {
					tempObject.setVelY(5);// y value decreases (means player
											// moves up -> inverted axis)
					keyDown[1] = true;
				}
				if (key == KeyEvent.VK_LEFT) {
					tempObject.setVelX(-5);
					keyDown[2] = true;
				}
				if (key == KeyEvent.VK_RIGHT) {
					tempObject.setVelX(5);
					keyDown[3] = true;
				}
			}

			// quick exit
			if (key == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}

			// pause when in game
			if (key == KeyEvent.VK_P && Game.gameState == Game.STATE.GAME) {

				// else if not already paused
				if (Game.paused == false) {
					Game.paused = true;
				}

				// if already paused
				else if (Game.paused == true) {
					Game.paused = false;
				}
			}

		}
		////////////////////////////////////////////////
	}

	/**
	 * KEY RELEASED
	 **/
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		////////////////////////////////////////////////
		/** PLAYER **/
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				// key events only apply to player
				// keys pressed will only affect player and not other objects

				if (key == KeyEvent.VK_UP) {
					keyDown[0] = false;
					// tempObject.setVelY(0); //y value decreases (means player
					// moves up -> inverted axis)
				}
				if (key == KeyEvent.VK_DOWN) {
					keyDown[1] = false;
					// tempObject.setVelY(0);//y value decreases (means player
					// moves up -> inverted axis)
				}
				if (key == KeyEvent.VK_LEFT) {
					keyDown[2] = false;
					// tempObject.setVelX(0);
				}
				if (key == KeyEvent.VK_RIGHT) {
					keyDown[3] = false;
					// tempObject.setVelX(0);
				}

				// FIX TO KEYBOARD INPUT GLITCH
				// SMOOTHER MOVEMENT

				// vertical movement
				if (!keyDown[0] && !keyDown[1]) {
					tempObject.setVelY(0);

					// horizontal movement
				}
				if (!keyDown[2] && !keyDown[3]) {
					tempObject.setVelX(0);
				}
			}
		}
		////////////////////////////////////////////////
	}
}
