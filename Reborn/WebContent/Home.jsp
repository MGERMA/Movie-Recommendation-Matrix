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
<title>PRFlix</title>
<style>

</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Gaegu">


</head>
<body>


	<%@ page import="java.util.*, com.mvc.controller.*"%>

	<div style="text-align: left" color="w"></div>
	<%String user = (String) request.getAttribute("nom");%>
	Bonjour
	<%=user%>
	<!-- Refer to the video to understand how this works -->
	<div style="text-align: right">
		<a href="LogoutServlet">Se d�connecter</a>
	</div>

	<div class="entete">
		<div style="text-align: center">
			<h1>PRFlix</h1>
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
				<div class="bande_synopsis">
					<span id="titre_film">
						 titre.
					</span>
					<p class="texte_synopsis">
						<span id="texte"> bla </span>	
					</p>				
				</div>
				<div class="container_pochettes">
					<%
					Collection<String> nomPochette = (Collection<String>) request.getAttribute("ListePochette");
					
					for (String p : nomPochette) {
							String s = "pochettes/" + p;							
					%>

					<div class="boite_pochette">
						<div class="pochette">
								<img src=<%=s%>>
						</div>
					</div>

					<%
						}
					%>
				</div>
				
	<!--				
		<table>	
			<tr>
				<%
				String iduser = (request.getAttribute("id")).toString();

				Collection<Integer> ListeIdFilms = (Collection<Integer>) request.getAttribute("ListeIdFilms");	
				for (Integer i : ListeIdFilms) { 
					String like = "VoteServ?id_user="+iduser+"&id_film="+Integer.toString(i)+"&note=2";
					String dislike = "VoteServ?id_user="+iduser+"&id_film="+Integer.toString(i)+"&note=1";
					
				%>
				<td><a href=<%=like%>>+1</a> <a href=<%=dislike%>>-1</a></td>
				<%
						}
					%>
			</tr>

	  -->

		</table>

		<div>
			<a href="AccesBDD?op=lister"> Consulter la liste de films </a>
		</div>
		<script src="homedynamics.js"></script>
</body>
</html>

