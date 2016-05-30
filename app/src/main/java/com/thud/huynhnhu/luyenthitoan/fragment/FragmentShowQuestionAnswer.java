package com.thud.huynhnhu.luyenthitoan.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.adapter.ExamContentAdapter;
import com.thud.huynhnhu.luyenthitoan.datas.ExamContentDAL;
import com.thud.huynhnhu.luyenthitoan.model.ExamContent;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

import java.util.ArrayList;

import io.github.kexanie.library.MathView;

/**
 * Created by KhanhNguyen on 5/29/2016.
 */
public class FragmentShowQuestionAnswer extends Fragment {
    private ListView lv_list_info;
    public static ExamContentAdapter examContent_adapter;
    private Context context;
    public static ArrayList<ExamContent> arr_ExamContent = new ArrayList<ExamContent>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_list_view_fragment, container, false);
        lv_list_info = (ListView) view.findViewById(R.id.lst_list_view);

        Flags.main_dethi = false;
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new apiGetExamContent().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    private class apiGetExamContent extends AsyncTask<String, Void, Result<ArrayList<ExamContent>>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<ExamContent>> doInBackground(String... strings){
            return new ExamContentDAL(getActivity()).getAllExamContent(Flags.chosen_ma_dethi);
        }

        @Override
        protected void onPostExecute(Result<ArrayList<ExamContent>> arrayListResult){
            super.onPostExecute(arrayListResult);
            if (arrayListResult.getKey() == ResultStatus.TRUE){
                arr_ExamContent = arrayListResult.getValue();

                if (arr_ExamContent != null){
                    examContent_adapter = new ExamContentAdapter(context, arr_ExamContent);
                    lv_list_info.setAdapter(examContent_adapter);
                }

            }
        }
    }
}
