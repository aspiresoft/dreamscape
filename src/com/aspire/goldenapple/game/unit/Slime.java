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

public class Slime extends Unit {
	public static final int CODE = 48;

	private static final int WIDTH = 25;
	private static final int HEIGHT = 20;
	private static final int SPEED = 1;
	private static final int POINT = 100;

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

	public Slime(Program program, GamePanel gamePanel, int x, int y, int width, int height, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;

		alive = true;
		goingLeft = true;
		goingRight = false;
		shouldWork = true;
		isAnEntity = true;

		width = WIDTH;
		height = HEIGHT;
		y += Constants.BRICK_SIZE - HEIGHT;
		frameCount = 2;
		score = POINT;

		images_move_left = new BufferedImage[frameCount];
		images_move_right = new BufferedImage[frameCount];
		images_dead = new BufferedImage[frameCount];
		images_move_left[0] = Constants.IMAGES[104];
		images_move_left[1] = Constants.IMAGES[105];
		images_move_right[0] = Constants.IMAGES[106];
		images_move_right[1] = Constants.IMAGES[107];
		images_dead[0] = Constants.IMAGES[108];
		images_dead[1] = Constants.IMAGES[109];
		anim_left = new Animation(gamePanel, images_move_left, x, y, width, height, ANIM_DURATION, true);
		anim_right = new Animation(gamePanel, images_move_right, x, y, width, height, ANIM_DURATION, true);
		anim_dead = new Animation(gamePanel, images_dead, x, y, width, height, ANIM_DURATION / 2, false);
	}

	public void update() {
		if (shouldWork) {

			if (alive) {

				for (int i = 1; i <= SPEED; i++) {
					if (goingLeft) {
						if (status == 0) {
							if (Engine.canMoveToLeft(x, y, width, height) && Engine.canMove(gamePanel.getCollision_matrix(), (x - 1) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_matrix(), (x - 1) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)
									&& !Engine.canMove(gamePanel.getCollision_matrix(), (x - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
								x--;
								anim_right.currentX--;
								anim_left.currentX--;
								anim_dead.currentX--;
								anim_left.update();

							} else {
								goingLeft = false;
								goingRight = true;
							}
						} else {
							if (Engine.canMoveToLeft(x, y, width, height)
									&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x - 1) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x - 1) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)
									&& !Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x - 1) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
								x--;
								anim_right.currentX--;
								anim_left.currentX--;
								anim_dead.currentX--;
								anim_left.update();

							} else {
								goingLeft = false;
								goingRight = true;
							}
						}

					}
					if (goingRight) {
						if (status == 0) {
							if (Engine.canMoveToRight(x, y, width, height) && Engine.canMove(gamePanel.getCollision_matrix(), (x + width) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_matrix(), (x + width) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)
									&& !Engine.canMove(gamePanel.getCollision_matrix(), (x + width) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
								x++;
								anim_right.currentX++;
								anim_left.currentX++;
								anim_dead.currentX++;
								anim_right.update();
							} else {
								goingLeft = true;
								goingRight = false;
							}
						} else {
							if (Engine.canMoveToRightParallel(x, y, width, height)
									&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width) / Constants.BRICK_SIZE, (y) / Constants.BRICK_SIZE)
									&& Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width) / Constants.BRICK_SIZE, (y + height - 1) / Constants.BRICK_SIZE)
									&& !Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], (x + width) / Constants.BRICK_SIZE, (y + height) / Constants.BRICK_SIZE)) {
								x++;
								anim_right.currentX++;
								anim_left.currentX++;
								anim_dead.currentX++;
								anim_right.update();
							} else {
								goingLeft = true;
								goingRight = false;
							}
						}
					}
				}

				Rectangle r1 = new Rectangle(x, y, width, height);
				if (gamePanel.ninjaPlaying) {
					PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());

					Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height);
					if (Engine.isInside(r1, r2)) {

						if (!p.isInvulnerable()) {
							gamePanel.addScore(POINT, x, y);
							die();
						}
						p.hurt(1);
					}
					p = null;
				}
				if (gamePanel.robotPlaying) {
					PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
					Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height);
					if (Engine.isInside(r1, r2)) {

						if (!p.isInvulnerable()) {
							gamePanel.addScore(POINT, x, y);
							die();
						}
						p.hurt(1);
					}
					p = null;
				}
				if (gamePanel.knightPlaying) {
					PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
					Rectangle r2 = new Rectangle(p.x, p.y, p.width, p.height);
					if (Engine.isInside(r1, r2)) {

						if (!p.isInvulnerable()) {
							gamePanel.addScore(POINT, x, y);
							die();
						}
						p.hurt(1);
					}
					p = null;
				}

				Iterator<Unit> iter = gamePanel.getUnits().iterator();
				while (iter.hasNext()) {
					Unit u = iter.next();
					if (r1.intersects(new Rectangle(u.x, u.y, u.width, u.height))) {
						if (u instanceof Snail && u.alive && alive && u.status == status) {
							Snail s = (Snail) u;
							if (s.isShell() && (s.isGoingLeft() || s.isGoingRight())) {
								die();
								gamePanel.addScore(score, x, y);
							}
						}
					}
				}
			} else {
				anim_dead.update();
				if (!anim_dead.shouldAnimate) {
					shouldWork = false;
				}
			}

		}
	}

	public void die() {
		if (alive) {
			MediaPlayer.playSound(Constants.slime_dead, 1.5f);
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
				anim_dead.draw(g);
			}
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
