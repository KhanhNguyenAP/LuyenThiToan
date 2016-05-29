package com.thud.huynhnhu.luyenthitoan.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.thud.huynhnhu.luyenthitoan.R;
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
public class FragmentShowQuection extends Fragment implements ActivityInterface {
    private View view;
    private TextView text_view_title;
    private MathView mathview;
    private Button btn_next, btn_back, btn_check;
    public static ArrayList<ExamContent> arr_ExampleContent = new ArrayList<ExamContent>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){
        view = inflater.inflate(R.layout.item_show_cauhoi_dethi, container, false);

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
        Flags.vitri_cauhoi = 1;
    }

    @Override
    public void initControl() {
        text_view_title = (TextView) view.findViewById(R.id.text_view_title);
        mathview = (MathView) view.findViewById(R.id.mathview);

        btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_back = (Button) view.findViewById(R.id.btn_back);
        btn_check = (Button) view.findViewById(R.id.btn_check);
    }

    @Override
    public void setEventForControl() {

    }

    @Override
    public void getData(String... params) {
        new apiGetExamContent().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void setData() {
        text_view_title.setText("Câu: " + Flags.vitri_cauhoi);
        mathview.setText(arr_ExampleContent.get(Flags.vitri_cauhoi-1).getQuestion());
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
                arr_ExampleContent = arrayListResult.getValue();

                setData();
            }
        }
    }
}
