package com.aspire.goldenapple.tools;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.io.Data;
import com.aspire.goldenapple.io.InputHandler;

public class MPManager
{
	private static final int	DISPLAY_COUNT	= 23;
	private Program				program;
	private long				lastTime;

	private SButton				NEW, LOAD, SAVE, BACK;
	private SButton				ADD, REMOVE, MOVE_UP, MOVE_DOWN;
	private SButton				RIGHT, LEFT;

	private int					selectedLevelIndex, currentIndex;
	private MapPack				selectedMapPack;

	public MPManager(Program program)
	{
		this.program = program;
		NEW = new SButton(program, Constants.SPACE, Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 2, "NEW", Constants.MENU_FONT_SMALLER);
		LOAD = new SButton(program, NEW.getX() + NEW.getWidth() + Constants.SPACE, Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 2, "LOAD",
						Constants.MENU_FONT_SMALLER);
		SAVE = new SButton(program, LOAD.getX() + LOAD.getWidth() + Constants.SPACE, Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 2,
						"SAVE", Constants.MENU_FONT_SMALLER);
		BACK = new SButton(program, SAVE.getX() + SAVE.getWidth() + Constants.SPACE, Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 2,
						"BACK", Constants.MENU_FONT_SMALLER);

		ADD = new SButton(program, NEW.getX() + Constants.SPACE * 19 + Constants.SPACE, NEW.getY() + NEW.getHeight() + Constants.SPACE + 3,
						Constants.SPACE * 4, Constants.SPACE * 2, "ADD", Constants.MENU_FONT_SMALLER);
		REMOVE = new SButton(program, NEW.getX() + Constants.SPACE * 19 + Constants.SPACE, ADD.getY() + ADD.getHeight() + Constants.SPACE,
						Constants.SPACE * 4, Constants.SPACE * 2, "REMOVE", Constants.MENU_FONT_SMALLER);
		MOVE_UP = new SButton(program, REMOVE.getX(), REMOVE.getY() + REMOVE.getHeight() + Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 2,
						"+", Constants.MENU_FONT_SMALLER);
		MOVE_DOWN = new SButton(program, MOVE_UP.getX(), MOVE_UP.getY() + MOVE_UP.getHeight() + Constants.SPACE, Constants.SPACE * 4,
						Constants.SPACE * 2, "-", Constants.MENU_FONT_SMALLER);
		RIGHT = new SButton(program, MOVE_DOWN.getX(), MOVE_DOWN.getY() + MOVE_DOWN.getHeight() + Constants.SPACE + Constants.SPACE * 3,
						Constants.SPACE * 4, Constants.SPACE * 2, ">", Constants.MENU_FONT_SMALLER);
		LEFT = new SButton(program, RIGHT.getX(), RIGHT.getY() + RIGHT.getHeight() + Constants.SPACE, Constants.SPACE * 4, Constants.SPACE * 2, "<",
						Constants.MENU_FONT_SMALLER);

		init();
	}

