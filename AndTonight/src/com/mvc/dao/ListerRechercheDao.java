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
		
		// On met la recherche en minuscule
		
		recherche = recherche.toLowerCase();
		
		
		
		
		CatalogueBean  complet = (CatalogueBean) session.getAttribute("film");
		PochetteBean poch = (PochetteBean) session.getAttribute("pochette");
		
		ArrayList<String> listFilms = complet.getListeFilm();
		ArrayList<Integer> listIdFilms = complet.getListeIdFilm();
		ArrayList<String> listSynopsis = complet.getListeSynopsis();
		ArrayList<String> listPochette = poch.getListePochette();

		

		CatalogueBean catalogueBean = new CatalogueBean();
		PochetteBean pochetteBean = new PochetteBean();


		// Recherche

		ArrayList<String> listFilmsR = new ArrayList<String>();
		ArrayList<Integer> listIdFilmsR = new ArrayList<Integer>();
		ArrayList<String> listSynopsisR = new ArrayList<String>();			
		ArrayList<String> listPochetteR = new ArrayList<String>();



		if (recherche.length()>0){


			for(int id=0 ; id < listIdFilms.size(); id++){

				
				String nom = listFilms.get(id);
				String nomlow = nom.toLowerCase();
				
				if(nomlow.contains(recherche)){
						
				
					
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