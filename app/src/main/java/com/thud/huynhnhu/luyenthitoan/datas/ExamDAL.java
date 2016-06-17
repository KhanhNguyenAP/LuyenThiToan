package com.thud.huynhnhu.luyenthitoan.datas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.model.Content;
import com.thud.huynhnhu.luyenthitoan.model.Exam;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NhuLe on 5/13/2016.
 */
public class ExamDAL {
    private Context context;

    /* SQLite */
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public ExamDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public Result<ArrayList<Exam>> getAllExam() {
        final ArrayList<Exam> arr_Exam = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+Exam.TABLENAME);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        Exam exam = new Exam(ob.getObjectId(),
                                ob.getInt(""+Exam.POSITION),
                                ob.getString(""+Exam.NAME),
                                ob.getString(""+Exam.INFO),
                                ob.getString(""+Exam.QUESTION),
                                ob.getString(""+Exam.ANSWER));
                        arr_Exam.add(exam);
                    }

                    if (arr_Exam.size() >0){
                        Result<String> result = new AllDAL(context).saveAll(arr_Exam);
                    }
                }
            }
        });

        return new Result<ArrayList<Exam>>(ResultStatus.TRUE, arr_Exam);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertExamFromLocal(ArrayList<Exam> exams){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(Exam exam : exams){
                ContentValues examDb = DbModel.getContentValueExam(exam);

                //insert database
                database.insert(Exam.TABLENAME, null, examDb);
            }

            database.setTransactionSuccessful();
            database.endTransaction();
            database.close();

            return new Result<String>(ResultStatus.TRUE, context.getResources().getString(R.string.msg_data_has_been_saved));
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }

        return new Result<String>(ResultStatus.FALSE, null);
    }

    public Result<ArrayList<Exam>> getAllExamFromLoCal(){
        ArrayList<Exam> exams = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        try {
            String query = "SELECT * FROM " +Exam.TABLENAME;
            Cursor cursor = database.rawQuery(query, null);

            if(cursor != null && cursor.moveToFirst()){
                do{
                    Exam exam = DbModel.getExam(cursor);
                    exams.add(exam);
                }while (cursor.moveToNext());
            }

            database.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }

        return new Result<ArrayList<Exam>>(ResultStatus.TRUE, exams);
    }
}

