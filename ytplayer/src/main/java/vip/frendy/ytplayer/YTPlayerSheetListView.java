package vip.frendy.ytplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;

import java.util.ArrayList;

import vip.frendy.ytplayer.adapter.SheetListAdapter;
import vip.frendy.ytplayer.extension.PraseHelper;

/**
 * Created by frendy on 2017/11/20.
 */

public class YTPlayerSheetListView<T> extends YTPlayerView<T> implements SheetListAdapter.IItemClickListener<T> {
    private static String TAG = "YTPlayerSheetListView";

    private LinearLayout mContent;
    protected BottomSheetDialog mBottomSheetDialog;
    protected RecyclerView mSheetList;

    private ImageButton mPlayNext, mPlayPrev;
    private Button mBtnList;

    private SheetListAdapter.IItemClickListener<T> mSheetItemClickListener;

    //播放列表
    protected ArrayList<T> mVideoList = new ArrayList<>();
    protected int mIndex = 0;

    private PraseHelper<T> mPraseHelper = new PraseHelper<>();


    public YTPlayerSheetListView(Context context) {
        super(context);
    }

    public YTPlayerSheetListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public YTPlayerSheetListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.yt_player_sheet_list_view;
    }

    protected int getLayoutDialogSheetListResId() {
        return R.layout.dialog_sheet_list;
    }

    @Override
    protected void init(Context context) {
        super.init(context);

        mContent = findViewById(R.id.content);

        mPlayNext = findViewById(R.id.play_next);
        mPlayNext.setOnClickListener(this);
        mPlayPrev = findViewById(R.id.play_prev);
        mPlayPrev.setOnClickListener(this);
        mBtnList = findViewById(R.id.sheet_list);
        mBtnList.setOnClickListener(this);
    }

    public void setVideoList(ArrayList<T> list) {
        mVideoList.clear();
        mVideoList.addAll(list);

        createBottomSheetDialog(mVideoList);
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

        if(view.getId() == R.id.play_next && mVideoList.size() > 0) {
            if(isVideoPlaying()) {
                mWebView.loadVideoById(getNextVideoId());
            } else {
                mWebView.cueVideoById(getNextVideoId());
            }
        } else if(view.getId() == R.id.play_prev && mVideoList.size() > 0) {
            if(isVideoPlaying()) {
                mWebView.loadVideoById(getPrevVideoId());
            } else {
                mWebView.cueVideoById(getPrevVideoId());
            }
        } else if(view.getId() == R.id.sheet_list) {
            if (mBottomSheetDialog.isShowing()) {
                mBottomSheetDialog.dismiss();
            } else {
                mBottomSheetDialog.show();
            }
        }
    }

    protected String getNextVideoId() {
        T video = mVideoList.get((++ mIndex) % mVideoList.size());

        return getVideoId(video);
    }

    protected String getPrevVideoId() {
        mIndex = mIndex + mVideoList.size();
        T video = mVideoList.get((-- mIndex) % mVideoList.size());

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

    /**
     * 列表
     */
    private void createBottomSheetDialog(ArrayList<T> list) {
        mBottomSheetDialog = new BottomSheetDialog(getContext());
        View view = LayoutInflater.from(getContext()).inflate(getLayoutDialogSheetListResId(), null, false);
        mBottomSheetDialog.setContentView(view);

        mSheetList = (RecyclerView) view.findViewById(R.id.recyclerView);
        mSheetList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        mSheetList.setLayoutManager(linearLayoutManager);
        SheetListAdapter adapter = new SheetListAdapter<T>(list, this);
        mSheetList.setAdapter(adapter);

        setBehaviorCallback();
    }

    private void setBehaviorCallback() {
        View view = mBottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(view);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    mBottomSheetDialog.dismiss();
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }

    @Override
    public void onItemClick(View view, int position, T data) {
        mIndex = position;
        mBottomSheetDialog.dismiss();
        mWebView.loadVideoById(getVideoId(position));

        if(mSheetItemClickListener != null) {
            mSheetItemClickListener.onItemClick(view, position, data);
        }
    }

    public void setSheetItemClickListener(SheetListAdapter.IItemClickListener<T> listener) {
        mSheetItemClickListener = listener;
    }
}
