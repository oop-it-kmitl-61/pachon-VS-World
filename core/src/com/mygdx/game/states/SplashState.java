package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Pachon;
import com.mygdx.game.managers.GameStateManager;

public class SplashState extends GameState{
	
	float acc = 0f;
	protected SpriteBatch batch;
    private Sprite pic;
    private Stage stage;
    private Texture tex;
    public BitmapFont font48;
    private TextButton tb, tb2;
    private Skin skin;
	public SplashState(final GameStateManager gsm) {
		super(gsm);
		tex = new Texture("..\\core\\assets\\img\\Players\\Player Green\\background.png");
		pic = new Sprite(tex);
		
		skin = new Skin(Gdx.files.internal("..\\core\\assets\\gdx-skins-master\\flat-earth\\skin\\flat-earth-ui.json"));
		tb = new TextButton("New Game", skin);
		tb2 = new TextButton("Exit", skin);
		tb.setSize(200, 60);
		tb2.setSize(200, 60);
		tb.setPosition(840/2 - (tb.getWidth()/2), 600/2 - 25);
		tb2.setPosition(840/2 - (tb.getWidth()/2), 600/3);
		tb.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	gsm.setState(new PlayState(gsm));
            	dispose();
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
		
		batch = new SpriteBatch();
		initFonts();
		
	}
 
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(pic, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		font48.draw(batch, "Pach0n VS World", (Gdx.graphics.getWidth()/2) - 185, Gdx.graphics.getHeight()/2 + 150);
		batch.end();
		stage.draw();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		tex.dispose();
		font48.dispose();
		batch.dispose();
		stage.dispose();
	}
	
	public void initFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("..\\core\\assets\\img\\fonts\\Arcon.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
		
		params.size = 48;
		params.color = Color.WHITE;
		font48 = generator.generateFont(params);
	}
	
}
