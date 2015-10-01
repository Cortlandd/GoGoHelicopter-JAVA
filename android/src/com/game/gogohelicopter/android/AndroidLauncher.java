package com.game.gogohelicopter.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.game.gogohelicopter.GGHGame;
import com.game.gogohelicopter.AdInterface;

public class AndroidLauncher extends AndroidApplication implements AdInterface {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new GGHGame(), config);
	}

    @Override
    public void show(final AdInterface.DoneCallback callback) {
        // Unimplemented
        callback.done();
    }
}
