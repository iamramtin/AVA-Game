package com.ramtin;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import org.newdawn.slick.Music;

/**
 * Created by PC User on 2017-04-20.
 */
public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * =========================================================
	 * 
	 * MAIN CLASS - Go to class - where everything will be handled
	 * 
	 * =========================================================
	 **/

	/**
	 * FIELDS
	 **/

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	// entire game will run in this single thread -> only because game is so
	// simple

	public static boolean running = false;

	public static Color color;

	public static boolean paused = false;
	public static int difficulty = 0;

	// DIFICULTY
	// 0 - normal
	// 1 - hard

	private Random random;
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;

	/**
	 * ENUMERATION
	 **/
	public enum STATE {
		MENU, HELP, GAME, SELECT, WIN, END
	};

	public static STATE gameState = STATE.MENU;

	public static BufferedImage sprite_sheet;

	/**
	 * CONSTRUCTOR
	 **/
	public Game() {

		handler = new Handler();
		// initialise handler before Window
		// otherwise -> NullPointerException
		// because when Window is initialised, it goes to the methods start() ->
		// run() -> render() ->handler()
		// but how can handler be called if it's not initialised yet?
		// as far as the game is concerned, handler does not exist until it is
		// initialised

		BufferedImageLoader loader = new BufferedImageLoader();
		sprite_sheet = loader.loadImage("");
		System.out.println("loaded");

		hud = new HUD();
		menu = new Menu(this, handler, hud);
		spawn = new Spawn(handler, hud);

		this.setFocusable(true);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);

		Audio.load();
		Music music = Audio.getMusic("music5");
		music.loop(1, 0.025f);

		new Window(WIDTH, HEIGHT, "Let's Build A Game!", this);
		// "this" refers to this game

		spawn = new Spawn(handler, hud);

		random = new Random();
		color = Color.GRAY.darker().darker().darker().darker();

		// menu particles
		if (gameState == STATE.MENU) {

			for (int i = 0; i < 5; i++) {
				handler.addObject(new MenuParticle(23, 70 + (i * 80), ID.MenuParticle, handler));
			}

			for (int i = 0; i < 5; i++) {
				handler.addObject(new MenuParticle(WIDTH - 46, 70 + (i * 80), ID.MenuParticle, handler));
			}
		}
	}

	/**
	 * START THREAD
	 **/
	public synchronized void start() {
		// STARTS UP THE THREAD
		thread = new Thread(this); // "this" refers to this instance of the game
									// class
		thread.start();
		running = true; // is the thread on or is it not on -> it is on
	}

	/**
	 * STOP THREAD
	 **/
	public synchronized void stop() {
		// STOPS THE THREAD
		try {
			thread.join();
			// thread.join() -> main will wait for this thread to complete
			// before carrying on with its own operation
			running = false;
		} catch (Exception e) {
			e.printStackTrace(); // tells us why error occurs if it does
		}
	}

	/**
	 * RUN/GAME LOOP
	 **/
	public void run() {
		// GAME LOOP
		// EVERY GAME NEEDS A GAME LOOP
		// HEARTBEAT OF THE GAME
		// UPDATES THE GAME

		////////////////////////////////////////////////////////////////////////

		/*
		 * Basically, the game loop runs (renders, updates, etc.) the game but
		 * it also sets a constant, controlled speed at which the game operates.
		 * Because different computers execute the code at diff. speeds
		 * 
		 * This code is implemented to control how fast the code is executed,
		 * and thus, how fast the game updates. You can do this by using the
		 * real world time (using methods that get time in nanoseconds). You
		 * limit the game to only updating the frame 60 times per second.
		 * 
		 * Using the if statement in the while (running) loop, you make the game
		 * update only when the program goes through the loop and the time since
		 * the last time the if statement executed is at least 1/60 of a second.
		 */

		////////////////////////////////////////////////////////////////////////

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				tick();
				delta--;
			}

			if (running) {
				render();
			}

			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	/**
	 * TICK
	 **/
	private void tick() {

		if (gameState == STATE.GAME && !paused) {
			// WON'T HAPPEN IN MENU, ONLY WHEN IN THE "GAME" STATE
			handler.tick();
			hud.tick();
			spawn.tick();

			// end menu particles
			if (HUD.HEALTH <= 0) {
				gameState = STATE.END; // result in end game screen
				handler.clearObject(); // clear all objects

				for (int i = 0; i < 15; i++) {
					handler.addObject(new MenuParticle(random.nextInt(WIDTH - 80), random.nextInt(HEIGHT - 100),
							ID.MenuParticle, handler));
				}
			}
		}

		else if (gameState == STATE.MENU || gameState == STATE.SELECT || gameState == STATE.HELP
				|| gameState == STATE.END || gameState == STATE.WIN) {
			handler.tick();
			menu.tick();
		}

	}

	/**
	 * RENDER
	 **/
	private void render() {
		/*
		 * RENDER TAKES ALL GRAPHICS (SPRITES, BACKGROUND, COLOURS, PIXELS) AND
		 * DISPLAYS THEM SO THE USER CAN SEE THEM
		 * 
		 * BUFFER STRATEGY -> HAS NEXT GRAPHIC READY TO DISPLAY COMMON TO USE
		 * TRIPLE BUFFERING (3 BUFFERS) -> VERY SMOOTH (NOT AS SMOOTH AS 4) BUT
		 * GOOD PERFORMANCE (SOME COMPUTERS CAN'T HANDLE 4)
		 */

		BufferStrategy bs = this.getBufferStrategy();

		////////////////////////////////////////////////////////////////////////

		// at first there is no buffer strategy -> so need to check if bs is
		// null
		if (bs == null) {
			// create buffer strategy and pass the number of buffers as a
			// parameter
			this.createBufferStrategy(3);
			// TRIPLE BUFFER STRATEGY
			// 3 FRAMES LOADED IN MEMORY
			// AS FIRST GRAPHIC IS FINISHED RENDERING
			// NEXT ONE IS ALREADY LOADED -> BECAUSE IT ALWAYS HAS 3 FRAMES
			// READY TO BE RENDERED
			// -> FASTER RENDER PROCESS -> SMOOTHER GAMEPLAY

			return; // want to go out and set bs again
		}

		////////////////////////////////////////////////////////////////////////

		Graphics g = bs.getDrawGraphics(); // buffer strategy is the object that
											// applies graphics context

		// black canvas
		g.setColor(color);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		if (gameState == STATE.GAME) {
			hud.render(g);
			spawn.render(g);
		}

		// paused
		if ((gameState == STATE.GAME) && paused) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Century Gothic", Font.PLAIN, 64));
			g.drawString("PAUSED", 205, 235);
		}

		// menu state
		else if (gameState == STATE.MENU || gameState == STATE.HELP || gameState == STATE.SELECT
				|| gameState == STATE.WIN) {

			if (handler.object.size() <= 10) {
				for (int i = 0; i < 5; i++) {
					handler.addObject(new MenuParticle(23, 70 + (i * 80), ID.MenuParticle, handler));
					handler.addObject(new MenuParticle(Game.WIDTH - 46, 70 + (i * 80), ID.MenuParticle, handler));
				}
			}

			menu.render(g);
		}

		// game over
		else if (gameState == STATE.END || gameState == STATE.WIN) {
			handler.clearObject();
			menu.render(g);
		}

		// game won
		else if (gameState == STATE.WIN) {
			handler.clearObject();
			menu.render(g);
		}

		g.dispose();
		// disposes of graphics context -> ready to create a new one

		bs.show();
		// show everything sent to buffer strategy
	}

	/**
	 * CLAMP METHOD
	 **/
	public static int clamp(int var, int max, int min) {
		/*
		 * CLAMP METHOD
		 * 
		 * KEEPS OBJECT WITHIN A SPECIFIC AREA BY MAKING ITS X/Y IS EQUAL TO THE
		 * MAXIMUM X/Y VALUE IT CAN TOUCH RETURN THE MAX VALUE (VICE VERSA FOR
		 * MIN)
		 * 
		 * IF THE VALUE PASSED IS GREATER/LESS THAN THE AREA WIDTH/HEIGHT MAKE
		 * THE VALUE = TO THE AREA WIDTH/HEIGHT THIS WILL MAKE IT SO THAT IT CAN
		 * NEVER EXCEED THE AREA -> CAN NEVER GO PASS AREA WIDTH/HEIGHT
		 */

		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}

	/**
	 * MAIN
	 **/
	public static void main(String[] args) {
		new Game();
	}
}