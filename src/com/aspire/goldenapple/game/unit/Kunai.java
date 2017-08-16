package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import java.util.Iterator;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.GamePanel;

public class Kunai extends Unit
{
	private static final int	DISTANCE	= Constants.BRICK_SIZE * 20;
	private static final int	SPEED		= 8;
	private GamePanel			gamePanel;
	private int					targetLoc;
	private boolean				direction;

	public Kunai(Program program, GamePanel gamePanel, int x, int y, int width, int height, boolean direction, int status, int priority)
	{
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;
		this.direction = direction;
		shouldWork = true;
		isAnEntity = false;

		if (this.direction)
			targetLoc = Engine.safeX(x + width + DISTANCE);
		else
			targetLoc = Engine.safeX(x - DISTANCE);

	}

	public void update()
	{
		if (shouldWork)
		{
			for (int i = 1; i <= SPEED; i++)
			{
				Iterator<Unit> iter = gamePanel.getUnits().iterator();
				while (iter.hasNext())
				{
					Unit u = iter.next();
					if (u instanceof Box)
					{
						Box b = (Box) u;
						if (new Rectangle(x - 1, y - 1, width + 2, height + 2).intersects(new Rectangle(b.x, b.y, b.width, b.height)))
						{
							b.die();
						}
						b = null;
					}
					if (u != this && u.isAnEntity && u.alive
									&& (new Rectangle(x, y, width, height).intersects(new Rectangle(u.x, u.y, u.width, u.height))))
					{
						u.die();
					}
					u = null;
				}
				iter = null;

				matrixX = (x + width / 2) / Constants.BRICK_SIZE;
				matrixY = (y + height / 2) / Constants.BRICK_SIZE;

				if (status == 0)
				{
					if (!Engine.canMove(gamePanel.getCollision_matrix(), matrixX, matrixY))
					{
						shouldWork = false;
					}
				}
				else
				{
					if (!Engine.canMove(gamePanel.getCollision_parallel_matrix()[status - 1], matrixX, matrixY))
					{
						shouldWork = false;
					}
				}
				if (direction)
				{
					if (x <= targetLoc)
					{
						x++;
					}
					else
					{
						shouldWork = false;
					}
				}
				else
				{
					if (x >= targetLoc)
					{
						x--;
					}
					else
					{
						shouldWork = false;
					}
				}
			}
		}
	}

	public void draw(java.awt.Graphics g)
	{
		if (shouldWork)
		{
			if (direction)
			{
				g.drawImage(Constants.IMAGES[386], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
			}
			else
			{
				g.drawImage(Constants.IMAGES[387], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height, null);
			}

		}
	}
}
