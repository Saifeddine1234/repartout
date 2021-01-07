package com.example.rparetout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java. util.ArrayList;

public class InVille extends AppCompatActivity {
    DatabaseReference databaseReference , DR ;
    ArrayList<String> spinnerDataList;
    ValueEventListener listener;
    Spinner spinner;
    ArrayAdapter<String> adapter;
    EditText editVille;
    Button inserer ;
    ImageView  flesh;
    String nomville , nomgov;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_ville);
        databaseReference = FirebaseDatabase.getInstance().getReference("Governement");
        DR = FirebaseDatabase.getInstance().getReference("Ville");
        spinner = findViewById(R.id.spinnerG);
        editVille = findViewById(R.id.textG);
        inserer = findViewById(R.id.inserer);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InVille.this,VilleAdmin.class);
                startActivity(intent);
            }
        });
        spinnerDataList = new ArrayList<>();
        adapter =new ArrayAdapter<String>(InVille.this, android.R.layout.simple_spinner_dropdown_item, spinnerDataList);
        spinner.setAdapter(adapter);
           retrive();
         nomville = editVille.getText().toString().trim();
           spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   nomgov = spinnerDataList.get(position);
               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
           });
           inserer.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (!isConnected(InVille.this)) buildDialog(InVille.this).show();
                   else {
                       nomville = editVille.getText().toString().trim();
                       Ville ville = new Ville(nomville, nomgov);
                       DR.child(nomville).setValue(ville);
                       Toast.makeText(InVille.this, "bien inserer, ajouter des autres ", Toast.LENGTH_SHORT).show();

                       editVille.setText("");
                   }
               }
           });

    }

    public void retrive() {
        listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    spinnerDataList.add(item.child("nomG").getValue().toString());
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
