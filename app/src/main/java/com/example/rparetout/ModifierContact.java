package com.example.rparetout;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModifierContact extends AppCompatActivity {
    EditText telA , emailA;
    String teladmin,emailadmin ;
    ImageView flesh ;
    Button modifier;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_contact);
        modifier=findViewById(R.id.modifier);
        flesh=findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifierContact.this,CompteAdmin.class);
                startActivity(intent);
            }
        });
        telA=findViewById(R.id.telAdmin);
        emailA=findViewById(R.id.emailAdmin);
        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Contact");
                teladmin = telA.getText().toString();
                emailadmin=emailA.getText().toString();
                if (teladmin.equals("") || emailadmin.equals("")) {
                    Toast.makeText(ModifierContact.this , "saisir votre données" , Toast.LENGTH_SHORT).show();
                } else if(teladmin.length() != 8) {
                    Toast.makeText(ModifierContact.this, "saisir le numero correctement", Toast.LENGTH_SHORT).show();
                }  else {
                    ContactM contact = new ContactM(teladmin,emailadmin);
                    databaseReference.child("admin").setValue(contact);

                    Toast.makeText(ModifierContact.this , "modification avec success" ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ModifierContact.this, CompteAdmin.class);
                    startActivity(intent);
                }
            }

        });

    }
}
