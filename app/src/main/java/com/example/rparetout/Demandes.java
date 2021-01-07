package com.example.rparetout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Demandes extends AppCompatActivity implements ImageAdapter4.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ImageAdapter4 mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<DemandeClass> mUploads;
    Button flesh ;
    private ValueEventListener mBDListener ;
    String nomR , prenomR , telephoneR , motdepasseR , emailR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demandes);
        Intent inten = getIntent();
        nomR = inten.getStringExtra("nom");
        prenomR = inten.getStringExtra("prenom");
        telephoneR = inten.getStringExtra("telephone");
        emailR = inten.getStringExtra("email");
        motdepasseR = inten.getStringExtra("motdepasse");
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapter4(Demandes.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(Demandes.this);
        flesh = findViewById(R.id.flesh);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Demande");
        mBDListener =   mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if(postSnapshot.child("t1").getValue().toString().equals(telephoneR)){
                        DemandeClass demandeClass = postSnapshot.getValue(DemandeClass.class);
                        demandeClass.setT0(postSnapshot.getKey());
                        mUploads.add(demandeClass);
                    }


                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Demandes.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = getIntent();
                String nomC = inten.getStringExtra("nom");
                String prenomC = inten.getStringExtra("prenom");
                String telephoneC = inten.getStringExtra("telephone");
                String emailC = inten.getStringExtra("email");
                String motdepasseC = inten.getStringExtra("motdepasse");
                Intent intent5 = new Intent(getApplicationContext(), Menu.class);
                intent5.putExtra("nom", nomC);
                intent5.putExtra("prenom", prenomC);
                intent5.putExtra("telephone", telephoneC);
                intent5.putExtra("email", emailC);
                intent5.putExtra("motdepasse", motdepasseC);
                startActivity(intent5);
            } });
    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDelete(int position) {
DemandeClass demandeClass = mUploads.get(position);
     String ff = demandeClass.getT0();
      mDatabaseRef.child(ff).removeValue();


    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mBDListener);
    }

}
