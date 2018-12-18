package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Constants;
import com.mygdx.game.Pachon;
import com.mygdx.game.managers.GameStateManager;
import static com.mygdx.game.Constants.Score;

public class GameOverState extends GameState {

	float acc = 0f;
	protected SpriteBatch batch;
    public BitmapFont font48;
    private Integer score = 0;
    private TextButton tb, tb2, tb3;
    private Skin skin;
    private Stage stage;
    private String Gamtend_text = "Game Over";
	public String getGamtend_text() {
		return Gamtend_text;
	}

	public void setGamtend_text(String gamtend_text) {
		Gamtend_text = gamtend_text;
	}

	public GameOverState(final GameStateManager gsm, String Gameend_text) {
		super(gsm);
		this.Gamtend_text = Gameend_text;
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("..\\core\\assets\\gdx-skins-master\\flat-earth\\skin\\flat-earth-ui.json"));
		tb = new TextButton("Main Menu", skin);
		tb2 = new TextButton("Exit", skin);
		tb.setSize(200, 60);
		tb2.setSize(200, 60);
		
		tb.setPosition(210, 100);
		tb2.setPosition(420, 100);
		tb.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	gsm.setState(new SplashState(gsm));
            	Constants.Score = 0;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
		tb2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	Gdx.app.exit();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		stage.addActor(tb);
		stage.addActor(tb2);
		
		initFonts();
		font48.setColor(Color.WHITE);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font48.draw(batch, Gamtend_text, Gdx.graphics.getWidth()/2 - 125, Gdx.graphics.getHeight()/2 + 150);
		font48.draw(batch, String.format("Score: %d", Score), Gdx.graphics.getWidth()/2 - 100, Gdx.graphics.getHeight()/2 + 50);
		batch.end();
		stage.draw();
		
	}
	
	public void initFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("..\\core\\assets\\img\\fonts\\Arcon.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
		
		params.size = 48;
		params.color = Color.WHITE;
		font48 = generator.generateFont(params);
	}
	 
	
	public void getScore(int value) {
		this.score = value;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
    
}
