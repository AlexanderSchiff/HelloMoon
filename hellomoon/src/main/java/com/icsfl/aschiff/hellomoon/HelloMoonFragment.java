package com.icsfl.aschiff.hellomoon;



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
    private AudioPlayer mAudioPlayer = new AudioPlayer();
    private boolean buttonsPressed = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hello_moon, container, false);
        mPlayButton = (Button)view.findViewById(R.id.hellomoon_play_button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAudioPlayer.play(getActivity());
                mPlayButton.setVisibility(View.GONE);
                mPauseButton.setVisibility(View.VISIBLE);
                mStopButton.setVisibility(View.VISIBLE);
                if (!buttonsPressed)
                    buttonsPressed = true;
            }
        });
        mPauseButton = (Button)view.findViewById(R.id.hellomoon_pause_button);
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAudioPlayer.pause();
                mPlayButton.setVisibility(View.VISIBLE);
                mPauseButton.setVisibility(View.GONE);
                mStopButton.setVisibility(View.VISIBLE);
                if (!buttonsPressed)
                    buttonsPressed = true;
            }
        });
        mStopButton = (Button)view.findViewById(R.id.hellomoon_stop_button);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAudioPlayer.stop();
                mPlayButton.setVisibility(View.VISIBLE);
                mPauseButton.setVisibility(View.GONE);
                mStopButton.setVisibility(View.GONE);
                if (!buttonsPressed)
                    buttonsPressed = true;
            }
        });
        if (!buttonsPressed) {
            mPlayButton.setVisibility(View.VISIBLE);
            mPauseButton.setVisibility(View.GONE);
            mStopButton.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAudioPlayer.stop();
    }
}