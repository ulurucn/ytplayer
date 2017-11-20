package vip.frendy.ytplayer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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

public class YTPlayerListView<T> extends YTPlayerView<T> implements SwipeItemClickListener {
    private static String TAG = "YTPlayerListView";

    private LinearLayout mContent;
    private UltraViewPager mPager;
    private YTPlayerPagerAdapter mAdapter;

    private Button mPlayNext, mCueNext;

    //播放列表
    protected ArrayList<T> mVideoList = new ArrayList<>();
    protected int mIndex = 0;

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

        mAdapter = new YTPlayerPagerAdapter<T>(context, this);
        mPager.setAdapter(mAdapter);
        mPager.setInfiniteRatio(100);

        mPager.initIndicator();
        mPager.getIndicator().setOrientation(UltraViewPager.Orientation.HORIZONTAL);

        mPlayNext = findViewById(R.id.play_next);
        mPlayNext.setOnClickListener(this);
        mCueNext = findViewById(R.id.cue_next);
        mCueNext.setOnClickListener(this);
    }

    public void setVideoList(ArrayList<T> list) {
        mVideoList.clear();
        mVideoList.addAll(list);
        mAdapter.initDataList(mVideoList);
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
        mIndex = position;
        mWebView.loadVideoById(getVideoId(position));
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        if(view.getId() == R.id.play_next && mVideoList.size() > 0) {
            mWebView.loadVideoById(getNextVideoId());
        } else if(view.getId() == R.id.cue_next && mVideoList.size() > 0) {
            mWebView.cueVideoById(getNextVideoId());
        }
    }

    protected String getNextVideoId() {
        T video = mVideoList.get((mIndex ++) % mVideoList.size());

        return getVideoId(video);
    }

    protected String getVideoId(int position) {
        T video = mVideoList.get(position);

        return getVideoId(video);
    }

    // 增
    public void addVideos(ArrayList<T> videos) {
        ArrayList<T> list = new ArrayList<>();
        list.addAll(mVideoList);
        list.addAll(videos);
        setVideoList(list);
    }

    // 删
    public void removeVideos(ArrayList<T> videos) {
        for(T video : videos) {
            for(T item : mVideoList) {
                if(getVideoId(video).equals(getVideoId(item))) {
                    mVideoList.remove(item);
                    break;
                }
            }
        }
    }

    // 改
    public void updateVideo(String video) {

    }

    // 查
    public ArrayList<T> queryVideos() {
        return new ArrayList<>();
    }
}
