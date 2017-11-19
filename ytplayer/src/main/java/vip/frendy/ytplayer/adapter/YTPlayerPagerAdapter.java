package vip.frendy.ytplayer.adapter;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import vip.frendy.ytplayer.R;

/**
 * Created by frendy on 2017/11/17.
 */

public class YTPlayerPagerAdapter extends PagerAdapter {

    public YTPlayerPagerAdapter() {

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
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.item_player_list, null);
        TextView textView = (TextView) linearLayout.findViewById(R.id.title);
        textView.setText(position + "");
        switch (position) {
            case 0:
                linearLayout.setBackgroundColor(Color.parseColor("#2196F3"));
                break;
            case 1:
                linearLayout.setBackgroundColor(Color.parseColor("#673AB7"));
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
}
