package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;

public class Spring extends Unit {
	public static final int CODE1 = 18, CODE2 = 19, CODE3 = 20, CODE4 = 21;

	private static int DELAY = (int) Constants.TICK_PER_SEC / 2;

	private GamePanel gamePanel;
	private int type;
	private boolean sprung;
	private int ticks;
	private int jumpTicks;

	public Spring(Program program, GamePanel gamePanel, int x, int y, int width, int height, int type, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;
		shouldWork = true;
		sprung = false;
		this.type = type;
		isAnEntity = false;
		ticks = 0;
		jumpTicks = 0;
	}

	public void update() {
		if (shouldWork) {
			ticks++;
			if (ticks - jumpTicks > DELAY)
				sprung = false;
			if (gamePanel.ninjaPlaying) {
				PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						if (type == CODE4) {
							if (!p.isJumping()) {
								sprung = true;
								jumpTicks = ticks;
								p.setSpringMovement(1);
								p.setSpringMoveAmount(0);
							}
						}
						if (type == CODE3) {
							sprung = true;
							jumpTicks = ticks;
							p.setSpringMovement(2);
							p.setSpringMoveAmount(0);
						}
						if (type == CODE1) {
							sprung = true;
							jumpTicks = ticks;
							p.setSpringMovement(3);
							p.setSpringMoveAmount(0);
						}
						if (type == CODE2) {
							sprung = true;
							jumpTicks = ticks;
							p.setSpringMovement(4);
							p.setSpringMoveAmount(0);
						}
					}
				}
			}
			if (gamePanel.robotPlaying) {
				PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						if (type == CODE4) {
							if (!p.isJumping()) {
								sprung = true;
								jumpTicks = ticks;
								p.setSpringMovement(1);
								p.setSpringMoveAmount(0);
							}
						}
						if (type == CODE3) {
							sprung = true;
							jumpTicks = ticks;
							p.setSpringMovement(2);
							p.setSpringMoveAmount(0);
						}
						if (type == CODE1) {
							sprung = true;
							jumpTicks = ticks;
							p.setSpringMovement(3);
							p.setSpringMoveAmount(0);
						}
						if (type == CODE2) {
							sprung = true;
							jumpTicks = ticks;
							p.setSpringMovement(4);
							p.setSpringMoveAmount(0);
						}
					}
				}
			}
			if (gamePanel.knightPlaying) {
				PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						if (type == CODE4) {
							if (!p.isJumping()) {
								sprung = true;
								jumpTicks = ticks;
								p.setSpringMovement(1);
								p.setSpringMoveAmount(0);
							}
						}
						if (type == CODE3) {
							sprung = true;
							jumpTicks = ticks;
							p.setSpringMovement(2);
							p.setSpringMoveAmount(0);
						}
						if (type == CODE1) {
							sprung = true;
							jumpTicks = ticks;
							p.setSpringMovement(3);
							p.setSpringMoveAmount(0);
						}
						if (type == CODE2) {
							sprung = true;
							jumpTicks = ticks;
							p.setSpringMovement(4);
							p.setSpringMoveAmount(0);
						}
					}
				}
			}
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			if (!sprung) {
				g.drawImage(Constants.IMAGES[Constants.UNIT_ID_TO_IMAGE_ID(type)], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);

			} else {
				if (type == CODE1) {
					g.drawImage(Constants.IMAGES[366], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
				}
				if (type == CODE2) {
					g.drawImage(Constants.IMAGES[367], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
				}
				if (type == CODE3) {
					g.drawImage(Constants.IMAGES[368], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
				}
				if (type == CODE4) {
					g.drawImage(Constants.IMAGES[369], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
				}
			}
		}
	}
}
