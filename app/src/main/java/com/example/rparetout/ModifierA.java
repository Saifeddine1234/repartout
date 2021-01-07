package com.example.rparetout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModifierA extends AppCompatActivity {
TextView code;
EditText mdp , cmdp;
ImageView flesh ;
String codeuser,mdpuser,cmdpuser ;
Button modifier;
DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);
        modifier=findViewById(R.id.modifier);
        code = findViewById(R.id.code);
        mdp=findViewById(R.id.mdp);
        cmdp=findViewById(R.id.mdp2);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifierA.this,CompteAdmin.class);
                startActivity(intent);
            }
        });
        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                codeuser = code.getText().toString();
                mdpuser=mdp.getText().toString();
                cmdpuser=cmdp.getText().toString();
                if (cmdpuser.equals("") || mdpuser.equals("")) {
                    Toast.makeText(ModifierA.this , "Remlir les champs" , Toast.LENGTH_SHORT).show();
                } else if(!codeuser.equals("12345678")) {
                    Toast.makeText(ModifierA.this, "le code 12345678 est  inchangable", Toast.LENGTH_SHORT).show();
                }else if(mdpuser.length() <= 6){
                    Toast.makeText(ModifierA.this, "saisir plus de 6  Caracteres pour le mot de passe", Toast.LENGTH_SHORT).show();
                }else if(!cmdpuser.equals(mdpuser)){
                    Toast.makeText(ModifierA.this, "verifier le mot de passe de confirmation", Toast.LENGTH_SHORT).show();
                }  else {
                    User user = new User(codeuser,mdpuser);
                    databaseReference.child(codeuser).setValue(user);

                    Toast.makeText(ModifierA.this , "le mot de passe est modifier avec succes" ,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ModifierA.this, Access.class);
                    startActivity(intent);
                }
            }

        });

    }
}
