package com.example.rparetout;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class ImageAdapterAccepte extends RecyclerView.Adapter<ImageAdapterAccepte.ImageViewHolderclient> {
    private Context mContext;
    private List<DemandeClass> mUploads ;
   // FragmentActivity mListener;
    OnItemClickListener mListener;
    DatabaseReference databaseReference ;
    public ImageAdapterAccepte(Context context , List<DemandeClass> uploads){
        mContext = context;
        mUploads = uploads;
    }
    @NonNull
    @Override
    public ImageViewHolderclient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.reponsedemandeadmin , parent , false);
        return new ImageViewHolderclient(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolderclient holder, final int position) {
        final DemandeClass uploadCor =mUploads.get(position);
            holder.txt1.setText(uploadCor.getT2());
            holder.txt2.setText(uploadCor.getT1());
            holder.txt3.setText(uploadCor.getT12()+" "+uploadCor.getT13());
            holder.txt4.setText(uploadCor.getT15());
            holder.txt5.setText(uploadCor.getT7());
        holder.txt11.setText(uploadCor.getT8());
            holder.txt6.setText(uploadCor.getT3() +" "+uploadCor.getT6()+" "+uploadCor.getT5());
            holder.txt7.setText(uploadCor.getT9());
            holder.txt8.setText(uploadCor.getT10());
            holder.txt9.setText(uploadCor.getT4());
            holder.autre.setText(uploadCor.getAutre());
            holder.txt10.setText(uploadCor.getT11());
            holder.txt10.setTextColor(0xE432C20A);
        holder.envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , ChatActivityAdmin.class);
                intent.putExtra("nomclient" , uploadCor.getT2());
                intent.putExtra("desc" , uploadCor.getT4());
                intent.putExtra("date" , uploadCor.getT8()+" "+uploadCor.getT7());
                intent.putExtra("nomrep", uploadCor.getT12());
                intent.putExtra("prenomrep", uploadCor.getT13());
                intent.putExtra("telephonerep", uploadCor.getT15());
                intent.putExtra("cinrep", uploadCor.getT14());
                intent.putExtra("typerep", uploadCor.getT9());
                //  intent.putExtra("mdprep", motdepasseR);
                mContext.startActivity(intent);
            }
        });
}
    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolderclient extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        TextView txt1 , txt2 , txt3 , txt4 , txt5,txt6 , txt7,txt8,txt9,txt10,txt11 , autre ,envoyer  ;
        public ImageViewHolderclient(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.t1);
            txt2 = itemView.findViewById(R.id.t2);
            txt3 = itemView.findViewById(R.id.t3);
            txt4 = itemView.findViewById(R.id.t4);
            txt5 = itemView.findViewById(R.id.t5);
            txt6 = itemView.findViewById(R.id.t6);
            txt7 = itemView.findViewById(R.id.t7);
            txt8 = itemView.findViewById(R.id.t8);
            txt9 = itemView.findViewById(R.id.t9);
            txt10 = itemView.findViewById(R.id.t10);
            txt11 = itemView.findViewById(R.id.t18);
            autre = itemView.findViewById(R.id.autre);
            envoyer = itemView.findViewById(R.id.envoyer);
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
            MenuItem delete = menu.add(android.view.Menu.NONE, 1 , 1 , "Supprimer pour vous ");
            MenuItem deleteT = menu.add(Menu.NONE, 2 , 2, "Supprimer pour tout le monde");

            delete.setOnMenuItemClickListener(this);
            deleteT.setOnMenuItemClickListener(this);
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
                        case 2:
                            mListener.onDeleteT(position);
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
        void onDeleteT(int position);


    }
    public void setOnItemClickListener(ImageAdapterAccepte.OnItemClickListener listener){
        mListener = listener;
    }
}




