package com.example.rparetout;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ImageAdapterclientreponse extends RecyclerView.Adapter<ImageAdapterclientreponse.ImageViewHolderclient> {
    private Context mContext;
    private List<DemandeClass> mUploads ;
    FragmentActivity mListener;
   // DatabaseReference databaseReference , databaseReference2;
    public ImageAdapterclientreponse(Context context , List<DemandeClass> uploads){
        mContext = context;
        mUploads = uploads;
    }
    @NonNull
    @Override
    public ImageViewHolderclient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.reponsedemandeclient , parent , false);

        return new ImageViewHolderclient(v);

    }
    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolderclient holder, final int position) {
        final DemandeClass uploadCor =mUploads.get(position);
        if (uploadCor.getT11().equals("Refusée")){
            holder.txt1.setText(uploadCor.getT12()+"" +uploadCor.getT13());
            holder.txt2.setText(uploadCor.getT15());
            holder.txt3.setText(uploadCor.getT7());
            holder.txt8.setText(uploadCor.getT8());
            holder.txt4.setText(uploadCor.getT3() +" "+uploadCor.getT6()+" "+uploadCor.getT5());
            holder.txt5.setText(uploadCor.getT9()+" "+uploadCor.getAutre());
            holder.txt6.setText(uploadCor.getT4());
            holder.txt7.setText(uploadCor.getT11());
            holder.envoyer.setText("");
            holder.envoyer.setBackgroundColor(0xFFFFFFFF);
            holder.txt7.setTextColor(0xD5FF0000);
        }else {
            holder.txt1.setText(uploadCor.getT12()+" " +uploadCor.getT13());
            holder.txt2.setText(uploadCor.getT15());
            holder.txt3.setText(uploadCor.getT7());
            holder.txt8.setText(uploadCor.getT8());
            holder.txt4.setText(uploadCor.getT3() +" "+uploadCor.getT6()+" "+uploadCor.getT5());
            holder.txt5.setText(uploadCor.getT9()+" "+uploadCor.getAutre());
            holder.txt6.setText(uploadCor.getT4());
            holder.txt7.setText(uploadCor.getT11());
            holder.envoyer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String num = uploadCor.getT1();
                    final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Clients");
                    final Query checkUser = databaseReference1.orderByChild("telephone").equalTo(num);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.exists()) {

                            } else {
                                    String nomBD = dataSnapshot.child(num).child("nom").getValue(String.class);
                                    String prenomBD = dataSnapshot.child(num).child("prenom").getValue(String.class);
                                    String telephoneBD = dataSnapshot.child(num).child("telephone").getValue(String.class);
                                    String emailBD = dataSnapshot.child(num).child("email").getValue(String.class);
                                String motdepasseBD = dataSnapshot.child(num).child("motdepasse").getValue(String.class);
                                Intent intent = new Intent(mContext , ChatActivity.class);
                                intent.putExtra("nomrep" , uploadCor.getT12()+" "+uploadCor.getT13());
                                intent.putExtra("nomclient" , uploadCor.getT2());
                                intent.putExtra("desc" , uploadCor.getT4());
                                intent.putExtra("date" , uploadCor.getT8()+" "+uploadCor.getT7());
                                intent.putExtra("imager" , uploadCor.getT16());
                                intent.putExtra("nom", nomBD);
                                intent.putExtra("prenom", prenomBD);
                                intent.putExtra("telephone", telephoneBD);
                                intent.putExtra("email", emailBD);
                                intent.putExtra("motdepasse", motdepasseBD);
                                mContext.startActivity(intent);
                                }

                            }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            });
            holder.txt7.setTextColor(0xE432C20A);
        }



}
    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolderclient extends RecyclerView.ViewHolder  {
        TextView txt1 , txt2 , txt3 , txt4 , txt5,txt6 , txt7,txt8 ,envoyer ;
        public ImageViewHolderclient(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.t1);
            txt2 = itemView.findViewById(R.id.t2);
            txt3 = itemView.findViewById(R.id.t3);
            txt4 = itemView.findViewById(R.id.t4);
            txt5 = itemView.findViewById(R.id.t5);
            txt6 = itemView.findViewById(R.id.t6);
            txt7 = itemView.findViewById(R.id.t7);
            txt8 = itemView.findViewById(R.id.t20);
            envoyer = itemView.findViewById(R.id.envoyerm);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                        }
                    }
                }
            });

        }
    }
    public interface OnItemClickSelect{
        void onItemSelect(int position);
        void onDelete(int position);

    }
    public void setOnItemClickListener(FragmentActivity listener){
        mListener = listener;
    }
}

