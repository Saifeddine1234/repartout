package com.example.rparetout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ReponseAdmin extends AppCompatActivity {
    ListView listView;
    String[] liste = {"Demandes Acceptées" , "Demandes Réfusées"};
Button flesh ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reponse_admin);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReponseAdmin.this,Access.class);
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
                    Intent intent = new Intent(ReponseAdmin.this,DemandeAccepterAdmin.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(ReponseAdmin.this,DemandeRefuserAdmin.class);
                    startActivity(intent);

                }
            }
        });

    }
}