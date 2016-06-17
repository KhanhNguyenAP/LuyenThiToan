package com.thud.huynhnhu.luyenthitoan.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.activities.ShowDetailLuyenThiActivity;
import com.thud.huynhnhu.luyenthitoan.adapter.TopicAdapter;
import com.thud.huynhnhu.luyenthitoan.datas.TopicDAL;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.model.Topic;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

import java.util.ArrayList;

public class FragmentLuyenThiGiaiTich extends Fragment {
    private ListView lv_list_luyenthi;
    public static TopicAdapter topic_Adapter;
    private Context context;

    public static ArrayList<Topic> arr_Topic = new ArrayList<Topic>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_list_view, container, false);
        lv_list_luyenthi = (ListView) view.findViewById(R.id.lv_daiso);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new apiGetTopic().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        setEvent();
    }

    private class apiGetTopic extends AsyncTask<String, Void, Result<ArrayList<Topic>>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<Topic>> doInBackground(String... strings){
            return new TopicDAL(context).getAllTopicFromLocalIsAlgebra(1, 0);
        }

        @Override
        protected void onPostExecute(Result<ArrayList<Topic>> arrayListResult){
            super.onPostExecute(arrayListResult);
            if (arrayListResult.getKey() == ResultStatus.TRUE){
                arr_Topic = arrayListResult.getValue();

                if (arr_Topic != null){
                    topic_Adapter = new TopicAdapter(context, arr_Topic);
                    lv_list_luyenthi.setAdapter(topic_Adapter);
                }
            }
        }
    }

    private void setEvent(){
        lv_list_luyenthi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Flags.chosen_ma_kienthuc = arr_Topic.get(position).getId();

                Intent intent = new Intent(context, ShowDetailLuyenThiActivity.class);
                startActivity(intent);
            }
        });
    }
}
