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
import com.thud.huynhnhu.luyenthitoan.model.Example;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.model.Topic;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NhuLe on 5/13/2016.
 */
public class TopicDAL {
    private Context context;

    /* SQLite */
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public TopicDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
    }

    public Result<ArrayList<Topic>> getAllTopic() {
        final ArrayList<Topic> arr_Topic = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+Topic.TABLENAME);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        Topic topic = new Topic(ob.getObjectId(),
                                ob.getString(""+Topic.NAME),
                                ob.getInt(""+Topic.ISBASIC),
                                ob.getInt(""+Topic.ISALGEBRA),
                                ob.getString(""+Topic.REFERENCESQUESTION),
                                ob.getParseFile(""+Topic.IMAGE));
                        arr_Topic.add(topic);
                    }

                    if (arr_Topic.size() >0){
                        Result<String> result = new AllDAL(context).saveAll(arr_Topic);
                    }
                }
            }
        });

        return new Result<ArrayList<Topic>>(ResultStatus.TRUE, arr_Topic);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertTopicFromLocal(ArrayList<Topic> topics){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(Topic topic : topics){
                ContentValues topicDb = DbModel.getContentValueTopic(topic);

                //insert database
                database.insert(Topic.TABLENAME, null, topicDb);
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

    public Result<ArrayList<Topic>> getAllTopicFromLocalIsAlgebra(int isBasic, int isAlgebra){
        database = dbHelper.getReadableDatabase();
        ArrayList<Topic> topics = new ArrayList<>();
        try {
            String query_topic = "SELECT * FROM " + Topic.TABLENAME + " WHERE "
                                    + Topic.ISBASIC + " = " +isBasic + " AND "
                                    + Topic.ISALGEBRA + " = " +isAlgebra;
            Cursor cursor = database.rawQuery(query_topic, null);
            if(cursor != null && cursor.moveToFirst()){
                do{
                    Topic topic = DbModel.getTopic(cursor);
                    topics.add(topic);
                }while (cursor.moveToNext());
            }

            database.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
            Log.e(Def.ERROR, ex.getMessage());
        }
        return  new Result<ArrayList<Topic>>(ResultStatus.TRUE, topics);
    }
}

