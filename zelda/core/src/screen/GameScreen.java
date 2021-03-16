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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.zelda.JoueurInter;
import com.zelda.TheLegendOfSopra;
import com.zelda.world.GameMap;

public class GameScreen extends ApplicationAdapter implements Screen{
	
	private static float linkX =320;
	private static float linkY=175;
	private static float linkSpeed = 80;
	
	

	private TheLegendOfSopra parent;
	
	private Hud hud;
	
	static FileHandle fontFile;
	BitmapFont bitmapfont;

	static JoueurInter link = new JoueurInter(linkX,linkY,linkSpeed); // Initialisation du Joueur
	static GameMap map = new GameMap();//Initialisation de la map

	
	//private static final int MINI_MAP_SIZE = 64;
	private static final int MINI_MAP_RATIO = 9;
    private static final int MINI_MAP_WIDTH = map.getWidth()/MINI_MAP_RATIO;
    private static final int MINI_MAP_HEIGHT = map.getHeight()/MINI_MAP_RATIO;
	private ShapeRenderer shapeRenderer;
	 private OrthographicCamera camera;
	// private Mode7 mode7;
	 
	public GameScreen(TheLegendOfSopra orch) 
	{
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
		map.gameScene.dispose();
		shapeRenderer.dispose();
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

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		map.batch.begin();
		map.batch.draw(map.gameScene, 0, 0, map.getWidth(),map.getHeight());//Affichage map
		map.batch.draw(link.getSprite(), link.getLinkX(), link.getLinkY());//Affichage personnage
		map.batch.draw(map.gameScene, 0, 0+map.getHeight(), MINI_MAP_WIDTH, MINI_MAP_HEIGHT);
		
		camera = new OrthographicCamera(GameMap.getWidth(), GameMap.getHeight()+60);
		//camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()+60);
		//camera = new OrthographicCamera(MINI_MAP_WIDTH, MINI_MAP_HEIGHT);
        camera.position.set(MINI_MAP_WIDTH-link.getLinkX() /MINI_MAP_RATIO , MINI_MAP_HEIGHT-link.getLinkY()/MINI_MAP_RATIO , 0);
    //    camera.normalizeUp();
        camera.update();
        
        map.batch.setProjectionMatrix(camera.invProjectionView);
        map.batch.end();
        shapeRenderer = new ShapeRenderer();
		
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.identity();
		//shapeRenderer.rotate(1, 0, 0, 10);
       // shapeRenderer.rect(x, y, width, height);
        float linkXMiniMap=camera.position.x;
        float linkYMiniMap=camera.position.y;
        //shapeRenderer.rect(camera.position.x, camera.position.y, 6, 6);
        shapeRenderer.rect(-225, 213, 6, 6);
        //shapeRenderer.rect(linkXMiniMap, linkYMiniMap, 6, 6);
        //shapeRenderer.rect((link.getLinkX())/ MINI_MAP_RATIO, MINI_MAP_HEIGHT- (link.getLinkY()) / MINI_MAP_RATIO  , 1, 1);        
   //     shapeRenderer.rect(link.getLinkX() * MINI_MAP_TRACK_RATIO,link.getLinkY() * MINI_MAP_TRACK_RATIO+map.getHeight(), 1, 1);        
        shapeRenderer.end();
	}
}
