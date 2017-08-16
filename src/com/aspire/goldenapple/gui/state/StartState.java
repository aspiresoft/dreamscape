package com.aspire.goldenapple.gui.state;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.Level;
import com.aspire.goldenapple.io.Data;
import com.aspire.goldenapple.io.InputHandler;
import com.aspire.goldenapple.tools.MapPack;
import com.aspire.goldenapple.tools.SButton;

public class StartState extends State {
	private static final int DISPLAY_COUNT = 15;
	private ArrayList<MapPack> mapPacks;
	private int mapIndex, displayIndex;;
	private SButton PLAY;
	private SButton BACK;
	private SButton LEFT;
	private SButton RIGHT;
	private SButton HERO_FIRST;
	private int SELECTED_HERO;

	private int CODE1 = 0;
	private int CODE2 = 54;
	private int CODE3 = 55;

	public StartState(Program p) {
		super(p);
		PLAY = new SButton(p, Constants.SPACE * 33, Constants.SPACE * 2, Constants.SPACE * 5, Constants.SPACE * 2, "PLAY", Constants.MENU_FONT_SMALLER);
		BACK = new SButton(p, Constants.SPACE * 33, Constants.SPACE * 28, Constants.SPACE * 5, Constants.SPACE * 2, "BACK", Constants.MENU_FONT_SMALLER);
		LEFT = new SButton(p, Constants.SPACE * 33, Constants.SPACE * 23, Constants.SPACE * 2, Constants.SPACE * 2, "<", Constants.MENU_FONT_SMALLER);
		RIGHT = new SButton(p, Constants.SPACE * 36, Constants.SPACE * 23, Constants.SPACE * 2, Constants.SPACE * 2, ">", Constants.MENU_FONT_SMALLER);
		HERO_FIRST = new SButton(p, Constants.SPACE * 33, Constants.SPACE * 6, Constants.SPACE * 5, Constants.SPACE * 5, SELECTED_HERO);
		init();
	}

	public void init() {
		lastTime = System.currentTimeMillis();
		mapPacks = new ArrayList<MapPack>();
		loadMaps();
		mapIndex = -1;
		displayIndex = 1;
	}

