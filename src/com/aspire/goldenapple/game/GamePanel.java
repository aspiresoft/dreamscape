package com.aspire.goldenapple.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.plaf.multi.MultiButtonUI;

import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.Program;
import com.aspire.goldenapple.editor.GridCell;
import com.aspire.goldenapple.game.unit.Acid;
import com.aspire.goldenapple.game.unit.Box;
import com.aspire.goldenapple.game.unit.Coin;
import com.aspire.goldenapple.game.unit.Door;
import com.aspire.goldenapple.game.unit.Fire;
import com.aspire.goldenapple.game.unit.Flag;
import com.aspire.goldenapple.game.unit.Fly;
import com.aspire.goldenapple.game.unit.Gate;
import com.aspire.goldenapple.game.unit.Ghost;
import com.aspire.goldenapple.game.unit.Heart;
import com.aspire.goldenapple.game.unit.Key;
import com.aspire.goldenapple.game.unit.KunaiPack;
import com.aspire.goldenapple.game.unit.Ladder;
import com.aspire.goldenapple.game.unit.LaserMachine;
import com.aspire.goldenapple.game.unit.Lock;
import com.aspire.goldenapple.game.unit.Node;
import com.aspire.goldenapple.game.unit.Particle;
import com.aspire.goldenapple.game.unit.PlayerKnight;
import com.aspire.goldenapple.game.unit.PlayerNinja;
import com.aspire.goldenapple.game.unit.PlayerRobot;
import com.aspire.goldenapple.game.unit.Slime;
import com.aspire.goldenapple.game.unit.Snail;
import com.aspire.goldenapple.game.unit.Spike;
import com.aspire.goldenapple.game.unit.Spinner;
import com.aspire.goldenapple.game.unit.Spring;
import com.aspire.goldenapple.game.unit.Unit;
import com.aspire.goldenapple.game.unit.Volcano;
import com.aspire.goldenapple.gui.FloatingNumber;
import com.aspire.goldenapple.gui.Hud;
import com.aspire.goldenapple.gui.state.PlayerState;
import com.aspire.goldenapple.io.CellData;
import com.aspire.goldenapple.io.Data;
import com.aspire.goldenapple.io.InputHandler;
import com.aspire.goldenapple.tools.Animation;
import com.aspire.goldenapple.tools.MediaPlayer;

public class GamePanel {
	private Program program;
	private int x, y;
	private int invertX, invertY;
	private ArrayList<Unit> units;
	private Brick grids_first[][];
	private Brick grids_second[][];
	private Brick grids_third[][];
	private Brick grids_parallel_first[][];
	private Brick grids_parallel_second[][];
	private Brick grids_parallel_third[][];
	private int collision_matrix[][];
	private int collision_parallel_matrix[][][];
	private ArrayList<Animation> animations;
	private ArrayList<Particle> particles;
	private ArrayList<FloatingNumber> floatingNumbers;
	private Hud hud;
	private Level level;
	private boolean won, lost;
	private boolean started;
	private boolean flag;
	private boolean once;
	private int lastX, lastY;
	public boolean blueFlagPicked;
	public boolean redFlagPicked;
	public boolean greenFlagPicked;
	public boolean yellowFlagPicked;
	public boolean blueKeyPicked;
	public boolean redKeyPicked;
	public boolean yellowKeyPicked;
	public boolean greenKeyPicked;

	private HashMap<GridCell, Integer> doorEntranceIDMAP;
	private ArrayList<GridCell> doorExitIDMAP;

	public boolean ninjaPlaying;
	public boolean robotPlaying;
	public boolean knightPlaying;

	public boolean isMulti;
	public boolean ninjaRobot;
	public boolean ninjaKnight;
	public boolean robotKnight;

	public boolean ninjaFocus;
	public boolean knightFocus;
	public boolean robotFocus;

	public boolean knight, robot, ninja;

