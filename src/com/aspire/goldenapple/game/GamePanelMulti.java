package com.aspire.goldenapple.game;

import com.aspire.goldenapple.Program;

public class GamePanelMulti {
	
	private Program program;
	private Level level;

	
	private boolean ninja,robot,knight;
	public GamePanelMulti(Program program, Level level, boolean ninja, boolean robot, boolean knight) {

		this.program = program;
		this.level = level;
		
		this.ninja = ninja;
		this.robot = robot;
		this.knight = knight;
		
	}

}
