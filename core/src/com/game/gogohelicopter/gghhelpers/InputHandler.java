package com.game.gogohelicopter.gghhelpers;

import com.badlogic.gdx.InputProcessor;
import com.game.gogohelicopter.objects.Helicopter;
import com.game.gogohelicopter.gameworld.*;

public class InputHandler implements InputProcessor {
    private Helicopter myHelicopter;
    private GameWorld myWorld;

    // Ask for a reference to the helicopter when InputHandler is created.
    public InputHandler(GameWorld myWorld) {
        // myhelicopter now represents the gameWorld's helicopter.
        this.myWorld = myWorld;
        myHelicopter = myWorld.getHelicopter();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (myWorld.isReady()) {
            myWorld.start();
        }

        myHelicopter.onClick();

        if (myWorld.isGameOver()) {
            // Reset all variables, go to GameState.READ
            myWorld.restart();
        }

        return true;
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