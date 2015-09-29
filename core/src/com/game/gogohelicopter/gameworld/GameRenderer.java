package com.game.gogohelicopter.gameworld;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.gogohelicopter.TweenAccessors.Value;
import com.game.gogohelicopter.TweenAccessors.ValueAccessor;
import com.game.gogohelicopter.gghhelpers.AssetLoader;
import com.game.gogohelicopter.gghhelpers.InputHandler;
import com.game.gogohelicopter.objects.Grass;
import com.game.gogohelicopter.objects.Helicopter;
import com.game.gogohelicopter.objects.Pipe;
import com.game.gogohelicopter.objects.ScrollHandler;
import com.game.gogohelicopter.ui.SimpleButton;
import com.game.gogohelicopter.screens.GameScreen;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

public class GameRenderer {
	
	private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;    
    private SpriteBatch batcher;    
    private int midPointY;
    // Game Objects
    private Helicopter helicopter;
    private ScrollHandler scroller;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;    
    // Game Assets
    private Animation helicopterAnimation;
    private TextureRegion bg, grass, helicopterMid, skullUp, skullDown, bar;    
    // Tween Stuff
    private TweenManager manager;
    private Value alpha = new Value();    
    // Buttons
    private List<SimpleButton> menuButtons;
	
	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;

        this.midPointY = midPointY;
        //this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor(InputHandler)).getMenuButtons();
        this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
        		.getMenuButtons();
        

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);
        
        batcher = new SpriteBatch();
        // Attach to camera
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        
        // Call helper methods to initialize instance variables
        initGameObjects();
        initAssets();
        setupTweens();
	}
	
	private void setupTweens() {
		Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, .5f).target(0).ease(TweenEquations.easeOutQuad)
                .start(manager);
	}

	public void render(float delta, float runTime) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);

        // Draw Background color
        shapeRenderer.setColor(132 / 255.0f, 182 / 255.0f, 214 / 255.0f, 1);
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

        // 1. Draw Grass
        drawGrass();
        // 2. Draw Pipes
        drawPipes();
        
        batcher.enableBlending();
        // 3. Draw Skulls (requires transparency)
        drawSkulls();

        if (myWorld.isRunning()) {
            drawHelicopter(runTime);
            drawScore();
        } else if (myWorld.isReady()) {
            drawHelicopter(runTime);
            drawScore();
        } else if (myWorld.isMenu()) {
            drawHelicopterCentered(runTime);
            drawMenuUI();
        } else if (myWorld.isGameOver()) {
            drawHelicopter(runTime);
            drawScore();
        } else if (myWorld.isHighScore()) {
            drawHelicopter(runTime);
            drawScore();
        }

        batcher.end();
        drawTransition(delta);

    }

    private void initGameObjects() {
        helicopter = myWorld.getHelicopter();
        scroller = myWorld.getScroller();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();  
    }

    private void initAssets() {
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        helicopterAnimation = AssetLoader.helicopterAnimation;
        helicopterMid = AssetLoader.helicopter;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }
    
    private void drawGrass() {
        // Draw the grass
        batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        // Temporary code! Sorry about the mess :)
        // We will fix this when we finish the Pipe class.

        batcher.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }
    
    private void drawHelicopterCentered(float runTime) {
        batcher.draw(helicopterAnimation.getKeyFrame(runTime), 59, helicopter.getY() - 15,
                helicopter.getWidth() / 2.0f, helicopter.getHeight() / 2.0f,
                helicopter.getWidth(), helicopter.getHeight(), 1, 1, helicopter.getRotation());
    }

    private void drawHelicopter(float runTime) {

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

    }

    private void drawPipes() {
        // Temporary code! Sorry about the mess :)
        // We will fix this when we finish the Pipe class.
        batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
    }
    
    private void drawMenuUI() {
        batcher.draw(AssetLoader.gghLogo, 136 / 2 - 56, midPointY - 50,
                AssetLoader.gghLogo.getRegionWidth() / 1.2f,
                AssetLoader.gghLogo.getRegionHeight() / 1.2f);

        for (SimpleButton button : menuButtons) {
            button.draw(batcher);
        }

    }

    private void drawScore() {
        int length = ("" + myWorld.getScore()).length();
        AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),
                68 - (3 * length), midPointY - 82);
        AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
                68 - (3 * length), midPointY - 83);
    }
    
    private void drawTransition(float delta) {
        if (alpha.getValue() > 0) {
            manager.update(delta);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeType.Filled);
            shapeRenderer.setColor(1, 1, 1, alpha.getValue());
            shapeRenderer.rect(0, 0, 136, 300);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);

        }
    }
}
