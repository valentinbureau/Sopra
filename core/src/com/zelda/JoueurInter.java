package com.zelda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.zelda.world.GameMap;

//Les méthodes sont après les getters et setters

public class JoueurInter {
	Sprite sprite;
	Texture linkTexture;
	private float linkX;
	private float linkY;
	private float linkSpeed;
	private float defaultSpeed;
	GameMap map= new GameMap();
	
	private boolean canUp;
	private boolean canDown;
	private boolean canLeft;
	private boolean canRight;
	public JoueurInter(float linkX, float linkY, float linkSpeed) {
		super();
		this.linkX = linkX;
		this.linkY = linkY;
		this.linkSpeed = linkSpeed;
		this.defaultSpeed = linkSpeed;
		this.canUp=true;
		this.canDown=true;
		this.canLeft=true;
		this.canRight=true;
		
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
		this.linkTexture = new Texture ("link.png");
		this.sprite= new Sprite( linkTexture );
	}
	
	public void render() {
		
		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
			linkSpeed = defaultSpeed;
			if(linkX < 0 || !canLeft) {
				linkSpeed=0;
			}
			else {canLeft=true;}
			linkX -= Gdx.graphics.getDeltaTime() * linkSpeed;}
		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			linkSpeed = defaultSpeed;
			if(linkX > map.getWidth()-this.linkTexture.getWidth() || !canRight) {
				linkSpeed=0;
			}
			else {canRight=true;}
				linkX += Gdx.graphics.getDeltaTime() * linkSpeed;}
		if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
			linkSpeed = defaultSpeed;
			if(linkY >  map.getHeight()-this.linkTexture.getHeight() || !canUp) {
				linkSpeed=0;
			}
			else {canUp=true;}
				linkY += Gdx.graphics.getDeltaTime() * linkSpeed;}
		if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			linkSpeed = defaultSpeed;
			if(linkY < 0 || !canDown) {
				linkSpeed=0;
			}
			else {canDown=true;}
				linkY -= Gdx.graphics.getDeltaTime() * linkSpeed;}
	}
	
}
