package vip.frendy.ytdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import vip.frendy.tablayout.CommonTabLayout;
import vip.frendy.tablayout.entity.TabEntity;
import vip.frendy.tablayout.listener.CustomTabEntity;
import vip.frendy.ytdemo.fragment.DemoListFragment;
import vip.frendy.ytdemo.fragment.DemoListFragment2;

/**
 * Created by frendy on 2017/11/18.
 */

public class DemoListActivity extends AppCompatActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private CommonTabLayout mTabs;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<CustomTabEntity>() {{
        add(new TabEntity("DRAG", R.mipmap.ic_launcher_round, R.mipmap.ic_launcher));
        add(new TabEntity("REFRESH", R.mipmap.ic_launcher_round, R.mipmap.ic_launcher));
    }};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list);

        mFragments.add(Fragment.instantiate(this, DemoListFragment.class.getName()));
        mFragments.add(Fragment.instantiate(this, DemoListFragment2.class.getName()));

        mTabs = findViewById(R.id.tabs);
        mTabs.setTabData(mTabEntities, this, R.id.content, mFragments);
    }
}
