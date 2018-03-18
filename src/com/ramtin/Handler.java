package com.ramtin;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by PC User on 2017-04-20.
 */
public class Handler {

	/**
	 * ========================================================= HANDLER CLASS
	 * Maintains, updates and renders all the objects in our game The objects
	 * need to be handled and processed correctly Loops through all objects and
	 * updates and renders them
	 * =========================================================
	 **/

	/**
	 * FIELDS
	 **/
	List<GameObject> object;

	/**
	 * CONSTRUCTOR
	 **/
	public Handler() {

		object = new LinkedList<>();
	}

	/**
	 * TICK
	 **/
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}

	/**
	 * RENDER
	 **/
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	/*
	 * /////////////////////////////////////////////////////////////////////////
	 * /////////////////////////////////// for (GameObject temp : object) {
	 * temp.tick(); }
	 * 
	 * 
	 * ITERABLE LOOP ABOVE CAUSES -> "Exception in thread "Thread-
	 * 2" java.util.ConcurrentModificationException" WHEN ADDING A TRAIL BE
	 * CAREFUL OF EXCEPTION USE NORMAL FOR LOOP
	 * 
	 */////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * ADD OBJECT
	 **/
	public void addObject(GameObject object) {
		this.object.add(object);
	}

	/**
	 * CLEAR OBJECT
	 **/
	public void clearObject() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			if (Game.gameState == Game.STATE.END || Game.gameState == Game.STATE.WIN) {
				removeObject(tempObject);
				i--;
			}

			else if (Game.gameState == Game.STATE.GAME && tempObject.getId() != ID.Player) {
				removeObject(tempObject);
				i--;
			}
		}
	}

	/**
	 * CLEAR PLAYER
	 **/
	public void clearPlayer() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			if (tempObject.getId() == ID.Player) {
				removeObject(tempObject);
				i--;
			}
		}
	}

	/**
	 * REMOVE OBJECT
	 **/
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

}
