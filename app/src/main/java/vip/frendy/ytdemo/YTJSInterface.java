package vip.frendy.ytdemo;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by frendy on 2017/11/16.
 */
class YTJSInterface {

    private YoutubeActivity youtubeActivity;

    public YTJSInterface(YoutubeActivity youtubeActivity) {
        this.youtubeActivity = youtubeActivity;
    }

    @JavascriptInterface
    public void setVideoDuration(String duration) {
        Log.d(youtubeActivity.TAG, "setVideoDuration = " + duration);
        try {
            youtubeActivity.changeSlider(Float.parseFloat(duration));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void setTotalVideoDuration(String duration) {
        Log.d(youtubeActivity.TAG, "setTotalVideoDuration = " + duration);
        try {
            youtubeActivity.totalVideoDuration = Float.parseFloat(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void videoEnd() {
        youtubeActivity.modifySlider("ENDED");
    }
}
