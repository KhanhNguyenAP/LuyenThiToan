package com.thud.huynhnhu.luyenthitoan.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.activities.MainActivity;
import com.thud.huynhnhu.luyenthitoan.activities.ShowDetailActivity;
import com.thud.huynhnhu.luyenthitoan.activities.ShowDetailLuyenThiActivity;
import com.thud.huynhnhu.luyenthitoan.adapter.ExamAdapter;
import com.thud.huynhnhu.luyenthitoan.adapter.TopicAdapter;
import com.thud.huynhnhu.luyenthitoan.datas.ExamDAL;
import com.thud.huynhnhu.luyenthitoan.datas.TopicDAL;
import com.thud.huynhnhu.luyenthitoan.model.Exam;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.model.Topic;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

import java.util.ArrayList;

/**
 * Created by NhuLe on 5/29/2016.
 */
public class FragmentBaiHoc extends Fragment implements ActivityInterface {
    private View view;
    private ListView lst_topic;
    private ArrayList<Topic> arr_topic= new ArrayList<>();
    private TopicAdapter topic_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.item_list_view_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        initFlags();

        initControl();

        setEventForControl();

        getData();
    }
    @Override
    public void initFlags() {
    }

    @Override
    public void initControl() {
        lst_topic = (ListView) view.findViewById(R.id.lst_list_view);
    }

    @Override
    public void setEventForControl() {
        lst_topic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Flags.chosen_ma_kienthuc = arr_topic.get(position).getId();
                Intent intent = null;

                if (Flags.chosen_luyenthi == 0){
                    intent = new Intent(getActivity(), ShowDetailActivity.class);
                }
                else {
                    intent = new Intent(getActivity(), ShowDetailLuyenThiActivity.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getData(String... params) {
        new apiGetTopic().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void setData() {
    }

    private class apiGetTopic extends AsyncTask<String, Void, Result<ArrayList<Topic>>> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<Topic>> doInBackground(String... strings){
            return new TopicDAL(getActivity()).getAllTopicFromLocal(Flags.chapterId);
        }

        @Override
        protected void onPostExecute(Result<ArrayList<Topic>> arrayListResult){
            super.onPostExecute(arrayListResult);
            if (arrayListResult.getKey() == ResultStatus.TRUE){
                arr_topic = arrayListResult.getValue();

                if (arr_topic != null){
                    topic_adapter = new TopicAdapter(getActivity(), arr_topic);
                    lst_topic.setAdapter(topic_adapter);
                }
            }
        }
    }
}
