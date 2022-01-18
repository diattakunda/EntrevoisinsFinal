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

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

public class ActivityDetail extends AppCompatActivity {

    private ImageView mImageAvatar;
    private TextView mTextImage;
    private ImageButton mButtonReturn;
    private FloatingActionButton mFab;
    private TextView mTextName;
    private TextView mTextAdress;
    private TextView mTextPhone;
    private TextView mTextSite;
    private TextView mTextAbout;
    private TextView mTextBlabla;
    private int position;
    private Neighbour neighbourName;
    private Neighbour favoriteNeighbour;
    private NeighbourApiService mApiService;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mApiService = DI.getNeighbourApiService();

        neighbourName = (Neighbour) getIntent().getSerializableExtra("Neighbour");
        position = getIntent().getIntExtra("Position", 0);


        mImageAvatar = (ImageView) findViewById(R.id.image_avatar);
        mTextImage = (TextView) findViewById(R.id.text_image);
        mButtonReturn = (ImageButton) findViewById(R.id.button_return);
        mFab = (FloatingActionButton)  findViewById(R.id.fab);
        mTextName = (TextView) findViewById(R.id.text_name);
        mTextAdress = (TextView) findViewById(R.id.text_adress);
        mTextPhone = (TextView) findViewById(R.id.text_phone);
        mTextSite = (TextView) findViewById(R.id.text_site);
        mTextAbout = (TextView) findViewById(R.id.text_about);
        mTextBlabla  = (TextView) findViewById(R.id.text_blabla);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
            });

        mButtonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

    }

    public void ButtonReturn (View view) {
        Intent intent = new Intent(ActivityDetail.this, ListNeighbourActivity.class);
        startActivity(intent);

    }




}