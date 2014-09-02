package com.icsfl.aschiff.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by aschiff on 9/2/2014.
 */
public class AudioPlayer {
    private MediaPlayer mMediaPlayer;
    private boolean isPaused = false;

    public void pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
            isPaused = true;
        }
    }

    public void play(Context context) {
        if (!isPaused) {
            stop();
            mMediaPlayer = MediaPlayer.create(context, R.raw.one_small_step);
        }
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });
        mMediaPlayer.start();
        isPaused = false;
    }
    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            isPaused = false;
        }
    }
}
