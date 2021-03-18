package com.zelda;

import java.io.Serializable;

public enum Arme implements Serializable{

	EPEE(1), MASSUE(2);
	
	private int degats;
	
	Arme (int degats)
	{
		this.degats=degats;
	}

	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}
	
	
	
}
