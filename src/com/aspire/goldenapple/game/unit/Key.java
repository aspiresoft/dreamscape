package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;

public class Key extends Unit {
	public static final int CODE1 = 10, CODE2 = 11, CODE3 = 12, CODE4 = 13;

	private GamePanel gamePanel;
	private int type;

	public Key(Program program, GamePanel gamePanel, int x, int y, int width, int height, int type, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;
		this.type = type;

		shouldWork = true;
		isAnEntity = false;
	}

	public void update() {
		if (shouldWork) {
			if (gamePanel.ninjaPlaying) {
				PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						shouldWork = false;
						if (type == CODE1) {
							gamePanel.blueKeyPicked = true;
						}
						if (type == CODE2) {
							gamePanel.greenKeyPicked = true;
						}
						if (type == CODE3) {
							gamePanel.redKeyPicked = true;
						}
						if (type == CODE4) {
							gamePanel.yellowKeyPicked = true;
						}
					}
				}
				p = null;
			}
			if (gamePanel.robotPlaying) {
				PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						shouldWork = false;
						if (type == CODE1) {
							gamePanel.blueKeyPicked = true;
						}
						if (type == CODE2) {
							gamePanel.greenKeyPicked = true;
						}
						if (type == CODE3) {
							gamePanel.redKeyPicked = true;
						}
						if (type == CODE4) {
							gamePanel.yellowKeyPicked = true;
						}
					}
				}
				p = null;
			}
			if (gamePanel.knightPlaying) {
				PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						shouldWork = false;
						if (type == CODE1) {
							gamePanel.blueKeyPicked = true;
						}
						if (type == CODE2) {
							gamePanel.greenKeyPicked = true;
						}
						if (type == CODE3) {
							gamePanel.redKeyPicked = true;
						}
						if (type == CODE4) {
							gamePanel.yellowKeyPicked = true;
						}
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
