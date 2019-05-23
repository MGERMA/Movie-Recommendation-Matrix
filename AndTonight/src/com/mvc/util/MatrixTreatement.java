package com.mvc.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.StringTokenizer;


public class MatrixTreatement {
	public void AjoutFilm(String nomFilm, String synopsis, String nompochette)
	{

		Connection con = null;
		Statement statement = null;


		try
		{

			// ----------- 1 ------------- On récupere la DataBase et on la stocke dans la matice matrix
			con = DBConnection.createConnection();
			statement = con.createStatement();

			String query = "SELECT * FROM Notes";


			ResultSet rs = statement.executeQuery(query);



			int n = 8;
			int m = 21;

			byte[][] matrix = new byte[n][m];

			int j = 0;
			while (rs.next())
			{
				j++;
				for(int i = 1; i<=m; i++) {
					byte noteI = rs.getByte(i);
					matrix[j][i] = noteI;
				}
			}
			statement.close();



			// ---------- 2 ----------- Traitement de la matrice


			//int[][] test = testData(testFile);

			Pair<byte[][], byte[][]> p = myRecommender(matrix,5, (byte) 0, (byte) 2);
			try {

				byte[][] matrixP = PredictRating(p.a,p.b,matrix);


				statement = con.createStatement();

				// ---------- 3 ----------- Mise à jour de la base de données



				for(int j2 = 0; j2<=n ; j2++)
				{
					for(int i = 1; i<=m; i++) {

						String query2 = "update Notes set " + i +" = " + matrixP[j2][i] + " where id_user = "+ j2;
						statement.executeUpdate(query);

					}
				}
				statement.close();


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			con.close();

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}


	public static byte[][] PredictRating(byte[][] U, byte[][] V, byte [][] test)throws FileNotFoundException, IOException
	{
		int n1 = U.length;
		int n2 = V.length;
		int r = V[0].length;


		byte[][] prodMatrix = new byte[n1][n2];
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


		return(prodMatrix);
	}


	public static Pair<byte[][], byte[][]> myRecommender(byte[][] matrix, int r, byte rate, byte lambda)
	{
		int maxIter = 1000;
		int n1 = matrix.length;
		int n2 = matrix[0].length;

		byte[][] U = new byte[n1][r];
		byte[][] V = new byte[n2][r];

		// Initialize U and V matrix
		Random rand = new Random();
		for (int i = 0; i < U.length; ++i)
		{
			for (int j = 0; j < U[0].length; ++j)
			{
				U[i][j] = (byte) (rand.nextInt(3)/(byte)r);
			}
		}

		for (int i = 0; i < V.length; ++i)
		{
			for (int j = 0; j < V[0].length; ++j)
			{
				V[i][j] = (byte) (rand.nextInt(3)/(byte)r);
			}
		}


		// Gradient Descent
		for (int iter = 0; iter < maxIter; ++iter)
		{
			//			System.out.println("Iteration no. " + iter + " / " + maxIter);

			byte[][] prodMatrix = new byte[n1][n2];
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

			byte[][] errorMatrix = new byte[n1][n2];
			for (int i = 0; i < n1; ++i)
			{
				for (int j = 0; j < n2; ++j)
				{
					if (matrix[i][j] == -1f)
					{
						errorMatrix[i][j] = (byte) 0f;
					}
					else
					{
						errorMatrix[i][j] = (byte) (matrix[i][j] - prodMatrix[i][j]);
					}
				}
			}

			byte[][] UGrad = new byte[n1][r];
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

			byte[][] VGrad = new byte[n2][r];
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

			byte[][] Un = new byte[n1][r];
			for (int i = 0; i < n1; ++i)
			{
				for (int j = 0; j < r; ++j)
				{
					Un[i][j] = (byte) ((1f - rate*lambda)*U[i][j] + rate*UGrad[i][j]);
				}
			}

			byte[][] Vn = new byte[n2][r];
			for (int i = 0; i < n2; ++i)
			{
				for (int j = 0; j < r; ++j)
				{
					Vn[i][j] = (byte) ((1f - rate*lambda)*V[i][j] + rate*VGrad[i][j]);
				}
			}

			U = Un;
			V = Vn;
		}

		Pair<byte[][], byte[][]> p = new Pair<byte[][], byte[][]>(U,V);
		return p;
	}

	public static class Pair<A,B>
	{
		public final A a;
		public final B b;

		public Pair(A a, B b)
		{
			this.a = a;
			this.b = b;
		}
	}

	public static int[][] testData(String testFile)throws FileNotFoundException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		StringTokenizer st = null;
		String row;
		int[][] data = new int[17636][2];
		int i = 0;
		while ((row = br.readLine()) != null)
		{
			st = new StringTokenizer(row, ",");
			while(st.hasMoreTokens())
			{
				data[i][0] = Integer.parseInt(st.nextToken());
				data[i][1] = Integer.parseInt(st.nextToken());
			}
			i += 1;
		}
		return data;
	}


}
