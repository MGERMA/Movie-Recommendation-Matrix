package com.mvc.bean;

import java.util.ArrayList;

public class FilmBean {

	private ArrayList<String> ListeFilm;
	private ArrayList<Integer> ListeIdFilm;
	private ArrayList<String> ListeSynopsis;
	private ArrayList<Integer> ListeIdPochette;

	public ArrayList<String> getListeFilm() {
		return ListeFilm;
	}

	public void setListeFilm(ArrayList<String> listeFilm) {
		ListeFilm = listeFilm;
	}

	public ArrayList<Integer> getListeIdFilm() {
		return ListeIdFilm;
	}

	public void setListeIdFilm(ArrayList<Integer> listeIdFilm) {
		ListeIdFilm = listeIdFilm;
	}

	public ArrayList<String> getListeSynopsis() {
		return ListeSynopsis;
	}

	public void setListeSynopsis(ArrayList<String> listeSynopsis) {
		ListeSynopsis = listeSynopsis;
	}

	public ArrayList<Integer> getListeIdPochette() {
		return ListeIdPochette;
	}

	public void setListeIdPochette(ArrayList<Integer> listeIdPochette) {
		ListeIdPochette = listeIdPochette;
	}
}
