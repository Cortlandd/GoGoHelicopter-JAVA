// The objects inside the game n shit

/* 
 * NOTES
 * 
 * Caution
 * Every time you create a new Object, you allocate a little bit of memory in
 * RAM for that object (specifically in the Heap). Once your Heap fills up, a
 * subroutine called a Garbage Collector will come in and clean up your memory
 * to ensure that you do not run out of space. This is great, except when you
 * are building a game. Every time the garbage collector comes into play, your
 * game will stutter for several essential milliseconds. To avoid garbage
 * collection, you need to avoid allocating new objects whenever possible.
 * 
 */

package com.game.gogohelicopter.android.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.game.gogohelicopter.android.gghhelpers.AssetLoader;
import com.game.gogohelicopter.android.objects.Helicopter;
import com.game.gogohelicopter.android.objects.ScrollHandler;

public class GameWorld {
	
	private Helicopter helicopter;
	private ScrollHandler scroller;
	private Rectangle ground;
	private float runTime = 0;
	private int midPointY;
	
	// Create the initial score
	private int score = 0;
	
	// Gamestate
	private GameState currentState;
	private GameRenderer renderer;
	
	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
	}
	
	public GameWorld(int midPointY) {
		currentState = GameState.MENU;
		this.midPointY = midPointY;
		// Initialize Helicopter and its position
		helicopter = new Helicopter(33, midPointY - 5, 17, 12);
		// The grass should start 66 pixels below the midPointY
		scroller = new ScrollHandler(this, midPointY + 66);
		// Ground
		ground = new Rectangle(0, midPointY + 66, 136, 11);
	}
	
	public void update(float delta) {
		runTime += delta;
		
		switch (currentState) {
			case READY:
				
			case MENU:
				updateReady(delta);
				break;
				
            case RUNNING:
				updateRunning(delta);
				break;
				
			default:
				break;
		}
	}
	
	private void updateReady(float delta) {
		helicopter.updateReady(runTime);
		scroller.updateReady(delta);
	}
	
	private void updateRunning(float delta) {
		// Add a delta cap so that if the game takes too long
		// to update, it won't break the collision detection

		if (delta > .15f){
			delta = .15f;
		}
		
		helicopter.update(delta);
		scroller.update(delta);
		
		// is helicopter is still flying and then collides with the buildings
        if (scroller.collides(helicopter) && helicopter.isAlive()) {
			// Clean up on game over
			scroller.stop();
			helicopter.die();
		}
		
		if (Intersector.overlaps(helicopter.getBoundingCircle(), ground)) {
			scroller.stop();
			helicopter.die();
			helicopter.decelerate();
			currentState = GameState.GAMEOVER;
			
			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
				currentState = GameState.HIGHSCORE;
			}
		}
	}
	
	// Getter methods
	public Helicopter getHelicopter() {
		return helicopter;
	}
	
	public int getMidPointY() {
		return midPointY;
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore(int increment) {
		score += increment;
	}
	
	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}
	
	public boolean isReady() {
		return currentState == GameState.READY;
	}
	
	public void start() {
		currentState = GameState.RUNNING;
	}

    public void retry() {
		restart();
    }
	
	public void restart() {
		currentState = GameState.READY;
        score = 0;
        helicopter.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
    
    public ScrollHandler getScroller() {
    	return scroller;
    }
    
    public void ready() {
    	currentState = GameState.READY;
    }
    
    public boolean isMenu() {
    	return currentState == GameState.MENU;
    }
    
    public boolean isRunning() {
    	return currentState == GameState.RUNNING;
    }

    public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}
}
