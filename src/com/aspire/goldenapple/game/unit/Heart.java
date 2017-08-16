package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.gui.state.PlayerState;
import com.aspire.goldenapple.tools.MediaPlayer;

public class Heart extends Unit {
	public static final int CODE = 51;
	private static final int SIZE = Constants.BRICK_SIZE * 3 / 4;
	private GamePanel gamePanel;

	public Heart(Program program, GamePanel gamePanel, int x, int y, int width, int height, int status, int priority) {
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;

		shouldWork = true;
		int space = (Constants.BRICK_SIZE - SIZE) / 2;
		x += space;
		y += space;
		width -= space * 2;
		height -= space * 2;
	}

	public void update() {
		if (shouldWork) {
			if (gamePanel.ninjaPlaying) {
				PlayerNinja p = gamePanel.findNinja(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						shouldWork = false;
						MediaPlayer.playSound(Constants.coin_picked);
						if (!gamePanel.getLevel().isTestLevel()) {
							PlayerState.currentHealth += 2;
							if (PlayerState.currentHealth > 10) {
								PlayerState.currentHealth = 10;
							}
						}
					}
				}
				p = null;
			}
			if (gamePanel.robotPlaying) {
				PlayerRobot p = gamePanel.findRobot(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						shouldWork = false;
						MediaPlayer.playSound(Constants.coin_picked);
						if (!gamePanel.getLevel().isTestLevel()) {
							PlayerState.currentHealth += 2;
							if (PlayerState.currentHealth > 10) {
								PlayerState.currentHealth = 10;
							}
						}
					}
				}
				p = null;
			}
			if (gamePanel.knightPlaying) {
				PlayerKnight p = gamePanel.findKnight(gamePanel.getUnits());
				if (p != null) {
					if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height))) {
						shouldWork = false;
						MediaPlayer.playSound(Constants.coin_picked);
						if (!gamePanel.getLevel().isTestLevel()) {
							PlayerState.currentHealth += 2;
							if (PlayerState.currentHealth > 10) {
								PlayerState.currentHealth = 10;
							}
						}
					}
				}
				p = null;
			}
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldWork) {
			g.drawImage(Constants.IMAGES[121], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
		}
	}
}
