package com.aspire.goldenapple.gui;

import com.aspire.goldenapple.Constants;

public class Key
{
	public static final int	CODE1	= 1, CODE2 = 2, CODE3 = 3, CODE4 = 4;
	private boolean			picked;
	private int				x, y, width, height;
	private int				type;

	public Key(int x, int y, int width, int height, int type)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;

		setPicked(false);
	}

	public void update()
	{

	}

	public void draw(java.awt.Graphics g)
	{
		if (isPicked())
		{
			if (type == CODE1)
			{
				g.drawImage(Constants.IMAGES[136], x, y, width, height, null);
			}
			if (type == CODE2)
			{
				g.drawImage(Constants.IMAGES[138], x, y, width, height, null);
			}
			if (type == CODE3)
			{
				g.drawImage(Constants.IMAGES[140], x, y, width, height, null);
			}
			if (type == CODE4)
			{
				g.drawImage(Constants.IMAGES[142], x, y, width, height, null);
			}

		}
		else
		{
			if (type == CODE1)
			{
				g.drawImage(Constants.IMAGES[137], x, y, width, height, null);
			}
			if (type == CODE2)
			{
				g.drawImage(Constants.IMAGES[139], x, y, width, height, null);
			}
			if (type == CODE3)
			{
				g.drawImage(Constants.IMAGES[141], x, y, width, height, null);
			}
			if (type == CODE4)
			{
				g.drawImage(Constants.IMAGES[143], x, y, width, height, null);
			}
		}
	}

	public boolean isPicked()
	{
		return picked;
	}

	public void setPicked(boolean picked)
	{
		this.picked = picked;
	}
}
