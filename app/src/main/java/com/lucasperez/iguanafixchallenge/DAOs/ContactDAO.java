package com.lucasperez.iguanafixchallenge.DAOs;

import com.lucasperez.iguanafixchallenge.Helpers.Constants;
import com.lucasperez.iguanafixchallenge.Helpers.ResultListener;
import com.lucasperez.iguanafixchallenge.Models.Contact;
import com.lucasperez.iguanafixchallenge.Models.ContainerContacts;
import com.lucasperez.iguanafixchallenge.Service.IguanaApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lukas on 25/01/2018.
 */

public class ContactDAO {
    private Retrofit retrofit;
    private String BaseURL = Constants.API_URL;

    public ContactDAO() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getContactList(final ResultListener<List<Contact>> listenerFromView){
        IguanaApiService service = retrofit.create(IguanaApiService.class);
        Call<List<Contact>> containerContactsCallRespuestaCall = service.getContacts();

        containerContactsCallRespuestaCall.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if (response.isSuccessful()){
                    List<Contact> contactsResponse = response.body();
                    List<Contact> contactList = contactsResponse;
                    listenerFromView.finish(contactList);
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                listenerFromView.finish(null);
            }
        });
    }

    public void getContactById(final String contactId, final ResultListener<Contact> listenerFromView){
        IguanaApiService service = retrofit.create(IguanaApiService.class);
        Call<Contact> containerContactsCallRespuestaCall = service.getContactId(contactId);

        containerContactsCallRespuestaCall.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                if (response.isSuccessful()){
                    Contact contactResponse = response.body();
                    listenerFromView.finish(contactResponse);
                }
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                listenerFromView.finish(null);
            }
        });
    }
}
