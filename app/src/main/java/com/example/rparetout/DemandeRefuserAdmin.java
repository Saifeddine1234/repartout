package com.example.rparetout;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class DemandeRefuserAdmin extends AppCompatActivity implements ImageAdapterRefuse.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ImageAdapterRefuse mAdapter;
    private DatabaseReference mDatabaseRef,mDatabaseRef2;
    private List<DemandeClass> mUploads;
    private ValueEventListener mBDListener ;
    Button flesh;
    String nomR , prenomR , telephoneR , typeR , nomclient , desc , date ,cinR , motdepasseR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demandeaccepteradmin);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getApplicationContext(), ReponseAdmin.class);
                startActivity(intent5);
            } });
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapterRefuse(DemandeRefuserAdmin.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(DemandeRefuserAdmin.this);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("ReponseDemandeA");
        mDatabaseRef2 = FirebaseDatabase.getInstance().getReference().child("ReponseDemande");
        mBDListener =   mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (postSnapshot.child("t11").getValue().equals("Refusée")) {
                        DemandeClass demandeClass = postSnapshot.getValue(DemandeClass.class);
                        mUploads.add(demandeClass);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DemandeRefuserAdmin.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void onDeleteT(int position) {
        DemandeClass demandeClass = mUploads.get(position);
        String f = demandeClass.getT1()+demandeClass.getT2()+demandeClass.getT7()+demandeClass.getT8()+demandeClass.getT9();
        mDatabaseRef.child(f).removeValue();
        mDatabaseRef2.child(f).removeValue();


    }

    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mBDListener);
    }

}
