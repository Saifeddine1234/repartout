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
public class ReparateurAdmin extends AppCompatActivity {
    ListView listView;
    String[] liste = {"ajouter un réparateur" , "afficher Les réparateurs"};
    Button flesh ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reparateur_admin);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReparateurAdmin.this,Access.class);
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
                    Intent intent = new Intent(ReparateurAdmin.this,EnregistrerReparateur.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(ReparateurAdmin.this,AfficheReparateur.class);
                    startActivity(intent);

                }
            }
        });

    }
}