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

public class Change extends AppCompatActivity {
TextView changeMP ,changeP;
String nomC , prenomC , telephoneC , emailC ,motdepasseC;
TextInputLayout  nomE , prenomE , emailE  ;
TextView telephoneE ;
DatabaseReference reference ;
ImageView flesh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
    reference = FirebaseDatabase.getInstance().getReference("Clients");
        changeP = findViewById(R.id.change);
        changeMP = findViewById(R.id.changemp);
        emailE = findViewById(R.id.email);
        telephoneE = findViewById(R.id.telephone);
        nomE = findViewById(R.id.nom);
        prenomE = findViewById(R.id.prenom);
        recuperer();
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
                Intent intent5 = new Intent(getApplicationContext(), Profil.class);
                intent5.putExtra("nom", nomC);
                intent5.putExtra("prenom", prenomC);
                intent5.putExtra("telephone", telephoneC);
                intent5.putExtra("email", emailC);
                intent5.putExtra("motdepasse", motdepasseC);
                startActivity(intent5);
            } });
        changeMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = getIntent();
                String nomC = inten.getStringExtra("nom");
                String prenomC = inten.getStringExtra("prenom");
                String telephoneC = inten.getStringExtra("telephone");
                String emailC = inten.getStringExtra("email");
                String motdepasseC = inten.getStringExtra("motdepasse");
                Intent intent5 = new Intent(getApplicationContext(), ChangerMP.class);
                intent5.putExtra("nom",nomC);
                intent5.putExtra("prenom",prenomC);
                intent5.putExtra("telephone",telephoneC);
                intent5.putExtra("email",emailC);
                intent5.putExtra("motdepasse",motdepasseC);
                startActivity(intent5);

            }
        });
        changeP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(Change.this)) buildDialog(Change.this).show();
                else {
                    if (isNameChange() || isPhoneChange() || isEmailChange()) {
                        Toast.makeText(Change.this, "donnee changer ", Toast.LENGTH_LONG).show();
                        Intent intent5 = new Intent(getApplicationContext(), Profil.class);
                        intent5.putExtra("nom", nomE.getEditText().getText().toString());
                        intent5.putExtra("prenom", prenomE.getEditText().getText().toString());
                        intent5.putExtra("telephone", telephoneC);
                        intent5.putExtra("email", emailE.getEditText().getText().toString());
                        startActivity(intent5);

                    } else {
                        Toast.makeText(Change.this, "saisir donnée", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
}
    private void recuperer() {
        Intent intent = getIntent();
         nomC = intent.getStringExtra("nom");
         prenomC = intent.getStringExtra("prenom");
         telephoneC = intent.getStringExtra("telephone");
         emailC = intent.getStringExtra("email");
        emailE.getEditText().setText(emailC);
        nomE.getEditText().setText(nomC);
        prenomE.getEditText().setText(prenomC);
        telephoneE.setText(telephoneC);
    }


    private boolean isEmailChange() {
        if(!emailC.equals(emailE.getEditText().getText().toString())) {
            reference.child(telephoneC).child("email").setValue(emailE.getEditText().getText().toString());
            return true;
        }else{
        return false;
        }
    }

    private boolean isPhoneChange() {
        if(!prenomC.equals(prenomE.getEditText().getText().toString())) {
            reference.child(telephoneC).child("prenom").setValue(prenomE.getEditText().getText().toString());
            return true;
        }else{
            return false;
        }
    }

    private boolean isNameChange() {
        if(!nomC.equals(nomE.getEditText().getText().toString())) {
            reference.child(telephoneC).child("nom").setValue(nomE.getEditText().getText().toString());
            return true;
        }else{
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
        builder.setMessage("Verifier votre données mobiles ..");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder;
    }

}
