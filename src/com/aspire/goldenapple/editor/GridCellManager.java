package com.aspire.goldenapple.editor;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import javax.swing.JOptionPane;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.game.unit.Door;
import com.aspire.goldenapple.game.unit.Gate;
import com.aspire.goldenapple.game.unit.PlayerNinja;
import com.aspire.goldenapple.io.CellData;
import com.aspire.goldenapple.io.InputHandler;

public class GridCellManager
{
	public static final int		GRID_MOVE_AMOUNT	= 1;
	public static final int		MATRIX_X			= 20, MATRIX_Y = 20;

	private static final long	DELAY				= 25;

	private Editor				editor;
	private int					x, y, width, height;
	private GridCell[][]		Layer_1_Grids, Layer_2_Grids, Layer_3_Grids;
	private GridCell[][]		Warp_1_Grids, Warp_2_Grids, Warp_3_Grids;
	private int					indicatorHorizontal;
	private int					indicatorVertical;
	private long				lastTime;
	private boolean				shouldWork;
	private int					lineEndX;
	private int					lineEndY;
	private int					lineStartX;
	private int					lineStartY;
	private int					lineTempX;
	private int					lineTempY;
	private boolean				drawingALine;
	private boolean				drawingASquare;
	private boolean				selectingGrids;
	private int					lineSecondEndX;
	private int					lineSecondEndY;
	private int					lineSecondStartX;
	private int					lineSecondStartY;
	private int					lineSecondTempX;
	private int					lineSecondTempY;
	private boolean				drawingASecondLine;
	private boolean				drawingASecondSquare;
	private boolean				selectingSecondGrids;

	private ArrayList<GridCell>	tempGrids;
	private ArrayList<GridCell>	copyGrids;
	private ArrayList<GridCell>	cutGrids;

	public GridCellManager(Editor editor, int x, int y, int width, int height)
	{
		this.editor = editor;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		init();
	}

	public void init()
	{
		drawingALine = false;
		drawingASquare = false;
		selectingGrids = false;

		drawingASecondLine = false;
		drawingASecondSquare = false;
		selectingSecondGrids = false;

		setTempGrids(new ArrayList<GridCell>());
		copyGrids = new ArrayList<GridCell>();
		cutGrids = new ArrayList<GridCell>();

		lastTime = System.currentTimeMillis();
		indicatorHorizontal = 1;
		indicatorVertical = 1;
		setLayer_1_Grids(new GridCell[Constants.MAP_SIZE_X][Constants.MAP_SIZE_Y]);
		setLayer_2_Grids(new GridCell[Constants.MAP_SIZE_X][Constants.MAP_SIZE_Y]);
		setLayer_3_Grids(new GridCell[Constants.MAP_SIZE_X][Constants.MAP_SIZE_Y]);
		setWarp_1_Grids(new GridCell[Constants.SCREEN_X][Constants.SCREEN_Y]);
		setWarp_2_Grids(new GridCell[Constants.SCREEN_X][Constants.SCREEN_Y]);
		setWarp_3_Grids(new GridCell[Constants.SCREEN_X][Constants.SCREEN_Y]);
		for (int i = 0; i < Constants.MAP_SIZE_X; i++)
		{
			for (int j = 0; j < Constants.MAP_SIZE_Y; j++)
			{
				getLayer_1_Grids()[i][j] = new GridCell(x + Constants.GRID_SIZE * i, y + Constants.GRID_SIZE * j, Constants.GRID_SIZE,
								Constants.GRID_SIZE, new CellData(), false);
				getLayer_2_Grids()[i][j] = new GridCell(x + Constants.GRID_SIZE * i, y + Constants.GRID_SIZE * j, Constants.GRID_SIZE,
								Constants.GRID_SIZE, new CellData(), false);
				getLayer_3_Grids()[i][j] = new GridCell(x + Constants.GRID_SIZE * i, y + Constants.GRID_SIZE * j, Constants.GRID_SIZE,
								Constants.GRID_SIZE, new CellData(), false);
			}
		}
		for (int i = 0; i < Constants.SCREEN_X; i++)
		{
			for (int j = 0; j < Constants.SCREEN_Y; j++)
			{
				getWarp_1_Grids()[i][j] = new GridCell(x + Constants.GRID_SIZE * i, y + Constants.GRID_SIZE * j, Constants.GRID_SIZE,
								Constants.GRID_SIZE, new CellData(), false);
				getWarp_2_Grids()[i][j] = new GridCell(x + Constants.GRID_SIZE * i, y + Constants.GRID_SIZE * j, Constants.GRID_SIZE,
								Constants.GRID_SIZE, new CellData(), false);
				getWarp_3_Grids()[i][j] = new GridCell(x + Constants.GRID_SIZE * i, y + Constants.GRID_SIZE * j, Constants.GRID_SIZE,
								Constants.GRID_SIZE, new CellData(), false);
			}
		}
		handleMatrixVisibility();
	}

