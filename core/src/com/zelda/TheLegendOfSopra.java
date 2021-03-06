package com.zelda;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.zelda.world.GameMap;

public class TheLegendOfSopra extends ApplicationAdapter {
	private float linkX =320;
	private float linkY=175;
	private float linkSpeed = 80;
	JoueurInter link = new JoueurInter(linkX,linkY,linkSpeed); // Initialisation du Joueur
	GameMap map = new GameMap();//Initialisation de la map
	
	@Override
	public void create () {
		map.create();//Cr?ation de la map (batch & texture)
		link.create();//Cr?ation du personnage (sprite)
	}

	@Override
	public void render () {
		link.render(); //Commande de d?placement personnage
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		map.batch.begin();
		map.batch.draw(map.gameScene, 0, 0, map.getWidth(),map.getHeight());//Affichage map
		map.batch.draw(link.sprite, link.getLinkX(), link.getLinkY());//Affichage personnage
		map.batch.end();
	}
	
	@Override
	public void dispose () {
		map.batch.dispose();
		map.gameScene.dispose();
	}
}
