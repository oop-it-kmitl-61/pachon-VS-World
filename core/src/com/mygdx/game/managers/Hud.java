package com.mygdx.game.managers;

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
	
	//Scene2D.ui Stage and its own Viewport for HUD
    public Stage stage;
    private Viewport viewport;
	
    //Pachon score/time Tracking Variables
    private static Integer worldTimer;
    public static boolean timeUp; // true when the world timer reaches 0
    private static float timeCount;
    private static Integer score;
    
  //Scene2D widgets
    private static Label countdownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label pachonLabel;

    public Hud(SpriteBatch sb){
        //define our tracking variables
        worldTimer = 180;
        timeCount = 0;
        score = 0;
       
        sb = new SpriteBatch();
        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        viewport = new FitViewport(840, 600, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);

        //define our labels using the String, and a Label style consisting of a font and color
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel =new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("Use Up Right Left and Down keys to play", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("Pach0n VS World", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pachonLabel = new Label("Score", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        
        countdownLabel.setFontScale(2);
        scoreLabel.setFontScale(2);
        timeLabel.setFontScale(2);
        levelLabel.setFontScale(2);
        worldLabel.setFontScale(2);
        pachonLabel.setFontScale(2);

        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add(pachonLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        //add a second row to our table
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        //add our table to the stage
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
        }
    }

    public void addScore(int value){
        score = value;
        scoreLabel.setText(String.format("%04d", score));
    }

    @Override
    public void dispose() { stage.dispose(); }
    

}
