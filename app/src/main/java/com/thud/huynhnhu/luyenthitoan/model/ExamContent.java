package com.thud.huynhnhu.luyenthitoan.model;

import com.google.gson.annotations.SerializedName;
import com.parse.ParseFile;

/**
 * Created by Le Nhu on 12/05/2016.
 */
public class ExamContent {
    public static final String TABLENAME = "examContent";
    public static final String ID = "id";
    public static final String ANSWER = "anwser";
    public static final String QUESTION = "question";
    public static final String EXAMID = "examId";
    public static final String IMAGE = "image";

    public ExamContent(String id, String anwser, String question,String examId, ParseFile image){
        this.id = id;
        this.anwser = anwser;
        this.question = question;
        this.examId = examId;
        this.image = image;
    }

    public ExamContent(){
    }

    @SerializedName("objectId")
    private String id;

    @SerializedName("anwser")
    private String anwser;

    @SerializedName("question")
    private String question;

    @SerializedName("examId")
    private String examId;

    @SerializedName("image")
    private ParseFile image;

    private String img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnwser() {
        return anwser;
    }

    public void setAnwser(String anwser) {
        this.anwser = anwser;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public ParseFile getImage() {
        return image;
    }

    public void setImage(ParseFile image) {
        this.image = image;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
