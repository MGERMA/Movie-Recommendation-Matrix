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
		
		String userID = request.getParameter("id_user");
		String filmID = request.getParameter("id_film");
		String note = request.getParameter("note");
		String redirect = request.getParameter("redirect");
		String validation = "votre avis a été pris en compte";
		VoteDao Vote = new VoteDao(); 

		Vote.VoteFilm(userID,filmID,note); 
		request.setAttribute("validation", validation); 
		request.getRequestDispatcher("/ChargementServlet?op="+redirect).forward(request, response);
	}
}