package com.example.rparetout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Enregistrer extends AppCompatActivity {
    TextInputLayout regNom, regPrenom, regEmail, regTelephone , regMotdepasse;
    DatabaseReference databaseReference ;
    FirebaseDatabase database ;
    TextView pass_log ;
     Button inscrit ;
    ProgressDialog progressDialog ;
     RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrer);
        inscrit = findViewById(R.id.inscrit);
        pass_log = findViewById(R.id.pass_log);
        regNom = findViewById(R.id.nom);
        regPrenom = findViewById(R.id.prenom);
        progressDialog = new ProgressDialog(this);
        regEmail = findViewById(R.id.email);
        regTelephone = findViewById(R.id.reg_telephone);
        regMotdepasse = findViewById(R.id.reg_password);
        radioButton = findViewById(R.id.radioButton);
        pass_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Enregistrer.this , Login.class);
                startActivity(intent1);
            }
        });
        inscrit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                        if(!isConnected(Enregistrer.this)) buildDialog(Enregistrer.this).show();
                        else {
                final String nom = regNom.getEditText().getText().toString();
                final String prenom = regPrenom.getEditText().getText().toString();
                final String telephone = regTelephone.getEditText().getText().toString();
                final String email = regEmail.getEditText().getText().toString();
               final String motdepasse = regMotdepasse.getEditText().getText().toString();
                final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Clients");
                final Query checkUser = databaseReference1.orderByChild("telephone").equalTo(telephone);

    if (email.equals("") || nom.equals("")||motdepasse.equals("")||prenom.equals("")||telephone.equals("")) {
       Toast.makeText(Enregistrer.this , "remplir les champs" , Toast.LENGTH_SHORT).show();
     } else if(telephone.length()!=8) {
        Toast.makeText(Enregistrer.this, "saisir les 8 chiffres de telephone", Toast.LENGTH_SHORT).show();
    }
    else if(telephone.equals("12345678")) {
        Toast.makeText(Enregistrer.this, "numéro de telephone incorrect", Toast.LENGTH_SHORT).show();
    }else if(email.length() <= 10){
        Toast.makeText(Enregistrer.this, "saisir l'email  correctement", Toast.LENGTH_SHORT).show();

    }else if(motdepasse.length() <= 5) {
        Toast.makeText(Enregistrer.this, "Le mot de passe est trés court", Toast.LENGTH_SHORT).show();
    }
    else if(!radioButton.isChecked()){
        Toast.makeText(Enregistrer.this, "verifier les conditions d'utilisation", Toast.LENGTH_SHORT).show();
    }else {
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(Enregistrer.this, "se numéro de telephone est exist deja", Toast.LENGTH_SHORT).show();

                } else {
                 /*   Intent intent = new Intent(Enregistrer.this, Otp.class);
                    intent.putExtra("telephone", telephone);
                    intent.putExtra("nom", nom);
                    intent.putExtra("prenom", prenom);
                    intent.putExtra("email", email);
                    intent.putExtra("motdepasse", motdepasse);
                    startActivity(intent);

                  */
                    progressDialog.setTitle("Creation en cours ");
                    progressDialog.setMessage("Attendre un peu ...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    database = FirebaseDatabase.getInstance();
                    databaseReference = database.getReference("Clients");
                    Client client = new Client(nom, prenom, email, telephone, motdepasse);
                    databaseReference.child(telephone).setValue(client);
                    Toast.makeText(Enregistrer.this, "Compte créé ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Enregistrer.this, Login.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
    }

    /*    Intent intent = new Intent(Enregistrer.this,Login.class);
        startActivity(intent);
     */
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
        Intent intent1 = new Intent(Enregistrer.this , Login.class);
        startActivity(intent1);
    }
}



