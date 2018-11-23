package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.managers.GameStateManager;

public class SplashState extends GameState{
	
	float acc = 0f;
	Texture tex;

	public SplashState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		acc += delta;
		if(acc >= 3) {
			gsm.setState(GameStateManager.State.PLAY);
		}
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1f, 1f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	

}
