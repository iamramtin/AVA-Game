package com.ramtin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

import com.ramtin.Game.STATE;

/**
 * Created by PC User on 2017-04-22.
 */
public class Menu extends MouseAdapter {

	/** FIELDS **/
	private Game game;
	private Handler handler;
	private HUD hud;
	private Music music;
	private Sound sound;

	private Random random = new Random();

	/** CONSTRUCTOR **/
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;

		// sounds/music
		Audio.load();
		music = Audio.getMusic("music");
		sound = Audio.getSound("menu_sound");

	}

	/**
	 * RENDER
	 **/
	public void render(Graphics g) {

		// ==== necessities ====
		Graphics2D g2d = (Graphics2D) g;
		Stroke stroke;

		FontMetrics metrics; // for text width (in pixels)
		// ====================

		Font fontTitle = new Font("Century Gothic", Font.PLAIN, 72);
		Font fontSmallTitle = new Font("Century Gothic", Font.PLAIN, 50);

		// ==== main title text ====
		String title = "-  A  V  A  -";

		metrics = g.getFontMetrics(fontTitle);
		int stringWidthTitle = metrics.stringWidth(title);
		// ====================

		// ==== end title text ====
		String titleEnd = "- G A M E  O V E R -";

		metrics = g.getFontMetrics(fontSmallTitle);
		int stringWidthEndTitle = metrics.stringWidth(titleEnd);
		// ====================

		// ==== game win text ====
		metrics = g.getFontMetrics(fontSmallTitle);

		String titleWin1 = "- YOU SURVIVED -";
		int waveWidthWin1 = metrics.stringWidth(titleWin1);
		int stringWidthWin1 = metrics.stringWidth(titleWin1);

		String titleWin2 = "G A M E  C O M P L E T E";
		int waveWidthWin2 = metrics.stringWidth(titleWin2);
		int stringWidthWin2 = metrics.stringWidth(titleWin2);
		// ====================

		// ==== help text ====
		String helpTitle = "H E L P";

		metrics = g.getFontMetrics(fontTitle);
		int stringWidthHelpTitle = metrics.stringWidth(helpTitle);
		// ====================

		// ==== difficulty title ====
		String difficultyTitle = "D I F F I C U L T Y";

		metrics = g.getFontMetrics(fontSmallTitle);
		int stringWidthDifficultyTitle = metrics.stringWidth(difficultyTitle);
		// ====================

		// ==== button text ====
		Font fontButton = new Font("Century Gothic", Font.PLAIN, 40);

		String play = "Play";
		String help = "Help";
		String quit = "Quit";
		String back = "Back";
		String normal = "Normal";
		String hard = "Hard";
		String playAgain = "Play Again";

		metrics = g.getFontMetrics(fontButton);
		int stringWidthPlay = metrics.stringWidth(play);
		int stringWidthHelp = metrics.stringWidth(help);
		int stringWidthQuit = metrics.stringWidth(quit);
		int stringWidthBack = metrics.stringWidth(back);
		int stringWidthNormal = metrics.stringWidth(normal);
		int stringWidthHard = metrics.stringWidth(hard);
		int stringWidthPlayAgain = metrics.stringWidth(playAgain);
		// ====================

		/*
		 * =====================================================================
		 * =====================================================================
		 */

		/** MENU **/
		if (Game.gameState == STATE.MENU) {

			// title
			int titleX = Game.WIDTH / 2 - stringWidthTitle / 2;

			g.setFont(fontTitle);

			{
				/**
				 * Algorithm loop to make title look cool Draws multiple strings
				 * depending on maxDrawings Can be tinkered -> (i-x)
				 **/

				int maxDrawings = 20;

				for (int i = maxDrawings; i > 1; i -= 1) {

					g.setColor(Color.WHITE);
					g.drawString(title, titleX - (i - 5), 100);
					g.drawString(title, titleX + (i - 5), 100);

					g.setColor(Game.color);
					g.drawString(title, titleX - (i - 14), 100);
					g.drawString(title, titleX + (i - 14), 100);
				}

				// draws final String to make it look neat
				g.setColor(Color.WHITE);
				g.drawString(title, titleX, 100);

			}

			// border
			{
				g.setColor(Color.WHITE);
				stroke = new BasicStroke(20);
				g2d.setStroke(stroke);
				g.drawRect(10, 10, Game.WIDTH - 26, Game.HEIGHT - 49);

				g.setColor(Game.color);
				stroke = new BasicStroke(10);
				g2d.setStroke(stroke);
				g.drawRect(10, 10, Game.WIDTH - 26, Game.HEIGHT - 49);
			}

			// buttons
			{
				g.setColor(Color.WHITE);
				stroke = new BasicStroke(3);
				g2d.setStroke(stroke);
				g.setFont(fontButton);

				g.setColor(Game.color);
				g.fillRect(Game.WIDTH / 2 - 200 / 2, 150, 200, 64);
				g.setColor(Color.WHITE);
				g.drawRect(Game.WIDTH / 2 - 200 / 2, 150, 200, 64);
				g.drawString(play, Game.WIDTH / 2 - stringWidthPlay / 2, 198);

				g.setColor(Game.color);
				g.fillRect(Game.WIDTH / 2 - 200 / 2, 250, 200, 64);
				g.setColor(Color.WHITE);
				g.drawRect(Game.WIDTH / 2 - 200 / 2, 250, 200, 64);
				g.drawString(help, Game.WIDTH / 2 - stringWidthHelp / 2, 298);

				g.setColor(Game.color);
				g.fillRect(Game.WIDTH / 2 - 200 / 2, 350, 200, 64);
				g.setColor(Color.WHITE);
				g.drawRect(Game.WIDTH / 2 - 200 / 2, 350, 200, 64);
				g.drawString(quit, Game.WIDTH / 2 - stringWidthQuit / 2, 398);

				// music options
				g.setFont(new Font("Century Gothic", Font.PLAIN, 24));

				// g.drawRect(22, Game.HEIGHT - 80, 20, 30);
				g.drawString("1", 25, Game.HEIGHT - 55);

				// g.drawRect(47, Game.HEIGHT - 80, 20, 30);
				g.drawString("2", 50, Game.HEIGHT - 55);

				// g.drawRect(72, Game.HEIGHT - 80, 20, 30);
				g.drawString("3", 75, Game.HEIGHT - 55);

				// g.drawRect(97, Game.HEIGHT - 80, 20, 30);
				g.drawString("4", 100, Game.HEIGHT - 55);

				// g.drawRect(122, Game.HEIGHT - 80, 20, 30);
				g.drawString("5", 125, Game.HEIGHT - 55);
			}
		}

		/** HELP **/
		else if (Game.gameState == STATE.HELP)

		{

			// title
			int titleX = Game.WIDTH / 2 - stringWidthHelpTitle / 2;
			{
				g.setFont(fontTitle);
				// draws final String to make it look neat
				g.setColor(Color.WHITE);
				g.drawString(helpTitle, titleX, 100);

			}

			// border
			{
				g.setColor(Color.CYAN);
				stroke = new BasicStroke(20);
				g2d.setStroke(stroke);
				g.drawRect(10, 10, Game.WIDTH - 26, Game.HEIGHT - 49);

				g.setColor(Game.color);
				stroke = new BasicStroke(10);
				g2d.setStroke(stroke);
				g.drawRect(10, 10, Game.WIDTH - 26, Game.HEIGHT - 49);
			}

			// information
			{
				g.setColor(Color.WHITE);
				Font fontInfo = new Font("Century Gothic", Font.PLAIN, 28);
				g.setFont(fontInfo);

				String s1 = "- Use arrow keys to move the player";
				String s2 = "- Dodge the enemies";
				String s3 = "- Survive as long as you can";

				metrics = g.getFontMetrics(fontInfo);
				int widthS1 = metrics.stringWidth(s1);
				int widthS2 = metrics.stringWidth(s2);
				int widthS3 = metrics.stringWidth(s3);

				g.drawString(s1, Game.WIDTH / 2 - widthS1 / 2, 200);
				g.drawString(s2, Game.WIDTH / 2 - widthS2 / 2, 240);
				g.drawString(s3, Game.WIDTH / 2 - widthS3 / 2, 280);

			}

			// back button
			{
				stroke = new BasicStroke(3);
				g2d.setStroke(stroke);

				g.setFont(fontButton);
				g.setColor(Game.color);
				g.fillRect(Game.WIDTH / 2 - 200 / 2, 350, 200, 64);
				g.setColor(Color.WHITE);
				g.drawRect(Game.WIDTH / 2 - 200 / 2, 350, 200, 64);
				g.drawString(back, Game.WIDTH / 2 - stringWidthBack / 2, 398);

			}
		}

		/** SELECT **/
		else if (Game.gameState == STATE.SELECT)

		{

			// title
			int titleX = Game.WIDTH / 2 - stringWidthDifficultyTitle / 2;
			{

				g.setFont(fontSmallTitle);
				// draws final String to make it look neat
				g.setColor(Color.WHITE);
				g.drawString(difficultyTitle, titleX, 100);
			}

			// border
			{
				g.setColor(Color.MAGENTA.darker());
				stroke = new BasicStroke(20);
				g2d.setStroke(stroke);
				g.drawRect(10, 10, Game.WIDTH - 26, Game.HEIGHT - 49);

				g.setColor(Game.color);
				stroke = new BasicStroke(10);
				g2d.setStroke(stroke);
				g.drawRect(10, 10, Game.WIDTH - 26, Game.HEIGHT - 49);
			}

			// buttons
			{
				g.setColor(Color.WHITE);
				stroke = new BasicStroke(3);
				g2d.setStroke(stroke);
				g.setFont(fontButton);

				g.setColor(Game.color);
				g.fillRect(Game.WIDTH / 2 - 200 / 2, 150, 200, 64);
				g.setColor(Color.WHITE);
				g.drawRect(Game.WIDTH / 2 - 200 / 2, 150, 200, 64);
				g.drawString(normal, Game.WIDTH / 2 - stringWidthNormal / 2, 198);

				g.setColor(Game.color);
				g.fillRect(Game.WIDTH / 2 - 200 / 2, 250, 200, 64);
				g.setColor(Color.WHITE);
				g.drawRect(Game.WIDTH / 2 - 200 / 2, 250, 200, 64);
				g.drawString(hard, Game.WIDTH / 2 - stringWidthHard / 2, 298);

				g.setColor(Game.color);
				g.fillRect(Game.WIDTH / 2 - 200 / 2, 350, 200, 64);
				g.setColor(Color.WHITE);
				g.drawRect(Game.WIDTH / 2 - 200 / 2, 350, 200, 64);
				g.drawString(back, Game.WIDTH / 2 - stringWidthBack / 2, 398);
			}
		}

		/** END **/
		else if (Game.gameState == STATE.END) {

			// title
			int titleX = Game.WIDTH / 2 - stringWidthEndTitle / 2;
			g.setFont(fontSmallTitle);
			g.setColor(Color.WHITE);
			g.drawString(titleEnd, titleX, 100);

			// border
			{
				g.setColor(Color.RED.darker());
				stroke = new BasicStroke(20);
				g2d.setStroke(stroke);
				g.drawRect(10, 10, Game.WIDTH - 26, Game.HEIGHT - 49);

				g.setColor(Game.color);
				stroke = new BasicStroke(10);
				g2d.setStroke(stroke);
				g.drawRect(10, 10, Game.WIDTH - 26, Game.HEIGHT - 49);
			}
			// information
			{
				g.setColor(Color.WHITE);
				Font fontInfo = new Font("Century Gothic", Font.PLAIN, 28);
				g.setFont(fontInfo);

				String wave = "HIGHEST WAVE: " + Spawn.wave;

				metrics = g.getFontMetrics(fontInfo);
				int widthWave = metrics.stringWidth(wave);

				g.drawString(wave, Game.WIDTH / 2 - widthWave / 2, 235);

			}

			// replay button
			g.setColor(Color.WHITE);
			stroke = new BasicStroke(3);
			g2d.setStroke(stroke);
			g.setFont(fontButton);

			g.setColor(Game.color);
			g.fillRect(Game.WIDTH / 2 - 220 / 2, 350, 220, 64);
			g.setColor(Color.WHITE);
			g.drawRect(Game.WIDTH / 2 - 220 / 2, 350, 220, 64);
			g.drawString(playAgain, Game.WIDTH / 2 - stringWidthPlayAgain / 2, 398);
		}

		/** GAME WIN **/
		else if (Game.gameState == STATE.WIN) {

			g.setFont(fontSmallTitle);
			int titleX1 = Game.WIDTH / 2 - waveWidthWin1 / 2;
			int titleX2 = Game.WIDTH / 2 - waveWidthWin2 / 2;

			// title
			g.setColor(Color.WHITE);
			g.drawString(titleWin1 + "", titleX1, 150);

			g.setColor(Color.WHITE);
			g.drawString(titleWin2 + "", titleX2, 225);

			// border
			{
				g.setColor(Color.ORANGE);
				stroke = new BasicStroke(20);
				g2d.setStroke(stroke);
				g.drawRect(10, 10, Game.WIDTH - 26, Game.HEIGHT - 49);

				g.setColor(Game.color);
				stroke = new BasicStroke(10);
				g2d.setStroke(stroke);
				g.drawRect(10, 10, Game.WIDTH - 26, Game.HEIGHT - 49);
			}

			// replay button
			g.setColor(Color.WHITE);
			stroke = new BasicStroke(3);
			g2d.setStroke(stroke);
			g.setFont(fontButton);

			g.setColor(Game.color);
			g.fillRect(Game.WIDTH / 2 - 220 / 2, 350, 220, 64);
			g.setColor(Color.WHITE);
			g.drawRect(Game.WIDTH / 2 - 220 / 2, 350, 220, 64);
			g.drawString(playAgain, Game.WIDTH / 2 - stringWidthPlayAgain / 2, 398);
		}

	}

	/**
	 * TICK
	 **/
	public void tick() {

	}

	/**
	 * MOUSE PRESSED
	 **/
	@Override
	public void mousePressed(MouseEvent e) {
		// STORES X AND Y POSITION OF MOUSE CLICK
		int mx = e.getX(); // mouse x
		int my = e.getY(); // mouse y

		if (game.gameState == STATE.MENU) {

			// PLAY BUTTON
			if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 150, 200, 64)) {
				// game.gameState = STATE.GAME;
				// handler.clearObject(); // clears all the menu particles
				// handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT
				// / 2 - 32, ID.Player, handler));
				// handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH -
				// 50), random.nextInt(Game.HEIGHT - 50),
				// ID.BasicEnemy, handler));

				game.gameState = STATE.SELECT;

				sound.play(1, 0.2f);

				return;
			}

			// HELP BUTTON
			if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 250, 200, 64)) {
				game.gameState = STATE.HELP;

				sound.play(1, 0.2f);
			}

			// QUIT BUTTON
			if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 350, 200, 64)) {
				sound.play(1, 0.2f);

				System.exit(1000000000);
			}

			// MUSIC OPTIONS
			if (mouseOver(mx, my, 22, Game.HEIGHT - 80, 20, 30)) {

				Music music = Audio.getMusic("music1");
				music.loop(1, 0.025f);
			}
			if (mouseOver(mx, my, 47, Game.HEIGHT - 80, 20, 30)) {

				Music music = Audio.getMusic("music2");
				music.loop(1, 0.025f);
			}
			if (mouseOver(mx, my, 72, Game.HEIGHT - 80, 20, 30)) {

				Music music = Audio.getMusic("music3");
				music.loop(1, 0.025f);
			}
			if (mouseOver(mx, my, 97, Game.HEIGHT - 80, 20, 30)) {

				Music music = Audio.getMusic("music4");
				music.loop(1, 0.025f);
			}

			if (mouseOver(mx, my, 122, Game.HEIGHT - 80, 20, 30)) {

				Music music = Audio.getMusic("music5");
				music.loop(1, 0.025f);
			}
		}

		else if (game.gameState == STATE.HELP) {
			// BACK BUTTON
			if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 350, 200, 64)) {

				sound.play(1, 0.2f);

				game.gameState = STATE.MENU;

				return;
			}
		}

		else if (game.gameState == STATE.SELECT) {

			// NORMAL BUTTON
			if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 150, 200, 64)) {
				game.difficulty = 0;
				game.gameState = STATE.GAME;

				handler.clearObject(); // clears all the menu particles
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));

				sound.play(1, 0.2f);
			}

			// HARD BUTTON
			if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 250, 200, 64)) {
				game.difficulty = 1;
				game.gameState = STATE.GAME;

				handler.clearObject(); // clears all the menu particles
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));

				sound.play(1, 0.2f);
			}

			// BACK BUTTON
			if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 350, 200, 64)) {

				sound.play(1, 0.2f);

				game.gameState = STATE.MENU;

				return;
			}

		}

		else if (game.gameState == STATE.END || game.gameState == STATE.WIN) {
			// PLAY AGAIN BUTTON
			if (mouseOver(mx, my, Game.WIDTH / 2 - 220 / 2, 350, 220, 64)) {

				hud.setLevel(1);
				hud.setScore(0);
				hud.HEALTH = 100;
				Spawn.setWave(1);
				Spawn.setWaveInc(-10);

				handler.clearObject(); // clears all the menu particles

				sound.play(1, 0.2f);

				game.gameState = STATE.SELECT;

				return;
			}
		}
	}

	/**
	 * MOUSE RELEASED
	 **/
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * MOUSE OVER CHECK
	 **/
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		// CHECKS TO SEE IF THE MOUSE CLICK IS LARGER THAN THE X/Y POSITION OF
		// THE BOX
		// AND SMALLER THAN THE X/Y POSITON OF THE BLOCK PLUS THE WIDTH/HEIGHT
		// OF THE BOX
		// THE BOX BEING THE BUTTON
		// AS LONG AS IT IS IN THE BOX, THEN THE CLICK WILL BE ACCEPTED FOR THE
		// SPECIFIC BUTTON

		if ((mx > x && mx < x + width) && (my > y && my < y + height)) {
			return true;
		}
		return false;
	}

}