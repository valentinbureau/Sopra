package screen;

import com.zelda.*;

import util.Context;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuPrincipal implements Screen{
	
	private TheLegendOfSopra parent = new TheLegendOfSopra();
	private Stage stage;
	private Table table;
	private Skin skin;
	
	static Joueur userConnected;
	static Partie part = new Partie();
	static String arme[] = {"EPEE", "MASSUE"};
	static String avatar[] = {"1", "2"};
	static String map[] = {"1 -Campagne"};
	
	public Joueur getUserConnected() {
		return userConnected;
	}

	public void setUserConnected(Joueur userConnected) {
		this.userConnected = userConnected;
	}

	public MenuPrincipal(TheLegendOfSopra orch) 
	{
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
		
		skin = new Skin (Gdx.files.internal("screen/assets/defaut/uiskin.json"));
		
		TextButton creationCompte = new TextButton ("Creation Compte", skin);
		TextButton quitter = new TextButton("Quitter", skin);
		
		if (Context.getInstance().getDaoJoueur().findAll()!=null)
		{
			TextButton connexion = new TextButton ("Connexion", skin);
			table.add(connexion).fillX().uniformX();
			table.row().pad(10,0,10,0);
			
			connexion.addListener(new ChangeListener() {
				public void changed (ChangeEvent event, Actor actor) {
					parent.changeScreen(TheLegendOfSopra.CONNEXION);
				}
			});
		}
		table.add(creationCompte).fillX().uniformX();
		table.row().pad(10,0,10,0);
		table.add(quitter).fillX().uniformX();
		
		quitter.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		
		creationCompte.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				parent.changeScreen(TheLegendOfSopra.CREATION);
			}
		});
	}

	@Override
	public void render(float delta) {
		//parent.changeScreen(TheLegendOfSopra.PRINCIPAL);
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
