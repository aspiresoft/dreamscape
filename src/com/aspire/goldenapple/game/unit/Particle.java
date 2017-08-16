package com.aspire.goldenapple.game.unit;

import java.awt.image.BufferedImage;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.game.GamePanel;

public class Particle extends Unit
{
	public static final int		CODE1	= 1, CODE2 = 2;

	private static final int	SPEED	= 4;
	private static final int	SIZE	= Constants.BRICK_SIZE / 3;
	private static final int	DELAY	= (int) Constants.TICK_PER_SEC / 5;

	private GamePanel			gamePanel;
	private BufferedImage[]		images;
	private int[]				targetY;
	private int[]				startX, startY;
	private int[]				currentX, currentY;
	private int[]				direction;
	private boolean[]			goingTop;
	private boolean[]			goingBot;
	private boolean[]			alive;
	private int					speed;
	private int					ticks;

	public Particle(Program program, GamePanel gamePanel, int x, int y, int type, int status)
	{
		super(program, x, y, SIZE, SIZE, status, 0);
		this.gamePanel = gamePanel;
		shouldWork = true;
		isAnEntity = false;
		images = new BufferedImage[3];
		startX = new int[3];
		startY = new int[3];
		targetY = new int[3];
		direction = new int[3];
		currentX = new int[3];
		currentY = new int[3];
		goingBot = new boolean[3];
		goingTop = new boolean[3];
		alive = new boolean[3];

		startX[0] = currentX[0] = x;
		startY[0] = currentY[0] = y;
		startX[1] = currentX[1] = x;
		startY[1] = currentY[1] = y;
		startX[2] = currentX[2] = x;
		startY[2] = currentY[2] = y;
		targetY[0] = Engine.safeX(y - Constants.R.nextInt(Constants.BRICK_SIZE));
		targetY[1] = Engine.safeX(y - Constants.R.nextInt(Constants.BRICK_SIZE + 5));
		targetY[2] = Engine.safeX(y - Constants.R.nextInt(Constants.BRICK_SIZE + 10));
		direction[0] = -1;
		direction[1] = 0;
		direction[2] = 1;
		goingBot[0] = false;
		goingBot[1] = false;
		goingBot[2] = false;
		goingTop[0] = true;
		goingTop[1] = true;
		goingTop[2] = true;
		alive[0] = true;
		alive[1] = true;
		alive[2] = true;
		ticks = 0;
		speed = SPEED / 2;

		switch (type)
		{
			case 1:
				for (int i = 0; i < 3; i++)
				{
					images[i] = Constants.IMAGES[81 + i];
				}
				break;
			case 2:
				for (int i = 0; i < 3; i++)
				{
					images[i] = Constants.IMAGES[78 + i];
				}
				break;
		}
	}

	public void update()
	{
		if (shouldWork)
		{
			ticks++;
			if (ticks == DELAY)
			{
				speed = SPEED;
			}
			for (int i = 1; i <= speed; i++)
			{
				for (int k = 0; k < 3; k++)
				{
					if (currentY[k] - targetY[k] <= 0 && alive[k])
					{
						goingTop[k] = false;
						goingBot[k] = true;
					}
					if (currentY[k] >= Constants.LEVEL_BOTTOM_MOST + Constants.LEVEL_TOP_MOST && alive[k])
					{
						alive[k] = false;
					}
					if ((currentX[k] < 0 || currentX[k] >= Constants.LEVEL_LEFT_MOST + Constants.LEVEL_RIGHT_MOST) && alive[k])
					{
						alive[k] = false;
					}
					if (goingTop[k] && alive[k])
					{
						currentY[k]--;
					}
					if (goingBot[k] && alive[k])
					{
						currentY[k]++;
					}
					if (direction[k] == -1 && alive[k])
					{
						currentX[k]--;
					}
					if (direction[k] == 1 && alive[k])
					{
						currentX[k]++;
					}
				}
			}
		}
		if (!alive[0] && !alive[1] && !alive[2])
		{
			shouldWork = false;
		}
	}

	public void draw(java.awt.Graphics g)
	{
		if (shouldWork)
		{
			for (int k = 0; k < 3; k++)
			{
				if (alive[k])
				{
					g.drawImage(images[k], gamePanel.getInvertX() + currentX[k], gamePanel.getInvertY() + currentY[k], width, height, null);
				}
			}
		}
	}
}