	public void update()
	{
		boolean right = InputHandler.keys[KeyEvent.VK_RIGHT];
		boolean left = InputHandler.keys[KeyEvent.VK_LEFT];
		boolean up = InputHandler.keys[KeyEvent.VK_UP];
		boolean down = InputHandler.keys[KeyEvent.VK_DOWN];
		if ((left || right || up || down) && System.currentTimeMillis() - lastTime > 15 && Editor.WARP == 0)
		{
			lastTime = System.currentTimeMillis();
			if (right)
			{
				right = false;
				moveGrids("RIGHT");
			}
			if (left)
			{
				left = false;
				moveGrids("LEFT");
			}
			if (up)
			{
				up = false;
				moveGrids("UP");
			}
			if (down)
			{
				down = false;
				moveGrids("DOWN");
			}
		}
		if (new Rectangle(x, y, width, height).contains(InputHandler.mouseX, InputHandler.mouseY))
		{
			if (!InputHandler.rightMouseClicked)
			{
				if (drawingASecondLine && Editor.TOOL == Editor.TOOL_LINE_ID
								&& canPlace(editor.getTextureManager().getSelectedSecondsTexture().getData()))
				{// LINE
					if (findSelectedGrid() != null)
					{
						drawingASecondLine = false;
						lineSecondEndX = (int) getGridCoordinates(findSelectedGrid()).getX();
						lineSecondEndY = (int) getGridCoordinates(findSelectedGrid()).getY();
						ArrayList<GridCell> grids = lineGrids(lineSecondStartX, lineSecondStartY, lineSecondEndX, lineSecondEndY);
						for (Iterator<GridCell> iter = grids.iterator(); iter.hasNext();)
						{
							setTexture(iter.next(), false);
						}
						clearHighlight();
					}
				}
				if (drawingASecondSquare && Editor.TOOL == Editor.TOOL_SQUARE_ID
								&& canPlace(editor.getTextureManager().getSelectedSecondsTexture().getData()))
				{// SQUARE
					if (findSelectedGrid() != null)
					{
						drawingASecondSquare = false;
						lineSecondEndX = (int) getGridCoordinates(findSelectedGrid()).getX();
						lineSecondEndY = (int) getGridCoordinates(findSelectedGrid()).getY();
						ArrayList<GridCell> grids = squareGrids(lineSecondStartX, lineSecondStartY, lineSecondEndX, lineSecondEndY);
						for (Iterator<GridCell> iter = grids.iterator(); iter.hasNext();)
						{
							setTexture(iter.next(), false);
						}
						clearHighlight();
					}
				}
				if (selectingSecondGrids && Editor.TOOL == Editor.TOOL_SELECT_ID)
				{// SELECT
					if (findSelectedGrid() != null)
					{
						selectingSecondGrids = false;
						lineSecondEndX = (int) getGridCoordinates(findSelectedGrid()).getX();
						lineSecondEndY = (int) getGridCoordinates(findSelectedGrid()).getY();
						getTempGrids().clear();
						ArrayList<GridCell> grids = squareGrids(lineSecondStartX, lineSecondStartY, lineSecondEndX, lineSecondEndY);
						for (Iterator<GridCell> iter = grids.iterator(); iter.hasNext();)
						{
							GridCell temp = iter.next();
							if (getTempGrids().indexOf(temp) == -1)
							{
								GridCell cell = new GridCell(temp.getX(), temp.getY(), Constants.GRID_SIZE, Constants.GRID_SIZE, temp.getData(),
												false);
								getTempGrids().add(cell);
								cell = null;
							}
						}
					}
				}
			}
			else
			{
				if (Editor.TOOL == Editor.TOOL_PENCIL_ID && canPlace(editor.getTextureManager().getSelectedSecondsTexture().getData()))
				{
					clearHighlight();
					setTexture(findSelectedGrid(), false);

				}
				if (Editor.TOOL == Editor.TOOL_LINE_ID && canPlace(editor.getTextureManager().getSelectedSecondsTexture().getData()))
				{// LINE
					clearHighlight();
					if (!drawingASecondLine)
					{
						if (findSelectedGrid() != null)
						{
							drawingASecondLine = true;
							lineSecondStartX = (int) getGridCoordinates(findSelectedGrid()).getX();
							lineSecondStartY = (int) getGridCoordinates(findSelectedGrid()).getY();
						}
					}
					else
					{
						if (findSelectedGrid() != null)
						{
							lineSecondTempX = (int) getGridCoordinates(findSelectedGrid()).getX();
							lineSecondTempY = (int) getGridCoordinates(findSelectedGrid()).getY();
							ArrayList<GridCell> temp = lineGrids(lineSecondStartX, lineSecondStartY, lineSecondTempX, lineSecondTempY);
							for (Iterator<GridCell> iter = temp.iterator(); iter.hasNext();)
							{
								iter.next().setHighlight(true);
							}
						}
					}
				}
				if (Editor.TOOL == Editor.TOOL_SQUARE_ID && canPlace(editor.getTextureManager().getSelectedSecondsTexture().getData()))
				{// SQUARE
					clearHighlight();
					if (!drawingASecondSquare)
					{
						if (findSelectedGrid() != null)
						{
							drawingASecondSquare = true;
							lineSecondStartX = (int) getGridCoordinates(findSelectedGrid()).getX();
							lineSecondStartY = (int) getGridCoordinates(findSelectedGrid()).getY();
						}
					}
					else
					{
						if (findSelectedGrid() != null)
						{
							lineSecondTempX = (int) getGridCoordinates(findSelectedGrid()).getX();
							lineSecondTempY = (int) getGridCoordinates(findSelectedGrid()).getY();
							ArrayList<GridCell> temp = squareGrids(lineSecondStartX, lineSecondStartY, lineSecondTempX, lineSecondTempY);
							for (Iterator<GridCell> iter = temp.iterator(); iter.hasNext();)
							{
								iter.next().setHighlight(true);
							}
						}
					}
				}
				if (Editor.TOOL == Editor.TOOL_FILL_ID && canPlace(editor.getTextureManager().getSelectedSecondsTexture().getData()))
				{// FILL
					clearHighlight();
					fillGrids(findSelectedGrid(), false);

					clearHighlight();
				}
				if (Editor.TOOL == Editor.TOOL_SELECT_ID)
				{// SELECT
					clearHighlight();
					if (!selectingSecondGrids)
					{
						if (findSelectedGrid() != null)
						{
							selectingSecondGrids = true;
							lineSecondStartX = (int) getGridCoordinates(findSelectedGrid()).getX();
							lineSecondStartY = (int) getGridCoordinates(findSelectedGrid()).getY();
						}
					}
					else
					{
						if (findSelectedGrid() != null)
						{
							lineSecondTempX = (int) getGridCoordinates(findSelectedGrid()).getX();
							lineSecondTempY = (int) getGridCoordinates(findSelectedGrid()).getY();

							ArrayList<GridCell> temp = squareGrids(lineSecondStartX, lineSecondStartY, lineSecondTempX, lineSecondTempY);
							for (Iterator<GridCell> iter = temp.iterator(); iter.hasNext();)
							{
								iter.next().setHighlight(true);
							}
						}
					}

				}
			}
			if (!InputHandler.mouseClicked)
			{
				if (drawingALine && Editor.TOOL == Editor.TOOL_LINE_ID && canPlace(editor.getTextureManager().getSelectedTexture().getData()))
				{// LINE
					if (findSelectedGrid() != null)
					{
						drawingALine = false;
						lineEndX = (int) getGridCoordinates(findSelectedGrid()).getX();
						lineEndY = (int) getGridCoordinates(findSelectedGrid()).getY();
						ArrayList<GridCell> grids = lineGrids(lineStartX, lineStartY, lineEndX, lineEndY);
						for (Iterator<GridCell> iter = grids.iterator(); iter.hasNext();)
						{
							setTexture(iter.next(), true);

						}
						clearHighlight();
					}
				}
				if (drawingASquare && Editor.TOOL == Editor.TOOL_SQUARE_ID && canPlace(editor.getTextureManager().getSelectedTexture().getData()))
				{// SQUARE
					if (findSelectedGrid() != null)
					{
						drawingASquare = false;
						lineEndX = (int) getGridCoordinates(findSelectedGrid()).getX();
						lineEndY = (int) getGridCoordinates(findSelectedGrid()).getY();
						ArrayList<GridCell> grids = squareGrids(lineStartX, lineStartY, lineEndX, lineEndY);
						for (Iterator<GridCell> iter = grids.iterator(); iter.hasNext();)
						{
							setTexture(iter.next(), true);

						}
						clearHighlight();
					}
				}
				if (selectingGrids && Editor.TOOL == Editor.TOOL_SELECT_ID)
				{// SELECT
					if (findSelectedGrid() != null)
					{
						selectingGrids = false;
						lineEndX = (int) getGridCoordinates(findSelectedGrid()).getX();
						lineEndY = (int) getGridCoordinates(findSelectedGrid()).getY();
						getTempGrids().clear();
						ArrayList<GridCell> grids = squareGrids(lineStartX, lineStartY, lineEndX, lineEndY);
						for (Iterator<GridCell> iter = grids.iterator(); iter.hasNext();)
						{
							GridCell temp = iter.next();
							if (getTempGrids().indexOf(temp) == -1)
							{
								GridCell cell = new GridCell(temp.getX(), temp.getY(), Constants.GRID_SIZE, Constants.GRID_SIZE, temp.getData(),
												false);
								getTempGrids().add(cell);
								cell = null;
							}
						}
					}
				}
			}
			else
			{
				if (System.currentTimeMillis() - lastTime > DELAY)
				{
					lastTime = System.currentTimeMillis();
					// PENCIL
					if (Editor.TOOL == Editor.TOOL_PENCIL_ID && canPlace(editor.getTextureManager().getSelectedTexture().getData()))
					{
						clearHighlight();
						setTexture(findSelectedGrid(), true);

					}
					if (Editor.TOOL == Editor.TOOL_LINE_ID && canPlace(editor.getTextureManager().getSelectedTexture().getData()))
					{// LINE
						clearHighlight();
						if (!drawingALine)
						{
							if (findSelectedGrid() != null)
							{
								drawingALine = true;
								lineStartX = (int) getGridCoordinates(findSelectedGrid()).getX();
								lineStartY = (int) getGridCoordinates(findSelectedGrid()).getY();
							}
						}
						else
						{
							if (findSelectedGrid() != null)
							{
								lineTempX = (int) getGridCoordinates(findSelectedGrid()).getX();
								lineTempY = (int) getGridCoordinates(findSelectedGrid()).getY();
								ArrayList<GridCell> temp = lineGrids(lineStartX, lineStartY, lineTempX, lineTempY);
								for (Iterator<GridCell> iter = temp.iterator(); iter.hasNext();)
								{
									iter.next().setHighlight(true);
								}
							}
						}
					}
					if (Editor.TOOL == Editor.TOOL_SQUARE_ID && canPlace(editor.getTextureManager().getSelectedTexture().getData()))
					{// SQUARE
						clearHighlight();
						if (!drawingASquare)
						{
							if (findSelectedGrid() != null)
							{
								drawingASquare = true;
								lineStartX = (int) getGridCoordinates(findSelectedGrid()).getX();
								lineStartY = (int) getGridCoordinates(findSelectedGrid()).getY();
							}
						}
						else
						{
							if (findSelectedGrid() != null)
							{
								lineTempX = (int) getGridCoordinates(findSelectedGrid()).getX();
								lineTempY = (int) getGridCoordinates(findSelectedGrid()).getY();
								ArrayList<GridCell> temp = squareGrids(lineStartX, lineStartY, lineTempX, lineTempY);
								for (Iterator<GridCell> iter = temp.iterator(); iter.hasNext();)
								{
									iter.next().setHighlight(true);
								}
							}
						}
					}
					if (Editor.TOOL == Editor.TOOL_FILL_ID && canPlace(editor.getTextureManager().getSelectedTexture().getData()))
					{// FILL
						clearHighlight();
						fillGrids(findSelectedGrid(), true);

						clearHighlight();
					}
					if (Editor.TOOL == Editor.TOOL_SELECT_ID)
					{// SELECT
						clearHighlight();
						if (!selectingGrids)
						{
							if (findSelectedGrid() != null)
							{
								selectingGrids = true;
								lineStartX = (int) getGridCoordinates(findSelectedGrid()).getX();
								lineStartY = (int) getGridCoordinates(findSelectedGrid()).getY();
							}
						}
						else
						{
							if (findSelectedGrid() != null)
							{
								lineTempX = (int) getGridCoordinates(findSelectedGrid()).getX();
								lineTempY = (int) getGridCoordinates(findSelectedGrid()).getY();

								ArrayList<GridCell> temp = squareGrids(lineStartX, lineStartY, lineTempX, lineTempY);
								for (Iterator<GridCell> iter = temp.iterator(); iter.hasNext();)
								{
									iter.next().setHighlight(true);
								}
							}
						}
					}
				}
			}
		}
		if (InputHandler.mouseClicked)
		{
			// COPY CLICKED
			if (new Rectangle(editor.getTools()[Editor.TOOL_COPY_ID].getX(), editor.getTools()[Editor.TOOL_COPY_ID].getY(),
							editor.getTools()[Editor.TOOL_COPY_ID].getWidth(), editor.getTools()[Editor.TOOL_COPY_ID].getHeight())
											.contains(InputHandler.mouseX, InputHandler.mouseY))
			{
				InputHandler.clearInput();
				if (!getTempGrids().isEmpty())
				{
					cutGrids.clear();
					clearHighlight();
					copyGrids.clear();
					for (Iterator<GridCell> iter = getTempGrids().iterator(); iter.hasNext();)
					{
						GridCell temp = iter.next();
						CellData data = new CellData(temp.getData().getTILE_ID(), temp.getData().getUNIT_ID(), temp.getData().getCOL_ID(),
										temp.getData().getOBJ_ID());

						copyGrids.add(new GridCell(temp.getX(), temp.getY(), Constants.GRID_SIZE, Constants.GRID_SIZE, data, false));
						temp = null;
						temp = null;
					}
				}
			}
			// CUT CLICKED
			if (new Rectangle(editor.getTools()[Editor.TOOL_CUT_ID].getX(), editor.getTools()[Editor.TOOL_CUT_ID].getY(),
							editor.getTools()[Editor.TOOL_CUT_ID].getWidth(), editor.getTools()[Editor.TOOL_CUT_ID].getHeight())
											.contains(InputHandler.mouseX, InputHandler.mouseY))
			{
				InputHandler.clearInput();
				if (!getTempGrids().isEmpty())
				{
					copyGrids.clear();
					clearHighlight();
					cutGrids.clear();
					for (Iterator<GridCell> iter = getTempGrids().iterator(); iter.hasNext();)
					{
						GridCell temp = iter.next();
						CellData data = new CellData(temp.getData().getTILE_ID(), temp.getData().getUNIT_ID(), temp.getData().getCOL_ID(),
										temp.getData().getOBJ_ID());

						cutGrids.add(new GridCell(temp.getX(), temp.getY(), Constants.GRID_SIZE, Constants.GRID_SIZE, data, false));
						temp.getData().setCOL_ID(0);
						temp.getData().setOBJ_ID(0);
						temp.getData().setTILE_ID(0);
						temp.getData().setUNIT_ID(0);
					}
				}
			}
			// PASTE CLICKED
			if (new Rectangle(editor.getTools()[Editor.TOOL_PASTE_ID].getX(), editor.getTools()[Editor.TOOL_PASTE_ID].getY(),
							editor.getTools()[Editor.TOOL_PASTE_ID].getWidth(), editor.getTools()[Editor.TOOL_PASTE_ID].getHeight())
											.contains(InputHandler.mouseX, InputHandler.mouseY))
			{
				InputHandler.clearInput();
				clearHighlight();
				if (!getTempGrids().isEmpty())
				{
					int x0 = (int) getGridCoordinates(getTempGrids().get(0)).getX();
					int y0 = (int) getGridCoordinates(getTempGrids().get(0)).getY();
					if (!copyGrids.isEmpty() || !cutGrids.isEmpty())
					{
						if (!copyGrids.isEmpty())
						{
							ArrayList<GridCell> keepGrids = new ArrayList<GridCell>();
							for (GridCell g : copyGrids)
							{
								CellData data = new CellData(g.getData().getTILE_ID(), g.getData().getUNIT_ID(), g.getData().getCOL_ID(),
												g.getData().getOBJ_ID());
								keepGrids.add(new GridCell(g.getX(), g.getY(), Constants.GRID_SIZE, Constants.GRID_SIZE, data, false));
							}

							int offX = (int) getGridCoordinates(keepGrids.get(0)).getX();
							int offY = (int) getGridCoordinates(keepGrids.get(0)).getY();

							for (Iterator<GridCell> iter = keepGrids.iterator(); iter.hasNext();)
							{
								GridCell source = iter.next();
								int x = (int) getGridCoordinates(source).getX() - offX + x0;
								int y = (int) getGridCoordinates(source).getY() - offY + y0;
								if (x >= (Editor.WARP == 0 ? Constants.MAP_SIZE_X : Constants.SCREEN_X)
												|| y >= (Editor.WARP == 0 ? Constants.MAP_SIZE_Y : Constants.SCREEN_Y))
								{
									continue;
								}
								if (Editor.TEXTURER == Editor.TEXTURER_TILE_ID)
								{
									findGrid(x, y).getData().setTILE_ID(source.getData().getTILE_ID());
								}
								if (Editor.TEXTURER == Editor.TEXTURER_OBJECT_ID)
								{
									findGrid(x, y).getData().setUNIT_ID(source.getData().getUNIT_ID());
								}
								if (Editor.TEXTURER == Editor.TEXTURER_COLLISION_ID)
								{
									findGrid(x, y).getData().setCOL_ID(source.getData().getCOL_ID());
								}
								if (Editor.TEXTURER == Editor.TEXTURER_DECOR_ID)
								{
									findGrid(x, y).getData().setOBJ_ID(source.getData().getOBJ_ID());
								}
							}
							// copyGrids = carryGrids;
						}
						if (!cutGrids.isEmpty())
						{
							ArrayList<GridCell> keepGrids = new ArrayList<GridCell>();
							for (GridCell g : cutGrids)
							{
								CellData data = new CellData(g.getData().getTILE_ID(), g.getData().getUNIT_ID(), g.getData().getCOL_ID(),
												g.getData().getOBJ_ID());
								keepGrids.add(new GridCell(g.getX(), g.getY(), Constants.GRID_SIZE, Constants.GRID_SIZE, data, false));
							}

							int offX = (int) getGridCoordinates(keepGrids.get(0)).getX();
							int offY = (int) getGridCoordinates(keepGrids.get(0)).getY();

							for (Iterator<GridCell> iter = keepGrids.iterator(); iter.hasNext();)
							{
								GridCell source = iter.next();
								int oldX = (int) getGridCoordinates(source).getX();
								int oldY = (int) getGridCoordinates(source).getY();
								int x = oldX - offX + x0;
								int y = oldY - offY + y0;
								if (x >= (Editor.WARP == 0 ? Constants.MAP_SIZE_X : Constants.SCREEN_X)
												|| y >= (Editor.WARP == 0 ? Constants.MAP_SIZE_Y : Constants.SCREEN_Y))
								{
									continue;
								}
								if (Editor.TEXTURER == Editor.TEXTURER_TILE_ID)
								{
									findGrid(x, y).getData().setTILE_ID(source.getData().getTILE_ID());
								}
								if (Editor.TEXTURER == Editor.TEXTURER_OBJECT_ID)
								{
									findGrid(x, y).getData().setUNIT_ID(source.getData().getUNIT_ID());
								}
								if (Editor.TEXTURER == Editor.TEXTURER_COLLISION_ID)
								{
									findGrid(x, y).getData().setCOL_ID(source.getData().getCOL_ID());
								}
								if (Editor.TEXTURER == Editor.TEXTURER_DECOR_ID)
								{
									findGrid(x, y).getData().setOBJ_ID(source.getData().getOBJ_ID());
								}
							}
						}
					}
				}
			}
			// DELETE CLICKED
			if (new Rectangle(editor.getTools()[Editor.TOOL_DELETE_ID].getX(), editor.getTools()[Editor.TOOL_DELETE_ID].getY(),
							editor.getTools()[Editor.TOOL_DELETE_ID].getWidth(), editor.getTools()[Editor.TOOL_DELETE_ID].getHeight())
											.contains(InputHandler.mouseX, InputHandler.mouseY))
			{
				InputHandler.clearInput();
				if (!getTempGrids().isEmpty())
				{
					copyGrids.clear();
					clearHighlight();
					cutGrids.clear();
					for (Iterator<GridCell> iter = getTempGrids().iterator(); iter.hasNext();)
					{
						GridCell temp = iter.next();
						temp.getData().setCOL_ID(0);
						temp.getData().setOBJ_ID(0);
						temp.getData().setTILE_ID(0);
						temp.getData().setUNIT_ID(0);
					}
				}
			}
		}

	}

