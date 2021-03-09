package menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuInfosJoueur implements Screen{
	
	private Orchestrator parent;
	private Stage stage;
	private Table table;
	private Skin 		skin ;

	
	private Label titre;
	private Label recap;

	
	private TextButton modifAvatar ;
	private TextButton modifMap;
	private TextButton modifProfil;
	private TextButton commencerPartie ;
	private TextButton deconnexion ;
	
	public MenuInfosJoueur(Orchestrator orch) 
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
		skin = new Skin (Gdx.files.internal("C:\\Users\\utilisateur\\Documents\\Perso\\Projet\\Sopra\\zelda\\core\\src\\menu\\assets\\default\\uiskin.json"));
		 titre = new Label ("MENU DE "+MenuPrincipal.userConnected.getLogin(), skin);
		recap = new Label ("Derniere partie jouée : Map - "+MenuPrincipal.part.getIdMap() + " - Avatar : "+MenuPrincipal.userConnected.getIdAvatar()+" - Arme : "+MenuPrincipal.userConnected.getIdArme(), skin);
		modifAvatar = new TextButton("Modifier l'Avatar", skin);
		modifMap = new TextButton("Modifier la Map", skin);
		modifProfil =new TextButton("Modifier le profil", skin);
		commencerPartie =new TextButton("Commencer la partie", skin);
		deconnexion =new TextButton("Deconnexion", skin);
		
		
		table.add(titre).colspan(2);
		table.row();
		table.add(recap).colspan(2);
		table.row();
		table.add(modifAvatar);
		table.add(modifMap);
		table.row();
		table.add(modifProfil);
		table.add(commencerPartie);
		table.row();
		table.add(deconnexion).colspan(2);
		
		deconnexion.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				parent.changeScreen(Orchestrator.PRINCIPAL);
			}
		});
		
		modifAvatar.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				parent.changeScreen(Orchestrator.AVATAR);
			}
		});
		
		modifMap.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				parent.changeScreen(Orchestrator.MAP);
			}
		});
		
		modifProfil.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				parent.changeScreen(Orchestrator.PROFIL);
			}
		});
		
		commencerPartie.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				parent.changeScreen(Orchestrator.PARTIE);
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
