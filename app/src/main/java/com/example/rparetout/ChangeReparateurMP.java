package com.example.rparetout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeReparateurMP extends AppCompatActivity {
    TextView B1;
    DatabaseReference reference;
    String nomR,prenomR , cinR,telephoneR,typeR,motdepasseR;
    TextInputLayout passwordE, passwordN;
    ImageView flesh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_reperateur_mp);
        reference = FirebaseDatabase.getInstance().getReference("Reparateur");
        passwordE = findViewById(R.id.mdpA);
        passwordN = findViewById(R.id.mdpN);
        B1 = findViewById(R.id.change);
        Intent inten = getIntent();
        nomR = inten.getStringExtra("nomrep");
        prenomR = inten.getStringExtra("prenomrep");
        telephoneR = inten.getStringExtra("telephonerep");
        typeR=inten.getStringExtra("typerep");
        cinR = inten.getStringExtra("cinrep");
        motdepasseR = inten.getStringExtra("mdprep");

        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getApplicationContext(), ProfilReparateur.class);
                intent5.putExtra("nomrep", nomR);
                intent5.putExtra("prenomrep", prenomR);
                intent5.putExtra("telephonerep", telephoneR);
                intent5.putExtra("typerep",typeR);
                intent5.putExtra("cinrep", cinR);
                intent5.putExtra("mdprep", motdepasseR);
                startActivity(intent5);
            } });
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(ChangeReparateurMP.this)) buildDialog(ChangeReparateurMP.this).show();
                else {


                    if (password()) {
                        if (passwordN.getEditText().getText().toString().equals("") || passwordN.getEditText().getText().toString().length() <= 6) {
                            DialogErreurPass dialogErreur = new DialogErreurPass();
                            dialogErreur.show(getSupportFragmentManager(), "alert");
                        } else {
                            Toast.makeText(ChangeReparateurMP.this, "mot de passe changer avec succes", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MenuClient.class);
                            intent.putExtra("nomrep", nomR);
                            intent.putExtra("prenomrpe", prenomR);
                            intent.putExtra("telephonerpe", telephoneR);
                            intent.putExtra("cinrep", cinR);
                            intent.putExtra("typerep", typeR);
                            intent.putExtra("mdprep", passwordN.getEditText().getText().toString());
                            startActivity(intent);
                        }
                    } else {
                        DialogErreurPass dialogErreur = new DialogErreurPass();
                        dialogErreur.show(getSupportFragmentManager(), "alert");
                    }
                }
            }
        });
    }

    public boolean password() {
        if (motdepasseR.equals(passwordE.getEditText().getText().toString())) {
            reference.child(cinR).child("mdprep").setValue(passwordN.getEditText().getText().toString());
            return true;
        } else {
            return false;

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
        builder.setMessage("Vous devez disposer de données mobiles ou wifi pour y accéder. Appuyez sur ok pour quitter ..");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder;
    }
}