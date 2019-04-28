package pack;

public class Adresse {
	
	private int id;
	private String rue;
	private String ville;
	
	public Adresse(){
	}
	
	
	public void setId(int id){
		this.id = id;
	}
	
	
	public void setRue(String rue){
		this.rue = rue;
	}
	
	public void setVille(String ville){
		this.ville = ville;
	}
	
	public int getId(){
		return this.id;
	}
	
	
	
	public String getrue(){
		return this.rue;
	}
	
	
	public String getVille(){
		return this.ville;
	}

}
