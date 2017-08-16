package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.tools.Animation;
import com.aspire.goldenapple.tools.MediaPlayer;

public class Fly extends Unit {
	public static final int CODE = 52;

	public static final int WIDTH = 30;
	public static final int HEIGHT = 24;

	private static final int SPEED = 2;
	private static final int POINT = 250;

	private static final int ANIM_DURATION = (int) Constants.TICK_PER_SEC / 2;

	private static final int SENSIVITY = 3;

	private Animation anim_left;
	private Animation anim_right;
	private Animation anim_dead_left;
	private Animation anim_dead_right;
	private BufferedImage images_move_left[];
	private BufferedImage images_move_right[];
	private BufferedImage images_dead_left[];
	private BufferedImage images_dead_right[];
	private int frameCount;
	private String type;
	private GamePanel gamePanel;
	private boolean goingRight, goingLeft;

	public Fly(Program program, GamePanel gamePanel, int x, int y, int width, int height, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;

		alive = true;
		setGoingLeft(false);
		setGoingRight(true);
		shouldWork = true;
		isAnEntity = true;

		this.width = WIDTH;
		this.height = HEIGHT;
		// y += Constants.BRICK_SIZE - HEIGHT;

		score = POINT;

		frameCount = 4;
		images_move_left = new BufferedImage[frameCount];
		images_move_right = new BufferedImage[frameCount];
		images_dead_left = new BufferedImage[frameCount - 1];
		images_dead_right = new BufferedImage[frameCount - 1];

		images_move_right[0] = Constants.IMAGES[499];
		images_move_right[1] = Constants.IMAGES[500];
		images_move_right[2] = Constants.IMAGES[501];
		images_move_right[3] = Constants.IMAGES[500];

		images_move_left[0] = Constants.IMAGES[502];
		images_move_left[1] = Constants.IMAGES[503];
		images_move_left[2] = Constants.IMAGES[504];
		images_move_left[3] = Constants.IMAGES[503];

		images_dead_right[0] = Constants.IMAGES[499];
		images_dead_right[1] = Constants.IMAGES[500];
		images_dead_right[2] = Constants.IMAGES[501];

		images_dead_left[0] = Constants.IMAGES[502];
		images_dead_left[1] = Constants.IMAGES[503];
		images_dead_left[2] = Constants.IMAGES[504];

		anim_left = new Animation(gamePanel, images_move_left, x, y, this.width, this.height, ANIM_DURATION, true);
		anim_right = new Animation(gamePanel, images_move_right, x, y, this.width, this.height, ANIM_DURATION, true);
		anim_dead_right = new Animation(gamePanel, images_dead_right, x, y, this.width, this.height, ANIM_DURATION / 2, false);
		anim_dead_left = new Animation(gamePanel, images_dead_left, x, y, this.width, this.height, ANIM_DURATION / 2, false);

	}

	public void update() {
		if (shouldWork) {
			if (alive) {
				for (int i = 1; i <= SPEED; i++) {
					if (status == 0) {
						if (isGoingLeft()) {
							if (Engine.canMoveToLeft(x, y, width, height) && Engine.canMove(gamePanel.getCollision_matrix(), (x - 1) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_matrix(), (x - 1) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)) {

								x--;
								anim_right.currentX--;
								anim_left.currentX--;
								anim_dead_left.currentX--;
								anim_dead_right.currentX--;
								anim_left.update();
							} else {
								setGoingLeft(false);
								setGoingRight(true);
							}
						}
					} else {
						if (isGoingLeft()) {
							if (Engine.canMoveToLeft(x, y, width, height)
									&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x - 1) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x - 1) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)) {

								x--;
								anim_right.currentX--;
								anim_left.currentX--;
								anim_dead_left.currentX--;
								anim_dead_right.currentX--;
								anim_left.update();
							} else {
								setGoingLeft(false);
								setGoingRight(true);
							}
						}
					}

					if (status == 0) {
						if (isGoingRight()) {
							if (Engine.canMoveToRightParallel(x, y, width, height) && Engine.canMove(gamePanel.getCollision_matrix(), (x + width) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_matrix(), (x + width) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)) {

								x++;
								anim_right.currentX++;
								anim_left.currentX++;
								anim_dead_left.currentX++;
								anim_dead_right.currentX++;
								anim_right.update();

							} else {
								setGoingLeft(true);
								setGoingRight(false);
							}
						}
					} else {
						if (isGoingRight()) {
							if (Engine.canMoveToRight(x, y, width, height)
									&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)) {

								x++;
								anim_right.currentX++;
								anim_left.currentX++;
								anim_dead_left.currentX++;
								anim_dead_right.currentX++;
								anim_right.update();

							} else {
								setGoingLeft(true);
								setGoingRight(false);
							}
						}
					}
					if (gamePanel.ninjaPlaying) {
						PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
						if (p != null) {
							Rectangle r1 = new Rectangle(x, y, width, height);
							Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height + 3);

							if (new Rectangle(x + SENSIVITY, y + SENSIVITY, width - SENSIVITY * 2, height - SENSIVITY * 2).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
								p.hurt(1);
							}

							if (r1.intersects(r2)) {
								if (r1.y - r2.y > r1.height * 2 / 3) {
									if (!p.isJumping()) {
										p.jump();
										p.setAffectedByForce(true);

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
							Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height + 3);

							if (new Rectangle(x + SENSIVITY, y + SENSIVITY, width - SENSIVITY * 2, height - SENSIVITY * 2).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
								p.hurt(1);
							}

							if (r1.intersects(r2)) {
								if (r1.y - r2.y > r1.height * 2 / 3) {
									if (!p.isJumping()) {
										p.jump();
										p.setAffectedByForce(true);

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
							Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height + 3);

							if (new Rectangle(x + SENSIVITY, y + SENSIVITY, width - SENSIVITY * 2, height - SENSIVITY * 2).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
								p.hurt(1);
							}

							if (r1.intersects(r2)) {
								if (r1.y - r2.y > r1.height * 2 / 3) {
									if (!p.isJumping()) {
										p.jump();
										p.setAffectedByForce(true);

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
			} else {
				if (goingLeft) {
					anim_dead_left.update();
				} else {
					anim_dead_right.update();
				}
				if (!anim_dead_left.shouldAnimate || !anim_dead_right.shouldAnimate) {
					shouldWork = false;
				}
			}
		}
	}

	public void die() {
		if (alive) {
			MediaPlayer.playSound(Constants.fly_dead);
			alive = false;
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			if (alive) {
				if (goingLeft) {
					anim_left.draw(g);
				} else if (goingRight) {
					anim_right.draw(g);
				}
			} else {
				if (goingLeft) {
					anim_dead_left.draw(g);
				} else if (goingRight) {
					anim_dead_right.draw(g);
				}
			}
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isGoingRight() {
		return goingRight;
	}

	public void setGoingRight(boolean goingRight) {
		this.goingRight = goingRight;
	}

	public boolean isGoingLeft() {
		return goingLeft;
	}

	public void setGoingLeft(boolean goingLeft) {
		this.goingLeft = goingLeft;
	}

}
