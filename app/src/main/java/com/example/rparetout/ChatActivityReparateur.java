package com.example.rparetout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivityReparateur extends AppCompatActivity implements MessageAdapterReparateur.OnItemClickSelect {
    private RecyclerView mRecyclerView;
    private MessageAdapterReparateur mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<Chat> mUploads;
    ImageView flesh ;
   DatabaseReference reference,reference2;
    Toolbar toolbar ;
    ImageView send ;
    EditText messages ;
    CircleImageView circleImageView;
    private ValueEventListener mBDListener ;
    String  nomrep ,nomclient , message ,desc , date , dateC , nomR ,imageR, prenomR ,motdepasseR, cinR,telephoneR,typeR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatreparateur);
        Intent intent = getIntent();
        nomclient=  intent.getStringExtra("nomclient");
        desc=  intent.getStringExtra("desc");
        date=  intent.getStringExtra("date");
         nomR = intent.getStringExtra("nomrep");
         prenomR = intent.getStringExtra("prenomrep");
         telephoneR = intent.getStringExtra("telephonerep");
         cinR = intent.getStringExtra("cinrep");
         typeR = intent.getStringExtra("typerep");
         motdepasseR = intent.getStringExtra("mdprep");
        imageR = intent.getStringExtra("imagerep");


        flesh = findViewById(R.id.flesh);
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getApplicationContext(), ReponseReparateur.class);
                intent5.putExtra("nomrep", nomR);
                intent5.putExtra("prenomrep", prenomR);
                intent5.putExtra("telephonerep", telephoneR);
                intent5.putExtra("cinrep", cinR);
                intent5.putExtra("typerep", typeR);
                intent5.putExtra("typerep", typeR);
               intent5.putExtra("mdprep", motdepasseR);
                intent5.putExtra("imagerep", imageR);
                startActivity(intent5);
            } });


        send = findViewById(R.id.sendbtn);
        messages = findViewById(R.id.msg);
        toolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mUploads = new ArrayList<>();
        mAdapter = new MessageAdapterReparateur(ChatActivityReparateur.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(ChatActivityReparateur.this);
        nomrep = nomR+" "+prenomR;
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Chats");
        mBDListener =   mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (postSnapshot.child("reciver").getValue().toString().equals(nomclient) && postSnapshot.child("sender").getValue().toString().equals(nomrep) && postSnapshot.child("desc").getValue().toString().equals(desc) && postSnapshot.child("date").getValue().toString().equals(date) || postSnapshot.child("sender").getValue().toString().equals(nomclient) && postSnapshot.child("reciver").getValue().toString().equals(nomrep) && postSnapshot.child("desc").getValue().toString().equals(desc) && postSnapshot.child("date").getValue().toString().equals(date)) {
                        Chat chat = postSnapshot.getValue(Chat.class);
                        chat.setReparateur(nomclient);
                        chat.setClient(nomrep);
                        mUploads.add(chat);
                   }
                    mAdapter.notifyDataSetChanged();
                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ChatActivityReparateur.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        toolbar.setTitle("            " +nomclient);

      send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messages.getText().toString().equals("") || messages.getText().toString().equals(" ")) {
                    Toast.makeText(ChatActivityReparateur.this , "message vide",Toast.LENGTH_SHORT).show();
                } else {
                    reference = FirebaseDatabase.getInstance().getReference("Chats");
                    reference2 = FirebaseDatabase.getInstance().getReference("ChatsA");
                    dateC = String.valueOf(System.currentTimeMillis());
                    message = messages.getText().toString();
                    Chat chat = new Chat( nomrep,nomclient,message," ",desc,date,dateC,"text");
                    reference.push().setValue(chat);
                    reference2.push().setValue(chat);
                    messages.setText("");
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            Intent intent=new Intent(getApplicationContext(),ChatActivityReparateur.class);
                            intent.putExtra("nomclient" , nomclient);
                            intent.putExtra("desc" ,desc);
                            intent.putExtra("date" , date);
                            intent.putExtra("nomrep", nomR);
                            intent.putExtra("prenomrep", prenomR);
                            intent.putExtra("telephonerep", telephoneR);
                            intent.putExtra("cinrep",cinR);
                            intent.putExtra("typerep", typeR);
                            intent.putExtra("imagerep", imageR);
                            startActivity(intent);
                            overridePendingTransition(R.anim.anim1,R.anim.anim2);
                        }

                    },0);


                }
            }
        });
}

    @Override
    public void onItemSelect(int position) {

    }

    @Override
    public void onDelete(int position) {

    }

    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),ReponseReparateur.class);
        intent.putExtra("nomclient" , nomclient);
        intent.putExtra("desc" ,desc);
        intent.putExtra("date" , date);
        intent.putExtra("nomrep", nomR);
        intent.putExtra("prenomrep", prenomR);
        intent.putExtra("telephonerep", telephoneR);
        intent.putExtra("cinrep",cinR);
        intent.putExtra("typerep", typeR);
        intent.putExtra("imagerep", imageR);
        startActivity(intent);
    }
}
