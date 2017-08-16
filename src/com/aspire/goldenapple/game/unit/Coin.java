package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.tools.Animation;
import com.aspire.goldenapple.tools.MediaPlayer;

public class Coin extends Unit {
	public static final int CODE1 = 7, CODE2 = 8, CODE3 = 9;

	private static final int SIZE = Constants.BRICK_SIZE * 3 / 4;
	private static final int DURATION = (int) Constants.TICK_PER_SEC * 2 / 5;
	private static final int POINT_GOLD = 1000;
	private static final int POINT_SILVER = 400;
	private static final int POINT_BRONZE = 150;

	private GamePanel gamePanel;
	private Animation anim;

	public Coin(Program program, GamePanel gamePanel, int x, int y, int width, int height, int type, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;

		shouldWork = true;
		int space = (Constants.BRICK_SIZE - SIZE) / 2;
		x += space;
		y += space;
		width -= space * 2;
		height -= space * 2;

		BufferedImage[] images = new BufferedImage[7];
		for (int i = 0; i < images.length; i++) {
			images[i] = Constants.IMAGES[(type - CODE1) * 7 + i + 344];
		}
		anim = new Animation(gamePanel, images, x, y, width, height, DURATION, true);
		scoreToAdd(type);
	}

	public void update() {
		if (shouldWork) {
			if (anim != null) {
				anim.update();
			}
			if (gamePanel.ninjaPlaying) {
				PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						shouldWork = false;
						MediaPlayer.playSound(Constants.coin_picked);
						gamePanel.addScore(score, x, y);
					}
				}
				p = null;
			}
			if (gamePanel.robotPlaying) {
				PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						shouldWork = false;
						MediaPlayer.playSound(Constants.coin_picked);
						gamePanel.addScore(score, x, y);
					}
				}
				p = null;
			}
			if (gamePanel.knightPlaying) {
				PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						shouldWork = false;
						MediaPlayer.playSound(Constants.coin_picked);
						gamePanel.addScore(score, x, y);
					}
				}
				p = null;
			}
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			if (anim != null) {
				anim.draw(g);
			}
		}
	}

	public void scoreToAdd(int type) {
		switch (type) {
		case CODE1:
			score = POINT_BRONZE;
			break;
		case CODE2:
			score = POINT_SILVER;
			break;
		case CODE3:
			score = POINT_GOLD;
			break;
		default:
			score = 0;
		}
	}
}
