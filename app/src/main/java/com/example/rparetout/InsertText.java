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

public class InsertText extends AppCompatActivity {
    EditText governement;
    Button insert;
    ImageView flesh;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_text);
        governement =findViewById(R.id.textG);
        insert = findViewById(R.id.inserer);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertText.this,CompteAdmin.class);
                startActivity(intent);
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("Text");
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = governement.getText().toString();
                if (name != null && name.length() > 0 ){

                    databaseReference.child("1").setValue(name);
                    governement.setText("");
                    Toast.makeText(InsertText.this , "modification avec success" ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(InsertText.this, CompteAdmin.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(InsertText.this,"erreur !!",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}
