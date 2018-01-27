package com.lucasperez.iguanafixchallenge.Models;

import com.google.gson.annotations.SerializedName;

public class Phone {

    @SerializedName("type")
    private String type;
    @SerializedName("number")
    private String number;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}