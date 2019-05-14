<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="homestyle.css">
<link rel="shortcut icon" type="image/x-icon"
	href="https://static.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico">
<link rel="mask-icon" type=""
	href="https://static.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg"
	color="#111">
<title>AndTonight</title>
<style>
</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Gaegu">


</head>
<body>


	<%@ page import="java.util.*, com.mvc.controller.*, com.mvc.bean.*"%>


	<%
		CatalogueBean filmBean = (CatalogueBean) session.getAttribute("film");
		UserBean userBean = (UserBean) session.getAttribute("user");
		PochetteBean pochetteBean = (PochetteBean) session.getAttribute("pochette");
	%>


	<div style="text-align: left" color="w"></div>

	Bonjour
	<%=userBean.getNom()%>
	<% System.out.println(userBean.getNom()); %>
	<div style="text-align: right">
		<a href="LogoutServlet">Se déconnecter</a>
	</div>

	<div class="entete">
		<div style="text-align: center">
			<h1>AndTonight</h1>
			<h2>Quel film pour ce soir ?</h2>
			<div class="wrapper">

				<div class="input-group">
					<input class="search" type="text"
						placeholder="Rechercher un film en particulier" style="")><span
						class="bar"></span>
				</div>
			</div>
		</div>
	</div>
	<div>

		Que pensez vous des films suivants :

		<div class="container_pochettes">
			<%
				for (int i : filmBean.getListeIdFilm()) {
	
					String s = "pochettes/" + pochetteBean.getListePochette().get(i-1);

			%>


			<div class="bande_synopsis">
				<span id="titre_film"> <%=filmBean.getListeFilm().get(i-1)%>
				</span>
				<p class="texte_synopsis">
					<span id="texte"> <%=filmBean.getListeSynopsis().get(i-1)%>
					</span>
				</p>
			</div>

			<div class="boite_pochette">
				<div class="pochette">
					<img height="200" src=<%=s%>>
				</div>
			</div>

			<%
				}
			%>
		</div>

		<!--				
		<table>	
			<tr>
				<%String iduser = String.valueOf(userBean.getIduser());

			for (Integer i : filmBean.getListeIdFilm()) {
				String like = "VoteServ?id_user=" + iduser + "&id_film=" + Integer.toString(i) + "&note=2";
				String dislike = "VoteServ?id_user=" + iduser + "&id_film=" + Integer.toString(i) + "&note=1";%>
				<td><a href=<%=like%>>+1</a> <a href=<%=dislike%>>-1</a></td>
				<%}%>
			</tr>

	  -->

		</table>
		<div>
			<a href="AjoutFilm.jsp"> Ajouter un film dans la base de données</a>
		</div>
		<div>
			<a href="AccesBDD?op=lister"> Consulter la liste de films </a>
		</div>
		<script src="homedynamics.js"></script>
</body>
</html>


