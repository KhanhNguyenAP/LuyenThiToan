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
import com.thud.huynhnhu.luyenthitoan.model.Chapter;
import com.thud.huynhnhu.luyenthitoan.model.Content;
import com.thud.huynhnhu.luyenthitoan.model.Result;
import com.thud.huynhnhu.luyenthitoan.model.ResultStatus;
import com.thud.huynhnhu.luyenthitoan.model.Topic;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NhuLe on 5/13/2016.
 */
public class ChapterDAL {
    private Context context;

    /* SQLite */
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public ChapterDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public Result<String> getAllChapter() {
        try{
            final ArrayList<Chapter> arr_Chapter = new ArrayList<>();
            ParseQuery<ParseObject> query = ParseQuery.getQuery(""+Chapter.TABLENAME);
            query.setLimit(1000);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if ( e ==  null){
                        for (ParseObject ob : objects){
                            Chapter chapter = new Chapter(ob.getObjectId(),
                                    ob.getString(""+Chapter.NAME),
                                    ob.getInt(""+Chapter.ISBASIC),
                                    ob.getInt(""+Chapter.ISALGEBRA));
                            arr_Chapter.add(chapter);
                        }

                        if (arr_Chapter.size() > 0){
                            Result<String> result = new AllDAL(context).saveAll(arr_Chapter);
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
    public Result<String> insertChapterFromLocal(ArrayList<Chapter> chapters){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(Chapter chapter : chapters){
                ContentValues chapterDb = DbModel.getChapterValueContent(chapter);

                //insert database
                database.insert(Chapter.TABLENAME, null, chapterDb);
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

    public Result<ArrayList<Chapter>> getAllChapterFromLocalIsAlgebra(int isBasic, int isAlgebra){
        database = dbHelper.getReadableDatabase();
        ArrayList<Chapter> chapters = new ArrayList<>();
        try {
            String query_chapter = "SELECT * FROM " + Chapter.TABLENAME + " WHERE "
                    + Chapter.ISBASIC + " = " +isBasic + " AND "
                    + Chapter.ISALGEBRA + " = " +isAlgebra;
            Cursor cursor = database.rawQuery(query_chapter, null);
            if(cursor != null && cursor.moveToFirst()){
                do{
                    Chapter chapter = DbModel.getChapter(cursor);
                    chapters.add(chapter);
                }while (cursor.moveToNext());
            }

            database.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
            Log.e(Def.ERROR, ex.getMessage());
        }
        return  new Result<ArrayList<Chapter>>(ResultStatus.TRUE, chapters);
    }
}

