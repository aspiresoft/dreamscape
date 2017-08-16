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

public class Volcano extends Unit {
	public static final int CODE = 46;

	private static final int ANIM_DURATION = (int) Constants.TICK_PER_SEC * 2 / 3;
	private static final int FREQUENCY = (int) Constants.TICK_PER_SEC * 4;
	private static final int DIST = 2;
	private static final int FRAME_COUNT = 10;

	private GamePanel gamePanel;
	private Rectangle rect;
	private Animation anim;
	private int ticks;
	private int fireTicks;
	private ArrayList<FireBall> fireBalls;

	public Volcano(Program program, GamePanel gamePanel, int x, int y, int width, int height, int mX, int mY, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;

		isAnEntity = false;
		shouldWork = true;

		matrixX = mX;
		matrixY = mY;

		BufferedImage[] images = new BufferedImage[FRAME_COUNT];
		if (status == 0) {
			gamePanel.getCollision_matrix()[matrixX][matrixY] = 1;
		} else {
			gamePanel.getCollision_parallel_matrix()[status - 1][matrixX][matrixY] = 1;
		}

		for (int i = 0; i < FRAME_COUNT; i++) {
			images[i] = Constants.IMAGES[111 + i];
		}

		ticks = 0;
		fireTicks = 0;
		fireBalls = new ArrayList<FireBall>();
		rect = new Rectangle(x, y - DIST, width, DIST * 2);
		anim = new Animation(gamePanel, images, x, y, width, height, ANIM_DURATION, true);
	}

	public void update() {
		if (shouldWork) {
			if (gamePanel.ninjaPlaying) {
				PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
				if (p != null) {
					if (rect.intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						p.hurt(1);
					}
					if (ticks - fireTicks > FREQUENCY) {
						fireTicks = ticks;
						fireBalls.add(new FireBall(program, gamePanel, x + width / 4, y, width / 2, height / 2, status, priority));

						if (Engine.isInRange(x, y, p.x, p.y, Constants.BRICK_SIZE * 20)) {
							MediaPlayer.playSound(Constants.fireball_sound_1, 0.2f);
						}
					}
				}
				p = null;
			}
			if (gamePanel.robotPlaying) {
				PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
				if (p != null) {
					if (rect.intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						p.hurt(1);
					}
					if (ticks - fireTicks > FREQUENCY) {
						fireTicks = ticks;
						fireBalls.add(new FireBall(program, gamePanel, x + width / 4, y, width / 2, height / 2, status, priority));

						if (Engine.isInRange(x, y, p.x, p.y, Constants.BRICK_SIZE * 20)) {
							MediaPlayer.playSound(Constants.fireball_sound_1, 0.2f);
						}
					}
				}
				p = null;
			}
			if (gamePanel.knightPlaying) {
				PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
				if (p != null) {
					if (rect.intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						p.hurt(1);
					}
					if (ticks - fireTicks > FREQUENCY) {
						fireTicks = ticks;
						fireBalls.add(new FireBall(program, gamePanel, x + width / 4, y, width / 2, height / 2, status, priority));

						if (Engine.isInRange(x, y, p.x, p.y, Constants.BRICK_SIZE * 20)) {
							MediaPlayer.playSound(Constants.fireball_sound_1, 0.2f);
						}
					}
				}
				p = null;
			}

			if (anim != null) {
				anim.update();
				if (!anim.shouldAnimate) {
					anim = null;
				}
			}

			Iterator<FireBall> iter = fireBalls.iterator();
			while (iter.hasNext()) {
				iter.next().update();
			}
			iter = null;
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			Iterator<FireBall> iter = fireBalls.iterator();
			while (iter.hasNext()) {
				iter.next().draw(g);
			}
			iter = null;
			if (anim != null) {
				anim.draw(g);
			}

		}
	}

}
