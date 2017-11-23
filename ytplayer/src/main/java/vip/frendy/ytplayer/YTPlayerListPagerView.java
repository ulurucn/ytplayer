package vip.frendy.ytplayer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tmall.ultraviewpager.UltraViewPager;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;

import java.util.ArrayList;

import vip.frendy.ytplayer.adapter.YTPlayerPagerAdapter;

/**
 * Created by frendy on 2017/11/19.
 */

public class YTPlayerListPagerView<T> extends YTPlayerListView<T> implements SwipeItemClickListener {
    private static String TAG = "YTPlayerListView";

    private LinearLayout mContentInfo;
    private UltraViewPager mPager;
    private YTPlayerPagerAdapter<T> mAdapter;

    public YTPlayerListPagerView(Context context) {
        super(context);
    }

    public YTPlayerListPagerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public YTPlayerListPagerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.yt_player_list_pager_view;
    }

    @Override
    protected void init(Context context) {
        super.init(context);

        mContentInfo = findViewById(R.id.content);

        mPager = findViewById(R.id.pager);
        mPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);

        mAdapter = new YTPlayerPagerAdapter<T>(context, this);
        mPager.setAdapter(mAdapter);
        mPager.setInfiniteRatio(100);

        mPager.initIndicator();
        mPager.getIndicator().setOrientation(UltraViewPager.Orientation.HORIZONTAL);
    }

    public void setVideoList(ArrayList<T> list) {
        super.setVideoList(list);
        mAdapter.initDataList(mVideoList);
    }

    @Override
    public void rollout() {
        super.rollout();
        mContentInfo.setVisibility(VISIBLE);
    }

    @Override
    public void rollup() {
        super.rollup();
        mContentInfo.setVisibility(GONE);
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
    public void onItemClick(View itemView, int position) {
        mIndex = position;
        mWebView.loadVideoById(getVideoId(position));
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }
}
