package com.zelda;

public class Partie {
	
	private CompteUtilisateur user;
	private int idMap;
	
	
	
	public Partie() {
	}

	public Partie(CompteUtilisateur user, int idMap) {
		this.user = user;
		this.idMap = idMap;
	}

	public CompteUtilisateur getUser() {
		return user;
	}

	public void setUser(CompteUtilisateur user) {
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
