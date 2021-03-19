package com.zelda;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Princesse extends PNJ{
	
	private float width;
	private float height;
	
	public Princesse() {
		super();
		this.posX=7145;
		this.posY=3200;
		this.width=60;
		this.height=60;
	}
	public void create() {
		texture = new Texture ("com/zelda/princesse.png");
		TextureRegion[][] tmpFrames = TextureRegion.split(texture, 30, 38);
		sprite= new Sprite( tmpFrames[1][0] );
		hitbox = new Rectangle(posX, posY, 30, 30);
	}
	public void render(Link link) {
		
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
