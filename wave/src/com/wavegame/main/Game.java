package com.wavegame.main;

//import java.awt.Canvas;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4945087843276566772L;
	
	public static final int WIDTH=640,HEIGHT =WIDTH/12*9;
	
	private Thread thread;
	
	private boolean running=false;
	
	private Handler handler;
	
	private Random r;
	private HUD hud ;
	private Spawn Spawner;
	private Menu menu;
	public enum STATE{
		highscore,end,Menu,Game,help
	};
	
	public static STATE gameState=STATE.Menu;
	
	
	public Game()
	{
		handler=new Handler();
		hud=new HUD();
		menu=new Menu(this,handler,hud);
		this.addKeyListener(new keyInput(handler));
		this.addMouseListener(menu);
		new Window(WIDTH,HEIGHT,"Wave Run",this);
		
	
		r= new Random();
		
		
		
		Spawner =new Spawn(handler,hud);
		
		if(gameState ==STATE.Game) 
		{
			handler.addObject(new Player (WIDTH/2-32,HEIGHT/2-32,ID.Player,handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
		}
		else
		{
			for(int i=0;i<5;i++) 
			{
				handler.addObject(new MenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.MenuParticle,handler));
			}
		}
		
	}
	
	
	public synchronized void start() {
		thread=new Thread(this);
		thread.start();
		running=true;
		
	}
	public synchronized void stop() {
		try {
			thread.join();;
			running=false;
		}catch(Exception e){
			e.printStackTrace();
		}
		thread=new Thread(this);
		thread.start();
		
	}
	
	public void run() {
		this.requestFocus();
		long lastTime=System.nanoTime();
		double amountOfTicks=60.0;
		double ns=1000000000 / amountOfTicks;
		double delta = 0;
		long timer=System.currentTimeMillis();
		int frames=0;
		while(running) {
			long now=System.nanoTime();
			delta+=(now - lastTime)/ns;
			lastTime=now;
			while(delta >=1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			if(System.currentTimeMillis()-timer>1000) {
				timer+=1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		if(gameState ==STATE.Game)
		{
				hud.tick();
				Spawner.tick();
				if (hud.HEALTH<=0){
					hud.HEALTH=100;
					gameState=STATE.end;
					handler.clearEnemy();
					for(int i=0;i<5;i++) 
					{
						handler.addObject(new MenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.MenuParticle,handler));
					}
					
					
				}
		}
		else if(gameState == STATE.Menu || gameState==STATE.end || gameState==STATE.highscore) {
			menu.tick();
		}
	}
	private void render() {
		BufferStrategy bs=this.getBufferStrategy();
		if(bs== null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g=bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		if(gameState ==STATE.Game)
		{
		hud.render(g);
		}
		else if(gameState == STATE.Menu|| gameState == STATE.help || gameState==STATE.end || gameState == STATE.highscore) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
		
	}
	public static float clamp(float var,float min,float max)
	{
		if(var>=max)
			return var=max;
		else if(var<=min)
			return var=min;
		else return var;
	}
	public static void main (String args[])
	{
		new Game();
		
	}

}
