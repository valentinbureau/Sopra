package screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.zelda.Link;
import com.zelda.TheLegendOfSopra;

import util.Context;

public class MenuModifAvatar implements Screen{
	
	private TheLegendOfSopra parent;
	private Stage stage;
	private Table table;
	private Skin skin;
	
	private Label titre;
	
	private SelectBox choixAvatar;
	private SelectBox choixArme;
	
	private TextButton valider;
	
	private Label avatar;
	private Label arme;
	
	private Image img;
	
	Texture linkTexture = new Texture ("com/zelda/link2.png");
	Texture linkTexture2 = new Texture ("com/zelda/Link.png");
	TextureRegion[][] tmpFrames = TextureRegion.split(linkTexture, 45, 44);
	
	public MenuModifAvatar(TheLegendOfSopra orch) 
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

		titre = new Label ("MODIFICATION DE L'AVATAR", skin);
		
		choixArme = new SelectBox(skin);
		choixArme.setItems(MenuPrincipal.arme);
		
		choixAvatar = new SelectBox (skin);
		choixAvatar.setItems(MenuPrincipal.avatar);
		
		avatar = new Label ("Avatar", skin);
		arme = new Label ("Arme", skin);
		
		valider = new TextButton ("Valider", skin);
		
		//System.out.println(choixAvatar.getSelectedIndex());
		if (choixAvatar.getSelectedIndex()==0)
		{
			System.out.println("ID 0");
			img=new Image (linkTexture2);
		}
		else
		{
			System.out.println("ID 1");
			img =  new Image (tmpFrames[0][0]);
		}
		table.add(titre);
		table.row();
		table.add(avatar);
		table.add(choixAvatar);
		table.add(img);
		table.row();
		table.add(arme);
		table.add(choixArme);
		table.row();
		table.add(valider);
		
//		valider.addListener(new ChangeListener() {
//			public void changed (ChangeEvent event, Actor actor) {
//				if (MenuPrincipal.userConnected.getAvatar()==null)
//				{
//					Link newL = new Link(GameScreen.link.getPosX(), GameScreen.link.getPosY(), GameScreen.link.getVie(), GameScreen.link.getSpeed());
//					MenuPrincipal.userConnected.setAvatar(newL);
//				}
//				MenuPrincipal.userConnected.getAvatar().(choixAvatar.getSelectedIndex()+1);
//				System.out.println(choixArme.getSelected().toString());
//				MenuPrincipal.userConnected.getAvatar().setArme(Arme.valueOf(choixArme.getSelected().toString()));
//				Context.getInstance().getDaoJoueur().save(MenuPrincipal.userConnected);
//				parent.changeScreen(TheLegendOfSopra.INFOS);
//			}
//		});
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
