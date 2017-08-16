package com.aspire.goldenapple;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.aspire.goldenapple.gui.state.StateManager;
import com.aspire.goldenapple.io.InputHandler;

@SuppressWarnings("serial")
public class Program extends Canvas implements Runnable
{
	public static int				tickCount;
	public static JFrame			frame;

	private InputHandler			inputHandler;
	private boolean					running;

	private Cursor					emptyCursor;
	private StateManager			manager;
	private BufferedImage			customCursor;
	private static BufferedImage	blurBG;
	private boolean					available;

	private Program()
	{
		// ADDING INPUT MANAGER TO THE GAME
		// ================================================================
		try
		{
			inputHandler = new InputHandler();
			addKeyListener(inputHandler);
			addFocusListener(inputHandler);
			addMouseListener(inputHandler);
			addMouseMotionListener(inputHandler);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// LOADING PREGAME IMAGES
		// ================================================================
		try
		{
			blurBG = ImageIO.read(new File("Resources/Textures/Backgrounds/BG_blur.jpg"));
			customCursor = ImageIO.read(new File("Resources/UI/cursor.png"));
			emptyCursor = Toolkit.getDefaultToolkit().createCustomCursor(customCursor, new Point(0, 0), "empty");
			setCursor(emptyCursor);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		// STARTING THE GAME BY CREATING STATE MANAGER THAT WILL MANAGE THE GAME
		// STATES
		// ================================================================
		available = false;
		manager = new StateManager(this);
	}

	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Program d = new Program();

		frame = new JFrame();
		frame.add(d);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(25, 25);
		frame.pack();
		frame.setTitle(Constants.TITLE);
		frame.setSize(Constants.SIZE);
		frame.setPreferredSize(Constants.SIZE);
		frame.setResizable(false);

		d.start();
	}

	private void start()
	{
		// STARTING THE THREAD THAT WILL LOAD INGAME RESOURCES
		// ================================================================
		new Thread(new ResourceLoader()).start();

		// STARTING THE THREAD THAT WILL RUN THE GAME
		// ================================================================
		new Thread(this).start();

		// SETTING THE STATUS OF THE GAME
		// ================================================================
		running = true;
	}

	public void stop()
	{
		running = false;
	}

	public void run()
	{
		int frames = 0;
		double unprocessedSeconds = 0;
		long lastTime = System.nanoTime();
		double secondsPerTick = 1 / Constants.TICK_PER_SEC;
		tickCount = 0;
		requestFocus();
		while (running)
		{
			long now = System.nanoTime();
			long passedTime = now - lastTime;
			lastTime = now;
			if (passedTime < 0)
				passedTime = 0;
			if (passedTime > 100000000)
				passedTime = 100000000;
			unprocessedSeconds += passedTime / 1000000000.0;
			boolean ticked = false;
			while (unprocessedSeconds > secondsPerTick)
			{
				update();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;
				tickCount++;
				if (tickCount % Constants.FRAME_PER_SEC == 0)
				{
					frame.setTitle(Constants.TITLE + " | " + frames);
					lastTime += 1000;
					frames = 0;
				}
			}
			if (ticked)
			{
				draw();
				frames++;
			}
			else
			{
				try
				{
					Thread.sleep(2);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}

		}
	}

	private void update()
	{
		if (tickCount > 60 * 2)
		{
			if (!available)
			{
				available = true;
			}
		}
		if (hasFocus())
		{
			if (available)
			{
				getManager().update();
			}
		}
	}

	private void draw()
	{
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		if (!available)
		{
			g.drawImage(blurBG, 0, 0, Constants.SIZE.width, Constants.SIZE.height, null);
			g.setColor(Constants.STEEL_BLUE_COLOR);
			g.setFont(Constants.BIG_FONT);
			g.drawString("Loading...", 270, 320);
		}
		else
		{

			getManager().draw(g);
		}

		g.dispose();
		bs.show();
	}

	public StateManager getManager()
	{
		return manager;
	}

	public InputHandler getInputHandler()
	{
		return inputHandler;
	}

}
