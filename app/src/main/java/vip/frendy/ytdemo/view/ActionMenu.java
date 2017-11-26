package vip.frendy.ytdemo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import vip.frendy.ytdemo.R;
import vip.frendy.ytdemo.utils.Utils;

public class ActionMenu extends LinearLayout {
    private static final int CONTEXT_MENU_WIDTH = Utils.dpToPx(120);

    private int item = -1;

    private OnMenuItemClickListener mItemClickListener;

    public ActionMenu(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.list_action_menu, this, true);
        setBackgroundResource(R.mipmap.bg_shadow);
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(CONTEXT_MENU_WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT));

        findViewById(R.id.btnDelete).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mItemClickListener != null)
                    mItemClickListener.onDelClicked(item);
            }
        });
    }

    public void bindToItem(int item) {
        this.item = item;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void dismiss() {
        ((ViewGroup) getParent()).removeView(ActionMenu.this);
    }


    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface OnMenuItemClickListener {
        void onDelClicked(int feedItem);
    }
}