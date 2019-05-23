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
			String query1 = "insert into Utilisateurs(id_user,nom,pseudo,mail,pwd) values (NULL,?,?,?,?)"; 
			preparedStatement = con.prepareStatement(query1); 
			preparedStatement.setString(1, fullName);
			preparedStatement.setString(2, userName);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			int i1= preparedStatement.executeUpdate();
			
			String query2 = "insert into Notes(id_user) values (NULL)"; 
			preparedStatement = con.prepareStatement(query2);
			int i2= preparedStatement.executeUpdate();
			
			String query3 = "insert into FilmsVu(id_user) values (NULL)"; 
			preparedStatement = con.prepareStatement(query3);
			int i3= preparedStatement.executeUpdate();
			
			
			preparedStatement.close();
			con.close();
			
			if (i1 + i2 + i3 !=0)  
				return "SUCCESS"; 
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return "Ooups, il y a eu une erreur à l'envoi dans la vase de données";  
	}
}