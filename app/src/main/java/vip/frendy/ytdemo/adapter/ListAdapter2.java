package vip.frendy.ytdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import vip.frendy.ytdemo.R;

/**
 * Created by frendy on 2017/11/18.
 */

public class ListAdapter2 extends RecyclerView.Adapter<ListAdapter2.ViewHolder> {
    private List<String> mDataList;
    private IMoreClickListener mMoreListener;

    public ListAdapter2(IMoreClickListener listener) {
        mMoreListener = listener;
    }

    public void notifyDataSetChanged(List<String> dataList) {
        this.mDataList = dataList;
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false), mMoreListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mDataList.get(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageButton mMore;
        private IMoreClickListener mMoreListener;

        public ViewHolder(View itemView, IMoreClickListener listener) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mMore = itemView.findViewById(R.id.more);
            mMoreListener = listener;
        }

        public void setData(String title) {
            this.tvTitle.setText(title);

            mMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMoreListener.onMoreClick(view, getPosition());
                }
            });
        }
    }


    public interface IMoreClickListener {
        void onMoreClick(View v, int position);
    }
}
