package com.game.gogohelicopter.gghhelpers;

import com.badlogic.gdx.InputProcessor;
import com.game.gogohelicopter.objects.Helicopter;

public class InputHandler implements InputProcessor {
	
	private Helicopter myHelicopter;
	
	// Ask for a reference to the Helicopter when InputHandler is created.
	public InputHandler(Helicopter helicopter) {
		// myHelicopter now represents the GameWorlds helicopter.
		myHelicopter = helicopter;
	}
	
	@Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		myHelicopter.onClick();
        return true; // Return true to say I handled the touch
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
