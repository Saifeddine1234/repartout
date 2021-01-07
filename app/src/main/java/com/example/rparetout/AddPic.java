package com.example.rparetout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddPic extends AppCompatActivity {
Button insert;
TextView select;
Uri fileUri;
CircleImageView circleImageView,iv;
String nom , prenom,cin, telephone,motdepasse,type,image;
DatabaseReference reference;
FirebaseDatabase firebaseDatabase;
StorageReference storageReference;
ProgressDialog progressDialog;

    private UploadTask loadtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pic);
        select=findViewById(R.id.selectimage);
        insert=findViewById(R.id.insererrep);
        circleImageView = findViewById(R.id.imageView11);
        iv = findViewById(R.id.iv);

        progressDialog=new ProgressDialog(this);
        storageReference = FirebaseStorage.getInstance().getReference("profil");
        Intent intent = getIntent();
        nom =   intent.getStringExtra("nomrep");
        prenom = intent.getStringExtra("prenomrep");
        cin =intent.getStringExtra("cinrep");
        telephone = intent.getStringExtra("telephonerep");
        motdepasse = intent.getStringExtra("mdprep");
        type =  intent.getStringExtra("typerep");
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                intent1.setType("image/*");
                startActivityForResult(intent1.createChooser(intent1, "Select Image"), 438);

            }
        });
        reference = FirebaseDatabase.getInstance().getReference("Reparateur");
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(AddPic.this)) buildDialog(AddPic.this).show();
                else {
                    if (circleImageView.getDrawable()==iv.getDrawable()) {
                        Toast.makeText(AddPic.this, "choisir une photo pour le reparateur", Toast.LENGTH_SHORT).show();
                    } else {
                        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AddPic.this);
                        builder.setTitle("Confirmer");
                        builder.setMessage("confirmer la Création d'un nouveau compte...");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Reparateur reparateur = new Reparateur(nom, prenom, cin, type, telephone, motdepasse, image);
                                reference.child(cin).setValue(reparateur);
                                Intent intent2 = new Intent(AddPic.this, ReparateurAdmin.class);
                                startActivity(intent2);
                                Toast.makeText(AddPic.this, "register success", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("Cancel",null);
                        builder.create().show();


                    }
                }

            }
        });

        }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Pas de connexion Internet");
        builder.setMessage("Vous devez disposer de données mobiles ou wifi pour y accéder. Appuyez sur ok pour quitter ..");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder;
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 438 && resultCode == RESULT_OK) {
            progressDialog.setTitle("Selectionner en cours ");
            progressDialog.setMessage("L'image est en cours d'enregistrer..");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            fileUri = data.getData();
            final StorageReference file = storageReference.child(System.currentTimeMillis() + "." + "jpg");
            loadtask = file.putFile(fileUri);
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
                        image = downloadUrl.toString();
                        select.setVisibility(View.INVISIBLE);
                      circleImageView.setImageURI(fileUri);
                        Toast.makeText(AddPic.this, "importation de l'image avec success", Toast.LENGTH_SHORT).show();




                    }
                }
            });

        }

    }
}
