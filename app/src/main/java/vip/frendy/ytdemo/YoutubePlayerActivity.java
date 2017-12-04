package vip.frendy.ytdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;

import vip.frendy.ytplayer.YTPlayerListPagerView;
import vip.frendy.ytplayer.YTPlayerListView;
import vip.frendy.ytplayer.YTPlayerManager;
import vip.frendy.ytplayer.YTPlayerSheetListView;

/**
 * Created by frendy on 2017/11/17.
 */

public class YoutubePlayerActivity extends Activity {

    private YTPlayerSheetListView<String> mPlayerView;

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

    public static int flag = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);

        mPlayerView = findViewById(R.id.player_view);

        YTPlayerManager.getInstance().attachListener(mPlayerView);

        mPlayerView.attachWebView(YTPlayerManager.getInstance().getWebView());
        mPlayerView.setProceedTouchEvent(true);
        mPlayerView.setVideoList(mVideoIds);
        mPlayerView.playVideoListAt(4);
        mPlayerView.setPlayListState(YTPlayerListView.PlayListState.SINGLE_LOOP);

        findViewById(R.id.roll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPlayerView.isRollup()) {
                    mPlayerView.rollout();
                } else {
                    mPlayerView.rollup();
                }
            }
        });

        findViewById(R.id.size).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int w = px2dp(YoutubePlayerActivity.this, mPlayerView.getMeasuredWidth());
                int h = px2dp(YoutubePlayerActivity.this, mPlayerView.getMeasuredHeight());

                if((flag % 2) == 0) {
                    flag++;
                    mPlayerView.setSize(160, 90);
                } else {
                    flag++;
                    mPlayerView.setSize(w, h);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayerView.stopVideo();
        mPlayerView.detachWebView();
        YTPlayerManager.getInstance().detachListener();
    }

    public int px2dp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }
}
