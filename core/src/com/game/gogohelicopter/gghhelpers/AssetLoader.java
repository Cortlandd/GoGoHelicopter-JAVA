package com.game.gogohelicopter.gghhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture texture, logoTexture;
    public static TextureRegion logo, gghLogo, bg, grass, helicopter, helicopterDown, helicopterUp, 
    skullUp, skullDown, bar, playButtonUp, playButtonDown, ready, gameOver, highScore, scoreboard, 
    star, noStar, retry;

    public static Animation helicopterAnimation;
    
    public static Sound dead;
    
    public static BitmapFont font, shadow, whiteFont;
    public static Preferences prefs;

    public static void load() {

    	logoTexture = new Texture(Gdx.files.internal("data/logo-rev.png"));
    	logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	
    	logo = new TextureRegion(logoTexture, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    	
        texture = new Texture(Gdx.files.internal("data/Untitled2.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        playButtonUp = new TextureRegion(texture, 2, 349, 118, 61);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        gghLogo = new TextureRegion(texture, 503, 280, 94, 24);
        gghLogo.flip(false, true);
        
        bg = new TextureRegion(texture, 0, 0, 566, 176);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 177, 691, 49);
        grass.flip(false, true);

        helicopterDown = new TextureRegion(texture, 570, 3, 69, 41);
        helicopterDown.flip(false, true);

        helicopter = new TextureRegion(texture, 641, 3, 69, 41);
        helicopter.flip(false, true);

        helicopterUp = new TextureRegion(texture, 710, 3, 69, 41);
        helicopterUp.flip(false, true);

        TextureRegion[] helicopters = { helicopterDown, helicopter, helicopterUp };
        helicopterAnimation = new Animation(0.06f, helicopters);
        helicopterAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        
        playButtonUp = new TextureRegion(texture, 2, 349, 119, 61);
		playButtonDown = new TextureRegion(texture, 121, 344, 119, 61);
		playButtonUp.flip(false, true);
		playButtonDown.flip(false, true);

		ready = new TextureRegion(texture, 244, 345, 143, 32);
		ready.flip(false, true);

		retry = new TextureRegion(texture, 244, 457, 143, 31);
		retry.flip(false, true);
		
		gameOver = new TextureRegion(texture, 244, 382, 195, 30);
		gameOver.flip(false, true);

		scoreboard = new TextureRegion(texture, 462, 344, 405, 154);
		scoreboard.flip(false, true);

		star = new TextureRegion(texture, 643, 290, 32, 42);
		noStar = new TextureRegion(texture, 689, 290, 32, 42);

		star.flip(false, true);
		noStar.flip(false, true);

		highScore = new TextureRegion(texture, 244, 420, 201, 31);
		highScore.flip(false, true);

        skullUp = new TextureRegion(texture, 801, 0, 98, 63);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 567, 66, 93, 14);
        bar.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/bomb.mp3"));
        
        // Fonts
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.getData().setScale(.25f, -.25f);
        
        whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		whiteFont.getData().setScale(.1f, -.1f);
		
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);
        
        // Create or retrieve preference file
        prefs = Gdx.app.getPreferences("GoGoHelicopter");
        
        // PRovide default high school of 0
        if (!prefs.contains("highScore")) {
        	prefs.putInteger("highScore", 0);
        }
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
        
        dead.dispose();
        font.dispose();
        shadow.dispose();
    }
    
    // Receives an integer and maps it to the String highScore in prefs
    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    // Retrieves the current high score
    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }
}
