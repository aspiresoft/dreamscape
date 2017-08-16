package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import java.util.Iterator;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.tools.MediaPlayer;

public class FireBall extends Unit {
	private static final int DISTANCE = Constants.BRICK_SIZE * 3;
	private static final int SPEED = 4;
	private static final int SIZE = 25;
	private static final int SPACE = (Constants.BRICK_SIZE - SIZE) / 2;

	private GamePanel gamePanel;
	private int targetLoc;
	private int startY;
	private boolean goingTop, goingBot;

	public FireBall(Program program, GamePanel gamePanel, int x, int y, int width, int height, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;

		shouldWork = true;
		goingTop = true;
		isAnEntity = false;
		goingBot = false;

		x += SPACE;
		y += SPACE;
		width -= SPACE * 2;
		height -= SPACE * 2;
		startY = y;
		targetLoc = Engine.safeY(y - DISTANCE);

	}

	public void update() {
		if (shouldWork) {
			for (int i = 1; i <= SPEED; i++) {
				if (gamePanel.ninjaPlaying) {
					PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
					if (p != null) {
						if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
							p.hurt(1);
						}
					}
					p = null;
				}
				if (gamePanel.robotPlaying) {
					PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
					if (p != null) {
						if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
							p.hurt(1);
						}
					}
					p = null;
				}
				if (gamePanel.knightPlaying) {
					PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
					if (p != null) {
						if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
							p.hurt(1);
						}
					}
					p = null;
				}
				Iterator<Unit> iter = gamePanel.getUnits().iterator();
				while (iter.hasNext()) {
					Unit u = iter.next();
					if (u.isAnEntity && (new Rectangle(x, y, width, height).intersects(new Rectangle(u.x, u.y, u.width, u.height)))) {
						u.die();
					}
					u = null;
				}
				iter = null;
				if (y - targetLoc <= 0) {
					goingTop = false;
					goingBot = true;
				}
				if (y > startY) {
					MediaPlayer.playSound(Constants.fireball_sound_2, 0.1f);
					shouldWork = false;
				}
				if (goingBot) {
					y++;
				}
				if (goingTop) {
					y--;
				}
			}
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			if (goingTop) {
				g.drawImage(Constants.IMAGES[382], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
			} else {
				g.drawImage(Constants.IMAGES[383], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
			}
		}
	}
}
