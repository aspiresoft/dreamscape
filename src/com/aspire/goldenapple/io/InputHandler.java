package com.aspire.goldenapple.io;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;

public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener
{
	public static boolean[]	keys	= new boolean[65536];

	public static int		mouseX, mouseY;
	public static boolean	mouseClicked;
	public static boolean	rightMouseClicked;

	public InputHandler()
	{
	}

	public void mouseDragged(MouseEvent arg0)
	{
		mouseX = arg0.getX();
		mouseY = arg0.getY();
	}

	public void mouseMoved(MouseEvent arg0)
	{
		mouseX = arg0.getX();
		mouseY = arg0.getY();
	}

	public void mouseClicked(MouseEvent arg0)
	{

	}

	public void mouseEntered(MouseEvent arg0)
	{
	}

	public void mouseExited(MouseEvent arg0)
	{
	}

	public void mousePressed(MouseEvent arg0)
	{
		if (SwingUtilities.isRightMouseButton(arg0))
		{
			rightMouseClicked = true;
		}
		else
		{
			mouseClicked = true;
		}
	}

	public void mouseReleased(MouseEvent arg0)
	{
		if (SwingUtilities.isRightMouseButton(arg0))
		{
			rightMouseClicked = false;
		}
		else
		{
			mouseClicked = false;
		}
	}

	public void focusGained(FocusEvent arg0)
	{
	}

	public void focusLost(FocusEvent arg0)
	{
		clearInput();
	}

	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		if (code > 0 && code < keys.length)
		{
			keys[code] = true;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();
		if (code > 0 && code < keys.length)
		{
			keys[code] = false;
		}
	}

	public void keyTyped(KeyEvent arg0)
	{
	}

	public static void clearInput()
	{
		for (int i = 0; i < keys.length; i++)
		{
			keys[i] = false;
		}
		mouseClicked = rightMouseClicked = false;
	}
}
