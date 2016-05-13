package com.thud.huynhnhu.luyenthitoan.utils.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Def;

/**
 * Created by conheo on 12/05/2016.
 */
public class ToastMessage {
    private Context context;

    public ToastMessage(Context context){
        this.context = context;
    }

    public  void showToast(String message){
        try {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            View view = toast.getView();
            view.setBackgroundResource(R.color.bg_black);
            TextView text = (TextView) view.findViewById(android.R.id.message);
            text.setTextColor(Color.WHITE);
            toast.show();
        }
        catch (Exception e){
            Log.e(Def.ERROR, e.getMessage());
            e.printStackTrace();
        }
    }
}
