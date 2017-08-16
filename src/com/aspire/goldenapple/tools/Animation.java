package com.aspire.goldenapple.tools;

import java.awt.image.BufferedImage;
import com.aspire.goldenapple.game.GamePanel;

public class Animation {
	
	public BufferedImage[]		frames;
	public boolean				shouldAnimate;
	public int					x, y, width, height;
	public int					currentX, currentY;
	public int					interval;
	public int					frameCount;
	public int					currentFrame;
	public boolean				loop;
	public GamePanel			gamePanel;
	
	private int ticks;
	private int animTicks;

	/**
	 * @param frames
	 *            Animasyonun kareleri. BufferedImage cinsinden bir array
	 * @param x
	 *            Animasyonun lokasyonu
	 * @param y
	 *            Animasyonun lokasyonu
	 * @param width
	 *            Animasyonun ebatý
	 * @param height
	 *            Animasyonun ebatý
	 * @param duration
	 *            Ne kadar sure ekranda gozukecek Milisaniye cinsinden (1000 MS
	 *            = 1S)
	 * @param loop
	 *            Kendini tekrar etmelimi yoksa bitince yok mu olmalý
	 */
	public Animation(GamePanel gamePanel, BufferedImage[] frames, int x, int y, int width, int height, int duration, boolean loop) {
		this.gamePanel = gamePanel;
		this.frames = frames;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.loop = loop;
		currentX = x;
		currentY = y;
		shouldAnimate = true;
		animTicks = ticks;
		frameCount = frames.length;
		interval = duration / frameCount;
		currentFrame = 0;
		ticks = 0;
		animTicks = 0;
	}

	public void update() {
		if (shouldAnimate) {
			ticks++;
			if (ticks - animTicks >= interval) {
				animTicks = ticks;
				currentFrame++;
				if (currentFrame >= frameCount) {
					if (loop) {
						currentFrame = 0;
					} else {
						shouldAnimate = false;
					}
				}
			}
		}
	}

	public void draw(java.awt.Graphics g) {
		if (shouldAnimate) {
			g.drawImage(frames[currentFrame], gamePanel.getInvertX() + currentX, gamePanel.getInvertY() + currentY, width, height, null);
		}
	}

}
