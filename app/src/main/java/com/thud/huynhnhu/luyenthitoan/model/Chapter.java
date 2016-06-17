package com.thud.huynhnhu.luyenthitoan.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Le Nhu on 12/05/2016.
 */
public class Chapter {
    public static final String TABLENAME = "chapter";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ISBASIC = "isBasic";
    public static final String ISALGEBRA = "isAlgebra";

    public Chapter(String id, String name, int isBasic, int isAlgebra){
        this.id = id;
        this.name = name;
        this.isBasic =  isBasic;
        this.isAlgebra = isAlgebra;
    }

    public Chapter(){
    }

    @SerializedName("objectId")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("isBasic")
    private int isBasic;

    @SerializedName("isAlgebra")
    private int isAlgebra;

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
}
