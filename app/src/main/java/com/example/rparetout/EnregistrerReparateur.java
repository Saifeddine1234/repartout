package com.example.rparetout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EnregistrerReparateur extends AppCompatActivity {
    TextInputLayout nomrep,prenomrep,telephonerep,cinrep,mdprep;
Spinner typerep;
    String trep;
    ArrayList<String> spinnerDataList;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
Button insererrep;
DatabaseReference databaseReference , databaseReference2;
FirebaseDatabase database;
    ImageView flesh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrer_reparateur);
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Types");
        nomrep = findViewById(R.id.nomrep);

        prenomrep = findViewById(R.id.prenomrep);
        telephonerep = findViewById(R.id.telephonerep);
        cinrep = findViewById(R.id.cinrep);
        mdprep = findViewById(R.id.mdprep);
        insererrep = findViewById(R.id.insererrep);
        typerep=findViewById(R.id.spinnerrep);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnregistrerReparateur.this,ReparateurAdmin.class);
                startActivity(intent);
            }
        });
        spinnerDataList = new ArrayList<>();
        adapter =new ArrayAdapter<String>(EnregistrerReparateur.this, android.R.layout.simple_spinner_dropdown_item, spinnerDataList);
        typerep.setAdapter(adapter);
        retrive();
        typerep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 trep = spinnerDataList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        insererrep.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isConnected(EnregistrerReparateur.this)) buildDialog(EnregistrerReparateur.this).show();
                else {
                    String nom = nomrep.getEditText().getText().toString().trim();
                    String prenom = prenomrep.getEditText().getText().toString().trim();
                    String telephone = telephonerep.getEditText().getText().toString().trim();
                    String cin = cinrep.getEditText().getText().toString().trim();
                    String motdepasse = mdprep.getEditText().getText().toString().trim();
                    String type = trep;
                    if (cin.equals("") || nom.equals("") || motdepasse.equals("") || prenom.equals("") || telephone.equals("")) {
                        Toast.makeText(EnregistrerReparateur.this, "saisir votre données", Toast.LENGTH_SHORT).show();
                    } else if (cin.length() != 8) {
                        Toast.makeText(EnregistrerReparateur.this, "saisir les 8 chiffres de CIN", Toast.LENGTH_SHORT).show();
                    } else if (telephone.length() != 8) {
                        Toast.makeText(EnregistrerReparateur.this, "saisir les 8 chiffres de Telephone", Toast.LENGTH_SHORT).show();
                    } else if (motdepasse.length() <= 5) {
                        Toast.makeText(EnregistrerReparateur.this, "Mot de passe est trés court", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext(),AddPic.class);
                        intent.putExtra("nomrep",nom);
                        intent.putExtra("prenomrep",prenom);

                        intent.putExtra("cinrep",cin);
                        intent.putExtra("telephonerep",telephone);
                        intent.putExtra("mdprep",motdepasse);
                        intent.putExtra("typerep",type);
                        startActivity(intent);
                    }
                }
            }
        });


    }
    public void retrive() {
        listener = databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    spinnerDataList.add(item.child("nomType").getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Pas de connexion Internet");
        builder.setMessage("Vous devez disposer de données mobiles ou wifi pour y accéder. Appuyez sur ok pour quitter ..");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder;
    }

}

