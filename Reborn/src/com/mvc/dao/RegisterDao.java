//RegisterDao.java
package com.mvc.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mvc.bean.RegisterBean;
import com.mvc.util.DBConnection;
public class RegisterDao {
public String registerUser(RegisterBean registerBean)
{
String fullName = registerBean.getFullName();
String email = registerBean.getEmail();
String userName = registerBean.getUserName();
String password = registerBean.getPassword();
Connection con = null;
PreparedStatement preparedStatement = null;
try
{
con = DBConnection.createConnection();
String query = "insert into Utilisateurs(id_user,nom,pseudo,mail,pwd) values (NULL,?,?,?,?)"; //Insert user details into the table 'USERS'
preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
preparedStatement.setString(1, fullName);
preparedStatement.setString(2, userName);
preparedStatement.setString(3, email);
preparedStatement.setString(4, password);
int i= preparedStatement.executeUpdate();
if (i!=0)  //Just to ensure data has been inserted into the database
return "SUCCESS"; 
}
catch(SQLException e)
{
e.printStackTrace();
}
return "Ooups, il y a eu une erreur à l'envoi dans la vase de données";  // On failure, send a message from here.
}
}