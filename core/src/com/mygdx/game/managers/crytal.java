package com.mygdx.game.managers;

import static com.mygdx.game.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class crytal {
	private float x,y;
	private Texture tex;
	private Animation cry;
	private Vector2  vc;
	private SpriteBatch batch;
	public crytal(float x,float y) {
		this.x = x;
		this.y = y;
		vc = new Vector2();
		batch = new SpriteBatch();
		tex = new Texture("..\\core\\assets\\img\\Players\\Player Green\\cr2.png");
		
		
		cry = new Animation(new TextureRegion(tex),28,0.1f);

		
	}
	public Vector2 getpo() {
		int  q,q1;
		q =  (int) (x/PPM);
		q1 = (int) (y/PPM);
		vc.x = q*32;
		vc.y = q1*32;
		return vc;
	}
	public  int  batch(boolean a) {
		int i=0;
		if(a==true) {
		batch.begin();
		cry.update(Gdx.graphics.getDeltaTime());
		batch.draw(cry.getFrame(), x, y);
	
		batch.end();
		return 0;
		}
		else {
			return i=1; 
		}
		
	}
	public SpriteBatch batch1() {
		return batch;
	}
	
	public void dispose() {
		tex.dispose();
    	batch.dispose();
    }
	
}