package com.aspire.goldenapple.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.game.unit.Unit;

public abstract class Engine
{
	public static boolean canGoLeft(int[][] matrix, int matrixX, int matrixY)
	{
		if (matrix[matrixX - 1][matrixY] == -1)
		{
			return true;
		}
		return true;

	}

	public static boolean canGoRight(int[][] matrix, int matrixX, int matrixY)
	{
		if (matrix[matrixX + 1][matrixY] == 0)
		{
			return true;
		}
		return true;
	}

	public static boolean canGoDown(int[][] matrix, int matrixX, int matrixY)
	{
		if (matrix[matrixX][matrixY + 1] == 0)
		{
			return true;
		}
		return false;
	}

	public static boolean canGoUp(int[][] matrix, int matrixX, int matrixY)
	{
		if (matrix[matrixX][matrixY - 1] == 0)
		{
			return true;
		}
		return false;
	}

	public static boolean canMove(int[][] matrix, int matrixX, int matrixY)
	{
		if (matrix[matrixX][matrixY] == 0)
		{
			return true;
		}
		return false;
	}

	public static boolean canTop(int[][] matrix, int matrixX, int matrixY)
	{
		if (matrix[matrixX][matrixY] == 0 || matrix[matrixX][matrixY] == 2)
		{
			return true;
		}
		return false;
	}

	public static boolean isInside(Rectangle r1, Rectangle r2)
	{
		if (r1.intersects(r2))
			return true;
		return false;
	}

	public static int safeX(int i)
	{
		if (i < 0)
			return 0;
		if (i > Constants.RIGHT_MOST)
			return Constants.RIGHT_MOST;
		return i;
	}

	public static int safeY(int i)
	{
		if (i < 0)
			return 0;
		if (i > Constants.BOT_MOST)
			return Constants.BOT_MOST;
		return i;
	}

	public static Point movePointToPoint(Point p1, Point p2, int radius)
	{
		Point p = new Point(0, 0);
		int x1 = p1.x;
		int y1 = p1.y;
		int x2 = p2.x;
		int y2 = p2.y;
		int deltaX = x2 - x1;
		int deltaY = y2 - y1;
		if (deltaX == 0 && deltaY == 0)
		{
			p = p2;
			return p;
		}
		if (deltaX == 0)
		{
			if (deltaY > 0)
			{
				p = p1;
				p.y -= radius;
				return p;
			}
			else if (deltaY < 0)
			{
				p = p1;
				p.y += radius;
				return p;
			}
		}
		if (deltaY == 0)
		{
			if (deltaX > 0)
			{
				p = p1;
				p.x -= radius;
				return p;
			}
			else if (deltaX < 0)
			{
				p = p1;
				p.x += radius;
				return p;
			}
		}
		if (deltaX > 0 && deltaY > 0)
		{ // ( + + )
			double delta = Math.atan(Math.abs(deltaY / deltaX));
			double degree = delta * 180 / Math.PI;
			p.x = (int) (x1 + radius * Math.cos(degree));
			p.y = (int) (y1 + radius * Math.sin(degree));
			return p;
		}
		if (deltaX < 0 && deltaY > 0)
		{ // ( - + )
			double delta = Math.atan(Math.abs(deltaY / deltaX));
			double degree = delta * 180 / Math.PI;
			p.x = (int) (x1 - radius * Math.cos(degree));
			p.y = (int) (y1 + radius * Math.sin(degree));
			return p;
		}
		if (deltaX > 0 && deltaY < 0)
		{ // ( + - )
			double delta = Math.atan(Math.abs(deltaY / deltaX));
			double degree = delta * 180 / Math.PI;
			p.x = (int) (x1 + radius * Math.cos(degree));
			p.y = (int) (y1 - radius * Math.sin(degree));
			return p;
		}
		if (deltaX < 0 && deltaY < 0)
		{ // ( - - )
			double delta = Math.atan(Math.abs(deltaY / deltaX));
			double degree = delta * 180 / Math.PI;
			p.x = (int) (x1 - radius * Math.cos(degree));
			p.y = (int) (y1 - radius * Math.sin(degree));
			return p;
		}
		return p;
	}

