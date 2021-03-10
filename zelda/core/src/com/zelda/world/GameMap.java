package com.zelda.world;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Les méthodes sont après les getters et setters
public class GameMap {
	public SpriteBatch batch;
	public Texture gameScene;
	public Texture secretScene;
	protected int width=580;
	protected int height=400;
	protected String colorNotPath= "ff000000";
	public GameMap(SpriteBatch batch, Texture gameScene) {
		this.height=height;
		this.width=width;
	}

	public GameMap() {
		this.height=height;
		this.width=width;
	}



	public Texture getSecretScene() {
		return secretScene;
	}

	public void setSecretScene(Texture secretScene) {
		this.secretScene = secretScene;
	}

	public int getHeight() {
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	public void create () {
		this.batch = new SpriteBatch();
		this.gameScene = new Texture("Scene1.png");
		this.secretScene = new Texture("Scene1Secret.png");
	}

	public void render() {
		this.batch.draw(gameScene, 0, 0,580,400);

	}

	public boolean analyseImage(Texture texture,int X, int Y) {
		File fichier = 	new File("Scene1Secret.png");
		try {
			BufferedImage image = ImageIO.read(fichier);
			Color c = new Color( image.getRGB(X, Y)); // On regarde la couleur en (X,Y)

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


