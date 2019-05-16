//LoginDao.java
package com.mvc.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mvc.bean.LoginBean;
import com.mvc.util.DBConnection;
public class LoginDao {
	public String authenticateUser(LoginBean loginBean)
	{
		String userName = loginBean.getUserName();
		String password = loginBean.getPassword();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String userNameDB = "";
		String passwordDB = "";
		try
		{
			con = DBConnection.createConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery("select id_user, nom, pseudo,pwd from Utilisateurs");
			while(resultSet.next())
			{
				userNameDB = resultSet.getString("pseudo");
				passwordDB = resultSet.getString("pwd");
				if(userName.equals(userNameDB) && password.equals(passwordDB))
				{
					loginBean.setIduser(Integer.parseInt(resultSet.getString("id_user")));
					loginBean.setNom(resultSet.getString("nom"));
					return "SUCCESS";
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return "Pseudo ou Mot de passe incorrect";
	}
}