package com.example.rparetout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView4;
    TextView envoyer;
    DatabaseReference databaseReference2;
    UserSessionManager session;
    String nomC ,prenomC,emailC,telephoneC,motdepasseC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        session = new UserSessionManager(getApplicationContext());
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView4 = findViewById(R.id.textView4);
        Toast.makeText(getApplicationContext(),
                "Menu",
                Toast.LENGTH_LONG).show();
        if(session.checkLogin())
            finish();
        HashMap<String, String> user = session.getUserDetails();
        nomC = user.get(UserSessionManager.KEY_NAME);
        prenomC = user.get(UserSessionManager.KEY_PRENOM);
        emailC = user.get(UserSessionManager.KEY_EMAIL);
        telephoneC = user.get(UserSessionManager.KEY_TELEPHONE);
        motdepasseC = user.get(UserSessionManager.KEY_MDP);

        databaseReference2 = FirebaseDatabase.getInstance().getReference("Text");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                String    txt=item.getValue(String.class);
                    textView4.setText(txt);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
        envoyer = findViewById(R.id.envoyer);
        toolbar = findViewById(R.id.toolbar);
        Intent inten = getIntent();
        nomC = inten.getStringExtra("nom");
        prenomC = inten.getStringExtra("prenom");
        telephoneC = inten.getStringExtra("telephone");
        emailC = inten.getStringExtra("email");
        motdepasseC = inten.getStringExtra("motdepasse");
  setSupportActionBar(toolbar);
envoyer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        HashMap<String, String> user = session.getUserDetails();
        nomC = user.get(UserSessionManager.KEY_NAME);
        prenomC = user.get(UserSessionManager.KEY_PRENOM);
        emailC = user.get(UserSessionManager.KEY_EMAIL);
        telephoneC = user.get(UserSessionManager.KEY_TELEPHONE);
        motdepasseC = user.get(UserSessionManager.KEY_MDP);
        Intent intent = new Intent(getApplicationContext(), Demande.class);
        intent.putExtra("nom",nomC);
        intent.putExtra("prenom",prenomC);
        intent.putExtra("telephone",telephoneC);
        intent.putExtra("email",emailC);
        intent.putExtra("motdepasse",motdepasseC);
        startActivity(intent);
    }
});

    navigationView.bringToFront();
    ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_menu);
    }
    @Override
    public void onBackPressed(){

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_menu:
                break;
                case R.id.nav_demande:
                    if(!isConnected(this)) buildDialog(this).show();
                    else {
                        HashMap<String, String> user = session.getUserDetails();
                        nomC = user.get(UserSessionManager.KEY_NAME);
                        prenomC = user.get(UserSessionManager.KEY_PRENOM);
                        emailC = user.get(UserSessionManager.KEY_EMAIL);
                        telephoneC = user.get(UserSessionManager.KEY_TELEPHONE);
                        motdepasseC = user.get(UserSessionManager.KEY_MDP);
                        Intent intent1 = new Intent(getApplicationContext(), Demandes.class);
                        intent1.putExtra("nom", nomC);
                        intent1.putExtra("prenom", prenomC);
                        intent1.putExtra("telephone", telephoneC);
                        intent1.putExtra("email", emailC);
                        intent1.putExtra("motdepasse", motdepasseC);
                        startActivity(intent1);
                        Toast.makeText(this, "Les Demandes", Toast.LENGTH_SHORT).show();
                        break;
                    }
            case R.id.nav_reponse:
                if(!isConnected(this)) buildDialog(this).show();
                else {
                    HashMap<String, String> user = session.getUserDetails();
                    nomC = user.get(UserSessionManager.KEY_NAME);
                    prenomC = user.get(UserSessionManager.KEY_PRENOM);
                    emailC = user.get(UserSessionManager.KEY_EMAIL);
                    telephoneC = user.get(UserSessionManager.KEY_TELEPHONE);
                    motdepasseC = user.get(UserSessionManager.KEY_MDP);
                    Intent intent12 = new Intent(getApplicationContext(), ReponseClient.class);
                    intent12.putExtra("nom", nomC);
                    intent12.putExtra("prenom", prenomC);
                    intent12.putExtra("telephone", telephoneC);
                    intent12.putExtra("email", emailC);
                    intent12.putExtra("motdepasse", motdepasseC);
                    startActivity(intent12);
                    Toast.makeText(this, "Les réponses", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.nav_contact:
                HashMap<String, String> user = session.getUserDetails();
                nomC = user.get(UserSessionManager.KEY_NAME);
                prenomC = user.get(UserSessionManager.KEY_PRENOM);
                emailC = user.get(UserSessionManager.KEY_EMAIL);
                telephoneC = user.get(UserSessionManager.KEY_TELEPHONE);
                motdepasseC = user.get(UserSessionManager.KEY_MDP);
                Intent intent = new Intent(getApplicationContext(), Contact.class);
                intent.putExtra("nom",nomC);
                intent.putExtra("prenom",prenomC);
                intent.putExtra("telephone",telephoneC);
                intent.putExtra("email",emailC);
                intent.putExtra("motdepasse",motdepasseC);
                startActivity(intent);
                Toast.makeText(this, "contact", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_profil:
                HashMap<String, String> user2 = session.getUserDetails();
                nomC = user2.get(UserSessionManager.KEY_NAME);
                prenomC = user2.get(UserSessionManager.KEY_PRENOM);
                emailC = user2.get(UserSessionManager.KEY_EMAIL);
                telephoneC = user2.get(UserSessionManager.KEY_TELEPHONE);
                motdepasseC = user2.get(UserSessionManager.KEY_MDP);
                Intent intent5 = new Intent(getApplicationContext(), Profil.class);
                intent5.putExtra("nom",nomC);
                intent5.putExtra("prenom",prenomC);
                intent5.putExtra("telephone",telephoneC);
                intent5.putExtra("email",emailC);
                intent5.putExtra("motdepasse",motdepasseC);
                startActivity(intent5);
                Toast.makeText(this, "profil", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Menu.this);
                builder.setTitle("Deconnexion");
                builder.setMessage("confirmer la Deconnexion...");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        session.logoutUser();
                    }
                });
                builder.setNegativeButton("Cancel",null);
                builder.create().show();

        }
        drawerLayout.closeDrawer(GravityCompat.START); return true;
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
