package com.thud.huynhnhu.luyenthitoan.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.fragment.FragmentLuyenThiDaiSo;
import com.thud.huynhnhu.luyenthitoan.fragment.FragmentLuyenThiHinhHoc;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;

import java.util.ArrayList;
import java.util.List;

public class LuyenThiActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ActivityInterface {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LayoutInflater mlayoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyenthi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_luyenthi);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_luyenthi);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_luyenthi);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(2).setChecked(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager_luyenthi);
        setupViewPager(viewPager);

        initFlags();

        initControl();

        tabLayout.setupWithViewPager(viewPager);
        setEventForControl();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_luyenthi);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
            intent = new Intent(LuyenThiActivity.this, HomeActivity.class);

        } else if (id == R.id.left_kienthuccanban) {
            intent = new Intent(LuyenThiActivity.this, MainActivity.class);

        } else if (id == R.id.left_luyenthi) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_luyenthi);
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.left_dethimau) {
            intent = new Intent(LuyenThiActivity.this, DeThiActivity.class);

        } else if (id == R.id.left_maytinh) {
            intent = new Intent(LuyenThiActivity.this, CalculatorActivity.class);

        } else if (id == R.id.left_thongtinungdung) {
            intent = new Intent(LuyenThiActivity.this, ShowInfoAppActivity.class);

        } else if (id == R.id.left_thoat){
            showDialog();
        }
        if (intent!=null){
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_luyenthi);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void initFlags() {
    }

    @Override
    public void initControl() {
        tabLayout = (TabLayout) findViewById(R.id.tabs_luyenthi);
        mlayoutInflater = LayoutInflater.from(LuyenThiActivity.this);
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
        adapter.addFragment(new FragmentLuyenThiDaiSo(), getResources().getString(R.string.tab_daiso));
        adapter.addFragment(new FragmentLuyenThiHinhHoc(), getResources().getString(R.string.tab_hinhhoc));
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

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close this application ?")
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.setTitle(R.string.close_app);
        alert.show();
    }
}
