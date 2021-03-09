package menu;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.zelda.CompteUtilisateur;

public class MenuConnexion implements Screen{
	
	private Orchestrator parent;
	private Stage stage;
	private Table table;
	private Skin skin;
	
	private Label titre;
	private Label loginLabel;
	private Label pwdLabel;
	
	private Label badLogin;
	private Label badPwd;
	
	private TextField saisieLogin;
	private TextField saisiePwd;
	
	private TextButton connexion;
	
	public MenuConnexion(Orchestrator orch) 
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

		titre = new Label ("CONNEXION",skin);
		loginLabel = new Label ("Login : ", skin);
		pwdLabel = new Label("Mot de passe : ", skin);
		
		badLogin = new Label ("", skin);
		badPwd = new Label ("", skin);
		
		saisieLogin = new TextField(MenuPrincipal.userConnected.getLogin(), skin);
		saisiePwd = new TextField(MenuPrincipal.userConnected.getPassword(), skin);
		
		connexion = new TextButton("Valider", skin);
		
		table.add(titre);
		table.row();
		table.add(loginLabel);
		table.add(saisieLogin);
		table.add(badLogin);
		table.row();
		table.add(pwdLabel);
		table.add(saisiePwd);
		table.add(badPwd);
		table.row();
		table.add(connexion);
		
		connexion.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				String log = saisieLogin.getText();
				String pass = saisiePwd.getText();
				System.out.println(log+ " - " + pass);
				boolean essai = false;
				Iterator<String>it=CompteUtilisateur.getListUtilisateur().keySet().iterator();
				do
				{
					String key=it.next();
					if (key.equals(log))
					{
						essai=true;
						MenuPrincipal.userConnected=CompteUtilisateur.getListUtilisateur().get(key);
					}
					else
					{
						essai=false;
					}
				}while(it.hasNext() && (essai == false));
				if (essai == false)
				{
					badLogin.setText("Login inconnu");
				}
				else if (!(pass.equals(MenuPrincipal.userConnected.getPassword())))
				{
					badPwd.setText("Mot de passe incorrect");
				}
				else
				{
					parent.changeScreen(Orchestrator.INFOS);
				}
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
