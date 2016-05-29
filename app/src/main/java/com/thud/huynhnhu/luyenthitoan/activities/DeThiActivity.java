package com.thud.huynhnhu.luyenthitoan.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.adapter.ExamAdapter;
import com.thud.huynhnhu.luyenthitoan.datas.ExamDAL;
import com.thud.huynhnhu.luyenthitoan.model.Exam;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;

import java.util.ArrayList;

public class DeThiActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ActivityInterface {
    private ViewPager viewPager;
    private LayoutInflater mlayoutInflater;
    private ListView lst_view_dethi;
    private ArrayList<Exam> arr_exam = new ArrayList<>();
    private ExamAdapter examAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dethi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_dethi);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_dethi);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_dethi);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager_dethi);

        initFlags();

        initControl();

        getData();

        setEventForControl();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_dethi);
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
        mlayoutInflater = LayoutInflater.from(DeThiActivity.this);
        lst_view_dethi = (ListView) findViewById(R.id.lv_daiso);
    }

    @Override
    public void setEventForControl() {

    }

    @Override
    public void getData(String... params) {
        new apiGetExam().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void setData() {

    }

    private class apiGetExam extends AsyncTask<String, Void, Result<ArrayList<Exam>>> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<Exam>> doInBackground(String... strings){
            return new ExamDAL(DeThiActivity.this).getAllExamFromLoCal();
        }

        @Override
        protected void onPostExecute(Result<ArrayList<Exam>> arrayListResult){
            super.onPostExecute(arrayListResult);
            if (arrayListResult.getKey() == ResultStatus.TRUE){
                arr_exam = arrayListResult.getValue();

                if (arr_exam != null){
                    examAdapter = new ExamAdapter(DeThiActivity.this, arr_exam);
                    lst_view_dethi.setAdapter(examAdapter);
                }
            }
        }
    }
}
