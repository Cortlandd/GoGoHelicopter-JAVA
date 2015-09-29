package com.game.gogohelicopter.gghhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture texture;
    public static TextureRegion bg, grass;

    public static Animation helicopterAnimation;
    public static TextureRegion helicopter, helicopterDown, helicopterUp;
    public static TextureRegion skullUp, skullDown, bar;
    
    public static Sound dead;
    
    public static BitmapFont font, shadow;

    public static void load() {

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        helicopterDown = new TextureRegion(texture, 136, 0, 17, 12);
        helicopterDown.flip(false, true);

        helicopter = new TextureRegion(texture, 153, 0, 17, 12);
        helicopter.flip(false, true);

        helicopterUp = new TextureRegion(texture, 170, 0, 17, 12);
        helicopterUp.flip(false, true);

        TextureRegion[] helicopters = { helicopterDown, helicopter, helicopterUp };
        helicopterAnimation = new Animation(0.06f, helicopters);
        helicopterAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/bomb.mp3"));
        
        // Fonts
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        ((BitmapFont) font).setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(.25f, -.25f);
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
        dead.dispose();
        font.dispose();
        shadow.dispose();
    }
}
