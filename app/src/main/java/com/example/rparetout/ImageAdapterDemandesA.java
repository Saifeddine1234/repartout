package com.example.rparetout;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageAdapterDemandesA extends RecyclerView.Adapter<ImageAdapterDemandesA.ImageViewHolder4> {
    private Context mContext;
    private List<DemandeClass> mUploads ;
    OnItemClickListener mListener;
    public ImageAdapterDemandesA(Context context , List<DemandeClass> uploads){
        mContext = context;
        mUploads = uploads;
    }
    @NonNull
    @Override
    public ImageViewHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.affichedemandesadmin , parent , false);
        return new ImageViewHolder4(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder4 holder, int position) {
        DemandeClass uploadCor =mUploads.get(position);
        holder.txt7.setText(uploadCor.getT2());
        holder.txt8.setText(uploadCor.getT1());
        holder.txt1.setText(uploadCor.getT7());
        holder.txt9.setText(uploadCor.getT8());
        holder.txt2.setText(uploadCor.getT3()+" "+uploadCor.getT6()+" "+uploadCor.getT5());
        holder.txt3.setText(uploadCor.getT10());
        holder.txt4.setText(uploadCor.getT9());
        holder.txt5.setText(uploadCor.getT4());
        holder.autre.setText(uploadCor.getAutre());
    }
    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder4 extends RecyclerView.ViewHolder   implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        TextView txt1 , txt2 , txt3 , txt4 , txt5, txt7,txt8,txt9 , autre;
        public ImageViewHolder4(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.t1);
            txt2 = itemView.findViewById(R.id.t2);
            txt3 = itemView.findViewById(R.id.t3);
            txt4 = itemView.findViewById(R.id.t4);
            txt5 = itemView.findViewById(R.id.t5);
            txt7 = itemView.findViewById(R.id.t7);
            txt8 = itemView.findViewById(R.id.t8);
            txt9 = itemView.findViewById(R.id.t21);
            autre = itemView.findViewById(R.id.autre);
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
            MenuItem delete = menu.add(Menu.NONE, 1 , 1 , "Supprimer pour vous ");
            MenuItem deleteR = menu.add(Menu.NONE, 2 , 2 , "Supprimer pour le réparateur");
            MenuItem deleteT = menu.add(Menu.NONE, 3 , 3 , "Supprimer pour tout le monde");

            delete.setOnMenuItemClickListener(this);
            deleteR.setOnMenuItemClickListener(this);
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
                            mListener.onDeleteR(position);
                            return true;
                        case 3 :
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
        void onDeleteR(int position);
        void onDeleteT(int position);


    }
    public void setOnItemClickListener(ImageAdapterDemandesA.OnItemClickListener listener){
        mListener = listener;
    }
}




