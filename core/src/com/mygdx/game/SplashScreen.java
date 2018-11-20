package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class SplashScreen implements Screen {
	private final pachon app;
	private Stage stage;
	private Image splashImg, splashImg2;
	private Sprite sp;
	
	public SplashScreen(final pachon app) {
		this.app = app;
		this.stage = new Stage(new FitViewport(0, 0, app.camera));
		Gdx.input.setInputProcessor(stage);
		
		Texture splashTex = new Texture(Gdx.files.internal("..\\core\\assets\\background.png"));
		sp = new Sprite(splashTex);
		sp.setSize(600, 840);
		splashImg = new Image(sp);
		splashImg2 = new Image(sp);
		splashImg.setPosition(stage.getWidth() - 128, stage.getHeight() - 128);
		splashImg2.setPosition(stage.getWidth() - 128, stage.getHeight() + 950);
		stage.addActor(splashImg);
		stage.addActor(splashImg2);
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        update(delta);
        
        stage.draw();
        
        app.batch.begin();
        app.font.draw(app.batch, "SplashScreen", 120, 120);
        app.batch.end();
	}
	
	public void update(float delta) {
		stage.act(delta);
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
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
		// TODO Auto-generated method stub
		
	}
	
}
