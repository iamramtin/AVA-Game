package com.ramtin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import java.awt.FontMetrics;

/**
 * Created by PC User on 2017-04-21.
 */
public class Spawn {

	/**
	 * FIELDS
	 **/
	private Handler handler;
	private HUD hud;
	private Random random = new Random();

	public static int wave = 1; // indicates current wave
	public static int waveInc = -10; // increment between spawns
	public static int miniWaves = 1; // used to measure spawn time
	private int maxMiniWaves = 20; // number of miniwaves before each big wave

	long timeAtLastAccept = System.nanoTime(); // current time

	private int scoreKeep = 0;

	/**
	 * CONSTRUCTOR
	 **/
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}

	/**
	 * TICK
	 **/
	public void tick() {
		scoreKeep++;
		waveInc += 10;

		long startTime = System.nanoTime();

		if (scoreKeep >= 150) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			miniWaves++;

			if ((hud.getLevel() % maxMiniWaves == 0)) {
				// NEW WAVE EVERY "maxWave"/20 MINI WAVES

				handler.clearObject();
				System.out.println("Enemies Cleared");
				if (wave == 1 || hud.getLevel() % maxMiniWaves == 0) {
					wave++;
					miniWaves = 1;
					System.out.println("Wave: " + wave);
				}
			}

			// HARD =================================================
			if (Game.difficulty == 1) {

				// wave 1
				if ((wave == 1)) {

					long k = (System.nanoTime() - timeAtLastAccept) / 1000000000;

					if (miniWaves > 2 && miniWaves < 4) {
						handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
						handler.addObject(
								new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
					}
					if (k > 10 && miniWaves > 4 && miniWaves < 14) {
						handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
						handler.addObject(
								new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));

						timeAtLastAccept = System.nanoTime();
					}
				}

				// wave 2
				if ((wave == 2)) {
					System.out.println(timeAtLastAccept);
					long k = (System.nanoTime() - timeAtLastAccept) / 1000000000;

					if (miniWaves > 1 && miniWaves < 3) {
						handler.addObject(new VerticalEnemy((20), (20), ID.VerticalEnemy, handler));
					}
					if (k > 5 && miniWaves > 5 && miniWaves < 16) {
						handler.addObject(new VerticalEnemy((20), (20), ID.VerticalEnemy, handler));
						timeAtLastAccept = System.nanoTime();
						System.out.println(k);

					}
				}

				// wave 3
				if ((wave == 2)) {
					System.out.println(timeAtLastAccept);
					long k = (System.nanoTime() - timeAtLastAccept) / 1000000000;

					if (miniWaves > 1 && miniWaves < 3) {
						handler.addObject(new HorizontalEnemy((20), (20), ID.HorizontalEnemy, handler));
					}
					if (k > 6 && miniWaves > 8 && miniWaves < 18) {
						handler.addObject(new HorizontalEnemy((20), (20), ID.HorizontalEnemy, handler));
						timeAtLastAccept = System.nanoTime();
						System.out.println(k);

					}
				}

				// wave 4
				if ((wave == 4)) {
					System.out.println(timeAtLastAccept);
					long k = (System.nanoTime() - timeAtLastAccept) / 1000000000;

					if (k > 7 && miniWaves > 2 && miniWaves < 10) {
						// handler.addObject(new
						// HorizontalEnemy(random.nextInt(250), (250),
						// ID.HorizontalEnemy, handler));
						handler.addObject(new VerticalEnemy((250), (250), ID.VerticalEnemy, handler));

						timeAtLastAccept = System.nanoTime();
						System.out.println(k);
					}
					System.out.println(handler.object.size());
				}

				// wave 5
				if ((wave == 5)) {
					System.out.println(timeAtLastAccept);
					long k = (System.nanoTime() - timeAtLastAccept) / 1000000000;

					if (k > 4) {
						if (miniWaves > 2 && miniWaves < 6) {
							handler.addObject(
									new HorizontalEnemy(random.nextInt(250), (250), ID.HorizontalEnemy, handler));
							handler.addObject(new VerticalEnemy(random.nextInt(250), (250), ID.VerticalEnemy, handler));
						}

						else if (miniWaves > 8 && miniWaves < 12) {
							handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
							handler.addObject(
									new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
						}
						timeAtLastAccept = System.nanoTime();
						System.out.println(k);
					}
				}

				// wave 6
				if ((wave == 6)) {
					System.out.println(timeAtLastAccept);
					long k = (System.nanoTime() - timeAtLastAccept) / 1000000000;

					if (k > 4) {
						if (miniWaves > 2 && miniWaves < 6) {
							handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
							handler.addObject(
									new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
						}

						else if (miniWaves > 10 && miniWaves < 14) {
							handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
							handler.addObject(
									new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
						} else if (miniWaves > 14 && miniWaves < 20) {
							handler.addObject(new SmartEnemy((45), (Game.HEIGHT - 45), ID.SmartEnemy, handler));
							handler.addObject(new SmartEnemy((45), (Game.HEIGHT - 45), ID.SmartEnemy, handler));
							handler.addObject(new SmartEnemy((Game.WIDTH - 45), (45), ID.SmartEnemy, handler));
							handler.addObject(new SmartEnemy((Game.WIDTH - 45), (45), ID.SmartEnemy, handler));

						}

						timeAtLastAccept = System.nanoTime();
						System.out.println(k);
					}
				}

				// wave 7
				if ((wave == 7)) {
					System.out.println(timeAtLastAccept);
					long k = (System.nanoTime() - timeAtLastAccept) / 1000000000;

					if (miniWaves > 2 && miniWaves < 4) {
						handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
						handler.addObject(
								new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
						handler.addObject(new VerticalEnemyStraight((10), (10), ID.VerticalEnemyStraight, handler));
					}

					else if (miniWaves > 4 && miniWaves < 6) {
						handler.addObject(new VerticalEnemyStraight((110), (110), ID.VerticalEnemyStraight, handler));
					} else if (miniWaves > 6 && miniWaves < 8) {
						handler.addObject(new VerticalEnemyStraight((210), (210), ID.VerticalEnemyStraight, handler));
					} else if (miniWaves > 8 && miniWaves < 10) {
						handler.addObject(new VerticalEnemyStraight((310), (310), ID.VerticalEnemyStraight, handler));
					} else if (miniWaves > 10 && miniWaves < 12) {
						handler.addObject(new VerticalEnemyStraight((410), (410), ID.VerticalEnemyStraight, handler));
					} else if (miniWaves > 12 && miniWaves < 14) {
						handler.addObject(new VerticalEnemyStraight((510), (10), ID.VerticalEnemyStraight, handler));
					} else if (miniWaves > 14 && miniWaves < 16) {
						handler.addObject(new VerticalEnemyStraight((610), (110), ID.VerticalEnemyStraight, handler));
						handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
						handler.addObject(
								new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
					}

					timeAtLastAccept = System.nanoTime();
					System.out.println(k);
				}

				// wave 8
				if ((wave == 8)) {
					System.out.println(timeAtLastAccept);
					long k = (System.nanoTime() - timeAtLastAccept) / 1000000000;

					if (miniWaves > 2 && miniWaves < 4) {
						handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
						handler.addObject(
								new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
						handler.addObject(new HorizontalEnemyStraight((10), (10), ID.HorizontalEnemyStraight, handler));
					}

					else if (miniWaves > 4 && miniWaves < 6) {
						handler.addObject(
								new HorizontalEnemyStraight((110), (110), ID.HorizontalEnemyStraight, handler));
					} else if (miniWaves > 6 && miniWaves < 8) {
						handler.addObject(
								new HorizontalEnemyStraight((210), (210), ID.HorizontalEnemyStraight, handler));
					} else if (miniWaves > 8 && miniWaves < 10) {
						handler.addObject(
								new HorizontalEnemyStraight((310), (310), ID.HorizontalEnemyStraight, handler));
					} else if (miniWaves > 10 && miniWaves < 12) {
						handler.addObject(
								new HorizontalEnemyStraight((410), (410), ID.HorizontalEnemyStraight, handler));
						handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
						handler.addObject(
								new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
					}

					timeAtLastAccept = System.nanoTime();
					System.out.println(k);
				}

				// wave 9
				if ((wave == 9)) {
					System.out.println(timeAtLastAccept);
					long k = (System.nanoTime() - timeAtLastAccept) / 1000000000;

					if (miniWaves > 2 && miniWaves < 4) {
						handler.addObject(
								new HorizontalEnemyStraight((110), (10), ID.HorizontalEnemyStraight, handler));
						handler.addObject(new VerticalEnemyStraight((10), (10), ID.VerticalEnemyStraight, handler));
						handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
						handler.addObject(
								new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
					}

					else if (miniWaves > 4 && miniWaves < 6) {
						handler.addObject(
								new HorizontalEnemyStraight((210), (110), ID.HorizontalEnemyStraight, handler));
						handler.addObject(new VerticalEnemyStraight((110), (110), ID.VerticalEnemyStraight, handler));
					} else if (miniWaves > 8 && miniWaves < 10) {
						handler.addObject(
								new HorizontalEnemyStraight((310), (210), ID.HorizontalEnemyStraight, handler));
						handler.addObject(new VerticalEnemyStraight((210), (210), ID.VerticalEnemyStraight, handler));
						handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
						handler.addObject(
								new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
					} else if (miniWaves > 14 && miniWaves < 16) {
						handler.addObject(
								new HorizontalEnemyStraight((310), (310), ID.HorizontalEnemyStraight, handler));
					} else if (miniWaves > 16 && miniWaves < 18) {
						handler.addObject(
								new HorizontalEnemyStraight((410), (310), ID.HorizontalEnemyStraight, handler));
						handler.addObject(new VerticalEnemyStraight((310), (310), ID.VerticalEnemyStraight, handler));
						handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
						handler.addObject(
								new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
					}

					timeAtLastAccept = System.nanoTime();
					System.out.println(k);
				}

				// game complete / win
				if (wave == 10) {
					Game.gameState = Game.STATE.WIN;
				}
			}

			//////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// NORMAL =================================================
			if (Game.difficulty == 0) {

				// wave 1
				if ((wave == 1)) {
					if (miniWaves > 7 && miniWaves < 9) {
						handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
						handler.addObject(
								new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
					} else if (miniWaves > 14 && miniWaves < 16) {
						handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
						handler.addObject(
								new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
					}
				}

				// wave 2
				if ((wave == 2)) {

					if (miniWaves > 2 && miniWaves < 4) {
						handler.addObject(new VerticalEnemy(random.nextInt(Game.WIDTH - 45),
								(random.nextInt(Game.HEIGHT - 45)), ID.VerticalEnemy, handler));
					} else if (miniWaves > 4 && miniWaves < 6) {
						handler.addObject(new VerticalEnemy((random.nextInt(Game.WIDTH - 45)),
								(random.nextInt(Game.HEIGHT - 45)), ID.VerticalEnemy, handler));
					} else if (miniWaves > 12 && miniWaves < 14) {
						handler.addObject(new VerticalEnemy((random.nextInt(Game.WIDTH - 45)),
								(random.nextInt(Game.HEIGHT - 45)), ID.VerticalEnemy, handler));
					} else if (miniWaves > 14 && miniWaves < 16) {
						handler.addObject(new VerticalEnemy((random.nextInt(Game.WIDTH - 45)),
								(random.nextInt(Game.HEIGHT - 45)), ID.VerticalEnemy, handler));
					}
				}

				// wave 3
				if ((wave == 3)) {
					if (miniWaves > 2 && miniWaves < 4) {
						handler.addObject(new HorizontalEnemy((random.nextInt(Game.WIDTH - 45)),
								(random.nextInt(Game.HEIGHT - 45)), ID.HorizontalEnemy, handler));
						handler.addObject(new HorizontalEnemy((random.nextInt(Game.WIDTH - 45)),
								(random.nextInt(Game.HEIGHT - 45)), ID.HorizontalEnemy, handler));
					}

					else if (miniWaves > 12 && miniWaves < 14) {
						handler.addObject(new HorizontalEnemy((random.nextInt(Game.WIDTH - 45)),
								(random.nextInt(Game.HEIGHT - 45)), ID.HorizontalEnemy, handler));
						handler.addObject(new HorizontalEnemy((random.nextInt(Game.WIDTH - 45)),
								(random.nextInt(Game.HEIGHT - 45)), ID.HorizontalEnemy, handler));
					}
				}

				// wave 4
				if ((wave == 4)) {
					if (miniWaves > 2 && miniWaves < 4) {
						handler.addObject(new HorizontalEnemy(random.nextInt(250), (250), ID.HorizontalEnemy, handler));
						handler.addObject(new VerticalEnemy((250), (250), ID.VerticalEnemy, handler));
					} else if (miniWaves > 10 && miniWaves < 12) {
						handler.addObject(new HorizontalEnemy(random.nextInt(250), (250), ID.HorizontalEnemy, handler));
						handler.addObject(new VerticalEnemy((250), (250), ID.VerticalEnemy, handler));
					}
				}

				// wave 5
				if ((wave == 5)) {
					if (miniWaves > 2 && miniWaves < 4) {
						handler.addObject(new HorizontalEnemy(random.nextInt(250), (250), ID.HorizontalEnemy, handler));
						handler.addObject(new VerticalEnemy(random.nextInt(250), (250), ID.VerticalEnemy, handler));
					} else if (miniWaves > 10 && miniWaves < 12) {
						handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
						handler.addObject(
								new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
					} else if (miniWaves > 14 && miniWaves < 16) {
						handler.addObject(new HorizontalEnemy(random.nextInt(250), (250), ID.HorizontalEnemy, handler));
						handler.addObject(new VerticalEnemy(random.nextInt(250), (250), ID.VerticalEnemy, handler));
					}
				}

				// wave 6
				if ((wave == 6)) {
					if (miniWaves > 2 && miniWaves < 4) {
						handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
						handler.addObject(
								new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
					} else if (miniWaves > 6 && miniWaves < 8) {
						handler.addObject(new HorizontalEnemy(random.nextInt(250), (250), ID.HorizontalEnemy, handler));
						handler.addObject(new VerticalEnemy(random.nextInt(250), (250), ID.VerticalEnemy, handler));
					} else if (miniWaves > 12 && miniWaves < 14) {
						handler.addObject(new SmartEnemy((45), (45), ID.SmartEnemy, handler));
						handler.addObject(
								new SmartEnemy((Game.HEIGHT - 45), (Game.HEIGHT - 45), ID.SmartEnemy, handler));
					}

				}
			}

			// wave 7
			if ((wave == 7)) {
				if (miniWaves > 2 && miniWaves < 4) {
					handler.addObject(new VerticalEnemyStraight((10), (10), ID.VerticalEnemyStraight, handler));
				} else if (miniWaves > 4 && miniWaves < 6) {
					handler.addObject(new VerticalEnemyStraight((110), (110), ID.VerticalEnemyStraight, handler));
				} else if (miniWaves > 6 && miniWaves < 8) {
					handler.addObject(new VerticalEnemyStraight((210), (210), ID.VerticalEnemyStraight, handler));
				} else if (miniWaves > 8 && miniWaves < 10) {
					handler.addObject(new VerticalEnemyStraight((310), (310), ID.VerticalEnemyStraight, handler));
				} else if (miniWaves > 10 && miniWaves < 12) {
					handler.addObject(new VerticalEnemyStraight((410), (410), ID.VerticalEnemyStraight, handler));
				} else if (miniWaves > 12 && miniWaves < 14) {
					handler.addObject(new VerticalEnemyStraight((510), (10), ID.VerticalEnemyStraight, handler));
				} else if (miniWaves > 14 && miniWaves < 16) {
					handler.addObject(new VerticalEnemyStraight((610), (110), ID.VerticalEnemyStraight, handler));
					handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
				}
			}

			// wave 8
			if ((wave == 8)) {
				if (miniWaves > 2 && miniWaves < 4) {
					handler.addObject(new HorizontalEnemyStraight((10), (10), ID.HorizontalEnemyStraight, handler));
				}

				else if (miniWaves > 4 && miniWaves < 6) {
					handler.addObject(new HorizontalEnemyStraight((110), (110), ID.HorizontalEnemyStraight, handler));
				} else if (miniWaves > 6 && miniWaves < 8) {
					handler.addObject(new HorizontalEnemyStraight((210), (210), ID.HorizontalEnemyStraight, handler));
				} else if (miniWaves > 8 && miniWaves < 10) {
					handler.addObject(new HorizontalEnemyStraight((310), (310), ID.HorizontalEnemyStraight, handler));
				} else if (miniWaves > 10 && miniWaves < 12) {
					handler.addObject(new HorizontalEnemyStraight((410), (410), ID.HorizontalEnemyStraight, handler));
					handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
				}
			}

			// wave 9
			if ((wave == 9)) {
				if (miniWaves > 2 && miniWaves < 4) {
					handler.addObject(new HorizontalEnemyStraight((110), (10), ID.HorizontalEnemyStraight, handler));
					handler.addObject(new VerticalEnemyStraight((10), (10), ID.VerticalEnemyStraight, handler));
					handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
				}

				else if (miniWaves > 4 && miniWaves < 6) {
					handler.addObject(new HorizontalEnemyStraight((210), (110), ID.HorizontalEnemyStraight, handler));
					handler.addObject(new VerticalEnemyStraight((110), (110), ID.VerticalEnemyStraight, handler));
				} else if (miniWaves > 8 && miniWaves < 10) {
					handler.addObject(new HorizontalEnemyStraight((310), (210), ID.HorizontalEnemyStraight, handler));
					handler.addObject(new VerticalEnemyStraight((210), (210), ID.VerticalEnemyStraight, handler));
					handler.addObject(new BasicEnemy((45), (45), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy((Game.WIDTH - 45), (Game.HEIGHT - 45), ID.BasicEnemy, handler));
				} else if (miniWaves > 14 && miniWaves < 16) {
					handler.addObject(new HorizontalEnemyStraight((310), (310), ID.HorizontalEnemyStraight, handler));
				} else if (miniWaves > 16 && miniWaves < 18) {
					handler.addObject(new HorizontalEnemyStraight((410), (310), ID.HorizontalEnemyStraight, handler));
					handler.addObject(new VerticalEnemyStraight((310), (310), ID.VerticalEnemyStraight, handler));
				}
			}

			// game complete / win
			if (wave == 10) {
				Game.gameState = Game.STATE.WIN;
			}
		}

	}

	/** RENDER **/

	public void render(Graphics g) {
		Font font = new Font("Century Gothic", Font.PLAIN, 50);
		g.setFont(font);

		String title;

		FontMetrics metrics = g.getFontMetrics(font);

		if (hud.getLevel() % maxMiniWaves == 0 && Game.gameState != Game.STATE.WIN) {

			title = "- W A V E  " + wave + " -";
			int waveWidth = metrics.stringWidth(title);
			int titleX = Game.WIDTH / 2 - waveWidth / 2;

			g.setColor(Color.WHITE);
			g.drawString(title + "", titleX, 100);
		}

	}

	/** SETTERS **/
	public static void setWave(int wave) {
		Spawn.wave = wave;
	}

	public static void setWaveInc(int waveInc) {
		Spawn.waveInc = waveInc;
	}
}