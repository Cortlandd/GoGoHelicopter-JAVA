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

package com.game.gogohelicopter.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.game.gogohelicopter.gghhelpers.AssetLoader;
import com.game.gogohelicopter.objects.Helicopter;
import com.game.gogohelicopter.objects.ScrollHandler;

public class GameWorld {
	
	private Helicopter helicopter;
	private ScrollHandler scroller;
	
	private Rectangle ground;
	
	public GameWorld(int midPointY) {
		// Initialize Helicopter
		helicopter = new Helicopter(33, midPointY - 5, 17, 12);
		// The grass should start 66 pixels below the midPointY
		scroller = new ScrollHandler(midPointY + 66);
		// Ground
		ground = new Rectangle(0, midPointY + 66, 136, 11);
	}
	
	public void update(float delta) {
		
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
			AssetLoader.dead.play();
		}
		
		if (Intersector.overlaps(helicopter.getBoundingCircle(), ground)) {
			scroller.stop();
			helicopter.die();
			helicopter.decelerate();
		}
	}
	
	// Getter method
	public Helicopter getHelicopter() {
		return helicopter;
	}
	
	public ScrollHandler getScroller() {
        return scroller;
    }
}
