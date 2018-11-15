package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

public class pachon extends ApplicationAdapter {
	private int width = 840;
	private int height = 600;
	private SpriteBatch batch;
	private Texture img,ob1;
	private ShapeRenderer r1,r2,r3,r4;
	private float i = 0, j = 0,k=0;
	private int jump = 0;
	private TiledMap map;
	private OrthographicCamera ca;
	private TiledMapRenderer mapre;

	public void setI(float i) {
		this.i = i;
	}
	public void setJ(float j) {
		this.j = j;
	}
	private Rectangle bounds = new Rectangle(100,100,50,50);
	private Rectangle bounds3 = new Rectangle(0,300,780,50);
	private Rectangle bounds4 = new Rectangle(490,500,350,50);
	//private Rectangle bounds5 = new Rectangle(100,100,50,50);
	//private Rectangle bounds6 = new Rectangle(100,100,50,50);
	private Rectangle bounds2 ;
	public pachon() {
		
	}
	@Override
	public void create () {
		ca = new OrthographicCamera();
        ca.setToOrtho(false,2000,2000);
        ca.update();
		map = new TmxMapLoader().load("..\\core\\assets\\map1.tmx");
		mapre = new OrthogonalTiledMapRenderer(map);
		r1 = new ShapeRenderer();
		r2 = new ShapeRenderer();
		r3 = new ShapeRenderer();
		r4 = new ShapeRenderer();
		batch = new SpriteBatch();
		img = new Texture("..\\core\\assets\\badlogic.jpg");
		}
	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(i<0) {i=0;}
		else if((j<0)) {j=0;}
		else if(i>height-50) {i=height-50;}
	
		else if(j>width-50 ) {j=width-50;}
		 if(Gdx.input.isKeyPressed(Input.Keys.UP)||Gdx.input.isKeyPressed(Input.Keys.W)){
			 if(!collides(getBounds2(j, i))&&jump<10) {
				 jump++;
				 i += 20;
			 }
			 
		  }
		
		 i-=5;
		 if(Gdx.input.isKeyPressed(Input.Keys.DOWN)||Gdx.input.isKeyPressed(Input.Keys.S)){
			 if(!collides(getBounds2(j, i))) {
				 i -= 5;
			 }
		  }
		 collides(getBounds2(j, i));
		 if(Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.A)){
			 if(!collides(getBounds2(j, i))) {
				 j-=3;
				 }
		 }
		 if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)||Gdx.input.isKeyPressed(Input.Keys.D)){
			 if(!collides(getBounds2(j, i))) {
				 j+=3;
			 }
		  }
		 if(i == 0) {jump=0;}
		 if(i<0) {i=0;}
		 if(i>height-50) {i-=3;}
		 if((j<0)) {j+=3;}
		 if(j>width-50 ) {j-=3;}
		 getR2();
		 getR1(j,i);
		 ca.update();
	     mapre.setView(ca);
	     mapre.render();
	
	}
	public void getR2() {
		
		r2.begin(ShapeType.Filled);
	    r2.setColor(Color.GREEN);
	    r2.rect(100,100,50,50);
	    r2.end();
	    r3.begin(ShapeType.Filled);
	    r3.setColor(Color.GREEN);
	    r3.rect(0,300,780,50);
	    r3.end();
	    r4.begin(ShapeType.Filled);
	    r4.setColor(Color.GREEN);
	    r4.rect(490,500,350,50);
	    r4.end();
		
	}
	public Rectangle getBounds2(float j2 ,float x) {
		
		return bounds2 = new Rectangle(j2,x,50,50);
	}
	public ShapeRenderer getR1(float j2,float x) {
		
		r1.begin(ShapeType.Filled);
	    r1.setColor(Color.GREEN);
	    r1.rect(j2, x, 50, 50);
	    r1.end();
		return r1;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public Rectangle getBounds3() {
		return bounds3;
	}
	public Rectangle getBounds4() {
		return bounds4;
	}
	public boolean collides  (Rectangle player) {
		
		if(player.overlaps(getBounds())&&player.y >= getBounds().y+40) {setI(getBounds().y+50);jump=0;}
		if(player.overlaps(getBounds3())&&player.y >= getBounds3().y+40) {setI(getBounds3().y+50);jump=0;}
		if(player.overlaps(getBounds4())&&player.y >= getBounds4().y+40) {setI(getBounds4().y+50);jump=0;}
		return player.overlaps(getBounds())||player.overlaps(getBounds3())||player.overlaps(getBounds4());
	}
	@Override
	public void dispose () {
		
		batch.dispose();
		img.dispose();
	}
}
