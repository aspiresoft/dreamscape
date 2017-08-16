package com.aspire.goldenapple.editor;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.unit.Door;
import com.aspire.goldenapple.io.InputHandler;
import com.aspire.goldenapple.tools.SButton;

public class Options
{
	private Editor				editor;
	private Program				program;

	private SButton				NAME;
	private SButton				BG_DESERT;
	private SButton				BG_GARDEN;
	private SButton				BG_GRAVE;
	private SButton				BG_WINTER;
	private SButton				SAVE;
	private SButton				CHANGE_DOOR;
	private SButton				DOOR_SAVE;

	private String				name;
	private int					bg_id;

	private long				lastTime;
	private boolean				doorMode;

	private ArrayList<GridCell>	entranceDoors;
	private ArrayList<GridCell>	exitDoors;

	private int					selectedIndexEntrance;
	private int					selectedIndexExit;

	private ArrayList<Point>	indexes;

	public Options(Program program, Editor editor)
	{
		this.program = program;
		this.editor = editor;

		NAME = new SButton(program, Constants.SPACE * 20 - Constants.SPACE * 6, Constants.SPACE * 6, Constants.SPACE * 12, Constants.SPACE * 2,
						"EDIT LEVEL NAME", Constants.MENU_FONT_SMALLER);

		BG_DESERT = new SButton(program, Constants.SPACE * 20 - Constants.SPACE * 12, Constants.SPACE * 13, Constants.SPACE * 8, Constants.SPACE * 2,
						"DESERT", Constants.MENU_FONT_SMALLER);
		BG_GARDEN = new SButton(program, Constants.SPACE * 20 + Constants.SPACE * 4, Constants.SPACE * 13, Constants.SPACE * 8, Constants.SPACE * 2,
						"GARDEN", Constants.MENU_FONT_SMALLER);
		BG_GRAVE = new SButton(program, Constants.SPACE * 20 - Constants.SPACE * 12, Constants.SPACE * 16, Constants.SPACE * 8, Constants.SPACE * 2,
						"GRAVE", Constants.MENU_FONT_SMALLER);
		BG_WINTER = new SButton(program, Constants.SPACE * 20 + Constants.SPACE * 4, Constants.SPACE * 16, Constants.SPACE * 8, Constants.SPACE * 2,
						"WINTER", Constants.MENU_FONT_SMALLER);
		SAVE = new SButton(program, Constants.SPACE * 20 - Constants.SPACE * 6, Constants.SPACE * 28, Constants.SPACE * 12, Constants.SPACE * 2,
						"SAVE AND EXIT", Constants.MENU_FONT_SMALLER);
		CHANGE_DOOR = new SButton(program, Constants.SPACE * 20 - Constants.SPACE * 6, Constants.SPACE * 23, Constants.SPACE * 12,
						Constants.SPACE * 2, "CHANGE DOORS MATCHING", Constants.MENU_FONT_SMALLER);

		DOOR_SAVE = new SButton(program, Constants.SPACE * 20 - Constants.SPACE * 6, Constants.SPACE * 23, Constants.SPACE * 12, Constants.SPACE * 2,
						"SAVE AND EXIT", Constants.MENU_FONT_SMALLER);
		init();
	}

	public void init()
	{
		lastTime = System.currentTimeMillis();
		name = editor.getLevel_name();
		bg_id = editor.getBG_ID();
		doorMode = false;
		editor.getEnterDoors().clear();
		editor.getExitDoors().clear();
	}

	private void doorModeInit()
	{
		entranceDoors = new ArrayList<GridCell>();
		exitDoors = new ArrayList<GridCell>();
		selectedIndexEntrance = -1;
		selectedIndexExit = -1;
		indexes = new ArrayList<Point>();
	}

