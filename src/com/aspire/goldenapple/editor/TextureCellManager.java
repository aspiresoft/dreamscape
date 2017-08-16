package com.aspire.goldenapple.editor;

import java.awt.Graphics;
import java.awt.Rectangle;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.io.CellData;
import com.aspire.goldenapple.io.InputHandler;

public class TextureCellManager
{
	public static final int	MATRIX_X	= 5, MATRIX_Y = 20;

	private Editor			editor;
	private int				x, y, width, height;
	private GridCell[][]	Texture_Grids;
	private GridCell		selectedTexture;
	private GridCell		selectedSecondsTexture;

	public TextureCellManager(Editor editor, int x, int y, int width, int height)
	{
		this.editor = editor;
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);

		init();
	}

	public void init()
	{
		Texture_Grids = new GridCell[MATRIX_X][MATRIX_Y];
		CellData[] Textures = new CellData[MATRIX_X * MATRIX_Y];
		for (int i = 0; i < Textures.length; i++)
		{
			Textures[i] = new CellData();
		}
		for (int i = 0; i <= Constants.UNIT_COUNT; i++)
		{
			Textures[i].setUNIT_ID(i);
		}
		for (int i = 0; i <= Constants.TILE_COUNT; i++)
		{
			Textures[i].setTILE_ID(i);
		}
		for (int i = 0; i <= Constants.COLLISION_COUNT; i++)
		{
			Textures[i].setCOL_ID(i);
		}
		for (int i = 0; i <= Constants.OBJECT_COUNT; i++)
		{
			Textures[i].setOBJ_ID(i);
		}
		for (int i = 0; i < MATRIX_X; i++)
		{
			for (int j = 0; j < MATRIX_Y; j++)
			{
				Texture_Grids[i][j] = new GridCell(getX() + i * Constants.GRID_SIZE, getY() + j * Constants.GRID_SIZE, Constants.GRID_SIZE,
								Constants.GRID_SIZE, Textures[j * MATRIX_X + i], true);
			}
		}
		setSelectedTexture(Texture_Grids[0][0]);
		setSelectedSecondsTexture(Texture_Grids[0][0]);
		editor.getFirstTexture().setData(new CellData());
		editor.getSecondTexture().setData(new CellData());
	}

	public void update()
	{
		if (InputHandler.mouseClicked)
		{
			if (new Rectangle(getX(), getY(), getWidth(), getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY))
			{
				InputHandler.clearInput();
				if (Editor.TOOL == Editor.TOOL_SELECT_ID)
				{
					Editor.TOOL = Editor.TOOL_PENCIL_ID;
				}
				GridCell texture = findSelectedTexture();
				if (texture != null)
				{
					CellData data = new CellData(texture.getData().getTILE_ID(), texture.getData().getUNIT_ID(), texture.getData().getCOL_ID(),
									texture.getData().getOBJ_ID());
					setSelectedTexture(texture);
					editor.getFirstTexture().setData(data);
				}

			}
		}
		if (InputHandler.rightMouseClicked)
		{
			if (new Rectangle(getX(), getY(), getWidth(), getHeight()).contains(InputHandler.mouseX, InputHandler.mouseY))
			{
				GridCell texture = findSelectedTexture();
				if (texture != null)
				{
					CellData data = new CellData(texture.getData().getTILE_ID(), texture.getData().getUNIT_ID(), texture.getData().getCOL_ID(),
									texture.getData().getOBJ_ID());
					setSelectedSecondsTexture(texture);
					editor.getSecondTexture().setData(data);
				}
			}
		}
		for (int i = 0; i < MATRIX_X; i++)
		{
			for (int j = 0; j < MATRIX_Y; j++)
			{
				Texture_Grids[i][j].update();
			}
		}
	}

	public void draw(Graphics g)
	{
		g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
		g.fillRect(x, y, width, height);

		for (int i = 0; i < MATRIX_X; i++)
		{
			for (int j = 0; j < MATRIX_Y; j++)
			{
				Texture_Grids[i][j].draw(g);
			}
		}
		if (getSelectedTexture() != null)
		{
			g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
			g.fillRect(getSelectedTexture().getX(), getSelectedTexture().getY(), Constants.GRID_SIZE, Constants.GRID_SIZE);
			g.setColor(Constants.SELECTED_COLOR);
			g.drawRect(getSelectedTexture().getX(), getSelectedTexture().getY(), Constants.GRID_SIZE, Constants.GRID_SIZE);
		}
		if (getSelectedSecondsTexture() != null)
		{
			g.setColor(Constants.GREEN_TRANSPARENT_COLOR);
			g.fillRect(getSelectedSecondsTexture().getX(), getSelectedSecondsTexture().getY(), Constants.GRID_SIZE, Constants.GRID_SIZE);
			g.setColor(Constants.STEEL_GREEN_COLOR);
			g.drawRect(getSelectedSecondsTexture().getX(), getSelectedSecondsTexture().getY(), Constants.GRID_SIZE, Constants.GRID_SIZE);
		}
	}

	private GridCell findSelectedTexture()
	{
		for (int i = 0; i < MATRIX_X; i++)
		{
			for (int j = 0; j < MATRIX_Y; j++)
			{
				GridCell temp = Texture_Grids[i][j];
				if (new Rectangle(temp.getX(), temp.getY(), Constants.GRID_SIZE, Constants.GRID_SIZE).contains(InputHandler.mouseX,
								InputHandler.mouseY))
				{
					return temp;
				}
			}

		}
		return null;
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

	public GridCell getSelectedTexture()
	{
		return selectedTexture;
	}

	public void setSelectedTexture(GridCell selectedTexture)
	{
		this.selectedTexture = selectedTexture;
	}

	public GridCell getSelectedSecondsTexture()
	{
		return selectedSecondsTexture;
	}

	public void setSelectedSecondsTexture(GridCell selectedSecondsTexture)
	{
		this.selectedSecondsTexture = selectedSecondsTexture;
	}

}
