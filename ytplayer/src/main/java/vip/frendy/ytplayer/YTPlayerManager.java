package vip.frendy.ytplayer;

import android.content.Context;

import vip.frendy.ytplayer.interfaces.IYTJSListener;

/**
 * Created by frendy on 2017/11/21.
 */

public class YTPlayerManager implements IYTJSListener {

    private static YTPlayerManager mPlayerManager;

    public static YTPlayerManager getInstance() {
        if(mPlayerManager == null) {
            mPlayerManager = new YTPlayerManager();
        }
        return mPlayerManager;
    }


    private YTWebView mWebView;
    private IYTJSListener mClientListener;

    public void init(Context context) {
        mWebView = new YTWebView(context);
        mWebView.init(this);
    }

    public YTWebView getWebView() {
        return mWebView;
    }

    public void attachListener(IYTJSListener listener) {
        mClientListener = listener;
    }

    public void detachListener() {
        mClientListener = null;
    }

    @Override
    public void onYouTubeIframeAPIReady() {
        if(mClientListener != null)
            mClientListener.onYouTubeIframeAPIReady();
    }

    @Override
    public void updateVideoDuration(String duration) {
        if(mClientListener != null)
            mClientListener.updateVideoDuration(duration);
    }

    @Override
    public void updateTotalVideoDuration(String duration) {
        if(mClientListener != null)
            mClientListener.updateTotalVideoDuration(duration);
    }

    @Override
    public void onVideoEnd() {
        if(mClientListener != null)
            mClientListener.onVideoEnd();
    }

    @Override
    public void onVideoPlaying() {
        if(mClientListener != null)
            mClientListener.onVideoPlaying();
    }

    @Override
    public void onVideoPaused() {
        if(mClientListener != null)
            mClientListener.onVideoPaused();
    }

    @Override
    public void onVideoBuffering() {
        if(mClientListener != null)
            mClientListener.onVideoBuffering();
    }

    @Override
    public void onVideoCued() {
        mClientListener.onVideoCued();
    }
}
