package com.thud.huynhnhu.luyenthitoan.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.fragment.FragmentBaiHoc;
import com.thud.huynhnhu.luyenthitoan.fragment.FragmentDeThi;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Def;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

public class ShowListBaiHocActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ActivityInterface {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    FragmentManager fragmentManager = this.getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dethi);

        toolbar = (Toolbar) findViewById(R.id.toolbar_dethi_main);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_dethi_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_dethi);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(3).setChecked(true);

        FragmentBaiHoc fragBaiHoc = (FragmentBaiHoc) getFragmentManager().findFragmentById(R.id.fra_dethi);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragBaiHoc = new FragmentBaiHoc();
        fragmentTransaction.replace(R.id.fra_dethi, fragBaiHoc, "Bai Hoc");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        Intent intent = null;
        if (Flags.chosen_luyenthi == 0){
            intent = new Intent(ShowListBaiHocActivity.this, MainActivity.class);
        }
        else {
            intent = new Intent(ShowListBaiHocActivity.this, LuyenThiActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
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
            intent = new Intent(ShowListBaiHocActivity.this, HomeActivity.class);

        } else if (id == R.id.left_kienthuccanban) {
            intent = new Intent(ShowListBaiHocActivity.this, MainActivity.class);

        } else if (id == R.id.left_luyenthi) {
            intent = new Intent(ShowListBaiHocActivity.this, LuyenThiActivity.class);

        } else if (id == R.id.left_dethimau) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_dethi_main);
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.left_maytinh) {
            intent = new Intent(ShowListBaiHocActivity.this, CalculatorActivity.class);

        } else if (id == R.id.left_thongtinungdung) {
            intent = new Intent(ShowListBaiHocActivity.this, ShowInfoAppActivity.class);

        } else if (id == R.id.left_thoat){
            showDialog();
        }

        if (id == R.id.left_taiungdung){
            if (inInternetOn() == true){
                intent = new Intent(ShowListBaiHocActivity.this, SplashActivity.class);
            }
        }

        if (intent!=null){
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_dethi_main);
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
