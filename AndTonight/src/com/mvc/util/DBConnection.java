package com.mvc.util;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
	public static Connection createConnection()
	
	{
		Connection con = null;
		String url = "jdbc:mysql://mysql-prflix.alwaysdata.net:3306/prflix_bdd"; 
		String username = "prflix"; 
		String password = "coolkids31";
		try
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver"); 
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, username, password); 
			System.out.println("Printing connection object "+con);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