	public GamePanel(Program program, Level level, boolean ninja, boolean robot, boolean knight) {
		this.program = program;
		this.setLevel(level);
		this.ninja = ninja;
		this.knight = knight;
		this.robot = robot;

		setUnits(new ArrayList<Unit>());

		setWon(false);
		setLost(false);
		setX(0);
		setY(0);
		started = false;
		flag = false;
		once = false;

		yellowKeyPicked = false;
		redKeyPicked = false;
		blueKeyPicked = false;
		greenKeyPicked = false;
		yellowFlagPicked = true;
		redFlagPicked = true;
		greenFlagPicked = true;
		blueFlagPicked = true;

		grids_first = new Brick[Constants.MAP_SIZE_X][Constants.MAP_SIZE_Y];
		grids_second = new Brick[Constants.MAP_SIZE_X][Constants.MAP_SIZE_Y];
		grids_third = new Brick[Constants.MAP_SIZE_X][Constants.MAP_SIZE_Y];
		grids_parallel_first = new Brick[Constants.SCREEN_X + 10][Constants.SCREEN_Y];
		grids_parallel_second = new Brick[Constants.SCREEN_X + 10][Constants.SCREEN_Y];
		grids_parallel_third = new Brick[Constants.SCREEN_X + 10][Constants.SCREEN_Y];

		setCollision_matrix(new int[Constants.MAP_SIZE_X][Constants.MAP_SIZE_Y]);
		setCollision_parallel_matrix(new int[3][Constants.SCREEN_X + 10][Constants.SCREEN_Y]);

		for (int i = 0; i < Constants.MAP_SIZE_X; i++) {
			for (int j = 0; j < Constants.MAP_SIZE_Y; j++) {
				grids_first[i][j] = new Brick(this, i * Constants.BRICK_SIZE, j * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE);
				grids_second[i][j] = new Brick(this, i * Constants.BRICK_SIZE, j * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE);
				grids_third[i][j] = new Brick(this, i * Constants.BRICK_SIZE, j * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE);
			}
		}
		for (int i = 0; i < Constants.SCREEN_X + 10; i++) {
			for (int j = 0; j < Constants.SCREEN_Y; j++) {
				grids_parallel_first[i][j] = new Brick(this, i * Constants.BRICK_SIZE, j * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE);
				grids_parallel_second[i][j] = new Brick(this, i * Constants.BRICK_SIZE, j * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE);
				grids_parallel_third[i][j] = new Brick(this, i * Constants.BRICK_SIZE, j * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE);
			}
		}
		setHud(new Hud(program, level, this));
		setAnimations(new ArrayList<Animation>());
		setParticles(new ArrayList<Particle>());
		floatingNumbers = new ArrayList<FloatingNumber>();
	}

	public void handleKeys() {
		isMulti = false;

		ninjaPlaying = ninja;
		robotPlaying = robot;
		knightPlaying = knight;

		if (ninja && robot) {
			ninjaRobot = true;
			ninjaFocus = true;
			findNinja(getUnits()).setKeys(true);
			findRobot(getUnits()).setKeys(false);

		}
		if (ninja && knight) {
			ninjaKnight = true;
			ninjaFocus = true;
			findNinja(getUnits()).setKeys(true);
			findKnight(getUnits()).setKeys(false);
		}
		if (knight && robot) {
			robotKnight = true;
			robotFocus = true;
			findRobot(getUnits()).setKeys(true);
			findKnight(getUnits()).setKeys(false);
		}
		if (ninjaRobot || ninjaKnight || robotKnight) {
			isMulti = true;
		}
		if (ninja && !robot && !knight) {
			ninjaFocus = true;
			findNinja(getUnits()).setKeys(true);
		}
		if (!ninja && robot && !knight) {
			findRobot(getUnits()).setKeys(true);
			robotFocus = true;
		}
		if (!ninja && !robot && knight) {
			findKnight(getUnits()).setKeys(true);
			knightFocus = true;
		}
	}

