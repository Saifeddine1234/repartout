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
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.data.DataHolder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ImageAdapterclient extends RecyclerView.Adapter<ImageAdapterclient.ImageViewHolderclient> {
    private Context mContext;
    private List<DemandeClass> mUploads ;
    FragmentActivity mListener;
    DatabaseReference databaseReference , databaseReference2,databaseReference1;;
    public ImageAdapterclient(Context context , List<DemandeClass> uploads){
        mContext = context;
        mUploads = uploads;
    }
    @NonNull
    @Override
    public ImageViewHolderclient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.affichedemandes , parent , false);

        return new ImageViewHolderclient(v);

    }
    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolderclient holder, final int position) {
        final DemandeClass uploadCor =mUploads.get(position);
        holder.txt1.setText(uploadCor.getT2());
        holder.txt2.setText(uploadCor.getT1());
        holder.txt3.setText(uploadCor.getT3() +" "+uploadCor.getT6()+" "+uploadCor.getT5());
        holder.txt4.setText(uploadCor.getT8());
        holder.txt8.setText(uploadCor.getT7());
        holder.txt5.setText(uploadCor.getT10());
        holder.txt6.setText(uploadCor.getT4());
        holder.txt7.setText(uploadCor.getT11());
        holder.autre.setText(uploadCor.getAutre());
        holder.refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Demande");
                databaseReference = FirebaseDatabase.getInstance().getReference("ReponseDemande");
                databaseReference1 = FirebaseDatabase.getInstance().getReference("ReponseDemandeA");
                String T1 = uploadCor.getT1();
                String T0 = uploadCor.getT1();
                String T2 = uploadCor.getT2();
                String T3 = uploadCor.getT3();
                String T4 = uploadCor.getT4();
                String T5 = uploadCor.getT5();
                String T6 = uploadCor.getT6();
                String T7 = uploadCor.getT7();
                String T8 = uploadCor.getT8();
                String T9 = uploadCor.getT9();
                String T10 = uploadCor.getT10();
                String T11 = "Refusée";
                String T12 = uploadCor.getT12();
                String T13 = uploadCor.getT13();
                String T14 = uploadCor.getT14();
                String T15 = uploadCor.getT15();
                String T16 = uploadCor.getT16();
                String Autre = uploadCor.getAutre();

                DemandeClass demandeClass = new DemandeClass(T0,T1,T2,T3,T4,T5,T6,T7,T8,Autre ,T9,T10,T11,T12,T13,T14,T15,T16);

                databaseReference.push().setValue(demandeClass);
                databaseReference1.push().setValue(demandeClass);
                    Toast.makeText(mContext, "demande refusée", Toast.LENGTH_SHORT).show();
                    databaseReference2.child(uploadCor.getT1()+uploadCor.getT2()+uploadCor.getT7()+uploadCor.getT8()+uploadCor.getT9()).removeValue();
                    mUploads.remove(position);
                    notifyItemRemoved(position);
            }
        });

     holder.accepte.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Demande");
            databaseReference = FirebaseDatabase.getInstance().getReference("ReponseDemande");
            databaseReference1 = FirebaseDatabase.getInstance().getReference("ReponseDemandeA");
            String T0 = uploadCor.getT1();
            String T1 = uploadCor.getT1();
            String T2 = uploadCor.getT2();
            String T3 = uploadCor.getT3();
            String T4 = uploadCor.getT4();
            String T5 = uploadCor.getT5();
            String T6 = uploadCor.getT6();
            String T7 = uploadCor.getT7();
            String T8 = uploadCor.getT8();
            String T9 = uploadCor.getT9();
            String T10 = uploadCor.getT10();
            String T11 = "Acceptée";
            String T12 = uploadCor.getT12();
            String T13 = uploadCor.getT13();
            String T14 = uploadCor.getT14();
            String T15 = uploadCor.getT15();
            String T16 = uploadCor.getT16();
            String Autre = uploadCor.getAutre();
            DemandeClass demandeClass = new DemandeClass(T0,T1,T2,T3,T4,T5,T6,T7,T8,Autre ,T9,T10,T11,T12,T13,T14,T15,T16);

            databaseReference.child(T1+T2+T7+T8+T9).setValue(demandeClass);
            databaseReference1.child(T1+T2+T7+T8+T9).setValue(demandeClass);
            Toast.makeText(mContext, "demande acceptée", Toast.LENGTH_SHORT).show();
            databaseReference2.child(uploadCor.getT1()+uploadCor.getT2()+uploadCor.getT7()+uploadCor.getT8()+uploadCor.getT9()).removeValue();
            mUploads.remove(position);
            notifyItemRemoved(position);






        }
    });
}
    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolderclient extends RecyclerView.ViewHolder  {
        TextView txt1 , txt2 , txt3 , txt4 , txt5,txt6 , txt7,txt8 , autre;
        TextView refuse , accepte ;
        public ImageViewHolderclient(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.t1);
            txt2 = itemView.findViewById(R.id.t2);
            txt3 = itemView.findViewById(R.id.t3);
            txt4 = itemView.findViewById(R.id.t4);
            txt5 = itemView.findViewById(R.id.t5);
            txt6 = itemView.findViewById(R.id.t6);
            txt7 = itemView.findViewById(R.id.t7);
            txt8 = itemView.findViewById(R.id.t16);
            autre = itemView.findViewById(R.id.autre);
            refuse = itemView.findViewById(R.id.refuse);
            accepte = itemView.findViewById(R.id.accepte);
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

