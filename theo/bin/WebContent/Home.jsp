<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
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
		<a href="LogoutServlet">Se d�connecter</a>
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
	<form action="" accept-charset="UTF-8">
		<div class="container_pochettes">
			<%
				int iduser = userBean.getIduser();
				for (int i : filmBean.getListeIdFilm()) {
	
					String s = "pochettes/" + pochetteBean.getListePochette().get(i-1);
					String like = "VoteServ?id_user=" + iduser + "&id_film=" + Integer.toString(i) + "&note=2";
					String dislike = "VoteServ?id_user=" + iduser + "&id_film=" + Integer.toString(i) + "&note=1";%>


			<div class="bande_synopsis">
				<div id="container_titre_film"> 
						<span id="titre_film"> </span>
				</div>
				<div id=container_image_resume> 
					<img id="img_resume" src=pochettes/captainMarvel.jpg>
				</div>
				<p class="texte_synopsis">
					<span id="texte"> </span>
				</p>
			</div>

			<div class="boite_pochette">
				<div class="pochette">
					<img id="poch<%=i%>" src=<%=s%>>
				</div>
				
				<div class="bande_like_gauche"> 
					<a href="<%=dislike %>" class="boutons_dislike">
						<span class="material-icons"> thumb_down_alt </span>
					</a>		
				</div>
				<div class="bande_like_droit"> 
					<a href="<%=like %>" class="boutons_like">
						<span class="material-icons"> thumb_up_alt </span>
					</a>
				</div>
			</div>

			<%
				}
			%>
		</div>

		

		<div>
			<a href="AjoutFilm.jsp"> Ajouter un film dans la base de donn�es</a>
		</div>
		<div>
			<a href="AccesBDD?op=lister"> Consulter la liste de films </a>
		</div>
		</form>
		<script>

		
		
		if (document.location.search.match(/type=embed/gi)) {
		    window.parent.postMessage("resize", "*");
		}
		
		window.console = window.console || function(t) {
		};
		
		//Apparition/D�sapparition bande synopsis 
		
		var boites = document.getElementsByClassName("boite_pochette");
		

		var jsListeSynopsis = new Array();
		var jsListeTitres = new Array();
		<% for (int i=0; i<filmBean.getListeIdFilm().size(); i++) { %>
		jsListeSynopsis[<%= i %>] = new String("les synopsis sont bugués mathis" + <%=i%>); 
		jsListeTitres[<%= i %>] = new String("<%= filmBean.getListeFilm().get(i) %>");
		<% } %>
		
		
		
		function toggle(e) {
			e.target.children[1].classList.toggle("bande_like_gauche--ouverte");
			e.target.children[2].classList.toggle("bande_like_droit--ouverte");
			e.target.children[1].children[0].classList.toggle("boutons_dislike--ouverts");
			e.target.children[2].children[0].classList.toggle("boutons_like--ouverts");
			
   	
			document.querySelector(".bande_synopsis").classList.toggle("bande_synopsis--ouverte");

		};
		function changerTexte(e) {
		    var texte = document.getElementById("texte");
			var titre_film = document.getElementById("titre_film");
			var image_resume = document.getElementById("container_image_resume");
		    var idTableau= parseInt(new String(e.target.firstElementChild.firstElementChild.getAttribute("id").substring(4))) - 1;
		    texte.firstChild.nodeValue = jsListeSynopsis[idTableau];
			titre_film.firstChild.nodeValue = jsListeTitres[idTableau];
			// console.log(e.target.firstElementChild.firstElementChild);
			image_resume.firstElementChild['src'] = e.target.firstElementChild.firstElementChild.getAttribute("src");
		};
		
		for (var i = 0; i < boites.length; i++) {
		    boites[i].addEventListener('mouseenter', toggle, false);
		    boites[i].addEventListener('mouseenter', changerTexte, false);
		    boites[i].addEventListener('mouseleave', toggle, false);
		}			
		</script>
</body>
</html>


