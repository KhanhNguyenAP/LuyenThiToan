package com.thud.huynhnhu.luyenthitoan.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.test.SingleLaunchActivityTestCase;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.adapter.ContentAdapter;
import com.thud.huynhnhu.luyenthitoan.datas.ContentDAL;
import com.thud.huynhnhu.luyenthitoan.model.Content;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.utils.async.SaveAllDataFromSerVer;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

import java.util.ArrayList;

/**
 * Created by KhanhNguyen on 5/11/2016.
 * Contact phone: 0975 282 877
 */
public class SplashActivity extends Activity{
    private Preference preference;
    public static ArrayList<Content> arr_Content = new ArrayList<Content>();

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //get share preference
        preference = new Preference(SplashActivity.this);

        if (Flags.synch_data == 0 && Flags.chosen_synch_data == 1){
            new SaveAllDataFromSerVer(SplashActivity.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

        new apiGetContent().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private class apiGetContent extends AsyncTask<String, Void, Result<ArrayList<Content>>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<Content>> doInBackground(String... strings){
            return new ContentDAL(SplashActivity.this).getAllConTentFromLoCal(Flags.chosen_ma_kienthuc);
        }

        @Override
        protected void onPostExecute(Result<ArrayList<Content>> arrayListResult){
            super.onPostExecute(arrayListResult);
            if (arrayListResult.getKey() == ResultStatus.TRUE){
                arr_Content = arrayListResult.getValue();
            }

            if (arr_Content.size() == 0){
                new SaveAllDataFromSerVer(SplashActivity.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }
    }
}