	public static int moveAmountofX(Point p1, Point p2, int radius)
	{
		int x1 = p1.x;
		int y1 = p1.y;
		int x2 = p2.x;
		int y2 = p2.y;
		int deltaX = x2 - x1;
		int deltaY = y2 - y1;
		if (deltaX == 0)
		{
			return 0;
		}
		if (deltaY == 0)
		{
			if (deltaX > 0)
			{
				return radius;
			}
			if (deltaX < 0)
			{
				return radius * -1;
			}
		}
		if (deltaX > 0 && deltaY > 0)
		{ // ( + + )
			double delta = Math.atan(Math.abs(deltaY / deltaX));
			double degree = delta * 180 / Math.PI;
			return (int) (radius * Math.cos(degree));
		}
		if (deltaX < 0 && deltaY > 0)
		{ // ( - + )
			double delta = Math.atan(Math.abs(deltaY / deltaX));
			double degree = delta * 180 / Math.PI;
			return (int) (-radius * Math.cos(degree));
		}
		if (deltaX > 0 && deltaY < 0)
		{ // ( + - )
			double delta = Math.atan(Math.abs(deltaY / deltaX));
			double degree = delta * 180 / Math.PI;
			return (int) (radius * Math.cos(degree));
		}
		if (deltaX < 0 && deltaY < 0)
		{ // ( - - )
			double delta = Math.atan(Math.abs(deltaY / deltaX));
			double degree = delta * 180 / Math.PI;
			return (int) (-radius * Math.cos(degree));
		}
		return 0;
	}

	public static int moveAmountofY(Point p1, Point p2, int radius)
	{
		int x1 = p1.x;
		int y1 = p1.y;
		int x2 = p2.x;
		int y2 = p2.y;
		int deltaX = x2 - x1;
		int deltaY = y2 - y1;
		if (deltaY == 0)
		{
			return 0;
		}
		if (deltaX == 0)
		{
			if (deltaY > 0)
			{
				return radius;
			}
			if (deltaY < 0)
			{
				return -radius;
			}
		}
		if (deltaX > 0 && deltaY > 0)
		{ // ( + + )
			double delta = Math.atan(Math.abs(deltaY / deltaX));
			double degree = delta * 180 / Math.PI;
			return (int) (radius * Math.sin(degree));
		}
		if (deltaX < 0 && deltaY > 0)
		{ // ( - + )
			double delta = Math.atan(Math.abs(deltaY / deltaX));
			double degree = delta * 180 / Math.PI;
			return (int) (radius * Math.sin(degree));
		}
		if (deltaX > 0 && deltaY < 0)
		{ // ( + - )
			double delta = Math.atan(Math.abs(deltaY / deltaX));
			double degree = delta * 180 / Math.PI;
			return (int) (-radius * Math.sin(degree));
		}
		if (deltaX < 0 && deltaY < 0)
		{ // ( - - )
			double delta = Math.atan(Math.abs(deltaY / deltaX));
			double degree = delta * 180 / Math.PI;
			return (int) (-radius * Math.sin(degree));
		}
		return 0;
	}

	public static String zeroFiller(int number, int max)
	{
		if (max == 1)
		{
			return number % 10 + "";
		}
		if (max == 2)
		{
			if (number < 10)
				return "0" + number;
			else
				return "" + number;
		}
		if (max == 3)
		{
			if (number < 10)
				return "00" + number;
			else
			{
				if (number < 100)
					return "0" + number;
				else
					return "" + number;
			}
		}
		if (max == 4)
		{
			if (number < 10)
				return "000" + number;
			else
			{
				if (number < 100)
					return "00" + number;
				else
				{
					if (number < 1000)
						return "0" + number;
					else
						return "" + number;
				}
			}
		}
		return "";
	}

