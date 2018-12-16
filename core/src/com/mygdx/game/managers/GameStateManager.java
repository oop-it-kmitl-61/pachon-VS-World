package com.mygdx.game.managers;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Pachon;
//import com.mygdx.game.states.GameState;
import com.mygdx.game.states.*;

public class GameStateManager {
//	Application Ref
	private final Pachon app;
	private Stack<GameState> states;
	
	public GameStateManager(final Pachon app) {
		this.app = app;
		this.states = new Stack<GameState>();
	}
	public Pachon pachon() {
		return app;
	}
	
//	public void resize(int w, int h) {
//		states.peek().resize(w,h);
//	}
	public void push(GameState state) {
		states.push(state);
	}
	public void pop() {
		states.pop();
	}
	public void setState(GameState state) {
		states.pop();
		states.push(state);
	}
	public void update(float dalta) {
		states.peek().update(dalta);
	}
	
	public void render(SpriteBatch batch) {
		states.peek().render(batch);
	}
	public void dispose() {
		for(GameState gs: states) {
			gs.dispose();
		}
		states.clear();
	}
}
