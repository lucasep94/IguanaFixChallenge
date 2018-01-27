package com.lucasperez.iguanafixchallenge.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucasperez.iguanafixchallenge.Controllers.ContactController;
import com.lucasperez.iguanafixchallenge.Helpers.ResultListener;
import com.lucasperez.iguanafixchallenge.Helpers.SimpleDividerItemDecoration;
import com.lucasperez.iguanafixchallenge.Models.Contact;
import com.lucasperez.iguanafixchallenge.R;
import com.lucasperez.iguanafixchallenge.Views.Adapters.ContactAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ListadoContactFragment extends Fragment implements ContactAdapter.NotificableDeClickRecycler {

    private List<Contact> contactList = new ArrayList<>();
    private ContactAdapter adapter;

    @BindView(R.id.ListadoFragment_RecyclerList) RecyclerView recyclerPedidos;
    private Unbinder unbinder;

    public ListadoContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listado, container, false);
        unbinder = ButterKnife.bind(this, view);

        NotificableDeFragmentListado notificable = (NotificableDeFragmentListado) getActivity();

        this.adapter = new ContactAdapter(this.getActivity(),this.contactList,this);
        this.getListado();

        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.ListadoFragment_RecyclerList);
        recycler.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(layoutManager);

        recycler.addItemDecoration(new SimpleDividerItemDecoration(
                getContext()
        ));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void getListado(){
        ContactController controller = new ContactController();
        controller.getContactList(new ResultListener<List<Contact>>() {
            @Override
            public void finish(List<Contact> resultado) {
                adapter.cargarNuevaLista(resultado);
            }
        });
    }

    @Override
    public void notificarClick(Contact clickedContact, int position) {
        NotificableDeFragmentListado parent = (NotificableDeFragmentListado)this.getActivity();
        parent.clickedContact(clickedContact, contactList, position);
    }

    public interface NotificableDeFragmentListado{
        void clickedContact(Contact clickedContact, List<Contact> currentList,int positionClicked);
    }
}
