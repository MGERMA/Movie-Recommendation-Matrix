package com.mvc.bean;

import java.text.DateFormat;

public class CommentaireBean {
	private UserBean commentateur;
	private String texte_commentaire;
	private FilmBean film;
	private DateFormat date;


	public DateFormat getDate() {
		return date;
	}

	public void setDate(DateFormat date) {
		this.date = date;
	}

	public CommentaireBean() {
		// TODO Auto-generated constructor stub
	}
	
	public FilmBean getFilm() {
		return film;
	}

	public void setFilm(FilmBean film) {
		this.film = film;
	}
	
	public void setTexte(String texte) {
		texte_commentaire = texte;
	}
	
	public String getTexte() {
		return texte_commentaire;
	}
	
	public void setCommentateur(UserBean comm) {
		commentateur = comm;
	}
	
	public UserBean getCommentateur() {
		return commentateur;
	}
}
