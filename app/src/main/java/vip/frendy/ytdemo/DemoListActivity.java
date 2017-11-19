package vip.frendy.ytdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vip.frendy.tablayout.CommonTabLayout;
import vip.frendy.tablayout.entity.TabEntity;
import vip.frendy.tablayout.listener.CustomTabEntity;
import vip.frendy.ytdemo.fragment.DemoListFragment;
import vip.frendy.ytdemo.fragment.DemoListFragment2;
import vip.frendy.ytdemo.view.AppBarStateChangeListener;

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

    private AppBarLayout mAppBar;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFragments.add(Fragment.instantiate(this, DemoListFragment.class.getName()));
        mFragments.add(Fragment.instantiate(this, DemoListFragment2.class.getName()));

        mTabs = findViewById(R.id.tabs);
        mTabs.setTabData(mTabEntities, this, R.id.content, mFragments);

        mBottomSheetBehavior = BottomSheetBehavior.from(mTabs);
        mBottomSheetBehavior.setPeekHeight(0);

        mAppBar = findViewById(R.id.appBar);
        mAppBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if(state == State.EXPANDED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if(state == State.COLLAPSED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        final MenuItem item = menu.findItem(R.id.action_edit);
        item.getActionView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(item);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if(item.getItemId() == R.id.action_test0) {
            Toast.makeText(getApplicationContext(), "TEST 0", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.action_edit) {
            Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "SETTING", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
