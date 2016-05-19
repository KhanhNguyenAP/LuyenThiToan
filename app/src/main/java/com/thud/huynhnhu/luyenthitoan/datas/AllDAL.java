package com.thud.huynhnhu.luyenthitoan.datas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.thud.huynhnhu.luyenthitoan.datas.ContentDAL;
import com.thud.huynhnhu.luyenthitoan.datas.DBHelper;
import com.thud.huynhnhu.luyenthitoan.datas.ExamDAL;
import com.thud.huynhnhu.luyenthitoan.datas.ExampleDAL;
import com.thud.huynhnhu.luyenthitoan.datas.TopicDAL;
import com.thud.huynhnhu.luyenthitoan.model.Content;
import com.thud.huynhnhu.luyenthitoan.model.Exam;
import com.thud.huynhnhu.luyenthitoan.model.Example;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.model.Topic;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by conheo on 12/05/2016.
 */
public class AllDAL {
    private Context context;

    /*sqlite*/
    private DBHelper db_helper;
    private SQLiteDatabase database;
    public static String path_document = Def.STRING_EMPTY;

    public AllDAL(Context current){
        this.context = current;
        db_helper = new DBHelper(context);
    }

    public Result<String> saveAll(Object... object){
        try {
            Result<String> result = new Result<String>(ResultStatus.FALSE, null, Def.STRING_EMPTY);

            if (((ArrayList) object[0]).size() >0){
                Object firstObject = ((ArrayList) object[0]).get(0);
                //with content
                if (firstObject != null && firstObject instanceof Content){
                    ArrayList<Content> contents = ((ArrayList) object[0]);
                    result = new ContentDAL(context).insertContentFromLocal(contents);
                }
                else {
                    if (firstObject != null && firstObject instanceof Exam){
                        ArrayList<Exam> exams = ((ArrayList) object[0]);
                        return result = new ExamDAL(context).insertExamFromLocal(exams);
                    }
                    else {
                        if(firstObject != null && firstObject instanceof Example){
                            ArrayList<Example> examples = ((ArrayList) object[0]);
                            result = new ExampleDAL(context).insertExampleFromLocal(examples);
                        }
                        else {
                            ArrayList<Topic> topics = (ArrayList) object[0];
                            return  result = new TopicDAL(context).insertTopicFromLocal(topics);
                        }//end Example
                    }//end Exam
                } // end Content
            }
            return result;
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
            return new Result<String>(ResultStatus.FALSE, null);
        }
    }

    public boolean dropAllTable(){
        try{
            db_helper.DropAllTable(database);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
