package com.zelda;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.zelda.world.GameMap;
import java.util.concurrent.TimeUnit;

//Les mÃ©thodes sont aprÃ¨s les getters et setters

public class Link extends Entite {
	Sprite sprite;
	Texture linkTexture;
	private float linkX;
	private float linkY;
	private float linkSpeed;
	private float defaultSpeed;
	private float stateTime = 0;
	private int swordDirection;
	
	private SpriteBatch batch=  new SpriteBatch();
	private Texture gameScene= new Texture("com/zelda/world/World.png");
	GameMap map= new GameMap(batch,gameScene);
	
	private Animation<TextureRegion> animBot;
	private Animation<TextureRegion> animTop;
	private Animation<TextureRegion> animLeft;
	private Animation<TextureRegion> animRight;
	private Animation<TextureRegion> animSword;
	private Array<TextureRegion> framesSword = new Array<TextureRegion>();

	private boolean canUp;
	private boolean canDown;
	private boolean canLeft;
	private boolean canRight;
	public Link(float linkX, float linkY, float linkSpeed) {
		super();
		this.posX = linkX;
		this.posY = linkY;
		this.linkSpeed = linkSpeed;
		this.defaultSpeed = linkSpeed;
		this.canUp=true;
		this.canDown=true;
		this.canRight=true;

	}

