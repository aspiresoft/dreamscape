package com.aspire.goldenapple.game.unit;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;

public class Acid extends Unit {
	public static final int CODE1 = 33, CODE2 = 34, CODE3 = 35, CODE4 = 36, CODE5 = 37, CODE6 = 38;

	private GamePanel gamePanel;
	private int type;

	public Acid(Program program, GamePanel gamePanel, int x, int y, int width, int height, int type, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;
		this.type = type;
		isAnEntity = false;
		shouldWork = true;
	}

	public void update() {
		if (shouldWork) {
			if (gamePanel.ninjaPlaying) {
				PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						p.kill();
					}
				}
			}
			if (gamePanel.robotPlaying) {
				PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						p.kill();
					}
				}
			}
			if (gamePanel.knightPlaying) {
				PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						p.kill();
					}
				}
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
		}
	}

	public void draw(Graphics g) {
		if (shouldWork) {
			g.drawImage(Constants.IMAGES[Constants.UNIT_ID_TO_IMAGE_ID(type)], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);

		}
	}

}