	public void update() {

		boolean b = false;
		for (int i = 0; i < InputHandler.keys.length; i++) {
			if (InputHandler.keys[i]) {
				flag = true;
				break;
			}
		}
		if (flag) {
			started = true;
		}
		if (started) {
			Iterator<Unit> unitIterator = getUnits().iterator();
			while (unitIterator.hasNext()) {
				Unit u = unitIterator.next();

				if (!u.shouldWork) {
					unitIterator.remove();
				} else {
					u.update();
				}
				u = null;
			}
		}

		handlePanelPosition();

		Iterator<Animation> iter = getAnimations().iterator();
		while (iter.hasNext()) {
			Animation a = iter.next();
			if (a != null) {
				a.update();
				if (!a.shouldAnimate) {
					iter.remove();
					a = null;
				}
			}
			a = null;
		}
		iter = null;
		Iterator<Particle> iter2 = getParticles().iterator();
		while (iter2.hasNext()) {
			Particle a = iter2.next();
			if (a != null) {
				a.update();
				if (!a.shouldWork) {
					iter2.remove();
					a = null;
				}
			}
			a = null;
		}
		iter2 = null;

		Iterator<FloatingNumber> iter3 = floatingNumbers.iterator();
		while (iter3.hasNext()) {
			FloatingNumber a = iter3.next();
			if (a != null) {
				a.update();
				if (!a.isAlive()) {
					iter3.remove();
					a = null;
				}
			}
			a = null;
		}
		iter3 = null;

		getHud().update();
	}

