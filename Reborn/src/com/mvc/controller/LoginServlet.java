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

import com.mvc.bean.FilmBean;
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
		//Here username and password are the names which I have given in the input box in Login.jsp page. Here I am retrieving the values entered by the user and keeping in instance variables for further use.
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean(); //creating object for LoginBean class, which is a normal java class, contains just setters and getters. Bean classes are efficiently used in java to access user information wherever required in the application.
		loginBean.setUserName(userName); //setting the username and password through the loginBean object then only you can get it in future.
		loginBean.setPassword(password);
		LoginDao loginDao = new LoginDao(); //creating object for LoginDao. This class contains main logic of the application.
		String userValidate = loginDao.authenticateUser(loginBean); //Calling authenticateUser function
		if(userValidate.equals("SUCCESS")) //If function returns success string then user will be rooted to Home page
		{	
			
			// Concernant l'utilisateur
			UserBean userBean = new UserBean();
			userBean.setIduser(loginBean.getIduser());
			userBean.setNom(loginBean.getNom());
			userBean.setUserName(loginBean.getUserName());
			
			
			// Concernant les films
			FilmBean filmBean = new FilmBean();
			filmBean.setListeFilm(ListeFilms());
			filmBean.setListeIdFilm(ListeIdFilms());
			filmBean.setListeSynopsis(ListeSynopsis());
			
			PochetteBean pochetteBean = new PochetteBean();
			pochetteBean.setListePochette(ListePochette());
			
			
			
			// Création de la session
			HttpSession session = request.getSession();

			session.setAttribute( "user", userBean );
			session.setAttribute( "film", filmBean );
			session.setAttribute( "pochette", pochetteBean );
			
		
			request.setAttribute("ListePochette", ListePochette());
			request.setAttribute("ListeIdFilms", ListeIdFilms());
			request.setAttribute("id",loginBean.getIduser());
			
			request.getRequestDispatcher("/Home.jsp").forward(request, response);//RequestDispatcher is used to send the control to the invoked page.
		}
		else
		{
			request.setAttribute("errMessage", userValidate); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
			request.getRequestDispatcher("/Login.jsp").forward(request, response);//forwarding the request
		}		
	
	}
	
	public ArrayList<String> ListePochette() {

		ArrayList<String> laListe = new ArrayList<>();

		Connection con = DBConnection.createConnection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM pochette");


			while(res.next()){

				laListe.add(res.getString("nom_fichier"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return laListe;
	}
	
	
	public ArrayList<Integer> ListeIdFilms() {

		ArrayList<Integer> laListe = new ArrayList<>();

		Connection con = DBConnection.createConnection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM films");


			while(res.next()){

				laListe.add(res.getInt("id"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return laListe;

	}
	
	
	public ArrayList<String> ListeFilms() {

		ArrayList<String> laListe = new ArrayList<>();

		Connection con = DBConnection.createConnection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM films");


			while(res.next()){;

				laListe.add(res.getString("titre"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return laListe;

	}
	
	
	
	public ArrayList<String> ListeSynopsis() {

		ArrayList<String> laListe = new ArrayList<>();

		Connection con = DBConnection.createConnection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM films");


			while(res.next()){

				laListe.add(res.getString("synopsis"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return laListe;

	}

}