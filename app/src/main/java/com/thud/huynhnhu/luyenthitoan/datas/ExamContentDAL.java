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
import com.thud.huynhnhu.luyenthitoan.model.ExamContent;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhu on 5/29/2016.
 */
public class ExamContentDAL {
    private Context context;
    /* SQLite */
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public ExamContentDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
    }

    public Result<ArrayList<ExamContent>> getAllExamContent() {
        final ArrayList<ExamContent> arr_ExamContent= new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+ExamContent.TABLENAME);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        ExamContent examContent = new ExamContent(ob.getObjectId(),
                                ob.getString(""+ExamContent.ANSWER),
                                ob.getString(""+ExamContent.QUESTION),
                                ob.getString(""+ExamContent.EXAMID),
                                ob.getParseFile(""+ExamContent.IMAGE));
                        arr_ExamContent.add(examContent);
                    }

                    if (arr_ExamContent.size() >0){
                        Result<String> result = new AllDAL(context).saveAll(arr_ExamContent);
                    }
                }
            }
        });

        return new Result<ArrayList<ExamContent>>(ResultStatus.TRUE, arr_ExamContent);
    }


    /*
    * insert all data from server
    * */
    public Result<String> insertExamContentFromLocal(ArrayList<ExamContent> examContents){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(ExamContent examContent : examContents){
                ContentValues examContentDb = DbModel.getContentValueExamContent(examContent);

                //insert database
                database.insert(ExamContent.TABLENAME, null, examContentDb);
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

    public Result<ArrayList<ExamContent>> getAllExamContent(String examId){
        database = dbHelper.getReadableDatabase();
        ArrayList<ExamContent> examContents = new ArrayList<>();
        try {
            String query_ExamContent = "SELECT * FROM " + ExamContent.TABLENAME + " WHERE "
                    + ExamContent.EXAMID + " = '" +examId + "'";
            Cursor cursor = database.rawQuery(query_ExamContent, null);
            if(cursor != null && cursor.moveToFirst()){
                do{
                    ExamContent examContent = DbModel.getExamContent(cursor);
                    examContents.add(examContent);
                }while (cursor.moveToNext());
            }

            database.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
            Log.e(Def.ERROR, ex.getMessage());
        }
        return  new Result<ArrayList<ExamContent>>(ResultStatus.TRUE, examContents);
    }
}
