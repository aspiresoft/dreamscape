package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.tools.Animation;

public class Fire extends Unit {
	public static final int CODE = 45;

	private static final int FRAME_COUNT = 8;
	private static final int SENSIVITY = 3;
	private static final int DURATION = (int) Constants.TICK_PER_SEC * 4 / 5;

	private Animation anim;
	private GamePanel gamePanel;

	public Fire(Program program, GamePanel gamePanel, int x, int y, int width, int height, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;

		shouldWork = true;
		isAnEntity = false;

		BufferedImage[] images = new BufferedImage[FRAME_COUNT];
		for (int i = 0; i < FRAME_COUNT; i++) {
			images[i] = Constants.IMAGES[374 + i];
		}
		anim = new Animation(gamePanel, images, x, y, width, height, DURATION, true);

	}

	public void update() {
		if (shouldWork) {
			if (gamePanel.ninjaPlaying) {
				PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x + SENSIVITY, y + SENSIVITY, width - SENSIVITY * 2, height - SENSIVITY * 2).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						p.hurt(1);
					}
				}
				p = null;
			}
			if (gamePanel.robotPlaying) {
				PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x + SENSIVITY, y + SENSIVITY, width - SENSIVITY * 2, height - SENSIVITY * 2).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						p.hurt(1);
					}
				}
				p = null;
			}
			if (gamePanel.knightPlaying) {
				PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x + SENSIVITY, y + SENSIVITY, width - SENSIVITY * 2, height - SENSIVITY * 2).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
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
			if (anim != null) {
				anim.update();
				if (!anim.shouldAnimate) {
					anim = null;
					shouldWork = true;
				}
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

}
