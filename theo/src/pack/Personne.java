package pack;

import java.util.ArrayList;
import java.util.Collection;

public class Personne {
	
	private int id;
	private String nom;
	private String prenom;
	private Collection<Adresse> adresses = new ArrayList<Adresse>();;
	
	public Personne(){
	}
	
	
	public void setId(int id){
		this.id = id;
	}
	
	
	public void setNom(String nom){
		this.nom = nom;
	}
	
	public void setPrenom(String prenom){
		this.prenom = prenom;
	}
	
	public void addAdresse(Adresse adresse){
		this.adresses.add(adresse);
	}
	
	public int getId(){
		return this.id;
	}
	
	
	
	public String getNom(){
		return this.nom;
	}
	
	
	public String getPrenom(){
		return this.prenom;
	}
	
	public Collection<Adresse> getAdresses(){
		return this.adresses;
	}
	
	

}
