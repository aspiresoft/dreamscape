package com.aspire.goldenapple.tools;

import java.awt.Font;
import java.awt.Rectangle;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.io.InputHandler;

public class SButton
{
	public static final int		CODE1			= -1, CODE2 = -2;

	private static final int	MAX_DISTANCE	= 4;
	private static final int	SPEED			= 3;

	private boolean				hasFocus;
	private boolean				shouldUpdate;
	private int					x, y, width, height;
	private String				text, subText;
	private Font				f;
	private int					distance;
	private int					type;

	public SButton(Program program, int x, int y, int width, int height, String text, Font f)
	{
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setText(text);
		this.f = f;
		hasFocus = false;
		shouldUpdate = true;
		setType(CODE1);
	}

	public SButton(Program program, int x, int y, int width, int height, String text, String subText, Font f)
	{
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setText(text);
		this.subText = subText;
		this.f = f;
		hasFocus = false;
		shouldUpdate = true;
		setType(CODE2);
	}

	public SButton(Program program, int x, int y, int width, int height, int type)
	{
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setType(type);
		hasFocus = false;
		shouldUpdate = true;
		distance = 0;
	}

	public void update()
	{
		if (shouldUpdate)
		{
			if (new Rectangle(getX(), getY(), getWidth(), getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY))
			{
				hasFocus = true;
			}
			else
			{
				hasFocus = false;
			}

			if (Program.tickCount % SPEED == 0)
			{
				if (hasFocus)
				{
					if (distance < MAX_DISTANCE)
					{
						distance++;
					}
				}
				else
				{
					if (distance > 0)
					{
						distance--;
					}
				}
			}

		}
	}

	public void draw(java.awt.Graphics g)
	{
		if (getType() == CODE1 || getType() == CODE2)
		{
			if (getType() == CODE1)
			{
				g.setColor(Constants.MAIN_COLOR);
				Engine.drawCenteredString(g, getText(), new Rectangle(getX(), getY(), getWidth(), getHeight()), f);
			}
			else
			{
				g.setColor(Constants.MAIN_COLOR);
				Engine.drawCenteredString(g, getText(), new Rectangle(getX(), getY(), getWidth(), getHeight() / 2), f);
				Engine.drawCenteredString(g, subText, new Rectangle(getX(), getY() + getHeight() / 2, getWidth(), getHeight() / 2), f);
			}
		}
		else
		{
			g.drawImage(Constants.IMAGES[getType()], getX() + distance + MAX_DISTANCE, getY() + distance + MAX_DISTANCE,
							getWidth() - distance * 2 - MAX_DISTANCE * 2, getHeight() - distance * 2 - MAX_DISTANCE * 2, null);
		}

		g.setColor(Constants.MAIN_COLOR);
		g.drawRect(getX(), getY(), getWidth(), getHeight());

		if (hasFocus)
		{
			g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.setColor(Constants.SELECTED_COLOR);
			g.drawRect(getX(), getY(), getWidth(), getHeight());
		}
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
