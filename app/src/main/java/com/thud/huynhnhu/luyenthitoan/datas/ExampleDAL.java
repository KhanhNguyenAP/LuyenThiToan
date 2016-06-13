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
import com.thud.huynhnhu.luyenthitoan.model.Exam;
import com.thud.huynhnhu.luyenthitoan.model.Example;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NhuLe on 5/13/2016.
 */
public class ExampleDAL {
    private Context context;

    /* SQLite */
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public ExampleDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public Result<ArrayList<Example>> getAllExample() {
        final ArrayList<Example> arr_Example = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+Example.TABLENAME);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        Example example = new Example(ob.getObjectId(),
                                ob.getString(""+Example.TOPICID),
                                ob.getInt(""+Example.POSITION),
                                ob.getString(""+Example.QUESTION),
                                ob.getString(""+Example.ANSWER));
                        arr_Example.add(example);
                    }

                    if (arr_Example.size() >0){
                        Result<String> result = new AllDAL(context).saveAll(arr_Example);
                    }
                }
            }
        });

        return new Result<ArrayList<Example>>(ResultStatus.TRUE, arr_Example);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertExampleFromLocal(ArrayList<Example> examples){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(Example example : examples){
                ContentValues exampleDb = DbModel.getContentValueExample(example);

                //insert database
                database.insert(Example.TABLENAME, null, exampleDb);
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

    public Result<ArrayList<Example>> getAllExampleFromLoCal(String topicId){
        ArrayList<Example> examples = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        try {
            String query = "SELECT * FROM " +Example.TABLENAME + " WHERE " +Example.TOPICID + " ='" + topicId+"'";
            Cursor cursor = database.rawQuery(query, null);

            if(cursor != null && cursor.moveToFirst()){
                do{
                    Example example = DbModel.getExample(cursor);
                    examples.add(example);
                }while (cursor.moveToNext());
            }

            database.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }

        return new Result<ArrayList<Example>>(ResultStatus.TRUE, examples);
    }
}

