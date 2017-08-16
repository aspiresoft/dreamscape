package com.aspire.goldenapple.gui.state;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.game.Engine;
import com.aspire.goldenapple.io.InputHandler;

public class OpeningState extends State
{
	private static final int	MIN_SELECT	= 1;
	private static final int	MAX_SELECT	= 3;
	private static final long	DELAY		= 180;

	private static Rectangle	R1			= new Rectangle(0, 100, 800, 100);
	private static Rectangle	R2			= new Rectangle(0, 200, 800, 100);
	private static Rectangle	R3			= new Rectangle(0, 300, 800, 100);
	private static Rectangle	R4			= new Rectangle(0, 400, 800, 100);

	private int					selected;

	public OpeningState(Program p)
	{
		super(p);
		init();
	}

	public void init()
	{
		selected = MIN_SELECT;
		lastTime = System.currentTimeMillis();
	}

	public void update()
	{
		if (shouldUpdate)
		{
			long currentTime = System.currentTimeMillis();
			boolean up = InputHandler.keys[KeyEvent.VK_UP];
			boolean down = InputHandler.keys[KeyEvent.VK_DOWN];
			boolean enter = InputHandler.keys[KeyEvent.VK_ENTER];
			if (new Rectangle(R1.x, R1.y, R1.width, R1.height).contains(InputHandler.mouseX, InputHandler.mouseY))
			{
				selected = 1;
			}
			if (new Rectangle(R2.x, R2.y, R2.width, R2.height).contains(InputHandler.mouseX, InputHandler.mouseY))
			{
				selected = 2;
			}
			if (new Rectangle(R3.x, R3.y, R3.width, R3.height).contains(InputHandler.mouseX, InputHandler.mouseY))
			{
				selected = 3;
			}
			if (new Rectangle(R4.x, R4.y, R4.width, R4.height).contains(InputHandler.mouseX, InputHandler.mouseY))
			{
				selected = 4;
			}
			if (InputHandler.mouseClicked)
			{
				InputHandler.clearInput();
				if (selected == 1)
					program.getManager().setState(2);
				else if (selected == 2)
					program.getManager().setState(5);
				else if (selected == 3)
					program.getManager().setState(3);
				else if (selected == 4)
					program.getManager().setState(-1);
			}
			if (up || down || enter)
			{
				if (currentTime - lastTime > DELAY)
				{
					lastTime = System.currentTimeMillis();
					if (up)
					{
						selected--;
						up = false;
						if (selected < MIN_SELECT)
							selected = MAX_SELECT;

					}
					else if (down)
					{
						down = false;
						selected++;
						if (selected > MAX_SELECT)
							selected = MIN_SELECT;
					}
					else if (enter)
					{
						if (selected == 1)
							program.getManager().setState(2);
						else if (selected == 2)
							program.getManager().setState(5);
						else if (selected == 3)
							program.getManager().setState(3);
						else if (selected == 4)
							program.getManager().setState(-1);
					}
				}
				up = false;
				down = false;
				enter = false;
			}
		}
	}

	public void draw(java.awt.Graphics g)
	{
		if (shouldDraw)
		{
			g.drawImage(Constants.BG, 0, 0, Constants.SIZE.width, Constants.SIZE.height, null);

			g.setColor(Constants.MAIN_COLOR);

			Engine.drawCenteredString(g, "Single Player", R1, selected == 1 ? Constants.MENU_FONT_SMALL : Constants.MENU_FONT_HUGE);
			
			Engine.drawCenteredString(g, "Multi Player", R2, selected == 2 ? Constants.MENU_FONT_SMALL : Constants.MENU_FONT_HUGE);

			Engine.drawCenteredString(g, "Editor", R3, selected == 3 ? Constants.MENU_FONT_SMALL : Constants.MENU_FONT_HUGE);

			Engine.drawCenteredString(g, "Exit", R4, selected == 4 ? Constants.MENU_FONT_SMALL : Constants.MENU_FONT_HUGE);

		}
	}

	public void setSelected(int i)
	{
		selected = i;
	}
}
