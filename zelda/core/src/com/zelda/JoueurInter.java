package com.zelda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

//Les méthodes sont après les getters et setters

public class JoueurInter {
	Sprite sprite;
	Texture linkTexture;
	private float linkX;
	private float linkY;
	private float linkSpeed;
	
	public JoueurInter(float linkX, float linkY, float linkSpeed) {
		super();
		this.linkX = linkX;
		this.linkY = linkY;
		this.linkSpeed = linkSpeed;
	}

	public JoueurInter() {

	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Texture getLinkTexture() {
		return linkTexture;
	}

	public void setLinkTexture(Texture linkTexture) {
		this.linkTexture = linkTexture;
	}

	public float getLinkX() {
		return linkX;
	}

	public void setLinkX(float linkX) {
		this.linkX = linkX;
	}

	public float getLinkY() {
		return linkY;
	}

	public void setLinkY(float linkY) {
		this.linkY = linkY;
	}

	public float getLinkSpeed() {
		return linkSpeed;
	}

	public void setLinkSpeed(float linkSpeed) {
		this.linkSpeed = linkSpeed;
	}

	public void create () {
		this.sprite= new Sprite( new Texture ("link.png"));
	}
	
	public void render() {
		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) 
			   linkX -= Gdx.graphics.getDeltaTime() * linkSpeed;
		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) 
				linkX += Gdx.graphics.getDeltaTime() * linkSpeed;
		if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) 
				linkY += Gdx.graphics.getDeltaTime() * linkSpeed;
		if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) 
				linkY -= Gdx.graphics.getDeltaTime() * linkSpeed;
	}
	
}
