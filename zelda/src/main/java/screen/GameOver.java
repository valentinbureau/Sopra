package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.zelda.TheLegendOfSopra;

public class GameOver implements Screen{
	
	private TheLegendOfSopra parent = new TheLegendOfSopra();
	private Stage stage;
	private Table table;
	private Texture texture;
	private Image img;
	private int vie;
	private boolean victory;

	public GameOver(TheLegendOfSopra orch) {
		parent = orch;
		stage = new Stage (new ScreenViewport());
	}

	@Override
	public void show() {
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		
		
		table = new Table();
		table.setFillParent(true);
		table.setDebug(true);
		stage.addActor(table);
		table.align(Align.center);
		
		texture = new Texture(Gdx.files.internal("screen/assets/Game_Over_Modele.png"));
		img = new Image (texture);
		table.add(img);
		
		stage.addActor(img);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
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
		stage.dispose();
	}

}
