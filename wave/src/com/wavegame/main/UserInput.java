package com.wavegame.main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class UserInput extends JFrame {
	
	JButton jb;
	JLabel jl;
	JTextField jtf;
	JFrame jf;
	
	
	
	public void UserInputfunction(int Score) 
	{
		
		JFrame jf=new JFrame();
		JButton jb=new JButton("OK");
		JLabel jl=new JLabel("Please Enter Your Name");
		JTextField jtf=new JTextField(20);
		jf.add(jl);
		jf.add(jtf);
		jf.add(jb);
		jb.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				DatabaseConnection db =new DatabaseConnection();
				String UserName=jtf.getText();
				db.insertData(UserName,Score);
				jf.dispose();
				
			}
	
	
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new FlowLayout());
		jf.setSize(300,150);
		jf.setVisible(true);
		setLocationRelativeTo(null);
		
		
		
	
		
	}
}
