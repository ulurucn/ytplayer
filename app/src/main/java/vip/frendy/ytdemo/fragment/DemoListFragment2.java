package vip.frendy.ytdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vip.frendy.ytdemo.adapter.ListAdapter2;
import vip.frendy.ytdemo.view.ActionMenu;
import vip.frendy.ytdemo.view.ActionMenuManager;

/**
 * Created by frendy on 2017/11/18.
 */

public class DemoListFragment2 extends RefreshListFragment implements ActionMenu.OnMenuItemClickListener {

    protected ListAdapter2 mAdapter;
    protected ArrayList<String> mDataList = new ArrayList<>();

    protected ArrayList<String> createDataList(int start) {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = start; i < start + 20; i++) {
            strings.add("第" + i + "个Item");
        }
        return strings;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDataList.addAll(createDataList(0));
        mAdapter = new ListAdapter2(new ListAdapter2.IMoreClickListener() {
            @Override
            public void onMoreClick(View v, int position) {
                ActionMenuManager.getInstance().toggleActionMenuFromView(v, position, DemoListFragment2.this);
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged(mDataList);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                ActionMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    protected SwipeItemClickListener getItemClickListener() {
        return new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Toast.makeText(getContext().getApplicationContext(), "第" + position + "个", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected OnItemMoveListener getItemMoveListener() {
        // 监听拖拽和侧滑删除，更新UI和数据源。
        return new OnItemMoveListener() {
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
                Toast.makeText(getContext().getApplicationContext(), "现在的第" + position + "条被删除。", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected OnRefreshListener getRefreshListener() {
        return new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        };
    }

    @Override
    protected OnLoadmoreListener getLoadMoreListener() {
        return new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> strings = createDataList(mAdapter.getItemCount());
                        mDataList.addAll(strings);
                        // notifyItemRangeInserted()或者notifyDataSetChanged().
                        mAdapter.notifyItemRangeInserted(mDataList.size() - strings.size(), strings.size());

                        mRefreshLayout.finishLoadmore();
                    }
                }, 1000);
            }
        };
    }

    @Override
    public void onDelClicked(int feedItem) {
        ActionMenuManager.getInstance().hideActionMenu();
    }
}
