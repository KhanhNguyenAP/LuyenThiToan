package com.thud.huynhnhu.luyenthitoan.datas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.thud.huynhnhu.luyenthitoan.model.Content;
import com.thud.huynhnhu.luyenthitoan.model.Exam;
import com.thud.huynhnhu.luyenthitoan.model.Example;
import com.thud.huynhnhu.luyenthitoan.model.Topic;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Def;

/**
 * Created by conheo on 12/05/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "luyenthitoan.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase database;

    public static final String sql_content = "CREATE TABLE IF NOT EXISTS " + Content.TABLENAME + "(" +
            Content.ID + " TEXT PRIMARY KEY, " +
            Content.TOPICID + " TEXT, " +
            Content.NAME + " TEXT, " +
            Content.POSITION + " INTEGER, " +
            Content.CONTENT + " TEXT)";

    public static final String sql_exam = "CREATE TABLE IF NOT EXISTS " + Exam.TABLENAME + "(" +
            Exam.ID + " TEXT PRIMARY KEY, " +
            Exam.POSITION + " INTEGER, " +
            Exam.NAME + " TEXT, " +
            Exam.INFO + " TEXT, " +
            Exam.QUESTION + " TEXT, " +
            Exam.ANSWER + " TEXT )";

    public static final String sql_example = "CREATE TABLE IF NOT EXISTS " + Example.TABLENAME + "(" +
            Example.ID + " TEXT PRIMARY KEY, " +
            Example.TOPICID + " TEXT, " +
            Example.POSITION + " INTEGER, " +
            Example.QUESTION + " TEXT, " +
            Example.ANSWER + " TEXT )";

    public static final String sql_topic = "CREATE TABLE IF NOT EXISTS " + Topic.TABLENAME + "(" +
            Topic.ID + " TEXT PRIMARY KEY, " +
            Topic.NAME + " TEXT, " +
            Topic.ISBASIC + " INTEGER, " +
            Topic.ISALGEBRA + " INTEGER, " +
            Topic.REFERENCESQUESTION + " TEXT, " +
            Topic.IMAGE + " TEXT )";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        try {
            database = this.getWritableDatabase();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try{
            db.execSQL(sql_exam);
            db.execSQL(sql_example);
            db.execSQL(sql_topic);
            db.execSQL(sql_content);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + Content.TABLENAME);
            db.execSQL("DROP TABLE IF EXISTS " + Exam.TABLENAME);
            db.execSQL("DROP TABLE IF EXISTS " + Example.TABLENAME);
            db.execSQL("DROP TABLE IF EXISTS " + Topic.TABLENAME);
            onCreate(db);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }
    }

    /*
    * Drop all table
    * */
    public void DropAllTable(SQLiteDatabase db){
        try {
            db.execSQL("DROP TABLE IF EXISTS " + Content.TABLENAME);
            db.execSQL("DROP TABLE IF EXISTS " + Exam.TABLENAME);
            db.execSQL("DROP TABLE IF EXISTS " + Example.TABLENAME);
            db.execSQL("DROP TABLE IF EXISTS " + Topic.TABLENAME);
            onCreate(db);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }
    }

    /*
    * Drop and Create table
    * */
    public void DropAndCreateTable(SQLiteDatabase db, String oldTable, String newTable){
        try{
            db.execSQL("DROP TABLE IF EXISTS " + oldTable);
            db.execSQL(newTable);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }
    }//--end DropAllTable
}
