package com.thud.huynhnhu.luyenthitoan.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.adapter.ContentAdapter;
import com.thud.huynhnhu.luyenthitoan.adapter.TopicAdapter;
import com.thud.huynhnhu.luyenthitoan.datas.ContentDAL;
import com.thud.huynhnhu.luyenthitoan.datas.TopicDAL;
import com.thud.huynhnhu.luyenthitoan.model.Content;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.model.Topic;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

import java.util.ArrayList;

public class FragmentShowDeTailInfo extends Fragment{
    private ListView lv_list_info;
    public static ContentAdapter content_Adapter;
    private Context context;
    public static ArrayList<Content> arr_Content = new ArrayList<Content>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_list_view, container, false);
        lv_list_info = (ListView) view.findViewById(R.id.lv_daiso);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new apiGetContent().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    private class apiGetContent extends AsyncTask<String, Void, Result<ArrayList<Content>>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<Content>> doInBackground(String... strings){
            return new ContentDAL(context).getAllConTentFromLoCal(Flags.chosen_ma_kienthuc);
        }

        @Override
        protected void onPostExecute(Result<ArrayList<Content>> arrayListResult){
            super.onPostExecute(arrayListResult);
            if (arrayListResult.getKey() == ResultStatus.TRUE){
                arr_Content = arrayListResult.getValue();

                if (arr_Content != null){
                    content_Adapter = new ContentAdapter(context, arr_Content);
                    lv_list_info.setAdapter(content_Adapter);
                }
            }
        }
    }
}
