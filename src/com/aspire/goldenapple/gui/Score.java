package com.aspire.goldenapple.gui;

import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.game.GamePanel;
import com.aspire.goldenapple.gui.state.PlayerState;

public class Score
{
	private static final int	LENGHT	= 8;
	private static char[]		SCORE;
	private static Digit[]		digits;
	private GamePanel			gamePanel;

	public Score(GamePanel gamePanel)
	{
		this.gamePanel = gamePanel;
		SCORE = new char[LENGHT];
		digits = new Digit[LENGHT];

		for (int i = 0; i < digits.length; i++)
		{
			digits[i] = new Digit(gamePanel, Constants.SIZE.width - Hud.SPACE * 2 - ((digits.length - i) * (Hud.SIZE * 3 / 4)), Hud.SPACE,
							Hud.SIZE * 3 / 4, Hud.SIZE * 3 / 4, i, true);
		}

	}

	public static void fillWith(char[] array, char c)
	{
		for (int i = 0; i < array.length; i++)
		{
			array[i] = c;
		}
	}

	public static int stringToPoints(char[] array)
	{
		int result = 0;
		for (int i = 0; i < array.length; i++)
		{
			int digit = ((int) array[i] & 0xF);
			for (int j = 0; j < array.length - 1 - i; j++)
			{
				digit *= 10;
			}
			result += digit;
		}
		return result;
	}

	public static void setScore(int score)
	{
		char[] chars = String.valueOf(score).toCharArray();
		fillWith(SCORE, '0');
		for (int i = 0; i < chars.length; i++)
		{
			SCORE[LENGHT - chars.length + i] = chars[i];
		}
		for (int i = 0; i < LENGHT; i++)
		{
			digits[i].setNumber(Character.getNumericValue(SCORE[i]));
		}
	}

	public void update()
	{
		if (!gamePanel.getLevel().isTestLevel())
		{
			setScore(PlayerState.currentScore);
			for (int i = 0; i < LENGHT; i++)
			{
				digits[i].setNumber(Character.getNumericValue(SCORE[i]));
			}
		}
	}

	public void draw(java.awt.Graphics g)
	{
		if (!gamePanel.getLevel().isTestLevel())
		{
			for (int i = 0; i < LENGHT; i++)
			{
				digits[i].draw(g);
			}
		}
	}
}
