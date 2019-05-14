<pre><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
body {
	font-family: 'Andale Mono', monospace;
	color: white;
	background: #141414;
}
</style>
<title>AndTonight</title>
<script> 
function validate()
{ 
var nomFilm = document.form.nomFilm.value;
var Synopsis = document.form.Synopsis.value;
var idpochette = document.form.idpochette.value; 
var nomPochette = document.form.nomPochette.value;
if (nomFilm==null || nomFilm=="")
{ 
alert("Veuillez entrer un titre pour le film"); 
return false; 
}
</script> 
</head>
<body>
<center><h2>Ajouter un Film dans notre base de données</h2></center>
<form name="form" action="AjoutFilmServlet" method="post" onsubmit="return validate()">
<table align="center">
<tr>
<td>Titre du film</td>
<td><input type="text" name="nomFilm" /></td>
</tr>
<tr>
<td>Email</td>
<td>  <textarea name="Synopsis" rows="10" cols="30">Entrer ici le synopsis du film</textarea>
</td>
</tr>
<tr>
<td>nom de l'image pochette</td>
<td><input type="text" name="nomPochette" /></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="Ajouter !"></input><input
type="reset" value="Reset"></input></td>
</tr>
</table>
</form>
</body>
</html>