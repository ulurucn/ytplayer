package vip.frendy.ytdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import vip.frendy.ytplayer.YTPlayerView;

/**
 * Created by frendy on 2017/11/17.
 */

public class YoutubePlayerActivity extends Activity {

    private YTPlayerView mPlayerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);

        mPlayerView = findViewById(R.id.player_view);
        mPlayerView.setVideoId("H6SShCF58-U");
    }
}
