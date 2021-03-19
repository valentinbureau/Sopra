package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import util.Context;

public class Hud implements Screen{  
	//head-up display : show Most info that stay visible during gameplay  
	public Stage stage;  
	private Viewport viewport; //-- Specific viewport for the static view of the HUD  
	static FileHandle fontFile;
	BitmapFont bitmapfont;
	Label lifeLabel;
	Image img;
	private OrthographicCamera hudCamera;

	private TextButton quit;
	private TextButton music;
	public Hud(SpriteBatch sb)  
	{  
		hudCamera = new OrthographicCamera(GameScreen.map.getWidth(), GameScreen.map.getHeight()+60);
		viewport=new FitViewport(GameScreen.map.getWidth(),GameScreen.map.getHeight()+60,hudCamera);  
		GameScreen.map.batch.setProjectionMatrix(hudCamera.combined);
		//-- Définition de l'écran  
		stage=new Stage(viewport,sb); 
		img = new Image (new Texture(Gdx.files.internal("screen/assets/heart.png")));
		//img.getAlign();
		//img.setScale(0.06f);
		fontFile = Gdx.files.internal("screen/assets/zelda.fnt");
		bitmapfont = new BitmapFont(fontFile);
		bitmapfont.getData().setScale(0.8f);
		//-- On rempli notre écran  

	}
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		stage.clear();
		Table table=new Table();  
		table.setWidth(stage.getWidth());
		table.top().left(); //-- Bandeau haut  
		table.setFillParent(true);  
		table.align(Align.right|Align.top);
		//	table.setPosition(0, Gdx.graphics.getHeight());
		lifeLabel   =new Label("Lives",new Label.LabelStyle(bitmapfont, Color.WHITE));  
		table.add(lifeLabel).top().right().padRight(20).row();//.expandX().padTop(10); 

		Table tableImg=new Table();  
		//tableImg.add(img).width(28).height(20).left();//.padTop(15);
		tableImg.setSize(GameScreen.link.getVie(), 1);
		System.out.println(tableImg.getWidth());
		tableImg.clear();
		for (int i = 0 ; i < tableImg.getWidth() ; ++i)
		{
			tableImg.add(new Image (new Texture(Gdx.files.internal("screen/assets/heart.png")))).width(28).height(20).left();//.padTop(15);
		}
		table.add(tableImg);

		Table table2=new Table();  
		table2.setWidth(stage.getWidth());
		table2.bottom().left();
		table2.setFillParent(true);
		Skin skin = new Skin (Gdx.files.internal("screen/assets/defaut/uiskin.json"));
		quit = new TextButton("Quitter", skin);
		table2.add(quit);

		music = new TextButton("No Music", skin);
		table2.add(music).bottom().right().padRight(10);

		stage.addActor(table);  
		stage.addActor(table2);  
		quit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Etat Joueur : " + MenuPrincipal.userConnected.getAvatar().toString());
				Context.getInstance().getDaoJoueur().save(MenuPrincipal.userConnected);
				Gdx.app.exit();
			}
		});

		music.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (music.getText().equals("No Music"))
				{
					GameScreen.sound.stop();
					music.setText("Music");

				}
				else
				{
					GameScreen.sound.play();
					music.setText("No Music");
				}
			}
		});

	}
	@Override
	public void render(float delta) {
		//		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		Gdx.input.setInputProcessor(stage);
		stage.draw();

	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
