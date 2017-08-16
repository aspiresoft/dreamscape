package com.aspire.goldenapple.game.unit;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.tools.MediaPlayer;

public class Node extends Unit {
	public static final int CODE1 = 39, CODE2 = 40;

	private static final int POINT = 200;

	private GamePanel gamePanel;
	private boolean picked;

	public Node(Program program, GamePanel gamePanel, int x, int y, int width, int height, int type, int matrixX, int matrixY, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;
		this.matrixX = matrixX;
		this.matrixY = matrixY;
		shouldWork = true;
		isAnEntity = false;
		score = POINT;

		if (type != CODE1) {
			picked = true;
		}

		if (status == 0) {
			gamePanel.getCollision_matrix()[matrixX][matrixY] = 1;
		} else {
			gamePanel.getCollision_parallel_matrix()[status - 1][matrixX][matrixY] = 1;
		}

	}

	public void update() {
		if (shouldWork) {
			if (!picked) {
				if (gamePanel.ninjaPlaying) {
					PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
					if (p != null) {
						if (y < p.y && new Rectangle(x, y, width, height + 1).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
							picked = true;
							MediaPlayer.playSound(Constants.brick_break, 0.5f);
							gamePanel.addScore(score, x, y);
							Iterator<Unit> iter = gamePanel.getUnits().iterator();
							while (iter.hasNext()) {
								Unit u = iter.next();
								if (u.alive && u.status == status && u.isAnEntity && new Rectangle(x, y, width, height).intersects(new Rectangle(u.x, u.y, u.width, u.height + 1))) {
									u.die();
									gamePanel.addScore(u.score, u.x, u.y);
								}
								u = null;
							}
							iter = null;
						}
					}
					p = null;
				}
				if (gamePanel.robotPlaying) {
					PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
					if (p != null) {
						if (y < p.y && new Rectangle(x, y, width, height + 1).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
							picked = true;
							MediaPlayer.playSound(Constants.brick_break, 0.5f);
							gamePanel.addScore(score, x, y);
							Iterator<Unit> iter = gamePanel.getUnits().iterator();
							while (iter.hasNext()) {
								Unit u = iter.next();
								if (u.alive && u.status == status && u.isAnEntity && new Rectangle(x, y, width, height).intersects(new Rectangle(u.x, u.y, u.width, u.height + 1))) {
									u.die();
									gamePanel.addScore(u.score, u.x, u.y);
								}
								u = null;
							}
							iter = null;
						}
					}
					p = null;
				}
				if (gamePanel.knightPlaying) {
					PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
					if (p != null) {
						if (y < p.y && new Rectangle(x, y, width, height + 1).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
							picked = true;
							MediaPlayer.playSound(Constants.brick_break, 0.5f);
							gamePanel.addScore(score, x, y);
							Iterator<Unit> iter = gamePanel.getUnits().iterator();
							while (iter.hasNext()) {
								Unit u = iter.next();
								if (u.alive && u.status == status && u.isAnEntity && new Rectangle(x, y, width, height).intersects(new Rectangle(u.x, u.y, u.width, u.height + 1))) {
									u.die();
									gamePanel.addScore(u.score, u.x, u.y);
								}
								u = null;
							}
							iter = null;
						}
					}
					p = null;
				}
			}
		}
	}

	public void draw(Graphics g) {
		if (shouldWork) {
			if (!picked) {
				g.drawImage(Constants.IMAGES[Constants.UNIT_ID_TO_IMAGE_ID(CODE1)], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
			} else {
				g.drawImage(Constants.IMAGES[Constants.UNIT_ID_TO_IMAGE_ID(CODE2)], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
			}
		}
	}

}
