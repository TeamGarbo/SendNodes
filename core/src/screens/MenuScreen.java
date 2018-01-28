package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sendnodes.GameController;
import com.sendnodes.Properties;
import com.sendnodes.ui.ButtonMaker;
import com.sendnodes.ui.ButtonContainer;

public class MenuScreen {	
    Stage stage;   
    ButtonContainer container;
	
	public MenuScreen(Stage stage) {
		this.stage = stage;
	}
	
	public void create() {
        container = new ButtonContainer(Properties.SCREEN_WIDTH/2-100,Properties.SCREEN_HEIGHT* 3/4,200,Properties.SCREEN_HEIGHT/2,200,100, stage);
     
        TextButton tb = ButtonMaker.getBasicButton("Play");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	GameController.getInstance().setScreenNumber(1);
            }
        });   
        container.addButton(tb);        
        
        tb = ButtonMaker.getBasicButton("Settings");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	//GameController.getInstance().setScreenNumber(1);
            }
        });   
        container.addButton(tb);
        
        tb = ButtonMaker.getBasicButton("Exit");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	System.exit(0);
            }
        });   
        container.addButton(tb);
        
        container.resizeActors();
        
/*		uiContainer = new UIContainer(Properties.SCREEN_WIDTH/2-100,Properties.SCREEN_HEIGHT* 3/4);
		
        container = new ButtonContainer(0,Properties.SCREEN_HEIGHT,200,Properties.SCREEN_HEIGHT/2,200,100);
        
        TextButton tb = ButtonMaker.getBasicButton("Play");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	GameController.getInstance().setScreenNumber(1);
            }
        });   
        container.addButton(tb);        
        
        tb = ButtonMaker.getBasicButton("Settings");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	//GameController.getInstance().setScreenNumber(1);
            }
        });   
        container.addButton(tb);
        
        tb = ButtonMaker.getBasicButton("Exit");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	System.exit(0);
            }
        });   
        container.addButton(tb);
        //container.resizeActors();
        
        incrementor = new Incrementor(0,Properties.SCREEN_HEIGHT/2,200,100, inc);
        
        uiContainer.addContainer(incrementor);
        uiContainer.addContainer(container);
        
        uiContainer.resizeActors();*/
	}

	
	public void render(SpriteBatch batch) {
		batch.end();
		batch.begin();
		stage.draw();
		container.render(batch);
	}
	
}
