package vip.frendy.ytplayer;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

import vip.frendy.ytplayer.adapter.SheetListAdapter;
import vip.frendy.ytplayer.utils.DensityUtil;

/**
 * Created by frendy on 2017/11/20.
 */

public class YTPlayerSheetListView<T> extends YTPlayerListView<T> implements SheetListAdapter.IItemClickListener<T> {
    private static String TAG = "YTPlayerSheetListView";

    protected Dialog mBottomSheetDialog;
    protected RecyclerView mSheetList;
    protected View mSheetContentView;

    protected ImageButton mBtnList;

    protected SheetListAdapter.IItemClickListener<T> mSheetItemClickListener;


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

        mBtnList = findViewById(R.id.sheet_list);
        mBtnList.setOnClickListener(this);
    }

    public void setVideoList(ArrayList<T> list) {
        super.setVideoList(list);

        createBottomSheetDialog(mVideoList);
    }

    @Override
    public void rollout() {
        super.rollout();
    }

    @Override
    public void rollup() {
        super.rollup();
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

        if(view.getId() == R.id.sheet_list) {
            if(mBottomSheetDialog == null) return;
            if (mBottomSheetDialog.isShowing()) {
                mBottomSheetDialog.dismiss();
            } else {
                mBottomSheetDialog.show();
            }
        }
    }

    /**
     * 列表
     */
    protected void createBottomSheetDialog(ArrayList<T> list) {
        mBottomSheetDialog = new Dialog(getContext(), R.style.BottomDialog);
        mSheetContentView = LayoutInflater.from(getContext()).inflate(getLayoutDialogSheetListResId(), null, false);
        mBottomSheetDialog.setContentView(mSheetContentView);

        setBottomDialogBehavior(mBottomSheetDialog, mSheetContentView);

        mSheetList = (RecyclerView) mSheetContentView.findViewById(R.id.recyclerView);
        mSheetList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        mSheetList.setLayoutManager(linearLayoutManager);
        SheetListAdapter adapter = createSheetListAdapter(list);
        mSheetList.setAdapter(adapter);
    }

    protected void setBottomDialogBehavior(Dialog dialog, View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        layoutParams.height = DensityUtil.dp2px(getContext(), 360);
        view.setLayoutParams(layoutParams);

        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
    }

    protected SheetListAdapter<T> createSheetListAdapter(ArrayList<T> list) {
        return new SheetListAdapter<T>(list, this);
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
