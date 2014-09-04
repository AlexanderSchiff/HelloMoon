package com.icsfl.aschiff.hellomoon;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HelloMoonFragment extends Fragment {
    private Button mPlayButton;
    private Button mPauseButton;
    private Button mStopButton;
    private MoonAudioPlayer mAudioPlayer = new MoonAudioPlayer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hello_moon, container, false);
        mPlayButton = (Button)view.findViewById(R.id.hellomoon_play_button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAudioPlayer.play(getActivity());
            }
        });
        mPauseButton = (Button)view.findViewById(R.id.hellomoon_pause_button);
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAudioPlayer.pause();
            }
        });
        mStopButton = (Button)view.findViewById(R.id.hellomoon_stop_button);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAudioPlayer.stop();
            }
        });
        updateButtons();
        return view;
    }

    private void updateButtons() {
        String currentStatus = mAudioPlayer.getStatus();
        if (currentStatus.equals("playing")) {
            mPlayButton.setVisibility(View.GONE);
            mPauseButton.setVisibility(View.VISIBLE);
            mStopButton.setVisibility(View.VISIBLE);
        } else if (currentStatus.equals("paused")) {
            mPlayButton.setVisibility(View.VISIBLE);
            mPauseButton.setVisibility(View.GONE);
            mStopButton.setVisibility(View.VISIBLE);
        } else if (currentStatus.equals("stopped")) {
            mPlayButton.setVisibility(View.VISIBLE);
            mPauseButton.setVisibility(View.GONE);
            mStopButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAudioPlayer.stop();
    }

    private class MoonAudioPlayer extends AudioPlayer {
        @Override
        public void pause() {
            super.pause();
            updateButtons();
        }

        @Override
        public void play(Context context) {
            super.play(context);
            updateButtons();
        }

        @Override
        public void stop() {
            super.stop();
            updateButtons();
        }
    }
}