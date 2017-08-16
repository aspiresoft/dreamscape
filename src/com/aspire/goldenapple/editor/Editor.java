package com.aspire.goldenapple.editor;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.Level;
import com.aspire.goldenapple.game.unit.Flag;
import com.aspire.goldenapple.game.unit.Gate;
import com.aspire.goldenapple.game.unit.PlayerKnight;
import com.aspire.goldenapple.game.unit.PlayerNinja;
import com.aspire.goldenapple.game.unit.PlayerRobot;
import com.aspire.goldenapple.gui.state.State;
import com.aspire.goldenapple.io.CellData;
import com.aspire.goldenapple.io.Data;
import com.aspire.goldenapple.io.InputHandler;
import com.aspire.goldenapple.tools.MPManager;
import com.aspire.goldenapple.tools.SButton;

public class Editor extends State {
	public static final int TOOL_PENCIL_ID = 0;
	public static final int TOOL_LINE_ID = 1;
	public static final int TOOL_FILL_ID = 2;
	public static final int TOOL_SQUARE_ID = 3;
	public static final int TOOL_SELECT_ID = 4;
	public static final int TOOL_COPY_ID = 5;
	public static final int TOOL_CUT_ID = 6;
	public static final int TOOL_PASTE_ID = 7;
	public static final int TOOL_DELETE_ID = 8;

	public static final int TEXTURER_TILE_ID = 0;
	public static final int TEXTURER_OBJECT_ID = 1;
	public static final int TEXTURER_COLLISION_ID = 2;
	public static final int TEXTURER_DECOR_ID = 3;

	private SButton Tools[];
	private SButton Button_New;
	private SButton Button_Load;
	private SButton Button_Save;
	private SButton Button_Run;
	private SButton Button_Help;
	private SButton Button_Options;
	private SButton Button_MapManager;
	private SButton Button_Back;
	private SButton Layer_1;
	private SButton Layer_2;
	private SButton Layer_3;
	private SButton Warp_1;
	private SButton Warp_2;
	private SButton Warp_3;
	private SButton Texturer_Unit;
	private SButton Texturer_Tile;
	private SButton Texturer_Object;
	private SButton Texturer_Collision;

	private SButton ninjaGirl;
	private SButton robot;
	private SButton knight;

	private GridCell firstTexture;
	private GridCell secondTexture;

	private GridCellManager gridManager;
	private TextureCellManager textureManager;
	private Level testLevel;
	private Options options;

	private MPManager mapManager;
	private boolean managerMode;
	private boolean optionsMode;
	private boolean levelTestMode;
	private int BG_ID;
	private String level_name;

	private int selectedPlayerId;

	private HashMap<GridCell, Integer> enterDoors;
	private ArrayList<GridCell> exitDoors;

	public static int LAYER, TEXTURER, WARP, TOOL;

