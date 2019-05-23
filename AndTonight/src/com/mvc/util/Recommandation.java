package com.mvc.util;
import java.util.*;
import java.io.*;
import java.lang.Math;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.http.HttpSession;

import com.mvc.bean.UserBean;

public class Recommandation{



	private ArrayList<Integer> mesRecommendations;
	private ArrayList<Float> mesNotes;


	public void RecommandationUser(HttpSession session, Float seuil){

		int n = 0;
		int m = 0;
		float[][] matrix = null; 
		
		try
		{

			// ----------- 1 ------------- On récupere la DataBase et on la stocke dans la matice matrix
			Connection con = DBConnection.createConnection();
			Statement statement = con.createStatement();
			
			
			// Calcul de la taille de la table
			String queryTaille = "SELECT COUNT(*) FROM Notes";
			ResultSet rs = statement.executeQuery(queryTaille);
			rs.next();
			n = rs.getInt(1);
			
			queryTaille = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'Notes'";
			rs = statement.executeQuery(queryTaille);
			rs.next();
			m = rs.getInt(1)-1;
			
			
					
			matrix = new float[n][m];		
			String query = "SELECT * FROM Notes";
			rs = statement.executeQuery(query);
			
			int j = 0;
			while (rs.next())
			{			
				for(int i = 1; i<=m; i++) {

					Byte note = rs.getByte(i+1);

					if (note == (byte) 0){
						matrix[j][i-1] = (float) -1 ;
					} else if (note == (byte) 1 ){
						matrix[j][i-1] = (float) 0 ;
					} else if (note == (byte) 2 ){
						matrix[j][i-1] = (float) 10 ;
					}			
				}	


				j++;
			}

			statement.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ---------- 2 ----------- Traitement de la matrice


		//int[][] test = testData(testFile);

		Pair<float[][], float[][]> p = Gradient(matrix,5, (float) 0.001, (float) 0.01);

		float[][] matrixP = PredictRating(p.a,p.b);


		// ---------- 3 ----------- Mise à jour de la base de données


		UserBean user = (UserBean) session.getAttribute("user");

		int id_user = user.getIduser();

		ArrayList<Integer> recom= new ArrayList<Integer>();
		ArrayList<Float> note = new ArrayList<Float>();

		for(int i = 1; i<=m; i++) {

			Float noteUser = matrixP[id_user-1][i-1];


			if (noteUser > seuil){
				recom.add(i);
				note.add(noteUser);				
			}

		}

		setMesNotes(note);
		setMesRecommendations(recom);




	}


	public static class Pair<A,B>{
		public final A a;
		public final B b;

		public Pair(A a, B b)
		{
			this.a = a;
			this.b = b;		
		}
	}



	public static Pair<float[][], float[][]> Gradient(float[][] matrix, int r, float rate, float lambda)
	{
		int maxIter = 1000;
		int n1 = matrix.length;
		int n2 = matrix[0].length;

		float[][] U = new float[n1][r];
		float[][] V = new float[n2][r];

		// Initialize U and V matrix
		Random rand = new Random();
		for (int i = 0; i < U.length; ++i)
		{
			for (int j = 0; j < U[0].length; ++j)
			{
				U[i][j] = rand.nextFloat()/(float)r;
			}
		}

		for (int i = 0; i < V.length; ++i)
		{
			for (int j = 0; j < V[0].length; ++j)
			{
				V[i][j] = rand.nextFloat()/(float)r;
			}
		}


		// Gradient Descent
		for (int iter = 0; iter < maxIter; ++iter)
		{
			//			System.out.println("Iteration no. " + iter + " / " + maxIter);

			float[][] prodMatrix = new float[n1][n2];
			for (int i = 0; i < n1; ++i)
			{
				for (int j = 0; j < n2; ++j)
				{
					for (int k = 0; k < r; ++k)
					{
						prodMatrix[i][j] += U[i][k]*V[j][k];
					}
				}
			}		

			float[][] errorMatrix = new float[n1][n2];
			for (int i = 0; i < n1; ++i)
			{
				for (int j = 0; j < n2; ++j)
				{
					if (matrix[i][j] == -1f)
					{
						errorMatrix[i][j] = 0f;
					}
					else
					{
						errorMatrix[i][j] = matrix[i][j] - prodMatrix[i][j];
					}
				}
			}

			float[][] UGrad = new float[n1][r];
			for (int i = 0; i < n1; ++i)
			{
				for (int j = 0; j < r; ++j)
				{
					for (int k = 0; k < n2; ++k)
					{
						UGrad[i][j] += errorMatrix[i][k]*V[k][j]; 
					}
				}
			}

			float[][] VGrad = new float[n2][r];
			for (int i = 0; i < n2; ++i)
			{
				for (int j = 0; j < r; ++j)
				{
					for (int k = 0; k < n1; ++k)
					{
						VGrad[i][j] += errorMatrix[k][i]*U[k][j];
					}
				}
			}	

			float[][] Un = new float[n1][r];
			for (int i = 0; i < n1; ++i)
			{
				for (int j = 0; j < r; ++j)
				{
					Un[i][j] = (1f - rate*lambda)*U[i][j] + rate*UGrad[i][j];
				}
			}

			float[][] Vn = new float[n2][r];
			for (int i = 0; i < n2; ++i)
			{
				for (int j = 0; j < r; ++j)
				{
					Vn[i][j] = (1f - rate*lambda)*V[i][j] + rate*VGrad[i][j];
				}
			}

			U = Un;
			V = Vn;
		}

		Pair<float[][], float[][]> p = new Pair<float[][], float[][]>(U,V);
		return p;
	}

	public static float[][] PredictRating(float[][] U, float[][] V){
		int n1 = U.length;
		int n2 = V.length;
		int r = V[0].length;

		float[][] prodMatrix = new float[n1][n2];
		for (int i = 0; i < n1; ++i)
		{
			for (int j = 0; j < n2; ++j)
			{
				for (int k = 0; k < r; ++k)
				{
					prodMatrix[i][j] += U[i][k]*V[j][k];
				}
			}
		}
		return prodMatrix;
	}

	public ArrayList<Integer> getMesRecommendations() {
		return mesRecommendations;
	}

	public void setMesRecommendations(ArrayList<Integer> mesRecommendations) {
		this.mesRecommendations = mesRecommendations;
	}

	public ArrayList<Float> getMesNotes() {
		return mesNotes;
	}

	public void setMesNotes(ArrayList<Float> mesNotes) {
		this.mesNotes = mesNotes;
	}







}