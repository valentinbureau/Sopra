package com.zelda;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Objet extends Entite {
	
	private Sprite sprite;

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Objet() {
		super();
	}
	
	public Objet(float posX, float posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}
	
	public void create() {
		Texture epee = new Texture("com/zelda/epee-zelda.png");
		this.sprite = new Sprite(epee);
		this.hitbox = new Rectangle(posX, posY, sprite.getWidth(), sprite.getHeight());
	}
	
	public void render(Link link) {
		
	}
}
