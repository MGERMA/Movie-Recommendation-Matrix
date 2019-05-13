package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ejb.Singleton;

@Singleton
public class Facade {

	

	private Connection con;
	
	{
		String db_url = "jdbc:mysql://mysql-prflix.alwaysdata.net:3306/prflix_bdd";
		String db_user = "prflix";
		String db_pass = "coolkids31";
	
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			this.con = DriverManager.getConnection(db_url,db_user,db_pass);
			
			//this.con = DriverManager.getConnection(db_url, db_user, db_pass);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	public ArrayList<Integer> ListeIdFilms(int id) {
		ArrayList<Integer> laListe = new ArrayList<Integer>();
		try {
			Statement stmt = con.createStatement();
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
	
	public String GetPochette(int film_id)  {
		String leNomjpg = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("SELECT nom_fichier FROM pochette WHERE id_film ="+film_id);
			
			leNomjpg = res.getString("nom_fichier");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return leNomjpg;
			
	}
	
	
}
