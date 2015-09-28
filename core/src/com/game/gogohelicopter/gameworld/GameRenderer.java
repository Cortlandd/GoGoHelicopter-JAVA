// Render the objects inside the game n shit

package com.game.gogohelicopter.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.gogohelicopter.gghhelpers.AssetLoader;
import com.game.gogohelicopter.objects.Helicopter;

public class GameRenderer {
	
	private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    
    private SpriteBatch batcher;
    
    private int gameHeight;
    private int midPointY;
    
    // Game Objects
    private Helicopter helicopter;
    
    // Game Assets
    private TextureRegion bg, grass;
    private Animation helicopterAnimation;
    private TextureRegion helicopterMid, helicopterDown, helicopterUp;
    private TextureRegion skullUp, skullDown, bar;
	
	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;
		
		// The word "this" refers to this instance.
        // We are setting the instance variables' values to be that of the
        // parameters passed in from GameScreen.
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, 204);
        
        batcher = new SpriteBatch();
        // Attach to camera
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        
        // Call helper methods to initialize instance variables
        initGameObjects();
        initAssets();
	}
	
	public void render(float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);

        // Draw Background color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Draw Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(bg, 0, midPointY + 23, 136, 43);

        batcher.enableBlending();

        if (helicopter.shouldntFlap()) {
            batcher.draw(helicopterMid, helicopter.getX(), helicopter.getY(),
                    helicopter.getWidth() / 2.0f, helicopter.getHeight() / 2.0f,
                    helicopter.getWidth(), helicopter.getHeight(), 1, 1, helicopter.getRotation());

        } else {
            batcher.draw(helicopterAnimation.getKeyFrame(runTime), helicopter.getX(),
                    helicopter.getY(), helicopter.getWidth() / 2.0f,
                    helicopter.getHeight() / 2.0f, helicopter.getWidth(), helicopter.getHeight(),
                    1, 1, helicopter.getRotation());
        }

        batcher.end();

    }

    private void initGameObjects() {
        helicopter = myWorld.getHelicopter();
    }

    private void initAssets() {
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        helicopterAnimation = AssetLoader.helicopterAnimation;
        helicopterMid = AssetLoader.helicopter;
        helicopterDown = AssetLoader.helicopterDown;
        helicopterUp = AssetLoader.helicopterUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }
}
