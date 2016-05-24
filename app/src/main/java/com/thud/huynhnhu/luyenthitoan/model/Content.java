package com.thud.huynhnhu.luyenthitoan.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Le Nhu on 12/05/2016.
 */
public class Content {
    public static final String TABLENAME = "content";
    public static final String ID = "id";
    public static final String TOPICID = "topicId";
    public static final String NAME = "name";
    public static final String POSITION = "position";
    public static final String CONTENT = "content";

    public Content(String id, String name, int position, String content, String topicId){
        this.id = id;
        this.name = name;
        this.position = position;
        this.content = content;
        this.topicId = topicId;
    }

    public Content(){
    }

    @SerializedName("objectId")
    private String id;

    @SerializedName("topicId")
    private String topicId;

    @SerializedName("name")
    private String name;

    @SerializedName("position")
    private int position;

    @SerializedName("content")
    private String content;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return position;
    }

    public void setIndex(int position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
