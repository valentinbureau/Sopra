package screen;



import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zelda.Link;
import com.zelda.Monstre;
import com.zelda.Objet;
import com.zelda.Princesse;
import com.zelda.TheLegendOfSopra;
import com.zelda.world.GameMap;

import util.Context;

public class GameScreen extends ApplicationAdapter implements Screen{


	private TheLegendOfSopra parent;
	private Hud hud;
	static FileHandle fontFile;
	BitmapFont bitmapfont;
	static Sound sound;

	OrthographicCamera camera;
	OrthographicCamera cameraPrinc;

	static Princesse princesse = new Princesse();
	static Link link = MenuPrincipal.userConnected.getAvatar(); // Initialisation du Joueur

	static Monstre monstre = new Monstre(6500, 2050, 50, 2); //Initialisation du Monstre de type 1
	static Monstre monstre1 = new Monstre(7114, 2316, 50, 2);
	static Monstre monstre2 = new Monstre(7010, 2338, 50, 5);
	static Monstre monstre3 = new Monstre(7198, 2162, 50, 2);
	static Monstre monstre4 = new Monstre(7112, 1947, 50, 5);
	static Monstre monstre5 = new Monstre(6838, 1922, 50, 2);
	static Monstre monstre6 = new Monstre(5920, 1503, 50, 3);
	static Monstre monstre7 = new Monstre(7042, 1461, 50, 5);
	static Monstre monstre8 = new Monstre(7323, 2868, 50, 5);
	static Monstre monstre9 = new Monstre(7702, 2789, 50, 1);
	static Monstre monstre10 = new Monstre(7587, 2326, 50, 5);
	static Monstre monstre11 = new Monstre(6690, 1930, 50, 2);
	static Monstre monstre12 = new Monstre(6680, 2330, 50, 6);
	static Monstre monstre13 = new Monstre(6557, 1513, 50, 3);
	static Monstre monstre14 = new Monstre(5919, 1805, 50, 7);

	static Objet epee = new Objet(6865, 2335);

	static List<Monstre> monsters;

	static ArrayList<Monstre> monstres = new ArrayList<Monstre>();
	static GameMap map = new GameMap();//Initialisation de la map

	private Texture miniMap;

	private static final int MINI_MAP_RATIO = 69;
	private static final int MINI_MAP_WIDTH = map.getGameSceneWidth()/MINI_MAP_RATIO;
	private static final int MINI_MAP_HEIGHT = map.getGameSceneHeight()/MINI_MAP_RATIO;
	private ShapeRenderer shapeRenderer;
	private ShapeRenderer shapeRendererPrinc;
	public static boolean victory = false;
	public GameScreen(TheLegendOfSopra orch) 
	{
		miniMap = new Texture(Gdx.files.internal("screen/assets/miniMap.png"));
		sound = Gdx.audio.newSound(Gdx.files.internal("com/zelda/world/Music.mp3"));
		sound.loop();
		sound.play(0.01f);
		camera = new OrthographicCamera();
		//miniCamera = new OrthographicCamera();
		camera.setToOrtho(false, map.getWidth(), map.getHeight());
		cameraPrinc = new OrthographicCamera();
		//miniCamera = new OrthographicCamera();
		cameraPrinc.setToOrtho(false,map.getWidth(), map.getHeight());
		parent = orch;
		map.create();//Cr�ation de la map (batch & texture)

		hud=new Hud(map.batch);

		monstres.add(monstre);
		monstres.add(monstre1);
		monstres.add(monstre2);
		monstres.add(monstre3);
		monstres.add(monstre4);
		monstres.add(monstre5);
		monstres.add(monstre6);
		monstres.add(monstre7);
		monstres.add(monstre8);
		monstres.add(monstre9);
		monstres.add(monstre10);
		monstres.add(monstre11);
		monstres.add(monstre12);
		monstres.add(monstre13);
		monstres.add(monstre14);
		epee.create();
		monstres.stream().forEach(m -> m.create());
		princesse.create();
		link.create();//Cr�ation du personnage (sprite)
	}

