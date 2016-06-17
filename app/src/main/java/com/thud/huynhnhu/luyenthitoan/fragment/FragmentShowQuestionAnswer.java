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
 * Created by NhuLe on 5/29/2016.
 */
public class FragmentShowQuestionAnswer extends Fragment implements ActivityInterface {
    private Context context;
    private View view;
    public static ArrayList<ExamContent> arr_ExampleContent = new ArrayList<ExamContent>();
    private TextView  text_view_title_cauhoi;
    private MathView mathview_noidung_cauhoi, mathview_noidung_dapan;
    private Button btn_back, btn_next, btn_close;

    private int vitri_cauhoi = 0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_show_detail_cauhoi_dapan, container, false);
        Flags.main_dethi = false;
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFlags();

        initControl();

        setEventForControl();

        getData();

    }

    @Override
    public void initFlags() {
        Flags.main_dethi = false;
    }

    @Override
    public void initControl() {
        text_view_title_cauhoi = (TextView) view.findViewById(R.id.text_view_title_cauhoi);
        mathview_noidung_cauhoi = (MathView) view.findViewById(R.id.mathview_noidung_cauhoi);
        mathview_noidung_dapan = (MathView) view.findViewById(R.id.mathview_noidung_dapan);

        btn_back = (Button) view.findViewById(R.id.btn_back);
        btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_close = (Button) view.findViewById(R.id.btn_close);
    }

    @Override
    public void setEventForControl() {
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNext();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBack();
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void getData(String... params) {
        new apiGetExamContent().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void setData() {
        text_view_title_cauhoi.setText("Câu " + Flags.vitri_cauhoi + " : ");
        mathview_noidung_cauhoi.setText(arr_ExampleContent.get(vitri_cauhoi).getQuestion());
        mathview_noidung_dapan.setText(arr_ExampleContent.get(vitri_cauhoi).getAnwser());

        setValue();
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

    private void setValue(){
        if (arr_ExampleContent.size() == 1){
            btn_back.setVisibility(View.GONE);
            btn_next.setVisibility(View.GONE);
        }
        else{
            if (Flags.vitri_cauhoi == 1){
                btn_next.setVisibility(View.VISIBLE);

                btn_back.setVisibility(View.GONE);
            }

            if (Flags.vitri_cauhoi > 1 && Flags.vitri_cauhoi < arr_ExampleContent.size()){
                btn_back.setVisibility(View.VISIBLE);
                btn_next.setVisibility(View.VISIBLE);
            }

            if (Flags.vitri_cauhoi == arr_ExampleContent.size()){
                btn_back.setVisibility(View.VISIBLE);

                btn_next.setVisibility(View.GONE);
            }
        }
    }

    protected void selectedNext(){
        vitri_cauhoi += 1;

        setData();
    }

    protected void selectedBack(){
        vitri_cauhoi -= 1;

        setData();
    }
}
