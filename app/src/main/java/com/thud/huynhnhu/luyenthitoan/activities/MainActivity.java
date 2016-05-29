package com.thud.huynhnhu.luyenthitoan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.fragment.FragmentDaiSo;
import com.thud.huynhnhu.luyenthitoan.fragment.FragmentHinhHoc;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ActivityInterface {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LayoutInflater mlayoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;
        if (id == R.id.left_trangchu) {
            intent = new Intent(MainActivity.this, HomeActivity.class);
        } else if (id == R.id.left_kienthuccanban) {
            intent = new Intent(MainActivity.this, MainActivity.class);
        } else if (id == R.id.left_luyenthi) {
            intent = new Intent(MainActivity.this, LuyenThiActivity.class);
        } else if (id == R.id.left_dethimau) {
            intent = new Intent(MainActivity.this, DeThiActivity.class);
        } else if (id == R.id.left_maytinh) {
            intent = new Intent(MainActivity.this, CalculatorActivity.class);
        } else if (id == R.id.left_thongtinungdung) {
            intent = new Intent(MainActivity.this, ShowInfoAppActivity.class);
        } else if (id == R.id.left_thoat){
            finish();
            System.exit(0);
        }
        if (intent!=null){
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void initFlags() {
        Flags.synch_data = 1;
    }

    @Override
    public void initControl() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        mlayoutInflater = LayoutInflater.from(MainActivity.this);
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
        adapter.addFragment(new FragmentDaiSo(), getResources().getString(R.string.tab_daiso));
        adapter.addFragment(new FragmentHinhHoc(), getResources().getString(R.string.tab_hinhhoc));
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
