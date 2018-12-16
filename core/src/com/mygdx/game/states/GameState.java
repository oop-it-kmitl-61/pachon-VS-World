package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Pachon;
import com.mygdx.game.managers.GameStateManager;

public abstract class GameState {
//	ref
	protected GameStateManager gsm;
	protected Pachon app;
	
	protected SpriteBatch batch;
	protected OrthographicCamera camera;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
		this.app = gsm.pachon();
		batch = app.getBatch();
		camera = app.getCamera();
	}
	
	public void resize(int w,int h) {
		camera.setToOrtho(false, w, h);
	}
	
	public abstract void update(float delta);
	public abstract void render(SpriteBatch batch);
	public abstract void dispose();

}
