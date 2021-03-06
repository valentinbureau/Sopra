package com.zelda.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Les m?thodes sont apr?s les getters et setters
public class GameMap {
	public SpriteBatch batch;
	public Texture gameScene;
	protected int width=650;
	protected int height=350;
	
	public GameMap(SpriteBatch batch, Texture gameScene) {
		this.height=height;
		this.width=width;
	}

	public GameMap() {
		this.height=height;
		this.width=width;
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int heigth) {
		this.height = heigth;
	}

	public void create () {
		this.batch = new SpriteBatch();
		this.gameScene = new Texture("Scene1.png");
	}
	
	public void render() {
		this.batch.draw(gameScene, 0, 0, 650,350);
		
	}
	
	
}
