package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.tools.MediaPlayer;

public class Box extends Unit {
	public static final int CODE1 = 30, CODE2 = 31, CODE3 = 32;

	private static final int POINT = 50;

	private GamePanel gamePanel;
	private int type;
	private boolean broken;

	public Box(Program program, GamePanel gamePanel, int x, int y, int width, int height, int type, int matrixX, int matrixY, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.matrixX = matrixX;
		this.matrixY = matrixY;
		this.gamePanel = gamePanel;
		this.type = type;

		shouldWork = true;
		setBroken(false);
		isAnEntity = false;

		if (status == 0) {
			gamePanel.getCollision_matrix()[matrixX][matrixY] = 1;
		} else {
			gamePanel.getCollision_parallel_matrix()[status - 1][matrixX][matrixY] = 1;
		}
	}

	public void update() {
		if (shouldWork) {
			if (!isBroken()) {
				if (gamePanel.ninjaPlaying) {
					PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
					if (p != null) {
						Rectangle r1 = new Rectangle(x, y, width, height);
						Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height + 1);

						if (r1.intersects(r2)) {
							if (r1.y - r2.y > r1.height * 2 / 3) {
								if (!p.isJumping()) {
									p.jump();
									p.setAffectedByForce(true);
									gamePanel.getParticles().add(new Particle(program, gamePanel, x + width / 2, y + width / 2, 2, status));

									die();
									gamePanel.addScore(POINT, x, y);
								}
							}
						}
						r1 = null;
						r2 = null;
					}
				}
				if (gamePanel.robotPlaying) {
					PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
					if (p != null) {
						Rectangle r1 = new Rectangle(x, y, width, height);
						Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height + 1);

						if (r1.intersects(r2)) {
							if (r1.y - r2.y > r1.height * 2 / 3) {
								if (!p.isJumping()) {
									p.jump();
									p.setAffectedByForce(true);
									gamePanel.getParticles().add(new Particle(program, gamePanel, x + width / 2, y + width / 2, 2, status));

									die();
									gamePanel.addScore(POINT, x, y);
								}
							}
						}
						r1 = null;
						r2 = null;
					}
				}
				if (gamePanel.knightPlaying) {
					PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
					if (p != null) {
						Rectangle r1 = new Rectangle(x, y, width, height);
						Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height + 1);

						if (r1.intersects(r2)) {
							if (r1.y - r2.y > r1.height * 2 / 3) {
								if (!p.isJumping()) {
									p.jump();
									p.setAffectedByForce(true);
									gamePanel.getParticles().add(new Particle(program, gamePanel, x + width / 2, y + width / 2, 2, status));

									die();
									gamePanel.addScore(POINT, x, y);
								}
							}
						}
						r1 = null;
						r2 = null;
					}
				}
			}
		}
	}

	public void die() {
		if (!isBroken()) {
			setBroken(true);
			if (status == 0) {
				gamePanel.getCollision_matrix()[matrixX][matrixY] = 0;
			} else {
				gamePanel.getCollision_parallel_matrix()[status - 1][matrixX][matrixY] = 0;
			}
			MediaPlayer.playSound(Constants.box_break, 0.3f);
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			if (!isBroken()) {
				g.drawImage(Constants.IMAGES[Constants.UNIT_ID_TO_IMAGE_ID(type)], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
			}
		}
	}

	public boolean isBroken() {
		return broken;
	}

	public void setBroken(boolean broken) {
		this.broken = broken;
	}
}
