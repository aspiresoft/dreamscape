package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.tools.Message;

public class Gate extends Unit {
	public static final int CODE = 2;

	private static final String DOOR_OPEN = "Exit door has been opened!";
	private GamePanel gamePanel;
	private boolean activated;

	public Gate(Program program, GamePanel gamePanel, int x, int y, int width, int height, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;
		shouldWork = true;
		isAnEntity = false;
		activated = false;
	}

	public void update() {
		if (shouldWork) {
			if (gamePanel.ninjaPlaying) {
				PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
				if (p != null) {
					if (gamePanel.blueFlagPicked && gamePanel.redFlagPicked && gamePanel.yellowFlagPicked && gamePanel.greenFlagPicked && !activated) {
						activated = true;
						gamePanel.getHud().getMessages().add(new Message(DOOR_OPEN));
					}
					if (activated && new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						gamePanel.addScore(2500, p.x, p.y);
						gamePanel.finish();
						activated = false;
					}
				}
				p = null;
			}
			if (gamePanel.robotPlaying) {
				PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
				if (p != null) {
					if (gamePanel.blueFlagPicked && gamePanel.redFlagPicked && gamePanel.yellowFlagPicked && gamePanel.greenFlagPicked && !activated) {
						activated = true;
						gamePanel.getHud().getMessages().add(new Message(DOOR_OPEN));
					}
					if (activated && new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						gamePanel.addScore(2500, p.x, p.y);
						gamePanel.finish();
						activated = false;
					}
				}
				p = null;
			}
			if (gamePanel.knightPlaying) {
				PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
				if (p != null) {
					if (gamePanel.blueFlagPicked && gamePanel.redFlagPicked && gamePanel.yellowFlagPicked && gamePanel.greenFlagPicked && !activated) {
						activated = true;
						gamePanel.getHud().getMessages().add(new Message(DOOR_OPEN));
					}
					if (activated && new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						gamePanel.addScore(2500, p.x, p.y);
						gamePanel.finish();
						activated = false;
					}
				}
				p = null;
			}
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			if (activated) {
				g.drawImage(Constants.IMAGES[Constants.UNIT_ID_TO_IMAGE_ID(CODE)], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
			} else {
				g.drawImage(Constants.IMAGES[365], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
			}
		}
	}

}
