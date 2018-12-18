package com.mygdx.game.managers;

import static com.mygdx.game.Constants.PPM;
import static com.mygdx.game.Constants.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class bomp {
	private float x,y;
	private Texture tex;
	private Animation cry;
	private Vector2  vc;
	private SpriteBatch batch;
	private Music music,music2;
	private String word;
	private Random i = new Random();
	private int j = 0;
	private ArrayList<String> a;
	private ArrayList<String> b;
	public BitmapFont font48;
	public bomp(float x,float y) {
		music2 = Gdx.audio.newMusic(Gdx.files.internal("..\\core\\assets\\hit.wav"));
	      music2.setLooping(false);
	      music2.setVolume(0.7f);
		a = new ArrayList();
		a.add("Hello");
		a.add("Abandon");
		a.add("Ambassador");
		a.add("Announce");
		a.add("Buffet");
		a.add("Calendar");
		a.add("Disappear");
		a.add("Economy");
		a.add("Fortune");
		a.add("Fragment");
		a.add("Hostile");
		a.add("Independent");
		a.add("Intelligent");
		a.add("Magazine");
		a.add("Massage");
		a.add("Necessary");
		a.add("Photograph");
		a.add("Refrigerator");
		a.add("Struggle");
		a.add("Temporary");
		b = new ArrayList();
		b.add("Helow");
		b.add("Abondan");
		b.add("Ambasdor");
		b.add("Anouce");
		b.add("Buffe");
		b.add("Calander");
		b.add("Disapeer");
		b.add("Eomony");
		b.add("Fortoun");
		b.add("Fregmant");
		b.add("Hostille");
		b.add("Indepandant");
		b.add("Inteligant");
		b.add("Megazene");
		b.add("Mesege");
		b.add("Nessessary");
		b.add("Fotograf");
		b.add("Refidgereter");
		b.add("Struccle");
		b.add("Tempolary");
		
		this.word = b.get(i.nextInt(b.size()));
	
		font48 = new BitmapFont();
		music = Gdx.audio.newMusic(Gdx.files.internal("..\\core\\assets\\crystal.wav"));
		music.setLooping(false);
		music.setVolume(0.1f);
		
		this.x = x;
		this.y = y;
		vc = new Vector2();
		batch = new SpriteBatch();
		tex = new Texture("..\\core\\assets\\img\\Players\\Player Green\\bomp.png");
		
		
		cry = new Animation(new TextureRegion(tex),4,0.4f);

		
	}
	public Vector2 getpo() {
		int  q,q1;
		q =  (int) (x/PPM);
		q1 = (int) (y/PPM);
		vc.x = q*32;
		vc.y = q1*32;
		return vc;
	}
	public  int  batch(boolean q) {
		int i=0;
		
		if(q==true) {
		batch.begin();
		font48.draw(batch, word,x,y);
		cry.update(Gdx.graphics.getDeltaTime());
		batch.draw(cry.getFrame(), x, y);
		
		batch.end();
		return 0;
		}
		else {
			
				if(a.contains(word)) {
				
					Score += 200;
					music.play();
					return 0;
				}
				else {
					Score -= 50;
					i = -1;
					music2.play();
					return i = -1;
				}
				
				
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