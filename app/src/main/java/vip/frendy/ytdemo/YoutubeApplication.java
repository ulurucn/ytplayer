package vip.frendy.ytdemo;

import android.app.Application;

import vip.frendy.ytplayer.YTPlayerManager;

/**
 * Created by frendy on 2017/11/21.
 */

public class YoutubeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        YTPlayerManager.getInstance().init(this);
    }
}
