package com.lucasperez.iguanafixchallenge.Service;

import com.lucasperez.iguanafixchallenge.Models.Contact;
import com.lucasperez.iguanafixchallenge.Models.ContainerContacts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Lukas on 25/01/2018.
 */

public interface IguanaApiService {
    @GET("contacts")
    Call<List<Contact>> getContacts(
    );

    @GET("contacts/{contactId}")
    Call<ContainerContacts> getContactId(
        @Path("contactId") Integer contactId
    );
}
