package com.aspire.goldenapple.game.unit;

import java.awt.Rectangle;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.tools.MediaPlayer;

public class KunaiPack extends Unit
{
	public static final int		CODE		= 47;

	private static final int	KUNAI_LIMIT	= 10;
	private static final int	AMOUNT		= 3;
	private static final int	SCORE		= 100;

	private GamePanel			gamePanel;

	public KunaiPack(Program program, GamePanel gamePanel, int x, int y, int width, int height, int status, int priority)
	{
		super(program, x, y, width, height, status, priority);
		this.gamePanel = gamePanel;
		shouldWork = true;
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
					shouldWork = false;
					MediaPlayer.playSound(Constants.kunai_pickup);
					gamePanel.addScore(SCORE, x, y);
					p.setAmmo(p.getAmmo() + AMOUNT);
					if (p.getAmmo() >= KUNAI_LIMIT)
					{
						p.setAmmo(KUNAI_LIMIT - 1);
					}
					gamePanel.getHud().getAmmo().getD().setNumber(p.getAmmo());
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
}
