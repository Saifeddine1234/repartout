package com.example.rparetout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
public class Autres extends AppCompatActivity implements ImageAdapterclient.OnItemClickSelect {
    String nomR,prenomR ,typeR,telephoneR,mdpR , cinR ,imageR;
    TextView traveaux ;
    ImageView flesh;
    private RecyclerView mRecyclerView;
    private ImageAdapterclient mAdapter;
    private DatabaseReference mDatabaseRef;
    private java.util.List<DemandeClass> mUploads;
    ValueEventListener mBDListener ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autres);
        flesh = findViewById(R.id.flesh);
        traveaux = findViewById(R.id.traveaux);
        mRecyclerView = findViewById(R.id.recycle_view);
        Intent intent = getIntent();
        nomR = intent.getStringExtra("nomrep");
        prenomR = intent.getStringExtra("prenomrep");
        telephoneR = intent.getStringExtra("telephonerep");
        typeR = intent.getStringExtra("typerep");
        mdpR = intent.getStringExtra("mdprep");
        cinR = intent.getStringExtra("cinrep");
        imageR = intent.getStringExtra("imagerep");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(Autres.this));
        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapterclient(Autres.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(Autres.this);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Demande");
        mBDListener =   mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (postSnapshot.child("t9").getValue().equals("Autre")) {
                        DemandeClass demandeClass = postSnapshot.getValue(DemandeClass.class);
                        demandeClass.setT12(nomR);
                        demandeClass.setT13(prenomR);
                        demandeClass.setT14(cinR);
                        demandeClass.setT15(telephoneR);
                        demandeClass.setT16(imageR);
                        mUploads.add(demandeClass);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Autres.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuClient.class);
                intent.putExtra("nomrep",nomR);
                intent.putExtra("prenomrep",prenomR);
                intent.putExtra("telephonerep",telephoneR);
                intent.putExtra("typerep",typeR);
                intent.putExtra("cinrep",cinR);
                intent.putExtra("imagerep",imageR);
                startActivity(intent);
                overridePendingTransition(R.anim.anim1,R.anim.anim2);

            }
        });
        traveaux.setText(typeR);
        traveaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), List.class);
                intent.putExtra("nomrep",nomR);
                intent.putExtra("prenomrep",prenomR);
                intent.putExtra("telephonerep",telephoneR);
                intent.putExtra("typerep",typeR);
                intent.putExtra("cinrep",cinR);
                intent.putExtra("imagerep",imageR);
                startActivity(intent);
                overridePendingTransition(R.anim.anim1,R.anim.anim2);

            }
        });
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MenuClient.class);
        intent.putExtra("nomrep",nomR);
        intent.putExtra("prenomrep",prenomR);
        intent.putExtra("telephonerep",telephoneR);
        intent.putExtra("typerep",typeR);
        intent.putExtra("cinrep",cinR);
        intent.putExtra("mdprep",mdpR);
        intent.putExtra("imagerep",imageR);
        startActivity(intent);
    }
}
