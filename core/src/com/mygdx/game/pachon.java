package com.mygdx.game;

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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import static com.mygdx.game.Constants.PPM;


public class pachon extends Game {
	private boolean DEBUG = true;
	private final float SCALE = 2.0f;

	protected OrthographicCamera camera;
    
    private OrthogonalTiledMapRenderer tmr;
    protected TiledMap map;

    private Box2DDebugRenderer b2dr;
    private World world;
    private Body player;
    
    protected SpriteBatch batch;
    private Texture tex;

    public BitmapFont font;
    
    @Override
    public void create () {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
       
        camera.setToOrtho(false, w / SCALE, h / SCALE);
        
        world = new World(new Vector2(0, -9.8f), false);
        b2dr = new Box2DDebugRenderer();
        
        player = createBox(242, 58,32, 50, false);
//        createBox(100, 48, 128, 32, true);
        
        batch = new SpriteBatch();
        font = new BitmapFont();
        
        this.setScreen(new SplashScreen(this));
        tex = new Texture("..\\core\\assets\\img\\Players\\Player Green\\playerGreen_walk1.png");
        
        map = new TmxMapLoader().load("..\\core\\assets\\map1.tmx");
        tmr = new OrthogonalTiledMapRenderer(map);
        
        TiledObjectUtil.parseTileObject(world, map.getLayers().get("obj").getObjects());
    }

    @Override
    public void render() {
    	
    	super.render();
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
    }

    @Override
    public void resize(int width, int height) {
    	camera.setToOrtho(false, width / SCALE, height / SCALE);
    	camera.position.x = 242;
    	camera.position.y = 150;
    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        batch.dispose();
        tmr.dispose();
        map.dispose();
    }

    public void update(float delta) {
        world.step(1 / 60f, 6, 2);

        inputUpdate(delta);
        cameraUpdate(delta);
        tmr.setView(camera);
        batch.setProjectionMatrix(camera.combined);
    }

    public void inputUpdate(float delta) {
        int horizontalForce = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            horizontalForce -= 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            horizontalForce += 1;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.applyForceToCenter(0, 600, false);
        }

        player.setLinearVelocity(horizontalForce * 5, player.getLinearVelocity().y);
    }

    public void cameraUpdate(float delta) {
        Vector3 position = camera.position;
        float a,b,c;
        a = camera.position.x;
        b = player.getPosition().x;
        c = player.getPosition().y;
        if(b<(float) 7.5625) {b = (float) 7.5625;}
        else if(b>42.404976) {b = (float) 42.404976;}
        
        if((a >= 242 == true)&&(a<= 1357.99)) {position.x = b* PPM;}
        if(c<4.7732534) {c = (float) 4.7732534;}
        else if( c>44.299213) {c = (float) 44.299213;}
        position.y = c*PPM;
        System.out.println(c);
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

//        pBody.createFixture(shape, 1.0f);
        pBody.createFixture(shape, 0.0f);
        shape.dispose();
        return pBody;
    }
}
