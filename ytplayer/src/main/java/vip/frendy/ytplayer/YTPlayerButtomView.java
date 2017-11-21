package vip.frendy.ytplayer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by frendy on 2017/11/21.
 */

public class YTPlayerButtomView<T> extends YTPlayerView<T> {
    private static String TAG = "YTPlayerView";

    private TextView mTitle;

    public YTPlayerButtomView(Context context) {
        super(context);
    }

    public YTPlayerButtomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public YTPlayerButtomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 继承时可替换布局
    protected int getLayoutResId() {
        return R.layout.yt_player_buttom_view;
    }

    protected void init(Context context) {
        super.init(context);

        mTitle = findViewById(R.id.title);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    // 展开
    public void rollout() {
        super.rollout();
    }

    // 收起
    public void rollup() {
        super.rollup();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }
}
