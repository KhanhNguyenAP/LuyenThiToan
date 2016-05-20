package com.thud.huynhnhu.luyenthitoan.datas;

import android.content.ContentValues;
import android.database.Cursor;

import com.thud.huynhnhu.luyenthitoan.model.Content;
import com.thud.huynhnhu.luyenthitoan.model.Exam;
import com.thud.huynhnhu.luyenthitoan.model.Example;
import com.thud.huynhnhu.luyenthitoan.model.Topic;

/**
 * Created by nhu
 */
public class DbModel {
    public static ContentValues getContentValueContent(Content content){
        ContentValues contentDb = new ContentValues();
        contentDb.put(Content.ID, content.getId());
        contentDb.put(Content.POSITION, content.getIndex());
        contentDb.put(Content.CONTENT, content.getContent());
        contentDb.put(Content.NAME, content.getName());
        contentDb.put(Content.TOPICID, content.getTopicId());

        return contentDb;
    }

    public static ContentValues getContentValueExam(Exam exam){
        ContentValues examDb = new ContentValues();
        examDb.put(Exam.ID, exam.getId());
        examDb.put(Exam.POSITION, exam.getIndex());
        examDb.put(Exam.NAME, exam.getName());
        examDb.put(Exam.INFO, exam.getInfo());
        examDb.put(Exam.QUESTION, exam.getQuestion());
        examDb.put(Exam.ANSWER, exam.getAnswer());

        return examDb;
    }

    public static ContentValues getContentValueExample(Example example){
        ContentValues exampleDb = new ContentValues();
        exampleDb.put(Example.ID, example.getId());
        exampleDb.put(Example.POSITION, example.getIndex());
        exampleDb.put(Example.TOPICID, example.getTopicId());
        exampleDb.put(Example.QUESTION, example.getQuestion());
        exampleDb.put(Example.ANSWER, example.getAnswer());

        return exampleDb;
    }

    public static ContentValues getContentValueTopic(Topic topic){
        ContentValues topicDb = new ContentValues();
        topicDb.put(Topic.ID, topic.getId());
        topicDb.put(Topic.IMAGE, String.valueOf(topic.getImage().getUrl()));
        topicDb.put(Topic.ISALGEBRA, topic.getIsAlgebra());
        topicDb.put(Topic.ISBASIC, topic.getIsBasic());
        topicDb.put(Topic.NAME, topic.getName());
        topicDb.put(Topic.REFERENCESQUESTION, topic.getReferencesQuestion());

        return topicDb;
    }

    public static Topic getTopic(Cursor cursor){
        Topic topic = new Topic();
        topic.setId(cursor.getString(cursor.getColumnIndex(Topic.ID)));
        topic.setName(cursor.getString(cursor.getColumnIndex(Topic.NAME)));
        topic.setIsBasic(cursor.getInt(cursor.getColumnIndex(Topic.ISBASIC)));
        topic.setIsAlgebra(cursor.getInt(cursor.getColumnIndex(Topic.ISALGEBRA)));
        topic.setImg(cursor.getString(cursor.getColumnIndex(Topic.IMAGE)));
        topic.setReferencesQuestion(cursor.getString(cursor.getColumnIndex(Topic.REFERENCESQUESTION)));

        return topic;
    }

}
