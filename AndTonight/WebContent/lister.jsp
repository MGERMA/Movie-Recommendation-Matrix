<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des films</title>
</head>
<body>

	<h1>Liste des fims de la base de donnée</h1>



	
	<%@ page import="java.util.*, com.mvc.controller.*"%>
	<%@ page import="java.util.*, com.mvc.bean.*"%>

	<%
		FilmBean filmBean = (FilmBean) session.getAttribute("film");
		for (String p : filmBean.getListeFilm()) {
			String s = p;
	%>

	<br>
	<%=s%><br>


	<%
		};
	%>

	<p>
	<form action="Home.jsp" name="Home">
		<input type="submit" name="ok" value="Home">
	</form>
	</p>

</body>
</html>