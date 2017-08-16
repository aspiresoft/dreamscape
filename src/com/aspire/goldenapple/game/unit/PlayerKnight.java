package com.aspire.goldenapple.game.unit;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.gui.state.PlayerState;
import com.aspire.goldenapple.io.ConfigData;
import com.aspire.goldenapple.io.InputHandler;
import com.aspire.goldenapple.tools.Animation;
import com.aspire.goldenapple.tools.MediaPlayer;

public class PlayerKnight extends Unit {
	public static final int CODE = 56;

	public static final int WIDTH = Constants.BRICK_SIZE * 2 / 3;
	public static final int HEIGHT = Constants.BRICK_SIZE;
	public static final int SPEED = 1;

	private static final int MAX_JUMP_HEIGHT = Constants.BRICK_SIZE * 4;
	private static final long INVUL_DURATION = (int) Constants.TICK_PER_SEC * 3;
	private static final int SPRING_MAX_MOVE = Constants.BRICK_SIZE * 12;
	private static final int SPRING_SPEED = 20;

	private static final int ANIM_DURATION = (int) Constants.TICK_PER_SEC / 2;
	private static final int FIRE_COOLDOWN = (int) Constants.TICK_PER_SEC / 2;
	private static final int FORCE_DURATION = (int) Constants.TICK_PER_SEC / 4;

	private boolean canBeControlled;
	private boolean jumping;
	private boolean falling;
	private boolean canJump;

	private int moveSpeed;
	private int jumpSpeed;
	private int fallSpeed;
	private int climbSpeed;

	public boolean active;

	private ArrayList<Kunai> kunais;

	private int currentHeight;
	private int ammo;
	private GamePanel gamePanel;
	private int x2, y2;
	private int matrixX2, matrixY2;
	private Point solUst, solAlt, sagUst, sagAlt;
	private Point solUstMatrix, solAltMatrix, sagUstMatrix, sagAltMatrix;
	private boolean goingLeft, goingRight, goingTop, goingBot;
	private static int pX, pY;
	private int cX, cY;
	private int ticks;
	private int invulTicks;
	private int kunaiTicks;
	private int forceTicks;
	private boolean once;
	private boolean invulnerable;
	private boolean flash;
	private int springMovement;
	private int springMoveAmount;
	private boolean isOnLadder;
	private boolean direction;
	private boolean isMoving;
	private boolean affectedByForce;
	private Animation idleRight;
	private Animation idleLeft;
	private Animation moveRight;
	private Animation moveLeft;
	private Animation jumpRight;
	private Animation jumpLeft;
	private Animation attack;
	private Animation toss;
	private Animation glideRight;
	private Animation glideLeft;
	private Animation climb;

	private boolean mainP;

	private int[] keys;

