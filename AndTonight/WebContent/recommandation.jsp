<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="recommandations.css">
<link rel="shortcut icon" type="image/x-icon"
	href="https://static.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico">
<link rel="mask-icon" type=""
	href="https://static.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg"
	color="#111">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<title>Recommandations</title>
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
		ArrayList<Float> notes = (ArrayList<Float>) session.getAttribute("notes");


		if (filmBean == null) {
			filmBean = new CatalogueBean();
		}

		if (pochetteBean == null) {
			pochetteBean = new PochetteBean();
		}
	%>


	<div style="text-align: left" color="w"></div>


	<div style="text-align: right">
		<a href="LogoutServlet">Se déconnecter</a>
	</div>

	<div class="entete">
		<div style="text-align: center">
			<h1>Vos recommandations</h1>
			<h2>Vous allez prendre un plaisir fou !</h2>
			<div class="wrapper">

				<div class="input-group">
					<form action="/AndTonight/ChargementServlet?op=recommander"
						method="get">
						<input class="search" type="text" placeholder="Entrer le seuil"
							name="seuil" style="")><span class="bar"></span>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div>

		<form action="" accept-charset="UTF-8">
			<div class="container_pochettes">
				<%

					for (int i = 0; i < filmBean.getListeIdFilm().size(); i++) {

						String s = "pochettes/" + pochetteBean.getListePochette().get(i);
						
						String like = "VoteServ?id_film=" + filmBean.getListeIdFilm().get(i) + "&note=3" + "&redirect=Home";
						String vu = "VoteServ?id_film=" + filmBean.getListeIdFilm().get(i) + "&note=2" + "&redirect=Home";
						String favoris = "VoteServ?id_film=" + filmBean.getListeIdFilm().get(i) + "&note=4" + "&redirect=Home";
						String dislike = "VoteServ?id_film=" + filmBean.getListeIdFilm().get(i) + "&note=1" + "&redirect=Home";
				
				%>


				<div class="bande_synopsis">
					<div id="container_titre_film">
						<span id="titre_film"> </span>
					</div>
					<div id=container_image_resume>
						<img id="img_resume" src=<%=s%>>
					</div>
					<p class="texte_synopsis">
					<div class="container_jauge">
									<h1 class="loader__title">0</h1>
									<div class="loader"></div>   
						</div>
						<span id="texte"> </span>
					</p>
				</div>

				

				<div class="boite_pochette">
					<div class="pochette">
						<img id="poch<%=i%>" src=<%=s%>>

					</div>
					<div class="bande_like_gauche">
					<a href="<%=dislike%>" class="boutons_dislike"> <span
						class="material-icons"> thumb_down_alt </span>
					</a>
				</div>
				<div class="bande_like_droit">
					<a href="<%=like%>" class="boutons_like"> <span
							class="material-icons"> thumb_up_alt </span>
					</a>
					<a href="<%=favoris%>" class="boutons_favoris"> <span
						class="material-icons"> favorite </span>
					</a>
					<a href="<%=vu%>" class="boutons_check"> <span
						class="material-icons"> check </span>
					</a>
				</div>
					
					
				</div>
				<%
					}
					%>

			</div>

			<div>
				<a href="ChargementServlet?op=Home"> Retour au Home</a>
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
		var boutons_like = new Array();
		var container = document.getElementsByClassName("container_pochettes")[0];

		var jsListeSynopsis = new Array();
		var jsListeTitres = new Array();
		var jsListeNotes = new Array();
		<%for (int i = 0; i < filmBean.getListeIdFilm().size(); i++) {%>
		jsListeSynopsis[<%=i%>] = new String("<%=filmBean.getListeSynopsis().get(i)%>"); 
		jsListeTitres[<%=i%>] = new String("<%=filmBean.getListeFilm().get(i)%>");
		jsListeNotes[<%=i%>] = <%=notes.get(i)%>;
		<%}%>
			function toggleBoutonDislike(e) {
				e.target.classList.toggle("boutons_dislike--ouverts--select")
			}
			function toggleBoutonLike(e) {
				e.target.classList.toggle("boutons_like--ouverts--select")
			}
			function toggleBoutonFavoris(e) {
				e.target.classList.toggle("boutons_favoris--ouverts--select");
			}
			function toggleBoutonCheck(e) {
				e.target.classList.toggle("boutons_check--ouverts--select");
			}

			function disparaitrePochette(e) {
				//var i = 0;
				var child = e.target.parentNode.parentNode.parentNode;
				/*
				while( (child = child.previousElementSibling) != null && i<10 ) {
					i++; // index dans la liste des pochettes
					console.log( i) 
					console.log(child);
				}
				
				var container = e.target.parentNode;
				for (var k=i+1; k<container.children.length; k++) {
					container.children[k-1] = container.children[k-1];
				}
				container.children[container.children.length] = null;
				*/
				
				child.className += "--selectionnee";
			}
			function toggleBoutons(e) {
				var bouton_like_pochette = e.target.children[2].children[0];
				var bouton_dislike_pochette = e.target.children[1].children[0];
				var bouton_favoris_pochette = e.target.children[2].children[1];
				var bouton_check_pochette = e.target.children[2].children[2];
				e.target.children[1].classList.toggle("bande_like_gauche--ouverte");
				e.target.children[2].classList.toggle("bande_like_droit--ouverte");
				bouton_dislike_pochette.classList.toggle("boutons_dislike--ouverts");
				bouton_like_pochette.classList.toggle("boutons_like--ouverts");
				bouton_favoris_pochette.classList.toggle("boutons_favoris--ouverts");
				bouton_check_pochette.classList.toggle("boutons_check--ouverts");
				bouton_dislike_pochette.addEventListener('mouseenter', toggleBoutonDislike, false);
				bouton_dislike_pochette.addEventListener('mouseleave', toggleBoutonDislike, false);
				bouton_like_pochette.addEventListener('mouseenter', toggleBoutonLike, false);
				bouton_like_pochette.addEventListener('mouseleave', toggleBoutonLike, false);
				bouton_favoris_pochette.addEventListener('mouseenter', toggleBoutonFavoris, false);
				bouton_favoris_pochette.addEventListener('mouseleave', toggleBoutonFavoris, false);
				bouton_check_pochette.addEventListener('mouseenter', toggleBoutonCheck, false);
				bouton_check_pochette.addEventListener('mouseleave', toggleBoutonCheck, false);
				bouton_dislike_pochette.addEventListener('click', disparaitrePochette, false); // Bouton dislike
				bouton_like_pochette.addEventListener('click', disparaitrePochette, false); // Bouton like
				bouton_favoris_pochette.addEventListener('click', disparaitrePochette, false);
				bouton_check_pochette.addEventListener('click', disparaitrePochette, false);

			};

			function toggleBande(e) {
				document.querySelector(".bande_synopsis").classList
						.toggle("bande_synopsis--ouverte");
			}
			
			function changerTexte(e) {
				var note = document.getElementById("note");
				var texte = document.getElementById("texte");
				var titre_film = document.getElementById("titre_film");
				
				var image_resume = document
						.getElementById("container_image_resume");
				var idTableau = parseInt(new String(
						e.target.firstElementChild.firstElementChild
								.getAttribute("id").substring(4)));
				texte.firstChild.nodeValue = jsListeSynopsis[idTableau];
				titre_film.firstChild.nodeValue = jsListeTitres[idTableau];
				
				
				// console.log(e.target.firstElementChild.firstElementChild);
				image_resume.firstElementChild['src'] = e.target.firstElementChild.firstElementChild.getAttribute("src");
				var pixels =	Math.round(jsListeNotes[idTableau]);
				const root = document.querySelector(":root");
				root.style.setProperty("--translate", 3*pixels + 'px');
			};

			for (var i = 0; i < boites.length; i++) {
				boites[i].addEventListener('mouseenter', toggleBoutons, false);
				boites[i].addEventListener('mouseenter', changerTexte, false);
				boites[i].addEventListener('mouseleave', toggleBoutons, false);
			}

			container.addEventListener('mouseenter', toggleBande, false);
			container.addEventListener('mouseleave', toggleBande, false);
			
		
		</script>
</body>
</html>


