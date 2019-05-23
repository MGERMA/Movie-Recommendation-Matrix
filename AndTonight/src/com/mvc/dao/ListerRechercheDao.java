package com.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.mvc.bean.CatalogueBean;
import com.mvc.bean.PochetteBean;
import com.mvc.bean.UserBean;
import com.mvc.util.DBConnection;

public class ListerRechercheDao {



	public void ListerFilmRecherche(String recherche, HttpSession session){


		ArrayList<String> listFilms = new ArrayList<String>();
		ArrayList<Integer> listIdFilms = new ArrayList<Integer>();
		ArrayList<String> listSynopsis = new ArrayList<String>();			
		ArrayList<String> listPochette = new ArrayList<String>();

		Connection con = DBConnection.createConnection();
		Statement stmt;
		ResultSet res;


		try {
			stmt = con.createStatement();

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


		session.setAttribute( "film", catalogueBean );
		session.setAttribute( "pochette", pochetteBean );


	}
}