package com.mygdx.game.states;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.managers.GameStateManager;
import com.mygdx.game.managers.Hud;
import com.mygdx.game.managers.boss;
import com.mygdx.game.managers.Player;
import com.mygdx.game.managers.bomp;
import com.mygdx.game.managers.crytal;
import com.mygdx.game.managers.jumpAA;
import com.mygdx.game.managers.mosterA;
import com.mygdx.game.managers.mosterB;
import com.mygdx.game.managers.Animation;
import com.mygdx.game.TiledObjectUtil;
import static com.mygdx.game.Constants.PPM;
import static com.mygdx.game.Constants.Score;

import java.util.ArrayList;

public class PlayState extends GameState{
	private boolean DEBUG = false;
	private final float SCALE = 2.0f;
    private OrthogonalTiledMapRenderer tmr;
    protected TiledMap map;
    
    private Animation ahp;
    private Texture hpp;
    private SpriteBatch shp;
    
    private Player pachon;
    private mosterA p1,p2,p3,p4,p7,p8,p9,p0,wall1;
    private ArrayList<mosterA> ma;
    private mosterB b1,b2,b3,b4,b5,b6,b7,b8;
    
    private jumpAA p5,p6;
    private boolean ck = true;
    private ArrayList<mosterB> mb;
    private Box2DDebugRenderer b2dr;
    private World world;
    private Body boss,player,jump,jump2,mon,mon1,mon2,mon3,mon4,mon5,mon6,mon7,wall,fly,fly2,fly3,fly4,fly5,fly6,t;
    
    private int hp = 5;
    private ArrayList<bomp> ab;
    private  bomp no;
    public static int score = 0;
    // Score
    private Hud hud;
    private Music music;
    //
    protected SpriteBatch batch,batch1;
    private Sprite pic,pic2;
    private Texture tex,tex2;
    public BitmapFont font;
    private crytal c1;
    private ArrayList<crytal> ac;
    
    private boss bo;
//    playstate like create class
	public PlayState(GameStateManager gsm) {
		super(gsm);
	
      world = new World(new Vector2(0, -9.8f), false);
      b2dr = new Box2DDebugRenderer();
      music = Gdx.audio.newMusic(Gdx.files.internal("..\\core\\assets\\hit.wav"));
      music.setLooping(false);
      music.setVolume(0.7f);
      hpp = new Texture("..\\core\\assets\\img\\Players\\Player Green\\hp.png");
      shp = new SpriteBatch();
      player = createBox(242, 58,32, 50, false);
      t = sc(672,1472,32, 50, false);
       
      mon = createBoxm(777, 185,32, 42, false);
      mon1 = createBoxm(750,409,32,42,false);
      mon2 = createBoxm(512,576,32,42,false);
      mon3 = createBoxm(832,576,32,42,false);
      mon4 = createBoxm(448,1216,32,42,false);
      //boss
      ab = new ArrayList();
      no = new bomp(224,1300);
      ab.add(no);
      no = new bomp(1490,1111);
      ab.add(no);
      no = new bomp(1490,1525);
      ab.add(no);
      no = new bomp(256,225);
      ab.add(no);
      no = new bomp(128,225);
      ab.add(no);
      
      //
      fly = createBoxm(1400,271,52,33,false);
      fly2 = createBoxm(1408,576,52,33,false);
      fly3 = createBoxm(96,832,52,33,false);
      fly4 = createBoxm(288,832,52,33,false);
      fly5 = createBoxm(1152,850,52,33,false);
      fly6 = createBoxm(1024,1504,52,33,false);
      wall = createwall(465,40,10000000,1,true);
      
      jump = createjump(1248,590,50,29,true);
      jump2 = createjump(1440,906,50,29,true);
      
      p1 = new mosterA(mon);
      p2 = new mosterA(mon1);
      p3 = new mosterA(mon2);
      p4 = new mosterA(mon3);
      p7 = new mosterA(mon4);
      p0 = new mosterA(t);
       
      p5 = new jumpAA(jump);
      p6 = new jumpAA(jump2);
      
      b2 = new mosterB(fly);
      b1 = new mosterB(fly2);
      b3 = new mosterB(fly3);
      b4 = new mosterB(fly4);
      b5 = new mosterB(fly5);
      b6 = new mosterB(fly6);
      
      wall1 = new mosterA(wall);
      camera.position.x = (float) (7.5625 *PPM);

      batch1 = new SpriteBatch();
      hud = new Hud(batch1);
      
      ac = new ArrayList();
      c1 = new crytal(410, 90);
      ac.add(c1);
      c1 = new crytal(1312,210);
      ac.add(c1);
      c1 = new crytal(832,180);
      ac.add(c1);
      c1 = new crytal(1376,470);
      ac.add(c1);
      c1 = new crytal(800,590);
      ac.add(c1);
      c1 = new crytal(1440,736);
      ac.add(c1);
      c1 = new crytal(1536,736);
      ac.add(c1);
      c1 = new crytal(1504,900);
      ac.add(c1);
      c1 = new crytal(224,1170);
      ac.add(c1);
      c1 = new crytal(576,1216);
      ac.add(c1);
      c1 = new crytal(1536,170);
      ac.add(c1);
      c1 = new crytal(800,384);
      ac.add(c1);
      c1 = new crytal(192,850);
      ac.add(c1);
      c1 = new crytal(1408,1413);
      ac.add(c1);
      pachon = new Player(player);
 
      
      font = new BitmapFont();
      tex2 = new Texture("..\\core\\assets\\img\\Players\\Player Green\\background.png");
      map = new TmxMapLoader().load("..\\core\\assets\\map1.tmx");
      tmr = new OrthogonalTiledMapRenderer(map);
      
      TiledObjectUtil.parseTileObject(world, map.getLayers().get("obj").getObjects());
	}
	
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
      world.step(1 / 60f, 6, 2);
      cameraUpdate();
      tmr.setView(camera);
      batch1.setProjectionMatrix(camera.combined);
      
