package com.example.rparetout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity implements MessageAdapter.OnItemClickSelect {
    private static final int PERMISSION_CODE =  1000 ;
    private RecyclerView mRecyclerView;
    private static final int CAMERA_REQUEST_CODE = 1;
    StorageReference storageReference, storageReference2 , mStorage;
    DatabaseReference databaseReference, databaseReference2;
    private StorageTask loadtask, loadtask2,loadtask3,loadtask4;
    CircleImageView circleImageView;
    ProgressDialog progressDialog;
    String myUrl;
    Uri fileUri,uri;
    private MessageAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    ImageButton add , galerie;
    private List<Chat> mUploads;
    DatabaseReference reference, reference2;
    Toolbar toolbar;
    ImageView send , image;
    EditText messages;
    ImageView flesh;
    private ValueEventListener mBDListener;
    String photo;
    String nomC,prenomC,telephoneC,emailC,motdepasseC;
    String nomrep, nomclient, message, desc, date,dateC,imageR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
           mStorage = FirebaseStorage.getInstance().getReference();

          final Intent intent = getIntent();
        nomrep = intent.getStringExtra("nomrep");
        nomclient = intent.getStringExtra("nomclient");
        desc = intent.getStringExtra("desc");
        date = intent.getStringExtra("date");
        nomC = intent.getStringExtra("nom");
        prenomC = intent.getStringExtra("prenom");
        telephoneC = intent.getStringExtra("telephone");
        emailC = intent.getStringExtra("email");
        motdepasseC = intent.getStringExtra("motdepasse");
        imageR = intent.getStringExtra("imager");

        flesh = findViewById(R.id.flesh);
        mStorage = FirebaseStorage.getInstance().getReference();
        flesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getApplicationContext(), ReponseClient.class);
                intent5.putExtra("nomrep" , nomrep);
                intent5.putExtra("nomclient" , nomclient);
                intent5.putExtra("desc" ,desc);
                intent5.putExtra("date" , date);
                intent5.putExtra("nom", nomC);
                intent5.putExtra("prenom", prenomC);
                intent5.putExtra("telephone", telephoneC);
                intent5.putExtra("email", emailC);
                intent5.putExtra("motdepasse", motdepasseC);
                intent5.putExtra("imager", imageR);
                startActivity(intent5);
            }
        });
        send = findViewById(R.id.sendbtn);
        messages = findViewById(R.id.msg);
        toolbar = findViewById(R.id.toolbar);
        storageReference = FirebaseStorage.getInstance().getReference("Chats");
        databaseReference = FirebaseDatabase.getInstance().getReference("Chats");
        storageReference2 = FirebaseStorage.getInstance().getReference("ChatsA");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("ChatsA");
        galerie = findViewById(R.id.galerie);
        image = findViewById(R.id.image);

        add = findViewById(R.id.add);
        galerie.setOnClickListener(new View.OnClickListener() {
                @Override
                                       public void onClick(View v) {
                                           Intent intent1 = new Intent();
                                           intent1.setAction(Intent.ACTION_GET_CONTENT);
                                           intent1.setType("image/*");
                                           startActivityForResult(intent1.createChooser(intent1, "Select Image"), 438);


                                       }
                                   });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AskCameraPermissions();
            }
        });
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mUploads = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        mAdapter = new MessageAdapter(ChatActivity.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(ChatActivity.this);
        progressDialog = new ProgressDialog(ChatActivity.this);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Chats");
        mBDListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Chat chat = postSnapshot.getValue(Chat.class);
                    if (postSnapshot.child("reciver").getValue().toString().equals(nomrep) && postSnapshot.child("sender").getValue().toString().equals(nomclient) && postSnapshot.child("desc").getValue().toString().equals(desc) && postSnapshot.child("date").getValue().toString().equals(date)  || postSnapshot.child("sender").getValue().toString().equals(nomrep) && postSnapshot.child("reciver").getValue().toString().equals(nomclient) && postSnapshot.child("desc").getValue().toString().equals(desc) && postSnapshot.child("date").getValue().toString().equals(date)) {
                        chat.setReparateur(nomclient);
                        chat.setClient(nomrep);
                        mUploads.add(chat);
                    }
                    mAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ChatActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        toolbar.setTitle("                   " + nomrep);
        circleImageView = findViewById(R.id.imagereparateur);
        Picasso.with(ChatActivity.this)
                .load(imageR)
                .fit()
                .centerInside()
                .into(circleImageView);

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),AfficheimageRep.class);
                intent1.putExtra("imager",imageR);
                startActivity(intent1);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messages.getText().toString().equals("") || messages.getText().toString().equals(" ")) {
                    Toast.makeText(ChatActivity.this, "message vide ", Toast.LENGTH_SHORT).show();
                } else {
                    reference = FirebaseDatabase.getInstance().getReference("Chats");
                    reference2 = FirebaseDatabase.getInstance().getReference("ChatsA");
                    dateC = String.valueOf(System.currentTimeMillis());
                    message = messages.getText().toString();
                    Chat chat = new Chat( nomclient,nomrep, message, " ", desc, date, dateC, "text");
                    reference.push().setValue(chat);
                    reference2.push().setValue(chat);
                    messages.setText("");
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run(){

                            Intent intents=new Intent(getApplicationContext(),ChatActivity.class);
                            intents.putExtra("nomrep" , nomrep);
                            intents.putExtra("nomclient" , nomclient);
                            intents.putExtra("desc" ,desc);
                            intents.putExtra("date" , date);
                            intents.putExtra("nom", nomC);
                            intents.putExtra("prenom", prenomC);
                            intents.putExtra("telephone", telephoneC);
                            intents.putExtra("email", emailC);
                            intents.putExtra("motdepasse", motdepasseC);
                            intents.putExtra("imager", imageR);
                            startActivity(intents);
                            overridePendingTransition(R.anim.anim1,R.anim.anim2);
                        }

                    },0);



                }
                }

        });

    }

    private void AskCameraPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                {
                    String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permission, PERMISSION_CODE);
                }
            } else {
                openCamera();
            }

        } else {
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE : {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }else{
                    Toast.makeText(this,"no permission", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"nouvelle image");
        values.put(MediaStore.Images.Media.DESCRIPTION,"de camera");
        uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Toast.makeText(ChatActivity.this, "camera ouvert", Toast.LENGTH_SHORT).show();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
        mAdapter.notifyDataSetChanged();
            }
    @Override
    public void onItemSelect(int position) {

    }

    @Override
    public void onDelete(int position) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 438 && resultCode == RESULT_OK) {
            progressDialog.setTitle("Envoi en cours ");
            progressDialog.setMessage("L'image est en cours d' envoyer au destinataire..");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            fileUri = data.getData();
                StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Chats");
                StorageReference storageReference2 = FirebaseStorage.getInstance().getReference().child("ChatsA");
                dateC = String.valueOf(System.currentTimeMillis());
                message = messages.getText().toString();
                final StorageReference file = storageReference.child(System.currentTimeMillis() + "." + "jpg");
                final StorageReference file2 = storageReference2.child(System.currentTimeMillis() + "." + "jpg");
                loadtask = file.putFile(fileUri);
                loadtask2 = file2.putFile(fileUri);
                loadtask.continueWithTask(new Continuation() {
                    @Override
                    public Object then(@NonNull Task task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return file.getDownloadUrl();
                    }


                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Uri downloadUrl = task.getResult();
                            myUrl = downloadUrl.toString();
                            Chat chat = new Chat(nomclient, nomrep, "no", myUrl, desc, date, dateC, "image");
                            String updateId = databaseReference.push().getKey();
                            databaseReference.child(updateId).setValue(chat);
                            new Handler().postDelayed(new Runnable(){
                                @Override
                                public void run(){
                                    Intent intents=new Intent(getApplicationContext(),ChatActivity.class);
                                    intents.putExtra("nomrep" , nomrep);
                                    intents.putExtra("nomclient" , nomclient);
                                    intents.putExtra("desc" ,desc);
                                    intents.putExtra("date" , date);
                                    intents.putExtra("nom", nomC);
                                    intents.putExtra("prenom", prenomC);
                                    intents.putExtra("telephone", telephoneC);
                                    intents.putExtra("email", emailC);
                                    intents.putExtra("motdepasse", motdepasseC);
                                    intents.putExtra("imager", imageR);
                                    startActivity(intents);
                                    overridePendingTransition(R.anim.anim1,R.anim.anim2);
                                }

                            },0);



                        }
                    }
                });
                loadtask2.continueWithTask(new Continuation() {
                    @Override
                    public Object then(@NonNull Task task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return file2.getDownloadUrl();
                    }


                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Uri downloadUrl1 = task.getResult();
                            myUrl = downloadUrl1.toString();
                            Chat chat = new Chat(nomclient, nomrep, "no", myUrl, desc, date, dateC, "image");
                            String updateId4 = databaseReference2.push().getKey();
                            databaseReference2.child(updateId4).setValue(chat);


                        }
                    }
                });

        } else if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            progressDialog.setTitle("Envoi en cours ");
            progressDialog.setMessage("L'image est en cours d' envoyer au destinataire..");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAdapter.notifyDataSetChanged();
           final StorageReference filepath =storageReference.child(System.currentTimeMillis() + "." + "jpeg");
            final StorageReference filepath2 = storageReference2.child(System.currentTimeMillis() + "." + "jpeg");

            loadtask3 = filepath.putFile(uri);
            loadtask4 = filepath2.putFile(uri);

            loadtask3.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return filepath.getDownloadUrl();
                }


            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Uri downloadUrl1 = task.getResult();
                        myUrl = downloadUrl1.toString();
                        dateC = String.valueOf(System.currentTimeMillis());
                        Chat chat = new Chat(nomclient, nomrep, "no", myUrl, desc, date, dateC, "image");
                        String updateId2 = databaseReference.push().getKey();
                        databaseReference.child(updateId2).setValue(chat);
                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run(){

                                Intent intents=new Intent(getApplicationContext(),ChatActivity.class);
                                intents.putExtra("nomrep" , nomrep);
                                intents.putExtra("nomclient" , nomclient);
                                intents.putExtra("desc" ,desc);
                                intents.putExtra("date" , date);
                                intents.putExtra("nom", nomC);
                                intents.putExtra("prenom", prenomC);
                                intents.putExtra("telephone", telephoneC);
                                intents.putExtra("email", emailC);
                                intents.putExtra("motdepasse", motdepasseC);
                                intents.putExtra("imager", imageR);

                                startActivity(intents);
                                overridePendingTransition(R.anim.anim1,R.anim.anim2);
                            }

                        },0);


                    }
                }
            });

            loadtask4.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return filepath2.getDownloadUrl();
                }


            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Uri downloadUrl1 = task.getResult();
                        myUrl = downloadUrl1.toString();
                        dateC = String.valueOf(System.currentTimeMillis());
                        Chat chat = new Chat(nomclient, nomrep, "no", myUrl, desc, date, dateC, "image");
                        String updateId4 = databaseReference2.push().getKey();
                        databaseReference2.child(updateId4).setValue(chat);
                    }
                }
            });

        }else {

        }
    }

    @Override
    public void onBackPressed() {
        Intent intents=new Intent(getApplicationContext(),ReponseClient.class);
        intents.putExtra("nomrep" , nomrep);
        intents.putExtra("nomclient" , nomclient);
        intents.putExtra("desc" ,desc);
        intents.putExtra("date" , date);
        intents.putExtra("nom", nomC);
        intents.putExtra("prenom", prenomC);
        intents.putExtra("telephone", telephoneC);
        intents.putExtra("email", emailC);
        intents.putExtra("motdepasse", motdepasseC);
        intents.putExtra("imager", imageR);
        startActivity(intents);
    }

}



