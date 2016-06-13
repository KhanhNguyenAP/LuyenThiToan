package com.thud.huynhnhu.luyenthitoan.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Nhu Le on 12/05/2016.
 */
public class Preference {
    private SharedPreferences preferences;

    public Preference(Context mContext){
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }
}
