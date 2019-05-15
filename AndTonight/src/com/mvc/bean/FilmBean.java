package com.mvc.bean;


public class FilmBean {

	private String NomFilm;
	private Integer IdFilm;
	private String Synopsis;
	private Integer IdPochette;
	
	public String getNomFilm() {
		return NomFilm;
	}
	public void setNomFilm(String nomFilm) {
		NomFilm = nomFilm;
	}
	public Integer getIdFilm() {
		return IdFilm;
	}
	public void setIdFilm(Integer idFilm) {
		IdFilm = idFilm;
	}
	public String getSynopsis() {
		return Synopsis;
	}
	public void setSynopsis(String synopsis) {
		Synopsis = synopsis;
	}
	public Integer getIdPochette() {
		return IdPochette;
	}
	public void setIdPochette(Integer idPochette) {
		IdPochette = idPochette;
	}

}
