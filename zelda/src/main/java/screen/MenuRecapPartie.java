package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.zelda.TheLegendOfSopra;



public class MenuRecapPartie implements Screen{
	
	private TheLegendOfSopra parent;
	private Stage stage;
	private Table table;
	private Skin skin;
	
	private Label titre;
	private Label infoMap;
	private Label infoAvatar;
	private Label infoArme;
	
	private TextButton commencerPartie;
	private TextButton annuler;
	
	public MenuRecapPartie(TheLegendOfSopra orch) 
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

		skin = new Skin (Gdx.files.internal("screen/assets/defaut/uiskin.json"));
		
		titre = new Label ("RECAPITULATIF DE LA PARTIE", skin);
		infoMap = new Label ("Map : "+MenuPrincipal.part.getIdMap(), skin);
//		infoAvatar = new Label ("Avatar : "+MenuPrincipal.userConnected.getAvatar().getId(), skin);
//		infoArme = new Label ("Arme : "+MenuPrincipal.userConnected.getAvatar().getArme().name(), skin);
		
		commencerPartie = new TextButton("Commencer la partie", skin);
		annuler = new TextButton("Annuler", skin);
		
		table.add(titre);
		table.row();
		table.add(infoMap);
		table.row();
		table.add(infoAvatar);
		table.row();
		table.add(infoArme);
		table.row();
		table.add(commencerPartie);
		table.add(annuler);
		
		annuler.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				parent.changeScreen(TheLegendOfSopra.INFOS);
			}
		});
		
		commencerPartie.addListener(new ChangeListener() {
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
		Gdx.input.setInputProcessor(stage);
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
