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
import com.mvc.bean.UserBean;
import com.mvc.util.DBConnection;

public class ChargementServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String op = request.getParameter("op");
		String recherche = request.getParameter("recherche");

		ArrayList<String> listFilms = new ArrayList<String>();
		ArrayList<Integer> listIdFilms = new ArrayList<Integer>();
		ArrayList<String> listSynopsis = new ArrayList<String>();			
		ArrayList<String> listPochette = new ArrayList<String>();

		Connection con = DBConnection.createConnection();
		
		ResultSet res;

		try {
			Statement stmt = con.createStatement();
			
			res = stmt.executeQuery("SELECT * FROM films");
			while(res.next()){;
			listFilms.add(res.getString("titre"));
			listIdFilms.add(res.getInt("id"));
			listSynopsis.add(res.getString("synopsis"));
			}


			res = stmt.executeQuery("SELECT * FROM pochette");
			while(res.next()){
				listPochette.add(res.getString("nom_fichier"));
			}
			
			stmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		if (recherche!=null){
			op="rechercher";
		} 



		if (op.equals("Home")){
			// Seulement les films que l'utilisateur n'a pas encore vu 
			
			// reccupération de la session
			HttpSession session = request.getSession();
			UserBean user = (UserBean) session.getAttribute("user");
			
			con = DBConnection.createConnection();
			Statement stmt;
						
			ArrayList<String> listFilms_NV = new ArrayList<String>();
			ArrayList<Integer> listIdFilms_NV = new ArrayList<Integer>();
			ArrayList<String> listSynopsis_NV = new ArrayList<String>();			
			ArrayList<String> listPochette_NV = new ArrayList<String>();
			
			try {
				stmt = con.createStatement();
				res = stmt.executeQuery("SELECT * from FilmsVu WHERE id_user="+String.valueOf(user.getIduser()));
				res.next();
				
				int id_film=1;			
				int nb_film = listFilms.size();
				
				while(id_film < nb_film){		
					
					if (res.getInt(String.valueOf(id_film)) == 0){
						System.out.println(id_film-1);
						listFilms_NV.add(listFilms.get(id_film-1));
						listIdFilms_NV.add(listIdFilms.get(id_film-1));
						listSynopsis_NV.add(listSynopsis.get(id_film-1));
						listPochette_NV.add(listPochette.get(id_film-1));				
					}
					
					id_film ++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
						
			
			
			CatalogueBean catalogueBean = new CatalogueBean();
			catalogueBean.setListeFilm(listFilms_NV);
			catalogueBean.setListeIdFilm(listIdFilms_NV);
			catalogueBean.setListeSynopsis(listSynopsis_NV);

			PochetteBean pochetteBean = new PochetteBean();
			pochetteBean.setListePochette(listPochette_NV);

			
			session.setAttribute( "film", catalogueBean );
			session.setAttribute( "pochette", pochetteBean );
		
			request.getRequestDispatcher("/Home.jsp").forward(request, response);

		}


		if (op.equals("lister")){
			// Concernant les films
			CatalogueBean catalogueBean = new CatalogueBean();
			catalogueBean.setListeFilm(listFilms);
			catalogueBean.setListeIdFilm(listIdFilms);
			catalogueBean.setListeSynopsis(listSynopsis);

			PochetteBean pochetteBean = new PochetteBean();
			pochetteBean.setListePochette(listPochette);



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



			if (recherche.length()>0){


				for(int i : listIdFilms){

					int id = i-1;
					String nom = listFilms.get(id);

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

}