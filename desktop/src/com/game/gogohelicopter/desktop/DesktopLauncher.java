package com.game.gogohelicopter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.gogohelicopter.GGHGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Go Go Helicopter";
        config.width = 272;
        config.height = 408;
		new LwjglApplication(new GGHGame(), config);
	}
}
