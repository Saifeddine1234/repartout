package com.example.rparetout;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DemandesReparateur extends AppCompatActivity implements ImageAdapter4Reparateur.OnItemClickSelect {
    private RecyclerView mRecyclerView;
    private ImageAdapter4Reparateur mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<DemandeClass> mUploads;
    private Button flesh ;
    private ValueEventListener mBDListener ;
    String nomR , prenomR , telephoneR , motdepasseR ,typeR, cinR,imageR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demandes_rep);
        flesh = findViewById(R.id.flesh);

        Intent inten = getIntent();
        nomR = inten.getStringExtra("nomrep");
        prenomR = inten.getStringExtra("prenomrep");
        telephoneR = inten.getStringExtra("telephonerep");
        cinR = inten.getStringExtra("cinrep");
        motdepasseR = inten.getStringExtra("mdprep");
        typeR = inten.getStringExtra("typerep");
         imageR = inten.getStringExtra("imagerep");
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            } });
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapter4Reparateur(DemandesReparateur.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(DemandesReparateur.this);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Demande");
        mBDListener =   mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if(postSnapshot.child("t9").getValue().toString().equals(typeR)|| postSnapshot.child("t9").getValue().toString().equals("Autre")){
                        DemandeClass demandeClass = postSnapshot.getValue(DemandeClass.class);
                        mUploads.add(demandeClass);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DemandesReparateur.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelect(int position) {

    }
    @Override
    public void onDelete(int position) {

    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mBDListener);
    }
}
