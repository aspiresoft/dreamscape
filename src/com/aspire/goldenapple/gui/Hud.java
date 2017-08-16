package com.aspire.goldenapple.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.game.Level;
import com.aspire.goldenapple.game.unit.PlayerNinja;
import com.aspire.goldenapple.gui.state.PlayerState;
import com.aspire.goldenapple.tools.Message;

public class Hud {
	public static final int SIZE = 30;
	public static final int SPACE = SIZE / 3;

	private static final int HEART_COUNT = 5;
	private static final int KEY_COUNT = 4;
	private static final Color MESSAGE_COLOR = Constants.STEEL_BLUE_COLOR;

	private Program program;
	private GamePanel gamePanel;
	private Level level;
	private Heart[] hearts;
	private Key[] keys;
	private Score score;
	private Ammo ammo;
	private ArrayList<Message> messages;
	private ArrayList<Flag> flags;

	public Hud(Program program, Level level, GamePanel gamePanel) {
		this.program = program;
		this.level = level;
		this.gamePanel = gamePanel;
		hearts = new Heart[HEART_COUNT];
		keys = new Key[KEY_COUNT];
		setMessages(new ArrayList<Message>());
		flags = new ArrayList<Flag>();
		for (int i = 0; i < hearts.length; i++) {
			hearts[i] = new Heart(SPACE + (i * (SPACE / 2 + SIZE)), SPACE, SIZE, SIZE);
		}
		for (int i = 0; i < keys.length; i++) {
			keys[i] = new Key(Constants.SIZE.width - Hud.SPACE * 2 - ((keys.length - i) * (SIZE * 3 / 4 + SPACE)), SPACE * 3 / 2 + SIZE * 3 / 4, SIZE, SIZE, i + 1);
		}
		setAmmo(new Ammo(gamePanel, SPACE + SIZE / 2, SPACE * 2 + SIZE, SIZE * 3 / 2, SIZE * 3 / 4));
		score = new Score(gamePanel);

	}

	public void handleFlags() {
		flags.add(new Flag(SPACE * 2, SPACE * 2, Flag.CODE1));

		flags.add(new Flag(SPACE * 2, SPACE * 2, Flag.CODE2));

		flags.add(new Flag(SPACE * 2, SPACE * 2, Flag.CODE3));

		flags.add(new Flag(SPACE * 2, SPACE * 2, Flag.CODE4));

		for (int i = 0; i < flags.size(); i++) {
			flags.get(i).setX(SPACE * 20 + SIZE * 3 + (SIZE) * i);
			flags.get(i).setY(SPACE * 3 / 2);
		}
	}

	public void update() {
		if (gamePanel.blueKeyPicked) {
			keys[0].setPicked(true);
		}
		if (gamePanel.greenKeyPicked) {
			keys[1].setPicked(true);
		}
		if (gamePanel.redKeyPicked) {
			keys[2].setPicked(true);
		}
		if (gamePanel.yellowKeyPicked) {
			keys[3].setPicked(true);
		}
		if (gamePanel.blueFlagPicked) {
			for (Flag flag : flags) {
				if (!flag.isPicked() && flag.getType() == Flag.CODE1) {
					flag.setPicked(true);
					break;
				}
			}
		}
		if (gamePanel.greenFlagPicked) {
			for (Flag flag : flags) {
				if (!flag.isPicked() && flag.getType() == Flag.CODE2) {
					flag.setPicked(true);
					break;
				}
			}
		}
		if (gamePanel.redFlagPicked) {
			for (Flag flag : flags) {
				if (!flag.isPicked() && flag.getType() == Flag.CODE3) {
					flag.setPicked(true);
					break;
				}
			}
		}
		if (gamePanel.yellowFlagPicked) {
			for (Flag flag : flags) {
				if (!flag.isPicked() && flag.getType() == Flag.CODE4) {
					flag.setPicked(true);
					break;
				}
			}
		}

		if (!level.isTestLevel()) {
			int ax = PlayerState.currentHealth / 2;
			for (int i = 0; i < ax; i++) {
				hearts[i].setAmount(2);
			}
			int bx = PlayerState.currentHealth % 2;
			if (bx != 0) {
				hearts[ax].setAmount(1);
				ax++;
			}
			for (int i = ax; i < HEART_COUNT; i++) {
				hearts[i].setAmount(0);
			}
			score.update();
		}

		Iterator<Message> msgIter = getMessages().iterator();
		while (msgIter.hasNext()) {
			Message msg = msgIter.next();
			msg.update();
			if (!msg.isAlive()) {
				msgIter.remove();
			}
			msg = null;
		}
		msgIter = null;
	}

