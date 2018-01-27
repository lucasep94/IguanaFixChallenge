package com.lucasperez.iguanafixchallenge.Views.Fragments;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lucasperez.iguanafixchallenge.Controllers.ContactController;
import com.lucasperez.iguanafixchallenge.Helpers.ResultListener;
import com.lucasperez.iguanafixchallenge.Models.Address;
import com.lucasperez.iguanafixchallenge.Models.Contact;
import com.lucasperez.iguanafixchallenge.Models.Phone;
import com.lucasperez.iguanafixchallenge.R;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleContactFragment extends Fragment {

    public static final String KEY_CONTACT = "CONTACT_DETALLE";
    private Contact contactDetalle;
    private String contactId;

    @BindView(R.id.DetalleContactFragment_ivFoto) ImageView ivFoto;
    @BindView(R.id.DetalleContactFragment_tvFullName)TextView tvFullName;
    @BindView(R.id.DetalleContactFragment_tvBirthDate)TextView tvBirthDate;
    @BindView(R.id.DetalleContactFragment_tvCreatedAt)TextView tvCreatedAt;
    @BindView(R.id.DetalleContactFragment_rootView)CoordinatorLayout rootView;
    @BindView(R.id.DetalleContactFragment_LinearContent)LinearLayout linearContentView;
    private Unbinder unbinder;
    private ContactController contactController;


    public DetalleContactFragment() {
        // Required empty public constructor
        contactController = new ContactController();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_contact, container, false);
        unbinder = ButterKnife.bind(this, view);
        this.contactId = getArguments().getString(this.KEY_CONTACT);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        contactController.getContactById(this.contactId, new ResultListener<Contact>() {
            @Override
            public void finish(Contact resultado) {
                contactDetalle = resultado;
                loadViews();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void loadViews(){

        this.tvFullName.setText(this.contactDetalle.getFullName());
        this.tvBirthDate.setText(this.contactDetalle.getBirthDateFormatted().toString());
        this.tvCreatedAt.setText(this.contactDetalle.getCreatedAtFormatted().toString());
        Glide.with(this.getActivity())
                .load(this.contactDetalle.getPhoto())
                .animate(R.animator.anim_loading)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(ivFoto);

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);

        for (Phone phone : this.contactDetalle.getPhones()){
            View cardView = inflater.inflate(R.layout.detail_item, null);

            TextView tvType = cardView.findViewById(R.id.DetailItem_tvType);
            TextView tvValue = cardView.findViewById(R.id.DetailItem_tvValue);
            ImageView ivType = cardView.findViewById(R.id.DetailItem_ivType);

            if(phone.getNumber() != null) {
                tvType.setText(phone.getType());
                tvValue.setText(phone.getNumber());
                ivType.setImageResource(R.drawable.ic_phone);
                this.linearContentView.addView(cardView);
            }
        }

        for (Address address : this.contactDetalle.getAddresses()){

            if(address.getHome() != null){

                View cardView = inflater.inflate(R.layout.detail_item, null);
                TextView tvType = cardView.findViewById(R.id.DetailItem_tvType);
                TextView tvValue = cardView.findViewById(R.id.DetailItem_tvValue);
                ImageView ivType = cardView.findViewById(R.id.DetailItem_ivType);
                ivType.setImageResource(R.drawable.ic_address);

                tvType.setText(getContext().getResources().getString(R.string.detail_address_type_home));
                tvValue.setText(address.getHome());
                this.linearContentView.addView(cardView);
            }
            if(address.getWork() != null){
                View cardView = inflater.inflate(R.layout.detail_item, null);
                TextView tvType = cardView.findViewById(R.id.DetailItem_tvType);
                TextView tvValue = cardView.findViewById(R.id.DetailItem_tvValue);
                ImageView ivType = cardView.findViewById(R.id.DetailItem_ivType);
                ivType.setImageResource(R.drawable.ic_address);

                tvType.setText(getContext().getResources().getString(R.string.detail_address_type_work));
                tvValue.setText(address.getWork());
                this.linearContentView.addView(cardView);
            }


        }
    }
}
