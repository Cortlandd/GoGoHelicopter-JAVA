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
import com.game.gogohelicopter.objects.Helicopter;

public class GameWorld {
	
	private Helicopter helicopter;
	
	public GameWorld(int midPointY) {
		// Initialize Helicopter
		// x = 33 | where the bird stays the entire time
		// y = midPointY - 5
		// width = 17
		// height = 12
		helicopter = new Helicopter(33, midPointY - 5, 17, 12);
	}
	
	public void update(float delta) {
		helicopter.update(delta);
	}
	
	// Getter method
	public Helicopter getHelicopter() {
		return helicopter;
	}
}
