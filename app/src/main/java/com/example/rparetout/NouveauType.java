package com.example.rparetout;
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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NouveauType extends AppCompatActivity {
    EditText type;
    Button insert;
    ImageView flesh;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_type);
        type =findViewById(R.id.nvtype);
        insert = findViewById(R.id.inserertype);
        flesh = findViewById(R.id.flesh);
        databaseReference = FirebaseDatabase.getInstance().getReference("Types");
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(NouveauType.this)) buildDialog(NouveauType.this).show();
                else {
                    String typer = type.getText().toString().trim();
                    if (typer != null && typer.length() > 0) {

                        Types types = new Types(typer);
                        databaseReference.child(typer).setValue(types);
                        Toast.makeText(NouveauType.this, "Bien inserer, ajouter des autres types", Toast.LENGTH_SHORT).show();
                        type.setText("");
                    } else {
                        Toast.makeText(NouveauType.this, "erreur !!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NouveauType.this,TypeAdmin.class);
                startActivity(intent);

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
