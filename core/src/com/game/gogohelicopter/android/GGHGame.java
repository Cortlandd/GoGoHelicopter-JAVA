package com.game.gogohelicopter.android;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.game.gogohelicopter.android.gghhelpers.AssetLoader;
import com.game.gogohelicopter.android.screens.GameScreen;
import com.game.gogohelicopter.android.screens.SplashScreen;

import aurelienribon.tweenengine.TweenAccessor;

public class GGHGame extends Game {
	@Override
	public void create() {
		// Used for printing values and is
		// implemented specifically for each platform.
		Gdx.app.log("GGHGame", "created");

		// So that we can load all the images before the GameScreen is created.
		AssetLoader.load();

		// Set this class to the screen created under
		// com.kilobolt.screens/GameScreen.java
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
