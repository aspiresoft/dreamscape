package com.aspire.goldenapple.game;

import java.awt.Graphics;
import com.aspire.goldenapple.Constants;
import com.aspire.goldenapple.io.CellData;

public class Brick {
	private int x, y, width, height;
	private GamePanel gamePanel;
	private CellData data;
	private boolean visible;

	public Brick(GamePanel gamePanel, CellData data, int x, int y, int width, int height) {
		this.gamePanel = gamePanel;
		this.data = data;
		this.x = x;
		this.y = y;
		this.setWidth(width);
		this.setHeight(height);
		setVisible(false);
	}

	public Brick(GamePanel gamePanel, int x, int y, int width, int height) {
		this.gamePanel = gamePanel;
		this.x = x;
		this.y = y;
		this.setWidth(width);
		this.setHeight(height);
		setVisible(false);
		data = new CellData();
	}

	public void update() {

	}

	public void draw(Graphics g) {
		if (data.getTILE_ID() != 0) {
			g.drawImage(Constants.IMAGES[Constants.TILE_ID_TO_IMAGE_ID(data.getTILE_ID())], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, getWidth(), getHeight(), null);

		}
		if (data.getOBJ_ID() != 0) {
			g.drawImage(Constants.IMAGES[Constants.OBJ_ID_TO_IMAGE_ID(data.getOBJ_ID())], gamePanel.getInvertX() + x, gamePanel.getInvertY() + y, getWidth(), getHeight(), null);

		}
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

	public CellData getData() {
		return data;
	}

	public void setData(CellData data) {
		this.data = data;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
