package com.aspire.goldenapple.gui.state;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.Level;
import com.aspire.goldenapple.io.ConfigData;
import com.aspire.goldenapple.io.Data;
import com.aspire.goldenapple.io.InputHandler;
import com.aspire.goldenapple.tools.MapPack;
import com.aspire.goldenapple.tools.SButton;

public class ConfigState extends State {
	private SButton BACK;
	private SButton SAVE;

	private SButton FIRST_UP;
	private SButton FIRST_DOWN;
	private SButton FIRST_LEFT;
	private SButton FIRST_RIGHT;
	private SButton FIRST_FIRE;
	private SButton FIRST_JUMP;
	private SButton FIRST_FOCUS;

	private SButton SECOND_UP;
	private SButton SECOND_DOWN;
	private SButton SECOND_LEFT;
	private SButton SECOND_RIGHT;
	private SButton SECOND_FIRE;
	private SButton SECOND_JUMP;
	private SButton SECOND_FOCUS;

	private int selectedEvent;
	private int selectedKey;

	private boolean expectingKey;

	private int keys[];

	public ConfigState(Program p) {
		super(p);

		init();

		BACK = new SButton(p, Constants.SPACE * 33, Constants.SPACE * 29, Constants.SPACE * 5, Constants.SPACE * 2, "BACK", Constants.MENU_FONT_SMALLER);
		SAVE = new SButton(p, Constants.SPACE, Constants.SPACE * 29, Constants.SPACE * 5, Constants.SPACE * 2, "SAVE", Constants.MENU_FONT_SMALLER);

		FIRST_UP = new SButton(p, Constants.SPACE * 12, Constants.SPACE * 4, Constants.SPACE * 6, Constants.SPACE * 2, "EMPTY", Constants.NORMAL_FONT);
		FIRST_DOWN = new SButton(p, Constants.SPACE * 12, Constants.SPACE * 7, Constants.SPACE * 6, Constants.SPACE * 2, "EMPTY", Constants.NORMAL_FONT);
		FIRST_LEFT = new SButton(p, Constants.SPACE * 12, Constants.SPACE * 10, Constants.SPACE * 6, Constants.SPACE * 2, "EMPTY", Constants.NORMAL_FONT);
		FIRST_RIGHT = new SButton(p, Constants.SPACE * 12, Constants.SPACE * 13, Constants.SPACE * 6, Constants.SPACE * 2, "EMPTY", Constants.NORMAL_FONT);
		FIRST_FIRE = new SButton(p, Constants.SPACE * 12, Constants.SPACE * 16, Constants.SPACE * 6, Constants.SPACE * 2, "EMPTY", Constants.NORMAL_FONT);
		FIRST_JUMP = new SButton(p, Constants.SPACE * 12, Constants.SPACE * 19, Constants.SPACE * 6, Constants.SPACE * 2, "EMPTY", Constants.NORMAL_FONT);
		FIRST_FOCUS = new SButton(p, Constants.SPACE * 12, Constants.SPACE * 22, Constants.SPACE * 6, Constants.SPACE * 2, "EMPTY", Constants.NORMAL_FONT);

		SECOND_UP = new SButton(p, Constants.SPACE * 32, Constants.SPACE * 4, Constants.SPACE * 6, Constants.SPACE * 2, "EMPTY", Constants.NORMAL_FONT);
		SECOND_DOWN = new SButton(p, Constants.SPACE * 32, Constants.SPACE * 7, Constants.SPACE * 6, Constants.SPACE * 2, "EMPTY", Constants.NORMAL_FONT);
		SECOND_LEFT = new SButton(p, Constants.SPACE * 32, Constants.SPACE * 10, Constants.SPACE * 6, Constants.SPACE * 2, "EMPTY", Constants.NORMAL_FONT);
		SECOND_RIGHT = new SButton(p, Constants.SPACE * 32, Constants.SPACE * 13, Constants.SPACE * 6, Constants.SPACE * 2, "EMPTY", Constants.NORMAL_FONT);
		SECOND_FIRE = new SButton(p, Constants.SPACE * 32, Constants.SPACE * 16, Constants.SPACE * 6, Constants.SPACE * 2, "EMPTY", Constants.NORMAL_FONT);
		SECOND_JUMP = new SButton(p, Constants.SPACE * 32, Constants.SPACE * 19, Constants.SPACE * 6, Constants.SPACE * 2, "EMPTY", Constants.NORMAL_FONT);
		SECOND_FOCUS = new SButton(p, Constants.SPACE * 32, Constants.SPACE * 22, Constants.SPACE * 6, Constants.SPACE * 2, "EMPTY", Constants.NORMAL_FONT);
	}

