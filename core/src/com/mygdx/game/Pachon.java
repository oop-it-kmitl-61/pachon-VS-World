package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.managers.GameStateManager;

import static com.mygdx.game.Constants.PPM;

public class Pachon extends Game {
	private boolean DEBUG = false;
	private final float SCALE = 2.0f;

	protected OrthographicCamera camera;
	
    private GameStateManager gsm;
    protected SpriteBatch batch;
  
    private Music music;
    @Override
    public void create () {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        music = Gdx.audio.newMusic(Gdx.files.internal("..\\core\\assets\\music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		
        camera = new OrthographicCamera();       
        camera.setToOrtho(false, w / SCALE, h / SCALE);
        batch = new SpriteBatch();
        gsm = new GameStateManager(this);
    }

    @Override
    public void render() {
    	gsm.update(Gdx.graphics.getDeltaTime());
    	gsm.render();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
    }
    @Override
    public void resize(int width, int height) {

    	gsm.resize((int) (width/ SCALE), (int)(height/SCALE));
//    	camera.setToOrtho(false, width / SCALE, height / SCALE);
//    	camera.position.x = 242;
//    	camera.position.y = 150;
    }

    @Override
    public void dispose() {
    	gsm.dispose();
    } 
    public OrthographicCamera getCamera() {
    	return camera;
    }
    
    public SpriteBatch getBatch() {
    	return batch;
    }
}
