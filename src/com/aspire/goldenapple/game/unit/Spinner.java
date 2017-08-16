package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.tools.Animation;

public class Spinner extends Unit {
	public static final int CODE = 53;

	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;
	private static final int SPEED = 3;

	private static final int ANIM_DURATION = (int) Constants.TICK_PER_SEC / 4;

	private Animation anim;
	private BufferedImage images_move[];
	private int frameCount;
	private String type;
	private GamePanel gamePanel;
	private boolean goingUp, goingDown;

	public Spinner(Program program, GamePanel gamePanel, int x, int y, int width, int height, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;

		alive = true;
		goingDown = true;
		goingUp = false;
		shouldWork = true;
		isAnEntity = true;

		this.width = WIDTH;
		this.height = HEIGHT;

		frameCount = 2;

		images_move = new BufferedImage[frameCount];
		images_move[0] = Constants.IMAGES[52];
		images_move[1] = Constants.IMAGES[505];

		anim = new Animation(gamePanel, images_move, x, y, this.width, this.height, ANIM_DURATION, true);

	}

	public void update() {
		if (shouldWork) {
			if (alive) {
				for (int i = 1; i <= SPEED; i++) {
					if (status == 0) {
						if (goingUp) {
							if (Engine.canMoveToTop(x, y, width, height) && Engine.canTop(gamePanel.getCollision_matrix(), (x + width - 1) / Constants.BRICK_SIZE, (y - 1) / Constants.BRICK_SIZE)
									&& Engine.canTop(gamePanel.getCollision_matrix(), (x) / Constants.BRICK_SIZE, (y - 1) / Constants.BRICK_SIZE)) {
								y--;
								anim.currentY--;
							} else {
								goingDown = true;
								goingUp = false;

							}
						}
						if (goingDown) {
							if (Engine.canMoveToBot(x, y, width, height) && Engine.canMove(gamePanel.getCollision_matrix(), (x + width - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_matrix(), (x) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
								y++;
								anim.currentY++;
							} else {
								goingDown = false;
								goingUp = true;

							}
						}
					} else {
						if (goingUp) {
							if (Engine.canMoveToTop(x, y, width, height) && Engine.canMove(gamePanel.getCollision_matrix(), (x + width - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_matrix(), (x) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
								y--;
								anim.currentY--;
							} else {
								goingDown = true;
								goingUp = false;

							}
						}
						if (goingDown) {
							if (Engine.canMoveToBotParallel(x, y, width, height)
									&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
								y++;
								anim.currentY++;
							} else {
								goingDown = false;
								goingUp = true;
							}
						}
					}

					if (gamePanel.ninjaPlaying) {
						PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
						if (Engine.isInside(new Rectangle(x, y, width, height), new Rectangle(p.x, p.y, p.width, p.height))) {
							p.hurt(1);
						}
						p = null;
					}
					if (gamePanel.robotPlaying) {
						PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
						if (Engine.isInside(new Rectangle(x, y, width, height), new Rectangle(p.x, p.y, p.width, p.height))) {
							p.hurt(1);
						}
						p = null;
					}
					if (gamePanel.knightPlaying) {
						PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
						if (Engine.isInside(new Rectangle(x, y, width, height), new Rectangle(p.x, p.y, p.width, p.height))) {
							p.hurt(1);
						}
						p = null;
					}

					anim.update();
				}
			}
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			anim.draw(g);
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
