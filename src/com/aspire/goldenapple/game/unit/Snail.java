package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.tools.Animation;
import com.aspire.goldenapple.tools.MediaPlayer;

public class Snail extends Unit {
	public static final int CODE = 49;

	private static final int WIDTH = 30;
	private static final int HEIGHT = 25;
	private static final int SPEED = 1;
	private static final int SHELL_SPEED = 9;
	private static final int POINT = 150;

	private static final int ANIM_DURATION = (int) Constants.TICK_PER_SEC / 4;

	private Animation anim_left;
	private Animation anim_right;
	private Animation anim_dead;
	private BufferedImage images_move_left[];
	private BufferedImage images_move_right[];
	private BufferedImage images_dead[];
	private int frameCount;
	private String type;
	private GamePanel gamePanel;
	private boolean goingRight, goingLeft;
	private boolean once;
	private boolean shell;
	private boolean converted;
	private int speed;

	public Snail(Program program, GamePanel gamePanel, int x, int y, int width, int height, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;

		alive = true;
		setGoingLeft(true);
		setGoingRight(false);
		shouldWork = true;
		isAnEntity = true;
		once = false;
		converted = false;
		setShell(false);

		width = WIDTH;
		height = HEIGHT;
		y += Constants.BRICK_SIZE - HEIGHT;
		frameCount = 2;
		score = POINT;

		images_move_left = new BufferedImage[frameCount];
		images_move_right = new BufferedImage[frameCount];
		images_dead = new BufferedImage[2];
		images_move_left[0] = Constants.IMAGES[98];
		images_move_left[1] = Constants.IMAGES[99];
		images_move_right[0] = Constants.IMAGES[100];
		images_move_right[1] = Constants.IMAGES[101];
		images_dead[0] = Constants.IMAGES[102];
		images_dead[1] = Constants.IMAGES[103];

		anim_left = new Animation(gamePanel, images_move_left, x, y, width, height, ANIM_DURATION, true);
		anim_right = new Animation(gamePanel, images_move_right, x, y, width, height, ANIM_DURATION, true);
		anim_dead = new Animation(gamePanel, images_dead, x, y, width, height, ANIM_DURATION * 3, true);

	}

