package com.aspire.goldenapple.gui;

import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.game.GamePanel;

public class Digit
{
	private int			x, y, width, height;
	private int			number;
	private GamePanel	gamePanel;
	private boolean		stable;

	public Digit(GamePanel gamePanel, int x, int y, int width, int height, int number, boolean stable)
	{
		this.gamePanel = gamePanel;
		this.x = x;
		this.setY(y);
		this.width = width;
		this.height = height;
		this.setNumber(number);
		this.stable = stable;
	}

	public void update()
	{

	}

	public void draw(java.awt.Graphics g)
	{
		if (stable)
			g.drawImage(Constants.IMAGES[144 + getNumber()], x, getY(), width, height, null);
		else
			g.drawImage(Constants.IMAGES[144 + getNumber()], gamePanel.getInvertX() + x, gamePanel.getInvertY() + getY(), width, height, null);
	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}
}
