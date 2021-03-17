package com.zelda;

import java.time.LocalTime;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.zelda.world.GameMap;

public class Monstre extends Entite{
	
	Sprite sprite;
	Texture monstreTexture;
	private float monstreX;
	private float monstreY;
	private float monstreSpeed;
	private float defaultSpeed;
	private float stateTime = 0;
	private static LocalTime tempsDeplacement = LocalTime.now();
	
	private boolean canUp;
	private boolean canDown;
	private boolean canLeft;
	private boolean canRight;
	private Animation<TextureRegion> animBot;
	private Animation<TextureRegion> animTop;
	private Animation<TextureRegion> animLeft;
	private Animation<TextureRegion> animRight;
	private TextureRegion[][] tmpFrames;
	private int monsterType;
	
	private SpriteBatch batch=  new SpriteBatch();
	private Texture gameScene= new Texture("com/zelda/world/World.png");
	GameMap map= new GameMap(batch,gameScene);
	
	
	public Monstre() {
		super();
	}
	
	public Monstre(float monstreX, float monstreY, float monstreSpeed, int monsterType) {
		super();
		this.monstreX = monstreX;
		this.monstreY = monstreY;
		this.monstreSpeed = monstreSpeed;
		this.monsterType = monsterType;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Texture getMonsterTexture() {
		return monstreTexture;
	}

	public void setMonstreTexture(Texture monstreTexture) {
		this.monstreTexture = monstreTexture;
	}

	public float getMonstreX() {
		return monstreX;
	}

	public void setMonstreX(float monstreX) {
		this.monstreX = monstreX;
	}

	public float getMonstreY() {
		return monstreY;
	}

	public void setMonstreY(float monstreY) {
		this.monstreY = monstreY;
	}

	public float getMonstreSpeed() {
		return monstreSpeed;
	}

	public void setMonstreSpeed(float monstreSpeed) {
		this.monstreSpeed = monstreSpeed;
	}

	public float getDefaultSpeed() {
		return defaultSpeed;
	}

	public void setDefaultSpeed(float defaultSpeed) {
		this.defaultSpeed = defaultSpeed;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
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

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public Texture getGameScene() {
		return gameScene;
	}

	public void setGameScene(Texture gameScene) {
		this.gameScene = gameScene;
	}

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}
	
	public TextureRegion[][] getTmpFrames() {
		return tmpFrames;
	}

	public void setTmpFrames(TextureRegion[][] tmpFrames) {
		this.tmpFrames = tmpFrames;
	}

	
	public int getMonsterType() {
		return monsterType;
	}

	public void setMonsterType(int monsterType) {
		this.monsterType = monsterType;
	}

	public static LocalTime getTempsDeplacement() {
		return tempsDeplacement;
	}

	public static void setTempsDeplacement(LocalTime tempsDeplacement) {
		Monstre.tempsDeplacement = tempsDeplacement;
	}

	public void create () {
		this.monstreTexture = new Texture("com/zelda/Monster-sprites.png");
		this.tmpFrames = TextureRegion.split(monstreTexture, monstreTexture.getWidth()/12, monstreTexture.getHeight()/8);
		int indexSprite = 3*monsterType -3 ;
		if (monsterType < 4) {
			this.sprite = new Sprite (tmpFrames[2][indexSprite]);
		}
		else {
			this.sprite = new Sprite (tmpFrames[6][indexSprite]);
		}
	}
	
	public void render() {
		
		
		int deplacement = (int) (Math.random() * 4);
		int indexSprite = 3*monsterType -3 ;
		int lineSprite = (this.monsterType < 4) ? 0 : 4;
		LocalTime now = LocalTime.now();
		
		
		if (now.isAfter(tempsDeplacement.plusNanos(5*10^8))) {
			if (deplacement == 1) {
				Array<TextureRegion> framesLeft = new Array<TextureRegion>();
				for (int i = indexSprite; i < indexSprite + 3; i++) {
					framesLeft.add(tmpFrames[lineSprite+3][i]);
				}
				
				this.animLeft = new Animation<TextureRegion>(0.08f, framesLeft);
				this.animLeft.setPlayMode(Animation.PlayMode.LOOP);
				float delta = Gdx.graphics.getDeltaTime();
				int currentFrame = animLeft.getKeyFrameIndex(stateTime += delta);
				this.sprite = new Sprite (framesLeft.get(currentFrame));
				
				double toScanX= (monstreX)/5.01-1;
				double toScanY=map.getGameSceneHeight()/5-(monstreY+sprite.getHeight()/2)/5;
				canLeft = map.analyseImage(map.secretScene,toScanX , toScanY);
				
				if(!canLeft) {
					monstreSpeed = 0;
				}
				else {
					monstreX -= Gdx.graphics.getDeltaTime() * monstreSpeed;
					tempsDeplacement = LocalTime.now();
				}
			}
			else if (deplacement == 2) {
				Array<TextureRegion> framesRight = new Array<TextureRegion>();
				for (int i = indexSprite; i < indexSprite + 3; i++) {
					framesRight.add(tmpFrames[lineSprite+1][i]);
				}
				
				this.animRight = new Animation<TextureRegion>(0.08f, framesRight);
				this.animRight.setPlayMode(Animation.PlayMode.LOOP);
				float delta = Gdx.graphics.getDeltaTime();
				int currentFrame = animRight.getKeyFrameIndex(stateTime += delta);
				this.sprite = new Sprite (framesRight.get(currentFrame));
				
				double toScanX= (monstreX + this.sprite.getWidth())/4.987+1;
				double toScanY=map.getGameSceneHeight()/5-(monstreY+sprite.getHeight()/2)/5;
				canRight = map.analyseImage(map.secretScene, toScanX, toScanY);
				
				if(!canRight) {
					monstreSpeed = 0;
				}
				else {
					monstreX += Gdx.graphics.getDeltaTime() * monstreSpeed;
					tempsDeplacement = LocalTime.now();
				}
			}
			else if (deplacement == 3) {
				Array<TextureRegion> framesUp = new Array<TextureRegion>();
				for (int i = indexSprite; i < indexSprite + 3; i++) {
					framesUp.add(tmpFrames[lineSprite+1][i]);
				}
				
				this.animTop = new Animation<TextureRegion>(0.08f, framesUp);
				this.animTop.setPlayMode(Animation.PlayMode.LOOP);
				float delta = Gdx.graphics.getDeltaTime();
				int currentFrame = animTop.getKeyFrameIndex(stateTime += delta);
				this.sprite = new Sprite (framesUp.get(currentFrame));
				
				double toScanX= (monstreX+sprite.getWidth()/2)/4.996;
				double toScanY=map.getGameSceneHeight()/5-(monstreY+this.sprite.getHeight())/5-1;
				canUp = map.analyseImage(map.secretScene, toScanX, toScanY);
				
				if(!canRight) {
					monstreSpeed = 0;
				}
				else {
					monstreY += Gdx.graphics.getDeltaTime() * monstreSpeed;
					tempsDeplacement = LocalTime.now();
				}
			}
			else if (deplacement == 4) {
				Array<TextureRegion> framesBot = new Array<TextureRegion>();
				for (int i = indexSprite; i < indexSprite + 3; i++) {
					framesBot.add(tmpFrames[lineSprite+1][i]);
				}
				
				this.animBot = new Animation<TextureRegion>(0.08f, framesBot);
				this.animBot.setPlayMode(Animation.PlayMode.LOOP);
				float delta = Gdx.graphics.getDeltaTime();
				int currentFrame = animBot.getKeyFrameIndex(stateTime += delta);
				this.sprite = new Sprite (framesBot.get(currentFrame));
				
				double toScanX= ((monstreX+sprite.getWidth()/2)/4.996);
				double toScanY= map.getGameSceneHeight()/5-(monstreY)/5+1;
				canDown = map.analyseImage(map.secretScene, toScanX, toScanY);
				
				if(!canRight) {
					monstreSpeed = 0;
				}
				else {
					monstreY -= Gdx.graphics.getDeltaTime() * monstreSpeed;
					tempsDeplacement = LocalTime.now();
				}
			}
		}
	}
	
}
