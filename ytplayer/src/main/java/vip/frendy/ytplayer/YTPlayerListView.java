package vip.frendy.ytplayer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.tmall.ultraviewpager.UltraViewPager;

import vip.frendy.ytplayer.adapter.YTPlayerPagerAdapter;

/**
 * Created by frendy on 2017/11/19.
 */

public class YTPlayerListView extends YTPlayerView {
    private static String TAG = "YTPlayerListView";

    private LinearLayout mContent;
    private UltraViewPager mPager;
    private YTPlayerPagerAdapter mAdapter;

    public YTPlayerListView(Context context) {
        super(context);
    }

    public YTPlayerListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public YTPlayerListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.yt_player_list_view;
    }

    @Override
    protected void init(Context context) {
        super.init(context);

        mContent = findViewById(R.id.content);

        mPager = findViewById(R.id.pager);
        mPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        mAdapter = new YTPlayerPagerAdapter();
        mPager.setAdapter(mAdapter);
        mPager.setInfiniteRatio(100);

        mPager.initIndicator();
        mPager.getIndicator().setOrientation(UltraViewPager.Orientation.HORIZONTAL);
    }

    @Override
    public void rollout() {
        super.rollout();
        mContent.setVisibility(VISIBLE);
    }

    @Override
    public void rollup() {
        super.rollup();
        mContent.setVisibility(GONE);
    }
}
