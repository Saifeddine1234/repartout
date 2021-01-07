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
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Client> mUploads ;
    OnItemClickListener mListener;

    public ImageAdapter(Context context ,List<Client> uploads){
        mContext = context;
        mUploads = uploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.affiche_user , parent , false);
        return new ImageViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Client uploadCor =mUploads.get(position);
        holder.nomA.setText(uploadCor.getNom()+" "+uploadCor.getPrenom());
        holder.emailA.setText(uploadCor.getEmail());
        holder.telephoneA.setText(uploadCor.getTelephone());
        holder.mdpA.setText(uploadCor.getMotdepasse());
    }
    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,View.OnCreateContextMenuListener , MenuItem.OnMenuItemClickListener {

        TextView nomA , telephoneA , emailA , mdpA;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            nomA = itemView.findViewById(R.id.nomT);
            emailA = itemView.findViewById(R.id.emailT);
            telephoneA = itemView.findViewById(R.id.telT);
            mdpA = itemView.findViewById(R.id.mdpT);
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
        public void setOnItemClickListener(ImageAdapter.OnItemClickListener listener){
            mListener = listener;
        }
    }
