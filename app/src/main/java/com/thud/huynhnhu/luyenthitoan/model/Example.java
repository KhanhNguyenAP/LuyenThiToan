package com.thud.huynhnhu.luyenthitoan.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Le Nhu on 12/05/2016.
 */
public class Example {
    public static final String TABLENAME = "example";
    public static final String ID = "id";
    public static final String TOPICID = "topicId";
    public static final String POSITION = "position";
    public static final String QUESTION = "question";
    public static final String ANSWER = "answer";

    public Example(String id, String topicId, int position, String question, String answer){
        this.id = id;
        this.topicId = topicId;
        this.position = position;
        this.question = question;
        this.answer = answer;
    }

    public Example(){
    }

    @SerializedName("objectId")
    private String id;

    @SerializedName("topicId")
    private String topicId;

    @SerializedName("position")
    private int position;

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

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public int getIndex() {
        return position;
    }

    public void setIndex(int position) {
        this.position = position;
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
