package com.yazd;

import java.util.ArrayList;

public class DataModel {

    String name;
    int subDataCount;
    ArrayList<DataModel> subData;
    int id_;
    int image;


    public DataModel(String name, int id_, Integer image) {
        this.name = name;
        this.subDataCount = 0;
        this.id_ = id_;
        this.image=image;
    }

    public DataModel(String name, int subDataCount, ArrayList<DataModel> subData, int id_, Integer image) {
        this.name = name;
        this.subDataCount = subDataCount;
        this.subData = subData;
        this.id_ = id_;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int subDataCount() {
        return subDataCount;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }

    public ArrayList<DataModel> getSubData() {
        return subData;
    }

    public void setSubData(ArrayList<DataModel> subData) {
        this.subData = subData;
    }

    public int getSubDataCount() {
        return subDataCount;
    }

}
