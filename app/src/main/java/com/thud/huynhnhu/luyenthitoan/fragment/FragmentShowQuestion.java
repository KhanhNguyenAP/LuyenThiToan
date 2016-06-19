package com.thud.huynhnhu.luyenthitoan.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import com.thud.huynhnhu.luyenthitoan.utils.dialogs.ToastMessage;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

import java.util.ArrayList;

import io.github.kexanie.library.MathView;

/**
 * Created by NhuLe on 5/29/2016.
 */
public class FragmentShowQuestion extends Fragment implements ActivityInterface {
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
        Flags.main_dethi = false;
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

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnwer();
            }
        });
    }

    @Override
    public void getData(String... params) {
        new apiGetExamContent().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void setData() {
        text_view_title.setText("Câu " + Flags.vitri_cauhoi + " : ");
        mathview.setText(arr_ExampleContent.get(Flags.vitri_cauhoi -1).getQuestion());

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
                Flags.vitri_cauhoi = 1;
                if (arr_ExampleContent.size() == 0){
                    getActivity().onBackPressed();
                    new ToastMessage(getActivity()).showToast("Đề thi trống !");
                }
                else {
                    setData();
                }
            }
        }
    }

    private void setValue(){
        if (arr_ExampleContent.size() == 1){
            btn_check.setVisibility(View.VISIBLE);

            btn_back.setVisibility(View.GONE);
            btn_next.setVisibility(View.GONE);
        }
        else{
            if (Flags.vitri_cauhoi == 1){
                btn_next.setVisibility(View.VISIBLE);

                btn_back.setVisibility(View.GONE);
                btn_check.setVisibility(View.GONE);
            }

            if (Flags.vitri_cauhoi > 1 && Flags.vitri_cauhoi < arr_ExampleContent.size()){
                btn_back.setVisibility(View.VISIBLE);
                btn_next.setVisibility(View.VISIBLE);

                btn_check.setVisibility(View.GONE);
            }

            if (Flags.vitri_cauhoi == arr_ExampleContent.size()){
                btn_check.setVisibility(View.VISIBLE);
                btn_back.setVisibility(View.VISIBLE);

                btn_next.setVisibility(View.GONE);
            }
        }
    }

    protected void selectedNext(){
        Flags.vitri_cauhoi += 1;

        setData();
    }

    protected void selectedBack(){
        Flags.vitri_cauhoi -= 1;

        setData();
    }

    private void showAnwer(){
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment fragShowAnwer = new FragmentShowQuestionAnswer();
        fragmentTransaction.replace(R.id.fra_dethi, fragShowAnwer, "Tra Loi");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}
