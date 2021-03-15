package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud {  
	//head-up display : show Most info that stay visible during gameplay  
	public Stage stage;  
	private Viewport viewport; //-- Specific viewport for the static view of the HUD  
	static FileHandle fontFile;
	BitmapFont bitmapfont;
	Label lifeLabel;
	Image img;
	private OrthographicCamera hudCamera;
	public Hud(SpriteBatch sb)  
	{  
		hudCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
		Table table=new Table();  
		table.setWidth(stage.getWidth());
		table.top().left(); //-- Bandeau haut  
		table.setFillParent(true);  
		table.align(Align.right|Align.top);
	//	table.setPosition(0, Gdx.graphics.getHeight());
		lifeLabel   =new Label("Lives",new Label.LabelStyle(bitmapfont, Color.WHITE));  
		  table.add(lifeLabel).top().center().row();//.expandX().padTop(10); 
		  Table tableImg=new Table();  
		//tableImg.add(img).width(28).height(20).left();//.padTop(15);
		  for (int i = 0 ; i < 6 ; ++i)
		  {
			  tableImg.add(new Image (new Texture(Gdx.files.internal("screen/assets/heart.png")))).width(28).height(20).left();//.padTop(15);
		  }
		table.add(tableImg);
		// table.row();  
		// table.add(lifeLabel).expandX();   
		stage.addActor(table);  
	}  
}
