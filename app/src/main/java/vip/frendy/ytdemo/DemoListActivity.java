package vip.frendy.ytdemo;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

public class DemoListActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private CommonTabLayout mTabs;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<CustomTabEntity>() {{
        add(new TabEntity("DRAG", R.mipmap.ic_launcher_round, R.mipmap.ic_launcher));
        add(new TabEntity("REFRESH", R.mipmap.ic_launcher_round, R.mipmap.ic_launcher));
    }};

    private AppBarLayout mAppBar;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        Drawable drawable = AppCompatResources.getDrawable(this, R.drawable.ic_menu_white_24dp);
        mToolbar.setNavigationIcon(drawable);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        mDrawerLayout.findViewById(R.id.menu_0).setOnClickListener(this);
        mDrawerLayout.findViewById(R.id.menu_1).setOnClickListener(this);
        mDrawerLayout.findViewById(R.id.menu_2).setOnClickListener(this);

        drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int drawerLockMode = mDrawerLayout.getDrawerLockMode(GravityCompat.START);
                if (drawerLockMode == DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
                    finish();
                }
            }
        });

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
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            drawerToggle.setDrawerIndicatorEnabled(false);
            VectorDrawableCompat a = VectorDrawableCompat.create(getResources(), R.drawable.ic_arrow_back_white_24dp, getTheme());
            a.setTint(Color.RED); //设置单一的颜色
            a.setTintList(ColorStateList.valueOf(Color.RED));//设置状态性的，比如点击一个颜色，未点击一个颜色
            mToolbar.setNavigationIcon(a);
        } else if(item.getItemId() == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "SETTING", Toast.LENGTH_SHORT).show();
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
            drawerToggle.setDrawerIndicatorEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        }
        return true;
    }

    @Override
    public void onClick(View view) {

    }
}
