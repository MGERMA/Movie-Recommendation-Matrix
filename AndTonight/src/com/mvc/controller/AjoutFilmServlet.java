package com.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dao.AjoutFilmDao;

@WebServlet("/AjoutFilmServlet")
public class AjoutFilmServlet extends HttpServlet {


	public AjoutFilmServlet() {
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Here username and password are the names which I have given in the input box in Login.jsp page. Here I am retrieving the values entered by the user and keeping in instance variables for further use.
		String nomFilm= request.getParameter("nomFilm");
		Integer idFilm;
		String synopsis= request.getParameter("Synopsis");
		Integer idPochette;
		String nomPochette= request.getParameter("nomPochette");
		String validation = "Le film a été ajouté";
		AjoutFilmDao AjoutFilm = new AjoutFilmDao(); //creating object for VoteDao. This class contains main logic of the application.
		AjoutFilm.AjoutFilm(nomFilm, synopsis, nomPochette);//Calling VoteFilm function
		request.setAttribute("validation", validation); //with setAttribute() you can define a "key" and value pair so that you can get it in future using getAttribute("key")
		request.getRequestDispatcher("/Home.jsp").forward(request, response);//RequestDispatcher is used to send the control to the invoked page.
	}
}