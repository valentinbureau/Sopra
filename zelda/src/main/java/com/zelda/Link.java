package com.zelda;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Embeddable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.zelda.world.GameMap;

import screen.GameScreen;

//Les méthodes sont après les getters et setters


@Embeddable
public class Link{
	private float posX;
	private float posY;
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
	
	
	transient Sprite sprite;
	transient Texture linkTexture = new Texture ("com/zelda/link2.png");
	private transient float linkSpeed;
	private transient float defaultSpeed;
	private transient float stateTime = 0;
	private transient int swordDirection;
	private transient Rectangle hitbox;
	private transient Rectangle attackHitbox;
	private transient boolean collision = false;
	private transient boolean collisionAttack = false;
	private transient boolean collisionPrincesse =false;
	private transient int attackDirection = 0;
	private transient boolean victory;
	private transient SpriteBatch batch=  new SpriteBatch();
	private transient Texture gameScene= new Texture("com/zelda/world/World.png");
	transient GameMap map= new GameMap(batch,gameScene);


	private transient Texture animationSwordDown = new Texture("com/zelda/link2-sword_down-left.png");
	private transient TextureRegion[][] tmpFramesSword = TextureRegion.split(animationSwordDown, 45, 45);
	private transient TextureRegion[][] tmpFramesSword2 = TextureRegion.split(animationSwordDown, 45, 45);
	private transient TextureRegion[][] tmpFramesSword3 = TextureRegion.split(animationSwordDown, 45, 49);
	private transient TextureRegion[][] tmpFramesSword4 = TextureRegion.split(animationSwordDown, 43, 47);
	private transient TextureRegion[][] tmpFrames = TextureRegion.split(linkTexture, 45, 44);
	private transient Texture animationSwordLeft = new Texture("com/zelda/link2-sword_left.png");
	private transient TextureRegion[][] tmpFramesSword5 = TextureRegion.split(animationSwordLeft, 45, 48);
	private transient Texture animationSwordUp = new Texture("com/zelda/link2-sword_up.png");
	private transient TextureRegion[][] tmpFramesSword6 = TextureRegion.split(animationSwordUp, 45, animationSwordLeft.getHeight());
	private transient Texture animationSwordRight = new Texture("com/zelda/link2-sword_right.png");
	private transient TextureRegion[][] tmpFramesSword7 = TextureRegion.split(animationSwordRight, 44, animationSwordRight.getHeight());

	private transient Array<TextureRegion> framesLeft = new Array<TextureRegion>();
	private transient Array<TextureRegion> framesRight = new Array<TextureRegion>();
	private transient Array<TextureRegion> framesUp = new Array<TextureRegion>();
	private transient Array<TextureRegion> framesBot = new Array<TextureRegion>();


	private transient Animation<TextureRegion> animBot;
	private transient Animation<TextureRegion> animTop;
	private transient Animation<TextureRegion> animLeft;
	private transient Animation<TextureRegion> animRight;
	private transient Animation<TextureRegion> animSword;
	private transient Array<TextureRegion> framesSword = new Array<TextureRegion>();

	private transient boolean canUp;
	private transient boolean canDown;
	private transient boolean canLeft;
	private transient boolean canRight;
	
