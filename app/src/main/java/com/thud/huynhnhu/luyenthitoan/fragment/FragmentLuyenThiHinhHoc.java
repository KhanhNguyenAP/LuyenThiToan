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
import com.thud.huynhnhu.luyenthitoan.activities.ShowListBaiHocActivity;
import com.thud.huynhnhu.luyenthitoan.adapter.ChapterAdapter;
import com.thud.huynhnhu.luyenthitoan.datas.ChapterDAL;
import com.thud.huynhnhu.luyenthitoan.model.Chapter;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

import java.util.ArrayList;

public class FragmentLuyenThiHinhHoc extends Fragment {
    private ListView lv_list_luyenthi;
    public static ChapterAdapter chapter_Adapter;
    private Context context;

    public static ArrayList<Chapter> arr_Chapter = new ArrayList<Chapter>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.item_list_view, container, false);
        lv_list_luyenthi = (ListView) view.findViewById(R.id.lv_daiso);

        setEvent();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new apiGetChapter().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        setEvent();
    }

    private class apiGetChapter extends AsyncTask<String, Void, Result<ArrayList<Chapter>>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<Chapter>> doInBackground(String... strings){
            return new ChapterDAL(context).getAllChapterFromLocalIsAlgebra(1, 0);
        }

        @Override
        protected void onPostExecute(Result<ArrayList<Chapter>> arrayListResult){
            super.onPostExecute(arrayListResult);
            if (arrayListResult.getKey() == ResultStatus.TRUE){
                arr_Chapter = arrayListResult.getValue();

                if (arr_Chapter != null){
                    chapter_Adapter = new ChapterAdapter(context, arr_Chapter);
                    lv_list_luyenthi.setAdapter(chapter_Adapter);
                }
            }
        }
    }

    private void setEvent(){
        lv_list_luyenthi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Flags.chapterId = arr_Chapter.get(position).getId();
                Flags.chosen_luyenthi =  1;

                Intent intent = new Intent(context, ShowListBaiHocActivity.class);
                startActivity(intent);
            }
        });
    }

}
