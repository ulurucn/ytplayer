package vip.frendy.ytplayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vip.frendy.ytplayer.R;
import vip.frendy.ytplayer.extension.PraseHelper;


/**
 * Created by frendy on 2017/11/20.
 */

public class SheetListAdapter<T> extends Adapter<SheetListAdapter.ViewHolder<T>> {

    protected ArrayList<T> mList;
    protected IItemClickListener<T> mListener;

    public SheetListAdapter(ArrayList<T> list, IItemClickListener<T> listener) {
        mList = list;
        mListener = listener;
    }

    protected int getLayoutItemResId() {
        return R.layout.item_sheet_list;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder<T> viewHolder, int position) {
        viewHolder.setData(mList.get(position));
    }

    @Override
    public ViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(getLayoutItemResId(), parent, false);
        return new ViewHolder<T>(view, mListener);
    }

    public static class ViewHolder<T> extends RecyclerView.ViewHolder {
        private IItemClickListener<T> mListener;
        private TextView mTitle;
        private PraseHelper<T> mPraseHelper = new PraseHelper<>();

        public ViewHolder(View itemView, final IItemClickListener<T> listener) {
            super(itemView);
            mListener = listener;
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }

        public void setData(final T item) {
            mTitle.setText(mPraseHelper.getTitle(item));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClick(view, getPosition(), item);
                }
            });
        }
    }

    public interface IItemClickListener<T> {
        void onItemClick(View view, int position, T data);
    }
}
