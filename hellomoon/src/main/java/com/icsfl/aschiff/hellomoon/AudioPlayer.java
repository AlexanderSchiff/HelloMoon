package com.icsfl.aschiff.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by aschiff on 9/3/2014.
 */
public class AudioPlayer {
    private String mStatus = "stopped";
    private MediaPlayer mMediaPlayer;

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public void play(Context context) {
        if (!getStatus().equals("paused")) {
            stop();
            mMediaPlayer = MediaPlayer.create(context, R.raw.one_small_step);
        }
        setStatus("playing");
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });
    }


    public void pause() {
        if (mMediaPlayer != null) {
            setStatus("paused");
            mMediaPlayer.pause();
        }
    }

    public void stop() {
        if (mMediaPlayer != null) {
            setStatus("stopped");
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}