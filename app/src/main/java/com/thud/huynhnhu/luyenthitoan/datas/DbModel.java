package com.thud.huynhnhu.luyenthitoan.datas;

import android.content.ContentValues;
import android.database.Cursor;

import com.thud.huynhnhu.luyenthitoan.model.Content;
import com.thud.huynhnhu.luyenthitoan.model.Exam;
import com.thud.huynhnhu.luyenthitoan.model.ExamContent;
import com.thud.huynhnhu.luyenthitoan.model.Example;
import com.thud.huynhnhu.luyenthitoan.model.Topic;

/**
 * Created by NhuLe
 */
public class DbModel {
    public static ContentValues getContentValueContent(Content content){
        ContentValues contentDb = new ContentValues();
        contentDb.put(Content.ID, content.getId());
        contentDb.put(Content.POSITION, content.getPosition());
        contentDb.put(Content.CONTENT, content.getContent());
        contentDb.put(Content.NAME, content.getName());
        contentDb.put(Content.TOPICID, content.getTopicId());

        return contentDb;
    }

    public static Content getContent(Cursor cursor){
        Content content = new Content();
        content.setId(cursor.getString(cursor.getColumnIndex(Content.ID)));
        content.setTopicId(cursor.getString(cursor.getColumnIndex(Content.TOPICID)));
        content.setName(cursor.getString(cursor.getColumnIndex(Content.NAME)));
        content.setPosition(cursor.getInt(cursor.getColumnIndex(Content.POSITION)));
        content.setContent(cursor.getString(cursor.getColumnIndex(Content.CONTENT)));

        return content;
    }

    /*----------Exam----------*/

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

    public static Exam getExam(Cursor cursor){
        Exam exam = new Exam();
        exam.setId(cursor.getString(cursor.getColumnIndex(Exam.ID)));
        exam.setAnswer(cursor.getString(cursor.getColumnIndex(Exam.ANSWER)));
        exam.setIndex(cursor.getInt(cursor.getColumnIndex(Exam.POSITION)));
        exam.setInfo(cursor.getString(cursor.getColumnIndex(Exam.INFO)));
        exam.setName(cursor.getString(cursor.getColumnIndex(Exam.NAME)));
        exam.setQuestion(cursor.getString(cursor.getColumnIndex(Exam.QUESTION)));

        return exam;
    }

    /*----------Example----------*/
    public static ContentValues getContentValueExample(Example example){
        ContentValues exampleDb = new ContentValues();
        exampleDb.put(Example.ID, example.getId());
        exampleDb.put(Example.POSITION, example.getIndex());
        exampleDb.put(Example.TOPICID, example.getTopicId());
        exampleDb.put(Example.QUESTION, example.getQuestion());
        exampleDb.put(Example.ANSWER, example.getAnswer());

        return exampleDb;
    }

    public static Example getExample(Cursor cursor){
        Example example = new Example();
        example.setId(cursor.getString(cursor.getColumnIndex(Example.ID)));
        example.setIndex(cursor.getInt(cursor.getColumnIndex(Example.POSITION)));
        example.setTopicId(cursor.getString(cursor.getColumnIndex(Example.TOPICID)));
        example.setQuestion(cursor.getString(cursor.getColumnIndex(Example.QUESTION)));
        example.setAnswer(cursor.getString(cursor.getColumnIndex(Example.ANSWER)));

        return example;
    }

    /*----------Topic----------*/
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

    /*----------Topic----------*/
    public static ContentValues getContentValueExamContent(ExamContent examContent){
        ContentValues examContentDb = new ContentValues();
        examContentDb.put(ExamContent.ID, examContent.getId());
        examContentDb.put(ExamContent.ANSWER, examContent.getAnwser());
        examContentDb.put(ExamContent.QUESTION, examContent.getQuestion());
        examContentDb.put(ExamContent.EXAMID, examContent.getExamId());
        examContentDb.put(ExamContent.IMAGE, String.valueOf(examContent.getImage().getUrl()));
        return examContentDb;
    }

    public static ExamContent getExamContent(Cursor cursor){
        ExamContent examContent = new ExamContent();
        examContent.setId(cursor.getString(cursor.getColumnIndex(ExamContent.ID)));
        examContent.setAnwser(cursor.getString(cursor.getColumnIndex(ExamContent.ANSWER)));
        examContent.setQuestion(cursor.getString(cursor.getColumnIndex(ExamContent.QUESTION)));
        examContent.setExamId(cursor.getString(cursor.getColumnIndex(ExamContent.EXAMID)));
        examContent.setImg(cursor.getString(cursor.getColumnIndex(ExamContent.IMAGE)));

        return examContent;
    }
}
