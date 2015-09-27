package com.game.gogohelicopter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.game.gogohelicopter.screens.GameScreen;

public class GGHGame extends Game {

	@Override
	public void create() {
		// Used for printing values and is 
		// implemented specifically for each platform.
		Gdx.app.log("GGHGame", "created");
		
		// Set this class to the screen created under
		// com.kilobolt.screens/GameScreen.java
		setScreen(new GameScreen());
	}
	
}