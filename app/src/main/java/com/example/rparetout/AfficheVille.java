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

public class AfficheVille extends AppCompatActivity implements ImageAdapterVille.OnItemClickListener {
    Button flesh;
    private RecyclerView mRecyclerView;
    private ImageAdapterVille mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<Ville> mUploads;
    private ValueEventListener mBDListener ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_ville);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfficheVille.this,VilleAdmin.class);
                startActivity(intent);
            }
        });
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapterVille(AfficheVille.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(AfficheVille.this);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Ville");
        mBDListener =   mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Ville ville = postSnapshot.getValue(Ville.class);
                    mUploads.add(ville);
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AfficheVille.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDelete(int position) {
        Ville reparateu = mUploads.get(position);
        String ff = reparateu.getNomV();
        mDatabaseRef.child(ff).removeValue();


    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mBDListener);
    }

}
