package com.aspire.goldenapple.game.unit;

import com.aspire.goldenapple.Program;

public class Unit
{
	public Program	program;
	public int		x, y, width, height;
	public int		matrixX, matrixY;
	public int		currentX, currentY;
	public boolean	shouldDraw;
	public boolean	shouldUpdate;
	public int		id;
	public boolean	shouldWork;
	public boolean	isAnEntity;
	public boolean	alive;
	public int		status;
	public int		score;
	public int		priority;

	public Unit(Program program, int x, int y, int width, int height, int status, int priority)
	{
		this.program = program;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.status = status;
		this.priority = priority;
		currentX = x;
		currentY = y;
		isAnEntity = false;

	}

	public void update()
	{

	}

	public void draw(java.awt.Graphics g)
	{

	}

	public void die()
	{

	}

}
