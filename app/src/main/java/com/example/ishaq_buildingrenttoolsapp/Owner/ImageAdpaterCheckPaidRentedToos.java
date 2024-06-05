package com.example.ishaq_buildingrenttoolsapp.Owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishaq_buildingrenttoolsapp.R;
import com.example.ishaq_buildingrenttoolsapp.UserToolRent;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdpaterCheckPaidRentedToos extends RecyclerView.Adapter <ImageAdpaterCheckPaidRentedToos.ImageViewHolder>{

    private Context mContext;
    private List<UserToolRent> mUploads;

    public ImageAdpaterCheckPaidRentedToos(Context context, List<UserToolRent> userUploads){
        mContext= context;
        mUploads= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item_check_paid_rented_tools, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserToolRent uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Rented Tool: "+uploadCurrent.gettName());
        holder.textViewPrice.setText("Rented Price: "+uploadCurrent.gettPrice()+" OMR");
        holder.textViewRName.setText("Rentee Name: "+uploadCurrent.getrName());
        holder.textViewRentDate.setText("Rent Date: "+uploadCurrent.getRentDate());
        holder.textViewReturnDate.setText("Return Date: "+uploadCurrent.getReturnDate());
        holder.textViewPaid.setText("Tool Rent Paid");
        Picasso.get()
                .load(uploadCurrent.gettUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewPrice;
        public TextView textViewRName;
        public TextView textViewRentDate;
        public TextView textViewReturnDate;
        public TextView textViewPaid;

        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name5);
            textViewPrice= itemView.findViewById(R.id.image_view_price5);
            textViewRName= itemView.findViewById(R.id.image_view_r_name5);
            textViewRentDate= itemView.findViewById(R.id.image_view_rent_date5);
            textViewReturnDate= itemView.findViewById(R.id.image_view_retrun_date5);
            textViewPaid= itemView.findViewById(R.id.image_view_paid5);
            imageView= itemView.findViewById(R.id.image_view_uploaded5);

        }
    }
}