	public void draw(Graphics g)
	{
		g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
		g.fillRect(x, y, width, height);

		for (int i = 0; i < (Editor.WARP == 0 ? Constants.MAP_SIZE_X : Constants.SCREEN_X); i++)
		{
			for (int j = 0; j < (Editor.WARP == 0 ? Constants.MAP_SIZE_Y : Constants.SCREEN_Y); j++)
			{
				findGrid(i, j).draw(g);
			}
		}
		for (int i = 0; i < (Editor.WARP == 0 ? Constants.MAP_SIZE_X : Constants.SCREEN_X); i++)
		{
			for (int j = 0; j < (Editor.WARP == 0 ? Constants.MAP_SIZE_Y : Constants.SCREEN_Y); j++)
			{
				GridCell temp = findGrid(i, j);
				if (temp.isHighlight())
				{
					g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
					g.fillRect(temp.getX(), temp.getY(), Constants.GRID_SIZE, Constants.GRID_SIZE);
				}
			}
		}

		GridCell temp = findSelectedGrid();
		if (temp != null)
		{
			g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
			g.fillRect(temp.getX(), temp.getY(), Constants.GRID_SIZE, Constants.GRID_SIZE);
		}

		if (!copyGrids.isEmpty())
		{
			g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
			g.fillRect(editor.getTools()[Editor.TOOL_COPY_ID].getX(), editor.getTools()[Editor.TOOL_COPY_ID].getY(),
							editor.getTools()[Editor.TOOL_COPY_ID].getWidth(), editor.getTools()[Editor.TOOL_COPY_ID].getHeight());
		}
		if (!cutGrids.isEmpty())
		{
			g.setColor(Constants.MAIN_TRANSPARENT_SELECTED_COLOR);
			g.fillRect(editor.getTools()[Editor.TOOL_CUT_ID].getX(), editor.getTools()[Editor.TOOL_CUT_ID].getY(),
							editor.getTools()[Editor.TOOL_CUT_ID].getWidth(), editor.getTools()[Editor.TOOL_CUT_ID].getHeight());
		}
	}

