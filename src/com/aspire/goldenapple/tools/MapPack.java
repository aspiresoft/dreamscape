package com.aspire.goldenapple.tools;

import java.io.Serializable;
import java.util.ArrayList;
import com.aspire.goldenapple.io.Data;

public class MapPack implements Serializable
{
	private static final long	serialVersionUID	= 199951L;
	public ArrayList<Data>		levels;
	public String				name;

	public MapPack()
	{
		levels = new ArrayList<Data>();
	}

	public MapPack(String name)
	{
		this.name = name;
		levels = new ArrayList<Data>();
	}
}