	public static boolean isInRange(int x, int y, int x1, int y1, int distance)
	{
		if (Math.pow(x - x1, 2) + Math.pow(y - y1, 2) <= Math.pow(distance, 2))
			return true;
		return false;
	}

	public static Point findTargetLocation(int startX, int startY, int radius)
	{
		double angle = Constants.R.nextDouble() * Math.PI * 2;
		double x = startX + radius * Math.cos(angle);
		double y = startY + radius * Math.sin(angle);
		return new Point((int) x, (int) y);
	}

	public static double getAngle(Point start, Point target)
	{
		double angle = Math.toDegrees(Math.atan2((target.y - start.y), target.x - start.x));
		if (angle < 0)
			angle += 360;
		return angle;
	}

	public static boolean canMoveToLeft(int x, int y, int width, int height)
	{
		if (x <= Constants.LEFT_MOST)
			return false;
		return true;
	}

	public static boolean canMoveToTop(int x, int y, int width, int height)
	{
		if (y <= Constants.TOP_MOST)
			return false;
		return true;
	}

	public static boolean canMoveToRight(int x, int y, int width, int height)
	{
		if (x >= Constants.RIGHT_MOST - width)
			return false;
		return true;
	}

	public static boolean canMoveToBot(int x, int y, int width, int height)
	{
		if (y >= Constants.BOT_MOST - height)
			return false;
		return true;
	}

	public static boolean canMoveToRightParallel(int x, int y, int width, int height)
	{
		if (x >= Constants.RIGHT_MOST_PARALLEL - width)
			return false;
		return true;
	}

	public static boolean canMoveToBotParallel(int x, int y, int width, int height)
	{
		if (y >= Constants.BOT_MOST_PARALLEL - height)
			return false;
		return true;
	}

	public static boolean canMoveScreenHorizontal(int x, int y)
	{
		if (x > Constants.LEFT_SAFE && x <= Constants.RIGHT_SAFE)
			return true;
		return false;
	}

	public static boolean canMoveScreenVertical(int x, int y)
	{
		if (y > Constants.TOP_SAFE && y <= Constants.BOT_SAFE)
			return true;
		return false;
	}

	public static int panelLocationXFounder(int x, int y)
	{
		if (x < Constants.LEFT_SAFE || x >= Constants.RIGHT_SAFE)
		{
			if (x < Constants.LEFT_SAFE)
			{
				return 0;
			}
			else
			{
				return Constants.RIGHT_SAFE - Constants.LEFT_SAFE;
			}
		}
		else
		{
			return x - Constants.LEFT_SAFE;
		}
	}

	public static int panelLocationYFounder(int x, int y)
	{
		if (y < Constants.TOP_SAFE || y >= Constants.BOT_SAFE)
		{
			if (y < Constants.TOP_SAFE)
			{
				return 0;
			}
			else
			{
				return Constants.BOT_SAFE - Constants.TOP_SAFE;
			}
		}
		else
		{
			return y - Constants.TOP_SAFE;
		}
	}

	public static int digitFounder(int number)
	{
		int x = 1;
		int y = 1;
		while (number / x > 9)
		{
			x = x * 10;
			y++;
		}
		return y;
	}

	public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font, Color color)
	{
		FontMetrics metrics = g.getFontMetrics(font);
		int x = (rect.width - metrics.stringWidth(text)) / 2;
		int y = ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, rect.x + x, rect.y + y);
	}

	public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font)
	{
		FontMetrics metrics = g.getFontMetrics(font);
		int x = (rect.width - metrics.stringWidth(text)) / 2;
		int y = ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setFont(font);
		g.drawString(text, rect.x + x, rect.y + y);
	}

	public static ArrayList<Unit> listContainsSpecifiedType(ArrayList<Unit> units, Class<?> t)
	{
		ArrayList<Unit> list = new ArrayList<Unit>();
		for (Unit unit : units)
		{
			if (unit.getClass().equals(t))
			{
				list.add(unit);
			}
		}
		return list;

	}

}
