package com.zelda.world;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

// Les méthodes sont après les getters et setters
public class GameMap {
	public SpriteBatch batch;
	public Texture gameScene;
	public Texture secretScene;
	protected static int width=580;
	protected static int height=400;

	protected String colorNotPath= "ff000000";
	protected int gameSceneWidth;
	protected int gameSceneHeight;
	OrthographicCamera camera;
	
	public GameMap(SpriteBatch batch, Texture gameScene) {
		this.height=height;
		this.width=width;
		this.gameScene=gameScene;
		this.gameSceneHeight=3432;
		this.gameSceneWidth=10000;
	}

	public GameMap() {
		this.height=height;
		this.width=width;


	}


	public int getGameSceneWidth() {
		return gameSceneWidth;
	}

	public void setGameSceneWidth(int gameSceneWidth) {
		this.gameSceneWidth = gameSceneWidth;
	}

	public int getGameSceneHeight() {
		return gameSceneHeight;
	}

	public void setGameSceneHeight(int gameSceneHeight) {
		this.gameSceneHeight = gameSceneHeight;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public Texture getSecretScene() {
		return secretScene;
	}

	public void setSecretScene(Texture secretScene) {
		this.secretScene = secretScene;
	}

	public static int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getColorNotPath() {
		return colorNotPath;
	}

	public void setColorNotPath(String colorNotPath) {
		this.colorNotPath = colorNotPath;
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

	public void setGameScene(Texture environmentScene) {
		this.gameScene = environmentScene;
	}

	public static int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	public void create () {
		this.batch = new SpriteBatch();
		this.gameScene = new Texture("com/zelda/world/World.png");
		this.secretScene = new Texture("com/zelda/world/WorldSecret.png");
		
	}

	public void render() {
		

		this.batch.begin();
		//this.batch.draw(this.secretScene, 0, 0,width,height);
		 
		this.batch.end();

	}

	public boolean analyseImage(Texture texture,double X, double Y) {
		URL url = getClass().getResource("WorldSecret.png");
		File fichier = 	new File(url.getPath());
		try {
			BufferedImage image = ImageIO.read(fichier);
			Color c = new Color( image.getRGB((int)X, (int)Y)); // On regarde la couleur en (X,Y)

			if (c.toString().equals( colorNotPath)) { // On check si c'est noir
				return false;
			}
			else {return true;}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}


