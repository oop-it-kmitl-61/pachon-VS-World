package com.mygdx.game.states;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.mygdx.game.TiledObjectUtil;
import static com.mygdx.game.Constants.PPM;

public class PlayState extends GameState{
	private boolean DEBUG = false;
	private final float SCALE = 2.0f;
    private OrthogonalTiledMapRenderer tmr;
    protected TiledMap map;

    private Box2DDebugRenderer b2dr;
    private World world;
    private Body player;
    
    protected SpriteBatch batch;
    private Texture tex;
    public BitmapFont font;
//    playstate like create class
	public PlayState(GameStateManager gsm) {
		super(gsm);
      world = new World(new Vector2(0, -9.8f), false);
      b2dr = new Box2DDebugRenderer();
      
      player = createBox(242, 58,32, 50, false);
      
      batch = new SpriteBatch();
      font = new BitmapFont();
      tex = new Texture("..\\core\\assets\\img\\Players\\Player Green\\playerGreen_walk1.png");
      
      map = new TmxMapLoader().load("..\\core\\assets\\map1.tmx");
      tmr = new OrthogonalTiledMapRenderer(map);
      
      TiledObjectUtil.parseTileObject(world, map.getLayers().get("obj").getObjects());
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
      world.step(1 / 60f, 6, 2);

      inputUpdate(delta);
      cameraUpdate();
      tmr.setView(camera);
      batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	tmr.render();
        update(Gdx.graphics.getDeltaTime());

        // Render
        
        tmr.render();
        batch.begin();
        batch.draw(tex, player.getPosition().x*PPM - (tex.getWidth()/2), player.getPosition().y*PPM - (tex.getHeight()/2));
        batch.end();
        
        
        
        if(DEBUG) {
        b2dr.render(world, camera.combined.scl(PPM));
        }
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
      world.dispose();
      b2dr.dispose();
      batch.dispose();
      tmr.dispose();
      map.dispose();
      batch.dispose();
	}
	
//    @Override
//    public void resize(int width, int height) {
//    	camera.setToOrtho(false, width / SCALE, height / SCALE);
//    	camera.position.x = 242;
//    	camera.position.y = 150;
//    }
    
    public void inputUpdate(float delta) {
        int horizontalForce = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            horizontalForce -= 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            horizontalForce += 1;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.applyForceToCenter(0, 300, false);
        }

        player.setLinearVelocity(horizontalForce * 5, player.getLinearVelocity().y);
    }
    public void cameraUpdate() {
        Vector3 position = camera.position;
//        a + (b-1) * lerp
//        b = target
//        a = current camera position
//        position.x = camera.position.x + (player.getPosition().x * PPM - camera.position.x) * .1f;
//        position.y = camera.position.y + (player.getPosition().y * PPM - camera.position.y) * .1f;
        
//        og camera transition
        position.x = player.getPosition().x * PPM;
        position.y = player.getPosition().y * PPM;
        
//        float a,b,c;
//        a = camera.position.x;
//        b = player.getPosition().x;
//        c = player.getPosition().y;
//        if(b<(float) 7.5625) {b = (float) 7.5625;}
//        else if(b>42.404976) {b = (float) 42.404976;}
//        
//        if((a >= 242 == true)&&(a<= 1357.99)) {position.x = camera.position.x + (b * PPM - camera.position.x) * .1f;}
//        if(c<4.7732534) {c = (float) 4.7732534;}
//        else if( c>44.299213) {c = (float) 44.299213;}
//        position.y = camera.position.y + (c * PPM - camera.position.y) * .1f;;
//        System.out.println(c);
//        camera.position.set(position);

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
        shape.dispose();
        return pBody;
    }
  public SpriteBatch getBatch() {
	return batch;
  }
	
}
