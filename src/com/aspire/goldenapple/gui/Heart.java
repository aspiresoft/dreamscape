package com.aspire.goldenapple.gui;

import com.aspire.goldenapple.Constants;

public class Heart
{
	private int	amount;
	private int	x, y, width, height;

	public Heart(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		setAmount(2);
	}

	public void update()
	{

	}

	public void draw(java.awt.Graphics g)
	{
		if (getAmount() == 2)
		{
			g.drawImage(Constants.IMAGES[121], x, y, width, height, null);
		}
		if (getAmount() == 1)
		{
			g.drawImage(Constants.IMAGES[122], x, y, width, height, null);
		}
		if (getAmount() == 0)
		{
			g.drawImage(Constants.IMAGES[123], x, y, width, height, null);
		}
	}

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

}
