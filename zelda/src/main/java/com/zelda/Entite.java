package com.zelda;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zelda.world.GameMap;

public class Entite extends Actor {

	protected Sprite sprite;
	protected Texture texture;
	protected Rectangle hitbox;
	protected float posX;
	protected float posY;
	protected boolean collision = false;
	protected boolean onAir;
	private SpriteBatch batch;
	private Texture gameScene;
	GameMap map;
	public Entite() {
		this.onAir = false;
		this.batch = new SpriteBatch();
		this.gameScene= new Texture("com/zelda/world/World.png");
		this.map = new GameMap(batch,gameScene);
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

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public boolean isOnAir() {
		return onAir;
	}

	public void setOnAir(boolean onAir) {
		this.onAir = onAir;
	}

	public void onCamera(Link link) {
		if ((link.getPosX()-map.getWidth()/2) > posX || (link.getPosX()+map.getWidth()/2) < posX || (link.getPosY()-map.getHeight()/2) > posY || (link.getPosY()+map.getHeight()/2) < posY) {

			this.onAir = false;
		}
		else {this.onAir = true;}
	}
	
	
	
}
