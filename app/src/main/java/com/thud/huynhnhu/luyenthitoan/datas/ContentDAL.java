package com.thud.huynhnhu.luyenthitoan.datas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.model.Content;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhu on 5/13/2016.
 */
public class ContentDAL {
    private Context context;

    /* SQLite */
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public ContentDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public Result<String> getAllContent() {
        try{
            final ArrayList<Content> arr_ConTent = new ArrayList<>();
            ParseQuery<ParseObject> query = ParseQuery.getQuery(""+Content.TABLENAME);
            query.setLimit(1000);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if ( e ==  null){
                        for (ParseObject ob : objects){
                            Content content = new Content(ob.getObjectId(),
                                    ob.getString(""+Content.NAME),
                                    ob.getInt(""+Content.POSITION),
                                    ob.getString(""+Content.CONTENT),
                                    ob.getString(""+Content.TOPICID));
                            arr_ConTent.add(content);
                        }

                        if (arr_ConTent.size() > 0){
                            Result<String> result = new AllDAL(context).saveAll(arr_ConTent);
                        }
                    }
                }
            });

            return new Result<String>(ResultStatus.TRUE, context.getResources().getString(R.string.msg_data_has_been_saved));
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, null);

            return new Result<String>(ResultStatus.FALSE, null, Def.STRING_EMPTY);
        }
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertContentFromLocal(ArrayList<Content> contents){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(Content content : contents){
                ContentValues contentDb = DbModel.getContentValueContent(content);

                //insert database
                database.insert(Content.TABLENAME, null, contentDb);
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
}

