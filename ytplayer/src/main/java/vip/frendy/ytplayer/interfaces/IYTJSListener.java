package vip.frendy.ytplayer.interfaces;

/**
 * Created by frendy on 2017/11/16.
 */

public interface IYTJSListener {

    void onYouTubeIframeAPIReady();

    void updateVideoDuration(String duration);

    void updateTotalVideoDuration(String duration);

    void onVideoEnd();

    void onVideoPlaying();

    void onVideoPaused();

    void onVideoBuffering();

    void onVideoCued();
}
