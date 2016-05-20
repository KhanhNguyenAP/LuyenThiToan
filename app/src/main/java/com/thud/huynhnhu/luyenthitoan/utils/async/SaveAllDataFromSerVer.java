package com.thud.huynhnhu.luyenthitoan.utils.async;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.datas.ContentDAL;
import com.thud.huynhnhu.luyenthitoan.datas.ExamDAL;
import com.thud.huynhnhu.luyenthitoan.datas.ExampleDAL;
import com.thud.huynhnhu.luyenthitoan.datas.TopicDAL;
import com.thud.huynhnhu.luyenthitoan.datas.AllDAL;
import com.thud.huynhnhu.luyenthitoan.model.Content;
import com.thud.huynhnhu.luyenthitoan.model.Exam;
import com.thud.huynhnhu.luyenthitoan.model.Example;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.model.Topic;
import com.thud.huynhnhu.luyenthitoan.utils.Preference;
import com.thud.huynhnhu.luyenthitoan.utils.dialogs.ToastMessage;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

import java.util.ArrayList;

/**
 * Created by conheo on 12/05/2016.
 */
public class SaveAllDataFromSerVer extends AsyncTask<Void, Integer, Result<String>> implements ActivityInterface {
    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private View view;
    private TextView text_view_percent, text_view_title;
    private ProgressBar progress_custom;
    private Context context;

    public SaveAllDataFromSerVer(Context current){
        this.context = current;
           view = ((Activity) context).getLayoutInflater().inflate(R.layout.custom_progressbar_percent, null);

        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);

        initControl();

        //create an alert dialog
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Result<String> doInBackground(Void... params) {
        try {
            if (Flags.synch_data == 0){
                new AllDAL(context).dropAllTable();
            }
            else {
                return new Result<String>(ResultStatus.FALSE, null, context.getResources().getString(R.string.msg_can_not_connect_to_network));
            }

            getContent();
            publishProgress(1);
            getExam();
            publishProgress(2);
            getExample();
            publishProgress(3);
            getTopic();
            publishProgress(4);
            return new Result<String>(ResultStatus.FALSE, null, context.getResources().getString(R.string.msg_data_has_been_saved));
        }
        catch (Exception e){
            e.printStackTrace();
            return new Result<String>(ResultStatus.FALSE, null, context.getResources().getString(R.string.msg_can_not_connect_to_network));
        }
    }

    @Override
    protected void onProgressUpdate(Integer... progress){
        super.onProgressUpdate(progress);
        setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(Result<String> result){
        super.onPostExecute(result);
        alertDialog.dismiss();

        if(result.getKey() == ResultStatus.FALSE){
            new ToastMessage(context).showToast(result.getMessage());
        }
        else {
            new ToastMessage(context).showToast(context.getResources().getString(R.string.msg_download_data));
            //Flags.synch_data = 1;
        }
    }

    @Override
    public void initFlags() {

    }

    @Override
    public void initControl() {
        text_view_title = (TextView)view.findViewById(R.id.text_view_title_progress);
        text_view_percent = (TextView)view.findViewById(R.id.text_view_percent);
        progress_custom = (ProgressBar)view.findViewById(R.id.progress_custom);
    }

    @Override
    public void setEventForControl() {

    }

    @Override
    public void getData(String... params) {

    }

    @Override
    public void setData() {

    }

    private void setProgress(int progressNumber){
        progress_custom.setProgress(progressNumber);
        text_view_percent.setText(progressNumber*20+"%");
    }

    //get Content
    private boolean getContent(){
        try {
            Result<String> resultContent = new ContentDAL(context).getAllContent();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get Exam
    private boolean getExam(){
        try {
            Result<ArrayList<Exam>> resultExam = new ExamDAL(context).getAllExam();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get Example
    private boolean getExample(){
        try {
            Result<ArrayList<Example>> resultExample = new ExampleDAL(context).getAllExample();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get Topic
    private boolean getTopic(){
        try {
            Result<ArrayList<Topic>> resultTopic = new TopicDAL(context).getAllTopic();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
