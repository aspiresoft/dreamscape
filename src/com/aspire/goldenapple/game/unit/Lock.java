package com.aspire.goldenapple.game.unit;

import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.tools.MediaPlayer;

public class Lock extends Unit {
	public static final int CODE1 = 14, CODE2 = 15, CODE3 = 16, CODE4 = 17;

	private GamePanel gamePanel;
	private int type;

	public Lock(Program program, GamePanel gamePanel, int x, int y, int width, int height, int type, int mX, int mY, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;
		this.type = type;
		shouldWork = true;
		isAnEntity = false;

		matrixX = mX;
		matrixY = mY;
		if (status == 0) {
			gamePanel.getCollision_matrix()[matrixX][matrixY] = 1;
		} else {
			gamePanel.getCollision_parallel_matrix()[status - 1][matrixX][matrixY] = 1;
		}

	}

	public void update() {
		if (shouldWork) {
			if (gamePanel.blueKeyPicked) {
				if (type == CODE1) {
					open();
				}
			}
			if (gamePanel.greenKeyPicked) {
				if (type == CODE2) {
					open();
				}
			}
			if (gamePanel.redKeyPicked) {
				if (type == CODE3) {
					open();
				}
			}
			if (gamePanel.yellowKeyPicked) {
				if (type == CODE4) {
					open();
				}
			}
		}
	}

	private void open() {
		shouldWork = false;
		gamePanel.getCollision_matrix()[matrixX][matrixY] = 0;
		MediaPlayer.playSound(Constants.lock_open);
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			g.drawImage(Constants.IMAGES[Constants.UNIT_ID_TO_IMAGE_ID(type)], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);

		}
	}
}
