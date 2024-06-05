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
import com.example.ishaq_buildingrenttoolsapp.UserReturnToolFile;
import com.example.ishaq_buildingrenttoolsapp.UserToolRent;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterReturnedTools extends RecyclerView.Adapter <ImageAdapterReturnedTools.ImageViewHolder>{

    private Context mContext;
    private List<UserReturnToolFile> mUploads;

    public ImageAdapterReturnedTools(Context context, List<UserReturnToolFile> userUploads){
        mContext= context;
        mUploads= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item_check_return_tool, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserReturnToolFile uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Rented Tool: "+uploadCurrent.getReturntName());
        holder.textViewPrice.setText("Rented Price: "+uploadCurrent.getReturntPrice()+" OMR");
        holder.textViewRName.setText("Rentee Name: "+uploadCurrent.getReturnrName());
        holder.textViewRPhone.setText("Rentee Phone: "+uploadCurrent.getReturnrPhone());
        holder.textViewRAddress.setText("Rentee Address: "+uploadCurrent.getReturnrAddress());
        holder.textViewRDays.setText("Rentee Name: "+uploadCurrent.getReturnrDays());
        holder.textViewRentDate.setText("Rent Date: "+uploadCurrent.getReturnrentDate());
        holder.textViewReturnDate.setText("Return Date: "+uploadCurrent.getReturnreturnDate());
        holder.textViewRPaid.setText("Paid and Tool Returned");
        Picasso.get()
                .load(uploadCurrent.getReturntUrl())
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
        public TextView textViewRDays;
        public TextView textViewRAddress;
        public TextView textViewRPaid;
        public TextView textViewRentDate;
        public TextView textViewReturnDate;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name7);
            textViewPrice= itemView.findViewById(R.id.image_view_price7);
            textViewRName= itemView.findViewById(R.id.image_view_r_name7);
            textViewRPhone= itemView.findViewById(R.id.image_view_r_phone7);
            textViewRAddress= itemView.findViewById(R.id.image_view_r_address7);
            textViewRDays= itemView.findViewById(R.id.image_view_r_days7);
            textViewRPaid= itemView.findViewById(R.id.image_view_paid7);
            textViewRentDate= itemView.findViewById(R.id.image_view_rent_date7);
            textViewReturnDate= itemView.findViewById(R.id.image_view_retrun_date7);
            imageView= itemView.findViewById(R.id.image_view_uploaded7);

        }
    }
}
