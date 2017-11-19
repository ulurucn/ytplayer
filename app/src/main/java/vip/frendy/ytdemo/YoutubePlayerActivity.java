package vip.frendy.ytdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import vip.frendy.ytplayer.YTPlayerView;

/**
 * Created by frendy on 2017/11/17.
 */

public class YoutubePlayerActivity extends Activity {

    private YTPlayerView mPlayerView;

    private ArrayList<String> mVideoIds = new ArrayList<String>() {{
        //dmLSM9zM0ME - 59 secs video
        //M7lc1UVf-VE - iframe API video
        //H6SShCF58-U
        //3tmd-ClpJxA ：被禁止？
        add("dmLSM9zM0ME");
        add("M7lc1UVf-VE");
        add("H6SShCF58-U");
        add("3tmd-ClpJxA");
    }};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);

        mPlayerView = findViewById(R.id.player_view);
        mPlayerView.setVideoId("H6SShCF58-U");
        mPlayerView.setVideoList(mVideoIds);
    }
}