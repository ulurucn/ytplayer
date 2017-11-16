package vip.frendy.ytdemo;

import android.util.Log;
import android.webkit.JavascriptInterface;

import vip.frendy.ytdemo.interfaces.IYTJSListener;

/**
 * Created by frendy on 2017/11/16.
 */
class YTJSInterface {
    String TAG = "YTJSInterface";

    private IYTJSListener listener;

    public YTJSInterface(IYTJSListener listener) {
        this.listener = listener;
    }

    @JavascriptInterface
    public void setVideoDuration(String duration) {
        Log.d(TAG, "setVideoDuration = " + duration);
        listener.setVideoDuration(duration);
    }

    @JavascriptInterface
    public void setTotalVideoDuration(String duration) {
        Log.d(TAG, "setTotalVideoDuration = " + duration);
        listener.setTotalVideoDuration(duration);
    }

    @JavascriptInterface
    public void videoEnd() {
        listener.videoEnd();
    }
}
