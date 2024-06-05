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

public class ImageAdapterCheckToolRenter extends RecyclerView.Adapter <ImageAdapterCheckToolRenter.ImageViewHolder>{

    private Context mContext;
    private List<UserToolRent> mUploads;

    public ImageAdapterCheckToolRenter(Context context, List<UserToolRent> userUploads){
        mContext= context;
        mUploads= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item_check_tool_rentee, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserToolRent uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Rented Tool: "+uploadCurrent.gettName());
        holder.textViewPrice.setText("Rented Price: "+uploadCurrent.gettPrice()+" OMR");
        holder.textViewRName.setText("Rentee Name: "+uploadCurrent.getrName());
        holder.textViewRPhone.setText("Rentee Phone: "+uploadCurrent.getrPhone());
        holder.textViewRAddress.setText("Rentee Address: "+uploadCurrent.getrAddress());
        holder.textViewRentDate.setText("Rent Date: "+uploadCurrent.getRentDate());
        holder.textViewReturnDate.setText("Return Date: "+uploadCurrent.getReturnDate());
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
        public TextView textViewRPhone;
        public TextView textViewRAddress;
        public TextView textViewRentDate;
        public TextView textViewReturnDate;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name6);
            textViewPrice= itemView.findViewById(R.id.image_view_price6);
            textViewRName= itemView.findViewById(R.id.image_view_r_name6);
            textViewRPhone= itemView.findViewById(R.id.image_view_r_phone6);
            textViewRAddress= itemView.findViewById(R.id.image_view_r_address6);
            textViewRentDate= itemView.findViewById(R.id.image_view_rent_date6);
            textViewReturnDate= itemView.findViewById(R.id.image_view_retrun_date6);
            imageView= itemView.findViewById(R.id.image_view_uploaded6);

        }
    }
}
