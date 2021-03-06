package com.jj.investigation.customebehavior.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.jj.investigation.customebehavior.R;
import com.jj.investigation.customebehavior.fragment.ContactFragment;
import com.jj.investigation.customebehavior.fragment.GroupFragment;
import com.jj.investigation.customebehavior.fragment.HomeFragment;
import com.jj.investigation.customebehavior.helper.NavHelper;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavHelper.OnTabChangedListener<Integer> {

    private BottomNavigationView mBnvMain;
    private NavHelper<Integer> mNavHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow()
                .addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
        initData();
    }

    private void initView() {

        mBnvMain = (BottomNavigationView) findViewById(R.id.bnv_main);
        mBnvMain.setOnNavigationItemSelectedListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomeViewActivity.class));
            }
        });
    }

    private void initData() {
        // 初始化底部辅助工具类
        mNavHelper = new NavHelper<>(this, R.id.fl_main_container,
                getSupportFragmentManager(), this);
        mNavHelper.add(R.id.action_home, new NavHelper.Tab<>(HomeFragment.class, R.string.action_home))
                .add(R.id.action_group, new NavHelper.Tab<>(GroupFragment.class, R.string.action_group))
                .add(R.id.action_contact, new NavHelper.Tab<>(ContactFragment.class, R.string.action_contact));

        Menu menu = mBnvMain.getMenu();
        // 触发首次选中Home
        menu.performIdentifierAction(R.id.action_home, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return mNavHelper.performClickMenu(item.getItemId());
    }

    @Override
    public void onTabChanged(NavHelper.Tab<Integer> newTab, NavHelper.Tab<Integer> oldTab) {

    }
}
