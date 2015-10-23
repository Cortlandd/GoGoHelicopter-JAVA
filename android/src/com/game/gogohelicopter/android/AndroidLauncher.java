package com.game.gogohelicopter.android;

import android.os.Bundle;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.game.gogohelicopter.GGHGame;
import com.game.gogohelicopter.AdInterface;

import com.jirbo.adcolony.*;

import com.saturnup.sdk.Saturnup;
import com.saturnup.sdk.question.*;

public class AndroidLauncher extends AndroidApplication implements AdInterface {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new GGHGame(), config);

        // Configure Saturnup
        Saturnup.initialize(this, "saturn_jnc41urYd7JFk9dqqgbXKA_up");

        // Configure AdColony
        String clientOptions = "";
        String appId = "appdfdf8080a0f7404a8b";
        String zoneIds = "vz7b7f0f27e4014db285";
        AdColony.configure(this, clientOptions, appId, zoneIds);

        addLifecycleListener(new LifecycleListener() {
            public void dispose() { }

            public void pause() {
                AdColony.pause();
            }

            public void resume() {
                AndroidLauncher.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AdColony.resume(AndroidLauncher.this);
                    }
                });
            }
        });
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
