package com.wavegame.main;

import java.util.Random;

	public class Spawn 
	{
		private Handler handler;
		private HUD hud;
		private int scorekeep=0;
		private Random r= new Random();
		
		public Spawn(Handler handler,HUD hud) {
			this.handler=handler;
			this.hud=hud;
	}
	public void tick()
	{
		
		scorekeep++;
		if(scorekeep>=100)
		{
			scorekeep=0;
			if(hud.getlevel()<=5) {
			hud.setlevel(hud.getlevel() + 1);
			}
			if (hud.getlevel()==2)
			{
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
			}
			else if(hud.getlevel()==3)
			{
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
				
			}
			else if(hud.getlevel()==4)
			{
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.FastEnemy,handler));

			}
			else if(hud.getlevel()==5)
			{
				handler.clearEnemy();
				handler.addObject(new BossEnemy((Game.WIDTH)/2-50,-100,ID.BossEnemy,handler));

			}
				
		}
	}
}
