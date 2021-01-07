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

public class ContactReparateur extends AppCompatActivity {
    TextView nomRec  ,envoyer;
    EditText msg ;
    ImageView flesh ;
    String prenomR , telephoneR , nomR , cinR, motdepasseR , typeR ,imageR;
    DatabaseReference databaseReference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_reparateur);
        databaseReference = FirebaseDatabase.getInstance().getReference("Reclamation");
        Intent inten = getIntent();
        nomR = inten.getStringExtra("nomrep");
        prenomR = inten.getStringExtra("prenomrep");
        telephoneR = inten.getStringExtra("telephonerep");
        cinR = inten.getStringExtra("cinrep");
        typeR = inten.getStringExtra("typerep");
        motdepasseR = inten.getStringExtra("mdprep");
        imageR = inten.getStringExtra("imagerep");
        envoyer = findViewById(R.id.envoyer);
        nomRec = findViewById(R.id.reg_name);
        msg  = findViewById(R.id.msg);
        nomRec.setText(nomR+" "+ prenomR);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = getIntent();
                String nomR = inten.getStringExtra("nomrep");
                String prenomR = inten.getStringExtra("prenomrep");
                String telephoneR = inten.getStringExtra("telephonerep");
                String cinR = inten.getStringExtra("cinrep");
                String motdepasseR = inten.getStringExtra("mdprep");
                String typeR = inten.getStringExtra("typerep");
                String imageR = inten.getStringExtra("imagerep");
                Intent intent5 = new Intent(getApplicationContext(), MenuClient.class);
                intent5.putExtra("nomrep", nomR);
                intent5.putExtra("prenomrep", prenomR);
                intent5.putExtra("telephonerep", telephoneR);
                intent5.putExtra("cinrep", cinR);
                intent5.putExtra("typerep", typeR);
                intent5.putExtra("mdprep", motdepasseR);
                intent5.putExtra("imagerep", imageR);
                startActivity(intent5);
            } });
        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(ContactReparateur.this))
                    buildDialog(ContactReparateur.this).show();
                else {

                    Intent inten = getIntent();
                    String nomR = inten.getStringExtra("nomrep");
                    String prenomR = inten.getStringExtra("prenomrep");
                    String telephoneR = inten.getStringExtra("telephonerep");
                    String cinR = inten.getStringExtra("cinrep");
                    String typeR = inten.getStringExtra("typerep");
                    String motdepasseR = inten.getStringExtra("mdprep");
                    String imageR = inten.getStringExtra("imagerep");
                    Intent intent5 = new Intent(getApplicationContext(), MenuClient.class);
                    intent5.putExtra("nomrep", nomR);
                    intent5.putExtra("prenomrep", prenomR);
                    intent5.putExtra("telephonerep", telephoneR);
                    intent5.putExtra("cinrep", cinR);
                    intent5.putExtra("typerep", typeR);
                    intent5.putExtra("mdprep", motdepasseR);
                    intent5.putExtra("imagerep", imageR);
                    startActivity(intent5);
                    String nomReclamation = nomR + " " + prenomR;
                    String telReclamation = cinR;
                    String msgReclamtion = msg.getText().toString();
                    Reclamation reclamation = new Reclamation(nomReclamation, msgReclamtion, telReclamation);
                    databaseReference.child(telReclamation).setValue(reclamation);
                    Toast.makeText(ContactReparateur.this, "votre réclamation est bien envoyer", Toast.LENGTH_SHORT).show();
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
