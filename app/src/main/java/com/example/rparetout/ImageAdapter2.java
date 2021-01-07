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
public class ImageAdapter2 extends RecyclerView.Adapter<ImageAdapter2.ImageViewHolder2> {
    private Context mContext;
    private List<Reclamation> mUploads ;
    ImageAdapter2.OnItemClickListener mListener;
    public ImageAdapter2(Context context ,List<Reclamation> uploads){
        mContext = context;
        mUploads = uploads;
    }
    @NonNull
    @Override
    public ImageViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.affichereclamation , parent , false);
        return new ImageViewHolder2(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder2 holder, int position) {
        Reclamation uploadCor =mUploads.get(position);
        holder.nomA.setText(uploadCor.getNomRec());
        holder.msgA.setText(uploadCor.getMsgRec());
        holder.telA.setText(uploadCor.getTelRec());
    }
    @Override
    public int getItemCount() {
        return mUploads.size();
    }


    public interface OnItemClickSelect {
    }

    public class ImageViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        TextView nomA ,msgA , telA;
        public ImageViewHolder2(@NonNull View itemView) {
            super(itemView);
            nomA = itemView.findViewById(R.id.nomA);
            msgA = itemView.findViewById(R.id.msgA);
            telA = itemView.findViewById(R.id.telRec);
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
    public void setOnItemClickListener(ImageAdapter2.OnItemClickListener listener){
        mListener = listener;
    }
}




