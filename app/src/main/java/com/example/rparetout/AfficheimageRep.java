package com.example.rparetout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AfficheimageRep extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_imagereparateur);
        ImageView imageView;
        String image;

            final Intent intent = getIntent();
            image =intent.getStringExtra("imager");
            imageView = findViewById(R.id.imageView);
            Picasso.with(AfficheimageRep.this)
                    .load(image)
                    .fit()
                    .centerInside()
                    .into(imageView);



    }
}
