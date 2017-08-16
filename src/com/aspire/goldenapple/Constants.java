package com.aspire.goldenapple;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Constants {
	// GAMEPLAY CONSTANTS
	// ================================================================
	public static final int BRICK_SIZE = 32;
	public static final String MAPPACK_LOCATION = "MapPacks/";
	public static final String LEVEL_LOCATION = "Levels/";
	public static final String MAPPACK_EXTENSION = "mpk";
	public static final String LEVEL_EXTENSION = "lvl";
	public static final String CONFIG = "config.txt";

	// GAME SIZES AND SCALES
	// ================================================================
	public static final int WIDTH = 200;
	public static final int HEIGHT = 160;
	public static final int SCALE = 4;
	public static final int EXTRA_X = 6;
	public static final int EXTRA_Y = 29;
	public static final Dimension SIZE = new Dimension(WIDTH * SCALE + EXTRA_X, HEIGHT * SCALE + EXTRA_Y);
	public static final int MAP_SIZE_X = 200;
	public static final int MAP_SIZE_Y = 200;
	public static final int SCREEN_X = 20;
	public static final int SCREEN_Y = 20;
	public static final int LEFT_SAFE = (WIDTH * SCALE / BRICK_SIZE) / 2 * BRICK_SIZE + BRICK_SIZE;
	public static final int TOP_SAFE = (HEIGHT * SCALE / BRICK_SIZE) / 2 * BRICK_SIZE;
	public static final int RIGHT_SAFE = (MAP_SIZE_X * BRICK_SIZE) - LEFT_SAFE;
	public static final int BOT_SAFE = (MAP_SIZE_Y * BRICK_SIZE) - TOP_SAFE;
	public static final int LEFT_MOST = 0;
	public static final int TOP_MOST = 0;
	public static final int RIGHT_MOST = (MAP_SIZE_X * BRICK_SIZE);
	public static final int BOT_MOST = (MAP_SIZE_Y * BRICK_SIZE);
	public static final int RIGHT_MOST_PARALLEL = SCREEN_X * BRICK_SIZE;
	public static final int BOT_MOST_PARALLEL = SCREEN_Y * BRICK_SIZE;
	public static final String TITLE = "Ninja Girl";
	public static final float TICK_PER_SEC = 60.0f;
	public static final float FRAME_PER_SEC = 60.0f;
	public static final int LEVEL_LEFT_MOST = (Constants.SIZE.width / Constants.BRICK_SIZE) / 2 * Constants.BRICK_SIZE;
	public static final int LEVEL_TOP_MOST = (Constants.SIZE.height / Constants.BRICK_SIZE) / 2 * Constants.BRICK_SIZE;
	public static final int LEVEL_RIGHT_MOST = (Constants.MAP_SIZE_X - 1) * Constants.BRICK_SIZE - LEVEL_LEFT_MOST;
	public static final int LEVEL_BOTTOM_MOST = (Constants.MAP_SIZE_Y - 1) * Constants.BRICK_SIZE - LEVEL_TOP_MOST;
	public static final int SPACE = 20;
	public static final int GRIDS_LENGHT = 500;
	public static final int TEXTURES_LENGHT = 240;
	public static final int GRID_SIZE = 24;

	public static Random R = new Random();
	// COLORS
	// ================================================================

	public static Color MAP_TEXT_COLOR = Color.decode("#ffffff");
	public static Color MAIN_COLOR = Color.decode("#4445FD");
	public static Color SELECTED_COLOR = Color.decode("#DC143C");
	public static Color STEEL_BLUE_COLOR = Color.decode("#4682B4");
	public static Color STEEL_GREEN_COLOR = Color.decode("#78AB46");

	public static Color MAIN_TRANSPARENT_COLOR = new Color(STEEL_BLUE_COLOR.getRed(), STEEL_BLUE_COLOR.getGreen(), STEEL_BLUE_COLOR.getBlue(), 40);
	public static Color MAIN_TRANSPARENT_SELECTED_COLOR = new Color(SELECTED_COLOR.getRed(), SELECTED_COLOR.getGreen(), SELECTED_COLOR.getBlue(), 40);
	public static Color GUI_HIGHLIGHT_COLOR = MAIN_TRANSPARENT_COLOR;
	public static Color GREEN_TRANSPARENT_COLOR = new Color(STEEL_GREEN_COLOR.getRed(), STEEL_GREEN_COLOR.getGreen(), STEEL_GREEN_COLOR.getBlue(), 40);
	// IMAGES
	// ================================================================
	public static final int UNIT_COUNT = 56, TILE_COUNT = 81, OBJECT_COUNT = 99, COLLISION_COUNT = 2;
	public static BufferedImage BG, CUSTOM_CURSOR;
	public static BufferedImage[] IMAGES = new BufferedImage[750];

	// FONTS
	// ================================================================
	public static Font BIG_FONT = new Font("Verdana", Font.PLAIN, 55);
	public static Font NORMAL_FONT = new Font("Verdana", Font.PLAIN, 15);
	public static Font MENU_FONT_BIG = new Font("Verdana", Font.PLAIN, 40);
	public static Font MENU_FONT_SMALL = new Font("Verdana", Font.PLAIN, 30);
	public static Font MENU_FONT_HUGE = new Font("Verdana", Font.PLAIN, 20);
	public static Font MENU_FONT_SMALLER = NORMAL_FONT;
	public static Font MENU_FONT_SMALLEST = new Font("Verdana", Font.PLAIN, 10);
	public static Font MENU_FONT_MOST_SMALLEST = new Font("Arial", Font.PLAIN, 9);
	// SoundsS
	// ================================================================
	public static final String coin_picked = "Resources/Sounds/coin1.wav";
	public static final String slime_dead = "Resources/Sounds/slime_dead_3.wav";
	public static final String snail_dead = "Resources/Sounds/snail.wav";
	public static final String laser = "Resources/Sounds/laser.wav";
	public static final String background_music_01 = "Resources/Sounds/In the sky again.wav";
	public static final String fireball_sound_1 = "Resources/Sounds/lava01.wav";
	public static final String fireball_sound_2 = "Resources/Sounds/lava02.wav";
	public static final String lock_open = "Resources/Sounds/switch32.wav";
	public static final String laser_explode = "Resources/Sounds/explode01.wav";
	public static final String machine_hit = "Resources/Sounds/machine_hit.wav";
	public static final String box_break = "Resources/Sounds/wood.wav";
	public static final String brick_break = "Resources/Sounds/coin5.wav";
	public static final String player_hurt = "Resources/Sounds/Fem1_Hurt3.wav";
	public static final String flag_taken = "Resources/Sounds/cloth.wav";
	public static final String point_counter = "Resources/Sounds/counter01.wav";
	public static final String kunai_throw = "Resources/Sounds/kunai.wav";
	public static final String kunai_pickup = "Resources/Sounds/kunai_pickup.wav";
	public static final String fly_dead = "Resources/Sounds/fly.wav";

	public static int TILE_ID_TO_IMAGE_ID(int id) {
		if (id <= 0) {
			return 0;
		}
		if (id == 400) {
			return 400;
		}
		if (id >= 64) {
			return 709 + id - 64;
		}
		return 165 + id;
	}

	public static int COL_ID_TO_IMAGE_ID(int id) {
		if (id <= 0) {
			return 0;
		}
		return 153 + id;
	}

	public static int UNIT_ID_TO_IMAGE_ID(int id) {
		if (id <= 0) {
			return 0;
		}
		return (id - 1);
	}

	public static int OBJ_ID_TO_IMAGE_ID(int id) {
		if (id <= 0) {
			return 0;
		}
		if (id == 91) {
			return 701;
		}
		if (id == 92) {
			return 702;
		}
		if (id == 93) {
			return 703;
		}
		if (id == 94) {
			return 704;
		}
		if (id == 95) {
			return 705;
		}
		if (id == 96) {
			return 706;
		}

		if (id == 97) {
			return 707;
		}
		if (id == 98) {
			return 708;
		}
		return 242 + id;
	}
}
