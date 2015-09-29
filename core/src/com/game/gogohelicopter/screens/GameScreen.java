package com.game.gogohelicopter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.game.gogohelicopter.gameworld.GameRenderer;
import com.game.gogohelicopter.gameworld.GameWorld;
import com.game.gogohelicopter.gghhelpers.InputHandler;

public class GameScreen implements Screen {
	
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;
	
	public GameScreen() {
		float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY);
        Gdx.input.setInputProcessor(new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight));
        renderer = new GameRenderer(world, (int) gameHeight, midPointY);
	}

	// Will basically be the game loop
	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(delta, runTime);
	}
	
	@Override
	public void show() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

}
