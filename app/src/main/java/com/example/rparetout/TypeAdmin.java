package com.example.rparetout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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

public class TypeAdmin extends AppCompatActivity {
    ListView listView;
    String[] liste = {"ajoute un type de repatation" , "affiche les Types de reparation"};
Button flesh ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_admin);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TypeAdmin.this,Access.class);
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
                    Intent intent = new Intent(TypeAdmin.this,NouveauType.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(TypeAdmin.this,AfficheType.class);
                    startActivity(intent);

                }
            }
        });

    }
}