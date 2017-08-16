package com.aspire.goldenapple.gui.state;

import com.aspire.goldenapple.Program;

public class State
{
	protected Program	program;
	protected boolean	shouldDraw;
	protected boolean	shouldUpdate;
	protected long		lastTime;

	public State(Program program)
	{
		this.program = program;
		shouldDraw = false;
		shouldUpdate = false;
	}

	public void update()
	{
	}

	public void draw(java.awt.Graphics g)
	{
	}

}
