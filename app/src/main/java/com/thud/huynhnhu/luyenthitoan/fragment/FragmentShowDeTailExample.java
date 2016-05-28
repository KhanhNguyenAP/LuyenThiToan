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
import com.thud.huynhnhu.luyenthitoan.adapter.ExampleAdapter;
import com.thud.huynhnhu.luyenthitoan.datas.ExampleDAL;
import com.thud.huynhnhu.luyenthitoan.model.Example;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

import java.util.ArrayList;

public class FragmentShowDeTailExample extends Fragment {
    private ListView lv_list_info;
    public static ExampleAdapter example_Adapter;
    private Context context;
    public static ArrayList<Example> arr_Example = new ArrayList<Example>();

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
        new apiGetExample().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    private class apiGetExample extends AsyncTask<String, Void, Result<ArrayList<Example>>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<Example>> doInBackground(String... strings){
            return new ExampleDAL(context).getAllExampleFromLoCal(Flags.chosen_ma_kienthuc);
        }

        @Override
        protected void onPostExecute(Result<ArrayList<Example>> arrayListResult){
            super.onPostExecute(arrayListResult);
            if (arrayListResult.getKey() == ResultStatus.TRUE){
                arr_Example = arrayListResult.getValue();

                if (arr_Example != null){
                    example_Adapter = new ExampleAdapter(context, arr_Example);
                    lv_list_info.setAdapter(example_Adapter);
                }
            }
        }
    }

}
