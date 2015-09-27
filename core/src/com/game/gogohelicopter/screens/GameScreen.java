package com.game.gogohelicopter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.game.gogohelicopter.gameworld.*;
import com.game.gogohelicopter.gghhelpers.InputHandler;

public class GameScreen implements Screen {
	
	private GameWorld world;
	private GameRenderer renderer;
	
	public GameScreen() {
		float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY);
		renderer = new GameRenderer(world);
		
		Gdx.input.setInputProcessor(new InputHandler(world.getHelicopter()));
		// Above line^ = 
		/* 
		 * Helicopter helicopter = world.getHelicopter();
		 * InputHandler handler = new InputHandler(helicopter);
		 * Gdx.input.setInputProcessor(handler);
		 * 
		 * But simplified
		 */
	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
	}

	// Will basically be the game loop
	@Override
	public void render(float delta) {
		world.update(delta);
		renderer.render();
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