	public Point getGridCoordinates(GridCell grid)
	{
		if (grid == null)
		{
			return new Point(-1, -1);
		}
		return new Point((grid.getX() - findGrid(0, 0).getX()) / Constants.GRID_SIZE, (grid.getY() - findGrid(0, 0).getY()) / Constants.GRID_SIZE);
	}

	public Point getParallelGridCoordinates(GridCell grid, int WARP)
	{
		if (grid == null)
		{
			return new Point(-1, -1);
		}
		return new Point((grid.getX() - findGrid(WARP, 0, 0).getX()) / Constants.GRID_SIZE,
						(grid.getY() - findGrid(WARP, 0, 0).getY()) / Constants.GRID_SIZE);
	}

	public boolean canPlace(CellData data)
	{
		if (Editor.TEXTURER == Editor.TEXTURER_COLLISION_ID || Editor.TEXTURER == Editor.TEXTURER_OBJECT_ID)
		{
			if (Editor.TEXTURER == Editor.TEXTURER_COLLISION_ID)
			{
				if (Editor.WARP == 0 && Editor.LAYER != 2)
				{
					JOptionPane.showMessageDialog(null, "Collisions of 1. and 3. layers can not be changed!", "Error",
									JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			}
			else
			{
				if (data.getUNIT_ID() == PlayerNinja.CODE && Editor.LAYER != 2)
				{
					JOptionPane.showMessageDialog(null, "You can place Player Unit only to 2. Layer.", "Error", JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
				if (((Editor.WARP == 0) && (Editor.LAYER == 1 || Editor.LAYER == 3)) && (data.getUNIT_ID() == Door.CODE1
								|| data.getUNIT_ID() == Door.CODE2 || data.getUNIT_ID() == Door.CODE3 || data.getUNIT_ID() == Door.CODE4))
				{
					JOptionPane.showMessageDialog(null, "You can not place Door unit to 1.Layer and 3.Layer.", "Error",
									JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
				if (data.getUNIT_ID() == Gate.CODE && Editor.LAYER != 2)
				{
					JOptionPane.showMessageDialog(null, "You can place Exit Gate Unit only to 2. Layer.", "Error", JOptionPane.INFORMATION_MESSAGE);
					return false;
				}

			}
		}
		return true;
	}

	public void setTexture(GridCell clicked, boolean flag)
	{

		CellData targetData = clicked.getData();
		CellData sourceData = null;
		if (flag)
		{
			if (editor.getTextureManager().getSelectedTexture() != null)
			{
				sourceData = editor.getTextureManager().getSelectedTexture().getData();
			}
		}
		else
		{
			if (editor.getTextureManager().getSelectedSecondsTexture() != null)
			{
				sourceData = editor.getTextureManager().getSelectedSecondsTexture().getData();
			}
		}
		if (sourceData != null)
		{
			if (Editor.TEXTURER == Editor.TEXTURER_TILE_ID)
			{
				targetData.setTILE_ID(sourceData.getTILE_ID());
				if (sourceData.getTILE_ID() == 0)
				{
					targetData.setCOL_ID(0);
				}
				else
				{
					targetData.setCOL_ID(1);
				}
			}
			if (Editor.TEXTURER == Editor.TEXTURER_OBJECT_ID)
			{
				if (canPlace(sourceData))
				{
					targetData.setUNIT_ID(sourceData.getUNIT_ID());
				}
			}
			if (Editor.TEXTURER == Editor.TEXTURER_COLLISION_ID)
			{
				if (canPlace(sourceData))
				{
					targetData.setCOL_ID(sourceData.getCOL_ID());
				}
			}
			if (Editor.TEXTURER == Editor.TEXTURER_DECOR_ID)
			{
				targetData.setOBJ_ID(sourceData.getOBJ_ID());
			}
		}
	}

	public void moveGrids(String direction)
	{
		if (Editor.WARP == 0)
		{
			int oldX = indicatorHorizontal;
			if (direction.equals("RIGHT"))
			{
				if (indicatorHorizontal < (Constants.MAP_SIZE_X / GRID_MOVE_AMOUNT) - (Constants.SCREEN_X - 1))
				{
					indicatorHorizontal++;
				}
			}
			if (direction.equals("LEFT"))
			{
				if (indicatorHorizontal > 1)
				{
					indicatorHorizontal--;
				}
			}
			int oldY = indicatorVertical;
			if (direction.equals("DOWN"))
			{
				if (indicatorVertical < (Constants.MAP_SIZE_Y / GRID_MOVE_AMOUNT) - (Constants.SCREEN_Y - 1))
				{
					indicatorVertical++;
				}
			}
			if (direction.equals("UP"))
			{
				if (indicatorVertical > 1)
				{
					indicatorVertical--;
				}
			}
			if (oldX != indicatorHorizontal)
			{
				for (int i = 0; i < Constants.MAP_SIZE_X; i++)
				{
					for (int j = 0; j < Constants.MAP_SIZE_Y; j++)
					{
						GridCell grid = findGrid(i, j);
						grid.setX(grid.getX() + ((GRID_MOVE_AMOUNT * Constants.GRID_SIZE)) * (oldX - indicatorHorizontal));
					}
				}
				handleMatrixVisibility();
			}
			if (oldY != indicatorVertical)
			{
				for (int i = 0; i < Constants.MAP_SIZE_X; i++)
				{
					for (int j = 0; j < Constants.MAP_SIZE_Y; j++)
					{
						GridCell grid = findGrid(i, j);
						grid.setY(grid.getY() + ((GRID_MOVE_AMOUNT * Constants.GRID_SIZE)) * (oldY - indicatorVertical));
					}
				}
				handleMatrixVisibility();
			}
		}
	}

	public void handleMatrixVisibility()
	{
		for (int i = 0; i < Constants.MAP_SIZE_X; i++)
		{
			for (int j = 0; j < Constants.MAP_SIZE_Y; j++)
			{
				getLayer_1_Grids()[i][j].setVisible(false);
				getLayer_2_Grids()[i][j].setVisible(false);
				getLayer_3_Grids()[i][j].setVisible(false);
			}
		}
		for (int i = 0; i < Constants.SCREEN_X; i++)
		{
			for (int j = 0; j < Constants.SCREEN_Y; j++)
			{
				getWarp_1_Grids()[i][j].setVisible(false);
				getWarp_2_Grids()[i][j].setVisible(false);
				getWarp_3_Grids()[i][j].setVisible(false);
			}
		}
		for (int i = 0; i < (Editor.WARP == 0 ? Constants.MAP_SIZE_X : Constants.SCREEN_X); i++)
		{
			for (int j = 0; j < (Editor.WARP == 0 ? Constants.MAP_SIZE_Y : Constants.SCREEN_Y); j++)
			{
				GridCell grid = findGrid(i, j);
				if (new Rectangle(x, y, width, height).contains(grid.getX() + Constants.GRID_SIZE / 2, grid.getY() + Constants.GRID_SIZE / 2))
				{
					grid.setVisible(true);
				}
			}
		}
	}

	public GridCell findSelectedGrid()
	{
		for (int i = 0; i < (Editor.WARP == 0 ? Constants.MAP_SIZE_X : Constants.SCREEN_X); i++)
		{
			for (int j = 0; j < (Editor.WARP == 0 ? Constants.MAP_SIZE_Y : Constants.SCREEN_Y); j++)
			{
				GridCell grid = findGrid(i, j);
				if (grid.isVisible())
				{
					if (new Rectangle(grid.getX(), grid.getY(), Constants.GRID_SIZE, Constants.GRID_SIZE).contains(InputHandler.mouseX,
									InputHandler.mouseY))
					{
						return grid;
					}
				}
			}
		}
		return null;
	}

	public ArrayList<GridCell> countOfSpecifiedId(int WARP_ID, int LAYER_ID, int id)
	{
		ArrayList<GridCell> grids = new ArrayList<GridCell>();

		for (int i = 0; i < (WARP_ID == 0 ? Constants.MAP_SIZE_X : Constants.SCREEN_X); i++)
		{
			for (int j = 0; j < (WARP_ID == 0 ? Constants.MAP_SIZE_Y : Constants.SCREEN_Y); j++)
			{
				if (WARP_ID == 0)
				{
					if (LAYER_ID == 1)
					{
						if (Layer_1_Grids[i][j].getData().getUNIT_ID() == id)
						{
							grids.add(Layer_1_Grids[i][j]);
						}
					}
					if (LAYER_ID == 2)
					{
						if (Layer_2_Grids[i][j].getData().getUNIT_ID() == id)
						{
							grids.add(Layer_2_Grids[i][j]);
						}
					}
					if (LAYER_ID == 3)
					{
						if (Layer_3_Grids[i][j].getData().getUNIT_ID() == id)
						{
							grids.add(Layer_3_Grids[i][j]);
						}
					}

				}
				else
				{
					if (WARP_ID == 1)
					{
						if (Warp_1_Grids[i][j].getData().getUNIT_ID() == id)
						{
							grids.add(Warp_1_Grids[i][j]);
						}
					}
					if (WARP_ID == 2)
					{
						if (Warp_2_Grids[i][j].getData().getUNIT_ID() == id)
						{
							grids.add(Warp_2_Grids[i][j]);
						}
					}
					if (WARP_ID == 3)
					{
						if (Warp_3_Grids[i][j].getData().getUNIT_ID() == id)
						{
							grids.add(Warp_3_Grids[i][j]);
						}
					}
				}
			}
		}
		return grids;
	}

	private ArrayList<GridCell> lineGrids(int startX, int startY, int endX, int endY)
	{
		int x0 = startX;
		int y0 = startY;
		int x1 = endX;
		int y1 = endY;
		GridCell endGrid = findGrid(endX, endY);
		ArrayList<GridCell> gridsToAdd = new ArrayList<>();

		int length, i;
		double x, y;
		double xincrement;
		double yincrement;

		length = Math.abs(x1 - x0);
		if (Math.abs(y1 - y0) > length)
		{
			length = Math.abs(y1 - y0);
		}
		xincrement = (double) (x1 - x0) / (double) length;
		yincrement = (double) (y1 - y0) / (double) length;
		x = x0 + 0.5;
		y = y0 + 0.5;
		for (i = 1; i <= length; ++i)
		{
			gridsToAdd.add(findGrid((int) x, (int) y));
			x += xincrement;
			y += yincrement;
		}
		gridsToAdd.add(endGrid);

		return gridsToAdd;
	}

	private void clearHighlight()
	{
		for (int i = 0; i < Constants.MAP_SIZE_X; i++)
		{
			for (int j = 0; j < Constants.MAP_SIZE_Y; j++)
			{
				Layer_1_Grids[i][j].setHighlight(false);
				Layer_2_Grids[i][j].setHighlight(false);
				Layer_3_Grids[i][j].setHighlight(false);
				Layer_1_Grids[i][j].setVisited(false);
				Layer_2_Grids[i][j].setVisited(false);
				Layer_3_Grids[i][j].setVisited(false);
			}
		}
		for (int i = 0; i < Constants.SCREEN_X; i++)
		{
			for (int j = 0; j < Constants.SCREEN_Y; j++)
			{
				Warp_1_Grids[i][j].setHighlight(false);
				Warp_2_Grids[i][j].setHighlight(false);
				Warp_3_Grids[i][j].setHighlight(false);
				Warp_1_Grids[i][j].setVisited(false);
				Warp_2_Grids[i][j].setVisited(false);
				Warp_3_Grids[i][j].setVisited(false);
			}
		}
	}

	private ArrayList<GridCell> squareGrids(int x1, int y1, int x2, int y2)
	{
		ArrayList<GridCell> grids = new ArrayList<GridCell>();
		int deltaX = x2 - x1;
		int deltaY = y2 - y1;
		if (deltaX >= 0 && deltaY >= 0)
		{
			for (int x = x1; x <= x2; x++)
			{
				for (int y = y1; y <= y2; y++)
				{
					grids.add(findGrid(x, y));
				}
			}
		}
		if (deltaX < 0 && deltaY >= 0)
		{
			for (int x = x2; x <= x1; x++)
			{
				for (int y = y1; y <= y2; y++)
				{
					grids.add(findGrid(x, y));
				}
			}
		}
		if (deltaX >= 0 && deltaY < 0)
		{
			for (int x = x1; x <= x2; x++)
			{
				for (int y = y2; y <= y1; y++)
				{
					grids.add(findGrid(x, y));
				}
			}
		}
		if (deltaX < 0 && deltaY < 0)
		{
			for (int x = x2; x <= x1; x++)
			{
				for (int y = y2; y <= y1; y++)
				{
					grids.add(findGrid(x, y));
				}
			}
		}
		return grids;
	}

	private GridCell findGrid(int x, int y)
	{
		if (Editor.WARP == 0)
		{
			if (Editor.LAYER == 1)
			{
				return Layer_1_Grids[x][y];
			}
			if (Editor.LAYER == 2)
			{
				return Layer_2_Grids[x][y];
			}
			if (Editor.LAYER == 3)
			{
				return Layer_3_Grids[x][y];
			}
		}
		else
		{
			if (Editor.WARP == 1)
			{
				return Warp_1_Grids[x][y];
			}
			if (Editor.WARP == 2)
			{
				return Warp_2_Grids[x][y];
			}
			if (Editor.WARP == 3)
			{
				return Warp_3_Grids[x][y];
			}
		}
		return null;
	}

	private GridCell findGrid(int WARP, int x, int y)
	{
		if (WARP == 1)
		{
			return Warp_1_Grids[x][y];
		}
		if (WARP == 2)
		{
			return Warp_2_Grids[x][y];
		}
		if (WARP == 3)
		{
			return Warp_3_Grids[x][y];
		}

		return null;
	}

	private void fillGrids(GridCell grid, boolean flag)
	{
		int id = 0;
		if (Editor.TEXTURER == Editor.TEXTURER_TILE_ID)
		{
			id = grid.getData().getTILE_ID();
		}
		if (Editor.TEXTURER == Editor.TEXTURER_OBJECT_ID)
		{
			id = grid.getData().getUNIT_ID();
		}
		if (Editor.TEXTURER == Editor.TEXTURER_COLLISION_ID)
		{
			id = grid.getData().getCOL_ID();
		}
		if (Editor.TEXTURER == Editor.TEXTURER_DECOR_ID)
		{
			id = grid.getData().getOBJ_ID();
		}
		if (!available((int) getGridCoordinates(grid).getX(), (int) getGridCoordinates(grid).getY()))
		{
			return;
		}
		Stack<GridCell> queue = new Stack<GridCell>();
		queue.push(grid);
		while (!queue.isEmpty())
		{
			GridCell tempGrid = queue.pop();

			if (tempGrid.isVisited())
			{
				continue;
			}
			setTexture(tempGrid, flag);
			tempGrid.setVisited(true);

			int mX = (int) getGridCoordinates(tempGrid).getX();
			int mY = (int) getGridCoordinates(tempGrid).getY();

			if (available(mX - 1, mY) && shouldFillGrid(findGrid(mX - 1, mY), id))
			{
				queue.add(findGrid(mX - 1, mY));
			}
			if (available(mX + 1, mY) && shouldFillGrid(findGrid(mX + 1, mY), id))
			{
				queue.add(findGrid(mX + 1, mY));
			}
			if (available(mX, mY + 1) && shouldFillGrid(findGrid(mX, mY + 1), id))
			{
				queue.add(findGrid(mX, mY + 1));
			}
			if (available(mX, mY - 1) && shouldFillGrid(findGrid(mX, mY - 1), id))
			{
				queue.add(findGrid(mX, mY - 1));
			}
		}
	}

	private boolean available(int x, int y)
	{
		if (x >= 0 && x < (Editor.WARP == 0 ? Constants.MAP_SIZE_X : Constants.SCREEN_X) && y >= 0
						&& y < (Editor.WARP == 0 ? Constants.MAP_SIZE_Y : Constants.SCREEN_Y))
		{
			return true;
		}
		return false;
	}

	private boolean shouldFillGrid(GridCell grid, int id)
	{
		if (grid == null)
		{
			return false;
		}
		if (grid.isVisited())
		{
			return false;
		}
		if (Editor.TEXTURER == Editor.TEXTURER_TILE_ID)
		{
			if (grid.getData().getTILE_ID() != id)
			{
				return false;
			}
		}
		if (Editor.TEXTURER == Editor.TEXTURER_OBJECT_ID)
		{
			if (grid.getData().getUNIT_ID() != id)
			{
				return false;
			}
		}
		if (Editor.TEXTURER == Editor.TEXTURER_COLLISION_ID)
		{
			if (grid.getData().getCOL_ID() != id)
			{
				return false;
			}
		}
		if (Editor.TEXTURER == Editor.TEXTURER_DECOR_ID)
		{
			if (grid.getData().getOBJ_ID() != id)
			{
				return false;
			}
		}

		return true;
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

	public GridCell[][] getLayer_1_Grids()
	{
		return Layer_1_Grids;
	}

	public void setLayer_1_Grids(GridCell[][] layer_1_Grids)
	{
		Layer_1_Grids = layer_1_Grids;
	}

	public GridCell[][] getLayer_2_Grids()
	{
		return Layer_2_Grids;
	}

	public void setLayer_2_Grids(GridCell[][] layer_2_Grids)
	{
		Layer_2_Grids = layer_2_Grids;
	}

	public GridCell[][] getLayer_3_Grids()
	{
		return Layer_3_Grids;
	}

	public void setLayer_3_Grids(GridCell[][] layer_3_Grids)
	{
		Layer_3_Grids = layer_3_Grids;
	}

	public GridCell[][] getWarp_1_Grids()
	{
		return Warp_1_Grids;
	}

	public void setWarp_1_Grids(GridCell[][] warp_1_Grids)
	{
		Warp_1_Grids = warp_1_Grids;
	}

	public GridCell[][] getWarp_2_Grids()
	{
		return Warp_2_Grids;
	}

	public void setWarp_2_Grids(GridCell[][] warp_2_Grids)
	{
		Warp_2_Grids = warp_2_Grids;
	}

	public GridCell[][] getWarp_3_Grids()
	{
		return Warp_3_Grids;
	}

	public void setWarp_3_Grids(GridCell[][] warp_3_Grids)
	{
		Warp_3_Grids = warp_3_Grids;
	}

	public void setShouldWork(boolean b)
	{
		this.shouldWork = b;

	}

	public boolean isShouldWork()
	{
		return shouldWork;
	}

	public ArrayList<GridCell> getTempGrids()
	{
		return tempGrids;
	}

	public void setTempGrids(ArrayList<GridCell> tempGrids)
	{
		this.tempGrids = tempGrids;
	}

	public int getHorizontalIndcator()
	{
		return indicatorHorizontal;
	}

	public int getVericalIndcator()
	{
		return indicatorVertical;
	}

}
