package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nonny.game.NonnyDemo;

public class MenuState extends State{
	private Texture background;
	private Texture playBtn;
	public MenuState(GameStateManager gsm) {
		super(gsm);
		background = new Texture("bg.png");
		playBtn = new Texture("playbtn.png");
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched()) {
			gsm.set(new PlayState(gsm));
		}
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		sb.begin();
		sb.draw(background, 0, 0, NonnyDemo.WIDTH, NonnyDemo.HEIGHT);
		sb.draw(playBtn, (NonnyDemo.WIDTH / 2) - (playBtn.getWidth() / 2), NonnyDemo.HEIGHT / 2);
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		background.dispose();
		playBtn.dispose();
		System.out.println("Menu State Disposed");
	}

}
