package com.example.rparetout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class AfficheReparateur extends AppCompatActivity  implements ImageAdapter3.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private ImageAdapter3 mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<Reparateur> mUploads;
    private ValueEventListener mBDListener ;
    ImageView flesh ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_reparateur);
        mRecyclerView = findViewById(R.id.recycler_view);
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfficheReparateur.this,ReparateurAdmin.class);
                startActivity(intent);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapter3(AfficheReparateur.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(AfficheReparateur.this);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Reparateur");
        mBDListener =   mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Reparateur reparateur = postSnapshot.getValue(Reparateur.class);
                    mUploads.add(reparateur);
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AfficheReparateur.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDelete(int position) {
        Reparateur reparateu = mUploads.get(position);
        String ff = reparateu.getCinrep();
        mDatabaseRef.child(ff).removeValue();


    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mBDListener);
    }

}
