package com.game.gogohelicopter.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.game.gogohelicopter.GGHGame;
import com.game.gogohelicopter.AdInterface;
import com.saturnup.sdk.Saturnup;
import com.saturnup.sdk.question.*;

public class AndroidLauncher extends AndroidApplication implements AdInterface {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new GGHGame(), config);
		Saturnup.initialize(this, "API_KEY");
	}

    @Override
    public void show(final AdInterface.DoneCallback callback) {
        Saturnup
            .questionAd(this)
            .listener(new QuestionAdListener() {
                @Override
                public void onResult(QuestionAdResult result) {
                    callback.done();
                }
            })
        .show();
    }
}
