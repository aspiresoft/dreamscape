package com.aspire.goldenapple.io;

import java.io.Serializable;

public class CellData implements Serializable
{
	private static final long serialVersionUID = 9L;
	private int TILE_ID, UNIT_ID, COL_ID, OBJ_ID;

	public CellData()
	{
		TILE_ID = UNIT_ID = COL_ID = OBJ_ID = 0;
	}

	public CellData(int TILE_ID, int UNIT_ID, int COL_ID, int OBJ_ID)
	{
		this.setTILE_ID(TILE_ID);
		this.setUNIT_ID(UNIT_ID);
		this.setCOL_ID(COL_ID);
		this.setOBJ_ID(OBJ_ID);
	}

	public int getTILE_ID()
	{
		return TILE_ID;
	}

	public void setTILE_ID(int tILE_ID)
	{
		TILE_ID = tILE_ID;
	}

	public int getUNIT_ID()
	{
		return UNIT_ID;
	}

	public int setUNIT_ID(int uNIT_ID)
	{
		UNIT_ID = uNIT_ID;
		return uNIT_ID;
	}

	public int getCOL_ID()
	{
		return COL_ID;
	}

	public int setCOL_ID(int cOL_ID)
	{
		COL_ID = cOL_ID;
		return cOL_ID;
	}

	public int getOBJ_ID()
	{
		return OBJ_ID;
	}

	public void setOBJ_ID(int oBJ_ID)
	{
		OBJ_ID = oBJ_ID;
	}

}
