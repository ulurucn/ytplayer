package vip.frendy.ytplayer;

import android.util.Log;
import android.webkit.JavascriptInterface;

import vip.frendy.ytplayer.interfaces.IYTJSListener;

import static vip.frendy.ytplayer.Contants.DEBUG;

/**
 * Created by frendy on 2017/11/16.
 */
public class YTJSInterface {
    String TAG = "YTJSInterface";

    private IYTJSListener listener;

    public YTJSInterface(IYTJSListener listener) {
        this.listener = listener;
    }

    @JavascriptInterface
    public void onYouTubeIframeAPIReady() {
        listener.onYouTubeIframeAPIReady();
    }

    @JavascriptInterface
    public void updateVideoDuration(float duration) {
        if(DEBUG) Log.d(TAG, "setVideoDuration = " + duration);
        listener.updateVideoDuration(duration);
    }

    @JavascriptInterface
    public void updateTotalVideoDuration(float duration) {
        if(DEBUG) Log.d(TAG, "updateTotalVideoDuration = " + duration);
        listener.updateTotalVideoDuration(duration);
    }

    @JavascriptInterface
    public void onReady() {
        listener.onReady();
    }

    @JavascriptInterface
    public void onVideoUnStarted() {
        listener.onVideoUnStarted();
    }

    @JavascriptInterface
    public void onVideoEnd() {
        listener.onVideoEnd();
    }

    @JavascriptInterface
    public void onVideoPlaying() {
        listener.onVideoPlaying();
    }

    @JavascriptInterface
    public void onVideoPaused() {
        listener.onVideoPaused();
    }

    @JavascriptInterface
    public void onVideoBuffering() {
        listener.onVideoBuffering();
    }

    @JavascriptInterface
    public void onVideoCued() {
        listener.onVideoCued();
    }

    @JavascriptInterface
    public void onVideoStateCheckResult(int state, float current, float total) {
        listener.onVideoStateCheckResult(state, current, total);
    }

    @JavascriptInterface
    public void onApiChange() {
        listener.onApiChange();
    }

    @JavascriptInterface
    public void onError(int error) {
        listener.onError(error);
    }

    @JavascriptInterface
    public void onPlaybackRateChange(double rate) {
        listener.onPlaybackRateChange(rate);
    }

    @JavascriptInterface
    public void onPlaybackQualityChange(int playbackQuality) {
        listener.onPlaybackQualityChange(playbackQuality);
    }
}
