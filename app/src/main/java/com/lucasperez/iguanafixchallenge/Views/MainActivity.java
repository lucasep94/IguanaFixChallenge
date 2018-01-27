package com.lucasperez.iguanafixchallenge.Views;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.lucasperez.iguanafixchallenge.Models.Contact;
import com.lucasperez.iguanafixchallenge.R;
import com.lucasperez.iguanafixchallenge.Views.Fragments.DetalleContactFragment;
import com.lucasperez.iguanafixchallenge.Views.Fragments.ListadoContactFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ListadoContactFragment.NotificableDeFragmentListado{

    @BindView(R.id.MainActivity_FrameContainer) FrameLayout fragmentContainer;
    private ListadoContactFragment contactListFragment;
    private DetalleContactFragment contactDetailFragment;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(!contactListFragment.isVisible()){
            super.onBackPressed();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.contactListFragment = new ListadoContactFragment();
        this.contactDetailFragment = new DetalleContactFragment();

        this.replaceFragment(this.contactListFragment);
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.MainActivity_FrameContainer,fragment)
                .addToBackStack("fragmentMain")
                .commit();
    }

    @Override
    public void clickedContact(Contact clickedContact, List<Contact> currentList, int positionClicked) {
        Snackbar.make(fragmentContainer,"Clicked: " + clickedContact.getUserId(),Snackbar.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalleContactFragment.KEY_CONTACT,clickedContact);
        this.contactDetailFragment.setArguments(bundle);
        this.replaceFragment(this.contactDetailFragment);

    }
}
