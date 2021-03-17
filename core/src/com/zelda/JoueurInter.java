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
	private float stateTime = 0;
	GameMap map= new GameMap();
	
	private Animation<TextureRegion> animBot;
	private Animation<TextureRegion> animTop;
	private Animation<TextureRegion> animLeft;
	private Animation<TextureRegion> animRight;
	
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
	
	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}
	
	public Animation<TextureRegion> getAnimBot() {
		return animBot;
	}

	public void setAnimBot(Animation<TextureRegion> animBot) {
		this.animBot = animBot;
	}

	public Animation<TextureRegion> getAnimTop() {
		return animTop;
	}

	public void setAnimTop(Animation<TextureRegion> animTop) {
		this.animTop = animTop;
	}

	public Animation<TextureRegion> getAnimLeft() {
		return animLeft;
	}

	public void setAnimLeft(Animation<TextureRegion> animLeft) {
		this.animLeft = animLeft;
	}

	public Animation<TextureRegion> getAnimRight() {
		return animRight;
	}

	public void setAnimRight(Animation<TextureRegion> animRight) {
		this.animRight = animRight;
	}

	public void create () {
		this.linkTexture = new Texture ("link.png");
		this.sprite= new Sprite( linkTexture );
	}
	
	public void render() {
		TextureRegion[][] tmpFrames = TextureRegion.split(linkTexture, linkTexture.getWidth()/10, linkTexture.getHeight()/8);
		
		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
			Array<TextureRegion> framesLeft = new Array<TextureRegion>();

			for (int i = 0; i < 10; i++) {
				framesLeft.add(tmpFrames[5][i]);
			}
			
			
			this.animLeft = new Animation<TextureRegion>(0.08f, framesLeft);
			this.animLeft.setPlayMode(Animation.PlayMode.LOOP);
			float delta = Gdx.graphics.getDeltaTime();
			int currentFrame = animLeft.getKeyFrameIndex(stateTime += delta);
			this.sprite = new Sprite (tmpFrames[5][currentFrame]);
			
			linkSpeed = defaultSpeed;
			if(linkX < 0 || !canLeft) {
				linkSpeed=0;
			}
			else {canLeft=true;}
			linkX -= Gdx.graphics.getDeltaTime() * linkSpeed;}
		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			Array<TextureRegion> framesRight = new Array<TextureRegion>();

			for (int i = 0; i < 10; i++) {
				framesRight.add(tmpFrames[7][i]);
			}
			
			
			Animation<TextureRegion> animRight = new Animation<TextureRegion>(0.08f, framesRight);
			animRight.setPlayMode(Animation.PlayMode.LOOP);
			float delta = Gdx.graphics.getDeltaTime();
			int currentFrame = animRight.getKeyFrameIndex(stateTime += delta);
			this.sprite = new Sprite (tmpFrames[7][currentFrame]);
			
			linkSpeed = defaultSpeed;
			if(linkX > map.getWidth()-this.linkTexture.getWidth() || !canRight) {
				linkSpeed=0;
			}
			else {canRight=true;}
				linkX += Gdx.graphics.getDeltaTime() * linkSpeed;}
		if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
			Array<TextureRegion> framesUp = new Array<TextureRegion>();

			for (int i = 0; i < 10; i++) {
				framesUp.add(tmpFrames[6][i]);
			}
			
			
			Animation<TextureRegion> animUp = new Animation<TextureRegion>(0.08f, framesUp);
			animUp.setPlayMode(Animation.PlayMode.LOOP);
			float delta = Gdx.graphics.getDeltaTime();
			int currentFrame = animUp.getKeyFrameIndex(stateTime += delta);
			this.sprite = new Sprite (tmpFrames[6][currentFrame]);
			
			linkSpeed = defaultSpeed;
			if(linkY >  map.getHeight()-this.linkTexture.getHeight() || !canUp) {
				linkSpeed=0;
			}
			else {canUp=true;}
				linkY += Gdx.graphics.getDeltaTime() * linkSpeed;}
		if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			Array<TextureRegion> framesBot = new Array<TextureRegion>();

			for (int i = 0; i < 10; i++) {
				framesBot.add(tmpFrames[4][i]);
			}
			
			
			Animation<TextureRegion> animBot = new Animation<TextureRegion>(0.08f, framesBot);
			animBot.setPlayMode(Animation.PlayMode.LOOP);
			float delta = Gdx.graphics.getDeltaTime();
			int currentFrame = animBot.getKeyFrameIndex(stateTime += delta);
			this.sprite = new Sprite (tmpFrames[4][currentFrame]);
			
			linkSpeed = defaultSpeed;
			if(linkY < 0 || !canDown) {
				linkSpeed=0;
			}
			else {canDown=true;}
				linkY -= Gdx.graphics.getDeltaTime() * linkSpeed;}
	}
	
}
