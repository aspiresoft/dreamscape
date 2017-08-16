package com.aspire.goldenapple.gui;

import com.aspire.goldenapple.game.GamePanel;

public class FloatingNumber
{
	private static final int	MAX	= 8;

	private int					x, y;
	private GamePanel			gamePanel;
	private int					number;
	private boolean				alive;
	private long				duration;
	private Digit[]				digits;
	private char[]				array;
	private long				timer;

	public FloatingNumber(GamePanel gamePanel, int x, int y, int number)
	{
		this.setX(x);
		this.setY(y);
		this.setGamePanel(gamePanel);
		this.setNumber(number);
		setAlive(true);
		array = String.valueOf(number).toCharArray();
		digits = new Digit[MAX];
		for (int i = 0; i < MAX; i++)
		{
			digits[i] = new Digit(gamePanel, x + (i * (Hud.SIZE / 3)), y, Hud.SIZE / 2, Hud.SIZE * 3 / 4, 0, false);
		}
		for (int i = 0; i < array.length; i++)
		{
			digits[i].setNumber(Character.getNumericValue(array[i]));
		}

		duration = 600 + 150 * (array.length);
		timer = System.currentTimeMillis();
	}

	public void update()
	{
		if (isAlive())
		{
			if (System.currentTimeMillis() - timer <= duration)
			{
				int cont = (int) (System.currentTimeMillis()) % 2;
				if (cont == 0)
				{
					for (int i = 0; i < digits.length; i++)
					{
						digits[i].setY(digits[i].getY() - 1);
					}
				}
			}
			else
			{
				setAlive(false);
			}
		}
	}

	public void draw(java.awt.Graphics g)
	{
		for (int i = 0; i < array.length; i++)
		{
			digits[i].draw(g);
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

	public GamePanel getGamePanel()
	{
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel)
	{
		this.gamePanel = gamePanel;
	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public boolean isAlive()
	{
		return alive;
	}

	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
}
