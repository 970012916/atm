package com.coderpig.drysisters.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.coderpig.drysisters.DryConstant;
import com.coderpig.drysisters.R;
import com.coderpig.drysisters.ui.fragment.LittleSisterFragment;
import com.coderpig.drysisters.ui.fragment.NewsFragment;
import com.coderpig.drysisters.ui.fragment.SubwayFragment;
import com.coderpig.drysisters.ui.fragment.ToolsFragment;
import com.coderpig.drysisters.ui.fragment.WeatherFragment;
import com.coderpig.drysisters.utils.PackageUtils;
import com.coderpig.drysisters.utils.ResUtils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private NavigationView navigationView;
    private TextView navigationView_textView;
    private ConstraintLayout constraintLayout_main_content;
    private FragmentManager mFgManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFgManager = getSupportFragmentManager();
        initView();
        initData();
    }
    /**
     * 初始化页面
     */
    private void initView() {
        //最外层根目录
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        /**
         * 其实，NavigationView是一个RecyclerView（在23.1.0版本之前是ListView），header布局通常是0号元素。在Support Library v23.1.1版本中，可以使用如下方法很方便地获取到header中的view：
         View headerLayout = navigationView.getHeaderView(0); // 0-index header
         而在23.1.0版本中，就需要通过这种方法：
         View headerLayout =
         navigationView.inflateHeaderView(R.layout.navigation_header);
         panel = headerLayout.findViewById(R.id.viewId);
         */
        navigationView_textView = navigationView.getHeaderView(0).findViewById(R.id.tv_nav_title);
        drawer_layout = findViewById(R.id.drawer_layout);
        constraintLayout_main_content = findViewById(R.id.cly_main_content);
        //替换actionBar
        setSupportActionBar(toolbar);
        //保持图标原有的颜色
        navigationView.setItemIconTintList(null);
        //导航栏目录
        navigationView.setNavigationItemSelectedListener(this);

        //三根横线，打开drawerLayout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close);
       // drawer_layout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initData() {
        /**
         * Remove an existing fragment.  If it was added to a container,
         */
        mFgManager.beginTransaction().replace(R.id.cly_main_content,
                LittleSisterFragment.newInstance(), DryConstant.FG_LITTLE_SISTER).commit();
        toolbar.setTitle(ResUtils.getString(R.string.menu_see_little_sister));
        String version = PackageUtils.packageName();
        if(version != null) {
            String msg = String.format(ResUtils.getString(R.string.menu_drysister_version), version);
            navigationView_textView.setText(msg);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_see_little_sister:
                if (mFgManager.findFragmentByTag(DryConstant.FG_LITTLE_SISTER) == null) {
                    mFgManager.beginTransaction().replace(R.id.cly_main_content,
                            LittleSisterFragment.newInstance(), DryConstant.FG_LITTLE_SISTER).commit();
                    toolbar.setTitle(ResUtils.getString(R.string.menu_see_little_sister));
                }
                break;
            case R.id.nav_see_news:
                if (mFgManager.findFragmentByTag(DryConstant.FG_NEWS) == null) {
                    mFgManager.beginTransaction().replace(R.id.cly_main_content,
                            NewsFragment.newInstance(), DryConstant.FG_NEWS).commit();
                    toolbar.setTitle(ResUtils.getString(R.string.menu_see_news));
                }
                break;
            case R.id.nav_use_check_weather:
                if (mFgManager.findFragmentByTag(DryConstant.FG_WEATHER) == null) {
                    mFgManager.beginTransaction().replace(R.id.cly_main_content,
                            WeatherFragment.newInstance(), DryConstant.FG_WEATHER).commit();
                    toolbar.setTitle(ResUtils.getString(R.string.menu_use_check_weather));
                }
                break;
            case R.id.nav_use_check_subway:
                if (mFgManager.findFragmentByTag(DryConstant.FG_SUBWAY) == null) {
                    mFgManager.beginTransaction().replace(R.id.cly_main_content,
                            SubwayFragment.newInstance(), DryConstant.FG_SUBWAY).commit();
                    toolbar.setTitle(ResUtils.getString(R.string.menu_use_check_subway));
                }
                break;
            case R.id.nav_use_tools:
                if (mFgManager.findFragmentByTag(DryConstant.FG_TOOLS) == null) {
                    mFgManager.beginTransaction().replace(R.id.cly_main_content,
                            ToolsFragment.newInstance(), DryConstant.FG_TOOLS).commit();
                    toolbar.setTitle(ResUtils.getString(R.string.menu_use_tools));
                }
                break;
            case R.id.nav_else_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.nav_else_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
        }
        /**
         * 左边菜单的设定使用android:layout_gravity="start" 设置。
         */
        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 如果使用onBackgress(),假如你的Activity有添加管理frgament回退的话,调用该方法是不会退出Activity的.直到Fragment回退栈清空,才会退出Activity.
     * 如果使用finish(),那么就会直接退出Activity
     */
    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
