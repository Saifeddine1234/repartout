package com.example.rparetout;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

public class ImageAdapterreparateurreponse extends RecyclerView.Adapter<ImageAdapterreparateurreponse.ImageViewHolderclient> {
    private Context mContext;
    private List<DemandeClass> mUploads ;
    OnItemClickListener mListener;
    DatabaseReference databaseReference ;
    public ImageAdapterreparateurreponse(Context context , List<DemandeClass> uploads){
        mContext = context;
        mUploads = uploads;
    }
    @NonNull
    @Override
    public ImageViewHolderclient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.reponsedemandereparateur , parent , false);
        return new ImageViewHolderclient(v);

    }
    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolderclient holder, final int position) {
        final DemandeClass uploadCor =mUploads.get(position);
        if (uploadCor.getT11().equals("Refusée")){
            holder.txt1.setText(uploadCor.getT2());
            holder.txt2.setText(uploadCor.getT1());
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
            holder.txt1.setText(uploadCor.getT2());
            holder.txt2.setText(uploadCor.getT1());
            holder.txt3.setText(uploadCor.getT7());
            holder.txt8.setText(uploadCor.getT8());
            holder.txt4.setText(uploadCor.getT3() +" "+uploadCor.getT6()+" "+uploadCor.getT5());
            holder.txt5.setText(uploadCor.getT9()+" "+uploadCor.getAutre());
            holder.txt6.setText(uploadCor.getT4());
            holder.txt7.setText(uploadCor.getT11());
            holder.envoyer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String cinrep = uploadCor.getT14();
                    final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Reparateur");
                    final Query checkUser = databaseReference1.orderByChild("cinrep").equalTo(cinrep);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.exists()) {

                            } else {

                                String motdepasseBD = dataSnapshot.child(cinrep).child("mdprep").getValue(String.class);
                                String imageBD = dataSnapshot.child(cinrep).child("imagerep").getValue(String.class);
                                Intent intent = new Intent(mContext , ChatActivityReparateur.class);
                                intent.putExtra("nomclient" , uploadCor.getT2());
                                intent.putExtra("desc" , uploadCor.getT4());
                                intent.putExtra("date" , uploadCor.getT8()+" "+uploadCor.getT7());
                                intent.putExtra("nomrep", uploadCor.getT12());
                                intent.putExtra("prenomrep", uploadCor.getT13());
                                intent.putExtra("telephonerep", uploadCor.getT15());
                                intent.putExtra("cinrep", uploadCor.getT14());
                                intent.putExtra("typerep", uploadCor.getT9());
                                  intent.putExtra("mdprep", motdepasseBD);
                                intent.putExtra("imagerep", imageBD);
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

    public class ImageViewHolderclient extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        TextView txt1 , txt2 , txt3 , txt4 , txt5,txt6 , txt7,txt8 , envoyer ;
        public ImageViewHolderclient(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.t1);
            txt2 = itemView.findViewById(R.id.t2);
            txt3 = itemView.findViewById(R.id.t3);
            txt4 = itemView.findViewById(R.id.t4);
            txt5 = itemView.findViewById(R.id.t5);
            txt6 = itemView.findViewById(R.id.t6);
            txt7 = itemView.findViewById(R.id.t7);
            txt8 = itemView.findViewById(R.id.t17);
            envoyer = itemView.findViewById(R.id.envoyerm);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }



        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }


        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select");
            MenuItem delete = menu.add(android.view.Menu.NONE, 1 , 1 , "Supprimer ");


            delete.setOnMenuItemClickListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(mListener != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    switch (item.getItemId()){
                        case 1 :
                            mListener.onDelete(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDelete(int position);



    }
    public void setOnItemClickListener(ReponseReparateur listener){
        mListener = listener;
    }
}




