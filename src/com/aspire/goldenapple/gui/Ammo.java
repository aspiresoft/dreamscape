package com.aspire.goldenapple.gui;

import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.game.unit.KunaiPack;

public class Ammo
{
	@SuppressWarnings("unused")
	private GamePanel	gamePanel;

	private int			x, y, width, height;
	private Digit		d;

	public Ammo(GamePanel gamePanel, int x, int y, int width, int height)
	{
		this.gamePanel = gamePanel;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		setD(new Digit(gamePanel, x + width / 2, y, width / 2, height, 0, true));
	}

	public void update()
	{

	}

	public void draw(java.awt.Graphics g)
	{
		g.drawImage(Constants.IMAGES[Constants.UNIT_ID_TO_IMAGE_ID(KunaiPack.CODE)], x, y, width / 2, height, null);
		getD().draw(g);
	}

	public Digit getD()
	{
		return d;
	}

	public void setD(Digit d)
	{
		this.d = d;
	}

}
