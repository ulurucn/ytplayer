package vip.frendy.ytplayer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by frendy on 2017/11/21.
 */

public class YTPlayerBottomView<T> extends YTPlayerView<T> {
    private static String TAG = "YTPlayerBottomView";

    private TextView mTitle;

    public YTPlayerBottomView(Context context) {
        super(context);
    }

    public YTPlayerBottomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public YTPlayerBottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.yt_player_buttom_view;
    }

    @Override
    protected void init(Context context) {
        super.init(context);

        mTitle = findViewById(R.id.title);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(isProceedTouchEvent) {
            super.onTouchEvent(event);
            return false;
        }
        return super.onTouchEvent(event);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
    }
}
