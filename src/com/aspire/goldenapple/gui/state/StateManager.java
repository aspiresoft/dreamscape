package com.aspire.goldenapple.gui.state;

import java.awt.event.KeyEvent;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.editor.Editor;
import com.aspire.goldenapple.io.InputHandler;

public class StateManager {
	private final int MIN_SELECT = 1;

	private OpeningState os;
	private StartState ss;
	private Editor es;
	private PlayerState ps;
	private MultiplayerState ms;
	private ConfigState cs;

	private int currentState = 0;

	public StateManager(Program program) {
		os = new OpeningState(program);
		ss = new StartState(program);
		es = new Editor(program);
		ps = new PlayerState(program);
		ms = new MultiplayerState(program);
		cs = new ConfigState(program);

		setState(1);

	}

	public void update() {

		if (os.shouldUpdate) {
			os.update();
		}
		if (ss.shouldUpdate) {
			ss.update();
		}
		if (getEs().shouldUpdate) {
			getEs().update();
		}
		if (getPs().shouldUpdate) {
			getPs().update();
		}
		if (ms.shouldUpdate) {
			ms.update();
		}
		if (cs.shouldUpdate) {
			cs.update();
		}
	}

	public void draw(java.awt.Graphics g) {
		if (os.shouldDraw) {
			os.draw(g);
		}
		if (ss.shouldDraw) {
			ss.draw(g);
		}
		if (getEs().shouldDraw) {
			getEs().draw(g);
		}
		if (getPs().shouldDraw) {
			getPs().draw(g);
		}
		if (ms.shouldDraw) {
			ms.draw(g);
		}
		if (cs.shouldDraw) {
			cs.draw(g);
		}
	}

	public void setState(int s) {
		currentState = s;
		os.lastTime = System.currentTimeMillis();
		ss.lastTime = System.currentTimeMillis();
		getEs().setLastTime(System.currentTimeMillis());
		getPs().lastTime = System.currentTimeMillis();
		os.setSelected(MIN_SELECT);
		InputHandler.keys[KeyEvent.VK_UP] = false;
		InputHandler.keys[KeyEvent.VK_DOWN] = false;
		InputHandler.keys[KeyEvent.VK_ENTER] = false;
		InputHandler.clearInput();
		if (currentState == -1) {
			System.exit(0);
		}
		if (currentState == 1) {
			os.shouldUpdate = true;
			os.shouldDraw = true;
			ss.shouldDraw = false;
			ss.shouldUpdate = false;
			getEs().shouldDraw = false;
			getEs().shouldUpdate = false;
			getPs().shouldUpdate = false;
			getPs().shouldDraw = false;
			cs.shouldDraw = false;
			cs.shouldUpdate = false;
			ms.shouldDraw = false;
			ms.shouldUpdate = false;
			os.init();
		}
		if (currentState == 2) {
			os.shouldUpdate = false;
			os.shouldDraw = false;
			ss.shouldDraw = true;
			ss.shouldUpdate = true;
			getEs().shouldDraw = false;
			getEs().shouldUpdate = false;
			getPs().shouldUpdate = false;
			getPs().shouldDraw = false;
			cs.shouldDraw = false;
			cs.shouldUpdate = false;
			ms.shouldDraw = false;
			ms.shouldUpdate = false;
			ss.init();
		}
		if (currentState == 3) {
			os.shouldUpdate = false;
			os.shouldDraw = false;
			ss.shouldDraw = false;
			ss.shouldUpdate = false;
			getEs().shouldDraw = true;
			getEs().shouldUpdate = true;
			getPs().shouldUpdate = false;
			getPs().shouldDraw = false;
			ms.shouldDraw = false;
			ms.shouldUpdate = false;
			cs.shouldDraw = false;
			cs.shouldUpdate = false;
			getEs().init();
		}
		if (currentState == 4) {
			os.shouldUpdate = false;
			os.shouldDraw = false;
			ss.shouldDraw = false;
			ss.shouldUpdate = false;
			getEs().shouldDraw = false;
			getEs().shouldUpdate = false;
			getPs().shouldUpdate = true;
			getPs().shouldDraw = true;
			ms.shouldDraw = false;
			ms.shouldUpdate = false;
			cs.shouldDraw = false;
			cs.shouldUpdate = false;
			getPs().init();
		}
		if (currentState == 5) {
			os.shouldUpdate = false;
			os.shouldDraw = false;
			ss.shouldDraw = false;
			ss.shouldUpdate = false;
			getEs().shouldDraw = false;
			getEs().shouldUpdate = false;
			getPs().shouldUpdate = false;
			getPs().shouldDraw = false;
			cs.shouldDraw = false;
			cs.shouldUpdate = false;
			ms.shouldDraw = true;
			ms.shouldUpdate = true;
			ms.init();
		}
		if (currentState == 6) {
			os.shouldUpdate = false;
			os.shouldDraw = false;
			ss.shouldDraw = false;
			ss.shouldUpdate = false;
			getEs().shouldDraw = false;
			getEs().shouldUpdate = false;
			getPs().shouldUpdate = false;
			getPs().shouldDraw = false;
			ms.shouldDraw = false;
			ms.shouldUpdate = false;
			cs.shouldDraw = true;
			cs.shouldUpdate = true;
			cs.init();
		}
	}

	public PlayerState getPs() {
		return ps;
	}

	public Editor getEs() {
		return es;
	}
}
