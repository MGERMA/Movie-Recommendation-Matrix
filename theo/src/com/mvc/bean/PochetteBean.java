package com.mvc.bean;

import java.util.ArrayList;

public class PochetteBean {
	private ArrayList<String> ListePochette;
	
	
	public PochetteBean(){	
		this.ListePochette = new ArrayList<String>();
	}
	public ArrayList<String> getListePochette() {
		return ListePochette;
	}

	public void setListePochette(ArrayList<String> listePochette) {
		ListePochette = listePochette;
	}
}



