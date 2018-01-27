package com.lucasperez.iguanafixchallenge.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Address  implements Serializable {
    @SerializedName("home")
    private String home;
    @SerializedName("work")
    private String work;

    public Address() {
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