	public void update() {
		if (shouldUpdate) {
			if (InputHandler.keys[KeyEvent.VK_ESCAPE]) {
				InputHandler.keys[KeyEvent.VK_ESCAPE] = false;
				program.getManager().setState(1);
			}

			PLAY.update();
			BACK.update();
			LEFT.update();
			RIGHT.update();
			HERO_FIRST.update();

			if (InputHandler.mouseClicked) {
				if (System.currentTimeMillis() - lastTime > 200) {
					lastTime = System.currentTimeMillis();

					if (new Rectangle(PLAY.getX(), PLAY.getY(), PLAY.getWidth(), PLAY.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (mapIndex != -1) {
							ArrayList<Level> levels = new ArrayList<Level>();
							ArrayList<Data> selectedMapPackLevels = mapPacks.get(mapIndex).levels;
							for (int i = 0; i < selectedMapPackLevels.size(); i++) {

								boolean ninja = false, robot = false, knight = false;
								if (SELECTED_HERO == CODE1) {
									ninja = true;
								}
								if (SELECTED_HERO == CODE2) {
									robot = true;
								}
								if (SELECTED_HERO == CODE3) {
									knight = true;
								}

								Level l = new Level(program, false, ninja, robot, knight);
								Data d = new Data();
								d.setBg_id(selectedMapPackLevels.get(i).getBg_id());
								d.setLevel_name(selectedMapPackLevels.get(i).getLevel_name());
								d.setGameGrids(selectedMapPackLevels.get(i).getGrid_1(), selectedMapPackLevels.get(i).getGrid_2(), selectedMapPackLevels.get(i).getGrid_3(),
										selectedMapPackLevels.get(i).getGrid_parallel_1(), selectedMapPackLevels.get(i).getGrid_parallel_2(), selectedMapPackLevels.get(i).getGrid_parallel_3());
								d.setDoorEntranceIDMAP(selectedMapPackLevels.get(i).getDoorEntranceIDMAP());
								d.setDoorExitIDMAP(selectedMapPackLevels.get(i).getDoorExitIDMAP());
								l.getGamePanel().setGameGrids(d);
								l.getGamePanel().handleKeys();
								levels.add(l);

							}
							if (levels.isEmpty()) {
								JOptionPane.showMessageDialog(null, mapPacks.get(mapIndex).name + " has no levels, it is empty. Please make sure you have selected proper map pack.", "Warning",
										JOptionPane.OK_OPTION);
							} else {
								program.getManager().setState(4);
								program.getManager().getPs().setLevels(levels);
								program.getManager().getPs().getLevels().get(0).setShouldDraw(true);
								program.getManager().getPs().getLevels().get(0).setShouldUpdate(true);

							}
						}

					}
					if (new Rectangle(BACK.getX(), BACK.getY(), BACK.getWidth(), BACK.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						program.getManager().setState(1);

					}
					if (new Rectangle(HERO_FIRST.getX(), HERO_FIRST.getY(), HERO_FIRST.getWidth(), HERO_FIRST.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (SELECTED_HERO == CODE1) {
							SELECTED_HERO = CODE2;
						} else if (SELECTED_HERO == CODE2) {
							SELECTED_HERO = CODE3;
						} else if (SELECTED_HERO == CODE3) {
							SELECTED_HERO = CODE1;
						}
						HERO_FIRST.setType(SELECTED_HERO);

					}
					if (mapPacks.size() > DISPLAY_COUNT) {
						if (new Rectangle(LEFT.getX(), LEFT.getY(), LEFT.getWidth(), LEFT.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
							InputHandler.clearInput();
							if (displayIndex > 1) {
								displayIndex--;
							}
						}
						if (new Rectangle(RIGHT.getX(), RIGHT.getY(), RIGHT.getWidth(), RIGHT.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
							InputHandler.clearInput();
							if (displayIndex < ((Math.abs((mapPacks.size() - 1) / DISPLAY_COUNT))) + 1) {
								displayIndex++;
							}
						}
					}
					if (new Rectangle(Constants.SPACE, Constants.SPACE, Constants.SPACE * 30, Constants.SPACE * 30).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						if (new Rectangle(Constants.SPACE, Constants.SPACE, Constants.SPACE * 30, Constants.SPACE * 30).contains(InputHandler.mouseX, InputHandler.mouseY)) {
							for (int i = 0; i < mapPacks.size(); i++) {
								if ((i / DISPLAY_COUNT) + 1 == displayIndex
										&& new Rectangle(Constants.SPACE, Constants.SPACE + (Constants.SPACE * 2 * (i % DISPLAY_COUNT)), Constants.SPACE * 30, Constants.SPACE * 2)
												.contains(InputHandler.mouseX, InputHandler.mouseY)) {
									mapIndex = i;
									break;
								}
							}
						}
					}
				}
			}
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldDraw) {
			g.drawImage(Constants.BG, 0, 0, Constants.SIZE.width, Constants.SIZE.height, null);

			g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
			g.fillRect(Constants.SPACE, Constants.SPACE, Constants.SPACE * 30, Constants.SPACE * 30);
			g.fillRect(Constants.SPACE * 32, Constants.SPACE, Constants.SPACE * 7, Constants.SPACE * 30);

			PLAY.draw(g);
			BACK.draw(g);
			HERO_FIRST.draw(g);

			g.setColor(Constants.MAIN_COLOR);
			g.drawString("   HERO 1", Constants.SPACE * 33, Constants.SPACE * 11 / 2);

			for (int i = 0; i < mapPacks.size(); i++) {
				if ((i / DISPLAY_COUNT) + 1 == displayIndex) {
					if (i == mapIndex) {
						g.setColor(Constants.SELECTED_COLOR);
					} else {
						g.setColor(Constants.MAIN_COLOR);
					}
					Engine.drawCenteredString(g, mapPacks.get(i).name,
							new Rectangle(Constants.SPACE, Constants.SPACE + ((i % DISPLAY_COUNT) * (Constants.SPACE * 2)), Constants.SPACE * 30, Constants.SPACE * 2), Constants.MENU_FONT_HUGE);
				}
			}
			if (mapPacks.size() > DISPLAY_COUNT) {
				LEFT.draw(g);
				RIGHT.draw(g);
				g.setColor(Constants.MAIN_COLOR);
				Engine.drawCenteredString(g, displayIndex + " / " + ((Math.abs((mapPacks.size() - 1) / DISPLAY_COUNT)) + 1),
						new Rectangle(Constants.SPACE * 33, Constants.SPACE * 26, Constants.SPACE * 5, Constants.SPACE / 2), Constants.MENU_FONT_HUGE);
			}
		}
	}

	private void loadMaps() {
		mapPacks.clear();
		ArrayList<MapPack> tempList = new ArrayList<MapPack>();
		try {
			File folder = new File(Constants.MAPPACK_LOCATION);

			File[] listOfFiles = folder.listFiles();
			if (listOfFiles.length != 0) {
				for (int i = 0; i < listOfFiles.length; i++) {
					File file = listOfFiles[i];
					if (file.isFile() && file.getName().endsWith(Constants.MAPPACK_EXTENSION)) {
						MapPack pack = null;
						try {
							ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
							pack = (MapPack) objectInputStream.readObject();
							objectInputStream.close();
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println(e.getMessage());
						}
						if (pack != null) {
							tempList.add(pack);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		for (MapPack mapPack : tempList) {
			mapPacks.add(mapPack);
		}
		tempList = null;
	}
}
