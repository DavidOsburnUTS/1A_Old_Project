package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;


public class WeightTrainingActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyCs3huB6L9Qymep9aZ9po5JryI5bm4Ty9A";
    public static String VIDEO_ID = "Bi1IRzJIoAo";
    public YouTubePlayer mYoutubePlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_training);

        final Spinner activity = findViewById(R.id.bodyPart_spinner);
        Button playButton = findViewById(R.id.playBtn);
        Button moreVideosBtn = findViewById(R.id.moreVideosBtn);
        Button weightTrainingBackBtn = findViewById(R.id.weightTrainingBackBtn);

        final String[] bodyPart = new String[]{
                "Chest", "Back", "Biceps and triceps", "Abs", "Legs"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, bodyPart);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activity.setAdapter(adapter);

        /** Initializing YouTube Player View **/
        final YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bodyPartID = activity.getSelectedItemPosition();
                playVideo(bodyPartID);

            }
        });

        moreVideosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (WeightTrainingActivity.this, FitnessBlenderActivity.class);
                startActivity(intent);
            }
        });

        weightTrainingBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void playVideo(int bodyPart){
            if (bodyPart == 0) {
                VIDEO_ID = "Bi1IRzJIoAo";
                mYoutubePlayer.loadVideo(VIDEO_ID);


            } else if (bodyPart == 1) {
                VIDEO_ID = "p1GERX3lf3Y";
                mYoutubePlayer.loadVideo(VIDEO_ID);


            } else if (bodyPart == 2) {
                VIDEO_ID = "KqZG-vlcYhg";
                mYoutubePlayer.loadVideo(VIDEO_ID);

            } else if (bodyPart == 3) {
                VIDEO_ID = "1919eTCoESo";
                mYoutubePlayer.loadVideo(VIDEO_ID);

            }
            else if (bodyPart == 4) {
                VIDEO_ID = "E4tiWhsQhQg";
                mYoutubePlayer.loadVideo(VIDEO_ID);

            }

    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failure to Initialize!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        /** add listeners to YouTubePlayer instance **/
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        /** Start buffering **/
        if (!wasRestored) {
            player.loadVideo(VIDEO_ID);
        }
        mYoutubePlayer = player;
    }

    private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }
        @Override
        public void onPaused() {
        }
        @Override
        public void onPlaying() {
        }
        @Override
        public void onSeekTo(int arg0) {
        }
        @Override
        public void onStopped() {
        }
    };

    private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }
        @Override
        public void onError(ErrorReason arg0) {
        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
        }
        @Override
        public void onVideoStarted() {
        }
    };

}
