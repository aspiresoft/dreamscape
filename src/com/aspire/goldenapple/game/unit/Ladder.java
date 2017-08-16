package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;

public class Ladder extends Unit
{
	public static final int	CODE	= 50;

	private GamePanel		gamePanel;
	private boolean			hasPlayerInside;

	public Ladder(Program program, GamePanel gamePanel, int x, int y, int width, int height, int status, int priority)
	{
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;

		shouldWork = true;
		isAnEntity = false;
		setHasPlayerInside(false);
	}

	public void update()
	{
		if (shouldWork)
		{
			/*
			PlayerNinja p = gamePanel.findHero(gamePanel.getUnits());
			if (p != null)
			{
				if (new Rectangle(x, y, width, height).intersects(new Rectangle(p.x, p.y, p.width, p.height)))
				{
					setHasPlayerInside(true);
				}
				else
				{
					setHasPlayerInside(false);
				}
			}
			p = null;
			*/
		}
	}

	public void draw(java.awt.Graphics g)
	{
		if (shouldWork)
		{
			g.drawImage(Constants.IMAGES[Constants.UNIT_ID_TO_IMAGE_ID(CODE)], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, width, height,
							null);
		}
	}

	public boolean isHasPlayerInside()
	{
		return hasPlayerInside;
	}

	public void setHasPlayerInside(boolean hasPlayerInside)
	{
		this.hasPlayerInside = hasPlayerInside;
	}

}
