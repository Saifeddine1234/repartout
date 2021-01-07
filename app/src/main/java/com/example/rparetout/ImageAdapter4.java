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

public class ImageAdapter4 extends RecyclerView.Adapter<ImageAdapter4.ImageViewHolder4> {
    private Context mContext;
    private List<DemandeClass> mUploads ;
    OnItemClickListener mListener;
    public ImageAdapter4(Context context , List<DemandeClass> uploads){
        mContext = context;
        mUploads = uploads;
    }
    @NonNull
    @Override
    public ImageViewHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.affichedemandesclients , parent , false);
        return new ImageViewHolder4(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder4 holder, int position) {
        DemandeClass uploadCor =mUploads.get(position);
int val = position + 1;
        holder.txt7.setText("Demande n° : " + val);
        holder.txt1.setText(uploadCor.getT7());
        holder.txt8.setText(uploadCor.getT8());
        holder.txt2.setText(uploadCor.getT3()+" "+uploadCor.getT6()+" "+uploadCor.getT5());
        holder.txt3.setText(uploadCor.getT10());
        holder.txt4.setText(uploadCor.getT9());
        holder.txt5.setText(uploadCor.getT4());
        holder.txt6.setText(uploadCor.getT11());
        holder.autre.setText(uploadCor.getAutre());
    }
    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener ,View.OnCreateContextMenuListener ,MenuItem.OnMenuItemClickListener {
        TextView txt1 , txt2 , txt3 , txt4 , txt5,txt6 , txt7,txt8 , autre;
        public ImageViewHolder4(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.t1);
            txt2 = itemView.findViewById(R.id.t2);
            txt3 = itemView.findViewById(R.id.t3);
            txt4 = itemView.findViewById(R.id.t4);
            txt5 = itemView.findViewById(R.id.t5);
            txt6 = itemView.findViewById(R.id.t6);
            txt7 = itemView.findViewById(R.id.t7);
            txt8 = itemView.findViewById(R.id.t14);
            autre = itemView.findViewById(R.id.autre);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }


        @Override
        public void onClick(View v) {
            if(mListener != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select");
            MenuItem delete = menu.add(Menu.NONE, 1 , 1 , "Supprimer");
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
                    }
                }
            }
            return false;
        }
    }
    public interface OnItemClickListener{
     //   void onItemSelect(int position);
     void onItemClick(int position);
        void onDelete(int position);


    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}

