package com.example.rparetout;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class ReponseReparateur extends AppCompatActivity implements ImageAdapterreparateurreponse.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ImageAdapterreparateurreponse mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<DemandeClass> mUploads;
    private ValueEventListener mBDListener ;
    Button flesh;
    String nomR , prenomR , telephoneR , typeR , nomclient , desc , date ,imageR,cinR , motdepasseR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reponse_reparateur);
        Intent intent = getIntent();
        nomclient=  intent.getStringExtra("nomclient");
        desc=  intent.getStringExtra("desc");
        date=  intent.getStringExtra("date");
        nomR = intent.getStringExtra("nomrep");
        prenomR = intent.getStringExtra("prenomrep");
        telephoneR = intent.getStringExtra("telephonerep");
        cinR = intent.getStringExtra("cinrep");
        typeR = intent.getStringExtra("typerep");
        imageR = intent.getStringExtra("imagerep");
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
        mAdapter = new ImageAdapterreparateurreponse(ReponseReparateur.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(ReponseReparateur.this);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("ReponseDemande");
        mBDListener =   mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if(postSnapshot.child("t14").getValue().toString().equals(cinR)){
                        DemandeClass demandeClass = postSnapshot.getValue(DemandeClass.class);
                        mUploads.add(demandeClass);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ReponseReparateur.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDelete(int position) {
        DemandeClass demandeClass = mUploads.get(position);
        String f = demandeClass.getT1()+demandeClass.getT2()+demandeClass.getT7()+demandeClass.getT8()+demandeClass.getT9();
        mDatabaseRef.child(f).removeValue();


    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mBDListener);
    }

}
