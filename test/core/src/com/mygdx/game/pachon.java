package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.brashmonkey.spriter.Player;


public class pachon extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img,ob1;
	private ShapeRenderer r1,r2;
	private  int i = 0, j = 0;
	private Rectangle bounds = new Rectangle(100,100,50,50);
	private Rectangle bounds2;
	public ShapeRenderer getR2() {
		r2 = new ShapeRenderer();
		r2.begin(ShapeType.Filled);
	    r2.setColor(Color.GREEN);
	    r2.rect(100,100,50,50);
	    r2.end();
		return r2;
	}
	public Rectangle getBounds2(int a ,int b) {
		bounds2 = new Rectangle(a,b,50,50);
		return bounds2;
	}
	public ShapeRenderer getR1(int a,int b) {
		r1 = new ShapeRenderer();
		r1.begin(ShapeType.Filled);
	    r1.setColor(Color.GREEN);
	    r1.rect(a, b, 50, 50);
	    r1.end();
		return r1;
	}
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("C:\\Users\\Champ\\Downloads\\test\\core\\assets\\badlogic.jpg");
		}
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		pachon a = new pachon();
		
		 if(Gdx.input.isKeyPressed(Input.Keys.UP)||Gdx.input.isKeyPressed(Input.Keys.W)){
			 if(!a.collides(a.getBounds2(j, i))) {
				i++;
			 }
			 else {
				 i-=3;
			 }
		  }
		 
		 if(Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.A)){
			 if(!a.collides(a.getBounds2(j, i))) {
				 j--;
			 }
			 else {
				 j+=3;
			 }
		  }
		 if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)||Gdx.input.isKeyPressed(Input.Keys.D)){
			 if(!a.collides(a.getBounds2(j, i))) {
				 j++;
			 }
			 else {
				 j-=3;
			 }
		  }
		 if(Gdx.input.isKeyPressed(Input.Keys.DOWN)||Gdx.input.isKeyPressed(Input.Keys.S)){
			 if(!a.collides(a.getBounds2(j, i))) {
				 i--;
			 }
			 else {
				 i+=3;
			 }
		  }
		 a.getR1(j, i);
		 a.getBounds2(j,i);
		 a.getR2();
	
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	public boolean collides (Rectangle player) {
		pachon a = new pachon();
		
		return player.overlaps(a.getBounds());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
