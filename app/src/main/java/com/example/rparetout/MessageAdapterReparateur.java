package com.example.rparetout;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MessageAdapterReparateur extends RecyclerView.Adapter<MessageAdapterReparateur.ImageViewHolderclient> {
    final static int ITEM_LEFT = 0 ;
    final static int ITEM_RIGHT = 1 ;
    private Context mContext;
    private List<Chat> mUploads ;
    FragmentActivity mListener;
    DatabaseReference databaseReference;
    public MessageAdapterReparateur(Context context , List<Chat> uploads){
        mContext = context;
        mUploads = uploads;
    }
    @NonNull
    @Override
    public ImageViewHolderclient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_LEFT) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new ImageViewHolderclient(v);
        } else {
            View v = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new ImageViewHolderclient(v);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolderclient holder, final int position) {
        final Chat uploadCor =mUploads.get(position);
        Calendar calendar = Calendar.getInstance(Locale.CANADA);
        calendar.setTimeInMillis(Long.parseLong(uploadCor.getDateC()));
        String datetime = DateFormat.format("dd/MM/yyyy hh:mm aa",calendar).toString();
        if (uploadCor.getType().equals("text")){
            holder.txt1.setVisibility(View.INVISIBLE);
            holder.txt7.setText(uploadCor.getMessage());
            holder.txt.setText(datetime);
        }else {
            Picasso.with(mContext)
                    .load(uploadCor.getImage())
                    .fit()
                    .centerInside()
                    .into(holder.txt1);
            holder.txt1.getLayoutParams().height = 500;
            holder.txt1.getLayoutParams().width = 500;
            holder.txt1.requestLayout();
            holder.txt.setText(datetime);
            holder.txt7.setVisibility(View.INVISIBLE);
         holder.RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(mContext,AfficheImage.class);
                    intent.putExtra("image",uploadCor.getImage());
                    mContext.startActivity(intent);
                    mListener.overridePendingTransition(R.anim.anim1,R.anim.anim2);

                }
            });


        }
    }
    @Override
    public int getItemCount() {
        return mUploads.size();
    }
    @Override
    public int getItemViewType(int position) {
        if(mUploads.get(position).getSender().equals(mUploads.get(position).getClient())){
            return ITEM_LEFT;
        }else {
            return ITEM_RIGHT;
        }
    }

    public class ImageViewHolderclient extends RecyclerView.ViewHolder  {
        TextView txt7 ,txt;
        ImageView txt1;
        RelativeLayout RL;
        public ImageViewHolderclient(@NonNull View itemView) {
            super(itemView);
            txt7 = itemView.findViewById(R.id.show_message);
            txt = itemView.findViewById(R.id.date);
            txt1 = itemView.findViewById(R.id.image);
RL = itemView.findViewById(R.id.relative);
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


