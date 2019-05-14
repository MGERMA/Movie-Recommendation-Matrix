package com.mvc.dao;


//VoteDao.java
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mvc.util.DBConnection;
public class IdDao {
public String GetID(String userName)
{
	
//On récupère l'ID de l'utilisateur
//String user_ID = String.valueOf(userID);
Connection con = null;
Statement statement = null;
ResultSet resultSet = null;
String userNameDB = "";
String userID = "";



//On récupère l'ID du film
//String film_ID = String.valueOf(filmID);

//On récupère la note de l'utilisateur relatif au film 
//String filmNote = String.valueOf(note);


try
{
con = DBConnection.createConnection(); //establishing connection
statement = con.createStatement(); //Statement is used to write queries. Read more about it.
resultSet = statement.executeQuery("select pseudo,id from Utilisateurs"); //Here table name is users and userName,password are columns. fetching all the records and storing in a resultSet.
userNameDB = resultSet.getString("pseudo"); //fetch the values present in database
userID = resultSet.getString("id");
while (!(userName.equals(userNameDB))){
	userID = resultSet.getString("id");
	userNameDB = resultSet.getString("pseudo");
	resultSet.next();	
	
}
}
catch(SQLException e)
{
e.printStackTrace();
}
return userID;
}
}
