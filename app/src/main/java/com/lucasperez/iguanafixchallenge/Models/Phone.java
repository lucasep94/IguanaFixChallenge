package com.lucasperez.iguanafixchallenge.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Phone  implements Serializable {

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}