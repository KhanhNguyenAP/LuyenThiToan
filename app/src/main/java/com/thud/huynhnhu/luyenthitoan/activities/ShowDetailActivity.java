package com.thud.huynhnhu.luyenthitoan.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.fragment.FragmentShowDeTailExample;
import com.thud.huynhnhu.luyenthitoan.fragment.FragmentShowDeTailInfo;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;

import java.util.ArrayList;
import java.util.List;

public class ShowDetailActivity extends BaseActivity
        implements ActivityInterface {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LayoutInflater mlayoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_show_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_show_detail);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager_show_detail);
        setupViewPager(viewPager);

        initFlags();

        initControl();

        tabLayout.setupWithViewPager(viewPager);
        setEventForControl();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void initFlags() {
    }

    @Override
    public void initControl() {
        tabLayout = (TabLayout) findViewById(R.id.tabs_show_detail);
        mlayoutInflater = LayoutInflater.from(ShowDetailActivity.this);
    }

    @Override
    public void setEventForControl() {

    }

    @Override
    public void getData(String... params) {

    }

    @Override
    public void setData() {

    }

    //Set up View Pager and TabLayout
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentShowDeTailInfo(), getResources().getString(R.string.tab_lythuyet));
        adapter.addFragment(new FragmentShowDeTailExample(), getResources().getString(R.string.tab_vidu));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
