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
import javax.servlet.http.HttpSession;

import com.mvc.bean.CatalogueBean;
import com.mvc.bean.FilmBean;
import com.mvc.bean.PochetteBean;
import com.mvc.util.DBConnection;

public class ChargementServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String op = request.getParameter("op");
		String recherche = request.getParameter("recherche");
		
		if (recherche!=null){
			op="rechercher";
		} 
		
			
		
		if (op.equals("Home")){
			// Concernant les films
			CatalogueBean catalogueBean = new CatalogueBean();
			catalogueBean.setListeFilm(ListeFilms());
			catalogueBean.setListeIdFilm(ListeIdFilms());
			catalogueBean.setListeSynopsis(ListeSynopsis());

			PochetteBean pochetteBean = new PochetteBean();
			pochetteBean.setListePochette(ListePochette());



			// reccupération de la session
			HttpSession session = request.getSession();

			session.setAttribute( "film", catalogueBean );
			session.setAttribute( "pochette", pochetteBean );

			request.getRequestDispatcher("/Home.jsp").forward(request, response);

		}


		if (op.equals("lister")){
			// Concernant les films
			CatalogueBean catalogueBean = new CatalogueBean();
			catalogueBean.setListeFilm(ListeFilms());
			catalogueBean.setListeIdFilm(ListeIdFilms());
			catalogueBean.setListeSynopsis(ListeSynopsis());

			PochetteBean pochetteBean = new PochetteBean();
			pochetteBean.setListePochette(ListePochette());



			// reccupération de la session
			HttpSession session = request.getSession();

			session.setAttribute( "film", catalogueBean );
			session.setAttribute( "pochette", pochetteBean );

			request.getRequestDispatcher("/lister.jsp").forward(request, response);

		}
		
		
		if (op.equals("rechercher")){
			
			CatalogueBean catalogueBean = new CatalogueBean();
			PochetteBean pochetteBean = new PochetteBean();
			
						
			// Recherche
			
			ArrayList<String> listFilmsR = new ArrayList<String>();
			ArrayList<Integer> listIdFilmsR = new ArrayList<Integer>();
			ArrayList<String> listSynopsisR = new ArrayList<String>();			
			ArrayList<String> listPochetteR = new ArrayList<String>();
			
			ArrayList<String> listFilms = ListeFilms();
			ArrayList<Integer> listIdFilms = ListeIdFilms();
			ArrayList<String> listSynopsis = ListeSynopsis();			
			ArrayList<String> listPochette = ListePochette();
			
			
			if (recherche.length()>0){
				
					
				for(int i : listIdFilms){
					
					int id = i-1;
					String nom = listFilms.get(id);
					System.out.println(nom + " " + nom.contains(recherche));
					
					if(nom.contains(recherche)){
						
						listFilmsR.add(nom);
						
						listIdFilmsR.add(id);
						
						listSynopsisR.add(listSynopsis.get(id));
						
						listPochetteR.add(listPochette.get(id));
						
					}		
				}
				
							
				catalogueBean.setListeFilm(listFilmsR);
			
				catalogueBean.setListeIdFilm(listIdFilmsR);
	
				catalogueBean.setListeSynopsis(listSynopsisR);
		
				pochetteBean.setListePochette(listPochetteR);
			
										
			} else {
				
				catalogueBean.setListeFilm(listFilms);
				catalogueBean.setListeIdFilm(listIdFilms);
				catalogueBean.setListeSynopsis(listSynopsis);			
				pochetteBean.setListePochette(listPochette);
				
			}
			
			



			// reccupération de la session
			
			HttpSession session = request.getSession();

			session.setAttribute( "film", catalogueBean );
			session.setAttribute( "pochette", pochetteBean );

			request.getRequestDispatcher("/Home.jsp").forward(request, response);

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