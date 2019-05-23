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
import com.mvc.dao.ListerFilmDao;
import com.mvc.dao.ListerFilmNonVuDao;
import com.mvc.dao.ListerRechercheDao;
import com.mvc.dao.ListerRecommandationDao;
import com.mvc.util.DBConnection;
import com.mvc.util.Recommandation;

public class ChargementServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String op = request.getParameter("op");
		String recherche_home = request.getParameter("recherche_home");
		String recherche_lister = request.getParameter("recherche_lister");
		String seuil = request.getParameter("seuil");
		String recherche = null;
		String redirect = null;



		HttpSession session = request.getSession();


		if (seuil != null){
			if(seuil.equals("")){
				seuil = "0";
			}
			op="recommander";
		}
				
		if (recherche_home!=null){
			op="rechercher";
			recherche = recherche_home;
			redirect = "Home";
		} else if (recherche_lister!=null){
			op="rechercher";
			recherche = recherche_lister;
			redirect = "lister";
		}



		if (op.equals("Home")){


			ListerFilmNonVuDao liste = new ListerFilmNonVuDao();
			liste.ListerFilmNonVu(session);
			request.getRequestDispatcher("/Home.jsp").forward(request, response);

		}


		if (op.equals("lister")){

			ListerFilmDao liste = new ListerFilmDao();
			liste.ListerFilm(session);
			request.getRequestDispatcher("/lister.jsp").forward(request, response);

		}


		if (op.equals("rechercher")){

			ListerRechercheDao liste = new ListerRechercheDao();
			liste.ListerFilmRecherche(recherche, session);
			request.getRequestDispatcher("/"+redirect+".jsp").forward(request, response);

		}
		
		
		if (op.equals("recommander")){

			Recommandation m = new Recommandation();
			m.RecommandationUser(session,Float.parseFloat(seuil));
			ListerRecommandationDao liste = new ListerRecommandationDao();
			liste.ListerRecommandation(session,m.getMesRecommendations(),m.getMesNotes());
			request.getRequestDispatcher("/recommandation.jsp").forward(request, response);

		}



	}

}