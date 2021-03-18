package screen;

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
import com.zelda.Princesse;
import com.zelda.TheLegendOfSopra;
import com.zelda.world.GameMap;

public class GameScreen extends ApplicationAdapter implements Screen{
	


	
	private TheLegendOfSopra parent;
	private Hud hud;
	
	static FileHandle fontFile;
	BitmapFont bitmapfont;
	Sound sound;
	OrthographicCamera camera;
	OrthographicCamera miniCamera;
	
	static Princesse princesse = new Princesse();
	static Link link = new Link(); // Initialisation du Joueur
	Monstre monstre = new Monstre(5920, 1500, 80, 1); //Initialisation du Monstre de type 1
	Monstre monstre1 = new Monstre(5920, 1300, 80, 4);
	static GameMap map = new GameMap();//Initialisation de la map

	private Texture miniMap;
	private static final int MINI_MAP_RATIO = 69;
    private static final int MINI_MAP_WIDTH = map.getGameSceneWidth()/MINI_MAP_RATIO;
    private static final int MINI_MAP_HEIGHT = map.getGameSceneHeight()/MINI_MAP_RATIO;
	private ShapeRenderer shapeRenderer;
	 
	public GameScreen(TheLegendOfSopra orch) 
	{
		miniMap = new Texture(Gdx.files.internal("screen/assets/miniMap.png"));
		//sound = Gdx.audio.newSound(Gdx.files.internal("com/zelda/world/Music.mp3"));
		//sound.play(0.05f);
		camera = new OrthographicCamera();
		miniCamera = new OrthographicCamera();
        camera.setToOrtho(false, map.getWidth(), map.getHeight());
		parent = orch;
		map.create();//Création de la map (batch & texture)
		link.create();//Création du personnage (sprite)
		princesse.create();
		hud=new Hud(map.batch);
		monstre.create();//Crï¿½ation du monstre (sprite)
		monstre1.create();
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
		
		draw();
		hud.stage.draw();
	}
	
	public void draw()
	{
		princesse.render();
		link.render(); //Commande de déplacement personnage
		monstre.render();//Déplacement Monstre
		monstre1.render();
		camera.update();
		miniCamera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.position.set(link.getPosX(), link.getPosY(), 0);
		camera.update();
		map.batch.setProjectionMatrix(camera.combined); 
		map.batch.begin();
		map.batch.draw(map.gameScene, 0, 0,10000,3424);//Affichage map
		map.batch.draw(link.getSprite(), link.getPosX(), link.getPosY());//Affichage personnage
		map.batch.draw(princesse.getSprite(), princesse.getPosX(), princesse.getPosY(),princesse.getWidth(),princesse.getHeight());//Affichage personnage

		map.batch.draw(monstre.getSprite(), monstre.getMonstreX(), monstre.getMonstreY()); //Affichage Monstre
		map.batch.draw(monstre1.getSprite(), monstre1.getMonstreX(), monstre1.getMonstreY());

//		draw(Texture texture, float x, float y, float width, float height)
//		Draws a rectangle with the bottom left corner at x,y and stretching the region to cover the given width and height.
		map.batch.draw(miniMap,  camera.position.x-map.getWidth()/2, camera.position.y+map.getHeight()/3+15, MINI_MAP_WIDTH,MINI_MAP_HEIGHT);
        map.batch.setProjectionMatrix(camera.invProjectionView);
        camera.position.set(link.getPosX() , link.getPosY() , 0);
        camera.update();

        map.batch.end();
        shapeRenderer = new ShapeRenderer();
		
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.rect(link.getPosY()-map.getWidth()/2-2 + link.getPosX()/MINI_MAP_RATIO, (link.getPosY()+map.getHeight()/3+14)+link.getPosY()/MINI_MAP_RATIO,2, 2);
        shapeRenderer.end();
        
        if(Gdx.input.isKeyPressed(Keys.TAB)) {
        		System.out.println("PRESSED");
        		parent.changeScreen(TheLegendOfSopra.MINIMAP);
			}
        
	}
}
