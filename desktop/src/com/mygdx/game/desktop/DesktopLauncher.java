package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.pachon;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 840;
		config.height = 600;
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		config.title = "Pachon VS World";
		new LwjglApplication(new pachon(), config);
	}
}
