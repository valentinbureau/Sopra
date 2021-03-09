package menu;

import com.zelda.*;

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
	
	private Orchestrator parent = new Orchestrator();
	private Stage stage;
	private Table table;
	private Skin skin;
	
	static CompteUtilisateur userConnected;
	static Partie part = new Partie();
	static String arme[] = {"1 - Epee", "2 - Massue"};
	static String avatar[] = {"1 - Vert", "2 - Bleu", "3 - Jaune", "4 - Violet" };
	static String map[] = {"1 -Campagne"};
	
	public CompteUtilisateur getUserConnected() {
		return userConnected;
	}

	public void setUserConnected(CompteUtilisateur userConnected) {
		this.userConnected = userConnected;
	}

	public MenuPrincipal(Orchestrator orch) 
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
		
		skin = new Skin (Gdx.files.internal("C:\\Users\\utilisateur\\Documents\\Perso\\Projet\\Sopra\\zelda\\core\\src\\menu\\assets\\glassy\\glassy-ui.json"));
		
		TextButton creationCompte = new TextButton ("Creation Compte", skin);
		TextButton quitter = new TextButton("Quitter", skin);
		
		if (!(CompteUtilisateur.getListUtilisateur().isEmpty()))
		{
			TextButton connexion = new TextButton ("Connexion", skin);
			table.add(connexion).fillX().uniformX();
			table.row().pad(10,0,10,0);
			
			connexion.addListener(new ChangeListener() {
				public void changed (ChangeEvent event, Actor actor) {
					parent.changeScreen(Orchestrator.CONNEXION);
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
				parent.changeScreen(Orchestrator.CREATION);
			}
		});
		
		
		
	}

	@Override
	public void render(float delta) {
		//parent.changeScreen(Orchestrator.PRINCIPAL);
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
