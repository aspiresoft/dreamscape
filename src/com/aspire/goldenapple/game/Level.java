package com.aspire.goldenapple.game;

import java.awt.event.KeyEvent;
import java.util.Iterator;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.io.InputHandler;
import com.aspire.goldenapple.tools.MediaPlayer;
import com.aspire.goldenapple.tools.Message;

public class Level {
	private Program program;
	private GamePanel gamePanel;
	private boolean shouldDraw, shouldUpdate;
	private long musicTimer;
	private long lastTime;
	private MediaPlayer mp;
	private boolean testLevel;
	private boolean confirming;
	private boolean exit;
	private int BG_ID;
	private String LEVEL_NAME;

	public Level(Program program, boolean testLevel, boolean ninja, boolean robot, boolean knight) {
		this.program = program;
		this.setTestLevel(testLevel);
		setGamePanel(new GamePanel(program, this, ninja, robot, knight));
		init();
	}

	public void init() {
		confirming = false;
		setMusicTimer(47000);
		setShouldDraw(false);
		setExit(false);
		setShouldUpdate(false);
		lastTime = System.currentTimeMillis();
	}

	public void startBackgroundMusic(float f) {
		if (!testLevel) {
			setMp(new MediaPlayer(Constants.background_music_01));
			getMp().setVolume(f);
			new Thread(getMp()).start();
		}
	}

	public void update() {
		if (isShouldUpdate()) {
			if (!confirming) {
				getGamePanel().update();
			}
			if (getMp() == null || (!getMp().isPlaying() && !getMp().isPaused())) {
				startBackgroundMusic(0.2f);
			}

			if (getMp() != null) {
				boolean q = InputHandler.keys[KeyEvent.VK_Q];
				if (q && getMp().isPlaying()) {
					q = false;
					getMp().pause();
				}
				boolean e = InputHandler.keys[KeyEvent.VK_E];
				if (e && getMp().isPaused()) {
					e = false;
					getMp().resume();
				}
			}
			if (confirming) {
				boolean enter = InputHandler.keys[KeyEvent.VK_ENTER];
				boolean yes = InputHandler.keys[KeyEvent.VK_Y];
				boolean no = InputHandler.keys[KeyEvent.VK_N];
				if (yes || enter) {
					if (getMp() != null) {
						getMp().stop();
					}
					program.getManager().setState(1);
				}
				if (no) {
					removeMessage("Are you sure you want to exit? Y/N");
					confirming = false;
				}
			}

			if (isExit()) {
				setExit(false);
				setShouldDraw(false);
				setShouldUpdate(false);
				program.getManager().getEs().setLevelTestMode(false);
				program.getManager().getEs().getGridManager().setShouldWork(true);
			}
			boolean esc = InputHandler.keys[KeyEvent.VK_ESCAPE];
			if (esc) {
				if (System.currentTimeMillis() - lastTime > 1000) {
					lastTime = System.currentTimeMillis();
					if (isTestLevel()) {
						esc = false;
						setShouldDraw(false);
						setShouldUpdate(false);
						program.getManager().getEs().setLevelTestMode(false);
						program.getManager().getEs().getGridManager().setShouldWork(true);
					} else {
						confirming = !confirming;
						if (confirming) {
							getGamePanel().getHud().getMessages().add(new Message("Are you sure you want to exit? Y/N", 1000000));
						} else {
							removeMessage("Are you sure you want to exit? Y/N");
						}
					}
				}
			}
		}
	}

	private void removeMessage(String str) {
		for (Iterator<Message> messageIter = getGamePanel().getHud().getMessages().iterator(); messageIter.hasNext();) {
			Message m = (Message) messageIter.next();
			if (m.str.equals(str)) {
				messageIter.remove();
			}
		}
	}

	public void draw(java.awt.Graphics g) {
		if (isShouldDraw()) {
			g.drawImage(Constants.IMAGES[229 + BG_ID], 0, 0, Constants.SIZE.width, Constants.SIZE.height, null);
			getGamePanel().draw(g);
			if (confirming) {
				g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
			}
		}
	}

	public MediaPlayer getMp() {
		return mp;
	}

	public void setMp(MediaPlayer mp) {
		this.mp = mp;
	}

	public boolean isTestLevel() {
		return testLevel;
	}

	public void setTestLevel(boolean testLevel) {
		this.testLevel = testLevel;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public long getMusicTimer() {
		return musicTimer;
	}

	public void setMusicTimer(long musicTimer) {
		this.musicTimer = musicTimer;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public boolean isShouldUpdate() {
		return shouldUpdate;
	}

	public void setShouldUpdate(boolean shouldUpdate) {
		this.shouldUpdate = shouldUpdate;
	}

	public boolean isShouldDraw() {
		return shouldDraw;
	}

	public void setShouldDraw(boolean shouldDraw) {
		this.shouldDraw = shouldDraw;
	}

	public int getBG_ID() {
		return BG_ID;
	}

	public void setBG_ID(int bG_ID) {
		BG_ID = bG_ID;
	}

	public String getLEVEL_NAME() {
		return LEVEL_NAME;
	}

	public void setLEVEL_NAME(String lEVEL_NAME) {
		LEVEL_NAME = lEVEL_NAME;
	}
}
