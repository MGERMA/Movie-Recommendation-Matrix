//DBConnection.java
package com.mvc.util;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
	public static Connection createConnection()
	{
		Connection con = null;
		String url = "jdbc:mysql://mysql-prflix.alwaysdata.net:3306/prflix_bdd"; //MySQL URL and followed by the database name
		String username = "prflix"; //MySQL username
		String password = "coolkids31"; //MySQL password
		try
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver"); //loading mysql driver
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
			System.out.println("Printing connection object "+con);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
