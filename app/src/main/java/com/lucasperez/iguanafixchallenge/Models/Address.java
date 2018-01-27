package com.lucasperez.iguanafixchallenge.Models;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("home")

    private String home;
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