	public void draw(java.awt.Graphics g) {

		for (int i = 0; i < keys.length; i++) {
			keys[i].draw(g);
		}
		score.draw(g);

		// drawbar(2, SPACE * 20 + SIZE * 5 / 2, SPACE, flags.size() * 6 + 2,
		// SPACE*3, g);
		for (Flag flag : flags) {
			flag.draw(g);
		}

		Iterator<Message> msgIter = getMessages().iterator();
		int counter = 0;
		while (msgIter.hasNext()) {
			Message msg = msgIter.next();
			++counter;
			g.setColor(MESSAGE_COLOR);
			g.setFont(Constants.MENU_FONT_SMALLER);
			g.drawString(msg.getMessage(), SPACE + SIZE / 2, SPACE * 4 + SIZE + SIZE * counter);
			msg = null;
		}
		msgIter = null;

		if (!level.isTestLevel()) {
			for (int i = 0; i < HEART_COUNT; i++) {
				hearts[i].draw(g);
			}

			drawbar(3, SPACE * 20, SPACE * 3 / 2, 10, SPACE * 2, g);
			g.setColor(Constants.STEEL_BLUE_COLOR);
			Engine.drawCenteredString(g, (PlayerState.currentLevel + 1) + "/" + (this.program.getManager().getPs().getLevels().size()), new Rectangle(SPACE * 20, SPACE * 3 / 2, SPACE * 5, SPACE * 2),
					Constants.MENU_FONT_SMALLER);
		}
	}

	public void drawbar(int type, int x, int y, int unit, int height, Graphics g) {
		switch (type) {
		case 0:
			g.drawImage(Constants.IMAGES[124], x, y, SPACE / 2, height, null);
			for (int i = 1; i < unit; i++) {
				g.drawImage(Constants.IMAGES[125], x + i * (SPACE / 2), y, SPACE / 2, height, null);
			}
			g.drawImage(Constants.IMAGES[126], x + (unit * SPACE / 2), y, SPACE / 2, height, null);
			break;
		case 1:
			g.drawImage(Constants.IMAGES[127], x, y, SPACE / 2, height, null);
			for (int i = 1; i < unit; i++) {
				g.drawImage(Constants.IMAGES[128], x + i * (SPACE / 2), y, SPACE / 2, height, null);
			}
			g.drawImage(Constants.IMAGES[129], x + (unit * SPACE / 2), y, SPACE / 2, height, null);
			break;
		case 2:
			g.drawImage(Constants.IMAGES[130], x, y, SPACE / 2, height, null);
			for (int i = 1; i < unit; i++) {
				g.drawImage(Constants.IMAGES[131], x + i * (SPACE / 2), y, SPACE / 2, height, null);
			}
			g.drawImage(Constants.IMAGES[132], x + (unit * SPACE / 2), y, SPACE / 2, height, null);
			break;
		case 3:
			g.drawImage(Constants.IMAGES[133], x, y, SPACE / 2, height, null);
			for (int i = 1; i < unit; i++) {
				g.drawImage(Constants.IMAGES[134], x + i * (SPACE / 2), y, SPACE / 2, height, null);
			}
			g.drawImage(Constants.IMAGES[135], x + (unit * SPACE / 2), y, SPACE / 2, height, null);
			break;
		default:
			break;
		}
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

	public Ammo getAmmo() {
		return ammo;
	}

	public void setAmmo(Ammo ammo) {
		this.ammo = ammo;
	}
}