	@Override
	public void dispose () {
		map.batch.dispose();

		map.gameScene.dispose();
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


	@Override
	public void render(float delta) {
		//if (victory) {System.out.println("Bravo !!");
		//finPartie.show();
		draw();
		hud.stage.draw();
	}

	public void draw()
	{
		if (victory)
		{
			parent.changeScreen(TheLegendOfSopra.VICTOIRE);
		}

		//Check de ce qui doit �tre pr�sent � l'�cran
		monstres.forEach(m-> m.onCamera(link)); // Init du bool�en (pr�sence sous cam�ra)
		monsters = monstres.stream().filter(m -> m.isOnAir())
				.collect(Collectors.toList()); //On filtre les monstres pr�sents sous la camera

//		List<Monstre> monstreToDelete = monsters.stream().filter(m -> m.getVie() < 1)
//				.collect(Collectors.toList());
//		monstreToDelete.forEach(m -> m.remove());
		monsters.stream().filter(m -> m.getVie() > 0)
				.forEach(m -> m.render(link)); //Rendering des monstres pr�sents � l'�cran et selection monstres vivants

		princesse.onCamera(link);
		epee.onCamera(link);
		if (princesse.isOnAir()) {princesse.render(link);}
		if (epee.isOnAir()) {epee.render(link);};
		link.render(monsters,princesse, epee); //Commande de d�placement personnage
		camera.update();





		// Initialisation du spriteBatch

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		map.batch.setProjectionMatrix(camera.combined);
		map.batch.begin();
		//Affichage monstres


		//Affichage de la scene et de Link
		map.batch.draw(map.gameScene, 0, 0,10000,3424);//Affichage map
		map.batch.draw(link.getSprite(), link.getPosX(), link.getPosY());//Affichage personnage

		//affichage de la princesse

		if (princesse.isOnAir()) {
			map.batch.draw(princesse.getSprite(), princesse.getPosX(), princesse.getPosY(),princesse.getWidth(),princesse.getHeight());//Affichage personnage
		}

		//Affichage monstres
		monsters.stream().forEach(m ->map.batch.draw(m.getSprite(), m.getPosX(), m.getPosY()));

		//Affichage objet
		map.batch.draw(epee.getSprite(), epee.getPosX(), epee.getPosY(), epee.getSprite().getWidth(), epee.getSprite().getHeight());

		// Creation de la minimap
		map.batch.draw(miniMap,  camera.position.x-map.getWidth()/2, camera.position.y+map.getHeight()/3+15, MINI_MAP_WIDTH,MINI_MAP_HEIGHT);
		//map.batch.setProjectionMatrix(camera.invProjectionView);

		//setting de la cam�ra

		camera.position.set(link.getPosX() , link.getPosY() , 0);
		camera.update();

		cameraPrinc.position.set(princesse.getPosX() , princesse.getPosY() , 0);
		cameraPrinc.update();


		map.batch.end();

		//Cr�ation Hud
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.rect(link.getPosX()-map.getWidth()/2-2 + link.getPosX()/MINI_MAP_RATIO, (link.getPosY()+map.getHeight()/3+14)+link.getPosY()/MINI_MAP_RATIO,2, 2);
		shapeRenderer.end();

		shapeRendererPrinc = new ShapeRenderer();
		shapeRendererPrinc.begin(ShapeRenderer.ShapeType.Filled);
		shapeRendererPrinc.setColor(Color.MAGENTA);
		shapeRendererPrinc.setProjectionMatrix(cameraPrinc.combined);
		shapeRendererPrinc.rect(princesse.getPosX()-map.getWidth()/2-2 + princesse.getPosX()/MINI_MAP_RATIO, (princesse.getPosY()+map.getHeight()/3+13)+princesse.getPosY()/MINI_MAP_RATIO,3, 3);
		shapeRendererPrinc.end();

		if(Gdx.input.isKeyPressed(Keys.TAB)) {
			parent.changeScreen(TheLegendOfSopra.MINIMAP);
		}

		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			MenuPrincipal.userConnected=Context.getInstance().getDaoJoueur().save(MenuPrincipal.userConnected);
			parent.changeScreen(TheLegendOfSopra.INFOS);
		}

		if (link.isVictory())
		{
			link.setVictory(false);
			parent.changeScreen(TheLegendOfSopra.VICTOIRE);

		}

		if (link.getVie() <= 0)
		{
			parent.changeScreen(TheLegendOfSopra.GAMEOVER);
		}
		hud.show();
	}


}