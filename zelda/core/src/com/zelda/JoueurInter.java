package com.zelda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.zelda.world.GameMap;

//Les méthodes sont après les getters et setters

public class JoueurInter {
	Sprite sprite;
	Texture linkTexture;
	private float linkX;
	private float linkY;
	private float linkSpeed;
	private float defaultSpeed;
	private float stateTime;
	GameMap map= new GameMap();
	
	private Animation animBot;
	private Animation animTop;
	private Animation animLeft;
	private Animation animRight;

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

	public float getDefaultSpeed() {
		return defaultSpeed;
	}

	public void setDefaultSpeed(float defaultSpeed) {
		this.defaultSpeed = defaultSpeed;
	}

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}
	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	public Animation getAnimBot() {
		return animBot;
	}

	public void setAnimBot(Animation animBot) {
		this.animBot = animBot;
	}

	public Animation getAnimTop() {
		return animTop;
	}

	public void setAnimTop(Animation animTop) {
		this.animTop = animTop;
	}

	public Animation getAnimLeft() {
		return animLeft;
	}

	public void setAnimLeft(Animation animLeft) {
		this.animLeft = animLeft;
	}

	public Animation getAnimRight() {
		return animRight;
	}

	public void setAnimRight(Animation animRight) {
		this.animRight = animRight;
	}


	public boolean isCanUp() {
		return canUp;
	}

	public void setCanUp(boolean canUp) {
		this.canUp = canUp;
	}

	public boolean isCanDown() {
		return canDown;
	}

	public void setCanDown(boolean canDown) {
		this.canDown = canDown;
	}

	public boolean isCanLeft() {
		return canLeft;
	}

	public void setCanLeft(boolean canLeft) {
		this.canLeft = canLeft;
	}

	public boolean isCanRight() {
		return canRight;
	}

	public void setCanRight(boolean canRight) {
		this.canRight = canRight;
	}


	public void create() {

		this.linkTexture = new Texture ("SpriteSheet_Zelda3.png");

		TextureRegion[][] tmpFrames = TextureRegion.split(linkTexture, linkTexture.getWidth()/10, linkTexture.getHeight()/8);
		this.sprite= new Sprite( tmpFrames[0][0] );
	}

	public void render() {
		TextureRegion[][] tmpFrames = TextureRegion.split(linkTexture, linkTexture.getWidth()/10, linkTexture.getHeight()/8);
		this.stateTime = 0;

		

		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
			Array<TextureRegion> framesLeft = new Array<TextureRegion>();

			for (int i = 0; i < 10; i++) {
				framesLeft.add(tmpFrames[5][i]);
			}

			this.animLeft = new Animation<TextureRegion>(0.1f, tmpFrames[5][1], tmpFrames[5][2]);
			this.animLeft.setPlayMode(Animation.PlayMode.LOOP);
			int currentFrame = animLeft.getKeyFrameIndex(stateTime += Gdx.graphics.getDeltaTime());
			this.sprite = new Sprite (tmpFrames[5][1]);
			
			canLeft = map.analyseImage(map.secretScene, (int)linkX-1, (int)linkY);
			//System.out.println(canLeft);

			linkSpeed = defaultSpeed;
			System.out.println(canLeft);
			if(linkX < 0 || !canLeft) {
				linkSpeed=0;
			}
			else {canLeft=true;}
			linkX -= Gdx.graphics.getDeltaTime() * linkSpeed;}

		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			this.sprite = new Sprite (tmpFrames[7][1]);
			
			canRight = map.analyseImage(map.secretScene, (int)linkX + (int)this.sprite.getWidth(), (int)linkY);
			linkSpeed = defaultSpeed;
			if(linkX > map.getWidth()-this.sprite.getWidth() || !canRight ) {
				linkSpeed=0;
			}
			else {canRight=true;}
			linkX += Gdx.graphics.getDeltaTime() * linkSpeed;}


		if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
			this.sprite = new Sprite (tmpFrames[6][1]);
			canUp = map.analyseImage(map.secretScene, (int)linkX, (int)linkY + (int) this.sprite.getHeight()-10);
			linkSpeed = defaultSpeed;
			if(linkY >  map.getHeight()-this.sprite.getHeight() || !canUp) {
				linkSpeed=0;
			}
			else {canUp=true;}
			linkY += Gdx.graphics.getDeltaTime() * linkSpeed;}


		if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			this.sprite = new Sprite (tmpFrames[4][1]);
			canDown = map.analyseImage(map.secretScene, (int)linkX, (int)linkY -1);
			linkSpeed = defaultSpeed;
			if(linkY < 0 || !canDown) {
				linkSpeed=0;
			}
			else {canDown=true;}
			linkY -= Gdx.graphics.getDeltaTime() * linkSpeed;}
	}

}
