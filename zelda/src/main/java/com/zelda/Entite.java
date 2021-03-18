package com.zelda;

public class Entite {
	protected float posX;
	protected float posY;
	public Entite() {
		
	}	
	
	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public void setPosition( float axisX, float axisY) {
		this.posX=axisX;
		this.posY=axisY;
	}
}
