package com.example.rparetout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventListener;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Demande2 extends AppCompatActivity {
Button temps ,date ;
ImageView ajout1 , ajout2 ,flesh;
TextView t1,t15 , t2 , envoyer;
Spinner typeS;
ArrayAdapter<String> adapter;
DatabaseReference databaseReference, databaseReference2 , databaseReference3;
ArrayList<String> spinnerDataList;
ValueEventListener listener;
String T1,T2,T5,T6,time1,time2 , T0,Autre ,T9,T10,T11;
RadioButton passable , medium,urgent;
EditText autre;
RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande2);
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Demande");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("DemandeA");
        Intent inten = getIntent();
        time1= inten.getStringExtra("txt");
        time2= inten.getStringExtra("txt2");
        t1 = findViewById(R.id.t1);
        ajout2 = findViewById(R.id.ajout2);
        flesh = findViewById(R.id.flesh);
        t2 = findViewById(R.id.t2);
        t15 = findViewById(R.id.t15);
        ajout1 = findViewById(R.id.ajout1);
        typeS = findViewById(R.id.typeS);
        passable = findViewById(R.id.passable);
        medium = findViewById(R.id.medium);
        urgent = findViewById(R.id.urgent);
        autre = findViewById(R.id.autre);
        T11 = "En attende ..";
        databaseReference = FirebaseDatabase.getInstance().getReference("Types");
        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(Demande2.this, android.R.layout.simple_spinner_dropdown_item, spinnerDataList);
        typeS.setAdapter(adapter);
        retrive();
        typeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                T9 = spinnerDataList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ajout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = getIntent();
                String nomC = inten.getStringExtra("nom");
                String prenomC = inten.getStringExtra("prenom");
                String telephoneC = inten.getStringExtra("telephone");
                String emailC = inten.getStringExtra("email");
                String motdepasseC = inten.getStringExtra("motdepasse");
                String T1 = inten.getStringExtra("T1");
                String T2 = inten.getStringExtra("T2");
                time1 = inten.getStringExtra("txt");
                time2 = inten.getStringExtra("txt2");
                String T3 = inten.getStringExtra("T3");
                String T4 = inten.getStringExtra("T4");
                String T5 = inten.getStringExtra("T5");
                String T6 = inten.getStringExtra("T6");
                Intent intent = new Intent(getApplicationContext(), Time.class);
                intent.putExtra("nom", nomC);
                intent.putExtra("prenom", prenomC);
                intent.putExtra("telephone", telephoneC);
                intent.putExtra("email", emailC);
                intent.putExtra("motdepasse", motdepasseC);
                intent.putExtra("txt", time1);
                intent.putExtra("T1", T1);
                intent.putExtra("T2", T2);
                intent.putExtra("T3", T3);
                intent.putExtra("T4", T4);
                intent.putExtra("T5", T5);
                intent.putExtra("T6", T6);
                intent.putExtra("txt2", time2);
                startActivity(intent);
            }
        });
        ajout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t1.getText().toString().equals("")){
                    Toast.makeText(Demande2.this, "selectionner Horaire", Toast.LENGTH_SHORT).show();

                }else {
                    Intent inten = getIntent();
                    String nomC = inten.getStringExtra("nom");
                    String prenomC = inten.getStringExtra("prenom");
                    String telephoneC = inten.getStringExtra("telephone");
                    String emailC = inten.getStringExtra("email");
                    String motdepasseC = inten.getStringExtra("motdepasse");
                    String T1 = inten.getStringExtra("T1");
                    String T2 = inten.getStringExtra("T2");
                    time1 = inten.getStringExtra("txt");
                    time2 = inten.getStringExtra("txt2");
                    String T3 = inten.getStringExtra("T3");
                    String T4 = inten.getStringExtra("T4");
                    String T5 = inten.getStringExtra("T5");
                    String T6 = inten.getStringExtra("T6");
                    Intent intent = new Intent(getApplicationContext(), Time2.class);
                    intent.putExtra("nom", nomC);
                    intent.putExtra("prenom", prenomC);
                    intent.putExtra("telephone", telephoneC);
                    intent.putExtra("email", emailC);
                    intent.putExtra("motdepasse", motdepasseC);
                    intent.putExtra("txt", time1);
                    intent.putExtra("T1", T1);
                    intent.putExtra("T2", T2);
                    intent.putExtra("T3", T3);
                    intent.putExtra("T4", T4);
                    intent.putExtra("T5", T5);
                    intent.putExtra("T6", T6);
                    intent.putExtra("txt2", time2);
                    startActivity(intent);
                }
                }



        });
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = getIntent();
                String nomC = inten.getStringExtra("nom");
                String prenomC = inten.getStringExtra("prenom");
                String telephoneC = inten.getStringExtra("telephone");
                String emailC = inten.getStringExtra("email");
                String motdepasseC = inten.getStringExtra("motdepasse");
                Intent intent5 = new Intent(getApplicationContext(), Demande.class);
                intent5.putExtra("nom", nomC);
                intent5.putExtra("prenom", prenomC);
                intent5.putExtra("telephone", telephoneC);
                intent5.putExtra("email", emailC);
                intent5.putExtra("motdepasse", motdepasseC);
                startActivity(intent5);
            } });
        t1.setText(time1);
        t2.setText(time2);
        envoyer = findViewById(R.id.envoyer);
        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(Demande2.this)) buildDialog(Demande2.this).show();
                else {
                    if (t1.getText().toString().equals("")) {
                        Toast.makeText(Demande2.this, "choisir l'horaire de travail", Toast.LENGTH_SHORT).show();

                    } else {
                        if (passable.isChecked()) {
                            T10 = passable.getText().toString();
                            insert();
                        } else if (medium.isChecked()) {
                            T10 = medium.getText().toString();
                            insert();
                        } else if (urgent.isChecked()) {
                            T10 = urgent.getText().toString();
                            insert();
                        } else {
                            Toast.makeText(Demande2.this, "selectionner l'urgence", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    public void retrive() {
        listener = databaseReference.addValueEventListener(new ValueEventListener() {
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
    public void insert() {
        if (T9.equals("Autre")) {
            if (autre.getText().toString().equals("")) {
                Toast.makeText(Demande2.this, "remplir le champs autre", Toast.LENGTH_SHORT).show();
            } else {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Demande2.this);
                builder.setTitle("Confirmer ?");
                builder.setMessage("confirmer la Demande...");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        T1 = intent.getStringExtra("T1");
                        T2 = intent.getStringExtra("T2");
                        time1 = intent.getStringExtra("txt");
                        time2 = intent.getStringExtra("txt2");
                        String T3 = intent.getStringExtra("T3");
                        final String T4 = intent.getStringExtra("T4");
                        T5 = intent.getStringExtra("T5");
                        T6 = intent.getStringExtra("T6");
                        Autre = autre.getText().toString();
                        DemandeClass demandeClass = new DemandeClass(T0,T1, T2, T3, T4, T5, T6, time1, time2,Autre, T9, T10, T11);
                        databaseReference2.child(T1+T2+time1+time2+T9).setValue(demandeClass);
                        databaseReference3.child(T1+T2+time1+time2+T9).setValue(demandeClass);

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
                    }
                });
                builder.setNegativeButton("Cancel",null);
                builder.create().show();


            }
        } else {
            if (autre.getText().toString().equals("")) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Demande2.this);
                builder.setTitle("Confirmer ?");
                builder.setMessage("confirmer la Demande...");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                Intent intent = getIntent();
                T1 = intent.getStringExtra("T1");
                T2 = intent.getStringExtra("T2");
                String T3 = intent.getStringExtra("T3");
                final String T4 = intent.getStringExtra("T4");
                T5 = intent.getStringExtra("T5");
                time1 = intent.getStringExtra("txt");
                time2 = intent.getStringExtra("txt2");
                T6 = intent.getStringExtra("T6");
                Autre = autre.getText().toString();
                T0 = T1+T2+time1+time2+T9;
                DemandeClass demandeClass = new DemandeClass(T0,T1, T2, T3, T4, T5, T6, time1, time2,Autre, T9, T10, T11);
                databaseReference2.child(T1+T2+time1+time2+T9).setValue(demandeClass);
                databaseReference3.child(T1+T2+time1+time2+T9).setValue(demandeClass);
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
                    }
                });
                builder.setNegativeButton("Cancel",null);
                builder.create().show();
            } else {
                Toast.makeText(Demande2.this, "il faut etre le champs vide", Toast.LENGTH_SHORT).show();
            }
        }
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
        builder.setMessage("Verifier votre données mobiles");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder;
    }

}