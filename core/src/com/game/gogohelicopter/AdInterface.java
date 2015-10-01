package com.game.gogohelicopter;

public interface AdInterface {

    public interface DoneCallback {
        void done();
    }

    void show(DoneCallback callback);

}
