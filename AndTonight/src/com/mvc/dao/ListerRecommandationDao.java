package com.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import com.mvc.bean.CatalogueBean;
import com.mvc.bean.PochetteBean;
import com.mvc.bean.UserBean;
import com.mvc.util.DBConnection;

public class ListerRecommandationDao {



	public void ListerRecommandation(HttpSession session, ArrayList<Integer> recoms, ArrayList<Float> notes){


		ArrayList<String> listFilms = new ArrayList<String>();
		ArrayList<Integer> listIdFilms = new ArrayList<Integer>();
		ArrayList<String> listSynopsis = new ArrayList<String>();			
		ArrayList<String> listPochette = new ArrayList<String>();

		Connection con = DBConnection.createConnection();
		Statement stmt;
		ResultSet res;

		UserBean user = (UserBean) session.getAttribute("user");

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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// Seulement les films que l'utilisateur n'a pas encore vu et qui sont recommandés
		// reccupération de la session



		ArrayList<String> listFilms_NV = new ArrayList<String>();
		ArrayList<Integer> listIdFilms_NV = new ArrayList<Integer>();
		ArrayList<String> listSynopsis_NV = new ArrayList<String>();			
		ArrayList<String> listPochette_NV = new ArrayList<String>();
		ArrayList<Float> notes_res = new ArrayList<Float>();

		try {

			stmt = con.createStatement();
			res = stmt.executeQuery("SELECT * from FilmsVu WHERE id_user="+String.valueOf(user.getIduser()));
			res.next();


			int ind =0;
			for (int k : recoms){		

				if (res.getInt(k+1) == 0){

					listFilms_NV.add(listFilms.get(k-1));
					listIdFilms_NV.add(listIdFilms.get(k-1));
					listSynopsis_NV.add(listSynopsis.get(k-1));
					listPochette_NV.add(listPochette.get(k-1));	
					notes_res.add(notes.get(ind));
				}	
				ind ++;
			}


			stmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
			

		// reccuperation de la permutation
		ArrayList<Integer> perm = Permutation(notes_res);
		
		System.out.println(perm);
		
		// tri des notes 
		
		listFilms_NV = Tri(listFilms_NV,perm);
		listIdFilms_NV = Tri(listIdFilms_NV,perm);
		listSynopsis_NV = Tri(listSynopsis_NV,perm);
		listPochette_NV = Tri(listPochette_NV,perm);
		notes_res = Tri(notes_res,perm);
		
		
		
		// Definition des nouveaux catalogue
		
		CatalogueBean catalogueBean = new CatalogueBean();
		catalogueBean.setListeFilm(listFilms_NV);
		catalogueBean.setListeIdFilm(listIdFilms_NV);
		catalogueBean.setListeSynopsis(listSynopsis_NV);

		PochetteBean pochetteBean = new PochetteBean();
		pochetteBean.setListePochette(listPochette_NV);
		
		

		session.setAttribute( "film", catalogueBean );
		session.setAttribute( "pochette", pochetteBean );
		session.setAttribute( "notes", normaliserNotes(notes_res));


	}


	//Renvoie la liste des permutation pour trier les listes dans l'ordre des notes
	public ArrayList<Integer> Permutation(ArrayList<Float> notes){
		
		ArrayList<Integer> permutation = new ArrayList<Integer>();
		ArrayList<Float> cpnotes = (ArrayList<Float>) notes.clone();
		float maxtlb = 0;
		int i = 0;
		int taille = cpnotes.size();
		
		while (taille > 0){
			maxtlb = Collections.max(cpnotes);
			i = cpnotes.indexOf(maxtlb);
			permutation.add(i);
			cpnotes.remove(i);
			cpnotes.add(i,(float) -15 );
			taille --;
		}
		
		return permutation;
	}

	//Renvoie la liste triM-CM-)e d'apres la liste de permutations
	public static <T> ArrayList<T> Tri(ArrayList<T> liste, ArrayList<Integer> permutation ){
		ArrayList<T> temp = new ArrayList<T>();

		for (int pos : permutation){		
			temp.add(liste.get(pos));
		}

		return temp;
	}
	
	
	public ArrayList<Float> normaliserNotes(ArrayList<Float> notes){
		
		ArrayList<Float> res = new ArrayList<Float>();
		float max =1;
		if (notes.size() >0){
			max = notes.get(0);
		}
		for (float note : notes){
			res.add((note*100)/max);
		}		
		return res;	
	}


}