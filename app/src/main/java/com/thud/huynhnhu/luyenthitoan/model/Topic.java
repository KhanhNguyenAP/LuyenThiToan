package com.thud.huynhnhu.luyenthitoan.model;

import com.google.gson.annotations.SerializedName;
import com.parse.ParseFile;

/**
 * Created by Le Nhu on 12/05/2016.
 */
public class Topic {
    public static final String TABLENAME = "topic";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ISBASIC = "isBasic";
    public static final String ISALGEBRA = "isAlgebra";
    public static final String REFERENCESQUESTION = "referencesQuestion";
    public static final String IMAGE = "image";

    public Topic(String id, String name, int isBasic, int isAlgebra, String referencesQuestion, ParseFile image){
        this.id = id;
        this.name = name;
        this.isBasic = isBasic;
        this.isAlgebra = isAlgebra;
        this.referencesQuestion = referencesQuestion;
        this.image = image;
    }

    @SerializedName("objectId")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("isBasic")
    private int isBasic;

    @SerializedName("isAlgebra")
    private int isAlgebra;

    @SerializedName("referencesQuestion")
    private String referencesQuestion;

    @SerializedName("image")
    private ParseFile image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsBasic() {
        return isBasic;
    }

    public void setIsBasic(int isBasic) {
        this.isBasic = isBasic;
    }

    public int getIsAlgebra() {
        return isAlgebra;
    }

    public void setIsAlgebra(int isAlgebra) {
        this.isAlgebra = isAlgebra;
    }

    public String getReferencesQuestion() {
        return referencesQuestion;
    }

    public void setReferencesQuestion(String referencesQuestion) {
        this.referencesQuestion = referencesQuestion;
    }

    public ParseFile getImage() {
        return image;
    }

    public void setImage(ParseFile image) {
        this.image = image;
    }
}
