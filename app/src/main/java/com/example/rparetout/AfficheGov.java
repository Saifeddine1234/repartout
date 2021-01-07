package com.example.rparetout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
public class AfficheGov extends AppCompatActivity   implements ImageAdapterGouv.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ImageAdapterGouv mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<Gov> mUploads;
    private ValueEventListener mBDListener ;
    Button flesh ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_gov);
        mRecyclerView = findViewById(R.id.recycler_view);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfficheGov.this,GovAdmin.class);
                startActivity(intent);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapterGouv(AfficheGov.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(AfficheGov.this);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Governement");
        mBDListener =   mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Gov types = postSnapshot.getValue(Gov.class);
                    mUploads.add(types);
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AfficheGov.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDelete(int position) {
        Gov reparateu = mUploads.get(position);
        String ff = reparateu.getNomG();
        mDatabaseRef.child(ff).removeValue();

    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mBDListener);
    }

}

