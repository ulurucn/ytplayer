package vip.frendy.ytplayer.interfaces;

/**
 * Created by frendy on 2017/11/16.
 */

public interface IYTJSListener {

    void onYouTubeIframeAPIReady();

    void updateVideoDuration(float duration);

    void updateTotalVideoDuration(float duration);

    void onReady();

    void onVideoUnStarted();

    void onVideoEnd();

    void onVideoPlaying();

    void onVideoPaused();

    void onVideoBuffering();

    void onVideoCued();

    void onVideoStateCheckResult(int state);

    void onApiChange();

    void onError(int error);

    void onPlaybackRateChange(double rate);

    void onPlaybackQualityChange(int playbackQuality);
}
