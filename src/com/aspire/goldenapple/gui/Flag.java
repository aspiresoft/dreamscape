package com.aspire.goldenapple.gui;

import com.aspire.goldenapple.Constants;

public class Flag
{
	public static final int	CODE1	= 1, CODE2 = 2, CODE3 = 3, CODE4 = 4;
	private boolean			picked;
	private int				x, y, width, height;
	private int				type;

	public Flag(int width, int height, int type)
	{
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
				g.drawImage(Constants.IMAGES[388], x, y, width, height, null);
			}
			if (type == CODE2)
			{
				g.drawImage(Constants.IMAGES[391], x, y, width, height, null);
			}
			if (type == CODE3)
			{
				g.drawImage(Constants.IMAGES[394], x, y, width, height, null);
			}
			if (type == CODE4)
			{
				g.drawImage(Constants.IMAGES[397], x, y, width, height, null);
			}

		}
		else
		{
			if (type == CODE1)
			{
				g.drawImage(Constants.IMAGES[390], x, y, width, height, null);
			}
			if (type == CODE2)
			{
				g.drawImage(Constants.IMAGES[393], x, y, width, height, null);
			}
			if (type == CODE3)
			{
				g.drawImage(Constants.IMAGES[396], x, y, width, height, null);
			}
			if (type == CODE4)
			{
				g.drawImage(Constants.IMAGES[399], x, y, width, height, null);
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

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getType()
	{
		return type;
	}
}
