package com.example.rparetout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int SPLASH_SCREEN = 4000 ;
    Animation topanim , imganim , botanim ,autreanim ;
    TextView T1 , T2 , T3 ;
    ImageView image ;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        topanim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.top_animation);
        botanim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bottom_animation);
        imganim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.image_animation);
        autreanim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.autre_animation);

        image = findViewById(R.id.imageView);
        T1 = findViewById(R.id.T1);
        T2 = findViewById(R.id.T2);
        T3 = findViewById(R.id.T3);
        T1.setAnimation(botanim);
        T2.setAnimation(topanim);
        image.setAnimation(imganim);
        T3.setAnimation(autreanim);



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                }

        },SPLASH_SCREEN);
    }

    @Override
    public void onBackPressed() {

    }
}









