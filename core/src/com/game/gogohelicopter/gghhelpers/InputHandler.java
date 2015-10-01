package com.game.gogohelicopter.gghhelpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.game.gogohelicopter.objects.Helicopter;
import com.game.gogohelicopter.ui.SimpleButton;
import com.game.gogohelicopter.gameworld.*;

public class InputHandler implements InputProcessor {
	
    private Helicopter myHelicopter;
    private GameWorld myWorld;
    
    private List<SimpleButton> menuButtons;
    private SimpleButton playButton;
    
    private float scaleFactorX;
    private float scaleFactorY;

    // Ask for a reference to the helicopter when InputHandler is created.
    public InputHandler(GameWorld myWorld, float scaleFactorX, float scaleFactorY) {
        // myhelicopter now represents the gameWorld's helicopter.
        this.myWorld = myWorld;
        myHelicopter = myWorld.getHelicopter();
        
        int midPointY = myWorld.getMidPointY();
        
        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(
                136 / 2 - (AssetLoader.playButtonUp.getRegionWidth() / 2),
                midPointY + 50, 29, 16, AssetLoader.playButtonUp,
                AssetLoader.playButtonDown);
        menuButtons.add(playButton);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	screenX = scaleX(screenX);
    	screenY = scaleY(screenY);
    	
        if (myWorld.isMenu()) {
            playButton.isTouchDown(screenX, screenY);
        } else if (myWorld.isReady()) {
        	myWorld.start();
        }

        myHelicopter.onClick();

        if (myWorld.isGameOver() || myWorld.isHighScore()) {
            // Reset all variables, go to GameState.READ
            myWorld.retry();
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (myWorld.isMenu()) {
            if (playButton.isTouchUp(screenX, screenY)) {
                myWorld.ready();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {

        // Can now use Space Bar to play the game
        if (keycode == Keys.SPACE) {

            if (myWorld.isMenu()) {
                myWorld.ready();
            } else if (myWorld.isReady()) {
                myWorld.start();
            }

            myHelicopter.onClick();

            if (myWorld.isGameOver() || myWorld.isHighScore()) {
                myWorld.retry();
            }

        }

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
    
    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }

}