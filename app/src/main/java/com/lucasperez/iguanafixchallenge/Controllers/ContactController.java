package com.lucasperez.iguanafixchallenge.Controllers;

import com.lucasperez.iguanafixchallenge.DAOs.ContactDAO;
import com.lucasperez.iguanafixchallenge.Helpers.ResultListener;
import com.lucasperez.iguanafixchallenge.Models.Contact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lukas on 25/01/2018.
 */

public class ContactController {

    public void getContactList(ResultListener<List<Contact>> listenerFromView){
        ContactDAO dao = new ContactDAO();
        dao.getContactList(listenerFromView);
    }

    public void getContactById(String contactId, ResultListener<Contact> listenerFromView){
        ContactDAO dao = new ContactDAO();
        dao.getContactById(contactId, listenerFromView);
    }

}
