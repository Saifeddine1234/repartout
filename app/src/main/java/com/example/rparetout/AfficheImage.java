package com.example.rparetout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AfficheImage extends AppCompatActivity {
    ImageView imageView;
    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_image);
        final Intent intent = getIntent();
        image =intent.getStringExtra("image");
        imageView = findViewById(R.id.imageView);
        Picasso.with(AfficheImage.this)
                .load(image)
                .fit()
                .centerInside()
                .into(imageView);


    }
}
