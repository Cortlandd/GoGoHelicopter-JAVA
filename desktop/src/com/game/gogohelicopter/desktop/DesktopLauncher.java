package com.game.gogohelicopter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.gogohelicopter.GGHGame;
import com.game.gogohelicopter.AdInterface;

public class DesktopLauncher implements AdInterface {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Go Go Helicopter";
        config.width = 272;
        config.height = 408;
		new LwjglApplication(new GGHGame(), config);
	}

    @Override
    public void showAd(AdInterface.DoneCallback callback) {
        // Unimplemented
        callback.done();
    }
}
