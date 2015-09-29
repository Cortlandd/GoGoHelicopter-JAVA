package com.game.gogohelicopter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

// GL20 IS A MORE UPDATED VERSION
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.gogohelicopter.GGHGame;
import com.game.gogohelicopter.TweenAccessors.SpriteAccessor;
import com.game.gogohelicopter.gghhelpers.AssetLoader;
  
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

public class SplashScreen implements Screen {

    private TweenManager manager;
    private SpriteBatch batcher;
    private Sprite sprite;
    private GGHGame game;

    public SplashScreen(GGHGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        sprite = new Sprite(AssetLoader.logo);
        sprite.setColor(1, 1, 1, 0);

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        sprite.setSize(width, height);
        sprite.setPosition(25, -100);
        setupTween();
        batcher = new SpriteBatch();
    }

    private void setupTween() {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        manager = new TweenManager();

        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                game.setScreen(new GameScreen());
            }
        };

        Tween.to(sprite, SpriteAccessor.ALPHA, .8f).target(1)
                .ease(TweenEquations.easeInOutQuad).repeatYoyo(1, .4f)
                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
                .start(manager);
    }

    @Override
    public void render(float delta) {
        manager.update(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batcher.begin();
        sprite.draw(batcher);
        batcher.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}