package com.aspire.goldenapple.editor;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.io.CellData;
import com.aspire.goldenapple.io.InputHandler;

public class GridCell implements Serializable
{
	private static final long	serialVersionUID	= 1907L;

	private static final int	MAX_DISTANCE		= 2;
	private static final int	SPEED				= 3;
	private int					distance;
	private int					x, y, width, height;
	private CellData			data;
	private boolean				visible;
	private boolean				isATExture;
	private boolean				highlight;
	private boolean				visited;
	private boolean				hasFocus;

	public GridCell(int x, int y, int width, int height, CellData data, boolean isATexture)
	{
		this.x = x;
		this.y = y;
		this.isATExture = isATexture;
		this.setData(data);
		this.setWidth(width);
		this.setHeight(height);

		setHighlight(false);
		distance = 0;
		hasFocus = false;

		if (!isATexture)
		{
			visible = false;
		}
		else
		{
			visible = true;
		}
	}

	public void update()
	{
		if (new Rectangle(getX(), getY(), getWidth(), getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY))
		{
			hasFocus = true;
		}
		else
		{
			hasFocus = false;
		}

		if (isATExture)
		{
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

	public void draw(Graphics g)
	{
		if (visible)
		{
			if (isATExture)
			{
				if (Editor.TEXTURER == Editor.TEXTURER_TILE_ID)
				{
					if (getData().getTILE_ID() != 0)
					{
						g.drawImage(Constants.IMAGES[Constants.TILE_ID_TO_IMAGE_ID(getData().getTILE_ID())], x + MAX_DISTANCE + distance,
										y + MAX_DISTANCE + distance, getWidth() - MAX_DISTANCE * 2 - distance * 2,
										getHeight() - MAX_DISTANCE * 2 - distance * 2, null);
					}
				}
				if (Editor.TEXTURER == Editor.TEXTURER_OBJECT_ID)
				{
					if (getData().getUNIT_ID() != 0)
					{
						g.drawImage(Constants.IMAGES[Constants.UNIT_ID_TO_IMAGE_ID(getData().getUNIT_ID())], x + MAX_DISTANCE + distance,
										y + MAX_DISTANCE + distance, getWidth() - MAX_DISTANCE * 2 - distance * 2,
										getHeight() - MAX_DISTANCE * 2 - distance * 2, null);
					}
				}
				if (Editor.TEXTURER == Editor.TEXTURER_COLLISION_ID)
				{
					if (getData().getCOL_ID() != 0)
					{
						g.drawImage(Constants.IMAGES[Constants.COL_ID_TO_IMAGE_ID(getData().getCOL_ID())], x + MAX_DISTANCE + distance,
										y + MAX_DISTANCE + distance, getWidth() - MAX_DISTANCE * 2 - distance * 2,
										getHeight() - MAX_DISTANCE * 2 - distance * 2, null);
					}
				}
				if (Editor.TEXTURER == Editor.TEXTURER_DECOR_ID)
				{
					if (getData().getOBJ_ID() != 0)
					{
						g.drawImage(Constants.IMAGES[Constants.OBJ_ID_TO_IMAGE_ID(getData().getOBJ_ID())], x + MAX_DISTANCE + distance,
										y + MAX_DISTANCE + distance, getWidth() - MAX_DISTANCE * 2 - distance * 2,
										getHeight() - MAX_DISTANCE * 2 - distance * 2, null);
					}
				}
				if (hasFocus)
				{
					g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
					g.fillRect(getX(), getY(), getWidth(), getHeight());
					g.setColor(Constants.SELECTED_COLOR);
					g.drawRect(getX(), getY(), getWidth(), getHeight());
				}
			}
			else
			{
				if (Editor.TEXTURER == Editor.TEXTURER_COLLISION_ID)
				{
					if (getData().getCOL_ID() != 0)
					{
						g.drawImage(Constants.IMAGES[Constants.COL_ID_TO_IMAGE_ID(getData().getCOL_ID())], x, y, getWidth(), getHeight(), null);
					}
				}
				else
				{
					if (getData().getTILE_ID() != 0)
					{
						g.drawImage(Constants.IMAGES[Constants.TILE_ID_TO_IMAGE_ID(getData().getTILE_ID())], x, y, getWidth(), getHeight(), null);
					}
					if (getData().getOBJ_ID() != 0)
					{
						g.drawImage(Constants.IMAGES[Constants.OBJ_ID_TO_IMAGE_ID(getData().getOBJ_ID())], x, y, getWidth(), getHeight(), null);
					}
					if (getData().getUNIT_ID() != 0)
					{
						g.drawImage(Constants.IMAGES[Constants.UNIT_ID_TO_IMAGE_ID(getData().getUNIT_ID())], x, y, getWidth(), getHeight(), null);
					}
				}
			}
			g.setColor(Constants.MAIN_COLOR);
			g.drawRect(x, y, getWidth(), getHeight());

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

	public boolean isVisible()
	{
		return visible;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

	public CellData getData()
	{
		return data;
	}

	public void setData(CellData data)
	{
		this.data = data;
	}

	public boolean isHighlight()
	{
		return highlight;
	}

	public void setHighlight(boolean highlight)
	{
		this.highlight = highlight;
	}

	public boolean isVisited()
	{
		return visited;
	}

	public void setVisited(boolean visited)
	{
		this.visited = visited;
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
}
