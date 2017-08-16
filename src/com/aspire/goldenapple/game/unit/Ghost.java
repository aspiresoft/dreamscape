package com.aspire.goldenapple.game.unit;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.tools.MediaPlayer;

public class Ghost extends Unit {
	public static final int CODE = 54;

	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;

	private static final int SPEED = 2;

	private static final int POINT = 400;

	private static final int RADIUS = Constants.BRICK_SIZE * 4;

	private BufferedImage images_move_left;
	private BufferedImage images_move_right;
	private BufferedImage images_attack_left;
	private BufferedImage images_attack_right;

	private Point[][] coordinates;
	private Point target_point, last_point;

	private boolean attacking;

	private String type;
	private GamePanel gamePanel;
	private boolean direction;

	private static Random r = new Random();

	public Ghost(Program program, GamePanel gamePanel, int x, int y, int w, int h, int status, int priority) {
		super(program, x, y, w, h, status, priority);
		this.gamePanel = gamePanel;

		alive = true;
		shouldWork = true;
		isAnEntity = true;
		direction = true;

		images_move_left = Constants.IMAGES[506];
		images_attack_left = Constants.IMAGES[507];

		images_move_right = Constants.IMAGES[508];
		images_attack_right = Constants.IMAGES[519];

		coordinates = new Point[3][3];

		for (int i = 0; i < coordinates.length; i++) {
			for (int j = 0; j < coordinates[0].length; j++) {
				coordinates[i][j] = new Point(x, y);
			}
		}
		for (int i = 0; i < coordinates.length; i++) {
			for (int j = 0; j < coordinates[0].length; j++) {
				if (i == 0) {
					coordinates[i][j].x -= RADIUS;
				}
				if (i == 2) {
					coordinates[i][j].x += RADIUS;
				}
				if (j == 0) {
					coordinates[i][j].y -= RADIUS;
				}
				if (j == 2) {
					coordinates[i][j].y += RADIUS;
				}
			}
		}
		target_point = new Point(coordinates[1][1].x, coordinates[1][1].y);

		last_point = new Point(-1, -1);

		while (target_point.x == coordinates[1][1].x && target_point.y == coordinates[1][1].y) {
			Point p = coordinates[r.nextInt(3)][r.nextInt(3)];
			target_point.x = p.x;
			target_point.y = p.y;
		}

	}

	public void update() {
		if (shouldWork) {
			if (alive) {
				for (int i = 1; i <= SPEED; i++) {
					if (x == target_point.x && y == target_point.y) {

					}
					if (x < target_point.x) {
						x++;
						if (!direction) {
							direction = true;
						}
					} else if (x > target_point.x) {
						x--;
						if (direction) {
							direction = false;
						}
					} else {

					}
					if (y < target_point.y) {
						y++;
					} else if (y > target_point.y) {
						y--;
					} else {

					}

				}
				if (gamePanel.ninjaPlaying) {
					PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
					if (p != null) {
						if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
							p.hurt(1);
						}
					}
				}
				if (gamePanel.robotPlaying) {
					PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
					if (p != null) {
						if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
							p.hurt(1);
						}
					}
				}
				if (gamePanel.knightPlaying) {
					PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
					if (p != null) {
						if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
							p.hurt(1);
						}
					}
				}
			}
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			if (direction) {
				g.drawImage(images_move_right, x, y, width, height, null);
			} else {
				g.drawImage(images_move_left, x, y, width, height, null);
			}
		}
	}

}
