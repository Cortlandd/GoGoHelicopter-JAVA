package com.kilobolt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import com.kilobolt.gameworld.*;

public class GameScreen implements Screen {
	
	private GameWorld world;
	private GameRenderer renderer;
	
	public GameScreen() {
		Gdx.app.log("GameScreen", "Attached");
	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
	}

	// Will basically be the game loop
	@Override
	public void render(float delta) {
		// Sets a Color to Fill the Screen with (RGB = 10, 15, 230), Opacity of 1 (100%)
	    Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f);
	    // Fills the screen with the selected color
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    // Covert Frame rate to String, print it
	    Gdx.app.log("GameScreen FPS", (1/delta) + "");
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("GameScreen", "resizing");
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");
	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide called");
	}

	@Override
	public void dispose() {
		
	}

}
