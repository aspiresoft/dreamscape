package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.tools.Animation;
import com.aspire.goldenapple.tools.MediaPlayer;

public class LaserMachine extends Unit {
	public static final int CODE1 = 26, CODE2 = 27, CODE3 = 28, CODE4 = 29;

	private static final int HIT_COUNT = 3;
	private static final int POINT = 200;

	private int hit_count;
	private GamePanel gamePanel;
	private int type;

	private ArrayList<Laser> lasers;

	private boolean shooting;
	private int laserX, laserY;

	private static final int HIT_DELAY = (int) Constants.TICK_PER_SEC / 3;
	private static final int COOLDOWN = (int) Constants.TICK_PER_SEC * 3;

	private int ticks;
	private int hitTicks;
	private int shootTicks;
	private BufferedImage[] expImages;

	public LaserMachine(Program program, GamePanel gamePanel, int x, int y, int width, int height, int type, int matrixX, int matrixY, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;
		this.type = type;
		this.matrixX = matrixX;
		this.matrixY = matrixY;
		shooting = false;
		isAnEntity = false;
		shouldWork = true;

		lasers = new ArrayList<Laser>();
		ticks = 0;
		hitTicks = 0;
		shootTicks = 0;

		score = POINT;
		hit_count = 0;

		expImages = new BufferedImage[8];
		for (int i = 0; i < expImages.length; i++) {
			expImages[i] = Constants.IMAGES[491 + i];
		}

		switch (type) {
		case CODE4:
			laserX = x + width / 6;
			laserY = y - height + height / 6;
			break;
		case CODE3:
			laserX = x + width;
			laserY = y + height / 6;
			break;
		case CODE1:
			laserX = x + width / 6;
			laserY = y + height;
			break;
		case CODE2:
			laserX = x - width + width / 6;
			laserY = y + height / 6;
			break;
		}
		if (status == 0) {
			gamePanel.getCollision_matrix()[matrixX][matrixY] = 1;
		} else {
			gamePanel.getCollision_parallel_matrix()[status - 1][matrixX][matrixY] = 1;
		}
	}

	public void update() {
		if (shouldWork) {
			ticks++;
			Iterator<Laser> iter = lasers.iterator();
			while (iter.hasNext()) {
				Laser l = iter.next();
				if (!l.shouldWork) {
					iter.remove();
				} else {
					l.update();
				}
				l = null;
			}
			iter = null;
			if (gamePanel.ninjaPlaying) {
				PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
				if (p != null) {
					Rectangle r1 = new Rectangle(x - 1, y - 1, width + 2, height + 2);
					Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height);

					if (r1.intersects(r2) && r1.y - r2.y > r1.height / 2 && !p.isJumping() && ticks - hitTicks >= HIT_DELAY) {
						hitTicks = ticks;
						p.jump();
						p.setAffectedByForce(true);
						hit_count++;
						gamePanel.getParticles().add(new Particle(program, gamePanel, x + width / 2, y + width / 2, 1, status));
						if (hit_count != HIT_COUNT) {
							MediaPlayer.playSound(Constants.machine_hit);
						}
					}
					r1 = null;
					r2 = null;

					if (ticks - shootTicks >= COOLDOWN * 9 / 10) {
						shooting = true;
					}
					if (ticks - shootTicks >= COOLDOWN) {
						shootTicks = ticks;
						shooting = false;
						fire();
					}

					if (hit_count >= HIT_COUNT) {
						gamePanel.addScore(score, x, y);
						die();
					}
				}
				p = null;
			}
			if (gamePanel.robotPlaying) {
				PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
				if (p != null) {
					Rectangle r1 = new Rectangle(x - 1, y - 1, width + 2, height + 2);
					Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height);

					if (r1.intersects(r2) && r1.y - r2.y > r1.height / 2 && !p.isJumping() && ticks - hitTicks >= HIT_DELAY) {
						hitTicks = ticks;
						p.jump();
						p.setAffectedByForce(true);
						hit_count++;
						gamePanel.getParticles().add(new Particle(program, gamePanel, x + width / 2, y + width / 2, 1, status));
						if (hit_count != HIT_COUNT) {
							MediaPlayer.playSound(Constants.machine_hit);
						}
					}
					r1 = null;
					r2 = null;

					if (ticks - shootTicks >= COOLDOWN * 9 / 10) {
						shooting = true;
					}
					if (ticks - shootTicks >= COOLDOWN) {
						shootTicks = ticks;
						shooting = false;
						fire();
					}

					if (hit_count >= HIT_COUNT) {
						gamePanel.addScore(score, x, y);
						die();
					}
				}
				p = null;
			}
			if (gamePanel.knightPlaying) {
				PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
				if (p != null) {
					Rectangle r1 = new Rectangle(x - 1, y - 1, width + 2, height + 2);
					Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height);

					if (r1.intersects(r2) && r1.y - r2.y > r1.height / 2 && !p.isJumping() && ticks - hitTicks >= HIT_DELAY) {
						hitTicks = ticks;
						p.jump();
						p.setAffectedByForce(true);
						hit_count++;
						gamePanel.getParticles().add(new Particle(program, gamePanel, x + width / 2, y + width / 2, 1, status));
						if (hit_count != HIT_COUNT) {
							MediaPlayer.playSound(Constants.machine_hit);
						}
					}
					r1 = null;
					r2 = null;

					if (ticks - shootTicks >= COOLDOWN * 9 / 10) {
						shooting = true;
					}
					if (ticks - shootTicks >= COOLDOWN) {
						shootTicks = ticks;
						shooting = false;
						fire();
					}

					if (hit_count >= HIT_COUNT) {
						gamePanel.addScore(score, x, y);
						die();
					}
				}
				p = null;
			}
		}
	}

	public void fire() {
		lasers.add(new Laser(program, gamePanel, laserX, laserY, width * 2 / 3, height * 2 / 3, type - (CODE1 - 1), status, priority));
		if (Engine.isInRange(x, y, PlayerNinja.getpX(), PlayerNinja.getpY(), Laser.DISTANCE)) {
			MediaPlayer.playSound(Constants.laser, 0.1f);
		}
	}

	public void die() {
		shouldWork = false;
		if (status == 0) {
			gamePanel.getCollision_matrix()[matrixX][matrixY] = 0;
		} else {
			gamePanel.getCollision_parallel_matrix()[status - 1][matrixX][matrixY] = 0;
		}
		MediaPlayer.playSound(Constants.laser_explode);
		gamePanel.getAnimations().add(new Animation(gamePanel, expImages, x - width / 2, y - height / 2, width * 2, height * 2, (int) Constants.TICK_PER_SEC, true));

	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			Iterator<Laser> iter = lasers.iterator();
			while (iter.hasNext()) {
				Laser l = iter.next();
				l.draw(g);
				l = null;
			}
			iter = null;

			if (shooting) {
				if (type == CODE1) {
					g.drawImage(Constants.IMAGES[370], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
				}
				if (type == CODE2) {
					g.drawImage(Constants.IMAGES[371], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
				}
				if (type == CODE3) {
					g.drawImage(Constants.IMAGES[372], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
				}
				if (type == CODE4) {
					g.drawImage(Constants.IMAGES[373], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
				}
			} else {
				g.drawImage(Constants.IMAGES[Constants.UNIT_ID_TO_IMAGE_ID(type)], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);

			}
		}
	}
}
