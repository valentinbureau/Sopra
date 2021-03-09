package menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuModifMap implements Screen {

	private Orchestrator parent;
	private Stage stage;
	private Table table;
	private Skin skin;
	
	private Label titre;
	private Label map;
	private SelectBox choixMap;
	
	private TextButton valider;

	public MenuModifMap(Orchestrator orch) 
	{
		parent = orch;
		stage = new Stage (new ScreenViewport());

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		stage.clear();
		table = new Table();
		table.setFillParent(true);
		table.setDebug(true);
		stage.addActor(table);

		skin = new Skin (Gdx.files.internal("C:\\Users\\utilisateur\\Documents\\Perso\\Projet\\Sopra\\zelda\\core\\src\\menu\\assets\\glassy\\glassy-ui.json"));

		titre = new Label ("MODIF DE LA MAP",skin);
		map= new Label ("Choix de la map : ",skin);
		
		choixMap = new SelectBox (skin);
		choixMap.setItems(MenuPrincipal.map);
		
		valider = new TextButton ("Valider", skin);
		
		table.add(titre);
		table.row();
		table.add(map);
		table.add(choixMap);
		table.row();
		table.add(valider);
		
		valider.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				MenuPrincipal.part.setIdMap(choixMap.getSelectedIndex());
				parent.changeScreen(Orchestrator.INFOS);
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
