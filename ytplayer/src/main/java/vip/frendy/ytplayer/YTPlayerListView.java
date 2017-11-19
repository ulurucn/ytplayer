package vip.frendy.ytplayer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.tmall.ultraviewpager.UltraViewPager;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;

import java.util.ArrayList;

import vip.frendy.ytplayer.adapter.YTPlayerPagerAdapter;

/**
 * Created by frendy on 2017/11/19.
 */

public class YTPlayerListView extends YTPlayerView implements SwipeItemClickListener, YTPlayerPagerAdapter.UpdateDataListListener {
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

        mAdapter = new YTPlayerPagerAdapter(context, this, this);
        mPager.setAdapter(mAdapter);
        mPager.setInfiniteRatio(100);

        mPager.initIndicator();
        mPager.getIndicator().setOrientation(UltraViewPager.Orientation.HORIZONTAL);
    }

    @Override
    public void setVideoList(ArrayList<String> list) {
        super.setVideoList(list);
        mAdapter.initDataList(list);
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

    @Override
    public void onItemClick(View itemView, int position) {
        mWebView.loadVideoById(mVideoList.get(position));
    }

    @Override
    public void updateDataList(ArrayList<String> list) {
        mVideoList.clear();
        mVideoList.addAll(list);
    }
}