	public void update()
	{
		if (!doorMode)
		{
			NAME.update();
			BG_DESERT.update();
			BG_GARDEN.update();
			BG_GRAVE.update();
			BG_WINTER.update();
			SAVE.update();
			CHANGE_DOOR.update();
		}
		else
		{
			DOOR_SAVE.update();

		}

		if (InputHandler.keys[KeyEvent.VK_ESCAPE])
		{
			InputHandler.clearInput();
			int dialogResult = JOptionPane.showConfirmDialog(null, "Any unsaved changes will be lost. Are you sure you want to return to the editor?",
							"Warning", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION)
			{
				if (doorMode)
				{
					doorMode = false;
				}
				else
				{
					program.getManager().getEs().isOptionsMode();
					doorMode = false;
				}
			}
		}

		if (InputHandler.mouseClicked)
		{
			if (System.currentTimeMillis() - lastTime > 200)
			{
				lastTime = System.currentTimeMillis();
				if (!doorMode)
				{
					if (new Rectangle(NAME.getX(), NAME.getY(), NAME.getWidth(), NAME.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY))
					{
						InputHandler.clearInput();
						String text = JOptionPane.showInputDialog(null, "Type the new name of the level", "Level Name", JOptionPane.QUESTION_MESSAGE);
						if (text != null)
						{
							if (text.isEmpty())
							{
								JOptionPane.showMessageDialog(null, "Level name cannot be empty", "Error", JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								name = text;
							}
						}
					}
					if (new Rectangle(BG_DESERT.getX(), BG_DESERT.getY(), BG_DESERT.getWidth(), BG_DESERT.getHeight()).contains(InputHandler.mouseX,
									InputHandler.mouseY))
					{
						InputHandler.clearInput();
						bg_id = 1;
					}
					if (new Rectangle(BG_GARDEN.getX(), BG_GARDEN.getY(), BG_GARDEN.getWidth(), BG_GARDEN.getHeight()).contains(InputHandler.mouseX,
									InputHandler.mouseY))
					{
						InputHandler.clearInput();
						bg_id = 2;
					}
					if (new Rectangle(BG_GRAVE.getX(), BG_GRAVE.getY(), BG_GRAVE.getWidth(), BG_GRAVE.getHeight()).contains(InputHandler.mouseX,
									InputHandler.mouseY))
					{
						InputHandler.clearInput();
						bg_id = 3;
					}
					if (new Rectangle(BG_WINTER.getX(), BG_WINTER.getY(), BG_WINTER.getWidth(), BG_WINTER.getHeight()).contains(InputHandler.mouseX,
									InputHandler.mouseY))
					{
						InputHandler.clearInput();
						bg_id = 4;
					}
					if (new Rectangle(SAVE.getX(), SAVE.getY(), SAVE.getWidth(), SAVE.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY))
					{
						InputHandler.clearInput();
						if (!name.isEmpty())
						{
							editor.setLevel_name(name);
						}
						if (bg_id != 0)
						{
							editor.setBG_ID(bg_id);
						}
						JOptionPane.showMessageDialog(null, "Changes have been successfully saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
						editor.setOptionsMode(false);
					}
					if (new Rectangle(CHANGE_DOOR.getX(), CHANGE_DOOR.getY(), CHANGE_DOOR.getWidth(), CHANGE_DOOR.getHeight())
									.contains(InputHandler.mouseX, InputHandler.mouseY))
					{
						InputHandler.clearInput();
						int dialogResult = JOptionPane.showConfirmDialog(null, "Old ID's will be lost. Are you sure you want to continue?", "Warning",
										JOptionPane.YES_NO_OPTION);
						if (dialogResult == JOptionPane.YES_OPTION)
						{
							doorModeInit();
							doorMode = true;
							loadDoors();
						}
					}
				}
				else
				{

					if (new Rectangle(Constants.SPACE, Constants.SPACE, Constants.SPACE * 18, Constants.SPACE * 20).contains(InputHandler.mouseX,
									InputHandler.mouseY))
					{
						for (int i = 0; i < entranceDoors.size(); i++)
						{
							if (new Rectangle(Constants.SPACE * 2, Constants.SPACE * 3 + Constants.SPACE * 2 * i, Constants.SPACE * 17,
											Constants.SPACE * 2).contains(InputHandler.mouseX, InputHandler.mouseY))
							{
								for (Point p : indexes)
								{
									if (p.x == i)
									{
										p.x = -1;
										p.y = -1;
									}
								}
								for (Iterator<Point> iter = indexes.iterator(); iter.hasNext();)
								{
									Point p = iter.next();
									if (p.x == -1 || p.y == -1)
									{
										iter.remove();
									}
								}
								if (selectedIndexExit == -1)
								{
									if (selectedIndexEntrance == i)
									{
										selectedIndexEntrance = -1;
									}
									else
									{
										selectedIndexEntrance = i;
									}
									break;
								}
								else
								{
									selectedIndexEntrance = i;
									boolean flag = false;
									for (Point p : indexes)
									{
										if (p.y == selectedIndexExit)
										{
											p.x = selectedIndexEntrance;
											selectedIndexEntrance = -1;
											selectedIndexExit = -1;
											flag = true;
											break;
										}
									}
									if (!flag)
									{
										indexes.add(new Point(selectedIndexEntrance, selectedIndexExit));
										selectedIndexEntrance = -1;
										selectedIndexExit = -1;
									}
								}
							}

						}
					}
					if (new Rectangle(Constants.SPACE * 21, Constants.SPACE, Constants.SPACE * 18, Constants.SPACE * 20).contains(InputHandler.mouseX,
									InputHandler.mouseY))
					{
						for (int i = 0; i < exitDoors.size(); i++)
						{
							if (new Rectangle(Constants.SPACE * 22, Constants.SPACE * 3 + Constants.SPACE * 2 * i, Constants.SPACE * 17,
											Constants.SPACE * 2).contains(InputHandler.mouseX, InputHandler.mouseY))
							{
								for (Point p : indexes)
								{
									if (p.y == i)
									{
										p.x = -1;
										p.y = -1;
										break;
									}
								}
								for (Iterator<Point> iter = indexes.iterator(); iter.hasNext();)
								{
									Point p = iter.next();
									if (p.x == -1 || p.y == -1)
									{
										iter.remove();
									}
								}
								if (selectedIndexEntrance == -1)
								{
									if (selectedIndexExit == i)
									{
										selectedIndexExit = -1;
									}
									else
									{
										selectedIndexExit = i;
									}
									break;
								}
								else
								{
									boolean flag = false;
									selectedIndexExit = i;
									for (Point p : indexes)
									{
										if (p.x == selectedIndexEntrance)
										{
											p.y = selectedIndexExit;
											selectedIndexEntrance = -1;
											selectedIndexExit = -1;
											flag = true;
											break;
										}
									}
									if (!flag)
									{
										indexes.add(new Point(selectedIndexEntrance, selectedIndexExit));
										selectedIndexEntrance = -1;
										selectedIndexExit = -1;
									}
								}
							}

						}
					}
					if (new Rectangle(DOOR_SAVE.getX(), DOOR_SAVE.getY(), DOOR_SAVE.getWidth(), DOOR_SAVE.getHeight()).contains(InputHandler.mouseX,
									InputHandler.mouseY))
					{
						InputHandler.clearInput();

						if (entranceDoors.isEmpty() || exitDoors.isEmpty())
						{
							doorMode = false;
						}
						else
						{
							if (entranceDoors.size() != exitDoors.size())
							{
								JOptionPane.showMessageDialog(null, "All doors must have a match. Please make sure you matched every single door.",
												"Warning", JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								if (indexes.size() != entranceDoors.size())
								{
									JOptionPane.showMessageDialog(null,
													"All doors must have a match. Please make sure you matched every single door.", "Warning",
													JOptionPane.WARNING_MESSAGE);
								}
								else
								{
									editor.getEnterDoors().clear();
									editor.getExitDoors().clear();

									for (int i = 0; i < indexes.size(); i++)
									{
										entranceDoors.get(i).setHeight(indexes.get(i).x);
										exitDoors.get(i).setHeight(i);
									}
									for (int i = 0; i < indexes.size(); i++)
									{
										editor.getEnterDoors().put(entranceDoors.get(i), indexes.get(i).y);
										editor.getExitDoors().add(exitDoors.get(i));
									}
									doorMode = false;
								}
							}
						}
					}
				}
			}
		}
	}

	public void draw(java.awt.Graphics g)
	{
		if (!doorMode)
		{
			g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
			g.fillRect(Constants.SPACE, Constants.SPACE, Constants.SPACE * 38, Constants.SPACE * 8);

			g.setColor(Constants.MAIN_COLOR);
			Engine.drawCenteredString(g, "LEVEL NAME", new Rectangle(Constants.SPACE, Constants.SPACE * 2, Constants.SPACE * 38, Constants.SPACE),
							Constants.MENU_FONT_BIG);

			if (name == null || name.isEmpty())
			{
				Engine.drawCenteredString(g, "This level has no name specified.",
								new Rectangle(Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 38, Constants.SPACE),
								Constants.MENU_FONT_SMALLER);
			}
			else
			{
				Engine.drawCenteredString(g, name, new Rectangle(Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 38, Constants.SPACE),
								Constants.MENU_FONT_SMALLER);
			}

			NAME.draw(g);

			g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
			g.fillRect(Constants.SPACE, Constants.SPACE * 10, Constants.SPACE * 38, Constants.SPACE * 9);

			g.setColor(Constants.MAIN_COLOR);
			Engine.drawCenteredString(g, "BACKGROUND", new Rectangle(Constants.SPACE, Constants.SPACE * 11, Constants.SPACE * 38, Constants.SPACE),
							Constants.MENU_FONT_BIG);

			if (bg_id == 1)
			{
				g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
				g.fillRect(BG_DESERT.getX(), BG_DESERT.getY(), BG_DESERT.getWidth(), BG_DESERT.getHeight());
			}
			if (bg_id == 2)
			{
				g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
				g.fillRect(BG_GARDEN.getX(), BG_GARDEN.getY(), BG_GARDEN.getWidth(), BG_GARDEN.getHeight());
			}
			if (bg_id == 3)
			{
				g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
				g.fillRect(BG_GRAVE.getX(), BG_GRAVE.getY(), BG_GRAVE.getWidth(), BG_GRAVE.getHeight());
			}
			if (bg_id == 4)
			{
				g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
				g.fillRect(BG_WINTER.getX(), BG_WINTER.getY(), BG_WINTER.getWidth(), BG_WINTER.getHeight());
			}

			BG_DESERT.draw(g);
			BG_GARDEN.draw(g);
			BG_GRAVE.draw(g);
			BG_WINTER.draw(g);

			g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
			g.fillRect(Constants.SPACE, Constants.SPACE * 20, Constants.SPACE * 38, Constants.SPACE * 6);

			g.setColor(Constants.MAIN_COLOR);
			Engine.drawCenteredString(g, "DOORS", new Rectangle(Constants.SPACE, Constants.SPACE * 21, Constants.SPACE * 38, Constants.SPACE),
							Constants.MENU_FONT_BIG);

			g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
			g.fillRect(Constants.SPACE, Constants.SPACE * 27, Constants.SPACE * 38, Constants.SPACE * 4);
			CHANGE_DOOR.draw(g);

			SAVE.draw(g);
		}
		else
		{
			g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
			g.fillRect(Constants.SPACE, Constants.SPACE, Constants.SPACE * 18, Constants.SPACE * 20);

			g.setColor(Constants.MAIN_COLOR);
			Engine.drawCenteredString(g, "ENTRANCE DOORS",
							new Rectangle(Constants.SPACE * 1, Constants.SPACE, Constants.SPACE * 18, Constants.SPACE * 2), Constants.MENU_FONT_HUGE);

			g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
			g.fillRect(Constants.SPACE * 21, Constants.SPACE, Constants.SPACE * 18, Constants.SPACE * 20);

			g.setColor(Constants.MAIN_COLOR);
			Engine.drawCenteredString(g, "EXIT DOORS",
							new Rectangle(Constants.SPACE * 21, Constants.SPACE, Constants.SPACE * 18, Constants.SPACE * 2),
							Constants.MENU_FONT_HUGE);

			for (int i = 0; i < entranceDoors.size(); i++)
			{
				GridCell grid = entranceDoors.get(i);
				int x = grid.getX() - 4;
				int y = grid.getY() + 1;
				if (selectedIndexEntrance == i)
				{
					g.setColor(Constants.SELECTED_COLOR);
				}
				else
				{
					g.setColor(Constants.MAIN_COLOR);
				}
				if (grid.getWidth() == 0)
				{
					Engine.drawCenteredString(g,
									"[DEFAULT WORLD] " + x + " - " + y, new Rectangle(Constants.SPACE * 1,
													Constants.SPACE * 3 + Constants.SPACE * 2 * i, Constants.SPACE * 18, Constants.SPACE * 2),
									Constants.NORMAL_FONT);
				}
				if (grid.getWidth() == 1)
				{
					Engine.drawCenteredString(g,
									"[WARP ZONE 1] " + x + " - " + y, new Rectangle(Constants.SPACE * 1,
													Constants.SPACE * 3 + Constants.SPACE * 2 * i, Constants.SPACE * 18, Constants.SPACE * 2),
									Constants.NORMAL_FONT);
				}
				if (grid.getWidth() == 2)
				{
					Engine.drawCenteredString(g,
									"[WARP ZONE 2] " + x + " - " + y, new Rectangle(Constants.SPACE * 1,
													Constants.SPACE * 3 + Constants.SPACE * 2 * i, Constants.SPACE * 18, Constants.SPACE * 2),
									Constants.NORMAL_FONT);
				}
				if (grid.getWidth() == 3)
				{
					Engine.drawCenteredString(g,
									"[WARP ZONE 3] " + x + " - " + y, new Rectangle(Constants.SPACE * 1,
													Constants.SPACE * 3 + Constants.SPACE * 2 * i, Constants.SPACE * 18, Constants.SPACE * 2),
									Constants.NORMAL_FONT);
				}

			}
			for (int i = 0; i < exitDoors.size(); i++)
			{
				GridCell grid = exitDoors.get(i);
				int x = grid.getX() - 4;
				int y = grid.getY() + 1;
				if (selectedIndexExit == i)
				{
					g.setColor(Constants.SELECTED_COLOR);
				}
				else
				{
					g.setColor(Constants.MAIN_COLOR);
				}
				if (grid.getWidth() == 0)
				{
					Engine.drawCenteredString(g,
									"[DEFAULT WORLD] " + x + " - " + y, new Rectangle(Constants.SPACE * 21,
													Constants.SPACE * 3 + Constants.SPACE * 2 * i, Constants.SPACE * 18, Constants.SPACE * 2),
									Constants.NORMAL_FONT);
				}
				if (grid.getWidth() == 1)
				{
					Engine.drawCenteredString(g,
									"[WARP ZONE 1] " + x + " - " + y, new Rectangle(Constants.SPACE * 21,
													Constants.SPACE * 3 + Constants.SPACE * 2 * i, Constants.SPACE * 18, Constants.SPACE * 2),
									Constants.NORMAL_FONT);
				}
				if (grid.getWidth() == 2)
				{
					Engine.drawCenteredString(g,
									"[WARP ZONE 2] " + x + " - " + y, new Rectangle(Constants.SPACE * 21,
													Constants.SPACE * 3 + Constants.SPACE * 2 * i, Constants.SPACE * 18, Constants.SPACE * 2),
									Constants.NORMAL_FONT);
				}
				if (grid.getWidth() == 3)
				{
					Engine.drawCenteredString(g,
									"[WARP ZONE 3] " + x + " - " + y, new Rectangle(Constants.SPACE * 21,
													Constants.SPACE * 3 + Constants.SPACE * 2 * i, Constants.SPACE * 18, Constants.SPACE * 2),
									Constants.NORMAL_FONT);
				}

			}
			for (int i = 0; i < indexes.size(); i++)
			{
				Point p = indexes.get(i);
				int x0 = Constants.SPACE * 19;
				int y0 = Constants.SPACE * 4 + Constants.SPACE * 2 * p.x;

				int x1 = Constants.SPACE * 21;
				int y1 = Constants.SPACE * 4 + Constants.SPACE * 2 * p.y;

				g.setColor(Constants.SELECTED_COLOR);
				g.drawLine(x0, y0, x1, y1);

				g.setColor(Constants.SELECTED_COLOR);
				g.drawRect(Constants.SPACE, Constants.SPACE * 3 + Constants.SPACE * 2 * p.x, Constants.SPACE * 18, Constants.SPACE * 2);
				g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
				g.fillRect(Constants.SPACE, Constants.SPACE * 3 + Constants.SPACE * 2 * p.x, Constants.SPACE * 18, Constants.SPACE * 2);

				g.setColor(Constants.SELECTED_COLOR);
				g.drawRect(Constants.SPACE * 21, Constants.SPACE * 3 + Constants.SPACE * 2 * p.y, Constants.SPACE * 18, Constants.SPACE * 2);
				g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
				g.fillRect(Constants.SPACE * 21, Constants.SPACE * 3 + Constants.SPACE * 2 * p.y, Constants.SPACE * 18, Constants.SPACE * 2);

			}
			DOOR_SAVE.draw(g);
		}
	}

	private void loadDoors()
	{
		entranceDoors.clear();
		exitDoors.clear();

		ArrayList<GridCell> tempDoors = editor.getGridManager().countOfSpecifiedId(0, 2, Door.CODE4);
		for (GridCell gridCell : tempDoors)
		{
			int mX = editor.getGridManager().getGridCoordinates(gridCell).x;
			int mY = editor.getGridManager().getGridCoordinates(gridCell).y;
			entranceDoors.add(new GridCell(mX, mY, 0, -1, null, false));
		}
		tempDoors = editor.getGridManager().countOfSpecifiedId(1, 0, Door.CODE4);
		for (GridCell gridCell : tempDoors)
		{
			int mX = editor.getGridManager().getParallelGridCoordinates(gridCell, 1).x;
			int mY = editor.getGridManager().getParallelGridCoordinates(gridCell, 1).y;
			entranceDoors.add(new GridCell(mX + 5, mY, 1, -1, null, false));
		}
		tempDoors = editor.getGridManager().countOfSpecifiedId(2, 0, Door.CODE4);
		for (GridCell gridCell : tempDoors)
		{
			int mX = editor.getGridManager().getParallelGridCoordinates(gridCell, 2).x;
			int mY = editor.getGridManager().getParallelGridCoordinates(gridCell, 2).y;
			entranceDoors.add(new GridCell(mX + 5, mY, 2, -1, null, false));
		}
		tempDoors = editor.getGridManager().countOfSpecifiedId(3, 0, Door.CODE4);
		for (GridCell gridCell : tempDoors)
		{
			int mX = editor.getGridManager().getParallelGridCoordinates(gridCell, 3).x;
			int mY = editor.getGridManager().getParallelGridCoordinates(gridCell, 3).y;
			entranceDoors.add(new GridCell(mX + 5, mY, 3, -1, null, false));
		}
		tempDoors = editor.getGridManager().countOfSpecifiedId(0, 2, Door.CODE2);
		for (GridCell gridCell : tempDoors)
		{
			int mX = editor.getGridManager().getGridCoordinates(gridCell).x;
			int mY = editor.getGridManager().getGridCoordinates(gridCell).y;
			exitDoors.add(new GridCell(mX, mY, 0, -1, null, false));
		}
		tempDoors = editor.getGridManager().countOfSpecifiedId(1, 0, Door.CODE2);
		for (GridCell gridCell : tempDoors)
		{
			int mX = editor.getGridManager().getParallelGridCoordinates(gridCell, 1).x;
			int mY = editor.getGridManager().getParallelGridCoordinates(gridCell, 1).y;
			exitDoors.add(new GridCell(mX + 5, mY, 1, -1, null, false));
		}
		tempDoors = editor.getGridManager().countOfSpecifiedId(2, 0, Door.CODE2);
		for (GridCell gridCell : tempDoors)
		{
			int mX = editor.getGridManager().getParallelGridCoordinates(gridCell, 2).x;
			int mY = editor.getGridManager().getParallelGridCoordinates(gridCell, 2).y;
			exitDoors.add(new GridCell(mX + 5, mY, 2, -1, null, false));
		}
		tempDoors = editor.getGridManager().countOfSpecifiedId(3, 0, Door.CODE2);
		for (GridCell gridCell : tempDoors)
		{
			int mX = editor.getGridManager().getParallelGridCoordinates(gridCell, 3).x;
			int mY = editor.getGridManager().getParallelGridCoordinates(gridCell, 3).y;
			exitDoors.add(new GridCell(mX + 5, mY, 3, -1, null, false));
		}

	}

}