	public Link() {
		super();
		this.posX = 5920;
		this.posY = 1700;
		this.linkSpeed = 80;
		this.defaultSpeed = linkSpeed;
		this.canUp=true;
		this.canDown=true;
		this.canRight=true;
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

	public int getSwordDirection() {
		return swordDirection;
	}

	public void setSwordDirection(int swordDirection) {
		this.swordDirection = swordDirection;
	}

	public Animation<TextureRegion> getAnimSword() {
		return animSword;
	}

	public void setAnimSword(Animation<TextureRegion> animSword) {
		this.animSword = animSword;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public Array<TextureRegion> getFramesSword() {
		return framesSword;
	}

	public void setFramesSword(Array<TextureRegion> framesSword) {
		this.framesSword = framesSword;
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



	public void create () {
		this.linkTexture = new Texture ("com/zelda/link2.png");
		TextureRegion[][] tmpFrames = TextureRegion.split(linkTexture, 45, 45);
		this.sprite= new Sprite( tmpFrames[0][0] );
	}

	public void render() {
		//Préparation du spriteSheet de Link
		TextureRegion[][] tmpFrames = TextureRegion.split(linkTexture, 45, 45);
		
		//Préparation du SpriteSheet de l'attaque
		Texture animationSwordDown = new Texture("com/zelda/link2-sword_down-left.png"); 
		TextureRegion[][] tmpFramesSword = TextureRegion.split(animationSwordDown, 45, 45);
		TextureRegion[][] tmpFramesSword2 = TextureRegion.split(animationSwordDown, 45, 45);
		TextureRegion[][] tmpFramesSword3 = TextureRegion.split(animationSwordDown, 45, 49);
		TextureRegion[][] tmpFramesSword4 = TextureRegion.split(animationSwordDown, 43, 47);
		

		Texture animationSwordLeft = new Texture("com/zelda/link2-sword_left.png");
		TextureRegion[][] tmpFramesSword5 = TextureRegion.split(animationSwordLeft, 45, 48);
		
		Texture animationSwordUp = new Texture("com/zelda/link2-sword_up.png");
		TextureRegion[][] tmpFramesSword6 = TextureRegion.split(animationSwordUp, 45, animationSwordLeft.getHeight());
		
		Texture animationSwordRight = new Texture("com/zelda/link2-sword_right.png");
		TextureRegion[][] tmpFramesSword7 = TextureRegion.split(animationSwordRight, 44, animationSwordRight.getHeight());

		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
			Array<TextureRegion> framesLeft = new Array<TextureRegion>();
			for (int i = 8; i < 14; i++) {
				framesLeft.add(tmpFrames[1][i]);
			}
			
			
			this.animLeft = new Animation<TextureRegion>(0.08f, framesLeft);
			this.animLeft.setPlayMode(Animation.PlayMode.LOOP);
			float delta = Gdx.graphics.getDeltaTime();
			int currentFrame = animLeft.getKeyFrameIndex(stateTime += delta);
			this.sprite = new Sprite (framesLeft.get(currentFrame));
			
			framesSword.clear();
			framesSword.add(tmpFramesSword5[0][0]);
			framesSword.add(tmpFramesSword5[0][1]);
			
			framesSword.add(tmpFramesSword5[0][2]);
			framesSword.add(tmpFramesSword5[0][3]);
			framesSword.add(tmpFramesSword5[0][4]);
			double toScanX= (linkX)/5.01-1;
			double toScanY=map.getGameSceneHeight()/5-(linkY+sprite.getHeight()/2)/5;
			canLeft = map.analyseImage(map.secretScene,toScanX , toScanY);
			linkSpeed = defaultSpeed;


			if(!canLeft) {
				linkSpeed=0;
			}

			else {canLeft=true;}
			linkX -= Gdx.graphics.getDeltaTime() * linkSpeed;}
		

		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			Array<TextureRegion> framesRight = new Array<TextureRegion>();

			for (int i = 8; i < 14; i++) {
				framesRight.add(tmpFrames[4][i]);
			}
			
			Animation<TextureRegion> animRight = new Animation<TextureRegion>(0.08f, framesRight);
			animRight.setPlayMode(Animation.PlayMode.LOOP);
			float delta = Gdx.graphics.getDeltaTime();
			int currentFrame = animRight.getKeyFrameIndex(stateTime += delta);
			this.sprite = new Sprite (framesRight.get(currentFrame));
			
			framesSword.clear();
			
			framesSword.add(tmpFramesSword7[0][0]);
			framesSword.add(tmpFramesSword7[0][1]);
			framesSword.add(tmpFramesSword7[0][2]);
			framesSword.add(tmpFramesSword7[0][3]);
			framesSword.add(tmpFramesSword7[0][4]);
			double toScanX= (linkX + this.sprite.getWidth())/5;
			double toScanY=map.getGameSceneHeight()/5-(linkY+sprite.getHeight()/2)/5;
			canRight = map.analyseImage(map.secretScene, toScanX, toScanY);
			linkSpeed = defaultSpeed;
			
			

			if(!canRight ) {
				linkSpeed=0;
			}
			else {canRight=true;}
			linkX += Gdx.graphics.getDeltaTime() * linkSpeed;}


		if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
			Array<TextureRegion> framesUp = new Array<TextureRegion>();

			for (int i = 0; i < 8; i++) {
				framesUp.add(tmpFrames[4][i]);
			}
			
			
			Animation<TextureRegion> animUp = new Animation<TextureRegion>(0.08f, framesUp);
			animUp.setPlayMode(Animation.PlayMode.LOOP);
			float delta = Gdx.graphics.getDeltaTime();
			int currentFrame = animUp.getKeyFrameIndex(stateTime += delta);
			this.sprite = new Sprite (framesUp.get(currentFrame));
			
			framesSword.clear();
			
			framesSword.add(tmpFramesSword6[0][0]);
			framesSword.add(tmpFramesSword6[0][1]);
			framesSword.add(tmpFramesSword6[0][2]);
			framesSword.add(tmpFramesSword6[0][3]);
			
			framesSword.add(tmpFramesSword6[0][4]);
			double toScanX= (linkX+sprite.getWidth()/2)/4.996;
			double toScanY=map.getGameSceneHeight()/5-(linkY+this.sprite.getHeight())/5-1;
			canUp = map.analyseImage(map.secretScene, toScanX, toScanY);
			linkSpeed = defaultSpeed;
			

			
			if(!canUp) {
				linkSpeed=0;

			}
			else {canUp=true;}
			linkY += Gdx.graphics.getDeltaTime() * linkSpeed;}


		if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			Array<TextureRegion> framesBot = new Array<TextureRegion>();

			for (int i = 0; i < 8; i++) {
				framesBot.add(tmpFrames[1][i]);
			}
			
			
			Animation<TextureRegion> animBot = new Animation<TextureRegion>(0.08f, framesBot);
			animBot.setPlayMode(Animation.PlayMode.LOOP);
			float delta = Gdx.graphics.getDeltaTime();
			int currentFrame = animBot.getKeyFrameIndex(stateTime += delta);
			this.sprite = new Sprite (framesBot.get(currentFrame));
			
			framesSword.clear();
			
			framesSword.add(tmpFramesSword[0][0]);
			framesSword.add(tmpFramesSword2[0][1]);
			framesSword.add(tmpFramesSword3[0][2]);
			framesSword.add(tmpFramesSword4[0][4]);
			framesSword.add(tmpFramesSword4[0][3]);
			

			double toScanX= ((linkX+sprite.getWidth()/2)/4.996);
			double toScanY= map.getGameSceneHeight()/5-(linkY)/5+1;
			canDown = map.analyseImage(map.secretScene, toScanX, toScanY);
			linkSpeed = defaultSpeed;
			


			if(!canDown) {
				linkSpeed=0;
			}
			
			else {canDown=true;}
			linkY -= Gdx.graphics.getDeltaTime() * linkSpeed;}
		
		if(Gdx.input.isKeyPressed(Keys.E)) {
			
			Sprite initialSprite = this.sprite;
			
			Animation<TextureRegion> animSword = new Animation<TextureRegion>(0.04f, framesSword);
			animSword.setPlayMode(Animation.PlayMode.LOOP);
			float delta = Gdx.graphics.getDeltaTime();
			TextureRegion currentFrame = animSword.getKeyFrame(stateTime += delta, true);
			
			
			this.sprite = new Sprite (currentFrame);
		
			
			
			
		}
	}

}
