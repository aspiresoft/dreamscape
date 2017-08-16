package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.io.InputHandler;

public class Door extends Unit
{
	public static final int	CODE1	= 41, CODE2 = 42, CODE3 = 43, CODE4 = 44;

	private GamePanel		gamePanel;
	private int				type;
	private int				id, targetID;
	private long			lastTime;

	public Door(Program program, GamePanel gamePanel, int x, int y, int width, int height, int type, int matrixX, int matrixY, int status, int id,
					int targetID, int priority)
	{
		super(program, x, y, width, height, status, priority);
		this.matrixX = matrixX;
		this.matrixY = matrixY;
		this.gamePanel = gamePanel;
		this.setType(type);
		this.id = id;
		this.targetID = targetID;

		lastTime = System.currentTimeMillis();
		shouldWork = true;
		isAnEntity = false;
	}

	public void update()
	{
		if (shouldWork)
		{
			/*
			if (id != -1 && getType() == CODE4)
			{
				PlayerNinja p = gamePanel.findHero(gamePanel.getUnits());
				if (p != null)
				{
					if (new Rectangle(x, y, width, height).intersects(p.x, p.y, p.width, p.height))
					{
						if (InputHandler.keys[KeyEvent.VK_UP] && System.currentTimeMillis() - lastTime > 200)
						{
							lastTime = System.currentTimeMillis();
							InputHandler.clearInput();

							Door targetDoor = gamePanel.findTargetDoor(targetID);
							if (targetDoor != null)
							{
								int targetStatus = targetDoor.status;
								if (targetStatus == 0)
								{
									gamePanel.handlePanelPosition(p.x + p.width / 2, p.y + p.height / 2);
								}
								else
								{
									gamePanel.setLastX(gamePanel.getX());
									gamePanel.setLastY(gamePanel.getY());
									gamePanel.setX(0);
									gamePanel.setY(0);
								}
								gamePanel.setStatus(targetDoor.status, targetDoor.x, targetDoor.y);
							}
						}
					}
				}
			}
			*/
		}
		
	}

	public void draw(java.awt.Graphics g)
	{
		if (shouldWork)
		{
			g.drawImage(Constants.IMAGES[Constants.UNIT_ID_TO_IMAGE_ID(getType())], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width,
							height, null);

		}
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}
}
