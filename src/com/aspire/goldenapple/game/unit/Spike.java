package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;

public class Spike extends Unit {
	public static final int CODE1 = 22, CODE2 = 23, CODE3 = 24, CODE4 = 25;

	private static final int DIST = 2;

	private GamePanel gamePanel;
	private Rectangle rect;
	private int type;

	public Spike(Program program, GamePanel gamePanel, int x, int y, int width, int height, int type, int mX, int mY, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;
		this.type = type;

		shouldWork = true;
		isAnEntity = false;

		matrixX = mX;
		matrixY = mY;

		if (status == 0) {
			gamePanel.getCollision_matrix()[matrixX][matrixY] = 1;
		} else {
			gamePanel.getCollision_parallel_matrix()[status - 1][matrixX][matrixY] = 1;
		}
		switch (type) {
		case CODE4:// UP
			rect = new Rectangle(x, y - DIST, width, DIST * 2);
			break;
		case CODE3:// RIGHT
			rect = new Rectangle(x + width - DIST, y, DIST * 2, height);
			break;
		case CODE1:// DOWN
			rect = new Rectangle(x, y + height - DIST, width, DIST * 2);
			break;
		case CODE2:// LEFT
			rect = new Rectangle(x - DIST, y, DIST * 2, height);
			break;
		}
	}

	public void update() {
		if (shouldWork) {
			if (gamePanel.ninjaPlaying) {
				PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
				if (p != null) {
					if (rect.intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						p.hurt(1);
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
				}
				p = null;
			}
			if (gamePanel.knightPlaying) {
				PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
				if (p != null) {
					if (rect.intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						p.hurt(1);
					}
				}
				p = null;
			}
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			g.drawImage(Constants.IMAGES[Constants.UNIT_ID_TO_IMAGE_ID(type)], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);

		}
	}

}
