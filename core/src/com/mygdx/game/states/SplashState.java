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
import com.mygdx.game.Pachon;
import com.mygdx.game.managers.GameStateManager;

public class SplashState extends GameState{
	
	float acc = 0f;
	protected SpriteBatch batch;
    private Sprite pic, pic2;
    private Texture tex, playBtn;
    public BitmapFont font48, font;
	public SplashState(GameStateManager gsm) {
		super(gsm);
		tex = new Texture("..\\core\\assets\\img\\Players\\Player Green\\background.png");
		playBtn = new Texture("..\\core\\assets\\img\\Other\\playBtn.png");
		pic = new Sprite(tex);
		pic2 = new Sprite(playBtn);
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(2);
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
		font48.draw(batch, "pachon VS World", (Gdx.graphics.getWidth()/2) - playBtn.getWidth() - 75, Gdx.graphics.getHeight()/2 + 100);
		font.draw(batch, "Press ESC to exit", (Gdx.graphics.getWidth()/2) - 115, Gdx.graphics.getHeight()/3 - 35);
		batch.draw(pic2, (Gdx.graphics.getWidth()/2) - (playBtn.getWidth() / 2), Gdx.graphics.getHeight()/3);
		batch.end();
		if(Gdx.input.justTouched()) {
			gsm.setState(new PlayState(gsm));
			dispose();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		tex.dispose();
		playBtn.dispose();
	}
	
	public void initFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("..\\core\\assets\\img\\fonts\\Arcon.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
		
		params.size = 48;
		params.color = Color.BLACK;
		font48 = generator.generateFont(params);
	}
	
}
