package com.coderpig.drysisters.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.coderpig.drysisters.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private NavigationView nav_view;
    private TextView tv_nav_title;
    private ConstraintLayout cly_main_content;
    private FragmentManager mFgManager;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
        mFgManager = getSupportFragmentManager();
        initView();
        initData();

    }


    /**
     * 初始化页面
     */
    private void initView(){
        //最外层根目录
        drawer_layout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolBar);
        //导航栏目录
        nav_view = findViewById(R.id.nav_view);


        /**
         * 其实，NavigationView是一个RecyclerView（在23.1.0版本之前是ListView），header布局通常是0号元素。在Support Library v23.1.1版本中，可以使用如下方法很方便地获取到header中的view：

         View headerLayout = navigationView.getHeaderView(0); // 0-index header

         而在23.1.0版本中，就需要通过这种方法：
         View headerLayout =
         navigationView.inflateHeaderView(R.layout.navigation_header);
         panel = headerLayout.findViewById(R.id.viewId);

         */
        tv_nav_title = nav_view.getHeaderView(0).findViewById(R.id.tv_nav_title);
        drawer_layout = findViewById(R.id.drawer_layout);
        //TODO
        cly_main_content = findViewById(R.id.cly_main_content);
        //替换actionBar
        setSupportActionBar(toolbar);
        //保持图标原有的颜色
        nav_view.setItemIconTintList(null);
        nav_view.setNavigationItemSelectedListener(this);

        /**
         * ActionBarDrawerToggle 的作用：
          改变android.R.id.home返回图标。
          Drawer拉出、隐藏，带有android.R.id.home动画效果。
          监听Drawer拉出、隐藏；
         */
        //TODO
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer_layout,
                R.string.drawer_open, R.string.drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

    }

    private void initData(){

        //Replace an existing fragment that was added to a container.
        //mFgManager.beginTransaction().replace(R.id.cly_main_content,)
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