      pachon.batch1().setProjectionMatrix(camera.combined);
      
      p5.batch1().setProjectionMatrix(camera.combined);
      p6.batch1().setProjectionMatrix(camera.combined);
      p1.batch1().setProjectionMatrix(camera.combined);
      p2.batch1().setProjectionMatrix(camera.combined);
      p3.batch1().setProjectionMatrix(camera.combined);
      p4.batch1().setProjectionMatrix(camera.combined);
      p7.batch1().setProjectionMatrix(camera.combined);
      p0.batch1().setProjectionMatrix(camera.combined);
      
      b1.batch1().setProjectionMatrix(camera.combined);
      b2.batch1().setProjectionMatrix(camera.combined);
      b3.batch1().setProjectionMatrix(camera.combined);
      b4.batch1().setProjectionMatrix(camera.combined);
      b5.batch1().setProjectionMatrix(camera.combined);
      b6.batch1().setProjectionMatrix(camera.combined);
      
      
      for(int i = 0;i<ac.size();i++) {
    	  ac.get(i).batch1().setProjectionMatrix(camera.combined);;
      }
      for(int i = 0;i<ab.size();i++) {
    	  ab.get(i).batch1().setProjectionMatrix(camera.combined);;
      }
	}

	@Override
	public void render(SpriteBatch batch) {
		
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch1.begin();
        batch1.draw(tex2, 0, 0);
        batch1.end();
        batch1.begin();
        batch1.draw(tex2, 0, 1080);
        batch1.end();
    	tmr.render();
    	p1.batch((float)24.2815,(float)31.555706,true);
    	p2.batch((float)23.442413,(float)28.409721,true);
    	p3.batch((float) 16.13064,(float)24, false);
    	p4.batch((float) 27,(float)33.4, false);
    	p7.batch((float) 14.047954,(float)22.706402, true);
    	
  
    	
    	System.out.println(pachon.getpo());
    	p5.batch(true);
    	p6.batch(true);
    	
    	b1.batch((float)14.796248, (float)25.078955,(float) 43, (float)44);
    	b2.batch((float)7.910124, (float)9.689845,(float)43.037514, (float)45.13125);
    	b3.batch((float)12.796248, (float)26.256123, (float)3.9673734, (float)3.98);
    	b4.batch((float)25, (float)20, (float)9.10604, (float)19.35604);
    	b5.batch((float)26, (float)31, (float)9.10604, (float)19.35604);
    	b6.batch((float)43.94704, (float)47.121815, (float)32.31839, (float)35.48501);
    	pachon.batch();
    	
    	batch.begin();
    	hud.update(Gdx.graphics.getDeltaTime());
    	hud.stage.draw();
    	batch.end();
    	
    	shp.begin();
    	int fa = 10;
    	for(int qe = 0;qe<hp;qe++) {
    		
    		shp.draw(hpp, fa , 10,40,36);
    		fa+=40;
    	}
    	
    	
    	shp.end();
    	world.setContactListener(new ContactListener() {
			@Override
			public void beginContact(Contact contact) {
				if(contact.getFixtureA().getBody().getUserData() == "Player" && contact.getFixtureB().getBody().getUserData() == "monster" ) {
					hp -=1;
					music.play();
		
					
				}
				if(contact.getFixtureA().getBody().getUserData() == "Player" && contact.getFixtureB().getBody().getUserData() == "j" ) {
					pachon.setcj();
					pachon.setjump();
					 
					
					
					
				}
				if(contact.getFixtureA().getBody().getUserData() == "Player" && contact.getFixtureB().getBody().getUserData() == "sc" ) {
					gsm.setState(new GameOverState(gsm));
					
					 
				}
				if(contact.getFixtureA().getBody().getUserData() == "Player" && contact.getFixtureB().getBody().getUserData() == "wall" ) {
					
					hp=0;
					gsm.setState(new GameOverState(gsm));
					
				}
				
			if(hp == 0) {
				gsm.setState(new GameOverState(gsm));
				
				}	
			if(Hud.timeUp == true) {
				
				gsm.setState(new GameOverState(gsm));
				
				}
			
			}
			
			@Override
			public void endContact(Contact contact) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				// TODO Auto-generated method stub
				
			}
        });
    	p5.batch(!ck);
    	
        update(Gdx.graphics.getDeltaTime());
        pachon.batch();
        
        
 
        
        if(DEBUG) {
        b2dr.render(world, camera.combined.scl(PPM));
        }
        for(int i = 0;i<ac.size();i++) {
        	if(ac.get(i).getpo().epsilonEquals((pachon.getpo()))) {
        		
        		
        		ac.get(i).batch(false);
        		ac.remove(i);
        	}
        	else{
        		ac.get(i).batch(true);
        	}
        	
        }
        for(int i = 0;i<ab.size();i++) {
        	if(ab.get(i).getpo().epsilonEquals((pachon.getpo()))) {
        		
        		
        		hp += ab.get(i).batch(false);
        		ab.remove(i);
        	}
        	else{
        		ab.get(i).batch(true);
        	}
        	
        }
       if(Score<0) {Score =0;}
        hud.addScore(Score);
        
	}

	@Override
	public void dispose() {
		tex2.dispose();
		map.dispose();
		world.dispose();
		b2dr.dispose();
		tmr.dispose();
		map.dispose();
	}
	
