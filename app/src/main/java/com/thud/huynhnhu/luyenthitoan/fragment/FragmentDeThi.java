package com.thud.huynhnhu.luyenthitoan.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.adapter.ExamAdapter;
import com.thud.huynhnhu.luyenthitoan.datas.ExamDAL;
import com.thud.huynhnhu.luyenthitoan.model.Exam;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

import java.util.ArrayList;

/**
 * Created by NhuLe on 5/29/2016.
 */
public class FragmentDeThi extends Fragment implements ActivityInterface {
    private View view;
    private ListView lst_dethi;
    private ArrayList<Exam> arr_exam = new ArrayList<>();
    private ExamAdapter exam_adapter;

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
        Flags.main_dethi = true;
    }

    @Override
    public void initControl() {
        lst_dethi = (ListView) view.findViewById(R.id.lst_list_view);
    }

    @Override
    public void setEventForControl() {
        lst_dethi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Flags.chosen_ma_dethi = arr_exam.get(position).getId();

                showDeTail();
            }
        });
    }

    @Override
    public void getData(String... params) {
        new apiGetExam().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void setData() {
    }

    private class apiGetExam extends AsyncTask<String, Void, Result<ArrayList<Exam>>> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<Exam>> doInBackground(String... strings){
            return new ExamDAL(getActivity()).getAllExamFromLoCal();
        }

        @Override
        protected void onPostExecute(Result<ArrayList<Exam>> arrayListResult){
            super.onPostExecute(arrayListResult);
            if (arrayListResult.getKey() == ResultStatus.TRUE){
                arr_exam = arrayListResult.getValue();

                if (arr_exam != null){
                    exam_adapter = new ExamAdapter(getActivity(), arr_exam);
                    lst_dethi.setAdapter(exam_adapter);
                }
            }
        }
    }

    public void showDeTail(){
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment fragShow = new FragmentShowQuestion();
        fragmentTransaction.replace(R.id.fra_dethi, fragShow, "De thi");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}
