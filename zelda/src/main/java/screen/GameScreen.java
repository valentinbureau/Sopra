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
import com.zelda.Link;
import com.zelda.Monstre;
import com.zelda.TheLegendOfSopra;
import com.zelda.world.GameMap;

public class GameScreen extends ApplicationAdapter implements Screen{
	
	private static float linkX =5920;
	private static float linkY=1700;
	private static float linkSpeed = 100;
	private static int linkVie = 8;

	
	private TheLegendOfSopra parent;
	private Hud hud;
	
	static FileHandle fontFile;
	BitmapFont bitmapfont;
	Sound sound;
	OrthographicCamera camera;

	static Link link = new Link(linkX,linkY, linkVie,linkSpeed); // Initialisation du Joueur
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
		sound = Gdx.audio.newSound(Gdx.files.internal("com/zelda/world/Music.mp3"));
		sound.play();
		camera = new OrthographicCamera();
        camera.setToOrtho(false, map.getWidth(), map.getHeight());
		parent = orch;
		map.create();//Création de la map (batch & texture)
		//link.create();//Création du personnage (sprite)
		MenuPrincipal.userConnected.getAvatar().create();
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
		
		hud.render(delta);
	}
	
	public void draw()
	{
		//link.render(); //Commande de déplacement personnage
		//System.out.println("SPEED : "+MenuPrincipal.userConnected.getAvatar().getSpeed());
		MenuPrincipal.userConnected.getAvatar().render(); //Commande de déplacement personnage
		monstre.render();//Déplacement Monstre
		monstre1.render();
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.position.set(MenuPrincipal.userConnected.getAvatar().getPosX(), MenuPrincipal.userConnected.getAvatar().getPosY(), 0);
		camera.update();
		map.batch.setProjectionMatrix(camera.combined); 
		map.batch.begin();
		map.batch.draw(map.gameScene, 0, 0,10000,3424);//Affichage map
		map.batch.draw(MenuPrincipal.userConnected.getAvatar().getSprite(), MenuPrincipal.userConnected.getAvatar().getPosX(), MenuPrincipal.userConnected.getAvatar().getPosY());//Affichage personnage

		map.batch.draw(monstre.getSprite(), monstre.getMonstreX(), monstre.getMonstreY()); //Affichage Monstre
		map.batch.draw(monstre1.getSprite(), monstre1.getMonstreX(), monstre1.getMonstreY());

//		draw(Texture texture, float x, float y, float width, float height)
//		Draws a rectangle with the bottom left corner at x,y and stretching the region to cover the given width and height.
		map.batch.draw(miniMap,  camera.position.x-map.getWidth()/2, camera.position.y+map.getHeight()/3+15, MINI_MAP_WIDTH,MINI_MAP_HEIGHT);
        map.batch.setProjectionMatrix(camera.invProjectionView);
        camera.position.set(MenuPrincipal.userConnected.getAvatar().getPosX() , MenuPrincipal.userConnected.getAvatar().getPosY() , 0);
        camera.update();

        map.batch.end();
        shapeRenderer = new ShapeRenderer();
		
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.rect(MenuPrincipal.userConnected.getAvatar().getPosX()-map.getWidth()/2-2 + MenuPrincipal.userConnected.getAvatar().getPosX()/MINI_MAP_RATIO, (MenuPrincipal.userConnected.getAvatar().getPosY()+map.getHeight()/3+14)+MenuPrincipal.userConnected.getAvatar().getPosY()/MINI_MAP_RATIO,2, 2);
        shapeRenderer.end();
        
        if(Gdx.input.isKeyPressed(Keys.TAB)) {
        		System.out.println("PRESSED");
        		parent.changeScreen(TheLegendOfSopra.MINIMAP);
			}
        hud.show();
	}
}
