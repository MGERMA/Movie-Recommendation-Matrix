package com.mvc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;
@WebServlet("/AccesBDD")
public class AccesBDD extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter("op");


	/*
		if(op.equals("ajouterpersonne")){
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			if(nom.length()*prenom.length()>0){
			facade.ajoutPersonne(nom,prenom);
			response.getWriter().println("Personne ajoutée");
			}
			request.getRequestDispatcher("ajouterpersonne.html").forward(request,response);
		}*/



		if(op.equals("lister")){
				ArrayList<Integer> listid = ListeIdFilms();
				ArrayList<String> listPochettes = new ArrayList<String>();
				listPochettes = ListeTitre();

				request.setAttribute("listePochettes", listPochettes);
				request.getRequestDispatcher("/lister.jsp").forward(request,response);
			}


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


	public ArrayList<Integer> ListeIdPochette() {

		ArrayList<Integer> laListe = new ArrayList<>();

		Connection con = DBConnection.createConnection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM films");


		while(res.next()){

			laListe.add(res.getInt("id_pochette"));
		}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return laListe;

}




	public ArrayList<String> ListeTitre() {

		ArrayList<String> laListe = new ArrayList<>();

		Connection con = DBConnection.createConnection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM films");


		while(res.next()){

			laListe.add(res.getString("titre"));
		}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return laListe;

}










}
