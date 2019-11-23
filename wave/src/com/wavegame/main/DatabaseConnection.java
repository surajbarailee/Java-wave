package com.wavegame.main;
import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;  
class DatabaseConnection
{  
	static ArrayList arr=new ArrayList();
	public static String valuekeeper[];
	public static int index=1;
	
//		public static highscore()
//		{
//		
//			
//			try{  
//			
//					Class.forName("com.mysql.cj.jdbc.Driver");
//					Connection con=DriverManager.getConnection(  
//					"jdbc:mysql://localhost/scoredatabase/?useLegacyDatetimeCode=false&serverTimezone=UTC","root","");  
//					Statement stmt=con.createStatement();  
//					ResultSet rs=stmt.executeQuery("select * from scoretable order by Score DESC LIMIT 10"); 
//					
//					
//					while(rs.next()) { 
//						
//						arr.add(rs.getString("PlayerName"));
//						arr.add(String.valueOf(rs.getString("Score")));
//					
//					}
//					con.close();
//				String[] arrayvalue=(String[]) arr.toArray(new String[arr.size()]);
//				for (String s:arrayvalue)
//				{
//					System.out.println(s);
//				}
//			
//			} 
//			catch(Exception e){ e.printStackTrace();}  
//			
//		  
//		}
		
		
		public void insertData(String name,int Score)
		{
			Connection con = null;
			try{  
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection(  
				"jdbc:mysql://localhost/scoredatabase?useLegacyDatetimeCode=false&serverTimezone=UTC","root","");  
				//Statement stmt=con.createStatement();
				String Query="INSERT INTO scoretable VALUES(?,?)";
				PreparedStatement preparedStmt=(PreparedStatement) con.prepareStatement(Query);
			System.out.println("preparedstatemetexecuted");;
				preparedStmt.setString(1,name);
				preparedStmt.setInt(2,Score);
				preparedStmt.executeUpdate();
				 
				
				}catch(Exception e){ System.out.println(e);}  
			
			
		}
	
		
}  