	public void draw(java.awt.Graphics g) {
		for (int i = 0; i < Constants.MAP_SIZE_X; i++) {
			for (int j = 0; j < Constants.MAP_SIZE_Y; j++) {
				if (ninjaPlaying && ninjaFocus) {
					if (Engine.isInRange(findNinja(getUnits()).getpX(), findNinja(getUnits()).getpY(), grids_third[i][j].getX(), grids_third[i][j].getY(),
							Constants.LEVEL_LEFT_MOST * 2 + Constants.LEVEL_TOP_MOST * 2)) {
						grids_third[i][j].draw(g);
					}
				}
				if (robotPlaying && robotFocus) {
					if (Engine.isInRange(findRobot(getUnits()).getpX(), findRobot(getUnits()).getpY(), grids_third[i][j].getX(), grids_third[i][j].getY(),
							Constants.LEVEL_LEFT_MOST * 2 + Constants.LEVEL_TOP_MOST * 2)) {
						grids_third[i][j].draw(g);
					}
				}
				if (knightPlaying && knightFocus) {
					if (Engine.isInRange(findKnight(getUnits()).getpX(), findKnight(getUnits()).getpY(), grids_third[i][j].getX(), grids_third[i][j].getY(),
							Constants.LEVEL_LEFT_MOST * 2 + Constants.LEVEL_TOP_MOST * 2)) {
						grids_third[i][j].draw(g);
					}
				}
			}
		}
		for (Iterator<Unit> iter = units.iterator(); iter.hasNext();) {
			Unit u = iter.next();
			if (!(u instanceof PlayerNinja) && !(u instanceof PlayerRobot) && !(u instanceof PlayerKnight)) {

				if (u.priority == 3) {
					u.draw(g);
				}

			}
		}
		for (int i = 0; i < Constants.MAP_SIZE_X; i++) {
			for (int j = 0; j < Constants.MAP_SIZE_Y; j++) {
				if (ninjaPlaying && ninjaFocus) {
					if (Engine.isInRange(findNinja(getUnits()).getpX(), findNinja(getUnits()).getpY(), grids_third[i][j].getX(), grids_third[i][j].getY(),
							Constants.LEVEL_LEFT_MOST * 2 + Constants.LEVEL_TOP_MOST * 2)) {
						grids_second[i][j].draw(g);
					}
				}
				if (robotPlaying && robotFocus) {
					if (Engine.isInRange(findRobot(getUnits()).getpX(), findRobot(getUnits()).getpY(), grids_third[i][j].getX(), grids_third[i][j].getY(),
							Constants.LEVEL_LEFT_MOST * 2 + Constants.LEVEL_TOP_MOST * 2)) {
						grids_second[i][j].draw(g);
					}
				}
				if (knightPlaying && knightFocus) {
					if (Engine.isInRange(findKnight(getUnits()).getpX(), findKnight(getUnits()).getpY(), grids_third[i][j].getX(), grids_third[i][j].getY(),
							Constants.LEVEL_LEFT_MOST * 2 + Constants.LEVEL_TOP_MOST * 2)) {
						grids_second[i][j].draw(g);
					}
				}

			}
		}
		for (Iterator<Unit> iter = units.iterator(); iter.hasNext();) {
			Unit u = iter.next();
			if (!(u instanceof PlayerNinja) && !(u instanceof PlayerRobot) && !(u instanceof PlayerKnight)) {

				if (u.priority == 2) {
					u.draw(g);
				}

			}
		}
		for (int i = 0; i < Constants.MAP_SIZE_X; i++) {
			for (int j = 0; j < Constants.MAP_SIZE_Y; j++) {
				if (ninjaPlaying && ninjaFocus) {
					if (Engine.isInRange(findNinja(getUnits()).getpX(), findNinja(getUnits()).getpY(), grids_third[i][j].getX(), grids_third[i][j].getY(),
							Constants.LEVEL_LEFT_MOST * 2 + Constants.LEVEL_TOP_MOST * 2)) {
						grids_first[i][j].draw(g);
					}
				}
				if (robotPlaying && robotFocus) {
					if (Engine.isInRange(findRobot(getUnits()).getpX(), findRobot(getUnits()).getpY(), grids_third[i][j].getX(), grids_third[i][j].getY(),
							Constants.LEVEL_LEFT_MOST * 2 + Constants.LEVEL_TOP_MOST * 2)) {
						grids_first[i][j].draw(g);
					}
				}
				if (knightPlaying && knightFocus) {
					if (Engine.isInRange(findKnight(getUnits()).getpX(), findKnight(getUnits()).getpY(), grids_third[i][j].getX(), grids_third[i][j].getY(),
							Constants.LEVEL_LEFT_MOST * 2 + Constants.LEVEL_TOP_MOST * 2)) {
						grids_first[i][j].draw(g);
					}
				}

			}
		}
		for (Iterator<Unit> iter = units.iterator(); iter.hasNext();) {
			Unit u = iter.next();
			if (!(u instanceof PlayerNinja) && !(u instanceof PlayerRobot) && !(u instanceof PlayerKnight)) {

				if (u.priority == 1) {
					u.draw(g);
				}

			}
		}

		if (ninjaPlaying) {
			PlayerNinja p = findNinja(getUnits());
			if (p != null) {
				if (!started) {
					if (Program.tickCount % 15 == 0) {
						once = !once;
					}
					if (once) {
						p.draw(g);
					}
				} else {
					p.draw(g);
				}
			}
			p = null;
		}
		if (robotPlaying) {
			PlayerRobot p = findRobot(getUnits());
			if (p != null) {
				if (!started) {
					if (Program.tickCount % 15 == 0) {
						once = !once;
					}
					if (once) {
						p.draw(g);
					}
				} else {
					p.draw(g);
				}
			}
			p = null;
		}
		if (knightPlaying) {
			PlayerKnight p = findKnight(getUnits());
			if (p != null) {
				if (!started) {
					if (Program.tickCount % 15 == 0) {
						once = !once;
					}
					if (once) {
						p.draw(g);
					}
				} else {
					p.draw(g);
				}
			}
			p = null;
		}

		Iterator<Animation> iter = getAnimations().iterator();
		while (iter.hasNext()) {
			Animation a = iter.next();
			if (a != null) {
				a.draw(g);
			}
			a = null;
		}
		iter = null;

		Iterator<Particle> iter2 = getParticles().iterator();
		while (iter2.hasNext()) {
			Particle a = iter2.next();
			if (a != null) {
				a.draw(g);
			}
			a = null;
		}
		iter2 = null;

		Iterator<FloatingNumber> iter3 = floatingNumbers.iterator();
		while (iter3.hasNext()) {
			FloatingNumber a = iter3.next();
			if (a != null) {
				a.draw(g);
			}
			a = null;
		}
		iter3 = null;

		getHud().draw(g);

		if (!started) {
			g.setColor(Constants.MAIN_TRANSPARENT_COLOR);
			g.fillRect(0, 0, Constants.SIZE.width, Constants.SIZE.height);

		}
	}

