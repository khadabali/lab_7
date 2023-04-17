package com.starwars.lab7;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StarWarApiResp implements Serializable {

    @SerializedName("count")
    private int count;

    @SerializedName("next")
    private String next;

    @SerializedName("previous")
    private Object previous;

    @SerializedName("results")
    private ArrayList<Result> results;

    public void setCount(int count){
        this.count = count;
    }

    public int getCount(){
        return count;
    }

    public void setNext(String next){
        this.next = next;
    }

    public String getNext(){
        return next;
    }

    public void setPrevious(Object previous){
        this.previous = previous;
    }

    public Object getPrevious(){
        return previous;
    }

    public void setResults(ArrayList<Result> results){
        this.results = results;
    }

    public ArrayList<Result> getResults(){
        return results;
    }
}
