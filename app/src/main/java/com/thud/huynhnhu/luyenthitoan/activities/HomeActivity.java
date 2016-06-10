package com.thud.huynhnhu.luyenthitoan.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.adapter.ExamAdapter;
import com.thud.huynhnhu.luyenthitoan.datas.ExamDAL;
import com.thud.huynhnhu.luyenthitoan.model.Exam;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ActivityInterface {
    private ViewPager viewPager;
    private LayoutInflater mlayoutInflater;
    private ExamAdapter examAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_dethi);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_home);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager_dethi);

        initFlags();

        initControl();

        getData();

        setEventForControl();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showDialog();
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
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home);
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.left_kienthuccanban) {
            intent = new Intent(HomeActivity.this, MainActivity.class);

        } else if (id == R.id.left_luyenthi) {
            intent = new Intent(HomeActivity.this, LuyenThiActivity.class);

        } else if (id == R.id.left_dethimau) {
            intent = new Intent(HomeActivity.this, DeThiActivity.class);

        } else if (id == R.id.left_maytinh) {
            intent = new Intent(HomeActivity.this, CalculatorActivity.class);

        } else if (id == R.id.left_thongtinungdung) {
            intent = new Intent(HomeActivity.this, ShowInfoAppActivity.class);

        } else if (id == R.id.left_thoat){
            showDialog();
        }

        if (id == R.id.left_taiungdung){
            if (inInternetOn() == true){
                intent = new Intent(HomeActivity.this, SplashActivity.class);
            }
        }

        if (intent != null){
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void initFlags() {
    }

    @Override
    public void initControl() {
        mlayoutInflater = LayoutInflater.from(HomeActivity.this);
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
            return new ExamDAL(HomeActivity.this).getAllExamFromLoCal();
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

    public final boolean inInternetOn(){
        ConnectivityManager connec = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet
            Flags.synch_data = 0;
            Flags.chosen_synch_data = 1;
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Flags.synch_data = 1;
            Flags.chosen_synch_data = 0;

            Toast.makeText(this, "You are not connect to the internet ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }
}
