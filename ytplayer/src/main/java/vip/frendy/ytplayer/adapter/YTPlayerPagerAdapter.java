package vip.frendy.ytplayer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemStateChangedListener;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.Collections;

import vip.frendy.ytplayer.R;

/**
 * Created by frendy on 2017/11/17.
 */

public class YTPlayerPagerAdapter<T> extends PagerAdapter {

    private Context mContext;

    private SwipeMenuRecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.ItemDecoration mItemDecoration;

    private SwipeItemClickListener itemClickListener;

    private ListAdapter<T> mAdapter;
    private ArrayList<T> mDataList;

    public YTPlayerPagerAdapter(Context context, SwipeItemClickListener itemClickListener) {
        this.mContext = context;
        this.itemClickListener = itemClickListener;
    }

    public void initDataList(ArrayList<T> list) {
        mDataList = list;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout linearLayout;
        switch (position) {
            case 1:
                linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.item_player_list, null);

                mRecyclerView = (SwipeMenuRecyclerView) linearLayout.findViewById(R.id.recycler_view);

                mLayoutManager = createLayoutManager(container.getContext());
                mItemDecoration = createItemDecoration(container.getContext());

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.addItemDecoration(mItemDecoration);
                mRecyclerView.setHasFixedSize(true);

                mRecyclerView.setSwipeItemClickListener(itemClickListener);

                mRecyclerView.setOnItemMoveListener(itemMoveListener);// 监听拖拽和侧滑删除，更新UI和数据源。
                mRecyclerView.setOnItemStateChangedListener(mOnItemStateChangedListener); // 监听Item的手指状态，拖拽、侧滑、松开。

                mRecyclerView.setItemViewSwipeEnabled(false); // 滑动删除，默认关闭。
                mRecyclerView.setLongPressDragEnabled(true); // 长按拖拽，默认关闭。

                mAdapter = new ListAdapter<T>();
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged(mDataList);
                break;

            case 0:
            default:
                linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.item_player_desc, null);
                TextView textView = (TextView) linearLayout.findViewById(R.id.title);
                textView.setText("desc");
                linearLayout.setBackgroundColor(Color.parseColor("#2196F3"));
                break;
        }
        container.addView(linearLayout);
        return linearLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }

    private RecyclerView.LayoutManager createLayoutManager(Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setSmoothScrollbarEnabled(true);
        return manager;
    }

    private RecyclerView.ItemDecoration createItemDecoration(Context context) {
        return new DefaultItemDecoration(ContextCompat.getColor(context, R.color.line));
    }

    /**
     * Item的拖拽/侧滑删除时，手指状态发生变化监听。
     */
    private OnItemStateChangedListener mOnItemStateChangedListener = new OnItemStateChangedListener() {
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState == OnItemStateChangedListener.ACTION_STATE_DRAG) {
                Log.i("", "** 状态：拖拽");
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_SWIPE) {
                Log.i("", "** 状态：滑动删除");
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_IDLE) {
                Log.i("", "** 状态：手指松开");
            }
        }
    };

    private OnItemMoveListener itemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
            // 不同的ViewType不能拖拽换位置。
            if (srcHolder.getItemViewType() != targetHolder.getItemViewType()) return false;

            // 真实的Position：通过ViewHolder拿到的position都需要减掉HeadView的数量。
            int fromPosition = srcHolder.getAdapterPosition() - mRecyclerView.getHeaderItemCount();
            int toPosition = targetHolder.getAdapterPosition() - mRecyclerView.getHeaderItemCount();

            Collections.swap(mDataList, fromPosition, toPosition);
            mAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;// 返回true表示处理了并可以换位置，返回false表示你没有处理并不能换位置。
        }

        @Override
        public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
            int adapterPosition = srcHolder.getAdapterPosition();
            int position = adapterPosition - mRecyclerView.getHeaderItemCount();

            mDataList.remove(position);
            mAdapter.notifyItemRemoved(position);
            Toast.makeText(mContext.getApplicationContext(), "现在的第" + position + "条被删除。", Toast.LENGTH_SHORT).show();
        }
    };
}
