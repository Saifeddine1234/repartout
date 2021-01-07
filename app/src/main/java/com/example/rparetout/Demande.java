package com.example.rparetout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Demande extends AppCompatActivity {
    ImageView flesh;
    String  nomC,prenomC,telephoneC;
    Spinner spgov,spville ;
    ValueEventListener listener;
    DatabaseReference databaseReference,databaseReference2;
    ArrayList<String> gov , bbb;
    ArrayAdapter<String> adapter , adapter2;
    String T1,T2,T3,T4,T5,T6;
    TextView TV1,TV2 , suivant;
    EditText ET1,ET2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande);
        databaseReference= FirebaseDatabase.getInstance().getReference("Governement");
        databaseReference2= FirebaseDatabase.getInstance().getReference("Ville");
        flesh = findViewById(R.id.flesh);
        spgov = findViewById(R.id.spgov);
        spville = findViewById(R.id.spville);
        TV1=findViewById(R.id.T1);
        TV2=findViewById(R.id.T2);
        gov = new ArrayList<>();
        Intent inten = getIntent();
        nomC = inten.getStringExtra("nom");
        prenomC = inten.getStringExtra("prenom");
        telephoneC = inten.getStringExtra("telephone");
       // emailC = inten.getStringExtra("email");
      //  motdepasseC = inten.getStringExtra("motdepasse");
        adapter =new ArrayAdapter<String>(Demande.this, android.R.layout.simple_spinner_dropdown_item, gov);
        spgov.setAdapter(adapter);
        retrive();
        spgov.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                T5=gov.get(position);
                databaseReference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot item : dataSnapshot.getChildren()) {

                            if (item.child("govV").getValue(String.class).equals(gov.get(position)))
                                bbb.add(item.child("nomV").getValue().toString());


                        }
                        adapter2.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }
                });



                bbb = new ArrayList<>();
                adapter2 =new ArrayAdapter<String>(Demande.this, android.R.layout.simple_spinner_dropdown_item, bbb);
                spville.setAdapter(adapter2);
                spville.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        T6 = bbb.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = getIntent();
                 nomC= inten.getStringExtra("nom");
                 prenomC = inten.getStringExtra("prenom");
                 telephoneC = inten.getStringExtra("telephone");
                String emailC= inten.getStringExtra("email");
                String motdepasseC = inten.getStringExtra("motdepasse");
                Intent intent5 = new Intent(getApplicationContext(), Menu.class);
                intent5.putExtra("nom", nomC);
                intent5.putExtra("prenom", prenomC);
                intent5.putExtra("telephone", telephoneC);
                intent5.putExtra("email", emailC);
                intent5.putExtra("motdepasse", motdepasseC);
                startActivity(intent5);
            } });
        ET1=findViewById(R.id.ee1);
        ET2 = findViewById(R.id.ee2);
        T1= telephoneC;
        T2=nomC +" "+prenomC;
        TV1.setText(T1);
        TV2.setText(T2);
        suivant = findViewById(R.id.suivant);
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 T3=ET1.getText().toString();
                 T4= ET2.getText().toString();
                 if (T3.equals("")||T4.equals("")||T5.equals("")||T6.equals("")){
                     Toast.makeText(Demande.this, "remplir les champs ", Toast.LENGTH_SHORT).show();

                 }else
                 {
                     Intent inten = getIntent();
                     nomC= inten.getStringExtra("nom");
                     prenomC = inten.getStringExtra("prenom");
                     telephoneC = inten.getStringExtra("telephone");
                     String emailC= inten.getStringExtra("email");
                     String motdepasseC = inten.getStringExtra("motdepasse");
                     Intent intent5 = new Intent(getApplicationContext(), Demande2.class);
                     intent5.putExtra("T1", T1);
                     intent5.putExtra("T2", T2);
                     intent5.putExtra("T3", T3);
                     intent5.putExtra("T4", T4);
                     intent5.putExtra("T5", T5);
                     intent5.putExtra("T6", T6);
                     intent5.putExtra("nom", nomC);
                     intent5.putExtra("prenom", prenomC);
                     intent5.putExtra("telephone", telephoneC);
                     intent5.putExtra("email", emailC);
                     intent5.putExtra("motdepasse", motdepasseC);
                     startActivity(intent5);

                 }

            }
        });

    }
    public void retrive() {
        listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    gov.add(item.child("nomG").getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
