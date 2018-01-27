package com.lucasperez.iguanafixchallenge.Models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Contact implements Serializable{
    @SerializedName("user_id")
    private String userId;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("birth_date")
    private Date birthDate;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("phones")
    private List<Phone> phones;
    @SerializedName("thumb")
    private String thumb;
    @SerializedName("photo")
    private String photo;
    @SerializedName("addresses")
    private List<Address> addresses;

    public Contact() {
    }

    public String getUserId() {
        return userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public String getThumb() {
        return thumb;
    }

    public String getPhoto() {
        return photo;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }
}
