package com.mvc.dao;

//VoteDao.java
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.text.StringEscapeUtils;

import com.mvc.util.DBConnection;

public class AjoutFilmDao {
	public void AjoutFilm(String nomFilm, String synopsis, String nompochette)
	{


		Connection con = null;
		Statement statement = null;


		try
		{
			con = DBConnection.createConnection(); //establishing connection
			statement = con.createStatement(); //Statement is used to write queries. Read more about it.
			// INSERT INTO `films` (`id`, `titre`, `id_pochette`, `synopsis`) VALUES (NULL, 'nomFilm', 'idpochette', 'synopsis')



			statement.executeUpdate("INSERT INTO `films` (`id`, `titre`, `synopsis`) VALUES (NULL,'"+nomFilm+"','"+StringEscapeUtils.escapeHtml4(synopsis)+"')");

			/* Exécution d'une requête de lecture */

			ResultSet rs = statement.executeQuery("SELECT MAX(id) AS id FROM films");
			rs.next();
			int idfilm = rs.getInt("id");

			/* Récupération des données du résultat de la requête de lecture */

			statement.executeUpdate("INSERT INTO `pochette` (`id_film`, `nom_fichier`) VALUES ('"+idfilm+"', '"+nompochette+"')");
			statement.executeUpdate("ALTER TABLE `Notes` ADD `"+idfilm+"` BIT(3) NOT NULL DEFAULT b'0' AFTER `"+(idfilm-1)+"`");
			statement.executeUpdate("ALTER TABLE `FilmsVu` ADD `"+idfilm+"` BIT(1) NOT NULL DEFAULT b'0' AFTER `"+(idfilm-1)+"`");






		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}