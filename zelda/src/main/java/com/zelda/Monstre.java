package com.zelda;

import java.time.LocalTime;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.zelda.world.GameMap;

public class Monstre extends Entite{
	
	private float monstreSpeed;
	private float defaultSpeed;
	private float stateTime = 0;
	private static LocalTime tempsDeplacement = LocalTime.now();
	private int deplacement = 1;

	
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
	
	public Monstre(float posX, float posY, float monstreSpeed, int monsterType) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.monstreSpeed = monstreSpeed;
		this.monsterType = monsterType;
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

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public int getDeplacement() {
		return deplacement;
	}
	public void setDeplacement(int deplacement) {
		this.deplacement = deplacement;
	}
	public void create () {
		this.texture = new Texture("com/zelda/Monster-sprites.png");
		this.tmpFrames = TextureRegion.split(texture, texture.getWidth()/12, texture.getHeight()/8);
		int indexSprite = (this.monsterType < 5) ? (3*monsterType -3) : (3*(monsterType-4) -3) ;
		if (monsterType < 5) {
			this.sprite = new Sprite (tmpFrames[2][indexSprite]);
		}
		else {
			this.sprite = new Sprite (tmpFrames[6][indexSprite]);
		}
		this.hitbox = new Rectangle(posX, posY, sprite.getWidth(), sprite.getHeight());
	}
	
	public void render(Link link) {
		
		
//		int deplacement = (int) (Math.random() * 4);
		int indexSprite = (this.monsterType < 5) ? (3*monsterType -3) : (3*(monsterType-4) -3) ;
		int lineSprite = (this.monsterType < 5) ? 0 : 4;
		LocalTime now = LocalTime.now();
		
		
		if (now.isAfter(tempsDeplacement.plusNanos(100000000))) {
////			if (deplacement == 1) {
//				Array<TextureRegion> framesLeft = new Array<TextureRegion>();
//				for (int i = indexSprite; i < indexSprite + 3; i++) {
//					framesLeft.add(tmpFrames[lineSprite+3][i]);
//				}
//				
//				this.animLeft = new Animation<TextureRegion>(0.08f, framesLeft);
//				this.animLeft.setPlayMode(Animation.PlayMode.LOOP);
//				float delta = Gdx.graphics.getDeltaTime();
//				int currentFrame = animLeft.getKeyFrameIndex(stateTime += delta);
//				this.sprite = new Sprite (framesLeft.get(currentFrame));
//				
//				double toScanX= (monstreX)/5.01-1;
//				double toScanY=map.getGameSceneHeight()/5-(monstreY+sprite.getHeight()/2)/5;
//				canLeft = map.analyseImage(map.secretScene,toScanX , toScanY);
//				
//				if(!canLeft) {
//					monstreSpeed = 0;
//				}
//				else {
//					monstreX -= Gdx.graphics.getDeltaTime() * monstreSpeed;
//					tempsDeplacement = LocalTime.now();
//					hitbox.setPosition(monstreX, monstreY);
//					if (collision == false) {
////					System.out.println(hitbox.overlaps(link.getHitbox()));
//					collision = hitbox.overlaps(link.getHitbox());
//					}
//					if(collision) {
//						monstreX += Gdx.graphics.getDeltaTime() * monstreSpeed;
//						hitbox.setPosition(monstreX, monstreY);
//					}
//				}
////			}
////			else if (deplacement == 2) {
//				Array<TextureRegion> framesRight = new Array<TextureRegion>();
//				for (int i = indexSprite; i < indexSprite + 3; i++) {
//					framesRight.add(tmpFrames[lineSprite+1][i]);
//				}
//				
//				this.animRight = new Animation<TextureRegion>(0.08f, framesRight);
//				this.animRight.setPlayMode(Animation.PlayMode.LOOP);
//				float delta = Gdx.graphics.getDeltaTime();
//				int currentFrame = animRight.getKeyFrameIndex(stateTime += delta);
//				this.sprite = new Sprite (framesRight.get(currentFrame));
//				
//				double toScanX= (monstreX + this.sprite.getWidth())/4.987+1;
//				double toScanY=map.getGameSceneHeight()/5-(monstreY+sprite.getHeight()/2)/5;
//				canRight = map.analyseImage(map.secretScene, toScanX, toScanY);
//				
//				if(!canRight) {
//					monstreSpeed = 0;
//				}
//				else {
//					monstreX += Gdx.graphics.getDeltaTime() * monstreSpeed;
//					tempsDeplacement = LocalTime.now();
//					hitbox.setPosition(monstreX, monstreY);
//					if (collision == false) {
////					System.out.println(hitbox.overlaps(link.getHitbox()));
//					collision = hitbox.overlaps(link.getHitbox());
//					}
//					if(collision) {
//						monstreX -= Gdx.graphics.getDeltaTime() * monstreSpeed;
//						hitbox.setPosition(monstreX, monstreY);
//					}
//				}
////			}
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
				
				double toScanX= (posX)/5.01-1;
				double toScanY=map.getGameSceneHeight()/5-(posY+sprite.getHeight()/2)/5;
				canLeft = map.analyseImage(map.secretScene,toScanX , toScanY);
				
				if(!canLeft) {
					monstreSpeed = 0;
				}
				else {
					posX -= Gdx.graphics.getDeltaTime() * monstreSpeed;
					tempsDeplacement = LocalTime.now();
					hitbox.setPosition(posX, posY);
					if (collision == false) {
					//System.out.println(hitbox.overlaps(link.getHitbox()));
					collision = hitbox.overlaps(link.getHitbox());
					}
					if(collision) {
						posX += Gdx.graphics.getDeltaTime() * monstreSpeed;
						hitbox.setPosition(posX, posY);
					}
				}
			}
			else if (deplacement == 2) {
				Array<TextureRegion> framesBot = new Array<TextureRegion>();
				for (int i = indexSprite; i < indexSprite + 3; i++) {
					framesBot.add(tmpFrames[lineSprite+2][i]);
				}
				this.animBot = new Animation<TextureRegion>(0.08f, framesBot);
				this.animBot.setPlayMode(Animation.PlayMode.LOOP);
				float delta = Gdx.graphics.getDeltaTime();
				int currentFrame = animBot.getKeyFrameIndex(stateTime += delta);
				this.sprite = new Sprite (framesBot.get(currentFrame));
				
				double toScanX= ((posX+sprite.getWidth()/2)/4.996);
				double toScanY= map.getGameSceneHeight()/5-(posY)/5+1;
				canDown = map.analyseImage(map.secretScene, toScanX, toScanY);
							
				if(!canDown) {
					monstreSpeed = 0;
					deplacement = 1;
				}
				else {
					posY -= Gdx.graphics.getDeltaTime() * monstreSpeed;
					tempsDeplacement = LocalTime.now();
					hitbox.setPosition(posX, posY);
					if (collision == false) {

					collision = hitbox.overlaps(link.getHitbox());
					}
					if(collision) {
						posY += Gdx.graphics.getDeltaTime() * monstreSpeed;
						hitbox.setPosition(posX, posY);
					}
				}
			}
		}
		collision = false;
	}
	
}