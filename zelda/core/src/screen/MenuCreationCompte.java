package screen;
import com.zelda.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuCreationCompte implements Screen{

	private TheLegendOfSopra parent;
	private Stage stage;
	private Table table;
	private Skin skin;

	private Label loginLabel;
	private Label pwdLabel;
	private Label confPwdLabel;
	private Label badPwdLabel;
	private Label mailLabel;

	private TextField saisieLogin;
	private TextField saisiePwd;
	private TextField saisieConfPwd;
	private TextField saisieMail;

	private TextButton valider;

	public MenuCreationCompte(TheLegendOfSopra orch) 
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

		loginLabel = new Label ("Login : ", skin);
		pwdLabel = new Label("Mot de passe : ", skin);
		confPwdLabel = new Label ("Confirmation du mot de passe", skin);
		mailLabel = new Label ("E-Mail : ", skin);
		badPwdLabel = new Label ("Mail", skin);
		
		saisieLogin = new TextField("login", skin);
		saisiePwd = new TextField(null, skin);
		saisieConfPwd = new TextField(null, skin);
		saisieMail = new TextField(null, skin);
		
		valider = new TextButton("Valider", skin);

		table.add(loginLabel).left();
		table.add(saisieLogin);
		table.row();
		table.add(pwdLabel).left();
		table.add(saisiePwd);
		table.row();
		table.add(confPwdLabel).left();
		table.add(saisieConfPwd);
		table.add(badPwdLabel);
		table.row();
		table.add(mailLabel).left();
		table.add(saisieMail);
		table.row();
		table.add(valider).colspan(2);
		
		valider.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				if (verifPwd(saisiePwd.getText(), saisieConfPwd.getText()))
				{
					CompteUtilisateur newC= new CompteUtilisateur(saisieLogin.getText(), saisieMail.getText(), saisiePwd.getText());
					MenuPrincipal.userConnected = newC;
					parent.changeScreen(TheLegendOfSopra.INFOS);
				}
				else
				{
					badPwdLabel.setText("Mot de passe incorrect");
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

	public String getLogin()
	{
		System.out.println(saisieLogin.getText());
		return saisieLogin.getText();
	}

	public String getPwd()
	{
		String pwd1;
		String pwd2;
		do
		{
			pwd1 = saisiePwd.getText();
			pwd2 = saisieConfPwd.getText();
			if (!(pwd1.equals(pwd2)))
			{
				badPwdLabel.setText("Mots de passe differents");
			}
			else
			{
				badPwdLabel.setText(null);
			}
		}while(!(pwd1.equals(pwd2)));
		return pwd1;
	}
	
	public boolean verifPwd(String pwd1, String pwd2)
	{
		return pwd1.equals(pwd2);
	}
}
