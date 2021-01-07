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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Contact extends AppCompatActivity {
    TextView nomRec ,contactemail ,envoyer, contacttel;
    EditText msg ;
    ImageView flesh ;
    String econtact,tcontact;
    String prenomC , telephoneC , nomC , emailC , motdepasseC ;
    DatabaseReference databaseReference ,databaseReference2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        databaseReference = FirebaseDatabase.getInstance().getReference("Reclamation");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Contact");
        Intent inten = getIntent();
        nomC = inten.getStringExtra("nom");
        prenomC = inten.getStringExtra("prenom");
        telephoneC = inten.getStringExtra("telephone");
        emailC = inten.getStringExtra("email");
        motdepasseC = inten.getStringExtra("motdepasse");
        envoyer = findViewById(R.id.envoyer);
        nomRec = findViewById(R.id.reg_name);
        msg  = findViewById(R.id.msg);
        nomRec.setText(nomC+" "+ prenomC);
        flesh = findViewById(R.id.flesh);
        contacttel=findViewById(R.id.telcontact);
        contactemail=findViewById(R.id.email);
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                     econtact=item.child("emailadmin").getValue(String.class);
                    tcontact=item.child("telephoneadmin").getValue(String.class);
                    contacttel.setText(tcontact);
                    contactemail.setText(econtact);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
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
                Intent intent5 = new Intent(getApplicationContext(), Menu.class);
                intent5.putExtra("nom", nomC);
                intent5.putExtra("prenom", prenomC);
                intent5.putExtra("telephone", telephoneC);
                intent5.putExtra("email", emailC);
                intent5.putExtra("motdepasse", motdepasseC);
                startActivity(intent5);
            } });
        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(Contact.this)) buildDialog(Contact.this).show();
                else {
                    Intent inten = getIntent();
                    String nomC = inten.getStringExtra("nom");
                    String prenomC = inten.getStringExtra("prenom");
                    String telephoneC = inten.getStringExtra("telephone");
                    String emailC = inten.getStringExtra("email");
                    String motdepasseC = inten.getStringExtra("motdepasse");

                    String nomReclamation = nomC +" "+prenomC;
                    String telReclamation = telephoneC;
                    String msgReclamtion = msg.getText().toString();
                    if (msgReclamtion.length()>=200){
                        Toast.makeText(Contact.this, "Votre message est très long", Toast.LENGTH_SHORT).show();

                    }else{
                        Intent intent5 = new Intent(getApplicationContext(), Menu.class);
                        intent5.putExtra("nom", nomC);
                        intent5.putExtra("prenom", prenomC);
                        intent5.putExtra("telephone", telephoneC);
                        intent5.putExtra("email", emailC);
                        intent5.putExtra("motdepasse", motdepasseC);
                        startActivity(intent5);
                        Reclamation reclamation = new Reclamation(nomReclamation, msgReclamtion, telReclamation);
                        databaseReference.child(telReclamation).setValue(reclamation);
                        Toast.makeText(Contact.this, "Votre réclamation est bien envoyer", Toast.LENGTH_SHORT).show();
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
        builder.setMessage("Verifier votre données mobiles");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder;
    }
}
