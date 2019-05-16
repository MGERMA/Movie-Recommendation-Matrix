package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mvc.dao.VoteDao;

@WebServlet("/VoteServ")
public class VoteServ extends HttpServlet {


	public VoteServ() {
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Here username and password are the names which I have given in the input box in Login.jsp page. Here I am retrieving the values entered by the user and keeping in instance variables for further use.
		String userID = request.getParameter("id_user");
		String filmID = request.getParameter("id_film");
		String note = request.getParameter("note");
		String validation = "votre avis a été pris en compte";
		VoteDao Vote = new VoteDao(); //creating object for VoteDao. This class contains main logic of the application.
		Vote.VoteFilm(userID,filmID,note); //Calling VoteFilm function
		request.setAttribute("validation", validation); //with setAttribute() you can define a "key" and value pair so that you can get it in future using getAttribute("key")
		request.getRequestDispatcher("/ChargementServlet?op=Home.jsp").forward(request, response);//RequestDispatcher is used to send the control to the invoked page.
	}
}