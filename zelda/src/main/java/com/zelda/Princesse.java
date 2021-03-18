package com.zelda;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Princesse extends PNJ{
	
	
	Sprite sprite;
	Texture princessTexture;
	private float width;
	private float height;
	
	public Princesse() {
		super();
		this.posX=7145;
		this.posY=3230;
		this.width=42;
		this.height=50;
	}
	public void create() {
		this.princessTexture = new Texture ("com/zelda/princesse.png");
		TextureRegion[][] tmpFrames = TextureRegion.split(princessTexture, 30, 38);
		this.sprite= new Sprite( tmpFrames[1][0] );
	}
	public void render() {
		
	}
	public Sprite getSprite() {
		return sprite;
	}
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	public Texture getPrincessTexture() {
		return princessTexture;
	}
	public void setPrincessTexture(Texture princessTexture) {
		this.princessTexture = princessTexture;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	
	
}
