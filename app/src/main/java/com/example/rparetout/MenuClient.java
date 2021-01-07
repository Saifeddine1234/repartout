package com.example.rparetout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
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

import java.util.HashMap;

public class MenuClient extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView envoyer;
    ImageView flesh ;
    String nomR ,prenomR ,typeR,telephoneR,mdpR , cinR ,imageR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_client);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        flesh =findViewById(R.id.flesh);
        envoyer = findViewById(R.id.envoyer);
        toolbar = findViewById(R.id.toolbar);
        Toast.makeText(getApplicationContext(),
                "Menu",
                Toast.LENGTH_LONG).show();
        setSupportActionBar(toolbar);
        Intent intent1 = getIntent();
        nomR = intent1.getStringExtra("nomrep");
        prenomR = intent1.getStringExtra("prenomrep");
        telephoneR = intent1.getStringExtra("telephonerep");
        typeR = intent1.getStringExtra("typerep");
        mdpR = intent1.getStringExtra("mdprep");
        cinR = intent1.getStringExtra("cinrep");
        imageR = intent1.getStringExtra("imagerep");

        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(MenuClient.this)) buildDialog(MenuClient.this).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), List.class);
                    intent.putExtra("nomrep", nomR);
                    intent.putExtra("prenomrep", prenomR);
                    intent.putExtra("telephonerep", telephoneR);
                    intent.putExtra("typerep", typeR);
                    intent.putExtra("cinrep", cinR);
                    intent.putExtra("mdprep", mdpR);
                    intent.putExtra("imagerep", imageR);
                    startActivity(intent);
                }
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
                if(!isConnected(MenuClient.this)) buildDialog(MenuClient.this).show();
                else {

                    Intent intent4 = new Intent(getApplicationContext(), DemandesReparateur.class);
                    intent4.putExtra("nomrep", nomR);
                    intent4.putExtra("prenomrep", prenomR);
                    intent4.putExtra("telephonerep", telephoneR);
                    intent4.putExtra("typerep", typeR);
                    intent4.putExtra("cinrep", cinR);
                    intent4.putExtra("mdprep", mdpR);
                    intent4.putExtra("imagerep", imageR);
                    startActivity(intent4);
                    break;
                }
            case R.id.nav_reponse:
                if(!isConnected(MenuClient.this)) buildDialog(MenuClient.this).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), ReponseReparateur.class);
                    intent.putExtra("nomrep", nomR);
                    intent.putExtra("prenomrep", prenomR);
                    intent.putExtra("telephonerep", telephoneR);
                    intent.putExtra("typerep", typeR);
                    intent.putExtra("cinrep", cinR);
                    intent.putExtra("mdprep", mdpR);
                    intent.putExtra("imagerep", imageR);
                    startActivity(intent);
                    break;
                }
            case R.id.nav_contact:
                Intent intent2 = new Intent(getApplicationContext(), ContactReparateur.class);
                intent2.putExtra("nomrep",nomR);
                intent2.putExtra("prenomrep",prenomR);
                intent2.putExtra("telephonerep",telephoneR);
                intent2.putExtra("typerep",typeR);
                intent2.putExtra("cinrep",cinR);
                intent2.putExtra("mdprep",mdpR);
                intent2.putExtra("imagerep", imageR);
                startActivity(intent2);
                Toast.makeText(this, "contact", Toast.LENGTH_SHORT).show();


                break;

            case R.id.nav_profil:
                Intent intent5 = new Intent(getApplicationContext(), ProfilReparateur.class);
                intent5.putExtra("nomrep",nomR);
                intent5.putExtra("prenomrep",prenomR);
                intent5.putExtra("telephonerep",telephoneR);
                intent5.putExtra("cinrep",cinR);
                intent5.putExtra("typerep",typeR);
                intent5.putExtra("mdprep",mdpR);
                intent5.putExtra("imagerep", imageR);
                startActivity(intent5);
                Toast.makeText(this, "profil", Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_logout:
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MenuClient.this);
                builder.setTitle("Deconnexion");
                builder.setMessage("confirmer la Deconnexion...");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MenuClient.this,Login.class);
                        startActivity(intent);
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
        builder.setMessage("Vous devez disposer de données mobiles ou wifi pour y accéder. Appuyez sur ok pour quitter");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder;
    }
}
