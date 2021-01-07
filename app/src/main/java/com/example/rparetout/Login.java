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
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button connexion ;
 TextView pass_en ;
TextInputLayout emailLogin , motdepasseLogin ;
ProgressDialog progressDialog ;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new UserSessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(),
                "Login",
                Toast.LENGTH_LONG).show();
        final Handler handler = new Handler();
progressDialog = new ProgressDialog(this);
        //button
        connexion = findViewById(R.id.connexion);
        pass_en = findViewById(R.id.pass_en);
        //EditText
        emailLogin = findViewById(R.id.reg_telephone);
        motdepasseLogin = findViewById(R.id.motdepasseLogin);
//actionB2
        pass_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Enregistrer.class);
                startActivity(intent);
            }
        });

        //actionB1
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(Login.this)) buildDialog(Login.this).show();
                else {

                    if (emailLogin.getEditText().getText().toString().equals("") || motdepasseLogin.getEditText().getText().toString().equals("")) {
                        DialogErreur dialogFrag = new DialogErreur();
                        dialogFrag.show(getSupportFragmentManager(), "alert");
                    } else {
                        if (emailLogin.getEditText().getText().toString().equals("12345678")) {
                        isUserA();
                    }else{
                            isUserC();

                        }


                    }
                }
            }

        });
    }
    private void isUserA(){
        final String codeA = emailLogin.getEditText().getText().toString().trim();
        final String mdpA = motdepasseLogin.getEditText().getText().toString().trim();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        final Query checkUser = databaseReference.orderByChild("codeU").equalTo(codeA);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String mdpBD = dataSnapshot.child(codeA).child("mdpU").getValue(String.class);
                    if (mdpBD.equals(mdpA)) {
                        progressDialog.setTitle("Login en cours ");
                        progressDialog.setMessage("Attendre un peu..");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();
                        Toast.makeText(Login.this,"Bienvenue administrateur",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, Access.class);
                        startActivity(intent);

                    }else {
                        DialogErreur dialogFrag = new DialogErreur();
                        dialogFrag.show(getSupportFragmentManager(), "alert");
                    }

                }else {
                    DialogErreur dialogFrag = new DialogErreur();
                    dialogFrag.show(getSupportFragmentManager(), "alert");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                DialogErreur dialogFrag = new DialogErreur();
                dialogFrag.show(getSupportFragmentManager(),"alert");
            }

        });
    }

    private void isUserC() {

        final String cinL = emailLogin.getEditText().getText().toString().trim();
        final String mdpL = motdepasseLogin.getEditText().getText().toString().trim();
        final DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference("Reparateur");
        final Query checkUser2 = databaseReference3.orderByChild("cinrep").equalTo(cinL);
        final String userenteredusername = emailLogin.getEditText().getText().toString().trim();
        final String userenteredpassword = motdepasseLogin.getEditText().getText().toString().trim();
        final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Clients");
        final Query checkUser1 = databaseReference1.orderByChild("telephone").equalTo(userenteredusername);
        checkUser2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String mdpBDC = dataSnapshot.child(cinL).child("mdprep").getValue(String.class);
                    if (!mdpBDC.equals(mdpL)) {
                        DialogErreur dialogFrag = new DialogErreur();
                        dialogFrag.show(getSupportFragmentManager(),"alert");
                    } else {
                        progressDialog.setTitle("Login en cours ");
                        progressDialog.setMessage("Attendre un peu");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();
                        String nomBDC = dataSnapshot.child(cinL).child("nomrep").getValue(String.class);
                        String prenomBDC = dataSnapshot.child(cinL).child("prenomrep").getValue(String.class);
                        String telephoneBDC = dataSnapshot.child(cinL).child("telephonerep").getValue(String.class);
                        String cinBDC = dataSnapshot.child(cinL).child("cinrep").getValue(String.class);
                        String typeBDC = dataSnapshot.child(cinL).child("typerep").getValue(String.class);
                        String imageBDC = dataSnapshot.child(cinL).child("imagerep").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), MenuClient.class);
                        intent.putExtra("nomrep", nomBDC);
                        intent.putExtra("prenomrep", prenomBDC);
                        intent.putExtra("cinrep", cinBDC);
                        intent.putExtra("mdprep", mdpBDC);
                        intent.putExtra("telephonerep", telephoneBDC);
                        intent.putExtra("typerep", typeBDC);
                        intent.putExtra("imagerep", imageBDC);
                        startActivity(intent);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                DialogErreur dialogFrag = new DialogErreur();
                dialogFrag.show(getSupportFragmentManager(),"alert");
            }

        });
        checkUser1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                }else {

                    String mdpBD = dataSnapshot.child(userenteredusername).child("motdepasse").getValue(String.class);
                    if (!mdpBD.equals(userenteredpassword)) {
                        DialogErreur dialogFrag = new DialogErreur();
                        dialogFrag.show(getSupportFragmentManager(),"alert");
                    } else {
                       progressDialog.setTitle("Login en cours ");
                        progressDialog.setMessage("Attendre un peu..");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();

                        String nomBD = dataSnapshot.child(userenteredusername).child("nom").getValue(String.class);
                        String prenomBD = dataSnapshot.child(userenteredusername).child("prenom").getValue(String.class);
                        String telephoneBD = dataSnapshot.child(userenteredusername).child("telephone").getValue(String.class);
                        String emailBD = dataSnapshot.child(userenteredusername).child("email").getValue(String.class);
                        session.createUserLoginSession(nomBD, prenomBD,emailBD,telephoneBD,mdpBD);
                        Intent i = new Intent(getApplicationContext(), Menu.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                        finish();

                    }



                }
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

    @Override
    public void onBackPressed() {

    }

}




