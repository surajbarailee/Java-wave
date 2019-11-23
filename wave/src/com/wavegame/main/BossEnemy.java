package com.wavegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject {
	private Handler handler;
	private int timer=50;
	private int timer2 =50;
	Random r= new Random();
	public BossEnemy(int x,int y,ID id,Handler handler)
	{
		super(x,y,id);
		this.handler=handler;
		velX=0;
		velY=2;
		
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,80,80);
		
	}
	public void tick() {
		x+=velX;
		y+=velY;
		
		
		if(timer<=0)velY=0;
		else timer--;
		if(timer<=0) timer2--;
		if(timer2<=0)
		{
			if(velX==0)velX=2;
			int spawn=r.nextInt(10);
			if(spawn ==0)
				handler.addObject(new BossBullet((int)x+40,(int)y+40,ID.BasicEnemy,handler));
		}
		if(x<=0 ||x>=Game.WIDTH -80)
		{	
			velX*=-1;
		}
		/**
		if(y<=0 ||y>=Game.HEIGHT-32)
		{
			velY*=-1;
		}
		*/
		
		handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.red,80,80,0.10f,handler));
		
	}
	public void render(Graphics g) 
	{
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 80, 80);
	}

}
