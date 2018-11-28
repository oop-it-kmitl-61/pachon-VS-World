package com.mygdx.game.managers;

import static com.mygdx.game.Constants.PPM;
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

public class Player {
	private Body player;
	private Texture tex,tex2;
	private SpriteBatch batch;
	private World world;
	private Vector3 potition;
	private Animation playeranimation,playeranimation2;
	private int i =0;
	public Player(Body player) {
		this.player = player;
		batch = new SpriteBatch();
		world = new World(new Vector2(0, -9.8f), false);
		tex2 = new Texture("..\\core\\assets\\img\\Players\\Player Green\\playerGreen_walk1.png");
		tex = new Texture("..\\core\\assets\\img\\Players\\Player Green\\walk.png");
		
		playeranimation = new Animation(new TextureRegion(tex2),1,0.3f);
		playeranimation2 = new Animation(new TextureRegion(tex),3,0.3f);
		
	}
	public void inputUpdate(float delta) {
        int horizontalForce = 0;
        i=0;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
        	
            horizontalForce -= 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            horizontalForce += 1;
            i=1;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
        	
            player.applyForceToCenter(0, 300, false);
        }
        
        player.setLinearVelocity(horizontalForce * 5, player.getLinearVelocity().y);
    }
	public void update(float delta) {
		// TODO Auto-generated method stub
		inputUpdate(delta);
		playeranimation.update(delta);
		playeranimation2.update(delta);
      
      
      
	}
	public void batch() {
		update(Gdx.graphics.getDeltaTime());
		batch.begin();
		
		if(i==0) {batch.draw(playeranimation.getFrame(), player.getPosition().x*PPM - (tex.getWidth()/5), player.getPosition().y*PPM - (tex.getHeight()/2));}
		else if(i==1) {batch.draw(playeranimation2.getFrame(), player.getPosition().x*PPM - (tex.getWidth()/5), player.getPosition().y*PPM - (tex.getHeight()/2));
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