	public PlayerNinja findNinja(ArrayList<Unit> list) {
		for (Iterator<Unit> iter = getUnits().iterator(); iter.hasNext();) {
			Unit u = iter.next();
			if (u instanceof PlayerNinja) {
				return (PlayerNinja) u;
			}
		}
		return null;
	}

	public PlayerRobot findRobot(ArrayList<Unit> list) {
		for (Iterator<Unit> iter = getUnits().iterator(); iter.hasNext();) {
			Unit u = iter.next();
			if (u instanceof PlayerRobot) {
				return (PlayerRobot) u;
			}
		}
		return null;
	}

	public PlayerKnight findKnight(ArrayList<Unit> list) {
		for (Iterator<Unit> iter = getUnits().iterator(); iter.hasNext();) {
			Unit u = iter.next();
			if (u instanceof PlayerKnight) {
				return (PlayerKnight) u;
			}
		}
		return null;
	}

	private void createUnit(int id, int mX, int mY, int status, int priority) {
		if (id == PlayerNinja.CODE)// 1
		{
			getUnits().add(new PlayerNinja(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, PlayerNinja.WIDTH, PlayerNinja.HEIGHT, status, priority));
		}
		if (id == Gate.CODE)// 2
		{
			getUnits().add(new Gate(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, status, priority));
		}
		if (id == Flag.CODE1 || id == Flag.CODE2 || id == Flag.CODE3 || id == Flag.CODE4)// 3-4-5-6
		{

			if (id == Flag.CODE1) {
				blueFlagPicked = false;
			}
			if (id == Flag.CODE2) {
				greenFlagPicked = false;
			}
			if (id == Flag.CODE3) {
				redFlagPicked = false;
			}
			if (id == Flag.CODE4) {
				yellowFlagPicked = false;
			}
			getUnits().add(new Flag(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, id, status, priority));

		}
		if (id == Coin.CODE1 || id == Coin.CODE2 || id == Coin.CODE3)// 7-8-9
		{
			getUnits().add(new Coin(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, id, status, priority));
		}
		if (id == Key.CODE1 || id == Key.CODE2 || id == Key.CODE3 || id == Key.CODE4)// 10-11-12-13
		{
			getUnits().add(new Key(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, id, status, priority));
		}
		if (id == Lock.CODE1 || id == Lock.CODE2 || id == Lock.CODE3 || id == Lock.CODE4)// 14-15-16-17
		{
			getUnits().add(new Lock(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, id, mX, mY, status, priority));
		}
		if (id == Spring.CODE1 || id == Spring.CODE2 || id == Spring.CODE3 || id == Spring.CODE4)// 18-19-20-21
		{
			getUnits().add(new Spring(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, id, status, priority));
		}
		if (id == Spike.CODE1 || id == Spike.CODE2 || id == Spike.CODE3 || id == Spike.CODE4)// 22-23-24-25
		{
			getUnits().add(new Spike(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, id, mX, mY, status, priority));
		}
		if (id == LaserMachine.CODE1 || id == LaserMachine.CODE2 || id == LaserMachine.CODE3 || id == LaserMachine.CODE4)// 26-27-28-29
		{
			getUnits().add(new LaserMachine(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, id, mX, mY, status, priority));
		}
		if (id == Box.CODE1 || id == Box.CODE2 || id == Box.CODE3)// 30-31-32
		{
			getUnits().add(new Box(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, id, mX, mY, status, priority));
		}
		if (id == Acid.CODE1 || id == Acid.CODE2 || id == Acid.CODE3 || id == Acid.CODE4 || id == Acid.CODE5 || id == Acid.CODE6) {
			getUnits().add(new Acid(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, id, status, priority));
		}
		if (id == Node.CODE1 || id == Node.CODE2) {
			getUnits().add(new Node(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, id, mX, mY, status, priority));
		}
		if (id == Door.CODE1 || id == Door.CODE2 || id == Door.CODE3 || id == Door.CODE4) {
			if (id == Door.CODE4) {
				for (Map.Entry<GridCell, Integer> entry : doorEntranceIDMAP.entrySet()) {
					GridCell cell = entry.getKey();
					int value = entry.getValue();

					if (cell.getX() == mX && cell.getY() == mY && cell.getWidth() == status) {
						getUnits().add(new Door(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, id, mX, mY, cell.getWidth(),
								cell.getHeight(), value, priority));
						break;
					}
				}
			}
			if (id == Door.CODE2) {
				for (GridCell cell : doorExitIDMAP) {
					if (cell.getX() == mX && cell.getY() == mY && cell.getWidth() == status) {
						getUnits().add(new Door(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, id, mX, mY, cell.getWidth(),
								cell.getHeight(), -1, priority));
						break;
					}
				}
			}
			if (id == Door.CODE1 || id == Door.CODE3) {
				getUnits().add(new Door(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, id, mX, mY, status, priority, -1, -1));
			}

		}
		if (id == Fire.CODE) {
			getUnits().add(new Fire(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, status, priority));
		}
		if (id == Volcano.CODE) {
			getUnits().add(new Volcano(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, mX, mY, status, priority));
		}
		if (id == KunaiPack.CODE) {
			getUnits().add(new KunaiPack(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, status, priority));
		}

		if (id == Slime.CODE) {
			getUnits().add(new Slime(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, status, priority));
		}
		if (id == Snail.CODE) {
			getUnits().add(new Snail(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, status, priority));
		}
		if (id == Ladder.CODE) {
			getUnits().add(new Ladder(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, status, priority));
		}
		if (id == Heart.CODE) {
			getUnits()
					.add(new com.aspire.goldenapple.game.unit.Heart(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, status, priority));
		}
		if (id == Fly.CODE) {
			getUnits().add(new Fly(program, this, (mX * Constants.BRICK_SIZE) + ((Constants.BRICK_SIZE - Fly.WIDTH) / 2), (mY * Constants.BRICK_SIZE) + ((Constants.BRICK_SIZE - Fly.HEIGHT) / 2),
					Constants.BRICK_SIZE, Constants.BRICK_SIZE, status, priority));
		}
		if (id == Spinner.CODE) {
			getUnits().add(new Spinner(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, status, priority));
		}
		if (id == Ghost.CODE) {
			getUnits().add(new Ghost(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, status, priority));
		}
		if (id == PlayerRobot.CODE) {
			getUnits().add(new PlayerRobot(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, status, priority));
		}
		if (id == PlayerKnight.CODE) {
			getUnits().add(new PlayerKnight(program, this, mX * Constants.BRICK_SIZE, mY * Constants.BRICK_SIZE, Constants.BRICK_SIZE, Constants.BRICK_SIZE, status, priority));
		}
	}

