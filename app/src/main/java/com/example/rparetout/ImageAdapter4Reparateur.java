package com.example.rparetout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageAdapter4Reparateur extends RecyclerView.Adapter<ImageAdapter4Reparateur.ImageViewHolder4> {
    private Context mContext;
    private List<DemandeClass> mUploads ;
    OnItemClickSelect mListener;
    public ImageAdapter4Reparateur(Context context , List<DemandeClass> uploads){
        mContext = context;
        mUploads = uploads;
    }
    @NonNull
    @Override
    public ImageViewHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.listdemanderep , parent , false);
        return new ImageViewHolder4(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder4 holder, int position) {
        DemandeClass uploadCor =mUploads.get(position);
        holder.txt1.setText(uploadCor.getT2());
        holder.txt2.setText(uploadCor.getT1());
        holder.txt3.setText(uploadCor.getT7());
        holder.txt4.setText(uploadCor.getT3()+" "+uploadCor.getT6()+" "+uploadCor.getT5());
        holder.txt5.setText(uploadCor.getT10());
        holder.txt6.setText(uploadCor.getT9());
        holder.txt8.setText(uploadCor.getT8());
        holder.autre.setText(uploadCor.getAutre());
        holder.txt7.setText(uploadCor.getT4());
    }
    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder4 extends RecyclerView.ViewHolder  {
        TextView txt1 , txt2 , txt3 , txt4 , txt5,txt6 , txt7,txt8, autre;
        public ImageViewHolder4(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.t1);
            txt2 = itemView.findViewById(R.id.t2);
            txt3 = itemView.findViewById(R.id.t3);
            txt4 = itemView.findViewById(R.id.t4);
            txt5 = itemView.findViewById(R.id.t5);
            txt6 = itemView.findViewById(R.id.t6);
            txt7 = itemView.findViewById(R.id.t7);
            txt8 = itemView.findViewById(R.id.t19);
            autre = itemView.findViewById(R.id.autre);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemSelect(position);
                        }
                    }
                }
            });
     /*       itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    menu.setHeaderTitle("Select");
                    MenuItem delete = menu.add(Menu.NONE, 1 , 1 , "Supprimer");
                    delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if(mListener != null) {
                                int position = getAdapterPosition();
                                if (position != RecyclerView.NO_POSITION) {
                                    switch (item.getItemId()){
                                        case 1 :
                                            mListener.onDelete(position);
                                            return true;
                                    }
                                }
                            }
                            return  false ;
                        }
                    });
                }
            });*/
        }


    }
    public interface OnItemClickSelect{
        void onItemSelect(int position);
        void onDelete(int position);

    }
    public void setOnItemClickListener(OnItemClickSelect listener){
        mListener = listener;
    }
}

