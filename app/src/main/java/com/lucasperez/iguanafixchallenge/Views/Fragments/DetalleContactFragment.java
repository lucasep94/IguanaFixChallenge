package com.lucasperez.iguanafixchallenge.Views.Fragments;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lucasperez.iguanafixchallenge.Models.Contact;
import com.lucasperez.iguanafixchallenge.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleContactFragment extends Fragment {

    public static final String KEY_CONTACT = "CONTACT_DETALLE";
    private Contact contactDetalle;

    @BindView(R.id.DetalleContactFragment_recyclerInfo) RecyclerView recyclerInfo;
    @BindView(R.id.DetalleContactFragment_ivFoto) ImageView ivFoto;
    @BindView(R.id.DetalleContactFragment_tvFullName)TextView tvFullName;
    @BindView(R.id.DetalleContactFragment_tvBirthDate)TextView tvBirthDate;
    @BindView(R.id.DetalleContactFragment_tvCreatedAt)TextView tvCreatedAt;
    @BindView(R.id.DetalleContactFragment_rootView)CoordinatorLayout rootView;


    public DetalleContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_contact, container, false);
        ButterKnife.bind(this, view);
        this.contactDetalle = (Contact) getArguments().getSerializable(this.KEY_CONTACT);
        this.loadViews();
        return view;
    }

    private void loadViews(){

        this.tvFullName.setText(this.contactDetalle.getFullName());
        this.tvBirthDate.setText(this.contactDetalle.getBirthDate().toString());
        this.tvCreatedAt.setText(this.contactDetalle.getCreatedAt().toString());
        Glide.with(this.getActivity())
                .load(this.contactDetalle.getPhoto())
                .animate(R.animator.anim_loading)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(ivFoto);
    }
}
