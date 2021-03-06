package com.zelda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class Joueur extends Entite{
	 private static final int FRAME_COLS =7, FRAME_ROWS = 1;
	 
	Animation<TextureRegion> walkAnimation;
	Texture walkSheet;
	private final static int STARTING_X = 320;
    private final static int STARTING_Y = 175;
	
	public Joueur(){
        createIdleAnimation();
        this.setPosition(STARTING_X, STARTING_Y);
        this.walkAnimation=walkAnimation;
    }
	
	private void createIdleAnimation() {
		walkSheet = new Texture(Gdx.files.internal("LinkAnimation2.png"));
		
		 TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				 	walkSheet.getWidth() / FRAME_COLS,
	                walkSheet.getHeight() / FRAME_ROWS);
		 
		 TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		 int index = 0;
	        for (int i = 0; i < FRAME_ROWS; i++) {
	            for (int j = 0; j < FRAME_COLS; j++) {
	                walkFrames[index++] = tmp[i][j];
	            }
	        }
	     walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);
	}
}
