package com.lucasperez.iguanafixchallenge.Views.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lucasperez.iguanafixchallenge.Models.Contact;
import com.lucasperez.iguanafixchallenge.R;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Lukas on 25/01/2018.
 */

public class ContactAdapter extends RecyclerView.Adapter  {

    private Context context;
    private List<Contact> contactList;
    private NotificableDeClickRecycler notificableDeClickRecycler;

    public ContactAdapter(Context context, List<Contact> contactList, NotificableDeClickRecycler notificableDeClickRecycler) {
        this.context = context;
        this.contactList = contactList;
        this.notificableDeClickRecycler = notificableDeClickRecycler;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);

        View view = inflater.inflate(R.layout.contact_item,parent, false);

        ContactViewHolder viewHolder = new ContactViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ContactViewHolder)holder).bindContact(this.contactList.get(position));

        ((ContactViewHolder)holder).celda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = contactList.get(position);
                notificableDeClickRecycler.notificarClick(contact, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void cargarNuevaLista(List<Contact> newContactList){
        //contactList.clear();
        if(newContactList!= null) {
            contactList.addAll(newContactList);
            notifyDataSetChanged();
        }
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ContactItem_TextViewFullName) TextView tvFullName;
        @BindView(R.id.ContactItem_TextViewPhone) TextView tvPhone;
        @BindView(R.id.ContactItem_CircleViewFoto) CircleImageView cvPhoto;
        private View celda;

        public ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.celda = itemView;
        }

        public void bindContact(Contact contact){
            this.tvFullName.setText(contact.getFullName());
            Glide.with(context)
                    .load(contact.getThumb())
                    .animate(R.animator.anim_loading)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(this.cvPhoto);
        }

    }

    public interface NotificableDeClickRecycler{
        public void notificarClick(Contact clickedContact, int position);
    }
}