	public Editor(Program program) {
		super(program);

		selectedPlayerId = 1;

		setTools(new SButton[9]);
		for (int i = 0; i < getTools().length; i++) {
			getTools()[i] = new SButton(program, Constants.SPACE, Constants.SPACE * 4 + (i * Constants.SPACE * 2), Constants.SPACE * 2, Constants.SPACE * 2, 156 + i);
		}

		Button_New = new SButton(program, Constants.SPACE, Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 2, "NEW", Constants.MENU_FONT_SMALLER);
		Button_Load = new SButton(program, Button_New.getX() + Button_New.getWidth() + Constants.SPACE, Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 2, "LOAD", Constants.MENU_FONT_SMALLER);
		Button_Save = new SButton(program, Button_Load.getX() + Button_Load.getWidth() + Constants.SPACE, Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 2, "SAVE",
				Constants.MENU_FONT_SMALLER);
		Button_Run = new SButton(program, Button_Save.getX() + Button_Save.getWidth() + Constants.SPACE, Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 2, "RUN", Constants.MENU_FONT_SMALLER);
		Button_Help = new SButton(program, Button_Run.getX() + Button_Run.getWidth() + Constants.SPACE, Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 2, "HELP", Constants.MENU_FONT_SMALLER);
		Button_Options = new SButton(program, Button_Help.getX() + Button_Help.getWidth() + Constants.SPACE, Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 2, "OPTIONS",
				Constants.MENU_FONT_SMALLER);
		Button_MapManager = new SButton(program, Button_Options.getX() + Button_Options.getWidth() + Constants.SPACE, Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 2, "MAP", "MANAGER",
				Constants.MENU_FONT_SMALLER);
		Button_Back = new SButton(program, Button_MapManager.getX() + Button_MapManager.getWidth() + Constants.SPACE, Constants.SPACE, Constants.SPACE * 3, Constants.SPACE * 2, "BACK",
				Constants.MENU_FONT_SMALLER);

		Layer_1 = new SButton(program, Constants.SPACE * 4, getTools()[0].getY(), Constants.GRID_SIZE * 5 / 2, Constants.GRID_SIZE * 3 / 2, "1.", "Layer", Constants.MENU_FONT_SMALLER);
		Layer_2 = new SButton(program, Layer_1.getX() + Layer_1.getWidth() + Constants.GRID_SIZE, Layer_1.getY(), Constants.GRID_SIZE * 5 / 2, Constants.GRID_SIZE * 3 / 2, "2.", "Layer",
				Constants.MENU_FONT_SMALLER);
		Layer_3 = new SButton(program, Layer_2.getX() + Layer_2.getWidth() + Constants.GRID_SIZE, Layer_1.getY(), Constants.GRID_SIZE * 5 / 2, Constants.GRID_SIZE * 3 / 2, "3.", "Layer",
				Constants.MENU_FONT_SMALLER);

		Warp_1 = new SButton(program, Layer_3.getX() + Layer_3.getWidth() + Constants.GRID_SIZE, Layer_1.getY(), Constants.GRID_SIZE * 5 / 2, Constants.GRID_SIZE * 3 / 2, "1.Warp", "Zone",
				Constants.MENU_FONT_SMALLER);
		Warp_2 = new SButton(program, Warp_1.getX() + Warp_1.getWidth() + Constants.GRID_SIZE, Layer_1.getY(), Constants.GRID_SIZE * 5 / 2, Constants.GRID_SIZE * 3 / 2, "2.Warp", "Zone",
				Constants.MENU_FONT_SMALLER);
		Warp_3 = new SButton(program, Warp_2.getX() + Warp_2.getWidth() + Constants.GRID_SIZE, Layer_1.getY(), Constants.GRID_SIZE * 5 / 2, Constants.GRID_SIZE * 3 / 2, "3.Warp", "Zone",
				Constants.MENU_FONT_SMALLER);

		Texturer_Tile = new SButton(program, Constants.SPACE * 5 + Constants.GRID_SIZE * 20, Layer_1.getY(), Constants.GRID_SIZE * 7 / 4, Constants.GRID_SIZE * 3 / 4, "Terrain",
				Constants.MENU_FONT_SMALLEST);
		Texturer_Unit = new SButton(program, Texturer_Tile.getX() + Texturer_Tile.getWidth() + Constants.GRID_SIZE / 2, Texturer_Tile.getY(), Constants.GRID_SIZE * 7 / 4, Constants.GRID_SIZE * 3 / 4,
				"Object", Constants.MENU_FONT_SMALLEST);
		Texturer_Object = new SButton(program, Texturer_Tile.getX(), Texturer_Tile.getY() + Texturer_Tile.getHeight() + Constants.GRID_SIZE / 2, Constants.GRID_SIZE * 7 / 4,
				Constants.GRID_SIZE * 3 / 4, "Decor", Constants.MENU_FONT_SMALLEST);
		Texturer_Collision = new SButton(program, Texturer_Tile.getX() + Texturer_Tile.getWidth() + Constants.GRID_SIZE / 2, Texturer_Tile.getY() + Texturer_Tile.getHeight() + Constants.GRID_SIZE / 2,
				Constants.GRID_SIZE * 7 / 4, Constants.GRID_SIZE * 3 / 4, "Block", Constants.MENU_FONT_SMALLEST);

		setFirstTexture(
				new GridCell(Texturer_Unit.getX() + Texturer_Unit.getWidth() + Constants.GRID_SIZE / 4, Texturer_Unit.getY(), Constants.GRID_SIZE * 2, Constants.GRID_SIZE * 2, new CellData(), true));
		setSecondTexture(new GridCell(getFirstTexture().getX() + getFirstTexture().getWidth() + Constants.GRID_SIZE / 4, getFirstTexture().getY(), Constants.GRID_SIZE * 2, Constants.GRID_SIZE * 2,
				new CellData(), true));

		ninjaGirl = new SButton(program, Texturer_Unit.getX() + Texturer_Unit.getWidth() + Constants.SPACE * 2, Texturer_Unit.getY() + Constants.SPACE * 3, Constants.SPACE * 3, Constants.SPACE * 3,
				0);
		robot = new SButton(program, Texturer_Unit.getX() + Texturer_Unit.getWidth() + Constants.SPACE * 2, Texturer_Unit.getY() + Constants.SPACE * 7, Constants.SPACE * 3, Constants.SPACE * 3, 54);
		knight = new SButton(program, Texturer_Unit.getX() + Texturer_Unit.getWidth() + Constants.SPACE * 2, Texturer_Unit.getY() + Constants.SPACE * 11, Constants.SPACE * 3, Constants.SPACE * 3, 55);

		init();
	}

	public void init() {
		WARP = 0;
		TEXTURER = TEXTURER_TILE_ID;
		LAYER = 2;
		BG_ID = 2;
		level_name = "Untitled";
		TOOL = TOOL_PENCIL_ID;

		setEnterDoors(new HashMap<GridCell, Integer>());
		setExitDoors(new ArrayList<>());

		gridManager = new GridCellManager(this, Constants.SPACE * 4, Button_New.getY() + Button_New.getHeight() + Constants.SPACE * 4, Constants.GRID_SIZE * GridCellManager.MATRIX_X,
				Constants.GRID_SIZE * GridCellManager.MATRIX_Y);
		textureManager = new TextureCellManager(this, gridManager.getX() + gridManager.getWidth() + Constants.SPACE, gridManager.getY(), Constants.GRID_SIZE * TextureCellManager.MATRIX_X,
				Constants.GRID_SIZE * TextureCellManager.MATRIX_Y);
		mapManager = new MPManager(program);
		options = new Options(program, this);

		testLevel = null;
		setManagerMode(false);
		setOptionsMode(false);
		setLevelTestMode(false);

		setLastTime(System.currentTimeMillis());
	}

