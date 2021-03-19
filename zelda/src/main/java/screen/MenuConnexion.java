package screen;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
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
import com.zelda.Joueur;
import com.zelda.TheLegendOfSopra;

import util.Context;

public class MenuConnexion implements Screen{

	private TheLegendOfSopra parent;
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

	private boolean verifLogin = false;

	public MenuConnexion(TheLegendOfSopra orch) 
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

		titre = new Label ("CONNEXION",skin);
		loginLabel = new Label ("Login : ", skin);
		pwdLabel = new Label("Mot de passe : ", skin);

		badLogin = new Label ("", skin);
		badPwd = new Label ("", skin);

		saisieLogin = new TextField(null, skin);
		saisiePwd = new TextField(null, skin);

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
				connexion();
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
	public void connexion()
	{
		String log = saisieLogin.getText();
		String pass = saisiePwd.getText();
		System.out.println(log+ " - " + pass);
		verifLogin = false;
		List<Joueur> joueurs = Context.getInstance().getDaoJoueur().findAll();
		for (Joueur j : joueurs)
		{
			if (j.getLogin().equals(log))
			{
				verifLogin = true;
				break;
			}
		}
		if (verifLogin==true)
		{
			if (Context.getInstance().getDaoJoueur().checkConnect(log, pass) == null)
			{
				badPwd.setText("Mot de passe incorrect");
				badLogin.setText(null);
			}
			else
			{
				MenuPrincipal.userConnected = Context.getInstance().getDaoJoueur().checkConnect(log, pass);
				parent.changeScreen(TheLegendOfSopra.INFOS);
			}
		}
		else
		{
			badLogin.setText("Login Inconnu");
			badPwd.setText(null);
		}
	
	}
}
