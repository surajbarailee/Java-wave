package com.wavegame.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import com.wavegame.main.Game.STATE;

public class Menu extends MouseAdapter{
	private Game game;
	private Handler handler;
	private Random r= new Random();
	private HUD hud;
	public static int counter1=0;
	public static int counter2=0;
	static ArrayList arr=new ArrayList();
	int i =0;
	public String[] arrayvalue;
	int ychange=150;
	
	
	
	public Menu(Game game,Handler handler,HUD hud) {
		this.game=game;
		this.handler=handler;
		this.hud=hud;
	
	
		
	}
	public void mousePressed(MouseEvent e) {
		int mx= e.getX();
		int my = e.getY();

		if(game.gameState==STATE.Menu)
		{
		
			if(mouseOver(mx,my,210,150,200,64)) {
			
				game.gameState=STATE.Game;
				handler.addObject(new Player (Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));
				handler.clearEnemy();
				//handler.addObject(new Player (Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
			
			}
			
			if(mouseOver(mx,my,210,350,200,64)) {
				System.exit(1);
			}
			
			
			if(mouseOver(mx,my,210,250,200,64)) 
			{
				game.gameState=STATE.help;
				
			}
			if(mouseOver(mx,my,Game.WIDTH-90,Game.HEIGHT-70,80,30))
			{
				
				game.gameState=STATE.highscore ;
				
				
			}
		}
		//back button
		else if(game.gameState==STATE.help)
		{
			if(mouseOver(mx,my,210,350,200,64)) 
			{
				
				game.gameState=STATE.Menu;
				return;
				
			}
			
		}
		//try again
		
		if(game.gameState==STATE.end)
		{
			if(mouseOver(mx,my,220, 280, 200, 64)) 
			{
				game.gameState=STATE.Game;
				hud.setscore(0);
				hud.setlevel(1);
				
				handler.addObject(new Player (Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));
				handler.clearEnemy();
				//handler.addObject(new Player (Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
			}
			if(mouseOver(mx,my,230, 360, 200, 64)) 
			{
				game.gameState=STATE.Menu;
				
				
			}
			
		}
		
		if(game.gameState==STATE.highscore)
		{
			
			if(mouseOver(mx,my,240, 390, 150, 50))
			{
				
				game.gameState=STATE.Menu ;
				
				
			}
		}
		
		
		
		
	}
	public void mouseReleased(MouseEvent e) {
		
		
	}
	private boolean mouseOver(int mx, int my, int x, int y,int width,int height) {
		if(mx>x && mx< x+width) {
			if(my>y && my < y+height) {
				return true;
			}
			else
				return false;
			
		}
		else 
			return false;
	}
	public void tick() {
		
	}
	public void render(Graphics g) 
	{
	
				if(game.gameState==STATE.Menu) 
					{
							Font fnt =new Font("arial",1,50);
							Font fnt1 =new Font("arial",1,30);
							Font fnt2 =new Font("arial",1,15);
							g.setFont(fnt);
							g.setColor(Color.white);
							g.drawString("Menu", 240, 100);
							
							g.setFont(fnt1);
							
							g.drawRect(210, 150, 200, 64);
							g.drawString("Play", 270, 190);
							
							
							g.drawRect(210, 250, 200, 64);
							g.drawString("Help", 270, 290);
							
							
							g.drawRect(210, 350, 200, 64);
							g.drawString("Quit", 270, 390);
							g.setFont(fnt2);
							g.setColor(Color.white);
							
							g.drawRect(Game.WIDTH-90,Game.HEIGHT-70,80,30);
							g.drawString("HighScore", Game.WIDTH-85,Game.HEIGHT-50);
					}
				if(game.gameState == STATE.help)
					{
							Font helpfnt =new Font("arial",1,50);
							Font backfnt =new Font("arial",1,30);
							Font messagefnt =new Font("arial",1,10);
			
							g.setFont(helpfnt);
							g.setColor(Color.white);
							g.drawString("Help",250,50);
							
							g.setFont(backfnt);
							g.drawRect(210, 350, 200, 64);
							g.drawString("Back", 270, 390);
							
							g.setFont(messagefnt);
							g.drawRect(210, 350, 200, 64);
							g.drawString("Use arrow keys to move the player", 10, 50);
							g.drawString("Use arrow keys to move the player", 10, 70);
							
					}
				 if(game.gameState == STATE.end )
					{
					
							Font helpfnt =new Font("arial",1,50);
							Font backfnt =new Font("arial",1,30);
							Font messagefnt =new Font("arial",1,10);
	
							g.setFont(helpfnt);
							g.setColor(Color.white);
							g.drawString("Game Over",210,50);
							
							
							g.setFont(backfnt);
							g.drawRect(230, 280, 200, 64);
							g.drawString("You Lost With a score of :"+ hud.getScore() , 150, 200);
							g.drawString("Try Again", 240, 330);
							
							
							g.drawRect(230, 360, 200, 64);
							g.drawString("Back", 270, 400);
							
							if(counter1==0) 
							{
								System.out.println("USerInput Created");
								UserInput ui = new UserInput();
								ui.UserInputfunction(hud.getScore());
								counter1++;
							}
							
					}
				 if(game.gameState == STATE.highscore )
					{
					 ychange=100;
					
					 Font messagefnt =new Font("arial",1,20);
					 g.setFont(messagefnt);
						g.setColor(Color.white);
						g.drawString("Top 10 Players",100, 50);
						g.drawString("Score",500, 50);
						
						Connection con=null;
								try
								{  
									
									String scorevalue;
									 Font tablefont =new Font("arial",1,15);
									 g.setFont(tablefont);
									 g.setColor(Color.white);
									
									Class.forName("com.mysql.jdbc.Driver");  
									con=DriverManager.getConnection(  
									"jdbc:mysql://localhost/scoredatabase?useLegacyDatetimeCode=false&serverTimezone=UTC","root","");  
									Statement stmt=con.createStatement();  
									ResultSet rs=stmt.executeQuery("select * from scoretable order by Score DESC LIMIT 10"); 
									System.out.println("lodddd");
									while(rs.next()) 
									{ 
											
										
											g.drawString(rs.getString("PlayerName"),100 , ychange);
											
											scorevalue=String.valueOf(rs.getString("Score"));
											g.drawString(scorevalue,500 ,ychange);
											ychange=ychange+30;
										
									
				
									}
								}
								catch(Exception e)
									{
										e.printStackTrace();
									}
								finally {
									try {
										con.close();
									}
									catch(Exception e1)
									{
										e1.printStackTrace();
										System.out.println("abcd");
										
									}
								}
								Font backfnt =new Font("arial",1,20);
								g.setFont(backfnt);
								g.drawRect(240, 390, 150, 50);
								g.drawString("Back", 290, 420);
								
								
							
							
								
					}
							
					
		}
}


