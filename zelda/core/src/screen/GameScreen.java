package screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.zelda.world.GameMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.zelda.JoueurInter;
import com.zelda.TheLegendOfSopra;
import com.zelda.world.GameMap;

public class GameScreen extends ApplicationAdapter implements Screen{

	private float linkX =320;
	private float linkY=175;
	private float linkSpeed = 80;

	private TheLegendOfSopra parent;

	JoueurInter link = new JoueurInter(linkX,linkY,linkSpeed); // Initialisation du Joueur
	GameMap map = new GameMap();//Initialisation de la map

	public GameScreen(TheLegendOfSopra orch) 
	{
		parent = orch;
		map.create();//Création de la map (batch & texture)
		link.create();//Création du personnage (sprite)
	}

	@Override
	public void dispose () {
		map.batch.dispose();
		map.gameScene.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		link.render(); //Commande de déplacement personnage

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		map.batch.begin();
		map.batch.draw(map.gameScene, 0, 0, map.getWidth(),map.getHeight());//Affichage map
		map.batch.draw(link.getSprite(), link.getLinkX(), link.getLinkY());//Affichage personnage
		map.batch.end();

	}
}
