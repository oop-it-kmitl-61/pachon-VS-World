package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Pachon;
import com.mygdx.game.states.PlayState;
import com.mygdx.game.managers.GameStateManager;

/**
 * Created by brentaureli on 10/8/15.
 */
public class GameOverState extends GameState {

	float acc = 0f;
	protected SpriteBatch batch;
    private BitmapFont font;
    private static Integer score = 0;
	public GameOverState(GameStateManager gsm) {
		super(gsm);
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(2);
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
		font.draw(batch, String.format("Score: %d", score), Gdx.graphics.getWidth()/2 - 30, Gdx.graphics.getHeight() - 200);
		font.draw(batch, "Click to main menu", Gdx.graphics.getWidth()/2 - 100, Gdx.graphics.getHeight()/2 - 25);
		font.draw(batch, "Press Esc to exit", Gdx.graphics.getWidth()/2 - 85, Gdx.graphics.getHeight()/3);
		batch.end();
		if(Gdx.input.justTouched()) {
			gsm.setState(new SplashState(gsm));
			dispose();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
	
	public static void getScore(int value) {
		score = value;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
    
}
