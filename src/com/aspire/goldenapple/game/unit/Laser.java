package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import java.util.Iterator;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.GamePanel;

public class Laser extends Unit {
	public static final int CODE1 = 1, CODE2 = 2, CODE3 = 3, CODE4 = 4;
	public static final int DISTANCE = Constants.BRICK_SIZE * 200;

	private static final int SPEED = 6;

	private GamePanel gamePanel;
	private int type;
	private int speed;
	private int startX, startY;
	private int targetX, targetY;
	private int distance;

	public Laser(Program program, GamePanel gamePanel, int x, int y, int width, int height, int type, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;
		this.type = type;
		shouldWork = true;
		isAnEntity = false;

		speed = SPEED;
		matrixX = (x + width / 2) / Constants.BRICK_SIZE;
		matrixX = (y + height / 2) / Constants.BRICK_SIZE;
		switch (type) {
		case 4:
			targetX = Engine.safeX(x);
			targetY = Engine.safeY(y - DISTANCE);
			break;
		case 3:
			speed = SPEED;
			targetX = Engine.safeX(x + DISTANCE);
			targetY = Engine.safeY(y);
			break;
		case 1:
			speed = SPEED;
			targetX = Engine.safeX(x);
			targetY = Engine.safeY(y + DISTANCE);
			break;
		case 2:
			speed = SPEED;
			targetX = Engine.safeX(x - DISTANCE);
			targetY = Engine.safeY(y);
			break;

		}
	}

	public void update() {
		if (shouldWork) {
			for (int i = 1; i <= speed; i++) {
				switch (type) {
				case 4:
					if (y > targetY) {
						y--;
					} else {
						shouldWork = false;
						return;
					}
					break;
				case 3:
					if (x <= targetX) {
						x++;
					} else {
						shouldWork = false;
						return;
					}
					break;
				case 1:
					if (y <= targetY) {
						y++;
					} else {
						shouldWork = false;
						return;
					}
					break;
				case 2:
					if (x > targetX) {
						x--;
					} else {
						shouldWork = false;
						return;
					}
					break;

				}
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
					if (u instanceof Box) {
						Box b = (Box) u;
						if (new Rectangle(x - 1, y - 1, width + 2, height + 2).intersects(new Rectangle(b.x, b.y, b.width, b.height))) {
							b.die();
						}
						b = null;
					}
					if (u != this && u.isAnEntity && u.alive && (new Rectangle(x, y, width, height).intersects(new Rectangle(u.x, u.y, u.width, u.height)))) {
						u.die();
					}
					u = null;
				}
				iter = null;

				matrixX = (x + width / 2) / Constants.BRICK_SIZE;
				matrixY = (y + height / 2) / Constants.BRICK_SIZE;

				if (status == 0) {
					if (!Engine.canMove(gamePanel.getCollision_matrix(), matrixX, matrixY)) {
						shouldWork = false;
					}
				} else {
					if (!Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], matrixX, matrixY)) {
						shouldWork = false;
					}
				}

			}
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			if (type == CODE1 || type == CODE4) {
				g.drawImage(Constants.IMAGES[384], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
			}
			if (type == CODE2 || type == CODE3) {
				g.drawImage(Constants.IMAGES[385], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
			}
		}
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

}
