package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.tools.Animation;
import com.aspire.goldenapple.tools.MediaPlayer;

public class Flag extends Unit {
	public static final int CODE1 = 3, CODE2 = 4, CODE3 = 5, CODE4 = 6;

	private static final int POINT = 1000;
	private static final int FRAME_COUNT = 2;

	private static final int DURATION = (int) Constants.TICK_PER_SEC / 4;

	private GamePanel gamePanel;
	private Animation anim;
	private boolean activated;
	private int id;
	private int type;

	public Flag(Program program, GamePanel gamePanel, int x, int y, int width, int height, int type, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;
		this.type = type;
		activated = false;
		isAnEntity = true;
		shouldWork = true;
		id = (type - CODE1) * 3 + 388;

		BufferedImage[] images = new BufferedImage[FRAME_COUNT];
		images[0] = Constants.IMAGES[id];
		images[1] = Constants.IMAGES[id + 1];
		anim = new Animation(gamePanel, images, x, y, width, height, DURATION, true);
	}

	public void update() {
		if (shouldWork) {
			if (gamePanel.ninjaPlaying) {
				PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
				if (p != null) {
					if (!activated && new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						MediaPlayer.playSound(Constants.flag_taken);

						activated = true;
						if (type == CODE1) {
							gamePanel.blueFlagPicked = true;
						}
						if (type == CODE2) {
							gamePanel.greenFlagPicked = true;
						}
						if (type == CODE3) {
							gamePanel.redFlagPicked = true;
						}
						if (type == CODE4) {
							gamePanel.yellowFlagPicked = true;
						}
						gamePanel.addScore(POINT, p.x, p.y);
					}
				}
				p = null;
			}
			if (gamePanel.robotPlaying) {
				PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
				if (p != null) {
					if (!activated && new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						MediaPlayer.playSound(Constants.flag_taken);

						activated = true;
						if (type == CODE1) {
							gamePanel.blueFlagPicked = true;
						}
						if (type == CODE2) {
							gamePanel.greenFlagPicked = true;
						}
						if (type == CODE3) {
							gamePanel.redFlagPicked = true;
						}
						if (type == CODE4) {
							gamePanel.yellowFlagPicked = true;
						}
						gamePanel.addScore(POINT, p.x, p.y);
					}
				}
				p = null;
			}
			if (gamePanel.knightPlaying) {
				PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
				if (p != null) {
					if (!activated && new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						MediaPlayer.playSound(Constants.flag_taken);

						activated = true;
						if (type == CODE1) {
							gamePanel.blueFlagPicked = true;
						}
						if (type == CODE2) {
							gamePanel.greenFlagPicked = true;
						}
						if (type == CODE3) {
							gamePanel.redFlagPicked = true;
						}
						if (type == CODE4) {
							gamePanel.yellowFlagPicked = true;
						}
						gamePanel.addScore(POINT, p.x, p.y);
					}
				}
				p = null;
			}

			if (activated && anim != null) {
				anim.update();
				if (!anim.shouldAnimate) {
					anim = null;
				}
			}
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			if (activated) {
				if (anim != null) {
					anim.draw(g);
				}
			} else {
				g.drawImage(Constants.IMAGES[id + 2], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
			}
		}
	}
}
