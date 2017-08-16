package com.aspire.goldenapple.io;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.editor.GridCell;

public class Data implements Serializable
{
	private static final long			serialVersionUID	= 149L;
	private CellData					grid_1[][];
	private CellData					grid_2[][];
	private CellData					grid_3[][];
	private CellData					grid_parallel_1[][];
	private CellData					grid_parallel_2[][];
	private CellData					grid_parallel_3[][];
	private String						name;
	private String						level_name;
	private int							bg_id;
	private HashMap<GridCell, Integer>	doorEntranceIDMAP;
	private ArrayList<GridCell>			doorExitIDMAP;

	public Data()
	{
		bg_id = 1;
		level_name = "";
		doorEntranceIDMAP = new HashMap<>();
		setDoorExitIDMAP(new ArrayList<>());
		setGrid_1(new CellData[Constants.MAP_SIZE_X][Constants.MAP_SIZE_Y]);
		setGrid_2(new CellData[Constants.MAP_SIZE_X][Constants.MAP_SIZE_Y]);
		setGrid_3(new CellData[Constants.MAP_SIZE_X][Constants.MAP_SIZE_Y]);
		setGrid_parallel_1(new CellData[Constants.SCREEN_X][Constants.SCREEN_Y]);
		setGrid_parallel_2(new CellData[Constants.SCREEN_X][Constants.SCREEN_Y]);
		setGrid_parallel_3(new CellData[Constants.SCREEN_X][Constants.SCREEN_Y]);
		for (int i = 0; i < Constants.MAP_SIZE_X; i++)
		{
			for (int j = 0; j < Constants.MAP_SIZE_Y; j++)
			{
				getGrid_1()[i][j] = new CellData();
				getGrid_2()[i][j] = new CellData();
				getGrid_3()[i][j] = new CellData();
			}
		}
		for (int i = 0; i < Constants.SCREEN_X; i++)
		{
			for (int j = 0; j < Constants.SCREEN_Y; j++)
			{
				getGrid_parallel_1()[i][j] = new CellData();
				getGrid_parallel_2()[i][j] = new CellData();
				getGrid_parallel_3()[i][j] = new CellData();
			}
		}
	}

	public void setGameGrids(GridCell[][] grid_1, GridCell[][] grid_2, GridCell[][] grid_3, GridCell[][] grid_4, GridCell[][] grid_5,
					GridCell[][] grid_6)
	{
		for (int i = 0; i < Constants.MAP_SIZE_X; i++)
		{
			for (int j = 0; j < Constants.MAP_SIZE_Y; j++)
			{
				getGrid_1()[i][j] = (grid_1[i][j].getData());
				getGrid_2()[i][j] = (grid_2[i][j].getData());
				getGrid_3()[i][j] = (grid_3[i][j].getData());
			}
		}
		for (int i = 0; i < Constants.SCREEN_X; i++)
		{
			for (int j = 0; j < Constants.SCREEN_Y; j++)
			{
				getGrid_parallel_1()[i][j] = (grid_4[i][j].getData());
				getGrid_parallel_2()[i][j] = (grid_5[i][j].getData());
				getGrid_parallel_3()[i][j] = (grid_6[i][j].getData());
			}
		}
	}

	public void setGameGrids(CellData[][] grid_1, CellData[][] grid_2, CellData[][] grid_3, CellData[][] grid_4, CellData[][] grid_5,
					CellData[][] grid_6)
	{
		for (int i = 0; i < Constants.MAP_SIZE_X; i++)
		{
			for (int j = 0; j < Constants.MAP_SIZE_Y; j++)
			{
				getGrid_1()[i][j] = (grid_1[i][j]);
				getGrid_2()[i][j] = (grid_2[i][j]);
				getGrid_3()[i][j] = (grid_3[i][j]);

			}
		}
		for (int i = 0; i < Constants.SCREEN_X; i++)
		{
			for (int j = 0; j < Constants.SCREEN_Y; j++)
			{
				getGrid_parallel_1()[i][j] = (grid_4[i][j]);
				getGrid_parallel_2()[i][j] = (grid_5[i][j]);
				getGrid_parallel_3()[i][j] = (grid_6[i][j]);
			}
		}
	}

	public CellData[][] getGrid_1()
	{
		return grid_1;
	}

	public void setGrid_1(CellData grid_1[][])
	{
		this.grid_1 = grid_1;
	}

	public CellData[][] getGrid_2()
	{
		return grid_2;
	}

	public void setGrid_2(CellData grid_2[][])
	{
		this.grid_2 = grid_2;
	}

	public CellData[][] getGrid_3()
	{
		return grid_3;
	}

	public void setGrid_3(CellData grid_3[][])
	{
		this.grid_3 = grid_3;
	}

	public CellData[][] getGrid_parallel_1()
	{
		return grid_parallel_1;
	}

	public void setGrid_parallel_1(CellData grid_parallel_1[][])
	{
		this.grid_parallel_1 = grid_parallel_1;
	}

	public CellData[][] getGrid_parallel_2()
	{
		return grid_parallel_2;
	}

	public void setGrid_parallel_2(CellData grid_parallel_2[][])
	{
		this.grid_parallel_2 = grid_parallel_2;
	}

	public CellData[][] getGrid_parallel_3()
	{
		return grid_parallel_3;
	}

	public void setGrid_parallel_3(CellData grid_parallel_3[][])
	{
		this.grid_parallel_3 = grid_parallel_3;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getLevel_name()
	{
		return level_name;
	}

	public void setLevel_name(String level_name)
	{
		this.level_name = level_name;
	}

	public int getBg_id()
	{
		return bg_id;
	}

	public void setBg_id(int bg_id)
	{
		this.bg_id = bg_id;
	}

	public HashMap<GridCell, Integer> getDoorEntranceIDMAP()
	{
		return doorEntranceIDMAP;
	}

	public void setDoorEntranceIDMAP(HashMap<GridCell, Integer> doorEntranceIDMAP)
	{
		this.doorEntranceIDMAP = doorEntranceIDMAP;
	}

	public ArrayList<GridCell> getDoorExitIDMAP()
	{
		return doorExitIDMAP;
	}

	public void setDoorExitIDMAP(ArrayList<GridCell> doorExitIDMAP)
	{
		this.doorExitIDMAP = doorExitIDMAP;
	}

}
