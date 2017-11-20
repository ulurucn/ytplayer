package vip.frendy.ytplayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vip.frendy.ytplayer.R;
import vip.frendy.ytplayer.extension.PraseHelper;

/**
 * Created by frendy on 2017/11/18.
 */

public class ListAdapter<T> extends RecyclerView.Adapter<ListAdapter.ViewHolder<T>> {
    private ArrayList<T> mDataList;

    public void notifyDataSetChanged(ArrayList<T> dataList) {
        this.mDataList = dataList;
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public ViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder<T>(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder<T> holder, int position) {
        holder.bind(mDataList.get(position));
    }

    static class ViewHolder<T> extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private PraseHelper<T> mPraseHelper = new PraseHelper<>();

        ViewHolder(final View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }

        private void bind(T item) {
            mTitle.setText(mPraseHelper.getTitle(item));
        }
    }

}
