package com.mygdx.game.managers;

import java.util.Stack;

import com.mygdx.game.Pachon;
//import com.mygdx.game.states.GameState;
import com.mygdx.game.states.*;

public class GameStateManager {
//	Application Ref
	private final Pachon app;
	private Stack<GameState> states;
	
	public enum State{
		SPLASH,
		PLAY
	}
	public GameStateManager(final Pachon app) {
		this.app = app;
		this.states = new Stack<GameState>();
		this.setState(State.SPLASH);
	}
	public Pachon pachon() {
		return app;
	}
	
	public void update(float dalta) {
		states.peek().update(dalta);
	}
	
	public void render() {
		states.peek().render();
	}
	public void dispose() {
		for(GameState gs: states) {
			gs.dispose();
		}
		states.clear();
	}
	public void resize(int w, int h) {
		states.peek().resize(w,h);
	}
	public void setState(State state) {
		if(states.size() >= 1) {
			states.pop().dispose();
		}
		states.push(getState(state));
	}
	private GameState getState(State state) {
		switch (state){
		case SPLASH: return new SplashState(this);
		case PLAY: return new PlayState(this);
		}
		return null;
	}
	
}