//    @Override
//    public void resize(int width, int height) {
//    	camera.setToOrtho(false, width / SCALE, height / SCALE);
//    	camera.position.x = 242;
//    	camera.position.y = 150;
//    }
    
    public void cameraUpdate() {
        Vector3 position = camera.position;

        float a,b,c;
        a = camera.position.x;
        b = pachon.position().getPosition().x;
        c = pachon.position().getPosition().y;
        if(b<(float) 7.5625) {b = (float) 7.5625;}
        else if(b>42.404976) {b = (float) 42.404976;}
        
        if((a >= 242 == true)&&(a<= 1357.99)) {position.x = camera.position.x + (b * PPM - camera.position.x) * .1f;}
        if(c<4.7732534) {c = (float) 4.7732534;}
        else if( c>44.299213) {c = (float) 44.299213;}
        position.y = camera.position.y + (c * PPM - camera.position.y) * .1f;
       
        camera.position.set(position);

        camera.update();
    }
    
    public Body createBox(int x, int y, int width, int height, boolean isStatic) {
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
        pBody.createFixture(shape, 0.0f);
        pBody.setUserData("Player");
        
        
        shape.dispose();
        return pBody;
    }
    public Body createBoxm(int x, int y, int width, int height, boolean isStatic) {
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
        pBody.createFixture(shape, 0.0f);
        pBody.setUserData("monster");
        
        
        shape.dispose();
        return pBody;
    }
    public Body createwall(int x, int y, int width, int height, boolean isStatic) {
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
        pBody.createFixture(shape, 0.0f);
        pBody.setUserData("wall");
        
  
        shape.dispose();
        return pBody;
    }
    public Body createjump(int x, int y, int width, int height, boolean isStatic) {
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
        pBody.createFixture(shape, 0.0f);
        pBody.setUserData("j");
        
  
        shape.dispose();
        return pBody;
    }
    public Body sc(int x, int y, int width, int height, boolean isStatic) {
        Body pBody;
        BodyDef def = new BodyDef();

        
            def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
        pBody.createFixture(shape, 0.0f);
        pBody.setUserData("sc");
        
  
        shape.dispose();
        return pBody;
    }
  public SpriteBatch getBatch() {
	return batch;
  }




	
}