	public void update() {
		if (shouldUpdate) {
			if (isManagerMode() || isLevelTestMode() || isOptionsMode()) {
				if (isManagerMode()) {
					mapManager.update();
				}
				if (isLevelTestMode()) {
					testLevel.update();
				}
				if (isOptionsMode()) {
					options.update();
				}
			} else {
				Button_New.update();
				Button_Load.update();
				Button_Save.update();
				Button_Run.update();
				Button_Help.update();
				Button_MapManager.update();
				Button_Options.update();
				Button_Back.update();

				Layer_1.update();
				Layer_2.update();
				Layer_3.update();

				Warp_1.update();
				Warp_2.update();
				Warp_3.update();

				Texturer_Collision.update();
				Texturer_Object.update();
				Texturer_Tile.update();
				Texturer_Unit.update();

				gridManager.update();
				textureManager.update();

				for (int i = 0; i < getTools().length; i++) {
					getTools()[i].update();
				}

				ninjaGirl.update();
				robot.update();
				knight.update();

				if (InputHandler.mouseClicked) {
					if (System.currentTimeMillis() - getLastTime() > 250) {
						setLastTime(System.currentTimeMillis());

						if (new Rectangle(Texturer_Tile.getX(), Texturer_Unit.getY(), Texturer_Tile.getWidth(), Texturer_Tile.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
							InputHandler.clearInput();

							TEXTURER = TEXTURER_TILE_ID;
						}
						if (new Rectangle(Texturer_Unit.getX(), Texturer_Unit.getY(), Texturer_Unit.getWidth(), Texturer_Unit.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
							InputHandler.clearInput();

							TEXTURER = TEXTURER_OBJECT_ID;
						}
						if (new Rectangle(Texturer_Collision.getX(), Texturer_Collision.getY(), Texturer_Collision.getWidth(), Texturer_Collision.getHeight()).contains(InputHandler.mouseX,
								InputHandler.mouseY)) {
							InputHandler.clearInput();

							TEXTURER = TEXTURER_COLLISION_ID;
						}
						if (new Rectangle(Texturer_Object.getX(), Texturer_Object.getY(), Texturer_Object.getWidth(), Texturer_Object.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
							InputHandler.clearInput();

							TEXTURER = TEXTURER_DECOR_ID;
						}

						if (new Rectangle(ninjaGirl.getX(), ninjaGirl.getY(), ninjaGirl.getWidth(), ninjaGirl.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
							InputHandler.clearInput();

							selectedPlayerId = 1;
						}
						if (new Rectangle(robot.getX(), robot.getY(), robot.getWidth(), robot.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
							InputHandler.clearInput();

							selectedPlayerId = 2;
						}
						if (new Rectangle(knight.getX(), knight.getY(), knight.getWidth(), knight.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
							InputHandler.clearInput();

							selectedPlayerId = 3;
						}

						// NEW BUTTON
						if (new Rectangle(Button_New.getX(), Button_New.getY(), Button_New.getWidth(), Button_New.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
							InputHandler.clearInput();

							int dialogResult = JOptionPane.showConfirmDialog(null, "Non-saved data will be lost. Are you sure you want to create a new sheet?", "Warning", JOptionPane.YES_NO_OPTION);
							if (dialogResult == JOptionPane.YES_OPTION) {
								init();
							}
						}
						// LOAD BUTTON
						if (new Rectangle(Button_Load.getX(), Button_Load.getY(), Button_Load.getWidth(), Button_Load.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
							InputHandler.clearInput();

							JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir") + "/" + Constants.LEVEL_LOCATION));
							int returnValue = fileChooser.showOpenDialog(null);
							if (returnValue == JFileChooser.APPROVE_OPTION) {
								File selectedFile = fileChooser.getSelectedFile();
								String extension = "";

								int index = selectedFile.getName().lastIndexOf('.');
								if (index > 0) {
									extension = selectedFile.getName().substring(index + 1);
								}
								if (extension.equals(Constants.LEVEL_EXTENSION)) {
									Data d = null;
									try {
										ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(selectedFile));
										d = (Data) objectInputStream.readObject();
										objectInputStream.close();
									} catch (Exception e) {
										e.printStackTrace();
									}
									if (d != null) {
										for (int i = 0; i < Constants.MAP_SIZE_X; i++) {
											for (int j = 0; j < Constants.MAP_SIZE_Y; j++) {
												getGridManager().getLayer_1_Grids()[i][j].setData(d.getGrid_1()[i][j]);
												getGridManager().getLayer_2_Grids()[i][j].setData(d.getGrid_2()[i][j]);
												getGridManager().getLayer_3_Grids()[i][j].setData(d.getGrid_3()[i][j]);
											}
										}
										for (int i = 0; i < Constants.SCREEN_X; i++) {
											for (int j = 0; j < Constants.SCREEN_Y; j++) {
												getGridManager().getWarp_1_Grids()[i][j].setData(d.getGrid_parallel_1()[i][j]);
												getGridManager().getWarp_2_Grids()[i][j].setData(d.getGrid_parallel_2()[i][j]);
												getGridManager().getWarp_3_Grids()[i][j].setData(d.getGrid_parallel_3()[i][j]);
											}
										}
										BG_ID = d.getBg_id();
										level_name = d.getLevel_name();
										setEnterDoors(d.getDoorEntranceIDMAP());
										setExitDoors(d.getDoorExitIDMAP());
										JOptionPane.showMessageDialog(null, d.getName() + " has been succesfully loaded!", "Success", JOptionPane.INFORMATION_MESSAGE);
									}
								}
							}
						}
						// SAVE BUTTON
						if (new Rectangle(Button_Save.getX(), Button_Save.getY(), Button_Save.getWidth(), Button_Save.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
							InputHandler.clearInput();

							if (BG_ID == 0) {
								JOptionPane.showMessageDialog(null, "No background has been selected! Please specify it from options menu.", "Warning", JOptionPane.WARNING_MESSAGE);
							} else {
								if (level_name.isEmpty()) {
									JOptionPane.showMessageDialog(null, "Level name has not been set! Please specify it from options menu.", "Warning", JOptionPane.WARNING_MESSAGE);
								} else {
									JFileChooser c = new JFileChooser(new File(System.getProperty("user.dir") + "/" + Constants.LEVEL_LOCATION));
									int returnValue = c.showSaveDialog(null);
									if (returnValue == JFileChooser.APPROVE_OPTION) {
										Data d = new Data();
										d.setLevel_name(level_name);
										d.setBg_id(BG_ID);
										d.setDoorEntranceIDMAP(getEnterDoors());
										d.setDoorExitIDMAP(getExitDoors());
										for (int i = 0; i < Constants.MAP_SIZE_X; i++) {
											for (int j = 0; j < Constants.MAP_SIZE_Y; j++) {
												d.getGrid_1()[i][j] = getGridManager().getLayer_1_Grids()[i][j].getData();
												d.getGrid_2()[i][j] = getGridManager().getLayer_2_Grids()[i][j].getData();
												d.getGrid_3()[i][j] = getGridManager().getLayer_3_Grids()[i][j].getData();
											}
										}
										for (int i = 0; i < Constants.SCREEN_X; i++) {
											for (int j = 0; j < Constants.SCREEN_Y; j++) {
												d.getGrid_parallel_1()[i][j] = getGridManager().getWarp_1_Grids()[i][j].getData();
												d.getGrid_parallel_2()[i][j] = getGridManager().getWarp_2_Grids()[i][j].getData();
												d.getGrid_parallel_3()[i][j] = getGridManager().getWarp_3_Grids()[i][j].getData();
											}
										}
										File f = c.getSelectedFile();
										if (f != null && f.exists()) {
											int response = JOptionPane.showConfirmDialog(null, "The file " + f.getName() + " already exists. Do you want to replace the existing file?",
													"Overwrite file", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
											if (response == JOptionPane.YES_OPTION) {
												d.setName(f.getName().substring(0, f.getName().length() - 4));
												try {
													ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(f));
													objectOutputStream.writeObject(d);
													objectOutputStream.flush();
													objectOutputStream.close();

													JOptionPane.showMessageDialog(null, d.getName() + " has been succesfully saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
												} catch (Exception e) {
													e.printStackTrace();
												}
											}
										} else {
											try {
												String name = f.getName();
												ObjectOutputStream objectOutputStream = null;
												if (name.endsWith(".lvl")) {
													objectOutputStream = new ObjectOutputStream(new FileOutputStream(f));

													d.setName(name.substring(0, name.length() - 4));
												} else {
													objectOutputStream = new ObjectOutputStream(
															new FileOutputStream(new File(Constants.LEVEL_LOCATION + "/" + name + "." + Constants.LEVEL_EXTENSION)));
													d.setName(name);
												}
												objectOutputStream.writeObject(d);
												objectOutputStream.flush();
												objectOutputStream.close();
												JOptionPane.showMessageDialog(null, d.getName() + " has been succesfully saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}
								}
							}
						}
						// RUn BUTTON
						if (new Rectangle(Button_Run.getX(), Button_Run.getY(), Button_Run.getWidth(), Button_Run.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
							InputHandler.clearInput();
							ArrayList<GridCell> grids = new ArrayList<GridCell>();
							grids = getGridManager().countOfSpecifiedId(0, 2, PlayerNinja.CODE);
							if (grids.size() == 0) {
								JOptionPane.showMessageDialog(null, "No Ninja Girl unit has been found. Please make sure your Ninja Girl unit has been placed!", "Warning",
										JOptionPane.WARNING_MESSAGE);
							} else {
								if (grids.size() != 1) {
									JOptionPane.showMessageDialog(null, "There can be only 1 Ninja Girl unit placed. Please make sure you have only 1 Ninja Girl unit placed!", "Warning",
											JOptionPane.WARNING_MESSAGE);
								} else {
									grids.clear();
									grids = getGridManager().countOfSpecifiedId(0, 2, Gate.CODE);
									if (grids.size() == 0) {
										JOptionPane.showMessageDialog(null, "No Exit Gate unit has been found. Please make sure your Exit Gate unit has been placed!", "Warning",
												JOptionPane.WARNING_MESSAGE);
									} else {
										if (grids.size() != 1) {
											JOptionPane.showMessageDialog(null, "There can be only 1 Exit Gate unit placed. Please make sure you have only 1 Exit Gate unit placed!", "Warning",
													JOptionPane.WARNING_MESSAGE);
										} else {
											grids.clear();
											grids = getGridManager().countOfSpecifiedId(0, 2, Flag.CODE1);
											if (grids.size() > 1) {
												JOptionPane.showMessageDialog(null, "There can be only 1 Blue Flag unit placed. Please make sure you have only 1 Blue Flag unit placed!", "Warning",
														JOptionPane.WARNING_MESSAGE);
											} else {
												grids.clear();
												grids = getGridManager().countOfSpecifiedId(0, 2, Flag.CODE2);
												if (grids.size() > 1) {
													JOptionPane.showMessageDialog(null, "There can be only 1 Green Flag unit placed. Please make sure you have only 1 Green Flag unit placed!",
															"Warning", JOptionPane.WARNING_MESSAGE);
												} else {
													grids.clear();
													grids = getGridManager().countOfSpecifiedId(0, 2, Flag.CODE3);
													if (grids.size() > 1) {
														JOptionPane.showMessageDialog(null, "There can be only 1 Red Flag unit placed. Please make sure you have only 1 Red Flag unit placed!",
																"Warning", JOptionPane.WARNING_MESSAGE);
													} else {
														grids.clear();
														grids = getGridManager().countOfSpecifiedId(0, 2, Flag.CODE4);
														if (grids.size() > 1) {
															JOptionPane.showMessageDialog(null,
																	"There can be only 1 Yellow Flag unit placed. Please make sure you have only 1 Yellow Flag unit placed!", "Warning",
																	JOptionPane.WARNING_MESSAGE);
														} else {
															if (BG_ID == 0) {
																JOptionPane.showMessageDialog(null, "No background has been selected! Please specify it from options menu.", "Warning",
																		JOptionPane.WARNING_MESSAGE);
															} else {
																if (level_name.isEmpty()) {
																	JOptionPane.showMessageDialog(null, "Level name has not been set! Please specify it from options menu.", "Warning",
																			JOptionPane.WARNING_MESSAGE);
																} else {
																	grids.clear();
																	grids = getGridManager().countOfSpecifiedId(0, 2, PlayerRobot.CODE);
																	if (grids.size() == 0) {
																		JOptionPane.showMessageDialog(null, "No Robot unit has been found. Please make sure your Robot unit has been placed!",
																				"Warning", JOptionPane.WARNING_MESSAGE);
																	} else {
																		if (grids.size() != 1) {
																			JOptionPane.showMessageDialog(null,
																					"There can be only 1 Robot unit placed. Please make sure you have only 1 Robot unit placed!", "Warning",
																					JOptionPane.WARNING_MESSAGE);
																		} else {
																			grids.clear();
																			grids = getGridManager().countOfSpecifiedId(0, 2, PlayerKnight.CODE);
																			if (grids.size() == 0) {
																				JOptionPane.showMessageDialog(null, "No Knight unit has been found. Please make sure your Knight unit has been placed!",
																						"Warning", JOptionPane.WARNING_MESSAGE);
																			} else {
																				if (grids.size() != 1) {
																					JOptionPane.showMessageDialog(null,
																							"There can be only 1 Knight unit placed. Please make sure you have only 1 Knight unit placed!", "Warning",
																							JOptionPane.WARNING_MESSAGE);
																				} else {
																					boolean ninja = false, robot = false, knight = false;
																					if (selectedPlayerId == 1) {
																						ninja = true;
																						grids.clear();
																						grids = getGridManager().countOfSpecifiedId(0, 2, PlayerKnight.CODE);
																						grids.get(0).setData(new CellData(grids.get(0).getData().getTILE_ID(), 0, grids.get(0).getData().getCOL_ID(),
																								grids.get(0).getData().getOBJ_ID()));

																						grids.clear();
																						grids = getGridManager().countOfSpecifiedId(0, 2, PlayerRobot.CODE);
																						grids.get(0).setData(new CellData(grids.get(0).getData().getTILE_ID(), 0, grids.get(0).getData().getCOL_ID(),
																								grids.get(0).getData().getOBJ_ID()));
																					} else if (selectedPlayerId == 2) {
																						robot = true;
																						grids.clear();
																						grids = getGridManager().countOfSpecifiedId(0, 2, PlayerNinja.CODE);
																						grids.get(0).setData(new CellData(grids.get(0).getData().getTILE_ID(), 0, grids.get(0).getData().getCOL_ID(),
																								grids.get(0).getData().getOBJ_ID()));

																						grids.clear();
																						grids = getGridManager().countOfSpecifiedId(0, 2, PlayerKnight.CODE);
																						grids.get(0).setData(new CellData(grids.get(0).getData().getTILE_ID(), 0, grids.get(0).getData().getCOL_ID(),
																								grids.get(0).getData().getOBJ_ID()));
																					} else if (selectedPlayerId == 3) {
																						knight = true;
																						grids.clear();
																						grids = getGridManager().countOfSpecifiedId(0, 2, PlayerNinja.CODE);
																						grids.get(0).setData(new CellData(grids.get(0).getData().getTILE_ID(), 0, grids.get(0).getData().getCOL_ID(),
																								grids.get(0).getData().getOBJ_ID()));

																						grids.clear();
																						grids = getGridManager().countOfSpecifiedId(0, 2, PlayerRobot.CODE);
																						grids.get(0).setData(new CellData(grids.get(0).getData().getTILE_ID(), 0, grids.get(0).getData().getCOL_ID(),
																								grids.get(0).getData().getOBJ_ID()));
																					}
																					Level l = new Level(program, true, ninja, robot, knight);
																					l.setShouldDraw(true);
																					l.setShouldUpdate(true);
																					Data d = new Data();
																					d.setGameGrids(getGridManager().getLayer_1_Grids(), getGridManager().getLayer_2_Grids(),
																							getGridManager().getLayer_3_Grids(), getGridManager().getWarp_1_Grids(), getGridManager().getWarp_2_Grids(),
																							getGridManager().getWarp_3_Grids());
																					d.setLevel_name(getLevel_name());
																					d.setBg_id(getBG_ID());
																					d.setDoorEntranceIDMAP(enterDoors);
																					d.setDoorExitIDMAP(getExitDoors());
																					l.getGamePanel().setGameGrids(d);
																					l.getGamePanel().handleKeys();
																					setTestLevel(l);
																					levelTestMode = true;
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					// HELP BUTTON
					if (new Rectangle(Button_Help.getX(), Button_Help.getY(), Button_Help.getWidth(), Button_Help.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();

						JOptionPane.showMessageDialog(null, "Such help very amaze wow!", "Doge", JOptionPane.OK_OPTION);
					}
					// OPTIONS BUTTON
					if (new Rectangle(Button_Options.getX(), Button_Options.getY(), Button_Options.getWidth(), Button_Options.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();
						options.init();
						setOptionsMode(true);
					}
					// MAPPER BUTTON
					if (new Rectangle(Button_MapManager.getX(), Button_MapManager.getY(), Button_MapManager.getWidth(), Button_MapManager.getHeight()).contains(InputHandler.mouseX,
							InputHandler.mouseY)) {
						InputHandler.clearInput();

						mapManager.init();
						setManagerMode(true);
					}
					// BACK BUTTON
					if (new Rectangle(Button_Back.getX(), Button_Back.getY(), Button_Back.getWidth(), Button_Back.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();

						int dialogResult = JOptionPane.showConfirmDialog(null, "Non-saved data will be lost. Are you sure you want to return to the main menu?", "Warning", JOptionPane.YES_NO_OPTION);
						if (dialogResult == JOptionPane.YES_OPTION) {
							try {
								program.getManager().setState(1);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					// WARP1 BUTTON
					if (new Rectangle(Warp_1.getX(), Warp_1.getY(), Warp_1.getWidth(), Warp_1.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();

						WARP = 1;
						getGridManager().handleMatrixVisibility();
					}
					// WARP2 BUTTON
					if (new Rectangle(Warp_2.getX(), Warp_2.getY(), Warp_2.getWidth(), Warp_2.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();

						WARP = 2;
						getGridManager().handleMatrixVisibility();
					}
					// WARP3 BUTTON
					if (new Rectangle(Warp_3.getX(), Warp_3.getY(), Warp_3.getWidth(), Warp_3.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();

						WARP = 3;
						getGridManager().handleMatrixVisibility();
					}
					// LAYER1 BUTTON
					if (new Rectangle(Layer_1.getX(), Layer_1.getY(), Layer_1.getWidth(), Layer_1.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();

						WARP = 0;
						LAYER = 1;
						getGridManager().handleMatrixVisibility();
					}
					// LAYER2 BUTTON
					if (new Rectangle(Layer_2.getX(), Layer_2.getY(), Layer_2.getWidth(), Layer_2.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();

						WARP = 0;
						LAYER = 2;
						getGridManager().handleMatrixVisibility();
					}
					// LAYER1 BUTTON
					if (new Rectangle(Layer_3.getX(), Layer_3.getY(), Layer_3.getWidth(), Layer_3.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
						InputHandler.clearInput();

						WARP = 0;
						LAYER = 3;
						getGridManager().handleMatrixVisibility();
					}
					for (int i = 0; i < 5; i++) {
						if (new Rectangle(getTools()[i].getX(), getTools()[i].getY(), getTools()[i].getWidth(), getTools()[i].getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY)) {
							InputHandler.clearInput();

							switch (i) {
							case 0:
								TOOL = TOOL_PENCIL_ID;
								break;
							case 1:
								TOOL = TOOL_LINE_ID;
								break;
							case 2:
								TOOL = TOOL_FILL_ID;
								break;
							case 3:
								TOOL = TOOL_SQUARE_ID;
								break;
							case 4:
								TOOL = TOOL_SELECT_ID;
								break;
							}
							break;
						}
					}
				}
			}
		}
	}

	public void draw(Graphics g) {
		if (shouldDraw) {
			g.drawImage(Constants.BG, 0, 0, Constants.SIZE.width, Constants.SIZE.height, null);
			if (isManagerMode() || isLevelTestMode() || isOptionsMode()) {
				if (isManagerMode()) {
					mapManager.draw(g);
				}
				if (isLevelTestMode()) {
					testLevel.draw(g);
				}
				if (isOptionsMode()) {
					options.draw(g);
				}
			} else {
				g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
				if (WARP == 0) {
					if (LAYER == 1) {
						g.fillRect(Layer_1.getX(), Layer_1.getY(), Layer_1.getWidth(), Layer_1.getHeight());
					}
					if (LAYER == 2) {
						g.fillRect(Layer_2.getX(), Layer_2.getY(), Layer_2.getWidth(), Layer_3.getHeight());
					}
					if (LAYER == 3) {
						g.fillRect(Layer_3.getX(), Layer_3.getY(), Layer_3.getWidth(), Layer_3.getHeight());
					}
				} else {
					if (WARP == 1) {
						g.fillRect(Warp_1.getX(), Warp_1.getY(), Warp_1.getWidth(), Warp_1.getHeight());
					}
					if (WARP == 2) {
						g.fillRect(Warp_2.getX(), Warp_2.getY(), Warp_2.getWidth(), Warp_2.getHeight());
					}
					if (WARP == 3) {
						g.fillRect(Warp_3.getX(), Warp_3.getY(), Warp_3.getWidth(), Warp_3.getHeight());
					}
				}
				if (TEXTURER == TEXTURER_TILE_ID) {
					g.fillRect(Texturer_Tile.getX(), Texturer_Tile.getY(), Texturer_Tile.getWidth(), Texturer_Tile.getHeight());
				}
				if (TEXTURER == TEXTURER_OBJECT_ID) {
					g.fillRect(Texturer_Unit.getX(), Texturer_Unit.getY(), Texturer_Unit.getWidth(), Texturer_Unit.getHeight());
				}
				if (TEXTURER == TEXTURER_COLLISION_ID) {
					g.fillRect(Texturer_Collision.getX(), Texturer_Collision.getY(), Texturer_Collision.getWidth(), Texturer_Collision.getHeight());
				}
				if (TEXTURER == TEXTURER_DECOR_ID) {
					g.fillRect(Texturer_Object.getX(), Texturer_Object.getY(), Texturer_Object.getWidth(), Texturer_Object.getHeight());
				}

				if (selectedPlayerId == 1) {
					g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
					g.fillRect(ninjaGirl.getX(), ninjaGirl.getY(), ninjaGirl.getWidth(), ninjaGirl.getHeight());
				} else if (selectedPlayerId == 2) {
					g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
					g.fillRect(robot.getX(), robot.getY(), robot.getWidth(), robot.getHeight());
				} else if (selectedPlayerId == 3) {
					g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
					g.fillRect(knight.getX(), knight.getY(), knight.getWidth(), knight.getHeight());
				}

				Button_New.draw(g);
				Button_Load.draw(g);
				Button_Save.draw(g);
				Button_Run.draw(g);
				Button_Help.draw(g);
				Button_MapManager.draw(g);
				Button_Options.draw(g);
				Button_Back.draw(g);

				Layer_1.draw(g);
				Layer_2.draw(g);
				Layer_3.draw(g);

				Warp_1.draw(g);
				Warp_2.draw(g);
				Warp_3.draw(g);

				Texturer_Collision.draw(g);
				Texturer_Object.draw(g);
				Texturer_Tile.draw(g);
				Texturer_Unit.draw(g);

				getFirstTexture().draw(g);
				getSecondTexture().draw(g);

				gridManager.draw(g);
				textureManager.draw(g);

				for (int i = 0; i < getTools().length; i++) {
					getTools()[i].draw(g);
				}

				if (TOOL == TOOL_PENCIL_ID) {
					g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
					g.fillRect(getTools()[0].getX(), getTools()[0].getY(), getTools()[0].getWidth(), getTools()[0].getHeight());
					g.setColor(Constants.SELECTED_COLOR);
					g.drawRect(getTools()[0].getX(), getTools()[0].getY(), getTools()[0].getWidth(), getTools()[0].getHeight());
				}
				if (TOOL == TOOL_LINE_ID) {
					g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
					g.fillRect(getTools()[1].getX(), getTools()[1].getY(), getTools()[1].getWidth(), getTools()[1].getHeight());
					g.setColor(Constants.SELECTED_COLOR);
					g.drawRect(getTools()[1].getX(), getTools()[1].getY(), getTools()[1].getWidth(), getTools()[1].getHeight());
				}
				if (TOOL == TOOL_FILL_ID) {
					g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
					g.fillRect(getTools()[2].getX(), getTools()[2].getY(), getTools()[2].getWidth(), getTools()[2].getHeight());
					g.setColor(Constants.SELECTED_COLOR);
					g.drawRect(getTools()[2].getX(), getTools()[2].getY(), getTools()[2].getWidth(), getTools()[2].getHeight());
				}
				if (TOOL == TOOL_SQUARE_ID) {
					g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
					g.fillRect(getTools()[3].getX(), getTools()[3].getY(), getTools()[0].getWidth(), getTools()[3].getHeight());
					g.setColor(Constants.SELECTED_COLOR);
					g.drawRect(getTools()[3].getX(), getTools()[3].getY(), getTools()[0].getWidth(), getTools()[3].getHeight());
				}
				if (TOOL == TOOL_SELECT_ID) {
					g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
					g.fillRect(getTools()[4].getX(), getTools()[4].getY(), getTools()[4].getWidth(), getTools()[4].getHeight());
					g.setColor(Constants.SELECTED_COLOR);
					g.drawRect(getTools()[4].getX(), getTools()[4].getY(), getTools()[4].getWidth(), getTools()[4].getHeight());
				}

				ninjaGirl.draw(g);
				robot.draw(g);
				knight.draw(g);

				// SATIR SAYILARI
				for (int i = 0; i < Constants.SCREEN_X; i++) {
					GridCell temp = gridManager.findSelectedGrid();
					g.setColor(Constants.MAIN_COLOR);
					if (temp != null) {
						if ((temp.getY() - gridManager.getY()) / Constants.GRID_SIZE == i) {
							g.setColor(Constants.SELECTED_COLOR);
						}
					}
					Engine.drawCenteredString(g, WARP != 0 ? (i + 1) + "" : Engine.zeroFiller((((gridManager.getVericalIndcator() - 1) * GridCellManager.GRID_MOVE_AMOUNT + i) + 1), 3) + "",
							new Rectangle(gridManager.getX() - Constants.SPACE * 7 / 8, gridManager.getY() + i * Constants.GRID_SIZE, Constants.SPACE * 7 / 8, Constants.GRID_SIZE),
							Constants.MENU_FONT_MOST_SMALLEST);
				}
				// SUTUN SAYILARI
				for (int i = 0; i < Constants.SCREEN_Y; i++) {
					GridCell temp = gridManager.findSelectedGrid();
					g.setColor(Constants.MAIN_COLOR);
					if (temp != null) {
						if ((temp.getX() - gridManager.getX()) / Constants.GRID_SIZE == i) {
							g.setColor(Constants.SELECTED_COLOR);
						}
					}
					Engine.drawCenteredString(g, WARP != 0 ? (i + 1) + "" : Engine.zeroFiller((((gridManager.getHorizontalIndcator() - 1) * GridCellManager.GRID_MOVE_AMOUNT + i) + 1), 3) + "",
							new Rectangle(gridManager.getX() + i * Constants.GRID_SIZE, gridManager.getY() - Constants.SPACE * 3 / 4, Constants.GRID_SIZE, Constants.SPACE * 3 / 4),
							Constants.MENU_FONT_MOST_SMALLEST);
				}
			}
		}

	}

	public GridCellManager getGridManager() {
		return gridManager;
	}

	public void setGridManager(GridCellManager gridManager) {
		this.gridManager = gridManager;
	}

	public TextureCellManager getTextureManager() {
		return textureManager;
	}

	public void setTextureManager(TextureCellManager textureManager) {
		this.textureManager = textureManager;
	}

	public Level getTestLevel() {
		return testLevel;
	}

	public void setTestLevel(Level testLevel) {
		this.testLevel = testLevel;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public MPManager getMapManager() {
		return mapManager;
	}

	public void setMapManager(MPManager mapManager) {
		this.mapManager = mapManager;
	}

	public boolean isManagerMode() {
		return managerMode;
	}

	public void setManagerMode(boolean managerMode) {
		this.managerMode = managerMode;
	}

	public int getBG_ID() {
		return BG_ID;
	}

	public void setBG_ID(int bG_ID) {
		BG_ID = bG_ID;
	}

	public String getLevel_name() {
		return level_name;
	}

	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}

	public boolean isOptionsMode() {
		return optionsMode;
	}

	public void setOptionsMode(boolean optionsMode) {
		this.optionsMode = optionsMode;
	}

	public boolean isLevelTestMode() {
		return levelTestMode;
	}

	public void setLevelTestMode(boolean levelTestMode) {
		this.levelTestMode = levelTestMode;
	}

	public SButton[] getTools() {
		return Tools;
	}

	public void setTools(SButton tools[]) {
		Tools = tools;
	}

	public GridCell getFirstTexture() {
		return firstTexture;
	}

	public void setFirstTexture(GridCell firstTexture) {
		this.firstTexture = firstTexture;
	}

	public GridCell getSecondTexture() {
		return secondTexture;
	}

	public void setSecondTexture(GridCell secondTexture) {
		this.secondTexture = secondTexture;
	}

	public HashMap<GridCell, Integer> getEnterDoors() {
		return enterDoors;
	}

	public void setEnterDoors(HashMap<GridCell, Integer> enterDoors) {
		this.enterDoors = enterDoors;
	}

	public ArrayList<GridCell> getExitDoors() {
		return exitDoors;
	}

	public void setExitDoors(ArrayList<GridCell> exitDoors) {
		this.exitDoors = exitDoors;
	}

}
