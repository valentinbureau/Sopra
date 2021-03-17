package screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.zelda.world.GameMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.zelda.JoueurInter;
import com.zelda.Monstre;
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
	OrthographicCamera miniCamera;
	JoueurInter link = new JoueurInter(linkX,linkY,linkSpeed); // Initialisation du Joueur
	Monstre monstre = new Monstre(5920, 1500, 80, 1); //Initialisation du Monstre de type 1
	static GameMap map = new GameMap();//Initialisation de la map

	

	
	//private static final int MINI_MAP_SIZE = 64;
	private Texture miniMap;
	private static final int MINI_MAP_RATIO = 69;
    private static final int MINI_MAP_WIDTH = map.getGameSceneWidth()/MINI_MAP_RATIO;
    private static final int MINI_MAP_HEIGHT = map.getGameSceneHeight()/MINI_MAP_RATIO;
	private ShapeRenderer shapeRenderer;
	// private Mode7 mode7;
	 
	public GameScreen(TheLegendOfSopra orch) 
	{
		miniMap = new Texture(Gdx.files.internal("screen/assets/miniMap.png"));
		sound = Gdx.audio.newSound(Gdx.files.internal("com/zelda/world/Music.mp3"));
		//sound.play();
		camera = new OrthographicCamera();
		miniCamera = new OrthographicCamera();
        camera.setToOrtho(false, map.getWidth(), map.getHeight());
		parent = orch;
		map.create();//Cr�ation de la map (batch & texture)
		link.create();//Cr�ation du personnage (sprite)
//		fontFile = Gdx.files.internal("screen/assets/zelda.fnt");
//		bitmapfont = new BitmapFont(fontFile);
//		bitmapfont.getData().setScale(1.0f);
		hud=new Hud(map.batch);
		monstre.create();//Cr�ation du monstre (sprite)
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

//	@Override
//	public void render(float delta) {
//		link.render(); //Commande de d�placement personnage
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
		link.render(); //Commande de d�placement personnage
		

		camera.update();
		miniCamera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.position.set(link.getLinkX(), link.getLinkY(), 0);
		camera.update();
		map.batch.setProjectionMatrix(camera.combined); 
		map.batch.begin();
		map.batch.draw(map.gameScene, 0, 0,10000,3424);//Affichage map
		map.batch.draw(link.getSprite(), link.getLinkX(), link.getLinkY());//Affichage personnage
		map.batch.draw(monstre.getSprite(), monstre.getMonstreX(), monstre.getMonstreY()); //Affichage Monstre
		//map.batch.draw(miniMap, camera.position.x-map.getWidth()/2, camera.position.y+map.getHeight()/2, MINI_MAP_WIDTH, MINI_MAP_HEIGHT);
//		draw(Texture texture, float x, float y, float width, float height)
//		Draws a rectangle with the bottom left corner at x,y and stretching the region to cover the given width and height.
		map.batch.draw(miniMap,  camera.position.x-map.getWidth()/2, camera.position.y+map.getHeight()/3+15, MINI_MAP_WIDTH,MINI_MAP_HEIGHT);

		//camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()+60);
		//camera = new OrthographicCamera(MINI_MAP_WIDTH, MINI_MAP_HEIGHT);
        //camera.position.set(MINI_MAP_WIDTH-link.getLinkX() /MINI_MAP_RATIO , MINI_MAP_HEIGHT-link.getLinkY()/MINI_MAP_RATIO , 0);
    //    camera.normalizeUp();
       // camera.update();
//		miniCamera.position.set(link.getLinkX()  , link.getLinkY() , 0);
//		miniCamera.update();
        map.batch.setProjectionMatrix(camera.invProjectionView);
        camera.position.set(link.getLinkX() , link.getLinkY() , 0);
        camera.update();
		//map.batch.draw(map.gameScene, camera.position.x, camera.position.y, camera.position.x-MINI_MAP_WIDTH, camera.position.y+MINI_MAP_HEIGHT);

        map.batch.end();
        shapeRenderer = new ShapeRenderer();
		
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.setProjectionMatrix(camera.combined);
//		shapeRenderer.identity();
		//shapeRenderer.rotate(1, 0, 0, 10);
       // shapeRenderer.rect(x, y, width, height);
//        float linkXMiniMap=camera.position.x;
//        float linkYMiniMap=camera.position.y;
        //shapeRenderer.rect(camera.position.x, camera.position.y, 6, 6);
        shapeRenderer.rect(link.getLinkX()-map.getWidth()/2-2 + link.getLinkX()/MINI_MAP_RATIO, (link.getLinkY()+map.getHeight()/3+14)+link.getLinkY()/MINI_MAP_RATIO,2, 2);
        //shapeRenderer.rect(linkXMiniMap, linkYMiniMap, 6, 6);
        //shapeRenderer.rect((link.getLinkX())/ MINI_MAP_RATIO, MINI_MAP_HEIGHT- (link.getLinkY()) / MINI_MAP_RATIO  , 1, 1);        
   //     shapeRenderer.rect(link.getLinkX() * MINI_MAP_TRACK_RATIO,link.getLinkY() * MINI_MAP_TRACK_RATIO+map.getHeight(), 1, 1);        
        shapeRenderer.end();
	}
}
