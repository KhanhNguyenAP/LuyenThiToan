package com.thud.huynhnhu.luyenthitoan.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Le Nhu on 12/05/2016.
 */
public class Exam {
    public static final String TABLENAME = "exam";
    public static final String ID = "id";
    public static final String POSITION = "position";
    public static final String NAME = "name";
    public static final String INFO = "info";
    public static final String QUESTION = "question";
    public static final String ANSWER = "answer";

    public Exam(String id, int position, String name, String info, String question, String answer){
        this.id = id;
        this.position = position;
        this.name = name;
        this.info = info;
        this.question = question;
        this.answer = answer;
    }

    @SerializedName("objectId")
    private String id;

    @SerializedName("position")
    private int position;

    @SerializedName("name")
    private String name;

    @SerializedName("info")
    private String info;

    @SerializedName("question")
    private String question;

    @SerializedName("answer")
    private String answer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return position;
    }

    public void setIndex(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}