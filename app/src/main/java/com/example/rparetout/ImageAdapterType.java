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

public class ImageAdapterType extends RecyclerView.Adapter<ImageAdapterType.ImageViewHolder> {
    private Context mContext;
    private List<Types> mUploads;
    ImageAdapterType.OnItemClickListener mListener;
    public ImageAdapterType(Context context, List<Types> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.affichetype, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Types uploadCor = mUploads.get(position);
        holder.typenom.setText(uploadCor.getNomType());
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }
    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        TextView  typenom;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            typenom = itemView.findViewById(R.id.typenom);
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
            void onItemClick(int position);
            void onDelete(int position);


        }
        public void setOnItemClickListener(ImageAdapterType.OnItemClickListener listener){
            mListener = listener;
        }
    }


