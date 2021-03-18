package screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.zelda.TheLegendOfSopra;
import com.zelda.world.GameMap;

import screen.GameScreen;

public class BigMiniMap extends ApplicationAdapter implements Screen{
	
	public Stage stage; 
	private TheLegendOfSopra parent;
	private Texture miniMap;
	private static final int MINI_MAP_RATIO = 15;
	private static final int MINI_MAP_WIDTH = GameScreen.map.getGameSceneWidth()/MINI_MAP_RATIO;
	private static final int MINI_MAP_HEIGHT = GameScreen.map.getGameSceneHeight()/MINI_MAP_RATIO;
	private ShapeRenderer shapeRenderer;
	OrthographicCamera camera;
	
	static GameMap map = new GameMap();

	public BigMiniMap(TheLegendOfSopra orch) {
		parent = orch;
		//	map = GameScreen.map;
		//miniMap = new Texture(Gdx.files.internal("com/zelda/world/World.png"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false,GameScreen.map.getGameSceneWidth(),GameScreen.map.getGameSceneHeight());
		map.create();
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		draw();

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
	public void dispose() {
		map.batch.dispose();
		//map.gameScene.dispose();

	}
	
	public void draw()
	{
		//link.render(); //Commande de déplacement personnage
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//camera.position.set(GameScreen.link.getLinkX(), GameScreen.link.getLinkY(), 0);
		//camera.update();
		map.batch.setProjectionMatrix(camera.combined); 
		map.batch.begin();
		map.batch.draw(GameScreen.map.gameScene, 0, 0,10000,3424);//Affichage map
		map.batch.draw(MenuPrincipal.userConnected.getAvatar().getSprite(),MenuPrincipal.userConnected.getAvatar().getPosX(), MenuPrincipal.userConnected.getAvatar().getPosY());//Affichage personnage
//        camera.position.set(GameScreen.link.getLinkX() , GameScreen.link.getLinkY() , 0);
//        camera.update();
		
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.rect(MenuPrincipal.userConnected.getAvatar().getPosX(), MenuPrincipal.userConnected.getAvatar().getPosY(),50, 50);
        shapeRenderer.end();
        
        map.batch.end();
		
		if(!(Gdx.input.isKeyPressed(Keys.TAB))) {
    		System.out.println("PRESSED");
    		parent.changeScreen(TheLegendOfSopra.APP);
		}
	}

}
