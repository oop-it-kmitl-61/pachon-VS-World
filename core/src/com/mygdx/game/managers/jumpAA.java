package com.mygdx.game.managers;

import static com.mygdx.game.Constants.PPM;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class jumpAA {
	private Body player;
	private Texture tex,tex2,tex3;
	private SpriteBatch batch;
	private World world;
	private Vector3 potition;
	private Animation playeranimation,playeranimation2,playeranimation3;
	private int i =0;
	
	private int ck = 0;
	public jumpAA(Body player) {
		this.player = player;
		batch = new SpriteBatch();
		world = new World(new Vector2(0, -9.8f), false);
		
		tex = new Texture("..\\core\\assets\\img\\Players\\Player Green\\jump.png");
		
		playeranimation = new Animation(new TextureRegion(tex),1,1f);
		playeranimation2 = new Animation(new TextureRegion(tex),2,1f);

		
	}
	
	public void update(float delta) {
		// TODO Auto-generated method stub
		
		
		playeranimation2.update(delta);
      
	
      
	}
	
	public void batch(boolean jump) {
		update(Gdx.graphics.getDeltaTime());
		batch.begin();
		if(true) {
		batch.draw(playeranimation2.getFrame(), player.getPosition().x*PPM - (tex.getWidth()/5), player.getPosition().y*PPM - (tex.getHeight()/2));
		}
		else if(false) {
			batch.draw(playeranimation2.getFrame(), player.getPosition().x*PPM - (tex.getWidth()/5), player.getPosition().y*PPM - (tex.getHeight()/2));
		}
		batch.end();	
	}
	public SpriteBatch batch1() {
		return batch;
	}
	public Body position() {
		return player;
	}
	public void dispose() {
    	batch.dispose();
    }
	
}
