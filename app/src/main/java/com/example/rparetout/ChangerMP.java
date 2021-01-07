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

public class ChangerMP extends AppCompatActivity {
    TextView B1;
    DatabaseReference reference;
    String nomC,prenomC , emailC,telephoneC,motdepasseC;
    TextInputLayout passwordE, passwordN;
    ImageView flesh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer_mp);
        reference = FirebaseDatabase.getInstance().getReference("Clients");
        passwordE = findViewById(R.id.mdpA);
        passwordN = findViewById(R.id.mdpN);
        B1 = findViewById(R.id.change);
        Intent inten = getIntent();
        nomC = inten.getStringExtra("nom");
        prenomC = inten.getStringExtra("prenom");
        telephoneC = inten.getStringExtra("telephone");
        emailC = inten.getStringExtra("email");
        motdepasseC = inten.getStringExtra("motdepasse");

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
                Intent intent5 = new Intent(getApplicationContext(), Change.class);
                intent5.putExtra("nom", nomC);
                intent5.putExtra("prenom", prenomC);
                intent5.putExtra("telephone", telephoneC);
                intent5.putExtra("email", emailC);
                intent5.putExtra("motdepasse", motdepasseC);
                startActivity(intent5);
            } });
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(ChangerMP.this)) buildDialog(ChangerMP.this).show();
                else {
                    if (password()) {
                        if (passwordN.getEditText().getText().toString().equals("") || passwordN.getEditText().getText().toString().length() <= 6) {
                            DialogErreurPass dialogErreur = new DialogErreurPass();
                            dialogErreur.show(getSupportFragmentManager(), "alert");
                        } else {
                            Toast.makeText(ChangerMP.this, "mot de passe changer avec succes", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Menu.class);
                            intent.putExtra("nom", nomC);
                            intent.putExtra("prenom", prenomC);
                            intent.putExtra("telephone", telephoneC);
                            intent.putExtra("email", emailC);
                            intent.putExtra("motdepasse", passwordN.getEditText().getText().toString());
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
        if (motdepasseC.equals(passwordE.getEditText().getText().toString())) {
            reference.child(telephoneC).child("motdepasse").setValue(passwordN.getEditText().getText().toString());
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
        builder.setMessage("Verifier votre données mobiles");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder;
    }
}