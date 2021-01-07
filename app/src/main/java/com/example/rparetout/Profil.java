package com.example.rparetout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profil extends AppCompatActivity {
    TextView telephone , email,nom , prenom ,change ;
    ImageView flesh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        change = findViewById(R.id.change);
        nom = findViewById(R.id.T1);
        prenom = findViewById(R.id.T2);
        email=findViewById(R.id.T3);
        telephone = findViewById(R.id.T4);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = getIntent();
                String nomC = inten.getStringExtra("nom");
                String prenomC = inten.getStringExtra("prenom");
                String telephoneC = inten.getStringExtra("telephone");
                String emailC = inten.getStringExtra("email");
                String motdepasseC = inten.getStringExtra("motdepasse");
                Intent intent5 = new Intent(getApplicationContext(), Menu.class);
                intent5.putExtra("nom", nomC);
                intent5.putExtra("prenom", prenomC);
                intent5.putExtra("telephone", telephoneC);
                intent5.putExtra("email", emailC);
                intent5.putExtra("motdepasse", motdepasseC);
                startActivity(intent5);
            } });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String nomC = intent.getStringExtra("nom");
                String prenomC = intent.getStringExtra("prenom");
                String telephoneC = intent.getStringExtra("telephone");
                String emailC = intent.getStringExtra("email");
                String motdepasseC = intent.getStringExtra("motdepasse");

                Intent intent5 = new Intent(getApplicationContext(), Change.class);
                intent5.putExtra("nom",nomC);
                intent5.putExtra("prenom",prenomC);
                intent5.putExtra("telephone",telephoneC);
                intent5.putExtra("email",emailC);
                intent5.putExtra("motdepasse",motdepasseC);
                startActivity(intent5);
            }
        });

        Intent inten = getIntent();
        String nomC= inten.getStringExtra("nom");
        String prenomC = inten.getStringExtra("prenom");
        String telephoneC = inten.getStringExtra("telephone");
        String emailC = inten.getStringExtra("email");
        String motdepasseC = inten.getStringExtra("motdepasse");
        email.setText(emailC);
        telephone.setText(telephoneC);
        nom.setText(nomC + " "+prenomC);
    }
}
