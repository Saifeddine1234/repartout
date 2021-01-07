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

public class AfficheDemandeA extends AppCompatActivity implements ImageAdapterDemandesA.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ImageAdapterDemandesA mAdapter;
    private DatabaseReference mDatabaseRef,mDatabaseRef2;
    private List<DemandeClass> mUploads;
    ImageView flesh ;
    private ValueEventListener mBDListener ;
    String nomR , prenomR , telephoneR , motdepasseR , emailR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_demandea);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapterDemandesA(AfficheDemandeA.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(AfficheDemandeA.this);
        flesh = findViewById(R.id.flesh);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("DemandeA");
        mDatabaseRef2 = FirebaseDatabase.getInstance().getReference().child("Demande");
        mBDListener =   mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        DemandeClass demandeClass = postSnapshot.getValue(DemandeClass.class);
                        mUploads.add(demandeClass);

                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AfficheDemandeA.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent5 = new Intent(getApplicationContext(), Access.class);
                startActivity(intent5);
            } });
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
    public void onDeleteT(int position) {
        DemandeClass demandeClass = mUploads.get(position);
        String f = demandeClass.getT1()+demandeClass.getT2()+demandeClass.getT7()+demandeClass.getT8()+demandeClass.getT9();
        mDatabaseRef.child(f).removeValue();
        mDatabaseRef2.child(f).removeValue();


    }
    public void onDeleteR(int position) {
        DemandeClass demandeClass = mUploads.get(position);
        String f = demandeClass.getT1()+demandeClass.getT2()+demandeClass.getT7()+demandeClass.getT8()+demandeClass.getT9();
        mDatabaseRef2.child(f).removeValue();


    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mBDListener);
    }

}
