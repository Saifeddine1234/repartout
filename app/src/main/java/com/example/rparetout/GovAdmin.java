package com.example.rparetout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rparetout.AfficheReclamation;
import com.example.rparetout.AfficheReparateur;
import com.example.rparetout.AfficheUtilisateur;
import com.example.rparetout.DialogFrag;
import com.example.rparetout.EnregistrerReparateur;
import com.example.rparetout.InVille;
import com.example.rparetout.InsertGov;
import com.example.rparetout.ListdesDemandes;
import com.example.rparetout.ModifierA;
import com.example.rparetout.ModifierContact;
import com.example.rparetout.NouveauType;
import com.example.rparetout.R;

public class GovAdmin extends AppCompatActivity {
    ListView listView;
    String[] liste = {"ajoute une gouvernement" , "affiche les Gouvernements"};
    Button flesh ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gov_admin);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GovAdmin.this,Access.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.listview);
        final ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, liste);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(GovAdmin.this,InsertGov.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(GovAdmin.this,AfficheGov.class);
                    startActivity(intent);

                }
            }
        });

    }
}