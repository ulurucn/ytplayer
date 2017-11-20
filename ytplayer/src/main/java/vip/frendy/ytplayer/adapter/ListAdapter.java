package vip.frendy.ytplayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vip.frendy.ytplayer.R;
import vip.frendy.ytplayer.model.PlaylistItems;

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
        TextView tvTitle;

        ViewHolder(final View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }

        private void bind(T item) {
            tvTitle.setText(getTitle(item));
        }

        private String getTitle(T item) {
            if(item instanceof String) {
                return (String) item;
            } else if(item instanceof PlaylistItems) {
                return ((PlaylistItems) item).getSnippet().getTitle();
            } else {
                return "";
            }
        }
    }

}
