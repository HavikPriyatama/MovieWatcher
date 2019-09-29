package com.moviewatcher.mvw.activity;

import android.os.Bundle;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.moviewatcher.mvw.BuildConfig;
import com.moviewatcher.mvw.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.moviewatcher.mvw.helper.Constant.YOUTUBE_KEY;
public class VideoPlayActivity extends YouTubeBaseActivity {

    private static final String YOUTUBE_API_KEY = BuildConfig.YoutubeApiKey;

    @BindView(R.id.player_video)
    YouTubePlayerView videoPlayer;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        key = getIntent().getStringExtra(YOUTUBE_KEY);
        ButterKnife.bind(this);
        videoPlayer.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.setFullscreen(true);
                youTubePlayer.loadVideo(key);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}
