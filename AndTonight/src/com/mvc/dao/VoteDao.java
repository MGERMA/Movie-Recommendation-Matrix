package com.mvc.dao;

//VoteDao.java
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;

import com.mvc.bean.LoginBean;
import com.mvc.util.DBConnection;

public class VoteDao {
	public void VoteFilm(String userID, String filmID, String note)
	{

		//On récupère l'ID de l'utilisateur
		//String user_ID = String.valueOf(userID);
		Connection con = null;
		Statement statement = null;

		//On récupère l'ID du film
		//String film_ID = String.valueOf(filmID);

		//On récupère la note de l'utilisateur relatif au film 
		//String filmNote = String.valueOf(note);


		try
		{
			con = DBConnection.createConnection(); //establishing connection
			statement = con.createStatement(); //Statement is used to write queries. Read more about it.
			statement.executeUpdate("UPDATE `Notes` SET `" + filmID + "` = " + note + " WHERE `id_user` = " + userID); //Here table name is users and userName,password are columns. fetching all the records and storing in a resultSet.
			statement.executeUpdate("UPDATE `FilmsVu` SET `" + filmID + "` = 1 WHERE `id_user` = " + userID);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}