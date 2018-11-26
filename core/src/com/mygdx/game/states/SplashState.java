package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Pachon;
import com.mygdx.game.managers.GameStateManager;

public class SplashState extends GameState{
	
	float acc = 0f;
	protected SpriteBatch batch;
    private Sprite pic, pic2;
    private Texture tex, playBtn;
    public BitmapFont font;
	public SplashState(GameStateManager gsm) {
		super(gsm);
		tex = new Texture("..\\core\\assets\\img\\Players\\Player Green\\background.png");
		playBtn = new Texture("..\\core\\assets\\img\\Other\\playBtn.png");
		pic = new Sprite(tex);
		pic2 = new Sprite(playBtn);
		batch = new SpriteBatch();
		font = new BitmapFont();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(pic, 0, 0, 840, 600);
		font.draw(batch, "pachon VS World", (840/2) - (playBtn.getWidth() / 2), 600/2 + 50);
		batch.draw(pic2, (840/2) - (playBtn.getWidth() / 2), 600/3);
		batch.end();
		if(Gdx.input.justTouched()) {
			
			gsm.setState(GameStateManager.State.PLAY);
			dispose();
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		tex.dispose();
		playBtn.dispose();
	}

	
	

}
