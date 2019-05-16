//LoginServlet.java
package com.mvc.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.CatalogueBean;
import com.mvc.bean.LoginBean;
import com.mvc.bean.PochetteBean;
import com.mvc.bean.UserBean;
import com.mvc.dao.LoginDao;
import com.mvc.util.DBConnection;

public class LoginServlet extends HttpServlet {
	
	
	    
	public LoginServlet() {
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUserName(userName); 
		loginBean.setPassword(password);
		LoginDao loginDao = new LoginDao(); 
		String userValidate = loginDao.authenticateUser(loginBean); 
		if(userValidate.equals("SUCCESS")) 
		{	
			
			// Concernant l'utilisateur
			UserBean userBean = new UserBean();
			userBean.setIduser(loginBean.getIduser());
			userBean.setNom(loginBean.getNom());
			userBean.setUserName(loginBean.getUserName());
			
			
			
			// Création de la session
			HttpSession session = request.getSession();
			session.setAttribute( "user", userBean );

			
			request.getRequestDispatcher("/ChargementServlet?op=Home").forward(request, response);
		}
		else
		{
			request.setAttribute("errMessage", userValidate); 
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}		
	
	}
	

}