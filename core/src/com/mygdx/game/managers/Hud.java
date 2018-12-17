package com.mygdx.game.managers;

import java.io.Console;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.*;


public class Hud implements Disposable{
	
    public Stage stage;
    private Viewport viewport;
	
    private static Integer worldTimer;
    public static boolean timeUp; // true when the world timer reaches 0
    private static float timeCount;
    private static Integer score;
    
    private static Label countdownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label tulabel;
    private Label worldLabel;
    private Label marioLabel;

    public Hud(SpriteBatch sb){
        worldTimer = 120;
        timeCount = 0;
        score = 0;
        viewport = new FitViewport(840, 600, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel =new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        tulabel = new Label("Up Right Left  key to play", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("Pach0n VS World", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        marioLabel = new Label("Score", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(tulabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);

    }
    
    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1) {
        	if(worldTimer > 0) {
        		worldTimer--;
        	} else {
        		timeUp = true;
        	}
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
            timeUp = (worldTimer == 0) ? true: false;
            System.out.println(timeUp);
        }
    }
    public static void addScore(int value){
        score = value;
        scoreLabel.setText(String.format("%03d", score));
    }

    @Override
    public void dispose() { stage.dispose(); }

	public static Integer getWorldTimer() {
		return worldTimer;
	}

	public static void setWorldTimer(Integer worldTimer) {
		Hud.worldTimer = worldTimer;
	}

	public static boolean isTimeUp() {
		return timeUp;
	}

	public static void setTimeUp(boolean timeUp) {
		Hud.timeUp = timeUp;
	}
    

}