	public void handlePanelPosition() {
		if (ninjaPlaying && ninjaFocus) {
			PlayerNinja ninja = findNinja(getUnits());
			setX(Engine.panelLocationXFounder(ninja.x, ninja.y));
			setY(Engine.panelLocationYFounder(ninja.x, ninja.y));
			setInvertX(-getX());
			setInvertY(-getY());
		}
		if (robotPlaying && robotFocus) {
			PlayerRobot robot = findRobot(getUnits());
			setX(Engine.panelLocationXFounder(robot.x, robot.y));
			setY(Engine.panelLocationYFounder(robot.x, robot.y));
			setInvertX(-getX());
			setInvertY(-getY());
		}
		if (knightPlaying && knightFocus) {
			PlayerKnight knight = findKnight(getUnits());
			setX(Engine.panelLocationXFounder(knight.x, knight.y));
			setY(Engine.panelLocationYFounder(knight.x, knight.y));
			setInvertX(-getX());
			setInvertY(-getY());
		}
	}

	public void setGameGrids(Data d) {
		doorEntranceIDMAP = d.getDoorEntranceIDMAP();
		doorExitIDMAP = d.getDoorExitIDMAP();
		level.setBG_ID(d.getBg_id());
		level.setLEVEL_NAME(d.getName());

		for (int i = 0; i < Constants.MAP_SIZE_X; i++) {
			for (int j = 0; j < Constants.MAP_SIZE_Y; j++) {
				grids_first[i][j].setData(d.getGrid_1()[i][j]);
				grids_second[i][j].setData(d.getGrid_2()[i][j]);
				grids_third[i][j].setData(d.getGrid_3()[i][j]);

				getCollision_matrix()[i][j] = d.getGrid_2()[i][j].getCOL_ID();

				if (d.getGrid_1()[i][j].getUNIT_ID() != 0) {
					createUnit(d.getGrid_1()[i][j].getUNIT_ID(), i, j, 0, 1);
				}
				if (d.getGrid_2()[i][j].getUNIT_ID() != 0) {
					createUnit(d.getGrid_2()[i][j].getUNIT_ID(), i, j, 0, 2);
				}
				if (d.getGrid_3()[i][j].getUNIT_ID() != 0) {
					createUnit(d.getGrid_3()[i][j].getUNIT_ID(), i, j, 0, 3);
				}

			}
		}
		for (int i = 0; i < Constants.SCREEN_X; i++) {
			for (int j = 0; j < Constants.SCREEN_Y; j++) {
				grids_parallel_first[i + 5][j].setData(d.getGrid_parallel_1()[i][j]);
				grids_parallel_second[i + 5][j].setData(d.getGrid_parallel_2()[i][j]);
				grids_parallel_third[i + 5][j].setData(d.getGrid_parallel_3()[i][j]);

				getCollision_parallel_matrix()[0][i + 5][j] = d.getGrid_parallel_1()[i][j].getCOL_ID();
				getCollision_parallel_matrix()[1][i + 5][j] = d.getGrid_parallel_2()[i][j].getCOL_ID();
				getCollision_parallel_matrix()[2][i + 5][j] = d.getGrid_parallel_3()[i][j].getCOL_ID();

				if (d.getGrid_parallel_1()[i][j].getUNIT_ID() != 0) {
					createUnit(d.getGrid_parallel_1()[i][j].getUNIT_ID(), i + 5, j, 1, 0);
				}
				if (d.getGrid_parallel_2()[i][j].getUNIT_ID() != 0) {
					createUnit(d.getGrid_parallel_2()[i][j].getUNIT_ID(), i + 5, j, 2, 0);
				}
				if (d.getGrid_parallel_3()[i][j].getUNIT_ID() != 0) {
					createUnit(d.getGrid_parallel_3()[i][j].getUNIT_ID(), i + 5, j, 3, 0);
				}

			}
		}
		hud.handleFlags();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < Constants.SCREEN_Y; j++) {
				CellData data = new CellData(400, 0, 1, 0);
				grids_parallel_first[i][j].setData(data);
				grids_parallel_second[i][j].setData(data);
				grids_parallel_third[i][j].setData(data);
				getCollision_parallel_matrix()[0][i][j] = data.getCOL_ID();
				getCollision_parallel_matrix()[1][i][j] = data.getCOL_ID();
				getCollision_parallel_matrix()[2][i][j] = data.getCOL_ID();
			}
		}
		for (int i = Constants.SCREEN_X + 5; i < Constants.SCREEN_X + 10; i++) {
			for (int j = 0; j < Constants.SCREEN_Y; j++) {
				CellData data = new CellData(400, 0, 1, 0);
				grids_parallel_first[i][j].setData(data);
				grids_parallel_second[i][j].setData(data);
				grids_parallel_third[i][j].setData(data);
				getCollision_parallel_matrix()[0][i][j] = data.getCOL_ID();
				getCollision_parallel_matrix()[1][i][j] = data.getCOL_ID();
				getCollision_parallel_matrix()[2][i][j] = data.getCOL_ID();
			}
		}

		for (int i = 0; i < Constants.SCREEN_X + 10; i++) {
			for (int j = 0; j < Constants.SCREEN_Y; j++) {
				grids_parallel_first[i][j].setX(grids_parallel_first[i][j].getX() - Constants.BRICK_SIZE * 5 / 2);
				grids_parallel_second[i][j].setX(grids_parallel_second[i][j].getX() - Constants.BRICK_SIZE * 5 / 2);
				grids_parallel_third[i][j].setX(grids_parallel_third[i][j].getX() - Constants.BRICK_SIZE * 5 / 2);
			}
		}

	}

	public Door findTargetDoor(int id) {
		for (Iterator<Unit> iter = getUnits().iterator(); iter.hasNext();) {
			Unit u = iter.next();
			if (u instanceof Door) {
				Door d = (Door) u;
				if (d.getId() == id && d.getType() == Door.CODE2) {
					return d;
				}
			}
		}
		return null;
	}

	public void finish() {
		if (getLevel().isTestLevel()) {
			getLevel().setExit(true);
		} else {
			setWon(true);
			getLevel().getMp().stop();
			PlayerState.incRate = PlayerState.scoreGathered / 70;
			MediaPlayer.playSound(Constants.point_counter);
		}
	}

	public void addScore(int scoreToAdd, int x, int y) {
		PlayerState.currentScore += scoreToAdd;
		PlayerState.scoreGathered += scoreToAdd;
		floatingNumbers.add(new FloatingNumber(this, x, y, scoreToAdd));
	}

	public int getInvertX() {
		return invertX;
	}

	public void setInvertX(int invertX) {
		this.invertX = invertX;
	}

	public int getInvertY() {
		return invertY;
	}

	public void setInvertY(int invertY) {
		this.invertY = invertY;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public boolean isLost() {
		return lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}

	public Hud getHud() {
		return hud;
	}

	public void setHud(Hud hud) {
		this.hud = hud;
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}

	public int[][] getCollision_matrix() {
		return collision_matrix;
	}

	public void setCollision_matrix(int collision_matrix[][]) {
		this.collision_matrix = collision_matrix;
	}

	public int[][][] getCollision_parallel_matrix() {
		return collision_parallel_matrix;
	}

	public void setCollision_parallel_matrix(int collision_parallel_matrix[][][]) {
		this.collision_parallel_matrix = collision_parallel_matrix;
	}

	public ArrayList<Particle> getParticles() {
		return particles;
	}

	public void setParticles(ArrayList<Particle> particles) {
		this.particles = particles;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLastX() {
		return lastX;
	}

	public void setLastX(int lastX) {
		this.lastX = lastX;
	}

	public int getLastY() {
		return lastY;
	}

	public void setLastY(int lastY) {
		this.lastY = lastY;
	}

	public ArrayList<Animation> getAnimations() {
		return animations;
	}

	public void setAnimations(ArrayList<Animation> animations) {
		this.animations = animations;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}
