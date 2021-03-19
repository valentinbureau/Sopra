package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zelda.TheLegendOfSopra;

import util.Context;

public class GameOver implements Screen{

	private TheLegendOfSopra parent = new TheLegendOfSopra();
	private Stage stage;
	private Table table;
	private Table tableImg;
	private Texture texture;
	private Image img;

//	private TextButton quitter;
//	private TextButton retry;

	private Skin skin;

	public GameOver(TheLegendOfSopra orch) {
		parent = orch;
		stage = new Stage (new ScreenViewport());
		
	}

	@Override
	public void show() {
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin (Gdx.files.internal("screen/assets/defaut/uiskin.json"));
		tableImg = new Table();
		tableImg.setFillParent(true);
		tableImg.setDebug(true);
		//tableImg.align(Align.top);
		texture = new Texture(Gdx.files.internal("screen/assets/Game_Over_Modele.png"));
		img = new Image (texture);
		table = new Table();
		table.setFillParent(true);
		table.setDebug(true);
		
		table.align(Align.bottom | Align.center);
		tableImg.add(img).row();
		stage.addActor(tableImg);
		stage.addActor(table);
		TextButton quitter = new TextButton("Quitter", skin);
		TextButton retry = new TextButton("Reesayer", skin);
		table.add(quitter).padBottom(10).padRight(10);
		table.add(retry).padBottom(10).padLeft(10);
		MenuPrincipal.userConnected.getAvatar().setVie(8);
		MenuPrincipal.userConnected.getAvatar().setPosX(5920);
		MenuPrincipal.userConnected.getAvatar().setPosY(1700);
		Context.getInstance().getDaoJoueur().save(MenuPrincipal.userConnected);
		
		quitter.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				
				Gdx.app.exit();
			}
		});
		
		retry.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				parent.changeScreen(TheLegendOfSopra.APP);
			}
		});

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
		stage.getViewport().update(width, height, true);

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
