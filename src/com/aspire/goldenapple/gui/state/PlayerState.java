package com.aspire.goldenapple.gui.state;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.Level;
import com.aspire.goldenapple.io.InputHandler;

public class PlayerState extends State
{
	public static long			incRate;
	public static int			currentLevel, currentScore, currentHealth, scoreGathered;

	private ArrayList<Level>	levels;
	private boolean				gonext, canGoNext;
	private boolean				completed;

	public PlayerState(Program program)
	{
		super(program);
		init();
	}

	public void update()
	{
		if (shouldUpdate)
		{
			boolean enter = InputHandler.keys[KeyEvent.VK_ENTER];

			if (completed)
			{
				if (enter)
				{
					gonext = false;
					completed = false;
					program.getManager().setState(1);
				}
			}
			else
			{
				if (getLevels().get(currentLevel).getGamePanel().isWon() || getLevels().get(currentLevel).getGamePanel().isLost())
				{
					if (canGoNext && enter)
					{
						InputHandler.clearInput();
						lastTime = System.currentTimeMillis();
						gonext = true;
					}
					if (scoreGathered > 0)
					{
						scoreGathered -= incRate;
					}
					else
					{
						canGoNext = true;
					}
					if (gonext && canGoNext)
					{
						canGoNext = false;
						gonext = false;
						if (getLevels().get(currentLevel).getGamePanel().isLost())
						{
							if (getLevels().get(currentLevel).getMp() != null)
							{
								getLevels().get(currentLevel).getMp().stop();
							}
							program.getManager().setState(1);
						}
						if (getLevels().get(currentLevel).getGamePanel().isWon())
						{
							getLevels().get(currentLevel).getGamePanel().setWon(false);
							getLevels().get(currentLevel).setShouldUpdate(false);

							if (currentLevel == getLevels().size() - 1)
							{
								completed = true;
								InputHandler.clearInput();
								if (getLevels().get(currentLevel).getMp() != null)
								{
									getLevels().get(currentLevel).getMp().stop();
								}
							}
							else
							{
								scoreGathered = 0;
								getLevels().get(currentLevel).setShouldDraw(false);
								currentLevel++;
								getLevels().get(currentLevel).setShouldDraw(true);
								getLevels().get(currentLevel).setShouldUpdate(true);
							}
						}
					}
				}
				else
				{
					if (currentLevel < getLevels().size())
					{
						getLevels().get(currentLevel).update();
					}
				}
			}
		}
	}

	public void draw(java.awt.Graphics g)
	{
		if (shouldDraw)
		{
			getLevels().get(currentLevel).draw(g);

			if (completed)
			{

				g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
				g.fillRect(0, 0, Constants.SIZE.width, Constants.SIZE.height);

				g.setColor(Constants.MAIN_COLOR);
				Engine.drawCenteredString(g, "Congratulations", new Rectangle(0, 100, 800, 100), Constants.BIG_FONT);
				Engine.drawCenteredString(g, "You have finished the map pack", new Rectangle(0, 200, 800, 100), Constants.MENU_FONT_BIG);
				Engine.drawCenteredString(g, "Score: " + currentScore, new Rectangle(0, 300, 800, 100), Constants.BIG_FONT);
				Engine.drawCenteredString(g, "Press Enter to return main menu", new Rectangle(0, 400, 800, 100), Constants.MENU_FONT_BIG);
			}
			else
			{
				if (getLevels().get(currentLevel).getGamePanel().isWon() || getLevels().get(currentLevel).getGamePanel().isLost())
				{
					if (getLevels().get(currentLevel).getGamePanel().isWon())
					{
						g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
						g.fillRect(0, 0, Constants.SIZE.width, Constants.SIZE.height);

						g.setColor(Constants.STEEL_BLUE_COLOR);
						if (scoreGathered > 0)
						{
							Engine.drawCenteredString(g, "Score: " + (currentScore - scoreGathered), new Rectangle(0, 200, 800, 100),
											Constants.BIG_FONT);
						}
						else
						{
							Engine.drawCenteredString(g, "Score: " + (currentScore), new Rectangle(0, 200, 800, 100), Constants.BIG_FONT);
							Engine.drawCenteredString(g, "Press Enter for next level", new Rectangle(0, 300, 800, 100), Constants.MENU_FONT_BIG);
						}

					}
					else
					{
						g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
						g.fillRect(0, 0, Constants.SIZE.width, Constants.SIZE.height);

						g.setFont(Constants.BIG_FONT);
						g.setColor(Constants.STEEL_BLUE_COLOR);
						if (scoreGathered > 0)
						{
							Engine.drawCenteredString(g, "Score: " + (currentScore - scoreGathered), new Rectangle(0, 200, 800, 100),
											Constants.BIG_FONT);
						}
						else
						{
							Engine.drawCenteredString(g, "YOU LOST", new Rectangle(0, 100, 800, 100), Constants.BIG_FONT);
							Engine.drawCenteredString(g, "Score: " + (currentScore), new Rectangle(0, 200, 800, 100), Constants.BIG_FONT);
							Engine.drawCenteredString(g, "Press Enter to return main menu", new Rectangle(0, 300, 800, 100), Constants.MENU_FONT_BIG);
						}
					}
				}
			}
		}
	}

	public void init()
	{
		completed = false;
		gonext = false;
		canGoNext = false;
		currentHealth = 10;
		currentScore = 0;
		setLevels(new ArrayList<Level>());
		currentLevel = 0;
		scoreGathered = 0;
		lastTime = System.currentTimeMillis();
	}

	public ArrayList<Level> getLevels()
	{
		return levels;
	}

	public void setLevels(ArrayList<Level> levels)
	{
		this.levels = levels;
	}
}
