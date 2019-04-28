<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Associer une personne et une adresse</title>
</head>
<body>

<%@ page import="java.util.*, pack.*" %>


<h1> Associer une personne et une adresse</h1>


<form method="get" action="serv">
Choisir la personne: <br>
<% 
Collection<Personne> personnes = (Collection<Personne>) request.getAttribute("listepersonnes");
for(Personne p: personnes){
	int id = p.getId();
	String s = p.getNom() + " " + p.getPrenom();

%>
<input type="radio" name="idp" value="<%=id %>">
<%=s %><br>
<%} %>


Choisir l'adresse: <br>
<% 
Collection<Adresse> adresses = (Collection<Adresse>) request.getAttribute("listeadresses");
for(Adresse a: adresses){
	int id = a.getId();
	String s = a.getrue() + ", " + a.getVille();

%>
<input type="radio" name="ida" value="<%=id %>">
<%=s %><br>
<%} %>


<input type="submit" value="ok">
<input type="hidden" name="op" value="associer">
</form>

<p>
<form action="index.html" name="Retour vers l'index">
<input type="submit" name="ok" value="Retour vers l'index">
</form>
</p>

</body>
</html>