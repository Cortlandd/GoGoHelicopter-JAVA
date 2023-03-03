package com.game.gogohelicopter.android.objects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

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
	private float originalY;
	
	public Helicopter(float x, float y, int width, int height) {
		this.width = width;
        this.height = height;
        this.originalY = y;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
        boundingCircle = new Circle();
        isAlive = true;
		
	}
	
	// Called when GameWorld.class updates
	public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 200) {
            velocity.y = 200;
        }
        
        // Ceiling check
        if (position.y < -3) {
        	position.y = -3;
            die();
        	velocity.y = 0;
        }

        position.add(velocity.cpy().scl(delta));
        
        // Set the circle's center to be (9, 6) with respect to the bird.
        // Set the circle's radius to be 6.5f;
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

        // Rotate counterclockwise
        if (velocity.y < 0) {
            rotation -= 600 * delta;

            if (rotation < 5) {
                rotation = 5;
            }
        }
        
        if (isFalling() || !isAlive) {
        	rotation += 480 * delta;
        	if (rotation > 45) {
        		rotation = 45;
        	}
        }

    }
	
	public void updateReady(float runTime) {
		position.y = 2 * (float) Math.sin(7 * runTime) + originalY;
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
    
    public void onRestart(int y) {
        rotation = 0;
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = 460;
        isAlive = true;
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