	private int vie;
	private transient Texture texture;
	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}

	public Link(float linkX, float linkY, float linkSpeed) {
		super();
		this.posX = linkX;
		this.posY = linkY;
		this.linkSpeed = linkSpeed;
		this.defaultSpeed = linkSpeed;
		this.canUp=true;
		this.canDown=true;
		this.canRight=true;
		this.vie=8;
		this.victory=false;

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
		this.vie=8;
		this.victory=false;
	}
	
	public boolean recevoirDegats()
	{
		this.vie--;
		if (vie ==0)
		{
			return true;
		}
		return false;
	}

	
	
	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public boolean isCollisionPrincesse() {
		return collisionPrincesse;
	}

	public void setCollisionPrincesse(boolean collisionPrincesse) {
		this.collisionPrincesse = collisionPrincesse;
	}

	public boolean isVictory() {
		return victory;
	}

	public void setVictory(boolean victory) {
		this.victory = victory;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
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

	public TextureRegion[][] getTmpFrames() {
		return tmpFrames;
	}

	public Texture getAnimationSwordDown() {
		return animationSwordDown;
	}

	public void setAnimationSwordDown(Texture animationSwordDown) {
		this.animationSwordDown = animationSwordDown;
	}

	public TextureRegion[][] getTmpFramesSword() {
		return tmpFramesSword;
	}

	public void setTmpFramesSword(TextureRegion[][] tmpFramesSword) {
		this.tmpFramesSword = tmpFramesSword;
	}

	public TextureRegion[][] getTmpFramesSword2() {
		return tmpFramesSword2;
	}

	public void setTmpFramesSword2(TextureRegion[][] tmpFramesSword2) {
		this.tmpFramesSword2 = tmpFramesSword2;
	}

	public TextureRegion[][] getTmpFramesSword3() {
		return tmpFramesSword3;
	}

	public void setTmpFramesSword3(TextureRegion[][] tmpFramesSword3) {
		this.tmpFramesSword3 = tmpFramesSword3;
	}

	public TextureRegion[][] getTmpFramesSword4() {
		return tmpFramesSword4;
	}

	public void setTmpFramesSword4(TextureRegion[][] tmpFramesSword4) {
		this.tmpFramesSword4 = tmpFramesSword4;
	}

	public Texture getAnimationSwordLeft() {
		return animationSwordLeft;
	}

	public void setAnimationSwordLeft(Texture animationSwordLeft) {
		this.animationSwordLeft = animationSwordLeft;
	}

	public TextureRegion[][] getTmpFramesSword5() {
		return tmpFramesSword5;
	}

	public void setTmpFramesSword5(TextureRegion[][] tmpFramesSword5) {
		this.tmpFramesSword5 = tmpFramesSword5;
	}

	public Texture getAnimationSwordUp() {
		return animationSwordUp;
	}

	public void setAnimationSwordUp(Texture animationSwordUp) {
		this.animationSwordUp = animationSwordUp;
	}

	public TextureRegion[][] getTmpFramesSword6() {
		return tmpFramesSword6;
	}

	public void setTmpFramesSword6(TextureRegion[][] tmpFramesSword6) {
		this.tmpFramesSword6 = tmpFramesSword6;
	}

	public Texture getAnimationSwordRight() {
		return animationSwordRight;
	}

	public void setAnimationSwordRight(Texture animationSwordRight) {
		this.animationSwordRight = animationSwordRight;
	}

	public TextureRegion[][] getTmpFramesSword7() {
		return tmpFramesSword7;
	}

	public void setTmpFramesSword7(TextureRegion[][] tmpFramesSword7) {
		this.tmpFramesSword7 = tmpFramesSword7;
	}

	public void setTmpFrames(TextureRegion[][] tmpFrames) {
		this.tmpFrames = tmpFrames;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public Array<TextureRegion> getFramesLeft() {
		return framesLeft;
	}

	public void setFramesLeft(Array<TextureRegion> framesLeft) {
		this.framesLeft = framesLeft;
	}

	public Array<TextureRegion> getFramesRight() {
		return framesRight;
	}

	public Rectangle getAttackHitbox() {
		return attackHitbox;
	}

	public void setAttackHitbox(Rectangle attackHitbox) {
		this.attackHitbox = attackHitbox;
	}

	public boolean isCollisionAttack() {
		return collisionAttack;
	}

	public void setCollisionAttack(boolean collisionAttack) {
		this.collisionAttack = collisionAttack;
	}

	public int getAttackDirection() {
		return attackDirection;
	}

	public void setAttackDirection(int attackDirection) {
		this.attackDirection = attackDirection;
	}

	public void setFramesRight(Array<TextureRegion> framesRight) {
		this.framesRight = framesRight;
	}

	public Array<TextureRegion> getFramesUp() {
		return framesUp;
	}

	public void setFramesUp(Array<TextureRegion> framesUp) {
		this.framesUp = framesUp;
	}

	public Array<TextureRegion> getFramesBot() {
		return framesBot;
	}

	public void setFramesBot(Array<TextureRegion> framesBot) {
		this.framesBot = framesBot;
	}

	public void create () {
		texture = new Texture ("com/zelda/link2.png");

		hitbox = new Rectangle(posX, posY, 45, 45);
		attackHitbox = new Rectangle(posX, posY, 55, 55);

		
		// Setting Frames for movements
		animationSwordDown = new Texture("com/zelda/link2-sword_down-left.png");
		tmpFramesSword = TextureRegion.split(animationSwordDown, 45, 45);
		tmpFramesSword2 = TextureRegion.split(animationSwordDown, 45, 45);
		tmpFramesSword3 = TextureRegion.split(animationSwordDown, 45, 49);
		tmpFramesSword4 = TextureRegion.split(animationSwordDown, 43, 47);
		tmpFrames = TextureRegion.split(texture, 45, 44);
		animationSwordLeft = new Texture("com/zelda/link2-sword_left.png");
		tmpFramesSword5 = TextureRegion.split(animationSwordLeft, 45, 48);
		animationSwordUp = new Texture("com/zelda/link2-sword_up.png");
		tmpFramesSword6 = TextureRegion.split(animationSwordUp, 45, animationSwordLeft.getHeight());
		animationSwordRight = new Texture("com/zelda/link2-sword_right.png");
		tmpFramesSword7 = TextureRegion.split(animationSwordRight, 44, animationSwordRight.getHeight());

		attackHitbox = new Rectangle(posX, posY, 55, 55);


		sprite= new Sprite( tmpFrames[0][0] );
		
		framesLeft = new Array<TextureRegion>();
		framesRight = new Array<TextureRegion>();
		framesUp = new Array<TextureRegion>();
		framesBot = new Array<TextureRegion>();
		
		
		for (int i = 8; i < 14; i++) {
			framesLeft.add(tmpFrames[1][i]);
		}

		for (int i = 8; i < 14; i++) {
			framesRight.add(tmpFrames[4][i]);
		}

		for (int i = 0; i < 8; i++) {
			framesUp.add(tmpFrames[4][i]);
		}

		for (int i = 0; i < 8; i++) {
			framesBot.add(tmpFrames[1][i]);
		}

	}


	public void render(List<Monstre> monstres,Princesse princesse) {

		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {

			animLeft = new Animation<TextureRegion>(0.08f, framesLeft);
			animLeft.setPlayMode(Animation.PlayMode.LOOP);
			float delta = Gdx.graphics.getDeltaTime();
			int currentFrame = animLeft.getKeyFrameIndex(stateTime += delta);
			this.sprite = new Sprite (framesLeft.get(currentFrame));

			framesSword.clear();
			framesSword.add(tmpFramesSword5[0][0]);
			framesSword.add(tmpFramesSword5[0][1]);

			framesSword.add(tmpFramesSword5[0][2]);
			framesSword.add(tmpFramesSword5[0][3]);
			framesSword.add(tmpFramesSword5[0][4]);
			attackDirection = 1;
			double toScanX= (this.posX)/5.01-1;
			double toScanY=map.getGameSceneHeight()/5-(this.posY+sprite.getHeight()/2)/5;
			canLeft = map.analyseImage(map.secretScene,toScanX , toScanY);
			linkSpeed = defaultSpeed;

			if(!canLeft) {
				linkSpeed=0;
			}

			else {canLeft=true;}
			posX -= Gdx.graphics.getDeltaTime() * linkSpeed;
			hitbox.setPosition(posX, posY);
			

			if (!collisionPrincesse) {
				collisionPrincesse = hitbox.overlaps(princesse.getHitbox());
				System.out.println(collisionPrincesse);
				if(collisionPrincesse) { victory=true;}
			}
			

//			monstres.stream()
//				.filter(m -> m.collision)
//				.forEach(m -> m.collision = hitbox.overlaps(m.getHitbox());

			for (Monstre monstre : monstres) {
				if (collision == false) {
					collision = hitbox.overlaps(monstre.getHitbox());
				}
			}
			if(collision) {
				posX += Gdx.graphics.getDeltaTime() * linkSpeed;
				hitbox.setPosition(posX, posY);
				vie--;
			}
		}


		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {

			animRight = new Animation<TextureRegion>(0.08f, framesRight);
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
			attackDirection = 3;
			double toScanX= (posX + this.sprite.getWidth())/5;
			double toScanY=map.getGameSceneHeight()/5-(posY+sprite.getHeight()/2)/5;
			canRight = map.analyseImage(map.secretScene, toScanX, toScanY);
			linkSpeed = defaultSpeed;

			if(!canRight ) {
				linkSpeed=0;
			}
			else {canRight=true;}
			posX += Gdx.graphics.getDeltaTime() * linkSpeed;
			hitbox.setPosition(posX, posY);
			hitbox.setPosition(posX, posY);

			if (!collisionPrincesse) {
				collisionPrincesse = hitbox.overlaps(princesse.getHitbox());
				System.out.println(collisionPrincesse);
				if(collisionPrincesse) { victory=true;}
			}

			for (Monstre monstre : monstres) {
				if (collision == false) {

					collision = hitbox.overlaps(monstre.getHitbox());
				}
			}
			if(collision) {
				posX -= Gdx.graphics.getDeltaTime() * linkSpeed;
				hitbox.setPosition(posX, posY);
				vie--;
			}
		}


		if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) {


			this.animTop = new Animation<TextureRegion>(0.08f, framesUp);
			animTop.setPlayMode(Animation.PlayMode.LOOP);
			float delta = Gdx.graphics.getDeltaTime();
			int currentFrame = animTop.getKeyFrameIndex(stateTime += delta);
			this.sprite = new Sprite (framesUp.get(currentFrame));

			framesSword.clear();

			framesSword.add(tmpFramesSword6[0][0]);
			framesSword.add(tmpFramesSword6[0][1]);
			framesSword.add(tmpFramesSword6[0][2]);
			framesSword.add(tmpFramesSword6[0][3]);

			framesSword.add(tmpFramesSword6[0][4]);
			attackDirection = 2;
			double toScanX= (posX+sprite.getWidth()/2)/4.996;
			double toScanY=map.getGameSceneHeight()/5-(posY+this.sprite.getHeight())/5-1;
			canUp = map.analyseImage(map.secretScene, toScanX, toScanY);
			linkSpeed = defaultSpeed;

			if(!canUp) {
				linkSpeed=0;

			}
			else {canUp=true;}
			posY += Gdx.graphics.getDeltaTime() * linkSpeed;
			hitbox.setPosition(posX, posY);
			
			if (!collisionPrincesse) {
				collisionPrincesse = hitbox.overlaps(princesse.getHitbox());
				System.out.println(collisionPrincesse);
				if(collisionPrincesse) { victory=true;}
			}
			
			for (Monstre monstre : monstres) {
				if (collision == false) {

					collision = hitbox.overlaps(monstre.getHitbox());
				}
			}
			if(collision) {
				posY -= Gdx.graphics.getDeltaTime() * linkSpeed;
				hitbox.setPosition(posX, posY);
				vie--;
			}
		}


		if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {

			animBot = new Animation<TextureRegion>(0.08f, framesBot);
			animBot.setPlayMode(Animation.PlayMode.LOOP);
			float delta = Gdx.graphics.getDeltaTime();
			int currentFrame = animBot.getKeyFrameIndex(stateTime += delta);
			this.sprite = new Sprite (framesBot.get(currentFrame));

			framesSword.clear();
			//Ajout d'animation attaque bas
			framesSword.add(tmpFramesSword[0][0]);
			framesSword.add(tmpFramesSword2[0][1]);
			framesSword.add(tmpFramesSword3[0][2]);
			framesSword.add(tmpFramesSword4[0][4]);
			framesSword.add(tmpFramesSword4[0][3]);

			attackDirection = 4;
			double toScanX= ((posX+sprite.getWidth()/2)/4.996);
			double toScanY= map.getGameSceneHeight()/5-(posY)/5+1;
			canDown = map.analyseImage(map.secretScene, toScanX, toScanY);
			linkSpeed = defaultSpeed;

			if(!canDown) {
				linkSpeed=0;
			}

			else {canDown=true;}
			posY -= Gdx.graphics.getDeltaTime() * linkSpeed;
			hitbox.setPosition(posX, posY);
			if (!collisionPrincesse) {
				collisionPrincesse = hitbox.overlaps(princesse.getHitbox());
				System.out.println(collisionPrincesse);
				if(collisionPrincesse) { victory=true;
				posY += Gdx.graphics.getDeltaTime() * linkSpeed;}
			}

			for (Monstre monstre : monstres) {
				if (collision == false) {

					collision = hitbox.overlaps(monstre.getHitbox());
				}
			}
			if(collision) {
				posY += Gdx.graphics.getDeltaTime() * linkSpeed;
				hitbox.setPosition(posX, posY);
				vie--;
			}
		}

		if(Gdx.input.isKeyPressed(Keys.E)) {
			
			Animation<TextureRegion> animSword = new Animation<TextureRegion>(0.04f, framesSword);
			animSword.setPlayMode(Animation.PlayMode.LOOP);
			float delta = Gdx.graphics.getDeltaTime();
			TextureRegion currentFrame = animSword.getKeyFrame(stateTime += delta, true);
			this.sprite = new Sprite (currentFrame);

			switch (attackDirection) {
			case 1:
				attackHitbox.setPosition(posX - 15, posY);
//				System.out.println(attackHitbox.getX());
//				System.out.println(getPosX());
				break;
			case 2 :
				attackHitbox.setPosition(posX, posY + 15);
//				System.out.println(attackHitbox.getX());
//				System.out.println(getPosX());
				break;
			case 3 :
				attackHitbox.setPosition(posX + 15, posY);
//				System.out.println(attackHitbox.getX());
//				System.out.println(getPosX());
				break;
			case 4 : 
				attackHitbox.setPosition(posX, posY - 15);
//				System.out.println(attackHitbox.getX());
//				System.out.println(getPosX());
				break;
			default:
				break;
			}
			
			for (Monstre monstre : monstres) {
				if (collisionAttack == false) {
					collisionAttack = attackHitbox.overlaps(monstre.getHitbox());
					if(collisionAttack) {
						monstre.setPosX(100);
						monstre.setPosY(100);
						monstre.getHitbox().setPosition(100, 100);
					}
				}
			}


		}
		collision = false;
		collisionAttack = false;
		attackHitbox.setPosition(posX, posY);
	}

}