package screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
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
	
	private static float linkX =5920;
	private static float linkY=1700;
	private static float linkSpeed = 100;

	
	private TheLegendOfSopra parent;
	private Hud hud;
	
	static FileHandle fontFile;
	BitmapFont bitmapfont;
	Sound sound;
	OrthographicCamera camera;
	OrthographicCamera miniCamera;
	static JoueurInter link = new JoueurInter(linkX,linkY,linkSpeed); // Initialisation du Joueur

	static GameMap map = new GameMap();//Initialisation de la map

	
	private Texture miniMap;
	private static final int MINI_MAP_RATIO = 69;
    private static final int MINI_MAP_WIDTH = map.getGameSceneWidth()/MINI_MAP_RATIO;
    private static final int MINI_MAP_HEIGHT = map.getGameSceneHeight()/MINI_MAP_RATIO;
	private ShapeRenderer shapeRenderer;
	 
	public GameScreen(TheLegendOfSopra orch) 
	{
		miniMap = new Texture(Gdx.files.internal("screen/assets/miniMap.png"));
		sound = Gdx.audio.newSound(Gdx.files.internal("com/zelda/world/Music.mp3"));
		sound.play();
		camera = new OrthographicCamera();
		miniCamera = new OrthographicCamera();
        camera.setToOrtho(false, map.getWidth(), map.getHeight());
		parent = orch;
		map.create();//Création de la map (batch & texture)
		link.create();//Création du personnage (sprite)
		hud=new Hud(map.batch);
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
		link.render(); //Commande de déplacement personnage
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
//		draw(Texture texture, float x, float y, float width, float height)
//		Draws a rectangle with the bottom left corner at x,y and stretching the region to cover the given width and height.
		map.batch.draw(miniMap,  camera.position.x-map.getWidth()/2, camera.position.y+map.getHeight()/3+15, MINI_MAP_WIDTH,MINI_MAP_HEIGHT);
        map.batch.setProjectionMatrix(camera.invProjectionView);
        camera.position.set(link.getLinkX() , link.getLinkY() , 0);
        camera.update();

        map.batch.end();
        shapeRenderer = new ShapeRenderer();
		
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.rect(link.getLinkX()-map.getWidth()/2-2 + link.getLinkX()/MINI_MAP_RATIO, (link.getLinkY()+map.getHeight()/3+14)+link.getLinkY()/MINI_MAP_RATIO,2, 2);
        shapeRenderer.end();
        
        if(Gdx.input.isKeyPressed(Keys.TAB)) {
        		System.out.println("PRESSED");
        		parent.changeScreen(TheLegendOfSopra.MINIMAP);
			}
        
	}
}