	public void update()
	{
		NEW.update();
		LOAD.update();
		SAVE.update();
		BACK.update();
		ADD.update();
		REMOVE.update();
		MOVE_UP.update();
		MOVE_DOWN.update();
		RIGHT.update();
		LEFT.update();

		if (InputHandler.mouseClicked)
		{
			if (System.currentTimeMillis() - lastTime > 200)
			{

				lastTime = System.currentTimeMillis();

				// NEW SButton CLICK
				// ================================================================
				if (new Rectangle(NEW.getX(), NEW.getY(), NEW.getWidth(), NEW.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY))
				{
					InputHandler.clearInput();
					String name = JOptionPane.showInputDialog("Enter the name of the new MapPack");
					if (name == null || name.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Error occured while creating mappack.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						MapPack m = new MapPack(name);
						try
						{
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(
											new FileOutputStream(new File(System.getProperty("user.dir") + "/" + Constants.MAPPACK_LOCATION + "/"
															+ name + "." + Constants.MAPPACK_EXTENSION)));
							objectOutputStream.writeObject(m);
							objectOutputStream.flush();
							objectOutputStream.close();
						}
						catch (Exception e)
						{
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error occured while creating mappack.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						selectedMapPack = m;
						selectedLevelIndex = -1;
					}
				}
				// LOAD SButton CLICK
				// ================================================================
				if (new Rectangle(LOAD.getX(), LOAD.getY(), LOAD.getWidth(), LOAD.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY))
				{
					InputHandler.clearInput();
					selectedMapPack = null;
					JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir") + "/" + Constants.MAPPACK_LOCATION));
					int returnValue = fileChooser.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION)
					{
						File selectedFile = fileChooser.getSelectedFile();
						String extension = "";

						int index = selectedFile.getName().lastIndexOf('.');
						if (index > 0)
						{
							extension = selectedFile.getName().substring(index + 1);
						}
						if (extension.equals(Constants.MAPPACK_EXTENSION))
						{
							try
							{
								ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(selectedFile));
								selectedMapPack = (MapPack) objectInputStream.readObject();
								objectInputStream.close();
								selectedLevelIndex = -1;
								JOptionPane.showMessageDialog(null, selectedMapPack.name + " has been succesfully loaded!", "Success",
												JOptionPane.INFORMATION_MESSAGE);
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
							if (selectedMapPack == null)
							{
								JOptionPane.showMessageDialog(null, "Error occured while opening mappack.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
				// SAVE SButton CLICK
				// ================================================================
				if (new Rectangle(SAVE.getX(), SAVE.getY(), SAVE.getWidth(), SAVE.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY))
				{
					InputHandler.clearInput();
					JFileChooser c = new JFileChooser(new File(System.getProperty("user.dir") + "/" + Constants.MAPPACK_LOCATION));
					int returnValue = c.showSaveDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION)
					{
						File f = c.getSelectedFile();
						if (f != null && f.exists())
						{
							int response = JOptionPane.showConfirmDialog(null,
											"The file " + f.getName() + " already exists. Do you want to overwrite the existing file?",
											"Ovewrite file", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
							if (response == JOptionPane.YES_OPTION)
							{
								selectedMapPack.name = f.getName().substring(0, f.getName().length() - 4);
								try
								{
									ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(f));
									objectOutputStream.writeObject(selectedMapPack);
									objectOutputStream.flush();
									objectOutputStream.close();
									JOptionPane.showMessageDialog(null, selectedMapPack.name + " has been succesfully saved!", "Success",
													JOptionPane.INFORMATION_MESSAGE);
								}
								catch (Exception e)
								{
									e.printStackTrace();
									JOptionPane.showMessageDialog(null, "Error occured while saving mappack.", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}
						}
						else
						{
							try
							{
								String name = f.getName();
								ObjectOutputStream objectOutputStream = null;
								if (name.endsWith(Constants.MAPPACK_EXTENSION))
								{
									objectOutputStream = new ObjectOutputStream(new FileOutputStream(f));
									selectedMapPack.name = f.getName().substring(0, f.getName().length() - 4);
								}
								else
								{
									objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(System.getProperty("user.dir") + "/"
													+ Constants.MAPPACK_LOCATION + "/" + name + "." + Constants.MAPPACK_EXTENSION)));
									selectedMapPack.name = f.getName();
								}
								objectOutputStream.writeObject(selectedMapPack);
								objectOutputStream.flush();
								objectOutputStream.close();
							}
							catch (Exception e)
							{
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, "Error occured while saving mappack.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
				// BACK SButton CLICK
				// ================================================================
				if (new Rectangle(BACK.getX(), BACK.getY(), BACK.getWidth(), BACK.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY))
				{
					InputHandler.clearInput();
					int dialogResult = JOptionPane.showConfirmDialog(null, "Any unsaved changes will be lost. Are you sure to exit?", "Warning",
									JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION)
					{
						// program.getManager().getEs().setMapManager(null);
						// this = null;
						program.getManager().getEs().setManagerMode(false);
					}
				}
				// ADD SButton CLICK
				// ================================================================
				if (new Rectangle(ADD.getX(), ADD.getY(), ADD.getWidth(), ADD.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY))
				{
					InputHandler.clearInput();
					if (selectedMapPack == null)
					{
						JOptionPane.showMessageDialog(null, "Please select a mappack first!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir") + "/" + Constants.LEVEL_LOCATION));
						int returnValue = fileChooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION)
						{
							File selectedFile = fileChooser.getSelectedFile();
							String extension = "";

							int index = selectedFile.getName().lastIndexOf('.');
							if (index > 0)
							{
								extension = selectedFile.getName().substring(index + 1);
							}
							if (extension.equals(Constants.LEVEL_EXTENSION))
							{
								Data d = null;
								try
								{
									ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(selectedFile));
									d = (Data) objectInputStream.readObject();
									objectInputStream.close();
								}
								catch (Exception e)
								{
									e.printStackTrace();
								}
								if (d != null)
								{
									selectedMapPack.levels.add(d);
									selectedLevelIndex = selectedMapPack.levels.size() - 1;
									// currentIndex =
									// Math.abs(selectedMapPack.levels.size() -
									// 1 / DISPLAY_COUNT) + 1;
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Error occured while opening level.", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}
						}

					}
				}
				// REMOVE SButton CLICK
				// ================================================================
				if (new Rectangle(REMOVE.getX(), REMOVE.getY(), REMOVE.getWidth(), REMOVE.getHeight()).contains(InputHandler.mouseX,
								InputHandler.mouseY))
				{
					InputHandler.clearInput();
					if (selectedMapPack != null && selectedLevelIndex != -1)
					{
						selectedMapPack.levels.remove(selectedLevelIndex);
						selectedLevelIndex = -1;
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Error occured while removing level.", "Error", JOptionPane.ERROR_MESSAGE);
					}

				}
				// MOVE UP SButton CLICK
				// ================================================================
				if (new Rectangle(MOVE_UP.getX(), MOVE_UP.getY(), MOVE_UP.getWidth(), MOVE_UP.getHeight()).contains(InputHandler.mouseX,
								InputHandler.mouseY))
				{
					if (selectedMapPack != null)
					{
						if (selectedLevelIndex != -1 && selectedLevelIndex > 0)
						{
							Collections.swap(selectedMapPack.levels, selectedLevelIndex, selectedLevelIndex - 1);
							selectedLevelIndex--;
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Error occured while moving level.", "Error", JOptionPane.ERROR_MESSAGE);
					}

				}
				// MOVE DOWN SButton CLICK
				// ================================================================
				if (new Rectangle(MOVE_DOWN.getX(), MOVE_DOWN.getY(), MOVE_DOWN.getWidth(), MOVE_DOWN.getHeight()).contains(InputHandler.mouseX,
								InputHandler.mouseY))
				{
					if (selectedMapPack != null)
					{
						if (selectedLevelIndex != -1 && selectedLevelIndex < selectedMapPack.levels.size() - 1)
						{
							Collections.swap(selectedMapPack.levels, selectedLevelIndex, selectedLevelIndex + 1);
							selectedLevelIndex++;
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Error occured while moving level.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (selectedMapPack != null && selectedMapPack.levels.size() > DISPLAY_COUNT)
				{
					// RIGHT SButton CLICK
					// ================================================================
					if (new Rectangle(RIGHT.getX(), RIGHT.getY(), RIGHT.getWidth(), RIGHT.getHeight()).contains(InputHandler.mouseX,
									InputHandler.mouseY))
					{
						if (selectedMapPack != null)
						{
							if (currentIndex < (Math.abs(selectedMapPack.levels.size() - 1) / DISPLAY_COUNT) + 1)
							{
								currentIndex++;
							}
						}
					}
					// LEFT SButton CLICK
					// ================================================================
					if (new Rectangle(LEFT.getX(), LEFT.getY(), LEFT.getWidth(), LEFT.getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY))
					{
						if (selectedMapPack != null)
						{
							if (currentIndex > 1)
							{
								currentIndex--;
							}
						}
					}
				}
				// HANDLE SELECTED LEVEL
				// ================================================================
				if (new Rectangle(NEW.getX(), NEW.getY() + NEW.getHeight() + Constants.SPACE, Constants.SPACE * 19, Constants.SPACE * DISPLAY_COUNT)
								.contains(InputHandler.mouseX, InputHandler.mouseY))
				{
					if (selectedMapPack != null && selectedMapPack.levels.size() > 0)
					{
						for (int i = 0; i < selectedMapPack.levels.size(); i++)
						{
							if ((i / DISPLAY_COUNT) + 1 == currentIndex && new Rectangle(NEW.getX(),
											NEW.getY() + NEW.getHeight() + Constants.SPACE + Constants.SPACE * ((i) % DISPLAY_COUNT),
											Constants.SPACE * 19, Constants.SPACE).contains(InputHandler.mouseX, InputHandler.mouseY))
							{
								selectedLevelIndex = i;
								break;
							}
						}
					}
				}
			}
		}
	}

	public void draw(java.awt.Graphics g)
	{
		NEW.draw(g);
		LOAD.draw(g);
		SAVE.draw(g);
		BACK.draw(g);
		ADD.draw(g);
		REMOVE.draw(g);
		MOVE_UP.draw(g);
		MOVE_DOWN.draw(g);

		// DRAW NAME OF THE MAPPACK
		// ================================================================
		g.setFont(Constants.MENU_FONT_SMALLER);
		g.setColor(Constants.MAIN_COLOR);
		if (selectedMapPack == null)
		{
			g.drawString("No mappack has been opened!", ADD.getX(), Constants.SPACE * 3);
		}
		else
		{
			g.drawString(selectedMapPack.name + " has been opened", ADD.getX(), Constants.SPACE * 3);
		}

		// DRAW INDEXERS
		// ================================================================
		if (selectedMapPack != null && selectedMapPack.levels.size() > DISPLAY_COUNT)
		{
			RIGHT.draw(g);
			LEFT.draw(g);
			g.setColor(Constants.MAIN_COLOR);
			Engine.drawCenteredString(g, currentIndex + " / " + (Math.abs((selectedMapPack.levels.size() - 1) / DISPLAY_COUNT) + 1),
							new Rectangle(ADD.getX(), LEFT.getY() + LEFT.getHeight(), Constants.SPACE * 4, Constants.SPACE * 3),
							Constants.MENU_FONT_SMALLER);

		}

		// DRAW LEVELS IN THE SELECTED MAPPACK
		// ================================================================
		if (selectedMapPack != null)
		{
			for (int i = 0; i < selectedMapPack.levels.size(); i++)
			{
				if ((i / DISPLAY_COUNT) + 1 == currentIndex)
				{
					if (i == selectedLevelIndex)
					{
						g.setColor(Constants.SELECTED_COLOR);
					}
					else
					{
						g.setColor(Constants.MAIN_COLOR);
					}
					Engine.drawCenteredString(g, selectedMapPack.levels.get(i).getName(),
									new Rectangle(NEW.getX(), NEW.getY() + NEW.getHeight() + Constants.SPACE * (i % DISPLAY_COUNT) + Constants.SPACE,
													Constants.SPACE * 19, Constants.SPACE),
									Constants.MENU_FONT_SMALLER);
				}
			}
		}
		/*
		// DRAW PREVIEW OF THE SELECTED LEVEL
		// ================================================================
		g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
		g.fillRect(0, Constants.SIZE.height - (Constants.WIDTH * Constants.SCALE) / (Constants.MAP_SIZE_X / Constants.MAP_SIZE_Y) - 28, 800, 80);

		if (selectedLevelIndex != -1)
		{
			int width = Constants.WIDTH * Constants.SCALE;
			int height = (width) / (Constants.MAP_SIZE_X / Constants.MAP_SIZE_Y);
			int x = 0;
			int y = Constants.SIZE.height - height - 28;
			int size_x = width / Constants.MAP_SIZE_X;
			int size_Y = height / Constants.MAP_SIZE_Y;

			for (int i = 0; i < Constants.MAP_SIZE_X; i++)
			{
				for (int j = 0; j < Constants.MAP_SIZE_Y; j++)
				{
					if (selectedMapPack.levels.get(selectedLevelIndex).getGrid_2()[i][j].getCOL_ID() == 1)
					{
						g.setColor(Constants.MAIN_COLOR);
						g.fillRect(x + i * size_x, y + j * size_Y, size_x, size_Y);
					}
				}
			}
		}
		
		g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
		g.fillRect(NEW.getX(), NEW.getY() + NEW.getHeight() + Constants.SPACE, Constants.SPACE * 19, Constants.SPACE * DISPLAY_COUNT);

*/
	}

	public void init()
	{
		lastTime = System.currentTimeMillis();
		selectedMapPack = null;
		selectedLevelIndex = -1;
		currentIndex = 1;
	}

}
