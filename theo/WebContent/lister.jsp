<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulter l'annuaire</title>
</head>
<body>

<h1> Annuaire</h1>



<%@ page import="java.util.*, pack.*" %>

<% 
Collection<String> nomPochette = (Collection<String>) request.getAttribute("listePochettes");
for(String p: nomPochette){
	String s = p;

%>

<br> <%=s %><br>


<%} 
%>

<p>
<form action="index.html" name="Retour vers l'index">
<input type="submit" name="ok" value="Retour vers l'index">
</form>
</p>

</body>
</html>