	public PlayerKnight(Program program, GamePanel gamePanel, int x, int y, int width, int height, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;
		currentX = x;
		currentY = y;
		setpX(x);
		setpY(y);
		setOnLadder(false);
		alive = true;
		setCanBeControlled(true);
		setInvulnerable(false);
		shouldDraw = true;
		setAffectedByForce(false);
		shouldUpdate = true;
		setFalling(false);
		setJumping(false);
		direction = true;
		canJump = true;
		goingBot = false;
		goingLeft = false;
		goingRight = false;
		goingTop = false;
		isAnEntity = true;
		setFlash(false);
		currentHeight = 0;
		setAmmo(0);
		setSolAlt(new Point());
		setSolUst(new Point());
		setSagAlt(new Point());
		setSagUst(new Point());
		setSolAltMatrix(new Point());
		setSolUstMatrix(new Point());
		setSagAltMatrix(new Point());
		setSagUstMatrix(new Point());
		keys = new int[14];
		score = 0;
		shouldWork = true;
		kunais = new ArrayList<Kunai>();
		active = false;

		ticks = 0;
		forceTicks = 0;
		invulTicks = 0;
		kunaiTicks = 0;

		moveSpeed = 2;
		jumpSpeed = 3;
		fallSpeed = 3;
		climbSpeed = 3;

		BufferedImage[] frames = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			frames[i] = Constants.IMAGES[601 + i];
		}
		climb = new Animation(gamePanel, frames, x, y, width, height, ANIM_DURATION, true);
		frames = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			frames[i] = Constants.IMAGES[611 + i];
		}
		glideLeft = new Animation(gamePanel, frames, x, y, width, height, ANIM_DURATION, true);
		frames = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			frames[i] = Constants.IMAGES[621 + i];
		}
		glideRight = new Animation(gamePanel, frames, x, y, width, height, ANIM_DURATION, true);
		frames = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			frames[i] = Constants.IMAGES[631 + i];
		}
		idleLeft = new Animation(gamePanel, frames, x, y, width, height, ANIM_DURATION, true);
		frames = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			frames[i] = Constants.IMAGES[641 + i];
		}
		idleRight = new Animation(gamePanel, frames, x, y, width, height, ANIM_DURATION, true);
		frames = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			frames[i] = Constants.IMAGES[651 + i];
		}
		jumpLeft = new Animation(gamePanel, frames, x, y, width, height, ANIM_DURATION, true);
		frames = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			frames[i] = Constants.IMAGES[661 + i];
		}
		jumpRight = new Animation(gamePanel, frames, x, y, width, height, ANIM_DURATION, true);
		frames = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			frames[i] = Constants.IMAGES[671 + i];
		}
		moveLeft = new Animation(gamePanel, frames, x, y, width, height, ANIM_DURATION, true);
		frames = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			frames[i] = Constants.IMAGES[681 + i];
		}
		moveRight = new Animation(gamePanel, frames, x, y, width, height, ANIM_DURATION, true);
	}

	public void update() {
		if (shouldUpdate) {
			ticks++;
			if (alive) {
				if (PlayerState.currentHealth <= 0 && !gamePanel.getLevel().isTestLevel()) {
					kill();
					return;
				}
				if (ticks - invulTicks >= INVUL_DURATION) {
					setInvulnerable(false);
					invulTicks = ticks;
				}
				setpX(x);
				setpY(y);
				cX = getpX() + width / 2;
				cY = getpY() + height / 2;

				boolean right = InputHandler.keys[mainP ? keys[3] : keys[10]];
				boolean left = InputHandler.keys[mainP ? keys[2] : keys[9]];
				boolean up = InputHandler.keys[mainP ? keys[0] : keys[7]];
				boolean down = InputHandler.keys[mainP ? keys[1] : keys[8]];
				boolean space = InputHandler.keys[mainP ? keys[5] : keys[12]];
				boolean fire = InputHandler.keys[mainP ? keys[4] : keys[11]];
				boolean focus = InputHandler.keys[mainP ? keys[6] : keys[13]];

				

				if (focus) {
					focus = false;
					if (gamePanel.knightPlaying && !gamePanel.knightFocus) {
						gamePanel.ninjaFocus = false;
						gamePanel.knightFocus = true;
						gamePanel.robotFocus = false;
					}
				}

				if (ticks - forceTicks >= FORCE_DURATION) {
					forceTicks = ticks;
					setAffectedByForce(false);
				}
				if (right) {
					goingRight = true;
					goingLeft = false;
					isMoving = true;
					direction = true;
				} else {
					goingRight = false;
				}
				if (left) {
					direction = false;
					goingLeft = true;
					goingRight = false;
					isMoving = true;
				} else {
					goingLeft = false;
				}

				for (int i = 1; i <= moveSpeed; i++) {
					handleAnimationPositions();
					if (goingLeft) {
						goingRight = false;
						if (status == 0) {
							if (Engine.canMoveToLeft(x, y, width, height) && Engine.canMove(gamePanel.getCollision_matrix(), (x - 1) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_matrix(), (x - 1) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)) {
								x--;
								direction = false;
								goingLeft = true;
								isMoving = true;

							} else {
								goingLeft = false;
							}
						} else {
							if (Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x - 1) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x - 1) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)) {
								goingLeft = true;
								isMoving = true;
								direction = false;
								x--;
							} else {
								goingLeft = false;
							}
						}
					}
					if (goingRight) {
						if (status == 0) {
							goingLeft = false;
							if (Engine.canMoveToRight(x, y, width, height) && Engine.canMove(gamePanel.getCollision_matrix(), (x + width) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_matrix(), (x + width) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)) {
								x++;
								goingRight = true;
								isMoving = true;
								direction = true;
							} else {
								goingRight = false;
							}
						} else {
							if (Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)) {
								x++;
								goingRight = true;
								isMoving = true;
								direction = true;
							} else {
								goingRight = false;
							}
						}
					}
				}

				if (isOnLadder()) {
					isMoving = true;
					for (int i = 1; i <= climbSpeed; i++) {
						if (up) {
							isMoving = true;
							down = false;
							if (status == 0) {
								if (Engine.canMoveToTop(x, y, width, height) && Engine.canTop(gamePanel.getCollision_matrix(), (x + width - 1) / Constants.BRICK_SIZE, (y - 1) / Constants.BRICK_SIZE)
										&& Engine.canTop(gamePanel.getCollision_matrix(), (x) / Constants.BRICK_SIZE, (y - 1) / Constants.BRICK_SIZE)) {
									y--;
									isMoving = true;
								}
							} else {
								if (Engine.canMoveToTop(x, y, width, height)
										&& Engine.canMove(gamePanel.getCollision_matrix(), (x + width - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)
										&& Engine.canMove(gamePanel.getCollision_matrix(), (x) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
									y--;
									isMoving = true;
								}
							}
						}
						if (down) {
							up = false;
							isMoving = true;
							if (status == 0) {
								if (Engine.canMoveToBot(x, y, width, height)
										&& Engine.canMove(gamePanel.getCollision_matrix(), (x + width - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)
										&& Engine.canMove(gamePanel.getCollision_matrix(), (x) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
									y++;
									isMoving = true;
								}
							} else {
								if (Engine.canMoveToBotParallel(x, y, width, height)
										&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)
										&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
									y++;
									isMoving = true;
								}
							}
						}
					}
					if (!up && !down) {
						isMoving = false;
					}
				} else {
					if (status == 0) {
						if (isJumping()) {
							for (int i = 1; i <= jumpSpeed; i++) {
								goingBot = false;
								if (Engine.canMoveToTop(x, y, width, height) && Engine.canTop(gamePanel.getCollision_matrix(), (x + width - 1) / Constants.BRICK_SIZE, (y - 1) / Constants.BRICK_SIZE)
										&& Engine.canTop(gamePanel.getCollision_matrix(), (x) / Constants.BRICK_SIZE, (y - 1) / Constants.BRICK_SIZE)) {
									if (currentHeight <= MAX_JUMP_HEIGHT) {
										y--;
										isMoving = true;
										currentHeight++;
										goingTop = true;
									} else {
										setJumping(false);
										goingTop = false;
									}
								} else {
									setJumping(false);
									goingTop = false;
								}
							}
						} else {
							goingTop = false;
							for (int i = 1; i <= fallSpeed; i++) {
								if (Engine.canMoveToBot(x, y, width, height)
										&& Engine.canMove(gamePanel.getCollision_matrix(), (x + width - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)
										&& Engine.canMove(gamePanel.getCollision_matrix(), (x) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
									y++;
									goingBot = true;
									isMoving = true;
								} else {
									canJump = true;
									goingBot = false;
								}
							}
						}
					} else {

						if (isJumping()) {
							for (int i = 1; i <= jumpSpeed; i++) {
								goingBot = false;
								if (Engine.canMoveToTop(x, y, width, height)
										&& Engine.canTop(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width - 1) / Constants.BRICK_SIZE, (y - 1) / Constants.BRICK_SIZE)
										&& Engine.canTop(gamePanel.getCollision_parallel_matrix()[status - 1], (x) / Constants.BRICK_SIZE, (y - 1) / Constants.BRICK_SIZE)) {
									if (currentHeight <= MAX_JUMP_HEIGHT) {
										y--;
										isMoving = true;
										currentHeight++;
										goingTop = true;
									} else {
										setJumping(false);
										goingTop = false;
									}
								} else {
									setJumping(false);
									goingTop = false;
								}
							}

						} else {
							goingTop = false;
							for (int i = 1; i <= fallSpeed; i++) {
								if (Engine.canMoveToBotParallel(x, y, width, height)
										&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)
										&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
									y++;
									isMoving = true;
									goingBot = true;
								} else {
									canJump = true;
									goingBot = false;
								}
							}
						}
					}
				}

				if (space) {
					if (!isJumping()) {
						if (canJump) {
							canJump = false;
							setJumping(true);
							currentHeight = 0;
						}
					}
				} else {
					if (!isAffectedByForce()) {
						setJumping(false);
						currentHeight = 0;
					}
				}
				if (!isOnLadder() && !goingLeft && !goingRight && !goingBot && !goingTop) {
					isMoving = false;
				}
				if (isOnLadder()) {
					if (isMoving) {
						climb.update();
					} else {
						// dont climb just stay
					}
				} else {
					if (!goingBot && !goingTop) {
						if (goingLeft) {
							moveLeft.update();
						}
						if (goingRight) {
							moveRight.update();
						}
					} else {
						if (goingTop) {
							if (!direction) {
								jumpLeft.update();
							} else {
								jumpRight.update();
							}
						} else {
							if (!direction) {
								glideLeft.update();
							} else {
								glideRight.update();
							}
						}
					}
					if (!isMoving) {
						if (!direction) {
							idleLeft.update();
						} else {
							idleRight.update();
						}
					}
				}
				if (!isMoving) {
					if (status == 0) {
						if (gamePanel.getCollision_matrix()[(x + width / 2) / Constants.BRICK_SIZE][(y + height / 2) / Constants.BRICK_SIZE] == 2) {
							y = Engine.safeY(y - Constants.BRICK_SIZE);
						}
					} else {
						if (gamePanel.getCollision_parallel_matrix()[status - 1][(x + width / 2) / Constants.BRICK_SIZE][(y + height / 2) / Constants.BRICK_SIZE] == 2) {
							y = Engine.safeY(y - Constants.BRICK_SIZE);
						}
					}
				}
				if (getSpringMovement() != 0) {
					if (getSpringMovement() == 1) {
						if (getSpringMoveAmount() <= SPRING_MAX_MOVE) {
							for (int k = 1; k < SPRING_SPEED; k++) {
								setJumping(false);
								canJump = false;
								if (status == 0) {
									if (Engine.canMoveToTop(x, y, width, height)
											&& Engine.canTop(gamePanel.getCollision_matrix(), (x + width - 1) / Constants.BRICK_SIZE, (y - 1) / Constants.BRICK_SIZE)
											&& Engine.canTop(gamePanel.getCollision_matrix(), (x) / Constants.BRICK_SIZE, (y - 1) / Constants.BRICK_SIZE)) {
										y--;
									}
								} else {
									if (Engine.canMoveToTop(x, y, width, height)
											&& Engine.canTop(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width - 1) / Constants.BRICK_SIZE, (y - 1) / Constants.BRICK_SIZE)
											&& Engine.canTop(gamePanel.getCollision_parallel_matrix()[status - 1], (x) / Constants.BRICK_SIZE, (y - 1) / Constants.BRICK_SIZE)) {
										y--;
									}
								}
								setSpringMoveAmount(getSpringMoveAmount() + 1);
							}
						}
					}
					if (getSpringMovement() == 2) {
						if (getSpringMoveAmount() <= SPRING_MAX_MOVE) {
							for (int k = 1; k < SPRING_SPEED; k++) {
								if (status == 0) {
									if (Engine.canMoveToRight(x, y, width, height) && Engine.canMove(gamePanel.getCollision_matrix(), (x + width) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
											&& Engine.canMove(gamePanel.getCollision_matrix(), (x + width) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)) {
										x++;
										if (x >= Constants.LEVEL_LEFT_MOST && x < Constants.LEVEL_RIGHT_MOST) {
											gamePanel.setX(gamePanel.getX() + 1);
										}
									}
								} else {
									if (Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
											&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)) {
										x++;
									}
								}
								setSpringMoveAmount(getSpringMoveAmount() + 1);
							}
						}
					}
					if (getSpringMovement() == 3) {
						if (getSpringMoveAmount() <= SPRING_MAX_MOVE) {
							for (int k = 1; k < SPRING_SPEED; k++) {
								if (status == 0) {
									if (Engine.canMoveToBot(x, y, width, height)
											&& Engine.canMove(gamePanel.getCollision_matrix(), (x + width - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)
											&& Engine.canMove(gamePanel.getCollision_matrix(), (x) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
										y++;
									}
								} else {
									if (Engine.canMoveToBotParallel(x, y, width, height)
											&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)
											&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
										y++;
									}
								}
								setSpringMoveAmount(getSpringMoveAmount() + 1);
							}
						}
					}
					if (getSpringMovement() == 4) {
						if (getSpringMoveAmount() <= SPRING_MAX_MOVE) {
							for (int k = 1; k < SPRING_SPEED; k++) {
								if (status == 0) {
									if (Engine.canMoveToLeft(x, y, width, height) && Engine.canMove(gamePanel.getCollision_matrix(), (x - 1) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
											&& Engine.canMove(gamePanel.getCollision_matrix(), (x - 1) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)) {
										x--;
										if (x >= Constants.LEVEL_LEFT_MOST && x < Constants.LEVEL_RIGHT_MOST)
											gamePanel.setX(gamePanel.getX() - 1);
									}
								} else {
									if (Engine.canMoveToLeft(x, y, width, height)
											&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x - 1) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
											&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x - 1) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)) {
										x--;
									}
								}
								setSpringMoveAmount(getSpringMoveAmount() + 1);
							}
						}
					}
				}
			}
			Iterator<Kunai> iter = kunais.iterator();
			while (iter.hasNext()) {
				Kunai k = iter.next();
				if (!k.shouldWork) {
					iter.remove();
				} else {
					k.update();
				}
				k = null;
			}
			iter = null;
		}

	}

	public void draw(Graphics g) {
		if (shouldDraw) {
			Iterator<Kunai> iter = kunais.iterator();
			while (iter.hasNext()) {
				Kunai k = iter.next();
				k.draw(g);
				k = null;
			}
			iter = null;
			if (isInvulnerable()) {
				if (once) {
					once = false;
				} else {
					once = true;
				}
			}
			if (!isInvulnerable() || (isInvulnerable() && once)) {
				if (isOnLadder()) {
					climb.draw(g);
				} else {
					if (!goingBot && !goingTop) {
						if (goingLeft) {
							moveLeft.draw(g);
						}
						if (goingRight) {
							moveRight.draw(g);
						}
					} else {
						if (goingTop) {
							if (!direction) {
								jumpLeft.draw(g);
							} else {
								jumpRight.draw(g);
							}
						} else {
							if (!direction) {
								glideLeft.draw(g);
							} else {
								glideRight.draw(g);
							}
						}
					}
					if (!isMoving) {
						if (!direction) {
							idleLeft.draw(g);
						} else {
							idleRight.draw(g);
						}
					}
				}

			}
		}
	}

	public void jump() {
		currentHeight = 0;
		canJump = false;
		goingBot = false;
		goingTop = true;
		setJumping(true);
		setAffectedByForce(true);
		forceTicks = ticks;
	}

	public void fire() {
		int x, y;
		if (direction) {
			x = this.x + this.width;
		} else {
			x = this.x;
		}
		y = this.y + this.height / 2;
		if (getAmmo() > 0) {
			setAmmo(getAmmo() - 1);
			kunais.add(new Kunai(program, gamePanel, x, y, Constants.BRICK_SIZE / 2, Constants.BRICK_SIZE / 2, direction, status, priority));
			MediaPlayer.playSound(Constants.kunai_throw);
		}
	}

	public void hurt(int amount) {
		if (!isInvulnerable()) {
			MediaPlayer.playSound(Constants.player_hurt);
			setInvulnerable(true);
			PlayerState.currentHealth -= amount;
			invulTicks = ticks;
		}
	}

	public void kill() {
		PlayerState.currentHealth = 0;
		alive = false;
		gamePanel.setLost(true);
		if (gamePanel.getLevel().isTestLevel()) {
			gamePanel.getLevel().setExit(true);
		}
	}

	private void handleAnimationPositions() {
		jumpLeft.currentX = x;
		jumpLeft.currentY = y;
		jumpRight.currentX = x;
		jumpRight.currentY = y;

		idleLeft.currentX = x;
		idleLeft.currentY = y;
		idleRight.currentX = x;
		idleRight.currentY = y;

		moveLeft.currentX = x;
		moveLeft.currentY = y;
		moveRight.currentX = x;
		moveRight.currentY = y;

		glideLeft.currentX = x;
		glideLeft.currentY = y;
		glideRight.currentX = x;
		glideRight.currentY = y;

		climb.currentX = x;
		climb.currentY = y;
	}

	public boolean isCanBeControlled() {
		return canBeControlled;
	}

	public void setCanBeControlled(boolean canBeControlled) {
		this.canBeControlled = canBeControlled;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getMatrixX2() {
		return matrixX2;
	}

	public void setMatrixX2(int matrixX2) {
		this.matrixX2 = matrixX2;
	}

	public int getMatrixY2() {
		return matrixY2;
	}

	public void setMatrixY2(int matrixY2) {
		this.matrixY2 = matrixY2;
	}

	public Point getSolUst() {
		return solUst;
	}

	public void setSolUst(Point solUst) {
		this.solUst = solUst;
	}

	public Point getSolAlt() {
		return solAlt;
	}

	public void setSolAlt(Point solAlt) {
		this.solAlt = solAlt;
	}

	public Point getSagUst() {
		return sagUst;
	}

	public void setSagUst(Point sagUst) {
		this.sagUst = sagUst;
	}

	public Point getSagAlt() {
		return sagAlt;
	}

	public void setSagAlt(Point sagAlt) {
		this.sagAlt = sagAlt;
	}

	public Point getSolUstMatrix() {
		return solUstMatrix;
	}

	public void setSolUstMatrix(Point solUstMatrix) {
		this.solUstMatrix = solUstMatrix;
	}

	public Point getSolAltMatrix() {
		return solAltMatrix;
	}

	public void setSolAltMatrix(Point solAltMatrix) {
		this.solAltMatrix = solAltMatrix;
	}

	public Point getSagUstMatrix() {
		return sagUstMatrix;
	}

	public void setSagUstMatrix(Point sagUstMatrix) {
		this.sagUstMatrix = sagUstMatrix;
	}

	public Point getSagAltMatrix() {
		return sagAltMatrix;
	}

	public void setSagAltMatrix(Point sagAltMatrix) {
		this.sagAltMatrix = sagAltMatrix;
	}

	public boolean isFlash() {
		return flash;
	}

	public void setFlash(boolean flash) {
		this.flash = flash;
	}

	public Animation getAttack() {
		return attack;
	}

	public void setAttack(Animation attack) {
		this.attack = attack;
	}

	public Animation getToss() {
		return toss;
	}

	public void setToss(Animation toss) {
		this.toss = toss;
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isAffectedByForce() {
		return affectedByForce;
	}

	public void setAffectedByForce(boolean affectedByForce) {
		this.affectedByForce = affectedByForce;
	}

	public boolean isInvulnerable() {
		return invulnerable;
	}

	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}

	public int getSpringMovement() {
		return springMovement;
	}

	public void setSpringMovement(int springMovement) {
		this.springMovement = springMovement;
	}

	public int getSpringMoveAmount() {
		return springMoveAmount;
	}

	public void setSpringMoveAmount(int springMoveAmount) {
		this.springMoveAmount = springMoveAmount;
	}

	public static int getpX() {
		return pX;
	}

	public static void setpX(int pX) {
		PlayerKnight.pX = pX;
	}

	public static int getpY() {
		return pY;
	}

	public static void setpY(int pY) {
		PlayerKnight.pY = pY;
	}

	public boolean isOnLadder() {
		return isOnLadder;
	}

	public void setOnLadder(boolean isOnLadder) {
		this.isOnLadder = isOnLadder;
	}

	public void setKeys(boolean main) {
		ConfigData d = null;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(System.getProperty("user.dir") + "/" + Constants.CONFIG)));
			d = (ConfigData) objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		keys = new int[14];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = d.keys[i];
		}
		mainP = main;
	}

}
