package vip.frendy.ytplayer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


/**
 * Created by frendy on 2017/11/17.
 */

public class YTPlayerButtonView<T> extends YTPlayerView<T> {
    private static String TAG = "YTPlayerView";

    protected LinearLayout mButtonLayout;
    protected Button mLoad, mStop, mClear;


    public YTPlayerButtonView(Context context) {
        super(context);
    }

    public YTPlayerButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public YTPlayerButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 继承时可替换布局
    protected int getLayoutResId() {
        return R.layout.yt_player_button_view;
    }

    protected void init(Context context) {
        super.init(context);

        mButtonLayout = findViewById(R.id.buttonLayout);
        mLoad = findViewById(R.id.load);
        mLoad.setOnClickListener(this);
        mStop = findViewById(R.id.stop);
        mStop.setOnClickListener(this);
        mClear = findViewById(R.id.clear);
        mClear.setOnClickListener(this);
    }

    // 展开
    public void rollout() {
        super.rollout();
        mButtonLayout.setVisibility(VISIBLE);
    }

    // 收起
    public void rollup() {
        super.rollup();
        mButtonLayout.setVisibility(GONE);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        if(view.getId() == R.id.load && mVideoId != null) {
            mWebView.loadDefault(mVideoId);
        } else if(view.getId() == R.id.stop) {
            stopVideo();
        } else if(view.getId() == R.id.clear) {
            clearVideo();
        }
    }
}
