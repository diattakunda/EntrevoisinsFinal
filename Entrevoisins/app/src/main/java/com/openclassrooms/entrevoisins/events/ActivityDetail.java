package com.openclassrooms.entrevoisins.events;

import android.content.Intent;
import android.support.design.animation.Positioning;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

public class ActivityDetail extends AppCompatActivity {

    private ImageView mImageAvatar;
    private TextView mTextImage;
    private ImageButton mButtonReturn;
    private ImageButton mFab;
    private TextView mTextName;
    private TextView mTextAdress;
    private TextView mTextPhone;
    private TextView mTextSite;
    private TextView mTextAbout;
    private TextView mTextBlabla;
    private Neighbour neighbourName;
    private Neighbour favoriteNeighbour;
    private NeighbourApiService mApiService;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mApiService = DI.getNeighbourApiService();

        neighbourName = (Neighbour) getIntent().getSerializableExtra("Neighbour");


        mImageAvatar = (ImageView) findViewById(R.id.image_avatar);
        mTextImage = (TextView) findViewById(R.id.text_image);
        mButtonReturn = (ImageButton) findViewById(R.id.button_return);
        mFab = (ImageButton)  findViewById(R.id.fab);
        mTextName = (TextView) findViewById(R.id.text_name);
        mTextAdress = (TextView) findViewById(R.id.text_adress);
        mTextPhone = (TextView) findViewById(R.id.text_phone);
        mTextSite = (TextView) findViewById(R.id.text_site);
        mTextAbout = (TextView) findViewById(R.id.text_about);
        mTextBlabla  = (TextView) findViewById(R.id.text_blabla);


        if (mApiService.getFavoritesNeighbours().contains(neighbourName)){
            mFab.setImageResource(R.drawable.ic_baseline_star_24);
        }else{
            mFab.setImageResource(R.drawable.ic_star_white_24dp);
        }

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mApiService.getFavoritesNeighbours().contains(favoriteNeighbour)) {
                    mApiService.createFavoritesNeighbours(favoriteNeighbour);
                    mFab.setImageResource(R.drawable.ic_baseline_star_24);
                }else{
                    mFab.setImageResource(R.drawable.ic_star_white_24dp);
                }

            }
            });

        //création méthodes qui récupèrent infos
        validateProfile();
        linkProfile();

        mButtonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

    }
    public void validateProfile() {
        //message qui affiche la récuperation du nom
        Toast.makeText(getApplicationContext(), neighbourName.getName(), Toast.LENGTH_LONG).show();
    }

    public void linkProfile() {
        // permet d'associer le layout avec les informations de la classe "model"
        //favoriteNeighbour = mApiService.getFavoritesNeighbours(favoriteNeighbour);
        Glide.with(this).load(neighbourName.getAvatarUrl()).into(mImageAvatar);
        mTextImage.setText(neighbourName.getName());
        mTextName.setText(neighbourName.getName());
        mTextAdress.setText(neighbourName.getAddress());
        mTextPhone.setText(neighbourName.getPhoneNumber());
        mTextSite.setText(String.format("%s%s","www.facebook.com/",neighbourName.getName().toLowerCase()));
        mTextBlabla.setText(neighbourName.getAboutMe());


    }

    public void ButtonReturn (View view) {
        Intent intent = new Intent(this, ListNeighbourActivity.class);
        startActivity(intent);


    }
}