	public void update() {
		if (shouldWork) {
			if (alive) {
				if (isShell())
					speed = SHELL_SPEED;
				else
					speed = SPEED;
				for (int i = 1; i <= speed; i++) {
					if (!once) {
						once = true;

						if (isShell()) {
							anim_dead.update();
						}
						if (status == 0) {
							if (isShell() && Engine.canMoveToBot(x, y, width, height)
									&& Engine.canMove(gamePanel.getCollision_matrix(), (x + width - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_matrix(), (x) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
								y++;
								anim_right.currentY++;
								anim_left.currentY++;
								anim_dead.currentY++;
							}
						} else {
							if (isShell() && Engine.canMoveToBotParallel(x, y, width, height)
									&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
								y++;
								anim_right.currentY++;
								anim_left.currentY++;
								anim_dead.currentY++;
							}
						}
						if (status == 0) {
							if (isGoingLeft()) {
								if (Engine.canMoveToLeft(x, y, width, height) && Engine.canMove(gamePanel.getCollision_matrix(), (x - 1) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
										&& Engine.canMove(gamePanel.getCollision_matrix(), (x - 1) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)
										&& (isShell() || (!isShell() && !Engine.canMove(gamePanel.getCollision_matrix(), (x - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)))) {

									x--;
									anim_right.currentX--;
									anim_left.currentX--;
									anim_dead.currentX--;
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
										&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x - 1) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)
										&& (isShell() || (!isShell()
												&& !Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)))) {

									x--;
									anim_right.currentX--;
									anim_left.currentX--;
									anim_dead.currentX--;
									anim_left.update();
								} else {
									setGoingLeft(false);
									setGoingRight(true);
								}
							}
						}

						if (status == 0) {
							if (isGoingRight()) {
								if (Engine.canMoveToRightParallel(x, y, width, height)
										&& Engine.canMove(gamePanel.getCollision_matrix(), (x + width) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
										&& Engine.canMove(gamePanel.getCollision_matrix(), (x + width) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)
										&& (isShell() || (!isShell() && !Engine.canMove(gamePanel.getCollision_matrix(), (x + width) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)))) {

									x++;
									anim_right.currentX++;
									anim_left.currentX++;
									anim_dead.currentX++;
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
										&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)
										&& (isShell() || (!isShell()
												&& !Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)))) {

									x++;
									anim_right.currentX++;
									anim_left.currentX++;
									anim_dead.currentX++;
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
								Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height);

								if (r1.intersects(r2)) {
									if (r1.y - r2.y > r1.height * 2 / 3) {
										if (!p.isJumping()) {
											if (!converted && !isShell()) {
												converted = true;
												setShell(true);
												gamePanel.addScore(POINT, x, y);
											}
											if (isShell() && converted) {
												p.jump();
												if (isGoingLeft() || isGoingRight()) {
													setGoingLeft(false);
													setGoingRight(false);
												} else {
													if (p.x >= x + width / 2) {
														setGoingLeft(true);
													} else {
														setGoingRight(true);
													}
												}
											}
										}
									} else {
										if (isShell() && !isGoingLeft() && !isGoingRight()) {
										} else {
											p.hurt(1);
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
								Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height);

								if (r1.intersects(r2)) {
									if (r1.y - r2.y > r1.height * 2 / 3) {
										if (!p.isJumping()) {
											if (!converted && !isShell()) {
												converted = true;
												setShell(true);
												gamePanel.addScore(POINT, x, y);
											}
											if (isShell() && converted) {
												p.jump();
												if (isGoingLeft() || isGoingRight()) {
													setGoingLeft(false);
													setGoingRight(false);
												} else {
													if (p.x >= x + width / 2) {
														setGoingLeft(true);
													} else {
														setGoingRight(true);
													}
												}
											}
										}
									} else {
										if (isShell() && !isGoingLeft() && !isGoingRight()) {
										} else {
											p.hurt(1);
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
								Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height);

								if (r1.intersects(r2)) {
									if (r1.y - r2.y > r1.height * 2 / 3) {
										if (!p.isJumping()) {
											if (!converted && !isShell()) {
												converted = true;
												setShell(true);
												gamePanel.addScore(POINT, x, y);
											}
											if (isShell() && converted) {
												p.jump();
												if (isGoingLeft() || isGoingRight()) {
													setGoingLeft(false);
													setGoingRight(false);
												} else {
													if (p.x >= x + width / 2) {
														setGoingLeft(true);
													} else {
														setGoingRight(true);
													}
												}
											}
										}
									} else {
										if (isShell() && !isGoingLeft() && !isGoingRight()) {
										} else {
											p.hurt(1);
										}
									}
								}
								r1 = null;
								r2 = null;
							}
						}

						if (isShell()) {
							Iterator<Unit> iter = gamePanel.getUnits().iterator();
							while (iter.hasNext()) {
								Unit u = iter.next();
								if (isGoingLeft()) {
									if (u instanceof Box && u.status == status) {
										Box b = (Box) u;
										if (!b.isBroken() && new Rectangle(x - 1, y, width, height).intersects(new Rectangle(b.x, b.y, b.width, b.height))) {
											b.die();
											setGoingLeft(false);
											setGoingRight(true);
										}
										b = null;
									}
								}
								if (isGoingRight()) {
									if (u instanceof Box && u.status == status) {
										Box b = (Box) u;
										if (!b.isBroken() && new Rectangle(x, y, width + 1, height).intersects(new Rectangle(b.x, b.y, b.width, b.height))) {
											b.die();
											setGoingLeft(true);
											setGoingRight(false);
										}
										b = null;
									}
								}
								if (new Rectangle(x, y, width, height).intersects(new Rectangle(u.x, u.y, u.width, u.height))) {
									if (u instanceof Snail && u.status == status && u.alive && (isGoingLeft() || isGoingRight())) {
										Snail s = (Snail) u;
										if (s != this) {
											if (!s.isShell()) {
												s.die();
												gamePanel.addScore(POINT, x, y);
											} else {
												if (isGoingLeft()) {
													s.setGoingRight(false);
													s.setGoingLeft(true);
													setGoingLeft(false);
													setGoingRight(true);
												} else {
													s.setGoingLeft(false);
													s.setGoingRight(true);
													setGoingLeft(true);
													setGoingRight(false);
												}
											}
										}
										s = null;
									}
								}
								u = null;
							}
							iter = null;
						}
					} else {
						once = false;
					}
				}
			}
		}
	}

	public void die() {
		if (alive) {
			MediaPlayer.playSound(Constants.snail_dead);
			alive = false;
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			if (alive) {
				if (isShell()) {
					anim_dead.draw(g);
				} else {
					if (isGoingLeft()) {
						anim_left.draw(g);
					}
					if (isGoingRight()) {
						anim_right.draw(g);
					}
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

	public boolean isShell() {
		return shell;
	}

	public void setShell(boolean shell) {
		this.shell = shell;
	}

}
