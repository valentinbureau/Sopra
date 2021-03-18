package com.zelda;

public class Partie {
	
	private Joueur user;
	private int idMap;
	
	
	
	public Partie() {
	}

	public Partie(Joueur user, int idMap) {
		this.user = user;
		this.idMap = idMap;
	}

	public Joueur getUser() {
		return user;
	}

	public void setUser(Joueur user) {
		this.user = user;
	}

	public int getIdMap() {
		return idMap;
	}

	public void setIdMap(int idMap) {
		this.idMap = idMap;
	}

	@Override
	public String toString() {
		return "Partie [user=" + user + ", idMap=" + idMap + "]";
	}
	
	
	
	

}
