package com.example.rparetout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Access extends AppCompatActivity {
ListView listView;
String[] liste={"Demandes","Réponses","Réparateurs","Clients","Reclamation","Ville","Gouvernement","Type","Compte","Déconexion"};
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
    listView =findViewById(R.id.listview);
        final ListAdapter listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,liste);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isConnected(Access.this)) buildDialog(Access.this).show();
                else {
                    if (position == 0) {
                        Intent intent = new Intent(Access.this, AfficheDemandeA.class);
                        startActivity(intent);
                    }
                    if (position == 1) {
                        Intent intent = new Intent(Access.this, ReponseAdmin.class);
                        startActivity(intent);
                    }
                    if (position == 2) {
                        Intent intent = new Intent(Access.this, ReparateurAdmin.class);
                        startActivity(intent);
                    }
                    if (position == 3) {
                        Intent intent = new Intent(Access.this, AfficheUtilisateur.class);
                        startActivity(intent);
                    }
                    if (position == 4) {
                        Intent intent = new Intent(Access.this, AfficheReclamation.class);
                        startActivity(intent);
                    }
                    if (position == 5) {
                        Intent intent = new Intent(Access.this, VilleAdmin.class);
                        startActivity(intent);
                    }
                    if (position == 6) {
                        Intent intent = new Intent(Access.this, GovAdmin.class);
                        startActivity(intent);
                    }
                    if (position == 7) {
                        Intent intent = new Intent(Access.this, TypeAdmin.class);
                        startActivity(intent);
                    }
                    if (position == 8) {
                        Intent intent = new Intent(Access.this, CompteAdmin.class);
                        startActivity(intent);
                    }
                    if (position == 9) {
                        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Access.this);
                        builder.setTitle("Deconnexion");
                        builder.setMessage("confirmer la Deconnexion...");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Access.this,Login.class);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("Cancel",null);
                        builder.create().show();
                    }
                }
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

    @Override
    public void onBackPressed() {

    }
    }

