package vip.frendy.ytdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import vip.frendy.ytdemo.R;
import vip.frendy.ytdemo.utils.AnimationExt;
import vip.frendy.ytdemo.utils.VibratorExt;

/**
 * Created by frendy on 2017/11/18.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<String> mDataList;

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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mDataList.get(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageButton mMore;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mMore = itemView.findViewById(R.id.more);
        }

        public void setData(String title) {
            this.tvTitle.setText(title);

            mMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMore.setVisibility(View.GONE);
                    AnimationExt.doAnimation(mMore, R.anim.anim_alpha);
                    VibratorExt.playShort(mMore.getContext());
                }
            });
        }
    }

}