	public void init() {
		lastTime = System.currentTimeMillis();
		expectingKey = false;
		keys = new int[14];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = -1;

		}
		selectedKey = -1;
	}

	public void update() {
		if (shouldUpdate) {
			if (InputHandler.keys[KeyEvent.VK_ESCAPE]) {
				InputHandler.keys[KeyEvent.VK_ESCAPE] = false;
				program.getManager().setState(1);
			}

			FIRST_UP.update();
			FIRST_DOWN.update();
			FIRST_LEFT.update();
			FIRST_RIGHT.update();
			FIRST_FIRE.update();
			FIRST_JUMP.update();
			FIRST_FOCUS.update();

			SECOND_UP.update();
			SECOND_DOWN.update();
			SECOND_LEFT.update();
			SECOND_RIGHT.update();
			SECOND_FIRE.update();
			SECOND_JUMP.update();
			SECOND_FOCUS.update();

			BACK.update();
			SAVE.update();

			if (expectingKey) {
				for (int i = 0; i < InputHandler.keys.length; i++) {
					if (InputHandler.keys[i]) {
						InputHandler.keys[i] = false;
						selectedKey = i;

						if (selectedEvent != -1 && selectedKey != -1) {
							keys[selectedEvent] = selectedKey;
							selectedEvent = -1;
							selectedKey = -1;
						}
						expectingKey = false;
						break;
					}
				}
			}

			if (InputHandler.mouseClicked) {
				if (System.currentTimeMillis() - lastTime > 200) {
					lastTime = System.currentTimeMillis();

					if (new Rectangle(BACK.getX(), BACK.getY(), BACK.getWidth(), BACK.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						program.getManager().setState(5);

					}
					if (new Rectangle(FIRST_UP.getX(), FIRST_UP.getY(), FIRST_UP.getWidth(), FIRST_UP.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (expectingKey) {
							expectingKey = false;
						} else {
							expectingKey = true;
							selectedEvent = 0;
						}
					}
					if (new Rectangle(FIRST_DOWN.getX(), FIRST_DOWN.getY(), FIRST_DOWN.getWidth(), FIRST_DOWN.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (expectingKey) {
							expectingKey = false;
						} else {
							expectingKey = true;
							selectedEvent = 1;
						}
					}
					if (new Rectangle(FIRST_LEFT.getX(), FIRST_LEFT.getY(), FIRST_LEFT.getWidth(), FIRST_LEFT.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (expectingKey) {
							expectingKey = false;
						} else {
							expectingKey = true;
							selectedEvent = 2;
						}
					}
					if (new Rectangle(FIRST_RIGHT.getX(), FIRST_RIGHT.getY(), FIRST_RIGHT.getWidth(), FIRST_RIGHT.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (expectingKey) {
							expectingKey = false;
						} else {
							expectingKey = true;
							selectedEvent = 3;
						}
					}
					if (new Rectangle(FIRST_FIRE.getX(), FIRST_FIRE.getY(), FIRST_FIRE.getWidth(), FIRST_FIRE.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (expectingKey) {
							expectingKey = false;
						} else {
							expectingKey = true;
							selectedEvent = 4;
						}
					}
					if (new Rectangle(FIRST_JUMP.getX(), FIRST_JUMP.getY(), FIRST_JUMP.getWidth(), FIRST_JUMP.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (expectingKey) {
							expectingKey = false;
						} else {
							expectingKey = true;
							selectedEvent = 5;
						}
					}
					if (new Rectangle(FIRST_FOCUS.getX(), FIRST_FOCUS.getY(), FIRST_FOCUS.getWidth(), FIRST_FOCUS.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (expectingKey) {
							expectingKey = false;
						} else {
							expectingKey = true;
							selectedEvent = 6;
						}
					}
					// =============================================================================
					if (new Rectangle(SECOND_UP.getX(), SECOND_UP.getY(), SECOND_UP.getWidth(), SECOND_UP.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (expectingKey) {
							expectingKey = false;
						} else {
							expectingKey = true;
							selectedEvent = 7;
						}
					}
					if (new Rectangle(SECOND_DOWN.getX(), SECOND_DOWN.getY(), SECOND_DOWN.getWidth(), SECOND_DOWN.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (expectingKey) {
							expectingKey = false;
						} else {
							expectingKey = true;
							selectedEvent = 8;
						}
					}
					if (new Rectangle(SECOND_LEFT.getX(), SECOND_LEFT.getY(), SECOND_LEFT.getWidth(), SECOND_LEFT.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (expectingKey) {
							expectingKey = false;
						} else {
							expectingKey = true;
							selectedEvent = 9;
						}
					}
					if (new Rectangle(SECOND_RIGHT.getX(), SECOND_RIGHT.getY(), SECOND_RIGHT.getWidth(), SECOND_RIGHT.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (expectingKey) {
							expectingKey = false;
						} else {
							expectingKey = true;
							selectedEvent = 10;
						}
					}
					if (new Rectangle(SECOND_FIRE.getX(), SECOND_FIRE.getY(), SECOND_FIRE.getWidth(), SECOND_FIRE.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (expectingKey) {
							expectingKey = false;
						} else {
							expectingKey = true;
							selectedEvent = 11;
						}
					}
					if (new Rectangle(SECOND_JUMP.getX(), SECOND_JUMP.getY(), SECOND_JUMP.getWidth(), SECOND_JUMP.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (expectingKey) {
							expectingKey = false;
						} else {
							expectingKey = true;
							selectedEvent = 12;
						}
					}
					if (new Rectangle(SECOND_FOCUS.getX(), SECOND_FOCUS.getY(), SECOND_FOCUS.getWidth(), SECOND_FOCUS.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (expectingKey) {
							expectingKey = false;
						} else {
							expectingKey = true;
							selectedEvent = 13;
						}
					}
					if (new Rectangle(SAVE.getX(), SAVE.getY(), SAVE.getWidth(), SAVE.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();

						if (duplicates(keys)) {
							JOptionPane.showMessageDialog(null, "Please make sure you use keys once!", "Duplicate Error", JOptionPane.OK_OPTION);
						} else {
							boolean error = false;
							for (int i = 0; i < keys.length; i++) {
								if (keys[i] == -1) {
									error = true;
									break;
								}
							}
							if (error) {
								JOptionPane.showMessageDialog(null, "Please make sure you bind all events with keys", "Empty Binding Error", JOptionPane.OK_OPTION);
							} else {
								ConfigData data = new ConfigData();
								data.keys = keys;
								try {
									ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(System.getProperty("user.dir") + "/" + Constants.CONFIG)));
									objectOutputStream.writeObject(data);
									objectOutputStream.flush();
									objectOutputStream.close();
								} catch (Exception e) {
									e.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "Key bindings have succesfully saved!", "Success", JOptionPane.OK_OPTION);
							}
						}
					}
					// ====================================================================================

				}
			}

			FIRST_UP.setText(keys[0] != (-1) ? KeyEvent.getKeyText(keys[0]) : "Empty");
			FIRST_DOWN.setText(keys[1] != (-1) ? KeyEvent.getKeyText(keys[1]) : "Empty");
			FIRST_LEFT.setText(keys[2] != (-1) ? KeyEvent.getKeyText(keys[2]) : "Empty");
			FIRST_RIGHT.setText(keys[3] != (-1) ? KeyEvent.getKeyText(keys[3]) : "Empty");
			FIRST_FIRE.setText(keys[4] != (-1) ? KeyEvent.getKeyText(keys[4]) : "Empty");
			FIRST_JUMP.setText(keys[5] != (-1) ? KeyEvent.getKeyText(keys[5]) : "Empty");
			FIRST_FOCUS.setText(keys[6] != (-1) ? KeyEvent.getKeyText(keys[6]) : "Empty");

			SECOND_UP.setText(keys[7] != (-1) ? KeyEvent.getKeyText(keys[7]) : "Empty");
			SECOND_DOWN.setText(keys[8] != (-1) ? KeyEvent.getKeyText(keys[8]) : "Empty");
			SECOND_LEFT.setText(keys[9] != (-1) ? KeyEvent.getKeyText(keys[9]) : "Empty");
			SECOND_RIGHT.setText(keys[10] != (-1) ? KeyEvent.getKeyText(keys[10]) : "Empty");
			SECOND_FIRE.setText(keys[11] != (-1) ? KeyEvent.getKeyText(keys[11]) : "Empty");
			SECOND_JUMP.setText(keys[12] != (-1) ? KeyEvent.getKeyText(keys[12]) : "Empty");
			SECOND_FOCUS.setText(keys[13] != (-1) ? KeyEvent.getKeyText(keys[13]) : "Empty");

		}
	}

	public static boolean duplicates(int[] x) {
		for (int i = 0; i < x.length; i++) {
			for (int j = i + 1; j < x.length; j++) {
				if (x[j] == x[i])
					return true;
			}
		}
		return false;
	}

	public void draw(java.awt.Graphics g) {
		if (shouldDraw) {
			g.drawImage(Constants.BG, 0, 0, Constants.SIZE.width, Constants.SIZE.height, null);

			g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
			g.fillRect(Constants.SPACE, Constants.SPACE, Constants.SPACE * 18, Constants.SPACE * 27);
			g.fillRect(Constants.SPACE * 21, Constants.SPACE, Constants.SPACE * 18, Constants.SPACE * 27);

			g.setColor(Constants.MAIN_COLOR);
			g.setFont(Constants.NORMAL_FONT);
			g.drawString("PLAYER 1", Constants.SPACE * 8, Constants.SPACE * 2);
			g.drawString("PLAYER 2", Constants.SPACE * 28, Constants.SPACE * 2);

			g.drawString("UP", Constants.SPACE * 2, Constants.SPACE * 5);
			g.drawString("DOWN", Constants.SPACE * 2, Constants.SPACE * 8);
			g.drawString("LEFT", Constants.SPACE * 2, Constants.SPACE * 11);
			g.drawString("RIGHT", Constants.SPACE * 2, Constants.SPACE * 14);
			g.drawString("FIRE", Constants.SPACE * 2, Constants.SPACE * 17);
			g.drawString("JUMP", Constants.SPACE * 2, Constants.SPACE * 20);
			g.drawString("FOCUS", Constants.SPACE * 2, Constants.SPACE * 23);

			FIRST_UP.draw(g);
			FIRST_DOWN.draw(g);
			FIRST_LEFT.draw(g);
			FIRST_RIGHT.draw(g);
			FIRST_FIRE.draw(g);
			FIRST_JUMP.draw(g);
			FIRST_FOCUS.draw(g);

			g.setColor(Constants.MAIN_COLOR);
			g.setFont(Constants.NORMAL_FONT);

			g.drawString("UP", Constants.SPACE * 22, Constants.SPACE * 5);
			g.drawString("DOWN", Constants.SPACE * 22, Constants.SPACE * 8);
			g.drawString("LEFT", Constants.SPACE * 22, Constants.SPACE * 11);
			g.drawString("RIGHT", Constants.SPACE * 22, Constants.SPACE * 14);
			g.drawString("FIRE", Constants.SPACE * 22, Constants.SPACE * 17);
			g.drawString("JUMP", Constants.SPACE * 22, Constants.SPACE * 20);
			g.drawString("FOCUS", Constants.SPACE * 22, Constants.SPACE * 23);

			SECOND_UP.draw(g);
			SECOND_DOWN.draw(g);
			SECOND_LEFT.draw(g);
			SECOND_RIGHT.draw(g);
			SECOND_FIRE.draw(g);
			SECOND_JUMP.draw(g);
			SECOND_FOCUS.draw(g);

			BACK.draw(g);
			SAVE.draw(g);

			if (expectingKey) {
				g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
				g.fillRect(0, 0, Constants.SIZE.width, Constants.SIZE.height);

				g.setColor(Constants.STEEL_BLUE_COLOR);
				g.setFont(Constants.BIG_FONT);
				g.drawString("Press any key", Constants.SIZE.width / 2 - 200, Constants.SIZE.height / 2 - 50);
			}

		}
	}

}
