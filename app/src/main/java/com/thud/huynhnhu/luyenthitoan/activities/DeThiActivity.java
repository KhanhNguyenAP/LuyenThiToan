package com.thud.huynhnhu.luyenthitoan.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.adapter.ExamAdapter;
import com.thud.huynhnhu.luyenthitoan.datas.ExamDAL;
import com.thud.huynhnhu.luyenthitoan.fragment.FragmentDeThi;
import com.thud.huynhnhu.luyenthitoan.model.Exam;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Def;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

import java.util.ArrayList;

public class DeThiActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ActivityInterface {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    android.app.FragmentManager fragmentManager = this.getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dethi);

        toolbar = (Toolbar) findViewById(R.id.toolbar_dethi_main);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_dethi_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_dethi);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentDeThi fragDeThi = (FragmentDeThi) getFragmentManager().findFragmentById(R.id.fra_dethi);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragDeThi = new FragmentDeThi();
        fragmentTransaction.replace(R.id.fra_dethi, fragDeThi, "De Thi");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (Flags.main_dethi == true){
            finish();
        }

        showListExam(fragmentManager);
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

        if (id == R.id.left_trangchu) {
            // Handle the camera action
        } else if (id == R.id.left_kienthuccanban) {

        } else if (id == R.id.left_luyenthi) {

        } else if (id == R.id.left_dethimau) {

        } else if (id == R.id.left_maytinh) {

        } else if (id == R.id.left_thongtinungdung) {

        } else if (id == R.id.left_thoat){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void initFlags() {
    }

    @Override
    public void initControl() {
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

    public static void showListExam(android.app.FragmentManager fragmentManager){
        try{
            android.app.Fragment fragmentRangeList =
                    fragmentManager.findFragmentByTag(Def.TAG_DETHI);

            if(fragmentRangeList != null){
                fragmentManager.beginTransaction().remove(fragmentRangeList).commitAllowingStateLoss();
            }

            fragmentRangeList = new FragmentDeThi();
            fragmentManager.beginTransaction()
                    .add(R.id.fra_dethi, fragmentRangeList, Def.TAG_DETHI)
                    .commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }//--end showRangeList
}
