package com.example.rparetout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilReparateur extends AppCompatActivity {
    TextView telephone , cin ,type,nom ,change  ;
    CircleImageView image;
    ImageView flesh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_rep);
        change = findViewById(R.id.change);
        cin = findViewById(R.id.T1);
        nom = findViewById(R.id.T2);
        type=findViewById(R.id.T3);
        telephone = findViewById(R.id.T4);
        image = findViewById(R.id.imageView19);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = getIntent();
                String nomR = inten.getStringExtra("nomrep");
                String prenomR = inten.getStringExtra("prenomrep");
                String telephoneR = inten.getStringExtra("telephonerep");
                String cinR = inten.getStringExtra("cinrep");
                String motdepasseR = inten.getStringExtra("mdprep");
                String typeR = inten.getStringExtra("typerep");
                String imageR = inten.getStringExtra("imagerep");
                Intent intent5 = new Intent(getApplicationContext(), MenuClient.class);
                intent5.putExtra("nomrep", nomR);
                intent5.putExtra("prenomrep", prenomR);
                intent5.putExtra("telephonerep", telephoneR);
                intent5.putExtra("cinrep", cinR);
                intent5.putExtra("typerep", typeR);
                intent5.putExtra("mdprep", motdepasseR);
                intent5.putExtra("imagerep", imageR);
                startActivity(intent5);
            } });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String nomR = intent.getStringExtra("nomrep");
                String prenomR = intent.getStringExtra("prenomrep");
                String telephoneR = intent.getStringExtra("telephonerep");
                String cinR = intent.getStringExtra("cinrep");
                String typeR = intent.getStringExtra("typerep");
                String motdepasseR = intent.getStringExtra("mdprep");
                String imageR = intent.getStringExtra("imagerep");

                Intent intent5 = new Intent(getApplicationContext(), ChangeReparateurMP.class);
                intent5.putExtra("nomrep",nomR);
                intent5.putExtra("prenomrep",prenomR);
                intent5.putExtra("telephonerep",telephoneR);
                intent5.putExtra("cinrep",cinR);
                intent5.putExtra("typerep",typeR);
                intent5.putExtra("mdprep",motdepasseR);
                intent5.putExtra("imagerep",imageR);
                startActivity(intent5);

            }
        });

        Intent inten = getIntent();
        String nomR= inten.getStringExtra("nomrep");
        String prenomR = inten.getStringExtra("prenomrep");
        String typeR = inten.getStringExtra("typerep");
        String telephoneR = inten.getStringExtra("telephonerep");
        String cinR = inten.getStringExtra("cinrep");
        String motdepasseR = inten.getStringExtra("mdprep");
        String imageR = inten.getStringExtra("imagerep");
        cin.setText(cinR);
        telephone.setText(telephoneR);
        nom.setText(nomR +" "+ prenomR);
        type.setText( typeR);
        Picasso.with(ProfilReparateur.this)
                .load(imageR)
                .fit()
                .centerInside()
                .into(image);






    }
}
