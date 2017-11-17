package vip.frendy.ytplayer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import vip.frendy.ytplayer.extension.HandlerExt;
import vip.frendy.ytplayer.interfaces.IYTJSListener;

import static vip.frendy.ytplayer.Contants.DEBUG;

/**
 * Created by frendy on 2017/11/17.
 */

public class YTPlayerView extends LinearLayout implements IYTJSListener, View.OnClickListener {
    private static String TAG = "YTPlayerView";

    private Button mLoad, mPlay, mPause, mStop, mClear, mPlayNext, mCueNext;

    private final static int MAX = 1000;
    private SeekBar mSeekBar;
    private float totalVideoDuration;

    private YTWebView mWebView;
    private String mVideoId;

    public YTPlayerView(Context context) {
        super(context);
        init(context);
    }

    public YTPlayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public YTPlayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.yt_player_view, this);

        mLoad = findViewById(R.id.load);
        mLoad.setOnClickListener(this);
        mPlay = findViewById(R.id.play);
        mPlay.setOnClickListener(this);
        mPause = findViewById(R.id.pause);
        mPause.setOnClickListener(this);
        mStop = findViewById(R.id.stop);
        mStop.setOnClickListener(this);
        mClear = findViewById(R.id.clear);
        mClear.setOnClickListener(this);
        mPlayNext = findViewById(R.id.play_next);
        mPlayNext.setOnClickListener(this);
        mCueNext = findViewById(R.id.cue_next);
        mCueNext.setOnClickListener(this);

        mSeekBar = findViewById(R.id.seek_bar);
        mSeekBar.setMax(MAX);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                double secs = (progress * totalVideoDuration) / 1000;
                secs = Math.ceil(secs);
                mWebView.loadUrl("javascript:seekVideo('"+ secs +"')");

                if(DEBUG) Log.d(TAG, "onStopTrackingTouch :: progress = " + progress +  "-- secs = " + secs);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(DEBUG) Log.d(TAG, "onStartTrackingTouch :: progress = " + seekBar.getProgress() );
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
        });

        mWebView = findViewById(R.id.web_view);
        mWebView.init(this);
    }

    public void setVideoId(String id) {
        mVideoId = id;
    }


    @Override
    public void onYouTubeIframeAPIReady() {

    }

    @Override
    public void updateVideoDuration(String duration) {
        try {
            changeSeekBar(Float.parseFloat(duration));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTotalVideoDuration(String duration) {
        try {
            totalVideoDuration = Float.parseFloat(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onVideoEnd() {
        setSeekBar("ENDED");
    }


    private void changeSeekBar(float time){
        float progress =  (time/totalVideoDuration) * 1000;
        final double d = Math.ceil(progress);

        if(DEBUG) Log.d(TAG, "changeSlider progress = " + d);

        HandlerExt.postToUI(new Runnable() {
            @Override
            public void run() {
                mSeekBar.setProgress((int)d);
            }
        });
    }

    private void setSeekBar(final String flag){
        HandlerExt.postToUI(new Runnable() {
            @Override
            public void run() {
                if(flag.equals("ENDED"))
                    mSeekBar.setProgress(MAX);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.load && mVideoId != null) {
            mWebView.loadDefault(mVideoId);
        }

    }
}
