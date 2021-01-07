package com.example.rparetout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ChatActivityAdmin extends AppCompatActivity implements MessageAdapterAdmin.OnItemClickSelect {
    private RecyclerView mRecyclerView;
    private MessageAdapterAdmin mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<Chat> mUploads;
    Button flesh ;
   DatabaseReference reference , reference2;
    Toolbar toolbar ;
    ImageView send ;
    EditText messages ;
    private ValueEventListener mBDListener ;
    String  nomrep ,nomclient , message ,desc , date , dateC , nomR , prenomR , cinR,telephoneR,typeR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatadmin);
        Intent intent = getIntent();
        nomclient=  intent.getStringExtra("nomclient");
        desc=  intent.getStringExtra("desc");
        date=  intent.getStringExtra("date");
         nomR = intent.getStringExtra("nomrep");
         prenomR = intent.getStringExtra("prenomrep");
         telephoneR = intent.getStringExtra("telephonerep");
         cinR = intent.getStringExtra("cinrep");
         typeR = intent.getStringExtra("typerep");
        send = findViewById(R.id.sendbtn);
        messages = findViewById(R.id.msg);
        toolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mUploads = new ArrayList<>();
        mAdapter = new MessageAdapterAdmin(ChatActivityAdmin.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(ChatActivityAdmin.this);
        nomrep = nomR+" "+prenomR;
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("ChatsA");
        mBDListener =   mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                  if (postSnapshot.child("reciver").getValue().toString().equals(nomclient)&&postSnapshot.child("sender").getValue().toString().equals(nomrep)&&postSnapshot.child("desc").getValue().toString().equals(desc) &&postSnapshot.child("date").getValue().toString().equals(date)||postSnapshot.child("sender").getValue().toString().equals(nomclient)&&postSnapshot.child("reciver").getValue().toString().equals(nomrep)&&postSnapshot.child("desc").getValue().toString().equals(desc) &&postSnapshot.child("date").getValue().toString().equals(date)) {
                        Chat chat = postSnapshot.getValue(Chat.class);
                        chat.setReparateur(nomrep);
                        chat.setClient(nomclient);
                        mUploads.add(chat);
                   }

                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ChatActivityAdmin.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent intent5 = new Intent(getApplicationContext(), DemandeAccepterAdmin.class);
                                         startActivity(intent5);
                                     }
                                 });
        toolbar.setTitle(" ");

}

    @Override
    public void onItemSelect(int position) {

    }

    @Override
    public void onDelete(int position) {

    }


}
