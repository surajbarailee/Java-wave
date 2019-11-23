package com.wavegame.main;

import java.awt.Color;
import java.awt.Graphics;


public class HUD {
	public static float HEALTH =100;
	private float greenValue=255f;
	private int level=1;
	private int score=0;
	public void tick() {
		//HEALTH=HEALTH-1;
		HEALTH=Game.clamp(HEALTH,0,100);
		greenValue=Game.clamp(greenValue,0,255);
		greenValue=HEALTH;
		score++;
		
		
	}
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(150,(int)greenValue,0));
		g.fillRect((int)15,(int) 15, (int)HEALTH*2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		g.drawString("Score: " + score, 15,60);
		g.drawString("Level: " + level, 15,70);
	}

	public void setscore(int score)
	{
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	public int getlevel() {
		return level;
	}
	public void setlevel(int level) {
		this.level = level;
		
	}
}
