package com.game.gogohelicopter.objects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.game.gogohelicopter.gghhelpers.AssetLoader;

public class Helicopter {
	// Basically the position, speed, etc.
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;

	// For when the helicopter rotate.
	private float rotation; // For handling Helicopter rotation
	private int width;
	private int height;
	
	private Circle boundingCircle;
	
	private boolean isAlive;
	
	public Helicopter(float x, float y, int width, int height) {
		// - what its X position should be
		// - what its Y position should be
		// - how wide it should be
		// - how tall it should be
		
		this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
        boundingCircle = new Circle();
        isAlive = true;
		
	}
	
	/* So Basically: (Bottom)
	 * 1. Add scaled acceleration vector to velocity vector. 
	 * This gives a new velocity. Basically: this is how the
	 * earth's gravity works. The downward speed increases by
	 * 9.8 m/s every second.

	 * 2. Flappy Bird physics has a max velocity cap 
	 * (there's some sort of terminal velocity). I set a velocity.y cap at 200.

	 * 3. We add the updated scaled velocity to the bird's position (this gives
	 * us our new position).
	  
	 * Scaled: multiply the acceleration and velocity vectors by the delta, which
	 * is the amount of time that has passed since the update method was previously
	 * called. This has a normalizing effect. 
	 * 
	 * By scaling our Vectors with delta, we can achieve frame-rate independent movement.
	 */
	
	// Called when GameWorld.class updates
	public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 200) {
            velocity.y = 200;
        }
        
        // Ceiling Check
        if (position.y < -13) {
        	position.y = -13;
        	velocity.y = 0;
        }

        position.add(velocity.cpy().scl(delta));
        
        // Set the circle's center to be (9, 6) with respect to the bird.
        // Set the circle's radius to be 6.5f;
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

        // Rotate counterclockwise
        if (velocity.y < 0) {
            rotation -= 600 * delta;

            if (rotation < 1) {
                rotation = 1;
            }
        }

        // Rotate clockwise
        if (isFalling()) {
            rotation += 480 * delta;
            if (rotation > 90) {
                rotation = 90;
            }
        }
        
        if (isFalling() || !isAlive) {
        	rotation += 480 * delta;
        	if (rotation > 90) {
        		rotation = 90;
        	}
        }

    }

    public boolean isFalling() {
        return velocity.y > 110;
    }

    // Animation when helicopter is dead
    public boolean shouldntFlap() {
        return velocity.y > 70 || !isAlive;
    }
    
    public boolean isAlive() {
    	return isAlive;
    }
	
	public void onClick() {
        if (isAlive) {
        	velocity.y = -140;
        }
    }
	
	public void die() {
		isAlive = false;
		velocity.y = 0;
	}
	
	public void decelerate() {
		// Stop the helicopter accelerating downwards once its crashed.
		acceleration.y = 0;
	}

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }
    
    public Circle getBoundingCircle() {
    	return boundingCircle;
    }
	
}
