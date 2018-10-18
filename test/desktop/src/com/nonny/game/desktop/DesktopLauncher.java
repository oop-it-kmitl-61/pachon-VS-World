package com.nonny.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nonny.game.NonnyDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = NonnyDemo.WIDTH;
		config.height = NonnyDemo.HEIGHT;
		config.title = NonnyDemo.TITLE;
		new LwjglApplication(new NonnyDemo(), config);
	}
}
