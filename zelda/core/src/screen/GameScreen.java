package screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.zelda.world.GameMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.zelda.JoueurInter;
import com.zelda.TheLegendOfSopra;
import com.zelda.world.GameMap;

public class GameScreen extends ApplicationAdapter implements Screen{

	private float linkX =5920;
	private float linkY=1700;
	private float linkSpeed = 100;
	
	private TheLegendOfSopra parent;
	private Hud hud;
	
	static FileHandle fontFile;
	BitmapFont bitmapfont;

	Sound sound;
	OrthographicCamera camera;
	JoueurInter link = new JoueurInter(linkX,linkY,linkSpeed); // Initialisation du Joueur
	static GameMap map = new GameMap();//Initialisation de la map

	public GameScreen(TheLegendOfSopra orch) 
	{
		sound = Gdx.audio.newSound(Gdx.files.internal("com/zelda/world/Music.mp3"));
		sound.play();
		camera = new OrthographicCamera();
        camera.setToOrtho(false, map.getWidth(), map.getHeight());
		parent = orch;
		map.create();//Création de la map (batch & texture)
		link.create();//Création du personnage (sprite)
//		fontFile = Gdx.files.internal("screen/assets/zelda.fnt");
//		bitmapfont = new BitmapFont(fontFile);
//		bitmapfont.getData().setScale(1.0f);
		hud=new Hud(map.batch);
	}

	@Override
	public void dispose () {
		map.batch.dispose();
		//map.gameScene.dispose();
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

//	@Override
//	public void render(float delta) {
//		link.render(); //Commande de déplacement personnage
//
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//		map.batch.begin();
//		map.batch.draw(map.gameScene, 0, 0, map.getWidth(),map.getHeight());//Affichage map
//		map.batch.draw(link.getSprite(), link.getLinkX(), link.getLinkY());//Affichage personnage
//		//bitmapfont.setColor(0,0, 0, 0);
//		bitmapfont.draw(map.batch, "Live: ",
//                 map.getWidth() - 150, map.getHeight() - 25);
//		map.batch.end();
//
//	}
	
	@Override
	public void render(float delta) {
		draw();
		hud.stage.draw();
	}
	
	public void draw()
	{
		link.render(); //Commande de déplacement personnage
		

		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.position.set(link.getLinkX(), link.getLinkY(), 0);
		camera.update();
		map.batch.setProjectionMatrix(camera.combined); 
		map.batch.begin();
		map.batch.draw(map.gameScene, 0, 0,10000,3424);//Affichage map
		map.batch.draw(link.getSprite(), link.getLinkX(), link.getLinkY());//Affichage personnage
		//bitmapfont.setColor(0,0, 0, 0);
		//bitmapfont.draw(map.batch, "Live ", map.getWidth() -100 , map.getHeight() +50);
		map.batch.end();
	}
}
