package com.wavegame.main;
import java.awt.*;
import javax.swing.JFrame;


public class Window  extends Canvas{

	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1515414040714166227L;
	static JFrame frame;

public Window(int width,int height, String title,Game game) {
	frame=new JFrame(title);
	frame.setPreferredSize(new Dimension(width,height));
	frame.setMaximumSize(new Dimension(width,height));
	frame.setMinimumSize(new Dimension(width,height));
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.add(game);
	frame.setVisible(true);
	game.start();
	}
public static void disposable() {
	frame.dispose();
	
}
}
