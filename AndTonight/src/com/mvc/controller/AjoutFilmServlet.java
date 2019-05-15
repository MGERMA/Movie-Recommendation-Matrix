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
		
		String nomFilm= request.getParameter("nomFilm");
		Integer idFilm;
		String synopsis= request.getParameter("Synopsis");
		Integer idPochette;
		String nomPochette= request.getParameter("nomPochette");
		String validation = "Le film a été ajouté";
		AjoutFilmDao AjoutFilm = new AjoutFilmDao(); 
		AjoutFilm.AjoutFilm(nomFilm, synopsis, nomPochette);
		request.setAttribute("validation", validation);  
		request.getRequestDispatcher("/ChargementServlet?op=Home").forward(request, response);
	}
}