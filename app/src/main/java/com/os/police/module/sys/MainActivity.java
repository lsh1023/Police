package com.os.police.module.sys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.os.police.R;
import com.os.police.base.FragmentHelper;
import com.os.police.utils.AppManager;
import com.os.police.base.BaseActivity;
import com.os.police.module.matter.MatterFragment;
import com.os.police.module.news.NewsFragment;
import com.os.police.module.home.ui.HomeFragment;
import com.os.police.module.mine.ui.MineFragment;

import java.util.ArrayList;

/**
 * 主页
 */

public class MainActivity extends BaseActivity {

    HomeFragment homeFragment = new HomeFragment();
    MatterFragment matterFragment = new MatterFragment();
    NewsFragment newsFragment = new NewsFragment();
    MineFragment mineFragment = new MineFragment();

    RadioButton rbHome;
    RadioButton rbMatter;
    RadioButton rbNews;
    RadioButton rbMine;
    RadioGroup radioGroupMain;


    private RadioButton[] radioButtons = new RadioButton[4];
    private ArrayList<Fragment> fragmentArrayList;
    private FragmentManager fragmentManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        rbHome = (RadioButton) findViewById(R.id.rb_home);
        rbMatter = (RadioButton) findViewById(R.id.rb_matter);
        rbNews = (RadioButton) findViewById(R.id.rb_news);
        rbMine = (RadioButton) findViewById(R.id.rb_mine);
        radioGroupMain = (RadioGroup) findViewById(R.id.radioGroup_main);

        radioButtons[0] = rbHome;
        radioButtons[1] = rbMatter;
        radioButtons[2] = rbNews;
        radioButtons[3] = rbMine;

        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(homeFragment);
        fragmentArrayList.add(matterFragment);
        fragmentArrayList.add(newsFragment);
        fragmentArrayList.add(mineFragment);

        fragmentManager = getSupportFragmentManager();
        FragmentHelper.replaceFragment(fragmentManager, fragmentArrayList, 0, R.id.frame_container, 0, 0);
        radioGroupMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < fragmentArrayList.size(); i++) {
                    radioButtons[i].setChecked(false);
                    if (radioButtons[i].getId() == checkedId) {
                        radioButtons[i].setChecked(true);
                        FragmentHelper.replaceFragment(fragmentManager, fragmentArrayList, i, R.id.frame_container, 0, 0);
                    }
                }
            }
        });
        radioGroupMain.check(R.id.rb_home);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AppManager.getAppManager().finishAllActivity();
        return super.onKeyDown(keyCode, event);
    }

}
